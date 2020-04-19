package personalSalud.personalsaludformulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
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
import java.util.List;
import java.util.ResourceBundle;

public class ControladorPersonalSalud implements Initializable {

    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeCargo facadeCargo = new FacadeCargo();
    FacadeInstitucionAcademica facadeInstitucionAcademica = new FacadeInstitucionAcademica();
    PsFacade psFacade = new PsFacade();
    FacadeTtAcademico facadeTtAcademico = new FacadeTtAcademico();


    @FXML
    private ComboBox<DtoTipoDocumento> cmb_tipodocumento;
    @FXML
    private TextField tf_numerodocumento;
    @FXML
    private TextField tf_nombre1;
    @FXML
    private TextField tf_nombre2;
    @FXML
    private TextField tf_apellido1;
    @FXML
    private TextField tf_apellido2;
    @FXML
    private ComboBox<String> cmb_sexo;
    @FXML
    private TextField tf_numtelefono;
    @FXML
    private TextField tf_correoelectronico;
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
    private TableColumn<PsDto, String> colIdPersonal;
    @FXML
    private TableColumn<PsDto, String> colIdTipoTitu;
    @FXML
    private TableColumn<PsDto, String> colIdIntitucion;
    @FXML
    private TableColumn<PsDto, Date> colFechaTitulacion;

    //---------------------------------------------------------------------------

    @FXML
    private Button bt_crear;
    @FXML
    private Button bt_consultar;
    @FXML
    private Button bt_cancelar;
    @FXML
    private Button bt_salir;
    @FXML
    private Button bt_guardar;
    @FXML
    private Button bt_modificar;
    @FXML
    private Button bt_inhabilitar;
    @FXML
    private Button bt_abrirFormularioPer;
    @FXML
    private Button bt_agregar;
    @FXML
    private Button bt_eliminar;
    @FXML
    private Button bt_ModificarTabla;
    @FXML
    private MenuItem mi_agregar;
    @FXML
    private MenuItem mi_modificar;
    @FXML
    private MenuItem mi_eliminar;
    @FXML
    private Label lbl_tipoDocumeto;
    @FXML
    private Label lblDocumento;
    @FXML
    private Label lbl_correo;
    @FXML
    private Label lbl_nombre1;
    @FXML
    private Label lbl_apellido1;
    @FXML
    private Label lbl_sexo;
    @FXML
    private Label lbl_numtelefono;
    @FXML
    private int valor=1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titulos = FXCollections.observableArrayList();

        tb_personal.setItems(titulos);

        initializeTableColumn();

