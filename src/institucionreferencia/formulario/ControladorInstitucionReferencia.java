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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorInstitucionReferencia extends Component implements Initializable {
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
    private TextField txtId, txtNombre, txtDireccion, txtTelefono;

    @FXML
    private Button btnCrear, btnConsultar, btnCancelar, btnSalir, btnGuardar, btnModificar, btnInhabilitar;
    @FXML
    private ObservableList<InstitucionReferencia> institucionReferencias;
    @FXML
    private List<InstitucionReferencia> institucionesReferencias;
    private String estado = "1";
    int valor = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        institucionReferencias = FXCollections.observableArrayList(facadeInstitucionReferencia.ListarTodas());

        tblInstitucionReferencia.setItems(institucionReferencias);

        colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        btnGuardar.setDisable(true);
        deshabilitarCampos();
        manejarEventos();
        reiniciarStilosCamposRequeridos();

    }

    public void validar() {
        txtTelefono.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });
        txtTelefono.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }
                txtTelefono.setStyle(null);
            }
        });
    }

    @FXML
    public void validarCamposVacios() {
        if (txtId.getText().isEmpty()) {
            //validarTt.setText("Campo requerido");
            btnGuardar.setDisable(true);

        } else {
            //validarTt.setText("");
            btnGuardar.setDisable(false);
        }
        if (txtTelefono.getText().isEmpty()) {
            btnGuardar.setDisable(true);
            // validarNombre.setText("Campo requerido");
        } else {
            //validarNombre.setText("");
            btnGuardar.setDisable(false);

        }

    }

    @FXML
    public void validarExistente() {

        txtNombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
                txtNombre.setStyle(null);
            }
        });
    }

    public void validarE() {
        if (valor == 1) {
            institucionesReferencias = facadeInstitucionReferencia.buscar(txtId.getText());

            if (institucionesReferencias.size() >= 1) {
                int i = 0;

                if (institucionesReferencias.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("La institución de referencia : " + txtId.getText() + " se escuentra registrada, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    txtNombre.setText("");
                    txtId.requestFocus();
                }
                if (institucionesReferencias.get(0).getEstado().equals("1")) {

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("Código: " + txtId.getText() + " existente no es posible agregar");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    txtNombre.setText("");
                    txtId.requestFocus();
                }
            }
        }
    }

    @FXML
    public void reiniciarStilosCamposRequeridos() {//Metodo para reiniciar los estilos de las validaciones

        txtId.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                txtId.setStyle(null);
            }
        });
        txtDireccion.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                txtDireccion.setStyle(null);
            }
        });
    }

    @FXML
    public void manejarEventos() {
        tblInstitucionReferencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InstitucionReferencia>() {
            @Override
            public void changed(ObservableValue<? extends InstitucionReferencia> observable, InstitucionReferencia oldValue, InstitucionReferencia newValue) {
                if (newValue != null) {
                    txtId.setText(newValue.getIdInstitucion());
                    txtNombre.setText(newValue.getNombre());
                    txtDireccion.setText(newValue.getDireccion());
                    txtTelefono.setText(newValue.getTelefono());

                    btnCrear.setDisable(true);
                    btnGuardar.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                    btnConsultar.setDisable(true);
                    txtId.setDisable(true);


                }
            }
        });
    }

    private void alerta() {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Gestiones - Institución de referencia");
        msg.setContentText("Campos requeridos");
        msg.setHeaderText("Resultado");
        msg.show();

    }

    @FXML
    public void textAction(KeyEvent e) {
        if (valor == 0) {


            if (e.getCode().equals(KeyCode.ENTER))
                consultarInsReferencia();
        }
    }

    @FXML
    public void textESC(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }


    @FXML
    private void botonGuardar() {
        institucionesReferencias = facadeInstitucionReferencia.buscar(txtId.getText());

        InstitucionReferencia institucionReferencia = new InstitucionReferencia(
                txtId.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(), estado
        );
        if (institucionesReferencias.isEmpty()) {
            if (txtId.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Código Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtId.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtId.requestFocus();

            } else if (txtNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Nombre Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtNombre.requestFocus();

            } else if (txtDireccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Dirección Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtDireccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtDireccion.requestFocus();

            } else if (txtTelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Teléfono v");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtTelefono.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtTelefono.requestFocus();

            } else {
                int res = facadeInstitucionReferencia.agregarInstitucionReferencia(institucionReferencia);
                if (res == 1) {
                    btnGuardar.setDisable(true);
                    institucionReferencias.add(institucionReferencia);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución Referencia");
                    msg.setContentText("La institución se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();

                } else {
                    cancelar();
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución Referencia");
                    msg.setContentText("No se ha podido agregar la institucion");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }
            }

        } else {
            if (txtId.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Código Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtId.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtId.requestFocus();

            } else if (txtNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Nombre Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtNombre.requestFocus();

            } else if (txtDireccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Dirección Institución de Referencia");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtDireccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtDireccion.requestFocus();

            } else if (txtTelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución de Referencia");
                msg.setContentText("Teléfono v");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtTelefono.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtTelefono.requestFocus();

            } else {
                int res = facadeInstitucionReferencia.modificarInstitucionReferencia(institucionReferencia);
                if (res == 1) {
                    // institucionReferencias.set(tblInstitucionReferencia.getSelectionModel().getSelectedIndex(), institucionReferencia);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución Referencia");
                    msg.setContentText("La Referencia se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    btnGuardar.setDisable(true);
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución Referencia");
                    msg.setContentText("La Referencia , No ha sido modificada");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }
            }
        }
    }

    @FXML
    public void eliminarInstitucionReferencia() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Institución Referencia");
        msg.setContentText("¿Está seguro de eliminar la referencia?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta = facadeInstitucionReferencia.eliminarInstitucionReferencia(txtId.getText());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Institución Referencia");
                msge.setContentText("Error al eliminar! \n" + "La referencia tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                institucionReferencias.remove(txtId.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Institución Referencia");
                msg2.setContentText("La EPS se ha eliminado");
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
    public void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    @FXML
    private void consultarInsReferencia() {
        if (txtId.getText().isEmpty()) {
            txtId.setDisable(false);
            txtNombre.setDisable(true);
            txtDireccion.setDisable(true);
            txtTelefono.setDisable(true);
            txtId.requestFocus();
            btnCrear.setDisable(true);
            btnGuardar.setDisable(true);
            tblInstitucionReferencia.setEditable(false);
            valor = 0;
        } else {
            int i = 0;
            institucionReferencias = FXCollections.observableArrayList(facadeInstitucionReferencia.buscar(txtId.getText()));
            if (institucionReferencias.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Institución de Referencia");
                msg.setContentText("Código " + txtId.getText() + " de referencia no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                txtId.setText("");
                txtId.requestFocus();

            } else {


                if (institucionReferencias.get(i).getEstado().equals("1")) {

                    tblInstitucionReferencia.setItems(institucionReferencias);
                    colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                    colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                    btnConsultar.setDisable(true);

                    txtId.setText(institucionReferencias.get(i).getIdInstitucion());
                    txtNombre.setText(institucionReferencias.get(i).getNombre());
                    txtDireccion.setText(institucionReferencias.get(i).getDireccion());
                    txtTelefono.setText(institucionReferencias.get(i).getTelefono());
                    btnInhabilitar.setDisable(false);
                    btnModificar.setDisable(false);
                    txtId.setDisable(true);
                }
                if (institucionReferencias.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("Código " + txtId.getText() + " de referencia no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    txtId.requestFocus();

                }

            }

        }

    }


    @FXML
    public void cancelar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        btnConsultar.setDisable(false);
        btnCrear.setDisable(false);
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);

        btnGuardar.setDisable(true);
        institucionReferencias = FXCollections.observableArrayList(facadeInstitucionReferencia.ListarTodas());

        tblInstitucionReferencia.setItems(institucionReferencias);

        colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
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
    public void modificar() {
        txtId.setDisable(true);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtNombre.requestFocus();
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        valor = 0;
        btnGuardar.setDisable(false);
    }

    @FXML
    private void crear() {
        btnCrear.setDisable(true); //siempre ira deshabilitado
        btnConsultar.setDisable(true);
        btnCancelar.setDisable(false);
        btnSalir.setDisable(false);
        // bt_guardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        habilitarCampos();
        valor = 1;
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
    public void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
    }

    @FXML
    private void cerrarInstitucionReferencia(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
