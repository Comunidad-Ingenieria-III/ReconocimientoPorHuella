package medicamento.formularioMedicamento;

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
import medicamento.dto.Medicamento;
import medicamento.facade.FacadeMedicamento;
import tipoTituloAcademico.dto.TtAcademico;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorMedicamentos extends Component implements Initializable {
    FacadeMedicamento facade =  new FacadeMedicamento();
    @FXML
    private TableView<Medicamento> tbMedicamentos;
    @FXML
    private TableColumn<Medicamento, String> idCodigo,idNombre;
    @FXML
    private TextField tf_Tipo,tf_nombre1;
    @FXML
    private Button bt_crear,bt_consultar,bt_cancelar,bt_salir,bt_guardar,bt_modificar,bt_inhabilitar;
    @FXML
    private Label validarMedicamento,validarNombre;
    @FXML
    private ObservableList<Medicamento> medicamentos;
    @FXML
    private List<Medicamento> medicamentos1;
    private String estado = "1";
    int valor=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicamentos = FXCollections.observableArrayList(facade.obternetTodosMedicamentos());
        tbMedicamentos.setItems(medicamentos);
        idCodigo.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_guardar.setDisable(true);
        manejarEventos();
        deshabilitarCampos();
    }

    public void manejarEventos() {
        tbMedicamentos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Medicamento>() {
            @Override
            public void changed(ObservableValue<? extends Medicamento> observable, Medicamento oldValue, Medicamento newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(newValue.getIdMedicamento());
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
            validarMedicamento.setText("Campo requerido");
            bt_guardar.setDisable(true);

        }else{
            validarMedicamento.setText("");
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
            medicamentos1 = facade.buscar(tf_Tipo.getText());
            if(medicamentos1.size()>=1){

                int i=0;
                if (medicamentos1.get(0).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Medicamentos");
                    msg.setContentText("El medicamento : " + tf_Tipo.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();

                }
                if(medicamentos1.get(0).getEstado().equals("1")){
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Medicamentos");
                    msg.setContentText("Código: " + tf_Tipo.getText()  +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();


                }




            }

        }


    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarMedicamento();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }


    @FXML
    public void limpiar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
    }
    @FXML
    public void botonGuardar(){
        medicamentos1= facade.buscar(tf_Tipo.getText());
        Medicamento medicamento = new Medicamento(
                tf_Tipo.getText(),
                tf_nombre1.getText(),estado
        );
        if (medicamentos1.isEmpty()) {
            if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Medicamentos");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Tipo.requestFocus();
                bt_guardar.setDisable(true);

            } else {

                int res = facade.agregar(medicamento);
                if (res == 1) {
                    bt_guardar.setDisable(true);
                    medicamentos.add(medicamento);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Medicamentos");
                    msg.setContentText("El medicamento se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();


                } else {
                    cancelar();
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Medicamento");
                    msg.setContentText("No se ha podido agregar el medicamento");
                    msg.setHeaderText("Resultado");
                    msg.show();
                }


            }
        } else {

            if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Medicamentos");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_nombre1.requestFocus();

            } else {


                int res = facade.modificar(medicamento);
                if (res == 1) {


                   // medicamentos.set(tbMedicamentos.getSelectionModel().getSelectedIndex(), medicamento);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Medicamentos");
                    msg.setContentText("El medicamento se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Medicamento");
                    msg.setContentText("El medicamento , No ha sido modificado");
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
        valor=0;
        bt_guardar.setDisable(false);
        bt_inhabilitar.setDisable(true);
    }


    @FXML
    public void eliminarMedicamento() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Medicamentos");
        msg.setContentText("¿Está seguro de eliminar el medicamento?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta=facade.eliminar(tf_Tipo.getText());

            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Medicamentos");
                msge.setContentText("Error al eliminar! \n" + "El medicamento tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                medicamentos.remove(tf_Tipo.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Medicamentos");
                msg2.setContentText("El medicamento se ha eliminado");
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
    public void validarB(){
        bt_guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validarCamposVacios();
            }
        });
    }

    @FXML
    private void consultarMedicamento() {
        if(tf_Tipo.getText().isEmpty()){
            tf_Tipo.setDisable(false);
            tf_nombre1.setDisable(true);
            tf_Tipo.requestFocus();
            bt_crear.setDisable(true);
            bt_guardar.setDisable(true);
            valor=0;

        }else{
            medicamentos = FXCollections.observableArrayList(facade.buscar(tf_Tipo.getText()));
            int i = 0;
            if (medicamentos.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Institución de Referencia");
                msg.setContentText("Código " + tf_Tipo.getText() +" de referencia no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Tipo.requestFocus();
                tf_Tipo.setText("");

            }else {


                if (medicamentos.get(i).getEstado().equals("1")) {
                    tbMedicamentos.setItems(medicamentos);
                    idCodigo.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
                    idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    tf_Tipo.setText(medicamentos.get(i).getIdMedicamento());
                    tf_nombre1.setText(medicamentos.get(i).getNombre());
                    bt_inhabilitar.setDisable(false);
                    bt_modificar.setDisable(false);

                }

                if (medicamentos.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("Código " + tf_Tipo.getText() + " de referencia no encontrado");
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
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        habilitarCampos();
        valor=1;
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
        validarMedicamento.setText("");
        validarNombre.setText("");

        medicamentos = FXCollections.observableArrayList(facade.obternetTodosMedicamentos());
        tbMedicamentos.setItems(medicamentos);
        idCodigo.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    private void cerrarMedicamentos(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}