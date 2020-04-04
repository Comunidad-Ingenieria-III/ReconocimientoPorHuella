package institucionreferencia.dao;

import conexionBD.ConexionRoot;
import institucionreferencia.dto.InstitucionReferencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstitucionReferenciaDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private InstitucionReferencia institucionReferencia;
    private List<InstitucionReferencia> institucionReferencias;

    public List<InstitucionReferencia> obtenerTodas(){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_referencia";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            institucionReferencias = new ArrayList();

            while (rset.next()){
                institucionReferencia = new InstitucionReferencia();

                institucionReferencia.setIdInstitucion(rset.getString("idInstiRefe"));
                institucionReferencia.setNombre(rset.getString("nombre"));
                institucionReferencia.setDireccion(rset.getString("direccion"));
                institucionReferencia.setTelefono(rset.getString("telefono"));

                institucionReferencias.add(institucionReferencia);
            }


        }catch (SQLException | RuntimeException e){
            System.out.println(e.toString());
        }
        return institucionReferencias;
    }

    public int agregar(InstitucionReferencia institucionReferencia) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into institucion_referencia (idInstiRefe, nombre, direccion, telefono)values(?, ?, ? ,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, institucionReferencia.getIdInstitucion());
            stmt.setString(2, institucionReferencia.getNombre());
            stmt.setString(3, institucionReferencia.getDireccion());
            stmt.setString(4, institucionReferencia.getTelefono());

            return stmt.executeUpdate();

        } catch (SQLException |RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int modificar(InstitucionReferencia institucionReferencia) {
        try {
        conn = ConexionRoot.getConexion();
        String sql = "update institucion_referencia set nombre = ?, set direccion = ?, set telefono = ? where idInstiRefe = ?";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, institucionReferencia.getNombre());
        stmt.setString(2, institucionReferencia.getDireccion());
        stmt.setString(3, institucionReferencia.getTelefono());

        stmt.setString(4, institucionReferencia.getIdInstitucion());

        return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int eliminar(String idInstitucionR) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from institucion_referencia where idInstiRefe = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idInstitucionR);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
