package login.formulariousuario;

import conexionBD.ConexionRoot;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControladorLogin implements Initializable {

    DaoUsuario daoUsuario = new DaoUsuario();
    Usuario usuario = new Usuario();


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

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
    private TextField tf_Usuario1;
    @FXML
    private PasswordField tf_Contrasena2;
    @FXML
    private ObservableList<Usuario> usuarios;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnConsultar;
    @FXML
    private Label lblContraseña;
    @FXML
    private Label lblUsuario;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    btnEntrar.setDisable(true);

    }


    public void validarcampos(){

        if (tf_Usuario.getText().isEmpty()){
            lblUsuario.setText("Campo Requerido");
        }else if (!tf_Usuario.getText().contains("@") || !tf_Usuario.getText().contains(".")){
            lblUsuario.setText("Usuario invalido");
        }else {
            lblUsuario.setText("");
        }


        if (tf_Contrasena.getText().isEmpty()){
            lblContraseña.setText("Campo Requerido");
        }else{
            lblContraseña.setText("");
        }
        if (tf_Usuario.getText().isEmpty() || tf_Contrasena.getText().isEmpty()){
            btnEntrar.setDisable(true);
        }else {
            btnEntrar.setDisable(false);
        }
    }

    @FXML
    public void validar(){
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
    private void iniciarSesion(ActionEvent event) throws IOException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from usuario where username = '" + tf_Usuario.getText().trim() + "' and contrasena = '" + tf_Contrasena.getText().trim() + "' ";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            if (rset.next()) {
                Stage stage = new Stage();
                Parent formulario_principal = FXMLLoader.load(getClass().getResource("/principal/FormularioPrincipal.fxml"));
                Scene scene = new Scene(formulario_principal, 1000, 580);
                stage.setResizable(false);
                stage.setMaximized(true);
                stage.getIcons().add(new Image("estrella_vida.jpg"));
                stage.setTitle("AP_Humana Menú Principal");
                stage.getIcons().add(new Image("estrella_vida.jpg"));

                stage.setScene(scene);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                //al cerrar la ventana de Libros
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        Platform.exit();
                        System.exit(0);
                    }
                });


                //ocultar la ventana de Login
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario Y/O Contraseña Incorrecta.",
                        "Acceso a Usuarios", JOptionPane.ERROR_MESSAGE);
                limpiar();
            }

        } catch (RuntimeException |
                SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
    }


    @FXML
    public void limpiar() {
        tf_Usuario.setText("");
        tf_Contrasena.setText("");
    }

    @FXML
    public void limpiarR() {
        tf_idUsuario.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        tf_Usuario1.setText("");
        tf_Contrasena2.setText("");
    }

    @FXML
    private Usuario crearUsuario() {

        int idUsuario = Integer.parseInt(tf_idUsuario.getText());
        String primerNombre = tf_primerNombre.getText();
        String segundoNombre = tf_segundoNombre.getText();
        String primerApellido = tf_primerApellido.getText();
        String segundoApellido = tf_segundoApellido.getText();
        String nombreUsuario = tf_Usuario1.getText();
        String contrasena = tf_Contrasena2.getText();
        Usuario usuario = new Usuario(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, nombreUsuario, contrasena);
        return usuario;

    }

    @FXML
    private void abrirFormularioRegistro(ActionEvent event) throws IOException {


        try {

            Parent formulario_registro_inicio = FXMLLoader.load(getClass().getClassLoader().getResource("login/formulariousuario/FormularioRegistro.fxml"));
            Stage stage = new Stage();

            stage.setTitle("AP_Humana(Registro Usuario Nuevo)");
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

        //ocultar la ventana de Login
        //((Node) (event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    private void guardarUsuario(ActionEvent e) {
        daoUsuario.crearUsuario(crearUsuario());
        limpiarR();
    }

    @FXML
    private void cerrarAplicacion(ActionEvent event) {
        System.exit(0);
    }

}
