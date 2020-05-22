package login.formulariousuario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;
import login.facadeusuario.FacadeUsuario;
import principal.ControladorPrincipal;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControladorLogin<escuchaTeclado> implements Initializable {

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = new Usuario();
    FacadeUsuario facadeUsuario = new FacadeUsuario();

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    @FXML
    private TextField tf_Usuario;
    @FXML
    private PasswordField tf_Contrasena;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblContraseña;
    @FXML
    private Label lblUsuario;

    KeyListener escuchaTeclado = new KeyListener() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {

        }

        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {

        }

        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {

        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnEntrar.setDisable(true);
    }

    public void validarcampos() {

        if (tf_Usuario.getText().isEmpty()) {
            lblUsuario.setText("Campo Requerido");
        } else if (!tf_Usuario.getText().contains("@") || !tf_Usuario.getText().contains(".")) {
            lblUsuario.setText("Usuario invalido");
        } else {
            lblUsuario.setText("");
        }


        if (tf_Contrasena.getText().isEmpty()) {
            lblContraseña.setText("Campo Requerido");
        } else {
            lblContraseña.setText("");
        }
        if (tf_Usuario.getText().isEmpty() || tf_Contrasena.getText().isEmpty()) {
            btnEntrar.setDisable(true);
        } else {
            btnEntrar.setDisable(false);
        }
    }

    @FXML
    public void validar() {
        tf_Usuario.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarcampos();

            }

        });
        tf_Contrasena.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarcampos();

            }

        });

    }


    @FXML
    public void validarCredenciales() throws IOException {

        Usuario usuario = facadeUsuario.validarUsuario(tf_Usuario.getText(),tf_Contrasena.getText());

        if (usuario.getUsername().equals(tf_Usuario.getText()) && usuario.getContrasena().equals(tf_Contrasena.getText())){

            Stage stage = new Stage();
            ControladorPrincipal controladorPrincipal = new ControladorPrincipal();
            controladorPrincipal.setUsuario(usuario);
            Parent formulario_principal = FXMLLoader.load(getClass().getResource("/principal/FormularioPrincipal.fxml"));
            Scene scene = new Scene(formulario_principal, 1200, 650);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            stage.setTitle("AP_Humana Menú Principal");
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    Platform.exit();
                    System.exit(0);
                }
            });

            this.cerrarLogin();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("AP_Humana - Ingreso al Sistema");
            msg.setContentText("Usuario Y/O contraseña incorrecta.");
            msg.setHeaderText("Verifica tus datos");
            msg.show();
        }

    }

    @FXML
    public void limpiar() {
        tf_Usuario.setText("");
        tf_Contrasena.setText("");
    }


    @FXML
    public void textAction(KeyEvent e) throws IOException {

        if(e.getCode().equals(KeyCode.ENTER)) {
            validarCredenciales();

        }
    }

    @FXML
    private void abrirFormularioRegistro(ActionEvent event) throws IOException {

        try {

            Parent formulario_registro_inicio = FXMLLoader.load(getClass().getClassLoader().getResource("login/formulariousuario/FormularioRegistro.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Registro Usuarios )");
            stage.setScene(new Scene(formulario_registro_inicio, 447, 443));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void cerrarLogin() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

}
