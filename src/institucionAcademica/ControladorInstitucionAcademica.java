package institucionAcademica;

import institucionAcademica.dao.FacadeInstitucionAcademica;
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

        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        btnGuardar.setDisable(true);

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

                    btnCrear.setDisable(false);
                    btnGuardar.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    public void guardarInstitucion() {

        InstitucionAcademica institucionAcademica = new InstitucionAcademica(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()
        );

        int res = facade.agregar(institucionAcademica);

        if (res == 1) {
            instituciones.add(institucionAcademica);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("No se ha podido agregar la institucion");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void modificarInstitucion() {
        InstitucionAcademica institucionAcademica = new InstitucionAcademica(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()

        );
        int res = facade.modificar(institucionAcademica);
        if (res == 1) {
            instituciones.set(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex(), institucionAcademica);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void eliminarInstitucion() {
        int res = facade.eliminar(tbIinstitucionAcademica.getSelectionModel().getSelectedItem().getIdInstitucion());
        if (res == 1) {
            instituciones.remove(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();

    }

    public void validarCamposVacios(){
        if (txtCodigo.getText().isEmpty()){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("Debe ingresar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void validarB(){
        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validarCamposVacios();
            }
        });
    }


    @FXML
    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCodigo.requestFocus();

        btnCrear.setDisable(false);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
    }

   @FXML
    public void cancelar(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCodigo.requestFocus();
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
