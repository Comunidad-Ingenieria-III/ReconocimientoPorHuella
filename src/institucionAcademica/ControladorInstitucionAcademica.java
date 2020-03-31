package institucionAcademica;

import institucionAcademica.dao.FacadeInstitucionAcademica;
import institucionAcademica.dto.InstitucionAcademica;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ControladorInstitucionAcademica implements Initializable {
    FacadeInstitucionAcademica facade =  new FacadeInstitucionAcademica();
    @FXML
    private TableView<InstitucionAcademica> tbIinstitucionAcademica;


    @FXML
    private TableColumn<InstitucionAcademica, String> colId;
    @FXML
    private TableColumn<InstitucionAcademica, String> colNombre;
    @FXML
    private TableColumn<InstitucionAcademica, String> colDireccion;
    @FXML
    private TableColumn<InstitucionAcademica, String> colTelefono;


    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button bntCancelar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnInhabilitar;

    private ObservableList<InstitucionAcademica> instituciones;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instituciones = FXCollections.observableArrayList(facade.obtenerTodasInstituciones());


        tbIinstitucionAcademica.setItems(instituciones);

        colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));



        manejarEventos();
    }

    public void manejarEventos() {
        tbIinstitucionAcademica.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InstitucionAcademica>() {
            @Override
            public void changed(ObservableValue<? extends InstitucionAcademica> observable, InstitucionAcademica oldValue, InstitucionAcademica newValue) {
                if (newValue != null) {
                    txtCodigo.setText(newValue.getIdInstitucion());
                    txtNombre.setText(newValue.getNombre());
                    txtDireccion.setText(newValue.getDireccion());
                    txtTelefono.setText(newValue.getTelefono());

                    btnCrear.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                }

            }
        });//FIN DEL LISTENER
    }








    @FXML
    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
    }

    @FXML
    private void habilitarBotones() {
        btnCrear.setDisable(true); //siempre ira deshabilitado
        btnConsultar.setDisable(true);
        bntCancelar.setDisable(true);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        habilitarCampos();
    }

    @FXML
    private void deshabilitarBotones() {
        btnCrear.setDisable(false); //siempre ira deshabilitado
        btnConsultar.setDisable(false);
        bntCancelar.setDisable(false);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);

    }

    @FXML
    private void habilitarCampos() {
        txtCodigo.setDisable(false);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtCodigo.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        txtCodigo.setDisable(true);
        txtNombre.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);
    }

    @FXML
    private void cerrarInstitucionAcademica(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
