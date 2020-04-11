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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
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
    private ArrayList<TtAcademico> titulosAcademicos;
    private ObservableList<TtAcademico> ttAcademicos;



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
    public void guardarTitulo(){
        titulosAcademicos= (ArrayList<TtAcademico>) facade.obtenerTodosTitulosAcdemicos();
        TtAcademico ttAcademico= new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );
        int esta=0;
        for(int i=0; i <titulosAcademicos.size(); i++){

            if(titulosAcademicos.get(i).getIdTipoTituloAcademico()==tf_Tipo.getText()){
                esta=esta +1;
            }

        }
        if(esta==0){
            guardarTtAcademico();

        }else{
            modificarTtAcademico();

        }


    }

    @FXML
    public void guardarTtAcademico() {
        TtAcademico ttAcademico= new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );
            int res = facade.agregar(ttAcademico);

            if (res == 1) {
                cancelar();
                ttAcademicos.add(ttAcademico);
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Tipo de titulo académico");
                msg.setContentText("El Tipo de titulo   se ha agregado");
                msg.setHeaderText("Resultado");
                msg.show();


            } else {
                cancelar();
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Tipo de titulo académico");
                msg.setContentText("No se ha podido agregar el Tipo de titulo");
                msg.setHeaderText("Resultado");
                msg.show();


            }
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

    }

    @FXML
    public void modificar(){
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(false);
        tf_nombre1.requestFocus();
    }


    @FXML
    public void eliminarTtAcademico() {
        int res = facade.eliminar(tb_tituloAcademico.getSelectionModel().getSelectedItem().getIdTipoTituloAcademico());
        int i = JOptionPane.showConfirmDialog(this,"Esta seguro de eliminar el Tipo de título");
        if(i==0){
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
            cancelar();

        }else if(i==1){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("El Tipo de titulo académico, No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
        cancelar();

    }
    @FXML
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
            //if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()){
           // bt_guardar.setDisable(true);
            //}//else {
            //bt_guardar.setDisable(false);
        //}

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
    @FXML
    private void consultarTitulo() {
        tf_Tipo.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_Tipo.requestFocus();
        bt_consultar.setDisable(true);
    }



    @FXML
    public void limpiarFormulario() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");

        bt_crear.setDisable(true);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
    }




    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        habilitarCampos();
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
    }

    @FXML
    private void cerrarTipoTituloAcademico(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}