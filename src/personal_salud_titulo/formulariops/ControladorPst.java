package personal_salud_titulo.formulariops;

import datospersona.dto.Persona;
import datospersona.facade.FacadePersona;
import eps.dto.DtoEps;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import personalSalud.personalsaluddto.PersonalSalud;
import personalSalud.personalsaludfacade.PersonalSaludFacade;
import personal_salud_titulo.psdto.PsDto;
import personal_salud_titulo.psfacade.PsFacade;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class ControladorPst implements Initializable {

    FacadeInstitucionAcademica facadeInstitucionAcademica = new FacadeInstitucionAcademica();
    PsFacade psFacade = new PsFacade();
    FacadeTtAcademico facadeTtAcademico = new FacadeTtAcademico();
    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();


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
    private TableColumn<PsDto, String> colFechaTitulacion;


    @FXML
    private Button btnCrear;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button bntCancelar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnInhabilitar;

    @FXML
    private DatePicker dp_fechatitulacion;

    @FXML
    private ComboBox<PersonalSalud> cbx_idpersona;
    @FXML
    private ComboBox<TtAcademico> cbx_idtipotitulo;
    @FXML
    private ComboBox<InstitucionAcademica> cbx_idinstitucion;


    private ObservableList<PsDto> titulos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titulos = FXCollections.observableArrayList(psFacade.obtenerTodas());


        tb_personal.setItems(titulos);

        //colIdPst.setCellValueFactory(new PropertyValueFactory<>("idPst"));
        colIdPersonal.setCellValueFactory(new PropertyValueFactory<>("idPersonal"));
        colIdTipoTitu.setCellValueFactory(new PropertyValueFactory<>("idTipoTitu"));
        colIdIntitucion.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colFechaTitulacion.setCellValueFactory(new PropertyValueFactory<>("fechaTitulacion"));

        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        btnGuardar.setDisable(false);


        iniciarCbxPersona();
        iniciarCbxTipoTitulo();
        iniciarInstitucion();
        manejarEventos();

    }


    @FXML
    public void iniciarCbxPersona() {
        ObservableList<PersonalSalud> listapersonas = FXCollections.observableArrayList(personalSaludFacade.obtenerTodoPersonalSalud());
        cbx_idpersona.setItems(listapersonas);

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
    public void guardarInstitucion() {

        PsDto psDto = new PsDto(
                0,
                cbx_idpersona.getSelectionModel().getSelectedItem().getIdPersonal(),
                cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico(),
                cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion(),
                Date.valueOf(dp_fechatitulacion.getValue())

        );

        int res = psFacade.agregar(psDto);

        if (res == 1) {
            titulos.add(psDto);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("No se ha podido agregar la institucion");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();
    }

    public void manejarEventos() {
        tb_personal.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PsDto>() {
            @Override
            public void changed(ObservableValue<? extends PsDto> observable, PsDto oldValue, PsDto newValue) {
                if (newValue != null) {
                    cbx_idpersona.getSelectionModel().getSelectedItem().setIdPersonal("idPersonal");
                    cbx_idtipotitulo.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico();
                    cbx_idinstitucion.getSelectionModel().getSelectedItem().getIdInstitucion();
                    Date.valueOf(dp_fechatitulacion.getValue());


                    btnCrear.setDisable(false);
                    btnGuardar.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                    //habilitarCampos();
                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    private void eventoCrear() {
        btnCrear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                limpiar();
                //habilitarCampos();
                btnInhabilitar.setDisable(true);
                btnModificar.setDisable(true);
                btnGuardar.setDisable(false);
            }
        });
    }
    /*
    @FXML
    private void eventoCancelar(){
        bntCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                limpiar();
                txtCodigo.requestFocus();
                btnModificar.setDisable(true);
                btnInhabilitar.setDisable(true);
            }
        });
    }

    @FXML
    private void eventoGuardar(){

        btnGuardar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            StringBuilder sb = new StringBuilder();
            boolean esValido = true;
            @Override
            public void handle(MouseEvent event) {
                if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
                        txtTelefono.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS",
                            "ERROR AL INTENTAR GUARDAR", JOptionPane.ERROR_MESSAGE);

                }else {
                    guardarInstitucion();
                }

                    if (txtCodigo.getText().isEmpty()) {
                        txtCodigo.setStyle("-fx-border-color: red ; -fx-border-width: 2px; -fx-border-radius: 6px;");
                        lblCodigo.setText("Re");

                    } else {
                        lblCodigo.setText("");
                    }
                    if (txtNombre.getText().isEmpty()) {
                        lbNombre.setText("Re");

                    } else {
                        lbNombre.setText("");
                    }
                    if (txtDireccion.getText().isEmpty()) {
                        lblDireccion.setText("Re");

                    } else {
                        lblDireccion.setText("");
                    }

                    if (txtTelefono.getText().isEmpty()) {
                        lblTelefono.setText("Re");

                    } else {
                        lblTelefono.setText("");
                    }

                    if (!esValido){

                        Alert msg = new Alert(Alert.AlertType.ERROR);
                        msg.setTitle("Gestiones - Personal de Salud");
                        msg.setContentText("Errores: \n" + sb.toString());

                        msg.setHeaderText("Resultado");
                        msg.show();
                    }






            }
        });
    }



    @FXML
    public void modificarInstitucion() {
        InstitucionAcademica institucionAcademica = new InstitucionAcademica(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()

        );
        int res = facadeInstitucionAcademica.modificar(institucionAcademica);
        if (res == 1) {
            instituciones.set(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex(), institucionAcademica);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();
    }

    @FXML
    public void eliminarInstitucion() {
        int res = facadeInstitucionAcademica.eliminar(tbIinstitucionAcademica.getSelectionModel().getSelectedItem().getIdInstitucion());
        if (res == 1) {
            instituciones.remove(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();
    }


    @FXML
    private void habilitarCampos() {
        txtCodigo.setDisable(false);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtCodigo.requestFocus();
    }

    @FXML
    private void deshabilitarBotones() {
        btnCrear.setDisable(false); //siempre ira deshabilitado
        btnConsultar.setDisable(false);
        bntCancelar.setDisable(false);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);

    }

    @FXML
    private void deshabilitarCampos() {
        txtCodigo.setDisable(true);
        txtNombre.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);
    }*/

    @FXML
    public void limpiar() {
        cbx_idpersona.setValue(null);
        cbx_idtipotitulo.setValue(null);
        cbx_idinstitucion.setValue(null);
        dp_fechatitulacion.setValue(null);
        bntCancelar.setDisable(false);
    }

    @FXML
    private void cerrarInstitucionAcademica(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
