package conexionBD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcHelper {
    //para read
    public ResultSet realizarConsulta(String query){

        Connection conn = ConexionRoot.getConexion();
        ResultSet rs = null;
        Statement stQuery;
        try{
            stQuery = conn.createStatement();
            rs = stQuery.executeQuery(query);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al ejecutar "+query+": "+ex);
        }
        return rs;
    }

    //para insert, update y delete
    public boolean ejecutarQuery(String query){

        Connection conn = ConexionRoot.getConexion();
        boolean exito = false;
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            if(affectedRows!=0)
                exito = true;
            else
                exito = false;
            System.out.println("Affected rows: "+affectedRows);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al ejecutar "+query+": "+ex);
            exito = false;
        }
        return exito;
    }
}
