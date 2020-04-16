package institucionAcademica.dao;

import conexionBD.ConexionRoot;
import institucionAcademica.dto.InstitucionAcademica;
import personal_salud_titulo.psdto.PsDto;
import tipoTituloAcademico.dto.TtAcademico;

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
            String sql = "select * from institucion_academica";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            institucionAcademicaList = new ArrayList<>();

            while (rset.next()){
                institucionAcademica = new InstitucionAcademica();
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setTelefono(rset.getString("telefono"));

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
            String sql= "insert into institucion_academica(idInstitucion, nombre, direccion, telefono) values(?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, institucionAcademica.getIdInstitucion());
            stmt.setString(2, institucionAcademica.getNombre());
            stmt.setString(3, institucionAcademica.getDireccion());
            stmt.setString(4, institucionAcademica.getTelefono());

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


    public int eliminar(String idInstitucion) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from institucion_academica where idInstitucion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idInstitucion);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()


<<<<<<< HEAD
    public InstitucionAcademica buscarPorId(String idInstitucion) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from institucion_academica where idInstitucion = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idInstitucion);
            rset = stmt.executeQuery();

            if (rset.next()) {
                institucionAcademica = new InstitucionAcademica();

=======
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
>>>>>>> origin/master
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setTelefono(rset.getString("telefono"));

<<<<<<< HEAD
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return institucionAcademica;
    }
=======
                institucionAcademicaList.add(institucionAcademica);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - Bucar Institución()!");
        }
        return institucionAcademicaList;
    }


>>>>>>> origin/master
}
