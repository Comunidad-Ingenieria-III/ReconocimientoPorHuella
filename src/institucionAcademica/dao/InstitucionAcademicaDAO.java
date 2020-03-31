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
            String sql = "select * from institucion_academica";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            institucionAcademicaList = new ArrayList<>();

            while (rset.next()){
                institucionAcademica = new InstitucionAcademica();
                institucionAcademica.setIdInstitucion(rset.getString("idInstitucion"));
                institucionAcademica.setNombre(rset.getString("nombre"));
                institucionAcademica.setDireccion(rset.getString("direccion"));
                institucionAcademica.setDireccion(rset.getString("telefono"));

                institucionAcademicaList.add(institucionAcademica);
            }


        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - obtenerTodas()!");
        }
        return institucionAcademicaList;

    }




}
