package eps.formularioeps;
import eps.dto.DtoEps;
import eps.facadeeps.FacadeEps;
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
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EPS  extends Component implements Initializable {

    FacadeEps facadeEps = new FacadeEps();
    @FXML
    private TableView<DtoEps> tb_eps;
    @FXML
    private TableColumn<DtoEps, String> colId;
    @FXML
    private TableColumn<DtoEps, String> colNombre;
    @FXML
    private TableColumn<DtoEps, String> colDireccion;
    @FXML
    private TableColumn<DtoEps, String> colTelefono;
    @FXML
    private TextField tf_Codigo,tf_Nombre,tf_Direccion,tf_Telefono;
    @FXML
    private Button bt_Crear,bt_Consultar,bt_Cancelar,bt_Salir,bt_Guardar,bt_Modificar,bt_Inhabilitar;
    @FXML
    private String estado="1";
    private ObservableList<DtoEps> epss;
    @FXML
    private List<DtoEps> listaEPS;
    @FXML
    int valor=0;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        epss = FXCollections.observableArrayList(facadeEps.CargarEps());
        tb_eps.setItems(epss);
        colId.setCellValueFactory(new PropertyValueFactory<>("idEps"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEps"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEps"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telEps"));

        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
        bt_Guardar.setDisable(true);
        deshabilitarCampos();
        manejarEventos();
    }



    @FXML
    public void mostrarPersonal(){

        DtoEps dtoEps = new DtoEps();


        tf_Nombre.setText(dtoEps.getNombreEps());
        System.out.println(tf_Nombre);
        tf_Telefono.setText(dtoEps.getTelEps());
        tf_Direccion.setText(dtoEps.getdireccionEps());
    }

    public void manejarEventos() {
        tb_eps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DtoEps>() {
            @Override
            public void changed(ObservableValue<? extends DtoEps> observable, DtoEps oldValue, DtoEps newValue) {
                if (newValue != null) {
                    tf_Codigo.setText(newValue.getIdEps());
                    tf_Nombre.setText(newValue.getNombreEps());
                    tf_Direccion.setText(newValue.getdireccionEps());
                    tf_Telefono.setText(newValue.getTelEps());

                    bt_Crear.setDisable(true);
                    bt_Guardar.setDisable(true);
                    bt_Modificar.setDisable(false);
                    bt_Inhabilitar.setDisable(false);
                    bt_Consultar.setDisable(true);
                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    public void botonGuardar() {

        listaEPS=facadeEps.buscar(tf_Codigo.getText());
        DtoEps dtoEps = new DtoEps(tf_Codigo.getText(), tf_Nombre.getText(), tf_Direccion.getText(), tf_Telefono.getText(), estado);

        if (listaEPS.isEmpty()) {
            if (tf_Codigo.getText().isEmpty() || tf_Nombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - EPS");
                msg.setContentText("Campos requeridos");
                msg.setHeaderText("Resultado");
                msg.show();
                //bt_guardar.setDisable(true);
                tf_Codigo.requestFocus();

            }else{
                int res = facadeEps.insertarEps(dtoEps);
                if (res == 1) {
                    epss.add(dtoEps);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - EPS");
                    msg.setContentText("La EPS se ha agregado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();

                } else {

                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - EPS ");
                    msg.setContentText("No se ha podido agregar la EPS");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                }


            }

        } else{
            if (tf_Codigo.getText().isEmpty() || tf_Nombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - EPS");
                msg.setContentText("Nombre es un campo requerido");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Codigo.requestFocus();



        }else{
                int res = facadeEps.modificarEps(dtoEps);
                if (res == 1) {
                   // epss.set(tb_eps.getSelectionModel().getSelectedIndex(), dtoEps);
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - EPS");
                    msg.setContentText("La EPS se ha modificado");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    cancelar();
                } else {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - EPS");
                    msg.setContentText("La EPS no ha sido modificada");
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
                consultarEps();
        }
    }

    @FXML
    public void textESC(KeyEvent e){

        if(e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }




    @FXML
    public void modificar(){
        tf_Codigo.setDisable(true);
        tf_Nombre.setDisable(false);
        tf_Direccion.setDisable(false);
        tf_Telefono.setDisable(false);
        tf_Nombre.requestFocus();
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
        valor =0;
        bt_Guardar.setDisable(false);
    }

    @FXML
    public void eliminarEps() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - EPS");
        msg.setContentText("¿Está seguro de eliminar la EPS?");
        msg.setHeaderText("Resultado");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta =facadeEps.eliminarEps(tf_Codigo.getText());
            if (respuesta) {

                Alert msge = new Alert(Alert.AlertType.ERROR);
                msge.setTitle("Gestiones - EPS");
                msge.setContentText("Error al eliminar! \n" + "La EPS tiene registros dependientes!\n"
                        + "Asegurese de eliminar los registros que dependen de este.");
                msge.setHeaderText("Error.");
                msge.show();
                limpiarFormulario();
                cancelar();

            } else {
                epss.remove(tf_Codigo.getText());
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - EPS");
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





    public void validar(){
        tf_Nombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarCamposVacios();
            }
        });
    }



    public void validarExistente(){

        tf_Nombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE(){
        if(valor==1){

            listaEPS=facadeEps.buscar(tf_Codigo.getText());
            if(listaEPS.size()>=1){
                int i=0;

                if (listaEPS.get(0).getEstado().equals("0")) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - EPS");
                    msg.setContentText("La EPS: " + tf_Codigo.getText() + " se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Codigo.setText("");
                    tf_Nombre.setText("");
                    tf_Codigo.requestFocus();

                }
                if(listaEPS.get(0).getEstado().equals("1")){


                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - EPS");
                    msg.setContentText("Código: " + tf_Codigo.getText() +" existente no es posible agregar" );
                    msg.setHeaderText("Resultado");
                    msg.show();
                    tf_Codigo.setText("");
                    tf_Nombre.setText("");
                    tf_Codigo.requestFocus();


                }




            }

        }


    }

    @FXML
    private void consultarEps() {
        if (tf_Codigo.getText().isEmpty()) {
            tf_Codigo.setDisable(false);
            tf_Nombre.setDisable(true);
            tf_Telefono.setDisable(true);
            tf_Direccion.setDisable(true);
            tf_Codigo.requestFocus();
            bt_Crear.setDisable(true);
            bt_Guardar.setDisable(true);
            tb_eps.setEditable(false);
            valor=0;
        } else {
            int i = 0;
            epss = FXCollections.observableArrayList(facadeEps.buscar(tf_Codigo.getText()));
            if (epss.isEmpty()) {

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - eps");
                msg.setContentText("EPS: " + tf_Codigo.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Codigo.requestFocus();
                tf_Codigo.setText("");
            }else{
            if (epss.get(i).getEstado().equals("1")) {
                tb_eps.setItems(epss);
                colId.setCellValueFactory(new PropertyValueFactory<>("idEps"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEps"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEps"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<>("telEps"));
                bt_Consultar.setDisable(true);

                tf_Codigo.setText(epss.get(i).getIdEps());
                tf_Nombre.setText(epss.get(i).getNombreEps());
                tf_Telefono.setText(epss.get(i).getTelEps());
                tf_Direccion.setText(epss.get(i).getdireccionEps());
                bt_Inhabilitar.setDisable(false);
                bt_Modificar.setDisable(false);
                tf_Codigo.setDisable(true);

            }
            if (epss.get(i).getEstado().equals("0")) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - EPS");
                msg.setContentText("EPS: " + tf_Codigo.getText() + " no encontrado");
                msg.setHeaderText("Resultado");
                msg.show();
                tf_Codigo.requestFocus();
                tf_Codigo.setText("");

            }


            }

        }
    }



    public void validarCamposVacios() {
        if (tf_Codigo.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - EPS");
            msg.setContentText("Debe ingresar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();

        }
    }

    @FXML
    private void habilitarBotones() {
        bt_Crear.setDisable(true); //siempre ira deshabilitado
        bt_Consultar.setDisable(false);
        bt_Cancelar.setDisable(false);
        bt_Salir.setDisable(false);
        bt_Guardar.setDisable(false);
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
        habilitarCampos();
        valor=1;
    }
    @FXML
    private void habilitarCampos() {
        tf_Codigo.setDisable(false);
        tf_Nombre.setDisable(false);
        tf_Direccion.setDisable(false);
        tf_Telefono.setDisable(false);
        tf_Codigo.requestFocus();
    }


    @FXML
    public void limpiarFormulario() {
        tf_Codigo.setText("");
        tf_Nombre.setText("");
        tf_Direccion.setText("");
        tf_Telefono.setText("");
        tf_Codigo.requestFocus();

        bt_Crear.setDisable(true);
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
    }

    @FXML
    private void deshabilitarCampos() {
        tf_Codigo.setDisable(true);
        tf_Nombre.setDisable(true);
        tf_Direccion.setDisable(true);
        tf_Telefono.setDisable(true);

    }

    @FXML
    public void cancelar() {
        tf_Codigo.setText("");
        tf_Nombre.setText("");
        tf_Direccion.setText("");
        tf_Telefono.setText("");
        tf_Codigo.requestFocus();
        bt_Modificar.setDisable(true);
        bt_Inhabilitar.setDisable(true);
        bt_Consultar.setDisable(false);
        bt_Crear.setDisable(false);
        tf_Nombre.setDisable(true);
        tf_Codigo.setDisable(true);
        tf_Direccion.setDisable(true);
        tf_Telefono.setDisable(true);
        bt_Guardar.setDisable(true);

        epss = FXCollections.observableArrayList(facadeEps.CargarEps());
        tb_eps.setItems(epss);
        colId.setCellValueFactory(new PropertyValueFactory<>("idEps"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEps"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionEps"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telEps"));

    }

    @FXML
    private void cerarEps(ActionEvent event) {
        Stage stage = (Stage) bt_Salir.getScene().getWindow();
        stage.close();
    }

}
