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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContraladorTipoTituloAcademico<tf_nombre1> extends Component implements Initializable {
    FacadeTtAcademico facade =  new FacadeTtAcademico();
    @FXML
    private TableView<TtAcademico> tb_tituloAcademico;
    @FXML
    private TableColumn<TtAcademico, String> idCodigo;
    @FXML
    private TableColumn<TtAcademico, String> idNombre;

    @FXML
    private TextField tf_Tipo, tf_nombre1;
    @FXML
    private Label validarTt,validarNombre;
    @FXML
    private Button bt_crear,bt_consultar,bt_cancelar,bt_salir,bt_guardar,bt_modificar,bt_inhabilitar;
    @FXML
    private ObservableList<TtAcademico> ttAcademicos;
    @FXML
    private List<TtAcademico> titulosAcademicos;
    @FXML
    int valor=0;
    private String estado ="1";



    public void validar(){
        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });
    }

    @FXML
    public void validarCamposVacios(){
        if (tf_Tipo.getText().isEmpty()){
            validarTt.setText("Campo requerido");
            bt_guardar.setDisable(true);

        }else{
            validarTt.setText("");
            bt_guardar.setDisable(false);
        }
        if(tf_nombre1.getText().isEmpty()){
            bt_guardar.setDisable(true);
            // validarNombre.setText("Campo requerido");
        }else{
            validarNombre.setText("");
            bt_guardar.setDisable(false);

        }

    }

    public void validarExistente(){

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){

            titulosAcademicos=facade.buscar(tf_Tipo.getText());

            if(titulosAcademicos.size()>=1){
                int i=0;

                if (titulosAcademicos.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("El Tipo de título: " + tf_Tipo.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();

                     }
                if(titulosAcademicos.get(0).getEstado().equals("1")){


                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("Código: " + tf_Tipo.getText() +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();


                }




            }

        }


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
        deshabilitarCampos();
        manejarEventos();
    }

    public void manejarEventos() {
        tb_tituloAcademico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TtAcademico>() {
            @Override
            public void changed(ObservableValue<? extends TtAcademico> observable, TtAcademico oldValue, TtAcademico newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(newValue.getIdTipoTituloAcademico());
                    tf_nombre1.setText(newValue.getNombre());
                    bt_crear.setDisable(true);
                    bt_guardar.setDisable(true);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                    bt_consultar.setDisable(true);
                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarTitulo();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }


    @FXML
    public void botonGuardar(){
        titulosAcademicos= facade.buscar(tf_Tipo.getText());
        TtAcademico ttAcademico= new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText(),estado
        );
            if (titulosAcademicos.isEmpty()) {


                if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
                   // cancelar();
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("Campos requeridos");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    if(tf_Tipo.getText().isEmpty()){
                        tf_Tipo.requestFocus();
                    } else if(tf_nombre1.getText().isEmpty()){
                        tf_nombre1.requestFocus();

                    }

                    } else {

                            int res = facade.agregar(ttAcademico);
                            if (res == 1) {
                                bt_guardar.setDisable(true);
                                ttAcademicos.add(ttAcademico);
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                msg.setTitle("Gestiones - Tipo de título académico");
                                msg.setContentText("El Tipo de título   se ha agregado");
                                msg.setHeaderText("Resultado");
                                msg.show();
                                cancelar();


                            } else {
                                cancelar();
                                Alert msg = new Alert(Alert.AlertType.ERROR);
                                msg.setTitle("Gestiones - Tipo de título académico");
                                msg.setContentText("No se ha podido agregar el Tipo de título");
                                msg.setHeaderText("Resultado");
                                 msg.show();
                            }


                    }
            } else {

                if (tf_nombre1.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("Campos requeridos");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_nombre1.requestFocus();

                } else {


                    int res = facade.modificar(ttAcademico);
                    if (res == 1) {
                        bt_guardar.setDisable(true);
                       // ttAcademicos.set(tb_tituloAcademico.getSelectionModel().getSelectedIndex(), ttAcademico);
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Gestiones - Tipo de título académico");
                        msg.setContentText("El tipo de título  se ha modificado");
                        msg.setHeaderText("Resultado");
                        msg.show();
                        cancelar();
                    } else {
                        bt_guardar.setDisable(true);
                        Alert msg = new Alert(Alert.AlertType.ERROR);
                        msg.setTitle("Gestiones - Tipo de título académico");
                        msg.setContentText("El Tipo de título , No ha sido modificada");
                        msg.setHeaderText("Resultado");
                        msg.show();
                        cancelar();
                    }
                }
            }
    }

    @FXML
    public void modificar(){
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(false);
        tf_nombre1.requestFocus();
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        valor =0;
        bt_guardar.setDisable(false);
    }


    @FXML
    public void eliminarTtAcademico() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Tipo de título académico");
        msg.setContentText("¿Está seguro de eliminar el tipo de título?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta  = facade.eliminar(tf_Tipo.getText());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Tipo de título académico");
                msge.setContentText("Error al eliminar! \n" + "El tipo de título tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                ttAcademicos.remove(tf_Tipo.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Tipo de título académico");
                msg2.setContentText("El Tipo de título se ha eliminado");
                msg2.setHeaderText("Resultado");
                msg2.show();
                limpiarFormulario();
                cancelar();
            }

        }
        limpiarFormulario();
        cancelar();

    }




    @FXML
    private void consultarTitulo() {
        if(tf_Tipo.getText().isEmpty()){
            tf_Tipo.setDisable(false);
            tf_nombre1.setDisable(true);
            tf_Tipo.requestFocus();
            bt_crear.setDisable(true);
            bt_guardar.setDisable(true);
            tb_tituloAcademico.setEditable(false);
            valor=0;
        }else{
            int i=0;
            ttAcademicos = FXCollections.observableArrayList(facade.buscar(tf_Tipo.getText()));
            if (ttAcademicos.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Tipo de título académico");
                msg.setContentText("Tipo de título " + tf_Tipo.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Tipo.requestFocus();
                tf_Tipo.setText("");
            }else {


                if (ttAcademicos.get(i).getEstado().equals("1")) {

                    tb_tituloAcademico.setItems(ttAcademicos);
                    idCodigo.setCellValueFactory(new PropertyValueFactory<>("idTipoTituloAcademico"));
                    idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    bt_consultar.setDisable(true);

                    tf_Tipo.setText(ttAcademicos.get(i).getIdTipoTituloAcademico());
                    tf_nombre1.setText(ttAcademicos.get(i).getNombre());
                    bt_inhabilitar.setDisable(false);
                    bt_modificar.setDisable(false);
                    tf_Tipo.setDisable(true);
                }
                if (ttAcademicos.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de título académico");
                    msg.setContentText("Tipo de título " + tf_Tipo.getText() + " no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.requestFocus();
                    tf_Tipo.setText("");

                }
            }
        }

    }


    @FXML
    public void limpiarFormulario() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
        bt_crear.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
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
        habilitarCampos();
        valor=1;
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

    @FXML
    public void cancelar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_consultar.setDisable(false);
        bt_crear.setDisable(false);
        tf_nombre1.setDisable(true);
        tf_Tipo.setDisable(true);
        validarTt.setText("");
        validarNombre.setText("");
        bt_guardar.setDisable(true);

        ttAcademicos = FXCollections.observableArrayList(facade.obtenerTodosTitulosAcdemicos());
        tb_tituloAcademico.setItems(ttAcademicos);
        idCodigo.setCellValueFactory(new PropertyValueFactory<>("idTipoTituloAcademico"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    private void cerrarTipoTituloAcademico(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}