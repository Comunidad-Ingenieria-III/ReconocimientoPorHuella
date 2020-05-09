package tipodocumento.formulariotipodocumento;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import java.awt.*;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorTipoDocumento extends  Component implements Initializable,  KeyListener {

    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();

    @FXML
    private TableView<DtoTipoDocumento> tb_tipoDocumento;

    @FXML
    private TableColumn<DtoTipoDocumento, String> colId;
    @FXML
    private TableColumn<DtoTipoDocumento, String> colNombre;

    @FXML
    public TextField tf_Tipo;

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
    private String estado = "1";
    private List<DtoTipoDocumento> tiposdeDocumento;
    @FXML
    int valor = 0;

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
        deshabilitarCampos();
        manejarEventos();


    }


    @FXML
    public void manejarEventos() {
        tb_tipoDocumento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DtoTipoDocumento>() {
            @Override
            public void changed(ObservableValue<? extends DtoTipoDocumento> observable, DtoTipoDocumento oldValue, DtoTipoDocumento newValue) {
                if (newValue != null) {
                    tf_Tipo.setText(newValue.getIdTipoDocumento());
                    tf_nombre1.setText(newValue.getNombreTipoDocumento());
                    tf_Tipo.requestFocus();

                    bt_crear.setDisable(true);
                    bt_guardar.setDisable(true);
                    bt_modificar.setDisable(false);
                    bt_inhabilitar.setDisable(false);
                    bt_consultar.setDisable(true);
                }
            }
        });//FIN DEL LISTENER
    }

    public void validarExistente() {

        tf_nombre1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }


    public void validarE() {
        if (valor == 1) {


            tiposdeDocumento = facadeTipoDocumento.buscar(tf_Tipo.getText());

            if (tiposdeDocumento.size() >= 1) {
                int i = 0;

                if (tiposdeDocumento.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de titulo académico");
                    msg.setContentText("El Tipo de documento: " + tf_Tipo.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();

                }
                if (tiposdeDocumento.get(0).getEstado().equals("1")) {


                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de titulo académico");
                    msg.setContentText("Código: " + tf_Tipo.getText() + " existente no es posible agregar");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.setText("");
                    tf_nombre1.setText("");
                    tf_Tipo.requestFocus();


                }


            }

        }


    }


    @FXML
    public void botonGuardar() {
        tiposdeDocumento = facadeTipoDocumento.buscar(tf_Tipo.getText());
        DtoTipoDocumento dtoTipoDocumento = new DtoTipoDocumento(
                tf_Tipo.getText(),
                tf_nombre1.getText(), estado
        );
        if (tiposdeDocumento.isEmpty()) {
            if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Tipo de documento");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();

                tf_Tipo.requestFocus();
            } else {
                int res = facadeTipoDocumento.insertarTipoDocumento(dtoTipoDocumento);
                if (res == 1) {
                    tipoDocumentos.add(dtoTipoDocumento);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de Documento");
                    msg.setContentText("El tipo de documento se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();

                } else {

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de Documento");
                    msg.setContentText("No se ha podido agregar el tipo de documento");
                    msg.setHeaderText("Resultado");
                    msg.show();
                }

            }
        } else {
            if (tf_Tipo.getText().isEmpty() || tf_nombre1.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Tipo de documento");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_nombre1.requestFocus();

            } else {
                int res = facadeTipoDocumento.modificarTipoDocumeto(dtoTipoDocumento);
                if (res == 1) {
                    tipoDocumentos.set(tb_tipoDocumento.getSelectionModel().getSelectedIndex(), dtoTipoDocumento);

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de Documento");
                    msg.setContentText("Se ha modificado el tipo de documento");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Tipo de Documento");
                    msg.setContentText("No se ha podido modificar el tipo de documento");
                    msg.setHeaderText("Resultado");
                    msg.show();
                }
            }
        }
    }

    @FXML
    public void modificar() {
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(false);
        tf_nombre1.requestFocus();
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        valor = 0;
        bt_guardar.setDisable(false);
    }


    @FXML
    public void eliminarTipoDocumento() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Tipo de Documento");
        msg.setContentText("¿Está seguro de eliminar el tipo de documento?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta = facadeTipoDocumento.eliminarTipoDocumento(String.valueOf(tb_tipoDocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento()));
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - Tipo de Documento");
                msge.setContentText("Error al eliminar! \n" + "El tipo de documento tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                tipoDocumentos.remove(tb_tipoDocumento.getSelectionModel().getSelectedIndex());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Tipo de Documento");
                msg2.setContentText("El Tipo de documento se ha eliminado");
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
    public void buttonPressed(KeyEvent e)
    {
        if(e.getCode().toString().equals("ENTER"))
        {
            //do something
        }
    }



    @FXML
    public void textAction(KeyEvent e){
        if (valor ==0){


        if(e.getCode().equals(KeyCode.ENTER))
            consultarTDocuemnto();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }







    @FXML
    private void consultarTDocuemnto() {

        if (tf_Tipo.getText().isEmpty()) {
            tf_Tipo.setDisable(false);
            tf_nombre1.setDisable(true);
            tf_Tipo.requestFocus();
            bt_crear.setDisable(true);
            bt_guardar.setDisable(true);
            tb_tipoDocumento.setEditable(false);
            valor=0;
        } else {
            int i = 0;
            tipoDocumentos = FXCollections.observableArrayList(facadeTipoDocumento.buscar(tf_Tipo.getText()));
            if (tipoDocumentos.isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Tipo de Documento");
                msg.setContentText("Tipo de documento " + tf_Tipo.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Tipo.requestFocus();
                tf_Tipo.setText("");
            }else{


                if (tipoDocumentos.get(i).getEstado().equals("1")) {
                    tb_tipoDocumento.setItems(tipoDocumentos);
                    colId.setCellValueFactory(new PropertyValueFactory<>("idTipoDocumento"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTipoDocumento"));
                    tf_Tipo.setText(tipoDocumentos.get(i).getIdTipoDocumento());
                    tf_nombre1.setText(tipoDocumentos.get(i).getNombreTipoDocumento());
                    bt_inhabilitar.setDisable(false);
                    bt_modificar.setDisable(false);
                }
                if (tipoDocumentos.get(i).getEstado().equals("0")) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Tipo de Documento");
                    msg.setContentText("Tipo de documento " + tf_Tipo.getText() + " no encontrado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Tipo.requestFocus();
                    tf_Tipo.setText("");

                }


            }


        }

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
    public void limpiarFormulario() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
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
    private void habilitarCampos() {
        tf_Tipo.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_Tipo.requestFocus();

    }

    @FXML
    private void deshabilitarCampos() {
        tf_Tipo.setDisable(true);
        tf_nombre1.setDisable(true);

    }

    @FXML
    public void cancelar() {
        tf_Tipo.setText("");
        tf_nombre1.setText("");
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_consultar.setDisable(false);
        bt_crear.setDisable(false);
        tf_nombre1.setDisable(true);
        tf_Tipo.setDisable(true);
        bt_guardar.setDisable(true);

        tipoDocumentos = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());
        tb_tipoDocumento.setItems(tipoDocumentos);
        colId.setCellValueFactory(new PropertyValueFactory<>("idTipoDocumento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTipoDocumento"));

    }


    @FXML
    private void cerrarTipoDocumento(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {

    }


    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

    }
}
