package conexionBD;

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
                cnxR = DriverManager.getConnection("jdbc:mysql://localhost/datos_por_huella", "root", "");
            }
            return cnxR;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al crear la conexion!", e);
        }
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