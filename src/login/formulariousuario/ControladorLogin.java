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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;
import login.facadeusuario.FacadeUsuario;
import principal.ControladorPrincipal;

import java.awt.*;
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
    private Label lblContraseña, mensajeSesion;
    @FXML
    private Label lblUsuario;
    private int contador=0;

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
            lblUsuario.setText("Requerido");


        }else if(tf_Contrasena.getText().isEmpty()){
            lblContraseña.setText("Requerido");

        }else{
            lblContraseña.setText("");
            lblUsuario.setText("");
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
        //Busca el usuario y contraseña
        Usuario usuario = facadeUsuario.validarUsuario(tf_Usuario.getText(),tf_Contrasena.getText());
        //Busca que le username exista
        Usuario usuario1= facadeUsuario.obtenerUsuario(tf_Usuario.getText());

        String cadena="DENEGAR";

        if(usuario1==null) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            Toolkit.getDefaultToolkit().beep();
            msg.setTitle("AP_Humana - Ingreso al Sistema");
            msg.setContentText("Usuario Y/O contraseña incorrecta.");
            msg.setHeaderText("Verifica tus datos");
            msg.show();
            tf_Usuario.setText("");
            tf_Contrasena.setText("");
            tf_Usuario.requestFocus();


        } else if(usuario==null){

            if(usuario1.getPermitir().equals("PERMITIR")){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario Y/O contraseña incorrecta.");
                msg.setHeaderText("Verifica tus datos");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
                facadeUsuario.bloquear(tf_Usuario.getText(),"UNO");
                mensajeSesion.setText("Quedan cuatro intentos de sesión");

            } else if(usuario1.getPermitir().equals("UNO")){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario Y/O contraseña incorrecta.");
                msg.setHeaderText("Verifica tus datos");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
                facadeUsuario.bloquear(tf_Usuario.getText(),"DOS");
                mensajeSesion.setText("Quedan tres intentos de sesión");
            }else if(usuario1.getPermitir().equals("DOS")){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario Y/O contraseña incorrecta.");
                msg.setHeaderText("Verifica tus datos");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
                facadeUsuario.bloquear(tf_Usuario.getText(),"TRES");
                mensajeSesion.setText("Quedan dos intentos de sesión");
            }else if(usuario1.getPermitir().equals("TRES")){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario Y/O contraseña incorrecta.");
                msg.setHeaderText("Verifica tus datos");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
                facadeUsuario.bloquear(tf_Usuario.getText(),"CUATRO");
                mensajeSesion.setText("Quedan un intento de sesión");
                Alert msgs= new Alert(Alert.AlertType.ERROR);
                msgs.setTitle("AP_Humana - Ingreso al Sistema");
                msgs.setContentText("¡Queda un intento de sesión! En caso de errar, " +
                        "su usuario será bloqueado!");
                msgs.setHeaderText("Verifica tus datos");
                msgs.show();
            }else if(usuario1.getPermitir().equals("CUATRO")){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario Y/O contraseña incorrecta.");
                msg.setHeaderText("Verifica tus datos");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
                facadeUsuario.bloquear(tf_Usuario.getText(),"DENEGAR");
                mensajeSesion.setText(" Usuario: " + tf_Usuario.getText() + " bloqueado");
            }else if(usuario1.getPermitir().equals("DENEGAR")){
                mensajeSesion.setText(" Usuario: " + tf_Usuario.getText() + " bloqueado");
            }


        }else if (usuario.getUsername().equals(tf_Usuario.getText()) && usuario.getContrasena().equals(tf_Contrasena.getText())){

            if(usuario.getPermitir().equals(cadena)){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("AP_Humana - Ingreso al Sistema");
                msg.setContentText("Usuario bloqueado");
                msg.setHeaderText("Contacte al administrador");
                msg.show();
                tf_Contrasena.setText("");
                tf_Usuario.requestFocus();
            }else{
                facadeUsuario.bloquear(tf_Usuario.getText(),"PERMITIR");
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

            }


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
