package institucionreferencia.dao;

import conexionBD.ConexionRoot;
import institucionAcademica.dto.InstitucionAcademica;
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
            String sql = "select * from institucion_referencia where estado= 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            institucionReferencias = new ArrayList();

            while (rset.next()){
                institucionReferencia = new InstitucionReferencia();

                institucionReferencia.setIdInstitucion(rset.getString("idInstiRefe"));
                institucionReferencia.setNombre(rset.getString("nombre"));
                institucionReferencia.setDireccion(rset.getString("direccion"));
                institucionReferencia.setTelefono(rset.getString("telefono"));
                institucionReferencia.setEstado(rset.getString("estado"));
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
            String sql = "insert into institucion_referencia (idInstiRefe, nombre, direccion, telefono, estado)values(?, ?, ? ,?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, institucionReferencia.getIdInstitucion());
            stmt.setString(2, institucionReferencia.getNombre());
            stmt.setString(3, institucionReferencia.getDireccion());
            stmt.setString(4, institucionReferencia.getTelefono());
            stmt.setInt(5, Integer.parseInt(institucionReferencia.getEstado()));
            return stmt.executeUpdate();

        } catch (SQLException |RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int modificar(InstitucionReferencia institucionReferencia) {
        try {
        conn = ConexionRoot.getConexion();
        String sql = "update institucion_referencia set nombre = ?, direccion = ?, telefono = ? where idInstiRefe = ?";
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


    public int eliminarr(String idInstitucionR) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update institucion_referencia set estado = 0 where idInstiRefe = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idInstitucionR);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean eliminar(String idInstitucionR) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idPersonaRecibe, p.idInstiRefe, ps.idInstitucion as relacion from documento_referencia AS p " +
                        "INNER JOIN institucion_referencia AS ps ON p.idInstiRefe=ps.idInstiRefe where ps.idInstiRefe = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idInstitucionR);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update institucion_referencia set estado = 0 where idInstiRefe = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idInstitucionR);
                    stmt.executeUpdate();
                    yes = false;


                }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }



    public List<InstitucionReferencia> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_referencia where idInstiRefe LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            institucionReferencias = new ArrayList();

            while (rset.next()){
                institucionReferencia = new InstitucionReferencia();

                institucionReferencia.setIdInstitucion(rset.getString("idInstiRefe"));
                institucionReferencia.setNombre(rset.getString("nombre"));
                institucionReferencia.setDireccion(rset.getString("direccion"));
                institucionReferencia.setTelefono(rset.getString("telefono"));
                institucionReferencia.setEstado(rset.getString("estado"));
                institucionReferencias.add(institucionReferencia);
            }


        }catch (SQLException | RuntimeException e){
            System.out.println(e.toString());
        }
        return institucionReferencias;
    }






}
