package login.formulariousuario;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;
import perfil.dtoperfil.PerfilDto;
import perfil.facadeperfil.PerfilFacade;

import java.net.URL;
import java.util.ResourceBundle;

public class ContraladorRegistro implements Initializable {

    DaoUsuario daoUsuario = new DaoUsuario();
    PerfilFacade perfilFacade = new PerfilFacade();

    @FXML
    private ComboBox<PerfilDto> cmb_perfil;
    @FXML
    private TextField tf_idUsuario;
    @FXML
    private TextField tf_primerNombre;
    @FXML
    private TextField tf_segundoNombre;
    @FXML
    private TextField tf_primerApellido;
    @FXML
    private TextField tf_segundoApellido;
    @FXML
    private TextField tf_Usuario;
    @FXML
    private PasswordField tf_Contrasena;
    @FXML
    private Button bt_salir;
    @FXML
    private RadioButton cb_estado;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

            ObservableList<PerfilDto> listaPerfiles = FXCollections.observableArrayList(perfilFacade.obtenerCargos());
            cmb_perfil.setItems(listaPerfiles);

    }


    @FXML
    private Usuario crearUsuario() {

        String idUsuario = tf_idUsuario.getText();
        String primerNombre = tf_primerNombre.getText();
        String segundoNombre = tf_segundoNombre.getText();
        String primerApellido = tf_primerApellido.getText();
        String segundoApellido = tf_segundoApellido.getText();
        String nombreUsuario = tf_Usuario.getText();
        String contrasena = tf_Contrasena.getText();
        String idperfil = cmb_perfil.getSelectionModel().getSelectedItem().getIdperfil();
        Boolean estado = cb_estado.isSelected();
        Usuario usuario = new Usuario(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, nombreUsuario, contrasena, idperfil, estado);
        return usuario;

    }

    @FXML
    private void guardarUsuario() {
        daoUsuario.crearUsuario(crearUsuario());
        limpiarR();
    }


    @FXML
    public void limpiarR() {
        tf_idUsuario.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        tf_Usuario.setText("");
        tf_Contrasena.setText("");
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
