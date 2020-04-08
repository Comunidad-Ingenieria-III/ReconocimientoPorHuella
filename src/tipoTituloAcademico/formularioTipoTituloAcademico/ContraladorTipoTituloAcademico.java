package tipoTituloAcademico.formularioTipoTituloAcademico;

import cargo.dto.Cargo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tipoTituloAcademico.dto.TtAcademico;
import tipoTituloAcademico.facade.FacadeTtAcademico;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

import java.net.URL;
import java.util.ResourceBundle;

public class ContraladorTipoTituloAcademico implements Initializable {

    FacadeTtAcademico facadeTtAcademico = new FacadeTtAcademico();

    @FXML
    private TableView<TtAcademico> tb_tituloAcademico;

    @FXML
    private TableColumn<TtAcademico, String> colId;
    @FXML
    private TableColumn<TtAcademico, String> colDescripcion;

    @FXML
    private TextField tf_Tipo;
    @FXML
    private TextField tf_nombre1;
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

    private ObservableList<TtAcademico> titulos;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titulos = FXCollections.observableArrayList(facadeTtAcademico.obtenerTodosTitulosAcdemicos());
        tb_tituloAcademico.setItems(titulos);

        colId.setCellValueFactory(new PropertyValueFactory<>("idTipoTitu"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        //deshabilitarBotones();
        //deshabilitarCampos();
    }

    @FXML
    public void guardarCargo() {


        TtAcademico ttAcademico = new TtAcademico(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );

        int res = facadeTtAcademico.agregar(ttAcademico);

        if (res == 1) {
            titulos.add(ttAcademico);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Cargos");
            msg.setContentText("El carego se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Cargos");
            msg.setContentText("No se ha podido agregar el cargo");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void limpiar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
    }

    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(true);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        //habilitarCampos();
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
        tf_Tipo.setDisable(false);
        tf_Tipo.requestFocus();
        tf_nombre1.setDisable(false);
    }

    @FXML
    private void deshabilitarCampos() {
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(true);
    }


    @FXML
    private void cerrarTipoTituloAcademico(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
