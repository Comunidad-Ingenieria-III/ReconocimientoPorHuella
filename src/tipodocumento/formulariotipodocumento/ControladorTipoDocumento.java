package tipodocumento.formulariotipodocumento;

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
import tipodocumento.daotipodocumento.DaoTipoDocumento;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorTipoDocumento implements Initializable {

    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();

    @FXML
    private TableView<DtoTipoDocumento> tb_tipoDocumento;

    @FXML
    private TableColumn<DtoTipoDocumento, String> colId;
    @FXML
    private TableColumn<DtoTipoDocumento, String> colNombre;

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

    private ObservableList<DtoTipoDocumento> tipoDocumentos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tipoDocumentos = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());


        tb_tipoDocumento.setItems(tipoDocumentos);

        colId.setCellValueFactory(new PropertyValueFactory<>("idTipoDocumento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTipoDocumento"));


        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_guardar.setDisable(true);

        manejarEventos();

    }

    @FXML
    public void manejarEventos() {
        tb_tipoDocumento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DtoTipoDocumento>() {
            @Override
            public void changed(ObservableValue<? extends DtoTipoDocumento> observable, DtoTipoDocumento oldValue, DtoTipoDocumento newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(String.valueOf(newValue.getIdTipoDocumento()));
                    tf_nombre1.setText(newValue.getNombreTipoDocumento());

                    bt_crear.setDisable(false);
                    bt_guardar.setDisable(true);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                }
            }
        });//FIN DEL LISTENER
    }

    @FXML
    public void guardarInstitucion() {

        DtoTipoDocumento tipoDocumento = new DtoTipoDocumento(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );

        int res = facadeTipoDocumento.insertarTipoDocumento(tipoDocumento);

        if (res == 1) {
            tipoDocumentos.add(tipoDocumento);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("El Tipo de Documento se ha agregado");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("No se ha podido agregar El Tipo de Documento");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void modificarInstitucion() {
        DtoTipoDocumento tipoDocumento = new DtoTipoDocumento(
                tf_Tipo.getText(),
                tf_nombre1.getText()
        );
        int res = facadeTipoDocumento.modificarTipoDocumeto(tipoDocumento);
        if (res == 1) {
            tipoDocumentos.set(tb_tipoDocumento.getSelectionModel().getSelectedIndex(), tipoDocumento);

            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("No se ha podido Modificar El Tipo de Documento");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("No se ha podido Modificar El Tipo de Documento");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void eliminarInstitucion() {

        int res = facadeTipoDocumento.eliminarTipoDocumento(tb_tipoDocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento());
        if (res == 1) {
            tipoDocumentos.remove(tb_tipoDocumento.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("El Tipo de Documento se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("l Tipo de Documento No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }
        limpiarFormulario();
    }

    @FXML
    public void validarCamposVacios() {
        if (tf_Tipo.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Tipo de Documento");
            msg.setContentText("Debe ingresar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    public void validarB(){
        bt_guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                validarCamposVacios();
            }
        });
    }

    @FXML
    public void limpiarFormulario() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");


        bt_crear.setDisable(false);
        bt_guardar.setDisable(false);
        bt_cancelar.setDisable(true);
        bt_inhabilitar.setDisable(true);
    }

    @FXML
    public void cancelar(){
        tf_Tipo.setText("");
        tf_nombre1.setText("");

    }


    @FXML
    private void habilitarCampos() {
        tf_Tipo.setDisable(false);
        tf_nombre1.setDisable(false);

    }

    @FXML
    private void deshabilitarCampos() {
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(true);

    }

    @FXML
    private void cerrarTipoDocumento(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
