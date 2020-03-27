package Informes.novedadtitulos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorNovedadTitulos implements Initializable {
    @FXML
    private Button bt_salir;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void cerrarInformeNovedadTitulos(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
