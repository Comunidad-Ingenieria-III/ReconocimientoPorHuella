package institucionreferencia.formulario;

import institucionreferencia.dto.InstitucionReferencia;
import institucionreferencia.facade.FacadeInstitucionReferencia;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorInstitucionReferencia implements Initializable {
    FacadeInstitucionReferencia facadeInstitucionReferencia = new FacadeInstitucionReferencia();

    @FXML
    private TableView<InstitucionReferencia> tblInstitucionReferencia;

    @FXML
    private TableColumn<ControladorInstitucionReferencia, String> colId;
    @FXML
    private TableColumn<InstitucionReferencia, String> colNombre;
    @FXML
    private TableColumn<InstitucionReferencia, String> colDireccion;
    @FXML
    private TableColumn<InstitucionReferencia, String> colTelefono;



    @FXML
    private TextField txtId;
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
    private Button btnCancelar;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnInhabilitar;

    private ObservableList<InstitucionReferencia> institucionReferencias;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        institucionReferencias = FXCollections.observableArrayList(facadeInstitucionReferencia.ListarTodas());

        tblInstitucionReferencia.setItems(institucionReferencias);


        colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        deshabilitarBotones();
        deshabilitarCampos();
        manejarEventos();
        eventoCrear();

    }

    private void eventoCrear() {
        btnCrear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                habilitarCampos();
                btnInhabilitar.setDisable(true);
                btnModificar.setDisable(true);
                btnGuardar.setDisable(false);
            }
        });
    }

    @FXML
    private void eventoCancelar(){
        btnCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                limpiar();
                txtId.requestFocus();
                btnModificar.setDisable(true);
                btnInhabilitar.setDisable(true);
            }
        });
    }

    @FXML
    private void eventoGuardar(){
        btnGuardar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
                    txtTelefono.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "DEBE LLENAR TODOS LOS CAMPOS",
                            "ERROR AL INTENTAR GUARDAR", JOptionPane.ERROR_MESSAGE);

                }else {
                    guardarInstitucionReferencia();
                }

            }
        });
    }


    public void manejarEventos(){
        tblInstitucionReferencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InstitucionReferencia>() {
            @Override
            public void changed(ObservableValue<? extends InstitucionReferencia> observable, InstitucionReferencia oldValue, InstitucionReferencia newValue) {
                if (newValue != null){
                    txtId.setText(newValue.getIdInstitucion());
                    txtNombre.setText(newValue.getNombre());
                    txtDireccion.setText(newValue.getDireccion());
                    txtTelefono.setText(newValue.getTelefono());

                    btnCrear.setDisable(false);
                    btnGuardar.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                    habilitarCampos();

                }
            }
        });
    }

    @FXML
    public void guardarInstitucionReferencia() {

        InstitucionReferencia institucionReferencia = new InstitucionReferencia(
                txtId.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()
        );

        int res = facadeInstitucionReferencia.agregarInstitucionReferencia(institucionReferencia);

        if (res == 1) {
            institucionReferencias.add(institucionReferencia);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("La institución se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("No se ha podido agregar la institucion");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();
    }

    @FXML
    public void modificarInstitucionReferencia() {
        InstitucionReferencia institucionReferencia = new InstitucionReferencia(
                txtId.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()

        );
        int res = facadeInstitucionReferencia.modificarInstitucionReferencia(institucionReferencia);
        if (res == 1) {
            institucionReferencias.set(tblInstitucionReferencia.getSelectionModel().getSelectedIndex(), institucionReferencia);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("La institución se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("La institución No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();
    }

    @FXML
    public void eliminarInstitucionReferencia() {
        int res = facadeInstitucionReferencia.eliminarInstitucionReferencia(tblInstitucionReferencia.getSelectionModel().getSelectedItem().getIdInstitucion());
        if (res == 1) {
            institucionReferencias.remove(tblInstitucionReferencia.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("La institución se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Institución Referencia");
            msg.setContentText("La institución No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiar();

    }


    @FXML
    public void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        btnCancelar.setDisable(false);
    }

    @FXML
    private void habilitarBotones() {
        btnCrear.setDisable(true); //siempre ira deshabilitado
        btnConsultar.setDisable(true);
        btnCancelar.setDisable(true);
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
        btnCancelar.setDisable(false);
        btnSalir.setDisable(false);
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);

    }

    @FXML
    private void habilitarCampos() {
        txtId.setDisable(false);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtId.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);
    }

    @FXML
    private void cerrarInstitucionReferencia(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
