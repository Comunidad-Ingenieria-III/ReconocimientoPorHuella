package login.formulariousuario;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;
import login.facadeusuario.FacadeUsuario;
import perfil.dtoperfil.PerfilDto;
import perfil.facadeperfil.PerfilFacade;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContraladorUsuario implements Initializable {

    FacadeUsuario facadeUsuario = new FacadeUsuario();
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
    private RadioButton cb_estado;

    ObservableList<PerfilDto> listaPerfiles;
    int valor=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            listaPerfiles = FXCollections.observableArrayList(perfilFacade.obtenerCargos());
            cmb_perfil.setItems(listaPerfiles);

            deshabilitarBotones();
            deshabilitarCampos();

    }


    public void validarExistente(){

        tf_primerNombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){


            Usuario usuario = facadeUsuario.obtenerUsuarioPorId(tf_idUsuario.getText());

            if(usuario !=null){
                int i=0;
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("El Tipo de título: " + tf_idUsuario.getText() + " se escuentra registrado ");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_idUsuario.setText("");
                    tf_primerNombre.setText("");
                    tf_idUsuario.requestFocus();

                }

        }


    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarUsuario();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
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
    public Boolean validarContraseña(String contraseña){
        boolean cumple=false;
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(contraseña.matches(pattern)){
            cumple=true;
        }else{
            cumple=false;
        }
        return  cumple;
    }

    @FXML
    private void guardarUsuario() {
        Usuario usuario = facadeUsuario.obtenerUsuarioPorId(tf_idUsuario.getText());
        Boolean cumple=validarContraseña(tf_Contrasena.getText());
        if (usuario == null) {
          if(tf_idUsuario.getText().isEmpty() || tf_primerNombre.getText().isEmpty() || tf_primerApellido.getText().isEmpty() || tf_Usuario.getText().isEmpty() || tf_Contrasena.getText().isEmpty() ){
              Alert msg1 = new Alert(Alert.AlertType.ERROR);
              msg1.setTitle("Gestiones - Usuario");
              msg1.setContentText("Campos requeridos");
              msg1.setHeaderText("Información");
              msg1.show();
              if(tf_idUsuario.getText().isEmpty()) {
                  tf_idUsuario.requestFocus();
              }else if(tf_primerNombre.getText().isEmpty()){
                  tf_primerNombre.requestFocus();

              } else if(tf_primerApellido.getText().isEmpty()){
                  tf_primerApellido.requestFocus();

              }else if(tf_Usuario.getText().isEmpty()){
                  tf_Usuario.requestFocus();

              }else if(tf_Contrasena.getText().isEmpty()){
                  tf_Contrasena.requestFocus();

              }

          }else if(cmb_perfil.getSelectionModel().isEmpty()){
              Alert msg1 = new Alert(Alert.AlertType.ERROR);
              msg1.setTitle("Gestiones - Usuario");
              msg1.setContentText("Debe seleccionar un perfil");
              msg1.setHeaderText("Información");
              msg1.show();
              cmb_perfil.requestFocus();
          }else if(cumple.booleanValue()==false){
              Alert msg = new Alert(Alert.AlertType.ERROR);
              msg.setTitle("Gestiones - Usuario");
              msg.setContentText("La contraseña debe tener por lo menos ocho (8) caracteres\n" +
                      "Letras minúsculas y mayúsculas al menos una vez\n" +
                      "Un carácter especial debe aparecer al menos una vez\n" +
                      "No se permiten espacios en blanco.");
              msg.setHeaderText("Algo salió mal.");
              msg.show();
              tf_Contrasena.setText("");
              tf_Contrasena.requestFocus();
          }



          else{


            int res = facadeUsuario.agregarUsuario(crearUsuario());
            if (res == 1) {
                Alert msg1 = new Alert(Alert.AlertType.INFORMATION);
                msg1.setTitle("Gestiones - Usuario");
                msg1.setContentText("Usario agregado correctamente");
                msg1.setHeaderText("Información");
                msg1.show();
                limpiarR();
            } else {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Usuario");
                msg.setContentText("No fue posible agregar el registro");
                msg.setHeaderText("Algo salio mal.");
                msg.show();
            }
          }
        }else{
            if( tf_primerNombre.getText().isEmpty() || tf_primerApellido.getText().isEmpty() || tf_Usuario.getText().isEmpty() || tf_Contrasena.getText().isEmpty() ){
                Alert msg1 = new Alert(Alert.AlertType.ERROR);
                msg1.setTitle("Gestiones - Usuario");
                msg1.setContentText("Campos requeridos");
                msg1.setHeaderText("Información");
                msg1.show();
                if(tf_primerNombre.getText().isEmpty()){
                    tf_primerNombre.requestFocus();

                } else if(tf_primerApellido.getText().isEmpty()){
                    tf_primerApellido.requestFocus();

                }else if(tf_Usuario.getText().isEmpty()){
                    tf_Usuario.requestFocus();

                }else if(tf_Contrasena.getText().isEmpty()){
                    tf_Contrasena.requestFocus();

                }

            }else if(cmb_perfil.getSelectionModel().isEmpty()){
                Alert msg1 = new Alert(Alert.AlertType.ERROR);
                msg1.setTitle("Gestiones - Usuario");
                msg1.setContentText("Debe seleccionar un perfil");
                msg1.setHeaderText("Información");
                msg1.show();
                cmb_perfil.requestFocus();
            }else if(cumple.booleanValue()==false){
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Usuario");
                msg.setContentText("La contraseña debe tener por lo menos ocho (8) caracteres\n" +
                        "Letras minúsculas y mayúsculas al menos una vez\n" +
                        "Un carácter especial debe aparecer al menos una vez\n" +
                        "No se permiten espacios en blanco.");
                msg.setHeaderText("Algo salió mal.");
                msg.show();
                tf_Contrasena.setText("");
                tf_Contrasena.requestFocus();
            }else{


            int res = facadeUsuario.modificarUsuario(crearUsuario());
            if (res == 1) {
                Alert msg1 = new Alert(Alert.AlertType.INFORMATION);
                msg1.setTitle("Gestiones - Usuario");
                msg1.setContentText("Usuario modificado correctamente");
                msg1.setHeaderText("Exito!");
                msg1.show();
                limpiarR();
                deshabilitarCampos();
            } else {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Usuario");
                msg.setContentText("No fue posible modificar el usuario");
                msg.setHeaderText("Error!");
                msg.show();
            }

            }
        }
    }

    @FXML
    public void eliminarUsuario(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Está seguro de eliminar el usuario ? ", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.YES) {
           facadeUsuario.eliminarUsuario(tf_idUsuario.getText());

            Alert msg1 = new Alert(Alert.AlertType.INFORMATION);
            msg1.setTitle("Gestiones - Usuario");
            msg1.setContentText("Usuario eliminado correctamente");
            msg1.setHeaderText("Exito!");
            msg1.show();
            limpiarR();
        } else if (action.get() == ButtonType.NO) {

        }
    }

    @FXML
    public void consultarUsuario(){//Función para consultar un usuario por medio de su id

        if (tf_idUsuario.getText().isEmpty()){
            tf_idUsuario.setDisable(false);
            tf_idUsuario.requestFocus();
            cb_estado.setDisable(true);
            tf_primerNombre.setDisable(true);
            tf_segundoNombre.setDisable(true);
            tf_primerApellido.setDisable(true);
            tf_segundoApellido.setDisable(true);
            cmb_perfil.setDisable(true);
            tf_Usuario.setDisable(true);
            tf_Contrasena.setDisable(true);
            bt_guardar.setDisable(true);
            valor=0;

        }else {

            Usuario usuario = facadeUsuario.obtenerUsuarioPorId(tf_idUsuario.getText());
            if (usuario==null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Usuario");
                msg.setContentText("El usuario no se encuentra en la base de datos");
                msg.setHeaderText("Algo salío mal.");
                msg.show();
                tf_idUsuario.requestFocus();
            } else {
                cmb_perfil.setValue(perfilFacade.obtenerPorId(usuario.getIdperfil()));

                if (usuario.isEstado()) {
                    cb_estado.setSelected(true);
                } else {
                    cb_estado.setSelected(false);
                }
                tf_idUsuario.setText(usuario.getIdUsuario());
                tf_primerNombre.setText(usuario.getPrimerNombre());
                tf_segundoNombre.setText(usuario.getSegundoNombre());
                tf_primerApellido.setText(usuario.getPrimerApellido());
                tf_segundoApellido.setText(usuario.getSegundoApellido());
                tf_Usuario.setText(usuario.getUsername());
                tf_Contrasena.setText(usuario.getContrasena());

                bt_modificar.setDisable(false);
                bt_inhabilitar.setDisable(false);
            }
        }

    }
    @FXML
    public void modificar(){
        cmb_perfil.setDisable(false);
        cb_estado.setDisable(false);
        tf_idUsuario.setDisable(true);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        tf_Usuario.setDisable(false);
        tf_Contrasena.setDisable(false);
        bt_consultar.setDisable(true);
        bt_guardar.setDisable(false);
        bt_inhabilitar.setDisable(true);
        tf_primerNombre.requestFocus();
        bt_modificar.setDisable(true);
        valor=0;
    }

    @FXML
    public void cancelar(){
        limpiarR();
        deshabilitarCampos();
    }




    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);

        //habilitarCampos();
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
    public void habilitarCampos() {
        tf_idUsuario.setDisable(false);
        cb_estado.setDisable(false);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        cmb_perfil.setDisable(false);
        tf_Usuario.setDisable(false);
        tf_Contrasena.setDisable(false);
        tf_idUsuario.requestFocus();
    }

    @FXML
    public void deshabilitarCampos() {
        tf_idUsuario.setDisable(true);
        tf_primerNombre.setDisable(true);
        tf_segundoNombre.setDisable(true);
        tf_primerApellido.setDisable(true);
        tf_segundoApellido.setDisable(true);
        tf_Usuario.setDisable(true);
        tf_Contrasena.setDisable(true);
        cmb_perfil.setDisable(true);
        cb_estado.setDisable(true);

    }

    @FXML
    public void limpiarR() {
        cmb_perfil.setValue(null);
        cb_estado.setSelected(true);
        tf_idUsuario.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        tf_Usuario.setText("");
        tf_Contrasena.setText("");
        bt_guardar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_consultar.setDisable(false);
    }

    @FXML
    private void eventoCrear() {
        bt_crear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                bt_inhabilitar.setDisable(true);
                bt_modificar.setDisable(true);
                bt_consultar.setDisable(false);
                bt_crear.setDisable(false);
                limpiarR();
                habilitarCampos();
                bt_guardar.setDisable(false);
                valor=1;
            }
        });
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
