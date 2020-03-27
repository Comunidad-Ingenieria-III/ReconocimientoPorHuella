package login.formulariousuario;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;
import java.net.URL;
import java.util.ResourceBundle;

public class ContraladorRegistro implements Initializable {

    DaoUsuario daoUsuario = new DaoUsuario();

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private Usuario crearUsuario() {

        int idUsuario = Integer.parseInt(tf_idUsuario.getText());
        String primerNombre = tf_primerNombre.getText();
        String segundoNombre = tf_segundoNombre.getText();
        String primerApellido = tf_primerApellido.getText();
        String segundoApellido = tf_segundoApellido.getText();
        String nombreUsuario = tf_Usuario.getText();
        String contrasena = tf_Contrasena.getText();
        Usuario usuario = new Usuario(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, nombreUsuario, contrasena);
        return usuario;

    }

    @FXML
    private void guardarUsuario(ActionEvent e) {
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
