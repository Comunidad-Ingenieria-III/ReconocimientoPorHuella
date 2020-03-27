package consultas.personalatiendeemergencia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ContraladorConsultaPersonalEmergencia implements Initializable {

    @FXML
    private Button bt_cerrarConsultaPersonalAPH;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void cerraConsultaPersonalEmergencia(ActionEvent event) {
        Stage stage = (Stage) bt_cerrarConsultaPersonalAPH.getScene().getWindow();
        stage.close();
    }
}
