package tipoTituloAcademico.formularioTipoTituloAcademico;

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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ContraladorTipoTituloAcademico<tf_nombre1> implements Initializable {
    FacadeTtAcademico facade =  new FacadeTtAcademico();
    @FXML
    private TableView<TtAcademico> tb_tituloAcademico;

    @FXML
    private TableColumn<TtAcademico, String> idCodigo;
    @FXML
    private TableColumn<TtAcademico, String> idNombre;

    @FXML
    private TextField tf_Tipo;
    @FXML
    private TextField tf_nombre1;
    @FXML
    private Label validarTt;
    @FXML
    private Label validarNombre;

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
    private ObservableList<TtAcademico> ttAcademicos;

    @FXML
    public void validar(){
        tf_Tipo.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }

        });
        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();

            }

        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ttAcademicos = FXCollections.observableArrayList(facade.obtenerTodosTitulosAcdemicos());


        tb_tituloAcademico.setItems(ttAcademicos);

        idCodigo.setCellValueFactory(new PropertyValueFactory<>("idTipoTituloAcademico"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_guardar.setDisable(true);
        manejarEventos();
    }

    public void manejarEventos() {
        tb_tituloAcademico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TtAcademico>() {
            @Override
            public void changed(ObservableValue<? extends TtAcademico> observable, TtAcademico oldValue, TtAcademico newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(newValue.getIdTipoTituloAcademico());
                    tf_nombre1.setText(newValue.getNombre());


                    bt_crear.setDisable(false);
                    bt_guardar.setDisable(false);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                }

            }
        });//FIN DEL LISTENER
    }


    @FXML
    public void limpiar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
    }

    @FXML
    public void guardarTtAcademico() {
        TtAcademico ttAcademico= new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );

        int res = facade.agregar(ttAcademico);

        if (res == 1) {
            ttAcademicos.add(ttAcademico);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("El Tipo de titulo   se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("No se ha podido agregar el Tipo de titulo");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void modificarTtAcademico() {
        TtAcademico ttAcademico = new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText()

        );
        int res = facade.modificar(ttAcademico);
        if (res == 1) {
            ttAcademicos.set(tb_tituloAcademico.getSelectionModel().getSelectedIndex(), ttAcademico);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("El tipo de titulo  se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("El Tipo de titulo , No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void eliminarTtAcademico() {
        int res = facade.eliminar(tb_tituloAcademico.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico());
        if (res == 1) {
            ttAcademicos.remove(tb_tituloAcademico.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de titulo académico ");
            msg.setContentText("El tipo de titulo  se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("El Tipo de titulo académico, No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();

    }

    public void validarCamposVacios(){
            if (tf_Tipo.getText().isEmpty()){
                 validarTt.setText("Campo obligatorio");

            }else{
                validarTt.setText("");
            }
            if(tf_nombre1.getText().isEmpty()){
                 validarNombre.setText("Campo Obligatorio");
            }else{
            validarNombre.setText("");

            }
            if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()){
            bt_guardar.setDisable(true);
            }else {
            bt_guardar.setDisable(false);
        }

    }

    public void habilitarBoton(){
        if(tf_nombre1.getText().isEmpty() || tf_nombre1.getText().isEmpty()){
            bt_guardar.setDisable(true);

        } else{
            bt_guardar.setDisable(false);
        }
    }






    @FXML
    public void validarB(){
        bt_guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validarCamposVacios();
            }
        });
    }
    public void validacionCaracter(java.awt.event.KeyEvent evento){
        if(evento.getKeyChar() >= 40 && evento.getKeyChar()<=57){
            evento.consume();

        }
    }


    @FXML
    public void limpiarFormulario() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");

        bt_crear.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
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
        tf_Tipo.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_Tipo.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(true);

    }
    public void cancelar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
    }

    @FXML
    private void cerrarTipoTituloAcademico(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}