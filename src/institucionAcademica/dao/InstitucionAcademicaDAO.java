package institucionAcademica.dao;

import conexionBD.ConexionRoot;
import institucionAcademica.dto.InstitucionAcademica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstitucionAcademicaDAO {


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private InstitucionAcademica institucionAcademica;
    private List<InstitucionAcademica> institucionAcademicaList;

    public List<InstitucionAcademica> obtenerTodas(){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_academica where estado = 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            institucionAcademicaList = new ArrayList<>();

            while (rset.next()){
                institucionAcademica = new InstitucionAcademica();
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setTelefono(rset.getString("telefono"));
                institucionAcademica.setEstado(rset.getString("estado"));
                institucionAcademicaList.add(institucionAcademica);
            }


        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - obtenerTodas()!");
        }
        return institucionAcademicaList;

    }


    public int agregar(InstitucionAcademica institucionAcademica) {
        try {
            conn = ConexionRoot.getConexion();
            String sql= "insert into institucion_academica(idInstitucion, nombre, direccion, telefono, estado) values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, institucionAcademica.getIdInstitucion());
            stmt.setString(2, institucionAcademica.getNombre());
            stmt.setString(3, institucionAcademica.getDireccion());
            stmt.setString(4, institucionAcademica.getTelefono());
            stmt.setInt(5, Integer.parseInt(institucionAcademica.getEstado()));
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()


    public int modificar(InstitucionAcademica institucionAcademica) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update institucion_academica set nombre = ?, direccion = ?, telefono = ?  where idInstitucion = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, institucionAcademica.getNombre());
            stmt.setString(2, institucionAcademica.getDireccion());
            stmt.setString(3, institucionAcademica.getTelefono());
            stmt.setString(4, institucionAcademica.getIdInstitucion());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    public int eliminarr(String idInstitucion) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update institucion_academica set estado= 0 where idInstitucion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idInstitucion);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()


    public boolean eliminar(String idInstitucion) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idPs, p.idPersonal, ps.idInstitucion as relacion from personal_salud_titulo AS p " +
                        "INNER JOIN institucion_academica AS ps ON p.idInstitucion=ps.idInstitucion where ps.idInstitucion = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idInstitucion);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update institucion_academica set estado= 0 where idInstitucion = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idInstitucion);
                    stmt.executeUpdate();
                    yes = false;


                }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }




    public InstitucionAcademica buscarPorId(String idInstitucion) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_academica where idInstitucion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idInstitucion);
            rset = stmt.executeQuery();

            if (rset.next()) {
                institucionAcademica = new InstitucionAcademica();
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setTelefono(rset.getString("telefono"));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return institucionAcademica;
    }



    public List<InstitucionAcademica> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_academica where idInstitucion LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            institucionAcademicaList = new ArrayList<>();
            while (rset.next()){
                institucionAcademica = new InstitucionAcademica();
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setTelefono(rset.getString("telefono"));
                institucionAcademica.setEstado(rset.getString("estado"));
                institucionAcademicaList.add(institucionAcademica);
            }

        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - Bucar Institución()!");
        }
        return institucionAcademicaList;
    }

}
