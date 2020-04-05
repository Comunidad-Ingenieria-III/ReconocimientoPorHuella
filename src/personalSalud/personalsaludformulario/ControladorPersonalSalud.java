package personalSalud.personalsaludformulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.Persona;
import eps.dto.DtoEps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import personalSalud.personalsaluddto.PersonalSalud;
import personalSalud.personalsaludfacade.PersonalSaludFacade;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControladorPersonalSalud implements Initializable {

    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeCargo facadeCargo = new FacadeCargo();

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
    private TableView mi_tabla;
    @FXML
    private TableColumn<PersonalSalud, String> colIdTitulo;
    @FXML
    private TableColumn<PersonalSalud, String> colIdInstitucion;
    @FXML
    private TableColumn<PersonalSalud, String> colFecha;
    @FXML
    private TableColumn<DtoEps, String> colTelefono;
    @FXML
    private Label lblDocumento;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        iniciarCbxDocumento();
        iniciarCbxSexo();
        iniciarCargo();
        //deshabilitarBotones();
        //deshabilitarCampos();


    }

    @FXML
    private PersonalSalud crearPersonalSalud() {

        int idPersonal = Integer.parseInt(tf_numerodocumento.getText());
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



    public boolean validar(PersonalSalud personalSalud) {
        StringBuilder sb = new StringBuilder();
        boolean esValido = true;
        if (personalSalud == null) {
            esValido = false;
            sb.append("*No existe libro\n");
        }

        if (tf_numerodocumento.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Documeto Requerido\n");
            lblDocumento.setText("Campo Requerido");
            tf_numerodocumento.requestFocus();
        }

        if (tf_numerodocumento.getLength() <= 4) {
            esValido = false;
            sb.append("Numero Documento Mayor de 4 y Menor de 10 Digitos\n");
        }

        if (tf_numerodocumento.getLength() > 10) {
            esValido = false;
            sb.append("Numero Documento Mayor de 4 y Menor de 10 Digitos\n");
        }

        if (tf_nombre1.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Primer Nombre Requerido\n");
        }

        if (tf_apellido1.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Primer Apellido Requerido\n");
        }

        if (cmb_sexo.getValue().isEmpty()) {
            esValido = false;
            sb.append("Campo Sexo Requerido\n");
        }

        if (tf_numtelefono.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Numero Telefono Requerido\n");
        }

        /*if (tf_correoelectronico.getText().contains("@")){
            esValido = false;
            sb.append("Debes Ingresar una Dirección de Correo Valida\n");
        }

        if (tf_correoelectronico.getText().contains(".")){
            esValido = false;
            sb.append("Debes Ingresar una Dirección de Correo Valida");
        }*/

        if (!esValido) {
            JOptionPane.showMessageDialog(null, "Se encontraron los siguientes "
                            + "errores: \n" + sb.toString(), "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }
        return esValido;
    }


    @FXML
    public boolean guardar() {

        try {
            PersonalSalud personalSalud = new PersonalSalud();

            if (validar(personalSalud) == false) {
                return false;
            }
            boolean exito;

            if (personalSalud.getIdPersonal() == 0) {

                exito = personalSaludFacade.agregarPersonalSalud(crearPersonalSalud());

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Personal de Salud");
                msg.setContentText("El Personal de Salud ha sido agregado correctamente");
                msg.setHeaderText("Resultado");
                msg.show();
                limpiar();

            } else
                exito = personalSaludFacade.agregarPersonalSalud(personalSalud);

            return exito;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @FXML
    public void validarId() {//Metodo para validar que el Id del cargo solo sean numeros
        tf_numerodocumento.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
    }
    /*
    @FXML
    public void eliminarFamiliar() {
        int res = facade.eliminarFamiliar(tblFamiliares.getSelectionModel().getSelectedItem().getIdFamiliar());
        if (res == 1) {
            familiares.remove(tblFamiliares.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar  No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }


    }*/

    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Seleccione", "Masculino", "Femenino");
        cmb_sexo.setItems(items);
        cmb_sexo.getSelectionModel().selectFirst();
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
    private void habilitarCampos() {

        cmb_tipodocumento.setDisable(true);
        tf_numerodocumento.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        cmb_sexo.setDisable(true);
        tf_numtelefono.setDisable(false);
        tf_correoelectronico.setDisable(false);
        cmb_cargo.setDisable(true);
        tf_numerodocumento.requestFocus();
    }
    /*
    @FXML
    private void deshabilitarBotones() {

        btnCrear.setDisable(false); //siempre ira deshabilitado
        btnConsultar.setDisable(false);
        bntCancelar.setDisable(false);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);

    }*/

    @FXML
    public void limpiar() {
        //cmb_tipodocumento.setValue(null);
        tf_numerodocumento.setText("");
        tf_nombre1.setText("");
        tf_nombre2.setText("");
        tf_apellido1.setText("");
        tf_apellido2.setText("");
        //cmb_sexo.setValue(null);
        tf_numtelefono.setText("");
        tf_correoelectronico.setText("");
        //cmb_cargo.setValue(null);
        tf_numerodocumento.setText("");

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
        mi_tabla.setDisable(true);
        tf_numerodocumento.requestFocus();
    }

    @FXML
    private void cerraPersonalSalud(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
