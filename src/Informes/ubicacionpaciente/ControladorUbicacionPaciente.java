package Informes.ubicacionpaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorUbicacionPaciente implements Initializable {

    @FXML
    private Button bt_salir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void cerrarInformeUbicacionPaciente(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
