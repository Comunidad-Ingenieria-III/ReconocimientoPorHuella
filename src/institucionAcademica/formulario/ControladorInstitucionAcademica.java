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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorInstitucionAcademica extends Component implements Initializable {
    FacadeInstitucionAcademica facade =  new FacadeInstitucionAcademica();
    @FXML
    private TableView<InstitucionAcademica> tbIinstitucionAcademica;
    @FXML
    private TableColumn<InstitucionAcademica, String> colId,colNombre,colDireccion,colTelefono;

    @FXML
    private TextField txtCodigo,txtNombre,txtDireccion,txtTelefono;

    @FXML
    private Button btnCrear,btnConsultar,bntCancelar,btnSalir,btnGuardar,btnModificar,btnInhabilitar;

    @FXML
    private Label lblCodigo,lbNombre,lblDireccion,lblTelefono;

    @FXML
    private ObservableList<InstitucionAcademica> instituciones;
    @FXML
    private List<InstitucionAcademica> institucionAcademicas;
    @FXML int valor =1;



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

    }

    public void validarNumerostelefono(){

        txtTelefono.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
    }

    public void validar(){
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

            }

        });
    }
    @FXML
    public void validarCamposVacios(){
        if (txtCodigo.getText().isEmpty()){
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


        instituciones = FXCollections.observableArrayList(facade.buscar(txtCodigo.getText()));
        if(instituciones.size()>=1){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de titulo académico");
            msg.setContentText("Codigo existente no es posible agregar");
            msg.setHeaderText("Resultado");
            msg.show();
            txtCodigo.setText("");

            txtCodigo.requestFocus();

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
    private void botonGuardar(){

        institucionAcademicas= facade.buscar(txtCodigo.getText());
        InstitucionAcademica institucionAcademica = new InstitucionAcademica(
                txtCodigo.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtTelefono.getText()
        );
        if (institucionAcademicas.isEmpty()) {
            if (txtCodigo.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtNombre.getText().isEmpty()) {

                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Institución académica");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                txtCodigo.requestFocus();
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
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("No se ha podido agregar la institución");
                    msg.setHeaderText("Resultado");
                    msg.show();
                }


            }
        } else {

            if (txtCodigo.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Institución cadémica");
                msg.setContentText("campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                txtCodigo.requestFocus();

            } else {


                int res = facade.modificar(institucionAcademica);
                if (res == 1) {
                    btnGuardar.setDisable(true);
                    instituciones.set(tbIinstitucionAcademica.getSelectionModel().getSelectedIndex(), institucionAcademica);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    btnGuardar.setDisable(true);
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Institución académica");
                    msg.setContentText("La institución , No ha sido modificada");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }

            }
        }




    }

    @FXML
    public void modificar(){
        txtCodigo.setDisable(true);
        txtNombre.setDisable(false);
        txtDireccion.setDisable(false);
        txtTelefono.setDisable(false);
        txtNombre.requestFocus();
        btnModificar.setDisable(true);
        btnInhabilitar.setDisable(true);
        valor=0;
        btnGuardar.setDisable(false);
    }



    @FXML
    public void eliminarInstitucion() {
        int res = facade.eliminar(tbIinstitucionAcademica.getSelectionModel().getSelectedItem().getIdInstitucion());
        int i = JOptionPane.showConfirmDialog(this,"Esta seguro de eliminar el la institución");
        if(i==0){
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
            cancelar();

        }else if(i==1){
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Instituciones Academicas");
            msg.setContentText("La institucion No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
        cancelar();

    }





    @FXML
    private void consultarInstitucion() {
        if(txtCodigo.getText().isEmpty()){
            txtCodigo.setDisable(false);
            txtNombre.setDisable(true);
            txtDireccion.setDisable(true);
            txtTelefono.setDisable(true);
            txtCodigo.requestFocus();
            btnCrear.setDisable(true);
            btnGuardar.setDisable(true);
            tbIinstitucionAcademica.setEditable(false);
        }else{

            instituciones = FXCollections.observableArrayList(facade.buscar(txtCodigo.getText()));
            tbIinstitucionAcademica.setItems(instituciones);

            colId.setCellValueFactory(new PropertyValueFactory<>("idInstitucion"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            btnConsultar.setDisable(true);


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
        valor=1;
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
