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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorInstitucionReferencia extends Component implements Initializable  {
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
    private TextField txtId,txtNombre,txtDireccion,txtTelefono;

    @FXML
    private Button btnCrear,btnConsultar,btnCancelar,btnSalir,btnGuardar,btnModificar,btnInhabilitar;
    @FXML
    private ObservableList<InstitucionReferencia> institucionReferencias;
    @FXML
    private List<InstitucionReferencia> institucionesReferencias;
    private String estado="1";
    int valor=0;


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

    }

    public void validar(){
        txtTelefono.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });
    }
    @FXML
    public void validarCamposVacios(){
        if (txtId.getText().isEmpty()){
            //validarTt.setText("Campo requerido");
            btnGuardar.setDisable(true);

        }else{
            //validarTt.setText("");
            btnGuardar.setDisable(false);
        }
        if(txtTelefono.getText().isEmpty()){
            btnGuardar.setDisable(true);
            // validarNombre.setText("Campo requerido");
        }else{
            //validarNombre.setText("");
            btnGuardar.setDisable(false);

        }

    }

    @FXML
    public void validarExistente(){

        txtNombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }
    public void validarE(){
        if(valor==1){
            institucionesReferencias = facadeInstitucionReferencia.buscar(txtId.getText());

            if(institucionesReferencias.size()>=1){
                int i=0;

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
                if(institucionesReferencias.get(0).getEstado().equals("1")){


                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("Código: " + institucionesReferencias.get(0).getEstado() +" existente no es posible agregar" );
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
    public void manejarEventos(){
        tblInstitucionReferencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InstitucionReferencia>() {
            @Override
            public void changed(ObservableValue<? extends InstitucionReferencia> observable, InstitucionReferencia oldValue, InstitucionReferencia newValue) {
                if (newValue != null){
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
    private void alerta(){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Gestiones - Institución académica");
        msg.setContentText("Campos requeridos");
        msg.setHeaderText("Resultado");
        msg.show();

    }

    @FXML
    private void botonGuardar() {
        institucionesReferencias = facadeInstitucionReferencia.buscar(txtId.getText());

        InstitucionReferencia institucionReferencia = new InstitucionReferencia(
                txtId.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(),estado
        );
        if (institucionesReferencias.isEmpty()) {
            if (txtId.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtNombre.getText().isEmpty()) {
                alerta();
                txtId.requestFocus();

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

            } else{
                if (txtId.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución Referencia");
                    msg.setContentText("campos requeridos");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.requestFocus();

                } else {


                    int res = facadeInstitucionReferencia.modificarInstitucionReferencia(institucionReferencia);
                    if (res == 1) {
                        institucionReferencias.set(tblInstitucionReferencia.getSelectionModel().getSelectedIndex(), institucionReferencia);
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
        int res = facadeInstitucionReferencia.eliminarInstitucionReferencia(tblInstitucionReferencia.getSelectionModel().getSelectedItem().getIdInstitucion());
        int i = JOptionPane.showConfirmDialog(this,"Esta seguro de eliminar el la institución de referencia");
        if(i==0){
            if (res == 1) {
                institucionReferencias.remove(tblInstitucionReferencia.getSelectionModel().getSelectedIndex());
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Institución Referencia");
                msg.setContentText("La  referencia se ha eliminado");
                msg.setHeaderText("Resultado");
                msg.show();
            }else{
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Institución Referencia");
                msg.setContentText("La referencia. No ha sido eliminada");
                msg.setHeaderText("Resultado");
                msg.show();
            }
            limpiarFormulario();
            cancelar();

        }else if(i==1){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La referemcia. No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
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
        } else {
            int i = 0;
            institucionReferencias = FXCollections.observableArrayList(facadeInstitucionReferencia.buscar(txtId.getText()));
            if (institucionReferencias.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Institución de Referencia");
                msg.setContentText("Código " + txtId.getText() +" de referencia no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();

            } else {


                if (institucionReferencias.get(i).getEstado().equals("1")) {

                    tblInstitucionReferencia.setItems(institucionReferencias);
                    colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                    colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                }
                if (institucionReferencias.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución de Referencia");
                    msg.setContentText("Código " + txtId.getText() +" de referencia no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();

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
    public void modificar(){
        txtId.setDisable(true);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtNombre.requestFocus();
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        valor =0;
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
        valor=1;
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
