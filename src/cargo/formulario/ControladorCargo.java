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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorCargo extends Component implements Initializable {
    FacadeCargo facadeCargo = new FacadeCargo();
    @FXML
    private TableView<Cargo> tblCargos;

    @FXML
    private TableColumn<Cargo, String> colId;
    @FXML
    private TableColumn<Cargo, String> colDescripcion;
    @FXML
    private TextField txtId,txtDescripcion;

    @FXML
    private Button btnCrear,btnConsultar,btnModificar,btnGuardar,btnEliminar,btnCancelar,btnSalir;
    @FXML
    private RadioButton estado;
    private ObservableList<Cargo>cargos;
    private List<Cargo> listaCargos;
    @FXML
    int valor=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            validar();
        cargos = FXCollections.observableArrayList(facadeCargo.obtenerCargos());
        tblCargos.setItems(cargos);
        colId.setCellValueFactory(new PropertyValueFactory<>("idCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        estado.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(true);
        txtDescripcion.setDisable(true);
        txtId.setDisable(true);
       // deshabilitarCampos(); HEAD

        estado.setDisable(true);


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

                btnCrear.setDisable(true);
                btnGuardar.setDisable(true);
                btnModificar.setDisable(false);
                btnEliminar.setDisable(false);
                btnConsultar.setDisable(true);
            }
        });//Fin del Listener


    }

    public void validarExistente(){

        txtDescripcion.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){

            listaCargos=facadeCargo.buscar(txtId.getText());

            if(listaCargos.size()>=1){
                int i=0;

                if (listaCargos.get(0).isEstado()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Cargo");
                    msg.setContentText("Código: " + txtId.getText() +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    txtDescripcion.setText("");
                    txtId.requestFocus();




                }else{

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Cargo");
                    msg.setContentText("El Cargo: " + txtId.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    txtDescripcion.setText("");
                    txtId.requestFocus();
                }


            }

        }


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





    /* Metodo desconocido
        Cargo cargo = new Cargo(
                txtId.getText(),
                txtDescripcion.getText(),
                estado.isSelected()
      //  );
      */




    @FXML
    public void guardarCargo() {
        listaCargos=facadeCargo.buscar(txtId.getText());
        Cargo cargo = new Cargo(txtId.getText(), txtDescripcion.getText(), estado.isSelected());
        if (listaCargos.isEmpty()) {
            if (txtId.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Cargos");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                txtId.requestFocus();
            } else {

                int res = facadeCargo.agregar(cargo);
                if (res == 1) {
                    cargos.add(cargo);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Cargos");
                    msg.setContentText("El cargo se ha agregado");
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
        } else {

            if (txtId.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Tipo de título académico");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                txtDescripcion.requestFocus();

            } else {


                int res = facadeCargo.modificar(cargo);
                if (res == 1) {
                    btnGuardar.setDisable(true);
                    //cargos.set(tblCargos.getSelectionModel().getSelectedIndex(), cargo);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Cargo");
                    msg.setContentText("El cargo se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    btnGuardar.setDisable(true);
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Cargo");
                    msg.setContentText("El cargo No ha sido modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }
            }
        }
    }

    @FXML
    public void modificar(){
        txtId.setDisable(true);
        txtDescripcion.setDisable(false);
        txtDescripcion.requestFocus();
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        valor =0;
        btnGuardar.setDisable(false);
    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarCargo();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }

    @FXML
    public void eliminarCargo() {

        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Cargo");
        msg.setContentText("¿Está seguro de eliminar el cargo?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta =facadeCargo.eliminar(txtId.getText());


            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Cargo");
                msge.setContentText("Error al eliminar! \n" + "El cargo  tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                cargos.remove(txtId.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Cargo");
                msg2.setContentText("El cargo se ha eliminado");
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
    private void consultarCargo() {
        if(txtId.getText().isEmpty()){
            txtId.setDisable(false);
            txtDescripcion.setDisable(true);
            txtId.requestFocus();
            btnCrear.setDisable(true);
            btnGuardar.setDisable(true);
            tblCargos.setEditable(false);
            valor=0;
        }else{
            int i=0;
            cargos = FXCollections.observableArrayList(facadeCargo.buscar(txtId.getText()));
            if(cargos.get(i).isEstado()){


                tblCargos.setItems(cargos);
                colId.setCellValueFactory(new PropertyValueFactory<>("idCargo"));
                colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                btnConsultar.setDisable(true);
                txtId.setText(cargos.get(i).getIdCargo());
                txtDescripcion.setText(cargos.get(i).getNombre());
                btnEliminar.setDisable(false);
                btnModificar.setDisable(false);
            }else {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Tipo de título académico");
                msg.setContentText("Tipo de título " + txtId.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();

            }


            if (cargos.isEmpty()){
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Tipo de título académico");
                msg.setContentText("Tipo de título " + txtId.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();

            }
        }

    }





    @FXML
    public void limpiarFormulario() {
        txtId.setText("");
        txtDescripcion.setText("");

        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        txtId.requestFocus();
    }






    @FXML
    private void habilitarBotones() {
        btnCrear.setDisable(true); //siempre ira deshabilitado
        btnConsultar.setDisable(false);
        btnCancelar.setDisable(false);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        habilitarCampos();
        valor=1;
    }

    @FXML
    private void habilitarCampos() {
        txtId.setDisable(false);
        txtDescripcion.setDisable(false);
        txtId.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        txtId.setDisable(true);
        txtDescripcion.setDisable(true);

    }

    @FXML
    public void cancelar() {
        txtId.setText("");
        txtDescripcion.setText("");

        txtId.requestFocus();

        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnConsultar.setDisable(false);
        btnCrear.setDisable(false);
        txtDescripcion.setDisable(true);
        txtId.setDisable(true);
        btnGuardar.setDisable(true);

        cargos = FXCollections.observableArrayList(facadeCargo.obtenerCargos());
        tblCargos.setItems(cargos);
        colId.setCellValueFactory(new PropertyValueFactory<>("idCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }


    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }




}
