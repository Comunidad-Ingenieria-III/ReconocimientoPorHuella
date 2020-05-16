package personalSalud.personalsaludformulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
import datospersona.formulariopersona.ControladorFormularioPersona;
import institucionAcademica.dao.FacadeInstitucionAcademica;
import institucionAcademica.dto.InstitucionAcademica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personalSalud.personalsaludfacade.PersonalSaludFacade;
import personal_salud_titulo.psdto.PsDto;
import personal_salud_titulo.psfacade.PsFacade;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorPersonalSalud implements Initializable {

    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeCargo facadeCargo = new FacadeCargo();
    FacadeInstitucionAcademica facadeInstitucionAcademica = new FacadeInstitucionAcademica();
    FacadeTtAcademico facadeTtAcademico = new FacadeTtAcademico();
    private final boolean estado = true;


    @FXML
    private ComboBox<DtoTipoDocumento> cmb_tipodocumento;
    @FXML
    private TextField tf_numerodocumento,tf_nombre1,tf_nombre2,tf_apellido1,tf_apellido2,tf_numtelefono,tf_correoelectronico;
    @FXML
    private ComboBox<String> cmb_sexo;
    @FXML
    private ComboBox<Cargo> cmb_cargo;

    //------------------------------------------------------------------------
    @FXML
    private DatePicker dp_fechatitulacion;
    @FXML
    private ComboBox<PersonalSalud> cbx_idpersona;
    @FXML
    private ComboBox<TtAcademico> cbx_idtipotitulo;
    @FXML
    private ComboBox<InstitucionAcademica> cbx_idinstitucion;

    private ObservableList<PsDto> titulos;

    @FXML
    private TableView<PsDto> tb_personal;
    @FXML
    private TableColumn<PsDto, Integer> colIdPst;
    @FXML
    private TableColumn<PsDto, String> colIdPersonal,colIdTipoTitu,colIdIntitucion;
    @FXML
    private TableColumn<PsDto, Date> colFechaTitulacion;

    //---------------------------------------------------------------------------

    @FXML
    private Button bt_crear,bt_consultar,bt_cancelar,bt_salir,bt_guardar,bt_modificar,bt_inhabilitar;

    //----------------------------------
    //Botones que pertenecen a la tabla personal_salud_titulo
    @FXML
    private Button bt_agregar,bt_ModificarTabla,bt_eliminarTabla,bt_SalirTabla;
    //----------------------------------

    @FXML
    private Label lbl_tipoDocumeto,lblDocumento,lbl_correo,lbl_nombre1,lbl_apellido1,lbl_sexo,lbl_numtelefono;
    @FXML
    private int valor = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titulos = FXCollections.observableArrayList();
        tb_personal.setItems(titulos);

        initializeTableColumn();
        manejarEventosTablaTitulos();
        iniciarCbxDocumento();
        iniciarCbxSexo();
        iniciarCargo();
        deshabilitarBotones();
        deshabilitarCampos();
        iniciarCbxTipoTitulo();
        iniciarCbxid_persona();
        iniciarInstitucion();
        validarId();
        colocarImagenBotones();

    }


    public void initializeTableColumn() {
        colIdPersonal.setCellValueFactory(new PropertyValueFactory<>("idPersonal"));
        colIdTipoTitu.setCellValueFactory(new PropertyValueFactory<>("idTipoTitu"));
        colIdIntitucion.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colFechaTitulacion.setCellValueFactory(new PropertyValueFactory<>("fechaTitulacion"));
    }


    @FXML
    public void modificarRegistroDeTabla() {//Metodo que permite editar los registros de la tabla y asi poder actualizar en la base de datos
        //if(dp_fechatitulacion.getValue() == null || cbx_idtipotitulo.getSelectionModel().isEmpty() || cbx_idinstitucion.getSelectionModel().isEmpty() ){
           // Alert msg = new Alert(Alert.AlertType.ERROR);
            //msg.setTitle("Gestiones - Personal Salud");
            //msg.setContentText("Debe seleccionar un registro de la tabla ");
           // msg.show();


       // }else{


        PsDto psDto = new PsDto(
                tb_personal.getSelectionModel().getSelectedItem().getId(),
                cbx_idpersona.getSelectionModel().getSelectedItem().getIdPersonal(),
                cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico(),
                cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                //java.sql.Date.valueOf(dp_fechatitulacion.getValue())
                Date.valueOf(dp_fechatitulacion.getValue()),
                estado
        );

        DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");//Formato de fecha para mostrarle al usuario.
        String convertido = fechaHora.format(psDto.getFechaTitulacion());//Formatear una fecha Date a String

        if (psDto.getFechaTitulacion().after(new java.util.Date())) {//Condicional que verifica si la fecha seleccionada es superior a la actual
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Debe ingresar una fecha valida: \n" + "La fecha: " + convertido + " es superior a la fecha de hoy");
            msg.show();
            dp_fechatitulacion.requestFocus();
        } else {
            boolean resultado = recorrerTablaTitulos(titulos, psDto);
            if (resultado) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("El registro ya existe ");
                msg.show();
            } else {
                personalSaludFacade.modificarPsdto(psDto);
                titulos.set(tb_personal.getSelectionModel().getSelectedIndex(), psDto);
                limpiarComponentes();
                cbx_idpersona.setValue(null);
                bt_agregar.setDisable(false);
                bt_ModificarTabla.setDisable(true);
                bt_eliminarTabla.setDisable(true);

            }

        }
    //    }
    }


    @FXML
    public void agregarTitulos() {//Metodo para agregar titulos a la tabla personal-salud-titulo de la BBDD
        // y a la tabla del formulario previamente validados para evitar registros dulicados

         if(cbx_idtipotitulo.getSelectionModel().isEmpty()){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Debe seleccionar una tipo de título");
            msg.show();
            cbx_idtipotitulo.requestFocus();

        }else if(cbx_idinstitucion.getSelectionModel().isEmpty()){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Debe seleccionar una institución");
            msg.show();
            cbx_idinstitucion.requestFocus();

         } else if(dp_fechatitulacion.getValue() == null){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Debe seleccionar una fecha");
            msg.show();
            dp_fechatitulacion.requestFocus();

        } else {

             PsDto psDto = new PsDto(
                     0,
                     cbx_idpersona.getSelectionModel().getSelectedItem().getIdPersonal(),
                     cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico(),
                     cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                     //java.sql.Date.valueOf(dp_fechatitulacion.getValue())
                     Date.valueOf(dp_fechatitulacion.getValue()),
                     estado
             );

             DateFormat date = new SimpleDateFormat("dd-MM-yyyy");//Formato de fecha para mostrarle al usuario.
             String dateConverted = date.format(psDto.getFechaTitulacion());//Formatear una fecha Date a String

             if (psDto.getFechaTitulacion().after(new java.util.Date())) {//Condicional que verifica si la fecha seleccionada es superior a la actual
                 Alert msg = new Alert(Alert.AlertType.ERROR);
                 msg.setTitle("Gestiones - Personal Salud");
                 msg.setContentText("Debe ingresar una fecha valida: \n" + "La fecha: " + dateConverted + " es superior a la fecha de hoy");
                 msg.show();
                 dp_fechatitulacion.requestFocus();
             } else {


                 if (titulos.isEmpty()) {
                     //Validamos si la lista que esta asignada a la tabla, esta vacia
                     boolean res = buscarDocumento(psDto.getIdPersonal());
                     if (res) {
                         personalSaludFacade.agregarPsdto(psDto);
                         titulos.add(psDto);
                         limpiarComponentes();
                     }

                 } else {
                     boolean resultado = recorrerTablaTitulos(titulos, psDto);
                     if (resultado) {
                         Alert msg = new Alert(Alert.AlertType.ERROR);
                         msg.setTitle("Gestiones - Personal Salud");
                         msg.setContentText("El registro ya existe ");
                         msg.show();
                     } else {
                         personalSaludFacade.agregarPsdto(psDto);
                         titulos.add(psDto);
                         limpiarComponentes();

                     }

                 }
             }
         }
    }



    public boolean recorrerTablaTitulos(List<PsDto> titulos, PsDto psDto) {//Método para recorrer la tabla con el fin de no ingresar registros duplicados
        boolean resultado = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).getIdPersonal().equals(psDto.getIdPersonal()) && titulos.get(i).getIdTipoTitu().equals(psDto.getIdTipoTitu())
                    && titulos.get(i).getIdInstitucion().equals(psDto.getIdInstitucion()
            )) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }//fin del metodo recorrerTablaTitulos()

    @FXML
    public void eliminarTitulos() {//Metodo para eliminar el registro seleccionado en la tabla
        int idPs = tb_personal.getSelectionModel().getSelectedItem().getId();//Extraemos en id del psDto, desde la fila seleccionada en la tabla
        boolean estado = false;
        personalSaludFacade.eliminarPsdto(idPs, estado);
        titulos.remove(tb_personal.getSelectionModel().getFocusedIndex());

        if (titulos.isEmpty()){
            bt_agregar.setDisable(false);
            bt_ModificarTabla.setDisable(true);
            bt_eliminarTabla.setDisable(true);
        }
        limpiarComponentes();
    }


    public void limpiarComponentes() {//Funcion para limpiar los componentes de la tabla del formulario Personal-salud
        cbx_idtipotitulo.setValue(null);
        cbx_idinstitucion.setValue(null);
        dp_fechatitulacion.setValue(null);
        bt_agregar.setDisable(false);
        bt_ModificarTabla.setDisable(true);
        bt_eliminarTabla.setDisable(true);
    }

    @FXML
    private PersonalSalud crearPersonalSalud() {//funcion que crea un objeto de tipo PersonalSalud

        String idPersonal = tf_numerodocumento.getText();
        String nombre1 = tf_nombre1.getText();
        String nombre2 = tf_nombre2.getText();
        String apellido1 = tf_apellido1.getText();
        String apellido2 = tf_apellido2.getText();
        String sexo = cmb_sexo.getValue();
        String telefono = tf_numtelefono.getText();
        String email = tf_correoelectronico.getText();
        String tipoDocumento = cmb_tipodocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        String cargo = cmb_cargo.getSelectionModel().getSelectedItem().getIdCargo();

        PersonalSalud personal = new PersonalSalud(idPersonal, nombre1, nombre2, apellido1, apellido2,
                sexo, telefono, email, tipoDocumento, cargo, estado);

        return personal;
    }


    public boolean buscarDocumento(String idPersonal) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = personalSaludFacade.buscarPorId(idPersonal);
        boolean resultado;
        if (documento) {
            resultado = true;
        } else {
            resultado = false;

        }
        return resultado;
    }

    @FXML
    public void guardarPersonal() {//Metodo Para guardar o modificar un registro de personal salud en la BBDD
        boolean documento = buscarDocumento(tf_numerodocumento.getText());//Se Realiza la busqueda del registro en la base de datos


        //De esto depende si se agrega o se modifica el registro
        if (!documento) {//Esta condición se cumple solo si el registro no existe en la BBDD, asi se podra guardar
            if(tf_numerodocumento.getText().isEmpty() || tf_nombre1.getText().isEmpty() || tf_apellido1.getText().isEmpty() || tf_numtelefono.getText().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();

                if(tf_numerodocumento.getText().isEmpty()){
                    tf_numerodocumento.requestFocus();

                } else if(tf_nombre1.getText().isEmpty()){
                    tf_nombre1.requestFocus();
                } else if(tf_apellido1.getText().isEmpty()){
                    tf_apellido1.requestFocus();
                }else if(tf_numtelefono.getText().isEmpty()){
                    tf_numtelefono.requestFocus();

                }


            } else if(cmb_tipodocumento.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un tipo de documento");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_tipodocumento.requestFocus();

            }else if(cmb_sexo.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un sexo");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_sexo.requestFocus();

            }else if(cmb_cargo.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un cargo");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_cargo.requestFocus();
            }

            else{


            int res = personalSaludFacade.agregarPersonal2(crearPersonalSalud());
            if (res == 1) {

                iniciarCbxid_persona();
                tf_numerodocumento.setDisable(true);
                tf_nombre1.setDisable(true);
                tf_nombre2.setDisable(true);
                tf_apellido1.setDisable(true);
                tf_apellido2.setDisable(true);
                cmb_sexo.setDisable(true);
                tf_numtelefono.setDisable(true);
                tf_correoelectronico.setDisable(true);
                cmb_tipodocumento.setDisable(true);
                cmb_cargo.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                alert.setTitle("Gestiones - Personal Salud");
                alert.setHeaderText("Elija una opción");
                alert.setContentText("Registro agregado correctamente\n \n" + "Desea ingresar títulos académicos ?");
                Optional<ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.YES) {
                    tb_personal.setDisable(false);
                    cbx_idpersona.setDisable(false);
                    cbx_idtipotitulo.setDisable(false);
                    cbx_idinstitucion.setDisable(false);
                    dp_fechatitulacion.setDisable(false);
                    bt_agregar.setDisable(false);
                    bt_SalirTabla.setDisable(false);
                    cbx_idpersona.setValue(personalSaludFacade.buscarPorIdPersonal(tf_numerodocumento.getText()));
                    cbx_idpersona.setDisable(true);
                } else if (action.get() == ButtonType.NO) {
                    deshabilitarCampos();
                    deshabilitarBotones();
                    limpiar();
                }
            } else {

                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("No fue posible agregar el registro");
                msg.setHeaderText("Error.");
                msg.show();
                }
            }

        } else {//Condicional para modificar si el registro ya esta creado
            if(tf_numerodocumento.getText().isEmpty() || tf_nombre1.getText().isEmpty() || tf_apellido1.getText().isEmpty() || tf_numtelefono.getText().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();

                if(tf_nombre1.getText().isEmpty()){
                    tf_nombre1.requestFocus();
                } else if(tf_apellido1.getText().isEmpty()){
                    tf_apellido1.requestFocus();
                }else if(tf_numtelefono.getText().isEmpty()){
                    tf_numtelefono.requestFocus();

                }

            } else if(cmb_tipodocumento.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un tipo de documento");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_sexo.requestFocus();

            }else if(cmb_sexo.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un sexo");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_sexo.requestFocus();

            }else if(cmb_cargo.getSelectionModel().isEmpty()){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Debe seleccionar un cargo");
                msg.setHeaderText("Resultado");
                msg.show();
                cmb_cargo.requestFocus();
            }else{




            int res = personalSaludFacade.modificarPersonal2(crearPersonalSalud());

            if (res == 1) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Registro modificado correctamente");
                msg.setHeaderText("Exito.");
                msg.show();

                titulos.clear();
                deshabilitarCampos();
                deshabilitarBotones();
                limpiarComponentes();
                tb_personal.setDisable(false);
                cbx_idpersona.setValue(null);
                limpiar();
            } else {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("No fue posible modificar el registro ");
                msg.setHeaderText("Error.");
                msg.show();
            }

            }

        }


    }
    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarPersonalTitulo();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }



    @FXML
    public void consultarPersonalTitulo() {//Metodo que realiza la consulta de una persona a la BBDD por medio de su cedula, devolviendo todas sus
        //caracteristicas y registros que esta cedula tenga relacionados en la tabla personal_salud_titulo
        if (tf_numerodocumento.getText().isEmpty()) {
            tf_numerodocumento.setDisable(false);
            tf_nombre1.setDisable(true);
            tf_nombre2.setDisable(true);
            tf_apellido1.setDisable(true);
            tf_apellido2.setDisable(true);
            cmb_sexo.setDisable(true);
            tf_correoelectronico.setDisable(true);
            tf_numtelefono.setDisable(true);
            cmb_cargo.setDisable(true);
            tf_numerodocumento.requestFocus();
            bt_modificar.setDisable(false);
            bt_inhabilitar.setDisable(false);
            cbx_idtipotitulo.setDisable(true);
            cbx_idinstitucion.setDisable(true);
            dp_fechatitulacion.setDisable(true);
            valor=0;

        } else {
            //Variable que almacena el resultado de la busqueda de un personal salud por medio de su clave primaria
            //Si el Numero de documento existe en la base de datos ejecuta la consulta, sino manda un error
            boolean documentoBuscado = personalSaludFacade.buscarPorId(tf_numerodocumento.getText());
            if (documentoBuscado) {

                BusquedaDePersonal busqueda = personalSaludFacade.buscarPersonalTitulos(tf_numerodocumento.getText());
                PersonalSalud personalSalud = busqueda.getPersonalSalud();

                if (!personalSalud.isEstado()) {//Condicional que verifica si el objeto personal_salud
                    // que se acaba de recuperar esta en estado inactivo en la BBDD
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.setTitle("Gestiones - Personal Salud");
                    msg.setContentText("El registro se encuentra inactivo \n"
                            + "Comuniquese con el adminsitrador del sistema para activar de nuevo este registro");
                    msg.setHeaderText("Advertencia.");
                    msg.show();
                    tf_numerodocumento.requestFocus();
                } else {

                    tf_nombre1.setText(personalSalud.getNombre1());
                    tf_nombre2.setText(personalSalud.getNombre2());
                    tf_apellido1.setText(personalSalud.getApellido1());
                    tf_apellido2.setText(personalSalud.getApellido2());
                    cmb_sexo.setValue(personalSalud.getSexo());
                    tf_numtelefono.setText(personalSalud.getTelefono());
                    tf_correoelectronico.setText(personalSalud.getEmail());

                    cmb_cargo.setValue(facadeCargo.obtenerPorId(personalSalud.getCargo()));
                    cmb_tipodocumento.setValue(facadeTipoDocumento.obtenerPorId(personalSalud.getTipoDocumento()));

                    tf_numerodocumento.setDisable(true);
                    cbx_idpersona.setDisable(true);

                    ObservableList<PsDto> titulosActivos;//Lista para recibir los titulos desde la BBDD
                    titulosActivos = FXCollections.observableArrayList(busqueda.getListaTitulos());

                    cbx_idpersona.setValue(personalSaludFacade.buscarPorIdPersonal(tf_numerodocumento.getText()));

                    for (int i = 0; i < titulosActivos.size(); i++) {
                        if (titulosActivos.get(i).isEstado())//Ciclo para recorrer la lista de objetos de personal-salud-titulo, para agregar a la lista que estara
                            //enlazada con ltb_personal solamente los que se encuentren en estado activo.
                            titulos.add(titulosActivos.get(i));
                    }

                    tb_personal.setItems(titulos);

                    if (titulos.isEmpty()){
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Gestiones - Personal Salud");
                        msg.setContentText("Esta persona no tiene titulos asignados o se encuentran inactivos.");
                        msg.setHeaderText("Información.");
                        msg.show();
                    }

                    initializeTableColumn();
                    bt_consultar.setDisable(true);
                    bt_crear.setDisable(false);

                }
            } else {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Ingrese un número de cedula valido!");
                msg.setHeaderText("Error.");
                msg.show();
                tf_numerodocumento.requestFocus();
            }
        }

    }

    @FXML
    public void eliminar() {
        boolean respuesta = personalSaludFacade.eliminarPersonal(tf_numerodocumento.getText());
        if (respuesta) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error al eliminar! \n" + "El documento tiene registros dependientes!\n"
                    + "Asegurese de eliminar los registros que dependen de este.");
            msg.setHeaderText("Error.");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Registro eliminado correctamente");
            msg.setHeaderText("Error.");
            msg.show();

        }

    }

    public void manejarEventosTablaTitulos() {//Metodo para que cuando se seleccione un registro de la tabla se asigne a los componentes

        tb_personal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PsDto>() {
            @Override
            public void changed(ObservableValue<? extends PsDto> observable, PsDto oldValue, PsDto newValue) {
                if (newValue != null) {
                    cbx_idpersona.getSelectionModel().select(personalSaludFacade.buscarPorIdPersonal(
                            tb_personal.getSelectionModel().getSelectedItem().getIdPersonal()));

                    cbx_idtipotitulo.getSelectionModel().select(facadeTtAcademico.obtenerPorId(
                            tb_personal.getSelectionModel().getSelectedItem().getIdTipoTitu()));

                    cbx_idinstitucion.getSelectionModel().select(facadeInstitucionAcademica.obtenerPorId(
                            tb_personal.getSelectionModel().getSelectedItem().getIdInstitucion()));

                    dp_fechatitulacion.setValue(newValue.getFechaTitulacion().toLocalDate());

                    bt_agregar.setDisable(true);
                    bt_ModificarTabla.setDisable(false);
                    bt_eliminarTabla.setDisable(false);

                }

            }
        });//FIN DEL LISTENER
    }


    @FXML
    public void validarId() {//Metodo para validar que el Id del cargo solo reciba numeros
        tf_numerodocumento.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
        tf_numtelefono.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
    }

    @FXML
    private void validarCamposVacios() {
        validarCampoCorreo();

       /* if (!cmb_tipodocumento.getSelectionModel().getSelectedItem().getNombreTipoDocumento().contains("Seleccione")==true) {
            lbl_tipoDocumeto.setText("Campo Requerido");
            System.out.println("por aqui voy");
        } else {
            lbl_tipoDocumeto.setText("");
        }*/

        if (tf_numerodocumento.getText().isEmpty()) {
            lblDocumento.setText("Ingresa solo Números");

        } else {
            lblDocumento.setText("");
        }
        if (tf_nombre1.getText().isEmpty()) {
            lbl_nombre1.setText("Campo Requerido");
        } else {
            lbl_nombre1.setText("");
        }
        if (tf_apellido1.getText().isEmpty()) {
            lbl_apellido1.setText("Campo Requerido");
        } else {
            lbl_apellido1.setText("");
        }

        if (tf_numtelefono.getText().isEmpty()) {
            lbl_numtelefono.setText("Ingresa solo Números");
        } else {
            lbl_numtelefono.setText("");
        }
        /*if (cmb_sexo.getSelectionModel().getSelectedItem().equals("Seleccione")) {
            lbl_sexo.setText("Campo Requerido");
        } else {
            lbl_sexo.setText("");
        }*/
        if (tf_numerodocumento.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
            bt_guardar.setDisable(true);
        } else {
            bt_guardar.setDisable(false);
        }
    }

    @FXML
    private void validarCampoCorreo() {

        if (tf_correoelectronico.getText().isEmpty()) {
            lbl_correo.setText("Campo Requerido");
        } else if (!tf_correoelectronico.getText().contains("@") || !tf_correoelectronico.getText().contains(".")) {
            lbl_correo.setText("Correo Invalido");
        } else {
            lbl_correo.setText("");
        }
    }

    @FXML
    private void eventoCrear() {
        bt_crear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                limpiar();
                habilitarCampos();
                bt_inhabilitar.setDisable(true);
                bt_modificar.setDisable(true);
                bt_guardar.setDisable(false);
                cmb_tipodocumento.requestFocus();
                titulos.clear();
                valor = 1;
            }
        });
    }


    @FXML
    public void validar() {

        cmb_tipodocumento.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }
        });

        tf_numerodocumento.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }

        });
        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }

        });

        tf_apellido1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }

        });

        cmb_sexo.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }
        });

        tf_correoelectronico.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });

        tf_numtelefono.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });

    }


    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Masculino", "Femenino");
        cmb_sexo.setItems(items);

    }

    @FXML
    public void iniciarCbxDocumento() {
        ObservableList<DtoTipoDocumento> listatipodocumento = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());
        cmb_tipodocumento.setItems(listatipodocumento);
    }

    @FXML
    public void iniciarCbxid_persona() {

        ObservableList<PersonalSalud> listaIdpersona = FXCollections.observableArrayList(personalSaludFacade.obtenerTodoPersonalSalud());
        for (int i = 0; i < listaIdpersona.size(); i++) {
            if (!listaIdpersona.get(i).isEstado())//Ciclo para recorrer la lista de objetos de personal-salud, y eliminar los registros que tengan
                //el estado inactivo en la BBDD
                listaIdpersona.remove(i);//si el estado esta inactivo, se elimina de la lista
        }
        cbx_idpersona.setItems(listaIdpersona);
    }

    @FXML
    public void iniciarCargo() {
        ObservableList<Cargo> listaCargo = FXCollections.observableArrayList(facadeCargo.obtenerCargos());
        cmb_cargo.setItems(listaCargo);
    }


    @FXML
    public void iniciarCbxTipoTitulo() {
        ObservableList<TtAcademico> listatitulos = FXCollections.observableArrayList(facadeTtAcademico.obtenerTodosTitulosAcdemicos());
        cbx_idtipotitulo.setItems(listatitulos);
    }

    @FXML
    public void iniciarInstitucion() {
        ObservableList<InstitucionAcademica> listainstituciones = FXCollections.observableArrayList(facadeInstitucionAcademica.obtenerTodasInstituciones());
        cbx_idinstitucion.setItems(listainstituciones);
    }

    @FXML
    public void modificar() {//Metodo que se ejecuta al presionar el boton modificar
        cmb_tipodocumento.setDisable(false);
        tf_numerodocumento.setDisable(true);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        cmb_sexo.setDisable(false);
        tf_numtelefono.setDisable(false);
        tf_correoelectronico.setDisable(false);
        cmb_cargo.setDisable(false);
        tf_nombre1.requestFocus();
        valor = 0;            //Ingreso una variable Jose Martin campo
        bt_modificar.setDisable(true);
        bt_guardar.setDisable(false);
        //--------------------------------
        //Componentes de la tabla personal-salud-titulo
        cbx_idpersona.setDisable(true);
        cbx_idtipotitulo.setDisable(false);
        cbx_idinstitucion.setDisable(false);
        dp_fechatitulacion.setDisable(false);
        bt_agregar.setDisable(false);
        bt_SalirTabla.setDisable(false);
        tb_personal.setDisable(false);
        //---------------------------------
    }

    @FXML
    private void habilitarCampos() {

        cmb_tipodocumento.setDisable(false);
        tf_numerodocumento.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        cmb_sexo.setDisable(false);
        tf_numtelefono.setDisable(false);
        tf_correoelectronico.setDisable(false);
        cmb_cargo.setDisable(false);
        tf_numerodocumento.requestFocus();
    }

    @FXML
    private void deshabilitarBotones() {

        bt_crear.setDisable(false); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);

        //Componentes de la tabla personal-salud-titulo
        //------------------------------
        cbx_idpersona.setDisable(true);
        cbx_idtipotitulo.setDisable(true);
        cbx_idinstitucion.setDisable(true);
        dp_fechatitulacion.setDisable(true);
        bt_agregar.setDisable(true);
        bt_ModificarTabla.setDisable(true);
        bt_eliminarTabla.setDisable(true);
        bt_SalirTabla.setDisable(true);
        //-------------------------------

    }

    @FXML
    public void cancelar() {
        cbx_idpersona.setValue(null);
        cmb_tipodocumento.setValue(null);
        tf_numerodocumento.setText("");
        tf_nombre1.setText("");
        tf_nombre2.setText("");
        tf_apellido1.setText("");
        tf_apellido2.setText("");
        cmb_sexo.setValue(null);
        tf_numtelefono.setText("");
        tf_correoelectronico.setText("");
        cmb_cargo.setValue(null);
        tf_numerodocumento.setText("");
        bt_crear.setDisable(false);
        bt_consultar.setDisable(false);
        titulos.clear();
        lbl_numtelefono.setText("");
        lbl_nombre1.setText("");
        lbl_apellido1.setText("");
        lbl_correo.setText("");
        lbl_tipoDocumeto.setText("");
        deshabilitarCampos();
        deshabilitarBotones();

    }

    @FXML
    public void limpiar() {

        cmb_tipodocumento.setValue(null);
        tf_numerodocumento.setText("");
        tf_nombre1.setText("");
        tf_nombre2.setText("");
        tf_apellido1.setText("");
        tf_apellido2.setText("");
        cmb_sexo.setValue(null);
        tf_numtelefono.setText("");
        tf_correoelectronico.setText("");
        cmb_cargo.setValue(null);
        tf_numerodocumento.setText("");
        titulos.clear();

    }

    @FXML
    private void deshabilitarCampos() {
        cmb_tipodocumento.setDisable(true);
        tf_numerodocumento.setDisable(true);
        tf_nombre1.setDisable(true);
        tf_nombre2.setDisable(true);
        tf_apellido1.setDisable(true);
        tf_apellido2.setDisable(true);
        cmb_sexo.setDisable(true);
        tf_correoelectronico.setDisable(true);
        tf_numtelefono.setDisable(true);
        cmb_cargo.setDisable(true);
        tf_numerodocumento.requestFocus();
        tb_personal.setDisable(true);
    }
    //  Inicio modificación jose Martin

    public void validarExistente() {

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE() {
        if (valor == 1) {

            boolean busqueda = buscarDocumento(tf_numerodocumento.getText());
            if (busqueda) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Ya existe una persona con el documento Nro:\n" + tf_numerodocumento.getText() + " asignado");
                msg.setHeaderText("Error.");
                msg.show();
                tf_numerodocumento.setText("");
                tf_nombre1.setText("");
                tf_numerodocumento.requestFocus();


            }
        }

    }

    public void validarDocumentoIdentidad() {//Metodo Que maneja un evento en la caja de texto primernombre para la validacion
        //del documento de identidad de la persona.

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean busqueda = buscarDocumento(tf_numerodocumento.getText());
                if (busqueda) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Personal Salud");
                    msg.setContentText("Ya existe una Persona con el documento Nro:\n" + tf_numerodocumento.getText() + " asignado");
                    msg.setHeaderText("Error.");
                    msg.show();
                    tf_numerodocumento.setText("");
                    tf_nombre1.setText("");
                    tf_numerodocumento.requestFocus();
                }

            }
        });
    }
    public void colocarImagenBotones(){

        URL linkbt_guardar = getClass().getResource("/imagenes/disquete.png");
        URL linkbt_modificar = getClass().getResource("/imagenes/btGuardar.png");
        URL linkbt_inhabilitar = getClass().getResource("/imagenes/eliminar.png");
        URL linkbt_refrescar = getClass().getResource("/imagenes/refrescar.png");

        Image imagenGuardar = new Image(linkbt_guardar.toString(),25,25,false,true);
        bt_agregar.setGraphic(new ImageView(imagenGuardar));

        Image imagenModificar = new Image(linkbt_modificar.toString(),25,25,false,true);
        bt_ModificarTabla.setGraphic(new ImageView(imagenModificar));

        Image imagenInhabilitar = new Image(linkbt_inhabilitar.toString(),25,25,false,true);
        bt_eliminarTabla.setGraphic(new ImageView(imagenInhabilitar));

        Image imagenSalir = new Image(linkbt_refrescar.toString(),25, 25, false, true);
        bt_SalirTabla.setGraphic(new ImageView(imagenSalir));


    }

    @FXML
    private void cerraPersonalSalud(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();

    }
}
