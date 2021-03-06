package institucionAcademica.formulario;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorInstitucionAcademica extends Component implements Initializable {
    FacadeInstitucionAcademica facade = new FacadeInstitucionAcademica();
    @FXML
    private TableView<InstitucionAcademica> tbIinstitucionAcademica;
    @FXML
    private TableColumn<InstitucionAcademica, String> colId, colNombre, colDireccion, colTelefono;

    @FXML
    private TextField txtCodigo, txtNombre, txtDireccion, txtTelefono;

    @FXML
    private Button btnCrear, btnConsultar, bntCancelar, btnSalir, btnGuardar, btnModificar, btnInhabilitar;

    @FXML
    private Label lblCodigo, lbNombre, lblDireccion, lblTelefono;

    @FXML
    private ObservableList<InstitucionAcademica> instituciones;
    @FXML
    private List<InstitucionAcademica> institucionAcademicas;
    @FXML
    int valor = 1;
    private String estado = "1";


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
        deshabilitarCampos();
        manejarEventos();
        reiniciarStilosCamposRequeridos();

    }

    @FXML
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
        if (txtCodigo.getText().isEmpty()) {
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
            institucionAcademicas = facade.buscar(txtCodigo.getText());
            int i = 0;
            if (institucionAcademicas.size() >= 1) {
                if (institucionAcademicas.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución : " + txtCodigo.getText() + " se escuentra registrada, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtCodigo.setText("");
                    txtCodigo.setText("");
                    txtCodigo.requestFocus();
                }

                if (institucionAcademicas.get(0).getEstado().equals("1")) {

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("Codigo: " + txtCodigo.getText() + " existente no es posible agregar");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtCodigo.setText("");
                    txtCodigo.setText("");
                    txtCodigo.requestFocus();
                }

            }

        }

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
                    btnGuardar.setDisable(true);
                    btnModificar.setDisable(false);
                    btnInhabilitar.setDisable(false);
                    btnConsultar.setDisable(true);
                    txtCodigo.setDisable(true);

                }

            }
        });//FIN DEL LISTENER
    }


    @FXML
    public void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    @FXML
    public void textAction(KeyEvent e) {
        if (valor == 0) {
            if (e.getCode().equals(KeyCode.ENTER))
                consultarInstitucion();
        }
    }

    @FXML
    public void textESC(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }


    @FXML
    public void cancelar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        btnConsultar.setDisable(false);
        btnCrear.setDisable(false);
        txtCodigo.setDisable(true);
        txtNombre.setDisable(true);
        txtDireccion.setDisable(true);
        txtTelefono.setDisable(true);


        btnGuardar.setDisable(true);

        instituciones = FXCollections.observableArrayList(facade.obtenerTodasInstituciones());
        tbIinstitucionAcademica.setItems(instituciones);
        colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

    }

    @FXML
    private void botonGuardar() {

        institucionAcademicas = facade.buscar(txtCodigo.getText());
        InstitucionAcademica institucionAcademica = new InstitucionAcademica(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(), estado
        );
        if (institucionAcademicas.isEmpty()) {
            if (txtCodigo.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Código Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtCodigo.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtCodigo.requestFocus();

            } else if (txtNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Nombre Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtNombre.requestFocus();

            } else if (txtDireccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Dirección Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtDireccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtDireccion.requestFocus();

            } else if (txtTelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Teléfono Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtTelefono.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtTelefono.requestFocus();

            } else {

                int res = facade.agregar(institucionAcademica);
                if (res == 1) {
                    btnGuardar.setDisable(true);
                    instituciones.add(institucionAcademica);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();


                } else {
                    cancelar();
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("No se ha podido agregar la institución");
                    msg.setHeaderText("Algo salió mal");
                    msg.show();
                    cancelar();
                }
            }

        } else {

            if (txtCodigo.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Código Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtCodigo.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtCodigo.requestFocus();

            } else if (txtNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Nombre Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtNombre.requestFocus();

            } else if (txtDireccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Dirección Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtDireccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtDireccion.requestFocus();

            } else if (txtTelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Institución Académica");
                msg.setContentText("Teléfono Institución Académica");
                msg.setHeaderText("Campo requerido");
                msg.show();
                txtTelefono.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                txtTelefono.requestFocus();

            } else {

                int res = facade.modificar(institucionAcademica);
                if (res == 1) {
                    btnGuardar.setDisable(true);
                    // instituciones.set(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex(), institucionAcademica);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución se ha modificado");
                    msg.setHeaderText("Información");
                    msg.show();
                    cancelar();
                } else {
                    btnGuardar.setDisable(true);
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución , No ha sido modificada");
                    msg.setHeaderText("Algo salió mal");
                    msg.show();
                    cancelar();
                }
            }
        }
    }

    @FXML
    public void reiniciarStilosCamposRequeridos() {//Metodo para reiniciar los estilos de las validaciones

        txtCodigo.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                txtCodigo.setStyle(null);
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
    public void modificar() {
        txtCodigo.setDisable(true);
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
    public void eliminarInstitucion() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Institución académica");
        msg.setContentText("¿Está seguro de eliminar la institución?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta = facade.eliminar(txtCodigo.getText());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Institución académica");
                msge.setContentText("Error al eliminar! \n" + "La institución tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                instituciones.remove(txtCodigo.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Institución académica");
                msg2.setContentText("La institución se ha eliminado");
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
    private void consultarInstitucion() {
        if (txtCodigo.getText().isEmpty()) {
            txtCodigo.setDisable(false);
            txtNombre.setDisable(true);
            txtDireccion.setDisable(true);
            txtTelefono.setDisable(true);
            txtCodigo.requestFocus();
            btnCrear.setDisable(true);
            btnGuardar.setDisable(true);
            tbIinstitucionAcademica.setEditable(false);
            valor = 0;
        } else {
            int i = 0;
            instituciones = FXCollections.observableArrayList(facade.buscar(txtCodigo.getText()));
            if (instituciones.isEmpty()) {

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Instituciones Academicas");
                msg.setContentText("El código : " + txtCodigo.getText() + " no se ha encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                txtCodigo.requestFocus();
                txtCodigo.setText("");
            } else {

                if (instituciones.get(i).getEstado().equals("1")) {

                    tbIinstitucionAcademica.setItems(instituciones);

                    colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                    colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                    btnConsultar.setDisable(true);

                    txtCodigo.setText(instituciones.get(i).getIdInstitucion());
                    txtNombre.setText(instituciones.get(i).getNombre());
                    txtDireccion.setText(instituciones.get(i).getDireccion());
                    txtTelefono.setText(instituciones.get(i).getTelefono());
                    btnInhabilitar.setDisable(false);
                    btnModificar.setDisable(false);
                    txtCodigo.setDisable(true);
                }
                if (instituciones.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Instituciones Academicas");
                    msg.setContentText("El código : " + txtCodigo.getText() + " no se ha encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtCodigo.requestFocus();
                    txtCodigo.setText("");
                }

            }


        }

    }


    @FXML
    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
    }

    @FXML
    private void crear() {
        btnCrear.setDisable(true); //siempre ira deshabilitado
        btnConsultar.setDisable(true);
        bntCancelar.setDisable(false);
        btnSalir.setDisable(false);
        // bt_guardar.setDisable(true);
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        habilitarCampos();
    }

    @FXML
    private void habilitarCampos() {
        txtCodigo.setDisable(false);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtCodigo.requestFocus();
        valor = 1;
        btnGuardar.setDisable(false);
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
