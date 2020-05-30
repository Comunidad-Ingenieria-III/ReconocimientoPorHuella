package login.formulariousuario;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Parent formulario_login = null;
        try {
            formulario_login = FXMLLoader.load(getClass().getResource("FormularioLogin.fxml"));
            primaryStage.setTitle("AP_Humana(Logeate)");
            primaryStage.getIcons().add(new Image("estrella_vida.jpg"));
            primaryStage.setScene(new Scene(formulario_login));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error: ");
            alert.setContentText(e.getLocalizedMessage());
            alert.show();
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

}
