package registroAtencionPaciente.formularioregistroatencion;

import conexionBD.ConexionRoot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import datospersona.facade.FacadePersona;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ContraladorRegistroAtencionPaciente implements Initializable {

    @FXML
    private ComboBox<DtoTipoDocumento> cbxtipodocumento = new ComboBox<>();
    @FXML
    private TextField tf_nombretipodocumento;
    @FXML
    private TextField tf_idpersonadps;
    @FXML
    private TextField tf_primerNombredps;
    @FXML
    private TextField tf_segundoNombredps;
    @FXML
    private TextField tf_primerApellidodps;
    @FXML
    private TextField tf_segundoApellidodps;
    @FXML
    private DatePicker dp_fechaNacimiento;
    @FXML
    private ComboBox<String> cbxsexo = new ComboBox<>();
    @FXML
    private ComboBox<String> cbxtipoeps = new ComboBox<>();
    @FXML
    private TextField tf_nombreeps;
    @FXML
    private ComboBox<Integer> cbxdocumentoidentidadps = new ComboBox<>();
    @FXML
    private TextField tf_nombrefamiliar;
    @FXML
    private TextField tf_numerotelefonicofamiliar;
    @FXML
    private TextArea ta_alergicoA;
    @FXML
    private TextArea ta_enfermedadSufre;
    @FXML
    private TextArea ta_observaciones;
    @FXML
    private ObservableList<Integer> listatipodocumento = FXCollections.observableArrayList();
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

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private FacadePersona facadepersona = new FacadePersona();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Seleccione", "Masculino", "Femenino");
        cbxsexo.setItems(items);
        cbxsexo.getSelectionModel().selectFirst();

        dp_fechaNacimiento.setValue(LocalDate.now());*/

        //deshabilitarBotones();
        // deshabilitarCampos();
        //cargarTabla();

    }

    @FXML
    public void cargarTabla() {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select idTipoDocumento, nombreTipoDocumento from tipo_de_documento";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            while (rset.next()) {

                int identificacion = rset.getInt("idpersona");
                listatipodocumento.add(identificacion);
                String nombre = rset.getString("nombreTipoDocumento");

                cbxdocumentoidentidadps.setItems(listatipodocumento);
                tf_nombretipodocumento.setText(nombre);

            }

        } catch (RuntimeException |
                SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }

    }

    /*@FXML
    public void limpiar() {
        tf_nombretipodocumento.setText("");
        tf_idpersona.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        tf_nombreeps.setText("");
        tf_nombrefamiliar.setText("");
        tf_numerotelefonicofamiliar.setText("");
        ta_alergicoA.setText("");
        ta_enfermedadSufre.setText("");
        ta_observaciones.setText("");
    }

    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(true);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        habilitarCampos();
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
    }


    @FXML
    private void habilitarCampos() {
        cbxtipodocumento.setDisable(false);
        tf_nombretipodocumento.setDisable(false);
        tf_idpersona.setDisable(false);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        cbxsexo.setDisable(false);
        cbxtipoeps.setDisable(false);
        tf_nombreeps.setDisable(false);
        cbxdocumentofamiliar.setDisable(false);
        tf_nombrefamiliar.setDisable(false);
        tf_numerotelefonicofamiliar.setDisable(false);
        ta_alergicoA.setDisable(false);
        ta_enfermedadSufre.setDisable(false);
        ta_observaciones.setDisable(false);
        dp_fechaNacimiento.setDisable(false);
        cbxtipodocumento.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        cbxtipodocumento.setDisable(true);
        tf_nombretipodocumento.setDisable(true);
        tf_idpersona.setDisable(true);
        tf_primerNombre.setDisable(true);
        tf_segundoNombre.setDisable(true);
        tf_primerApellido.setDisable(true);
        tf_segundoApellido.setDisable(true);
        cbxtipoeps.setDisable(true);
        tf_nombreeps.setDisable(true);
        cbxdocumentofamiliar.setDisable(true);
        tf_nombrefamiliar.setDisable(true);
        tf_numerotelefonicofamiliar.setDisable(true);
        ta_alergicoA.setDisable(true);
        ta_enfermedadSufre.setDisable(true);
        ta_observaciones.setDisable(true);
        dp_fechaNacimiento.setDisable(true);
        cbxsexo.setDisable(true);

    }

    @FXML
    private void btnNuevo_click(ActionEvent event) {
        habilitarBotones();
        limpiar();
        habilitarCampos();
        //habilitarBotones();

    }*/

    @FXML
    private void setCerrarFormularioRegistroAtencion(ActionEvent event) {
        Stage stage = (Stage) bt_crear.getScene().getWindow();
        stage.close();
    }
}

