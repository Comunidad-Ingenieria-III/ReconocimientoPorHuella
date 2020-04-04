package datosFamiliar.formulariofamiliar;

import datosFamiliar.dtofamiliar.Familiar;
import datosFamiliar.facadefamiliar.Facade;
import institucionreferencia.dto.InstitucionReferencia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.nio.file.Watchable;
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


    private ObservableList<Familiar> familiares;





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



        deshabilitarBotones();
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
                    tf_apellido2.setText(newValue.getSegundoNombre());
                    tf_direccion.setText(newValue.getDireccion());
                    tf_numtelefono.setText(newValue.getTelFamiliar());

                    bt_crear.setDisable(false);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                    bt_guardar.setDisable(true);

                }
            }

            

        });//FIN DEL LISTENER
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
    }

    @FXML
    private void habilitarBotones() {
        limpiarFormulario();
        bt_guardar.setDisable(false);
        bt_consultar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        habilitarCampos();

    }

    @FXML
    private void deshabilitarBotones() {
        bt_crear.setDisable(false); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
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
    private void cerraDatosFamiliar(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void guardarFamiliar() {//Este metodo toma los valores de los componenetes del formulario, crea un objeto y lo envia a la BD
        Familiar familiar= new Familiar(
                Integer.parseInt(tf_idfamiliar.getText()),
                tf_nombre1.getText(),
                tf_nombre2.getText(),
                tf_apellido1.getText(),
                tf_apellido2.getText(),
                tf_direccion.getText(),
                tf_numtelefono.getText()

        );

        int res = facade.agregarFamiliar(familiar);

        if (res == 1) {
            familiares.add(familiar);//Cada que se agrege un objeto a se actualiza la observable(el modelo de la tableView
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Pacientes - familiares");
            msg.setContentText("El familiar ha sido agregado correctamente");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Pacientes - Familiares");
            msg.setContentText("El familiar NO ha sido agregado correctamente");
            msg.setHeaderText("REsult");
            msg.show();
        }
        limpiarFormulario();

    }


    @FXML
    public void modificarFamiliar() {
        Familiar familiar = new Familiar(
                Integer.parseInt(tf_idfamiliar.getText()),
                tf_nombre1.getText(),
                tf_nombre2.getText(),
                tf_apellido1.getText(),
                tf_apellido2.getText(),
                tf_direccion.getText(),
                tf_numtelefono.getText()

        );
        int res = facade.modificarFamiliar(familiar);
        if (res == 1) {
            familiares.set(tblFamiliares.getSelectionModel().getSelectedIndex(), familiar);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }

    }

    @FXML
    public void eliminarFamiliar() {
        int res = facade.eliminarFamiliar(tblFamiliares.getSelectionModel().getSelectedItem().getIdFamiliar());
        if (res == 1) {
            familiares.remove(tblFamiliares.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        }else{
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar  No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }


    }

}