        iniciarCbxDocumento();
        iniciarCbxSexo();
        iniciarCargo();
        deshabilitarBotones();
        deshabilitarCampos();
        //iniciarCbxPersona();
        iniciarCbxTipoTitulo();
        iniciarInstitucion();
        validarId();

    }


    public void initializeTableColumn(){
        colIdPersonal.setCellValueFactory(new PropertyValueFactory<>("idPersonal"));
        colIdTipoTitu.setCellValueFactory(new PropertyValueFactory<>("idTipoTitu"));
        colIdIntitucion.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colFechaTitulacion.setCellValueFactory(new PropertyValueFactory<>("fechaTitulacion"));
    }


    @FXML
    public void modificarRegistroDeTabla(){//Metodo que permite editar los registros de la tabla y asi poder actualizar en la base de datos
        PsDto psDto = new PsDto(
                tb_personal.getSelectionModel().getSelectedItem().getId(),
                tf_numerodocumento.getText(),
                cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico(),
                cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                //java.sql.Date.valueOf(dp_fechatitulacion.getValue())
                Date.valueOf(dp_fechatitulacion.getValue())
        );
        titulos.set(tb_personal.getSelectionModel().getSelectedIndex(),psDto);
        limpiarComponentes();
    }

    @FXML
    public void agregarTitulos() {//Metodo para agregar titulos a la tabla previamente validados para evitar registros dulicados
        PsDto psDto = new PsDto(
                0,
                tf_numerodocumento.getText(),
                cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico(),
                cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                //java.sql.Date.valueOf(dp_fechatitulacion.getValue())
                Date.valueOf(dp_fechatitulacion.getValue())
        );

        if (titulos.size() < 1) {
            titulos.add(psDto);
            limpiarComponentes();

        } else {
            boolean resultado = recorrerTablaTitulos(titulos, psDto);
            if (resultado) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("El Registro Ya Existe ");
                msg.show();
            } else {
                titulos.add(psDto);
                limpiarComponentes();

            }

        }
        manejarEventosTablaTitulos();
    }

    public boolean recorrerTablaTitulos(List<PsDto> titulos, PsDto psDto){//Metodo para recorrer la tabla con el fin de no ingresar regisstros duplicados
        boolean resultado = false;
        for (int i = 0; i<titulos.size(); i++){
            if (titulos.get(i).getIdPersonal().equals(psDto.getIdPersonal())&&titulos.get(i).getIdTipoTitu().equals(psDto.getIdTipoTitu())
                    &&titulos.get(i).getIdInstitucion().equals(psDto.getIdInstitucion()
                )){

                resultado = true;
                break;
            }
        }
        return resultado;
    }

    @FXML
    public void eliminarTitulos() {//Metodo para eliminar el registro seleccionado en la tabla
        titulos.remove(tb_personal.getSelectionModel().getFocusedIndex());
        limpiarComponentes();
    }


    public void limpiarComponentes() {
        cbx_idtipotitulo.setValue(null);
        cbx_idinstitucion.setValue(null);
        dp_fechatitulacion.setValue(null);
    }

    @FXML
    private PersonalSalud crearPersonalSalud() {

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
                sexo, telefono, email, tipoDocumento, cargo);

        return personal;
    }





    public boolean buscarDocumento(String idPersonal) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = personalSaludFacade.buscarPorId(idPersonal);
         boolean resultado;
        if (documento) {
            resultado = true;
        }else{
            resultado = false;

        }
        return  resultado;
    }

    @FXML

    public void guardarPersonalS(){//Metodo Para guardar o modificar un registro de personal salud en la BBDD
        boolean documento = buscarDocumento(tf_numerodocumento.getText());//Se Realiza la busqueda del registro en la base de datos
        //De esto depende si se agrega o se modifica
        if(!documento){

            int res = personalSaludFacade.agregarPersonal(crearPersonalSalud(), titulos);

            if (res == 1) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Registro Agregado Correctamente");
                msg.setHeaderText("Exito!");
                msg.show();
                limpiar();
                titulos.clear();
                deshabilitarCampos();
                deshabilitarBotones();
            } else {

                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("No Fue Pisible Agregar El Registro");
                msg.setHeaderText("Error.");
                msg.show();
            }

        }else{

            int res = personalSaludFacade.modificarPersonal(crearPersonalSalud(),titulos);

            if (res == 1) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Registro Modificado Correctamente");
                msg.setHeaderText("Exito!");
                msg.show();
                limpiar();
                titulos.clear();
                deshabilitarCampos();
                deshabilitarBotones();
            } else {

                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("No Fue Posible Modificar El Registro ");
                msg.setHeaderText("Error.");
                msg.show();
            }
        }

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
            mi_agregar.setDisable(true);
            mi_modificar.setDisable(true);
            mi_eliminar.setDisable(true);

        } else {

            BusquedaDePersonal busqueda = personalSaludFacade.buscarPersonalTitulos(tf_numerodocumento.getText());
            PersonalSalud personalSalud = busqueda.getPersonalSalud();

            tf_nombre1.setText(personalSalud.getNombre1());
            tf_nombre2.setText(personalSalud.getNombre2());
            tf_apellido1.setText(personalSalud.getApellido1());
            tf_apellido2.setText(personalSalud.getApellido2());
            cmb_sexo.setValue(personalSalud.getSexo());
            tf_numtelefono.setText(personalSalud.getTelefono());
            tf_correoelectronico.setText(personalSalud.getEmail());

            cmb_cargo.setValue(facadeCargo.obtenerPorId(personalSalud.getCargo()));
            cmb_tipodocumento.setValue(facadeTipoDocumento.obtenerPorId(personalSalud.getTipoDocumento()));

            titulos = FXCollections.observableArrayList(busqueda.getListaTitulos());

            tb_personal.setItems(titulos);
            initializeTableColumn();
            manejarEventosTablaTitulos();
            bt_consultar.setDisable(true);
            bt_crear.setDisable(true);

        }

    }
    public void manejarEventosTablaTitulos() {//Metodo para que cuando se seleccione un registro de la tabla se asigne a los componentes
        tb_personal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PsDto>() {
            @Override
            public void changed(ObservableValue<? extends PsDto> observable, PsDto oldValue, PsDto newValue) {
                if (newValue != null) {

                    cbx_idtipotitulo.getSelectionModel().select(facadeTtAcademico.obtenerPorId(
                            tb_personal.getSelectionModel().getSelectedItem().getIdTipoTitu()));
                    cbx_idinstitucion.getSelectionModel().select(facadeInstitucionAcademica.obtenerPorId(
                            tb_personal.getSelectionModel().getSelectedItem().getIdInstitucion()));
                    dp_fechatitulacion.setValue(newValue.getFechaTitulacion().toLocalDate());


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
                mi_agregar.setDisable(false);
                mi_modificar.setDisable(false);
                mi_eliminar.setDisable(false);
                cmb_tipodocumento.requestFocus();
                titulos.clear();
                valor=1;
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
    public void eliminarFamiliar() {

        int opcion = JOptionPane.showConfirmDialog(null, "Desea eliminar el"
                + "Registro?", "Confirmación", JOptionPane.YES_NO_OPTION, 2);
        if (opcion == JOptionPane.YES_OPTION) {
            String id = tf_numerodocumento.getText();
            personalSaludFacade.eliminarPersonal(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            eliminarTitulos();
        }
        limpiar();
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
        valor=0;            //Ingreso una variable Jose Martin campo
        bt_modificar.setDisable(true);
        mi_modificar.setDisable(false);
        bt_guardar.setDisable(false);
        mi_agregar.setDisable(false);
        mi_eliminar.setDisable(false);
        cbx_idtipotitulo.setDisable(false);
        cbx_idinstitucion.setDisable(false);
        dp_fechatitulacion.setDisable(false);
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
        cbx_idtipotitulo.setDisable(false);
        cbx_idinstitucion.setDisable(false);
        dp_fechatitulacion.setDisable(false);

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
        mi_modificar.setDisable(true);
        mi_agregar.setDisable(true);
        mi_eliminar.setDisable(true);
        cbx_idtipotitulo.setDisable(true);
        cbx_idinstitucion.setDisable(true);
        dp_fechatitulacion.setDisable(true);

    }

    @FXML
    public void cancelar() {

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
    }
    //  Inicio modificación jose Martin

    public void validarExistente(){

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){

            boolean busqueda = buscarDocumento(tf_numerodocumento.getText());
            if(busqueda){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Personal Salud");
                msg.setContentText("Ya Existe Una Persona Con El Documento Nro:\n" + tf_numerodocumento.getText() + " Asignado");
                msg.setHeaderText("Error.");
                msg.show();
                tf_numerodocumento.setText("");
                tf_nombre1.setText("");
                tf_numerodocumento.requestFocus();



            }
        }

    }

    public void validarDocumentoIdentidad(){//Metodo Que maneja un evento en la caja de texto primernombre para la validacion
        //del documento de identidad de la persona.

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                boolean busqueda = buscarDocumento(tf_numerodocumento.getText());
                if (busqueda) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Personal Salud");
                    msg.setContentText("Ya Existe Una Persona Con El Documento Nro:\n" + tf_numerodocumento.getText() + " Asignado");
                    msg.setHeaderText("Error.");
                    msg.show();
                    tf_numerodocumento.setText("");
                    tf_nombre1.setText("");
                    tf_numerodocumento.requestFocus();
                }

            }
        });
    }



    @FXML
    private void cerraPersonalSalud(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();

    }
}
