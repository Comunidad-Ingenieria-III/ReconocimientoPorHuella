package personalSalud.personalsaludformulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
import conexionBD.ConexionRoot;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.Persona;
import eps.dto.DtoEps;
import institucionAcademica.dto.InstitucionAcademica;
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
import java.sql.*;
import java.util.List;
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
    private TextField tf_aux;
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

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

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

    @FXML
    public void buscarPersonaSalud() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud where idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tf_numerodocumento.getText());
            rset = stmt.executeQuery();

            if (rset.next()) {

                //tf_numerodocumento.setText(rset.getString("idPersonal"));
                tf_nombre1.setText(rset.getString("nombre1"));
                tf_nombre2.setText(rset.getString("nombre2"));
                tf_apellido1.setText(rset.getString("apellido1"));
                tf_apellido2.setText(rset.getString("apellido2"));
                cmb_sexo.setValue(String.valueOf(rset.getString("sexo")));
                tf_numtelefono.setText(rset.getString("telefono"));
                tf_correoelectronico.setText(rset.getString("email"));
                //cmb_tipodocumento.setValue(String.valueOf(rset.getString("tipoDocumento")));
                //cmb_cargo.setSelectionModel().setItems(); = rs.getString("cargo");

            }
        } catch (Exception ex) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        //JOptionPane.showMessageDialog(null, "Error al buscar Personal de Salud: ",
        //"Error", JOptionPane.ERROR_MESSAGE);

    }

    @FXML
    public void modificarPersonal() {

        int res = Integer.parseInt(tf_numerodocumento.getText());
        personalSaludFacade.eliminarPersonal(res);

        if (res == 1) {

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
                    JOptionPane.ERROR_MESSAGE);
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

    @FXML
    public void eliminarFamiliar() {

        int opcion = JOptionPane.showConfirmDialog(null, "Desea eliminar el"
                + "registro?", "Confirmación", JOptionPane.YES_NO_OPTION, 2);
        if (opcion == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(tf_numerodocumento.getText());
            personalSaludFacade.eliminarPersonal(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        limpiar();
    }

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
