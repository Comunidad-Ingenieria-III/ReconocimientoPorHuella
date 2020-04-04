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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import medicamento.dto.Medicamento;
import medicamento.facade.FacadeMedicamento;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorMedicamentos implements Initializable {
    FacadeMedicamento facade =  new FacadeMedicamento();
    @FXML
    private TableView<Medicamento> tbMedicamentos;


    @FXML
    private TableColumn<Medicamento, String> idCodigo;
    @FXML
    private TableColumn<Medicamento, String> idNombre;


    @FXML
    private TextField tf_Tipo;
    @FXML
    private TextField tf_nombre1;
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

    private ObservableList<Medicamento> medicamentos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicamentos = FXCollections.observableArrayList(facade.obternetTodosMedicamentos());


        tbMedicamentos.setItems(medicamentos);

        idCodigo.setCellValueFactory(new PropertyValueFactory<>("idMedicamento"));
        idNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));




        deshabilitarBotones();
        deshabilitarCampos();
        manejarEventos();
    }

    public void manejarEventos() {
        tbMedicamentos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Medicamento>() {
            @Override
            public void changed(ObservableValue<? extends Medicamento> observable, Medicamento oldValue, Medicamento newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(newValue.getIdMedicamento());
                    tf_nombre1.setText(newValue.getNombre());


                    bt_crear.setDisable(false);
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
    public void guardarMedicamentos() {
        Medicamento medicamento = new Medicamento(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );

        int res = facade.agregar(medicamento);

        if (res == 1) {
            medicamentos.add(medicamento);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("El medicamento  se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("No se ha podido agregar el medicamento");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void modificarMedicamento() {
        Medicamento medicamento = new Medicamento(
                tf_Tipo.getText(),
                tf_nombre1.getText()

        );
        int res = facade.modificar(medicamento);
        if (res == 1) {
            medicamentos.set(tbMedicamentos.getSelectionModel().getSelectedIndex(), medicamento);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("El medicamento se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("El medicamento, No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void eliminarMedicamento() {
        int res = facade.eliminar(tbMedicamentos.getSelectionModel().getSelectedItem().getIdMedicamento());
        if (res == 1) {
            medicamentos.remove(tbMedicamentos.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("El medicamento se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("El medicamento, No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();

    }

    public void validarCamposVacios(){
        if (tf_Tipo.getText().isEmpty()){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Medicamentos");
            msg.setContentText("Debe ingresar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();

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

    @FXML
    private void cerrarMedicamentos(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
