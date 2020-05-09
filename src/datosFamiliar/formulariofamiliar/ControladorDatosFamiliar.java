package datosFamiliar.formulariofamiliar;

import datosFamiliar.dtofamiliar.Familiar;
import datosFamiliar.facadefamiliar.Facade;
import eps.dto.DtoEps;
import institucionreferencia.dto.InstitucionReferencia;
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
import java.net.URL;
import java.nio.file.Watchable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorDatosFamiliar implements Initializable {

    private Facade facade = new Facade();


    @FXML
    private TableView<Familiar> tblFamiliares;

    @FXML
    private TableColumn<Familiar, String> colId;
    @FXML
    private TableColumn<Familiar, String> colNombre1;
    @FXML
    private TableColumn<Familiar, String> colNombre2;
    @FXML
    private TableColumn<Familiar, String> colApellido1;
    @FXML
    private TableColumn<Familiar, String> colApellido2;
    @FXML
    private TableColumn<Familiar, String> colDireccion;
    @FXML
    private TableColumn<Familiar, String> colTelefono;

    @FXML
    private TextField tf_idfamiliar;
    @FXML
    private TextField tf_nombre1;
    @FXML
    private TextField tf_nombre2;
    @FXML
    private TextField tf_apellido1;
    @FXML
    private TextField tf_apellido2;
    @FXML
    private TextField tf_direccion;
    @FXML
    private TextField tf_numtelefono;
    @FXML
    private Button bt_crear;
    @FXML
    private Button bt_consultar;
    @FXML
    private Button bt_cancelar;
    @FXML
    private Button bt_salir;
    @FXML
    private Button bt_guardar;
    @FXML
    private Button bt_modificar;
    @FXML
    private Button bt_inhabilitar;
    private String estado="1";
    private ObservableList<Familiar> familiares;
    @FXML
    private List<Familiar> listaFamiliares;
    @FXML
    int valor=0;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        familiares = FXCollections.observableArrayList(facade.obtenerTodosFamiliares());

        tblFamiliares.setItems(familiares);

        colId.setCellValueFactory(new PropertyValueFactory<>("idFamiliar"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<>("segundoNombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telFamiliar"));



        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_guardar.setDisable(true);
        deshabilitarCampos();
        manejarEventos();
    }

    public void manejarEventos() {
        tblFamiliares.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Familiar>() {
            @Override
            public void changed(ObservableValue<? extends Familiar> observable, Familiar oldValue, Familiar newValue) {
                if (newValue != null ){
                    tf_idfamiliar.setText(newValue.getIdFamiliar() + "");
                    tf_nombre1.setText(newValue.getPrimerNombre());
                    tf_nombre2.setText(newValue.getSegundoNombre());
                    tf_apellido1.setText(newValue.getPrimerApellido());
                    tf_apellido2.setText(newValue.getSegundoApellido());
                    tf_direccion.setText(newValue.getDireccion());
                    tf_numtelefono.setText(newValue.getTelFamiliar());

                    bt_crear.setDisable(true);
                    bt_guardar.setDisable(true);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                    bt_consultar.setDisable(true);

                }
            }

            

        });//FIN DEL LISTENER
    }

    public void validarExistente(){

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){

            listaFamiliares=facade.buscar(tf_idfamiliar.getText());
            if(listaFamiliares.size()>=1){
                int i=0;

                if (listaFamiliares.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Familiar Paciente");
                    msg.setContentText("Familiar: " + tf_idfamiliar.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_idfamiliar.setText("");
                    tf_nombre1.setText("");
                    tf_idfamiliar.requestFocus();

                }
                if(listaFamiliares.get(0).getEstado().equals("1")){

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Familiar Paciente");
                    msg.setContentText("Familiar: " + tf_idfamiliar.getText() +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_idfamiliar.setText("");
                    tf_nombre1.setText("");
                    tf_idfamiliar.requestFocus();
                }
            }
        }
    }

    @FXML
    public void validarId() {//Metodo para validar que el Id del cargo solo reciba numeros
        tf_idfamiliar.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
        tf_numtelefono.setOnKeyTyped(new EventHandler<KeyEvent>() {
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
    public void guardarFamiliar() {
        listaFamiliares=facade.buscar(tf_idfamiliar.getText());
        Familiar familiar= new Familiar(tf_idfamiliar.getText(), tf_nombre1.getText(), tf_nombre2.getText(), tf_apellido1.getText(),
                tf_apellido2.getText(), tf_direccion.getText(), tf_numtelefono.getText(), estado);
        if (listaFamiliares.isEmpty()) {
            if (tf_idfamiliar.getText().isEmpty() || tf_nombre1.getText().isEmpty() || tf_apellido1.getText().isEmpty() || tf_direccion.getText().isEmpty() || tf_numtelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Familiar Paciente");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                //bt_guardar.setDisable(true);
                tf_idfamiliar.requestFocus();

            }else{
                int res = facade.agregarFamiliar(familiar);

                    if (res == 1) {

                        familiares.add(familiar);//Cada que se agrege un objeto a se actualiza la observable(el modelo de la tableView
                         Alert msg = new Alert(Alert.AlertType.INFORMATION);
                         msg.setTitle("Gestiones - Familiar Paciente");
                         msg.setContentText("El familiar ha sido agregado correctamente");
                         msg.setHeaderText("Resultado");
                         msg.show();
                        cancelar();

                    } else {

                        Alert msg = new Alert(Alert.AlertType.ERROR);
                        msg.setTitle("Gestiones - Familiar Paciente");
                        msg.setContentText("El familiar. NO ha sido agregado correctamente");
                        msg.setHeaderText("REsult");
                        msg.show();
                        cancelar();
                     }


                 }

        } else{
            if (tf_idfamiliar.getText().isEmpty() || tf_nombre1.getText().isEmpty() || tf_apellido1.getText().isEmpty() || tf_direccion.getText().isEmpty() || tf_numtelefono.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Familiar Paciente");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_idfamiliar.requestFocus();


            }else{
                int res = facade.modificarFamiliar(familiar);
                if (res == 1) {
                    familiares.set(tblFamiliares.getSelectionModel().getSelectedIndex(), familiar);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Familiar Paciente");
                    msg.setContentText("El familiar se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }else{
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Familiar Paciente");
                    msg.setContentText("El familiar No ha sido modificada");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }

            }
        }
    }

    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


            if(e.getCode().equals(KeyCode.ENTER))
                consultar();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }





    @FXML
    public void modificar(){
        tf_idfamiliar.setDisable(true);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        tf_direccion.setDisable(false);
        tf_numtelefono.setDisable(false);
        tf_nombre1.requestFocus();
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        valor =0;
        bt_guardar.setDisable(false);
    }

    @FXML
    public void eliminar() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Familiar Paciente");
        msg.setContentText("¿Está seguro de eliminar el familiar?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta =facade.eliminarFamiliar(tblFamiliares.getSelectionModel().getSelectedItem().getIdFamiliar());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Familiar Paciente");
                msge.setContentText("Error al eliminar! \n" + "El familiar tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                familiares.remove(tblFamiliares.getSelectionModel().getSelectedIndex());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Familiar Paciente");
                msg2.setContentText("El familiar se ha eliminado");
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
    private void consultar() {
        if (tf_idfamiliar.getText().isEmpty()) {
            tf_idfamiliar.setDisable(false);
            tf_nombre1.setDisable(true);
            tf_nombre2.setDisable(true);
            tf_apellido1.setDisable(true);
            tf_apellido2.setDisable(true);
            tf_numtelefono.setDisable(true);
            tf_direccion.setDisable(true);
            tf_idfamiliar.requestFocus();
            bt_crear.setDisable(true);
            bt_guardar.setDisable(true);
            tblFamiliares.setEditable(false);
            valor=0;
        } else {
            int i = 0;
            familiares = FXCollections.observableArrayList(facade.buscar(tf_idfamiliar.getText()));
            if (familiares.isEmpty()) {

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Familiar Paciente");
                msg.setContentText("Familiar: " + tf_idfamiliar.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_idfamiliar.requestFocus();
                tf_idfamiliar.setText("");
            }else{
                if (familiares.get(i).getEstado().equals("1")) {
                    tblFamiliares.setItems(familiares);
                    colId.setCellValueFactory(new PropertyValueFactory<>("idFamiliar"));
                    colNombre1.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
                    colNombre2.setCellValueFactory(new PropertyValueFactory<>("segundoNombre"));
                    colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
                    colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
                    colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                    colTelefono.setCellValueFactory(new PropertyValueFactory<>("telFamiliar"));
                    bt_consultar.setDisable(true);

                    tf_idfamiliar.setText(familiares.get(i).getIdFamiliar());
                    tf_nombre1.setText(familiares.get(i).getPrimerNombre());
                    tf_nombre2.setText(familiares.get(i).getSegundoNombre());
                    tf_apellido1.setText(familiares.get(i).getPrimerApellido());
                    tf_apellido2.setText(familiares.get(i).getSegundoApellido());
                    tf_numtelefono.setText(familiares.get(i).getTelFamiliar());
                    tf_direccion.setText(familiares.get(i).getDireccion());
                    bt_inhabilitar.setDisable(false);
                    bt_modificar.setDisable(false);
                }
                if (familiares.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Familiar Paciente");
                    msg.setContentText("EPS: " + tf_idfamiliar.getText() + " no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_idfamiliar.requestFocus();
                    tf_idfamiliar.setText("");

                }


            }

        }
    }



    @FXML
    public void limpiarFormulario() {//Este metodo deja vacios los campos del formulario
        tf_idfamiliar.setText("");
        tf_nombre1.setText("");
        tf_nombre2.setText("");
        tf_apellido1.setText("");
        tf_apellido2.setText("");
        tf_direccion.setText("");
        tf_numtelefono.setText("");
        bt_crear.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
    }

    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        habilitarCampos();
        valor=1;

    }

    @FXML
    private void deshabilitarBotones() {

        bt_guardar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_consultar.setDisable(false);
        bt_crear.setDisable(false);
    }

    @FXML
    private void habilitarCampos() {
        tf_idfamiliar.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        tf_direccion.setDisable(false);
        tf_numtelefono.setDisable(false);
        tf_idfamiliar.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        tf_idfamiliar.setDisable(true);
        tf_nombre1.setDisable(true);
        tf_nombre2.setDisable(true);
        tf_apellido1.setDisable(true);
        tf_apellido2.setDisable(true);
        tf_direccion.setDisable(true);
        tf_numtelefono.setDisable(true);
    }

    @FXML
    public void cancelar() {
        tf_idfamiliar.setText("");
        tf_nombre1.setText("");
        tf_nombre2.setText("");
        tf_apellido1.setText("");
        tf_apellido2.setText("");
        tf_direccion.setText("");
        tf_numtelefono.setText("");
        tf_idfamiliar.requestFocus();
        deshabilitarBotones();
        deshabilitarCampos();
        familiares = FXCollections.observableArrayList(facade.obtenerTodosFamiliares());
        tblFamiliares.setItems(familiares);
        colId.setCellValueFactory(new PropertyValueFactory<>("idFamiliar"));
        colNombre1.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<>("segundoNombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telFamiliar"));

    }

    @FXML
    private void cerraDatosFamiliar(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }



}
