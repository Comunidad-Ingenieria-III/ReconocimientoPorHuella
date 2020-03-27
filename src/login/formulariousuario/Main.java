package login.formulariousuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent formulario_login = FXMLLoader.load(getClass().getResource("FormularioLogin.fxml"));
        primaryStage.setTitle("AP_Humana(Logeate)");
        primaryStage.getIcons().add(new Image("estrella_vida.jpg"));
        primaryStage.setScene(new Scene(formulario_login, 400, 350));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
