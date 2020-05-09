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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import perfil.dtoperfil.PerfilDto;
import perfil.facadeperfil.PerfilFacade;
import tipoTituloAcademico.dto.TtAcademico;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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
    @FXML
    private List<PerfilDto> listaPerfiles;
    @FXML
    int valor=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            validar();

        perfiles = FXCollections.observableArrayList(perfilFacade.obtenerCargos());

        tbl_perfiles.setItems(perfiles);

        colId.setCellValueFactory(new PropertyValueFactory<>("idperfil"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        estado.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(true);

        deshabilitarCampos();
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

                btnCrear.setDisable(true);
                btnGuardar.setDisable(true);
                btnModificar.setDisable(false);
                btnEliminar.setDisable(false);
                btnConsultar.setDisable(true);
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

            listaPerfiles=perfilFacade.buscar(txtId.getText());

            if(listaPerfiles.size()>=1){
                int i=0;

                if (listaPerfiles.get(0).isEstado()) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("El Perfil: " + txtId.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    colDescripcion.setText("");
                    txtId.requestFocus();

                }else{


                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("Código: " + txtId.getText() +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.setText("");
                    colDescripcion.setText("");
                    txtId.requestFocus();


                }




            }

        }


    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultarPerfil();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }








    @FXML
    public void guardarPerfil() {

        listaPerfiles= perfilFacade.buscar(txtId.getText());
        PerfilDto perfilDto = new PerfilDto(
                txtId.getText(),
                txtDescripcion.getText(),estado.isSelected()

        );
        if (listaPerfiles.isEmpty()) {
            if (txtId.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Perfil");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                //bt_guardar.setDisable(true);
                txtId.requestFocus();

            }else { int res = perfilFacade.agregar(perfilDto);
                if (res == 1) {
                    perfiles.add(perfilDto);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("El Perfil se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();

                } else {

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("No se ha podido agregar el Perfil");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();

                }

            }


            }else {
            if (txtId.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Perfil");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                txtDescripcion.requestFocus();

            } else {
                int res = perfilFacade.modificar(perfilDto);
                if (res == 1) {
                    //perfiles.set(tbl_perfiles.getSelectionModel().getSelectedIndex(), perfilDto);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("El Perfil se ha Modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }else{
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("No se ha podido Modificar el Perfil");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }
            }
        }
    }








    @FXML
    public void eliminarPerfil() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Perfil");
        msg.setContentText("¿Está seguro de eliminar el perfil?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta = perfilFacade.eliminar(txtId.getText());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Perfil");
                msge.setContentText("Error al eliminar! \n" + "El perfil tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                perfiles.remove(txtId.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Perfil");
                msg2.setContentText("El perfil se ha eliminado");
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
    private void consultarPerfil() {
        if (txtId.getText().isEmpty()) {
            txtId.setDisable(false);
            txtDescripcion.setDisable(true);
            txtId.requestFocus();
            btnCrear.setDisable(true);
            btnGuardar.setDisable(true);
            tbl_perfiles.setEditable(false);
            valor=0;
        } else {
            int i = 0;
            perfiles = FXCollections.observableArrayList(perfilFacade.buscar(txtId.getText()));
            if (perfiles.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Perfil");
                msg.setContentText("Perfil " + txtId.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                txtId.requestFocus();
                txtId.setText("");
            } else {
                if (perfiles.get(i).isEstado()) {


                    tbl_perfiles.setItems(perfiles);
                    colId.setCellValueFactory(new PropertyValueFactory<>("idperfil"));
                    colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    btnConsultar.setDisable(true);

                    txtId.setText(perfiles.get(i).getIdperfil());
                    txtDescripcion.setText(perfiles.get(i).getNombre());
                    btnEliminar.setDisable(false);
                    btnModificar.setDisable(false);
                } else {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Perfil");
                    msg.setContentText("Perfil " + txtId.getText() + " no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    txtId.requestFocus();
                    txtId.setText("");


                }
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
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnConsultar.setDisable(false);
        btnCrear.setDisable(false);
        txtDescripcion.setDisable(true);
        txtId.setDisable(true);
        btnGuardar.setDisable(true);

        perfiles = FXCollections.observableArrayList(perfilFacade.obtenerCargos());
        tbl_perfiles.setItems(perfiles);
        colId.setCellValueFactory(new PropertyValueFactory<>("idperfil"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }




}
