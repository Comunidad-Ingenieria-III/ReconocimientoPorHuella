package eps.formularioeps;

import eps.dao.DaoEps;
import eps.dto.DtoEps;
import eps.facadeeps.FacadeEps;
import institucionAcademica.dto.InstitucionAcademica;
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

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EPS implements Initializable {

    FacadeEps facadeEps = new FacadeEps();

    @FXML
    private TableView<DtoEps> tb_eps;


    @FXML
    private TableColumn<DtoEps, String> colId;
    @FXML
    private TableColumn<DtoEps, String> colNombre;
    @FXML
    private TableColumn<DtoEps, String> colDireccion;
    @FXML
    private TableColumn<DtoEps, String> colTelefono;


    @FXML
    private TextField tf_Codigo;
    @FXML
    private TextField tf_Nombre;
    @FXML
    private TextField tf_Direccion;
    @FXML
    private TextField tf_Telefono;
    @FXML
    private Button bt_Crear;
    @FXML
    private Button bt_Consultar;
    @FXML
    private Button bt_Cancelar;
    @FXML
    private Button bt_Salir;
    @FXML
    private Button bt_Guardar;
    @FXML
    private Button bt_Modificar;
    @FXML
    private Button bt_Inhabilitar;

    private ObservableList<DtoEps> epss;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        epss = FXCollections.observableArrayList(facadeEps.CargarEps());


        tb_eps.setItems(epss);

        colId.setCellValueFactory(new PropertyValueFactory<>("idEps"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEps"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEps"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telEps"));

        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
        bt_Guardar.setDisable(true);

        manejarEventos();
    }

    public void manejarEventos() {
        tb_eps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DtoEps>() {
            @Override
            public void changed(ObservableValue<? extends DtoEps> observable, DtoEps oldValue, DtoEps newValue) {
                if (newValue != null) {
                    tf_Codigo.setText(newValue.getIdEps());
                    tf_Nombre.setText(newValue.getNombreEps());
                    tf_Direccion.setText(newValue.getdireccionEps());
                    tf_Telefono.setText(newValue.getTelEps());

                    bt_Crear.setDisable(false);
                    bt_Guardar.setDisable(true);
                    bt_Modificar.setDisable(false);
                    bt_Inhabilitar.setDisable(false);
                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    public void guardarEps() {

        DtoEps dtoEps = new DtoEps(
                tf_Codigo.getText(),
                tf_Nombre.getText(),
                tf_Direccion.getText(),
                tf_Telefono.getText()
        );

        int res = facadeEps.insertarEps(dtoEps);

        if (res == 1) {
            epss.add(dtoEps);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("La EPS se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - EPS Academicas");
            msg.setContentText("No se ha podido agregar la EPS");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void modificarEps() {
        DtoEps dtoEps = new DtoEps(
                tf_Codigo.getText(),
                tf_Nombre.getText(),
                tf_Direccion.getText(),
                tf_Telefono.getText()

        );
        int res = facadeEps.modificarEps(dtoEps);

        if (res == 1) {
            epss.set(tb_eps.getSelectionModel().getSelectedIndex(), dtoEps);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("La EPS se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("La EPS No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void eliminarEps() {
        int res = facadeEps.eliminarEps(tb_eps.getSelectionModel().getSelectedItem().getIdEps());
        if (res == 1) {
            epss.remove(tb_eps.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("La EPS se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("La EPS No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();

    }

    public void validarCamposVacios() {
        if (tf_Codigo.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("Debe ingresar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void validarB() {
        bt_Guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validarCamposVacios();
            }
        });
    }


    @FXML
    public void limpiarFormulario() {
        tf_Codigo.setText("");
        tf_Nombre.setText("");
        tf_Direccion.setText("");
        tf_Telefono.setText("");
        tf_Codigo.requestFocus();

        bt_Crear.setDisable(false);
        bt_Guardar.setDisable(false);
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
    }

    @FXML
    public void cancelar() {
        tf_Codigo.setText("");
        tf_Nombre.setText("");
        tf_Direccion.setText("");
        tf_Telefono.setText("");
        tf_Codigo.requestFocus();
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
    }

    @FXML
    private void cerarEps(ActionEvent event) {
        Stage stage = (Stage) bt_Salir.getScene().getWindow();
        stage.close();
    }

}
