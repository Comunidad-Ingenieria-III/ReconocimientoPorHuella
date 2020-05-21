package conexionBD;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionRoot {

    private static Connection cnxR = null;

    public static Connection getConexion() {
        try {
            if (cnxR == null) {
                Runtime.getRuntime().addShutdownHook(new ShutdownHook());
                Class.forName("com.mysql.jdbc.Driver");
                //cnxR = DriverManager.getConnection("jdbc:mysql://db4free.net/datos_por_huella", "negisaro", "nelson2020");
                cnxR = DriverManager.getConnection("jdbc:mysql://localhost/datos_por_huella", "root", "");
            }
            return cnxR;
        } catch (ClassNotFoundException | SQLException ex) {
                //throw new RuntimeException("Error al crear la conexion!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ocurrio el Error:");
                alert.setContentText(ex.getLocalizedMessage());
        }
        return null;
    }

    static class ShutdownHook extends Thread {

        @Override
        public void run() {
            try {
                Connection con = ConexionRoot.getConexion();
                System.out.println("Conexión cerrada!");
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}