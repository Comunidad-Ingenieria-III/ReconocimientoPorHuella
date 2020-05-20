package Informes.InformesPacientes.daoPa;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoP {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public InputStream getReport(){
        InputStream input = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud";
            //String sql = "select "+colum_name+ " from reporte_name";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();


            while (rset.next()) {
                input = rset.getBinaryStream(1);

            }

        } catch (RuntimeException | SQLException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Obtener Los Registros De Personal Salud");
            msg.setHeaderText("Error.");
            msg.show();
            e.printStackTrace();

        }
        return input;
    }




}
