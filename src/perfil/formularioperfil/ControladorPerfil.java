package perfil.formularioperfil;

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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import perfil.dtoperfil.PerfilDto;
import perfil.facadeperfil.PerfilFacade;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPerfil implements Initializable {

    PerfilFacade perfilFacade = new PerfilFacade();

    @FXML
    private TableView<PerfilDto> tbl_perfiles;

    @FXML
    private TableColumn<PerfilDto, String> colId;
    @FXML
    private TableColumn<PerfilDto, String> colDescripcion;

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
    @FXML
    private RadioButton estado;

    private ObservableList<PerfilDto> perfiles;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            validar();

        perfiles = FXCollections.observableArrayList(perfilFacade.obtenerCargos());

        tbl_perfiles.setItems(perfiles);

        colId.setCellValueFactory(new PropertyValueFactory<>("idperfil"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        estado.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(true);

        manejarEventosI();

    }

    private void manejarEventosI() {
        tbl_perfiles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PerfilDto>() {
            @Override
            public void changed(ObservableValue<? extends PerfilDto> observable, PerfilDto oldValue, PerfilDto newValue) {
                if (newValue != null){
                    txtId.setText(newValue.getIdperfil());
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
    public void guardarPerfil() {


        PerfilDto perfilDto = new PerfilDto(
                txtId.getText(),
                txtDescripcion.getText(),
                estado.isSelected()
        );

        int res = perfilFacade.agregar(perfilDto);

        if (res == 1) {
            perfiles.add(perfilDto);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("El Perfil se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("No se ha podido agregar el Perfil");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void modificarPerfil() {
        PerfilDto perfilDto = new PerfilDto(
                txtId.getText(),
                txtDescripcion.getText(),
                estado.isSelected()
        );
        int res = perfilFacade.modificar(perfilDto);
        if (res == 1) {
            perfiles.set(tbl_perfiles.getSelectionModel().getSelectedIndex(), perfilDto);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("El Perfil se ha Modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("No se ha podido Modificar el Perfil");
            msg.setHeaderText("Resultado");
            msg.show();
        }

    }

    @FXML
    public void eliminarPerfil() {
        int res = perfilFacade.eliminar(tbl_perfiles.getSelectionModel().getSelectedItem().getIdperfil());
        if (res == 1) {
            perfiles.remove(tbl_perfiles.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("El Perfil se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Perfil");
            msg.setContentText("El Perfil No ha sido eliminado");
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
        estado.setDisable(false);

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
        estado.setDisable(false);
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
