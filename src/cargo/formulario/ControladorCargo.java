package cargo.formulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCargo implements Initializable {

    FacadeCargo facadeCargo = new FacadeCargo();

    @FXML
    private TableView<Cargo> tblCargos;

    @FXML
    private TableColumn<Cargo, String> colId;
    @FXML
    private TableColumn<Cargo, String> colDescripcion;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDescripcion;

    @FXML
    private Button btnCrear;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalir;

    private ObservableList<Cargo>cargos;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            validar();

        cargos = FXCollections.observableArrayList(facadeCargo.obtenerCargos());

        tblCargos.setItems(cargos);

        colId.setCellValueFactory(new PropertyValueFactory<>("idCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(true);

        manejarEventosI();

    }

    private void manejarEventosI() {
        tblCargos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cargo>() {
            @Override
            public void changed(ObservableValue<? extends Cargo> observable, Cargo oldValue, Cargo newValue) {
                if (newValue != null){
                    txtId.setText(newValue.getIdCargo());
                    txtDescripcion.setText(newValue.getNombre());

                }

                btnModificar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });//Fin del Listener


    }

    public void validar(){//Metodo para validar que el Id del cargo solo sean numeros
        txtId.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if(!Character.isDigit(car)){
                    event.consume();
                }

            }

        });
    }

    @FXML
    public void guardarCargo() {


        Cargo cargo = new Cargo(
                txtId.getText(),
                txtDescripcion.getText()
        );

        int res = facadeCargo.agregar(cargo);

        if (res == 1) {
            cargos.add(cargo);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Cargos");
            msg.setContentText("El carego se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Cargos");
            msg.setContentText("No se ha podido agregar el cargo");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void modificarCargo() {
        Cargo cargo = new Cargo(
                txtId.getText(),
                txtDescripcion.getText()
        );
        int res = facadeCargo.modificar(cargo);
        if (res == 1) {
            cargos.set(tblCargos.getSelectionModel().getSelectedIndex(), cargo);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Cargo");
            msg.setContentText("El cargo se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Cargo");
            msg.setContentText("El cargo No ha sido modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }

    }

    @FXML
    public void eliminarCargo() {
        int res = facadeCargo.eliminar(tblCargos.getSelectionModel().getSelectedItem().getIdCargo());
        if (res == 1) {
            cargos.remove(tblCargos.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Cargo");
            msg.setContentText("El cargo se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Cargo");
            msg.setContentText("El cargo No ha sido eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }

    }





    @FXML
    public void limpiarFormulario() {
        txtId.setDisable(false);
        txtDescripcion.setDisable(false);
        txtId.setText("");
        txtDescripcion.setText("");
        txtId.requestFocus();

        btnCrear.setDisable(false);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    @FXML
    public void cancelar(){
        txtId.setText("");
        txtDescripcion.setText("");
        txtId.requestFocus();
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCrear.setDisable(false);
    }
    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }




}
