package tipoTituloAcademico.dao;

import conexionBD.ConexionRoot;
import tipoTituloAcademico.dto.TtAcademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TtAcademicoDao {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private TtAcademico ttAcademico;
    private List<TtAcademico> ttAcademicoList;


    public List<TtAcademico> obterTodas(){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_titulo_academico";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            ttAcademicoList = new ArrayList<>();
            while (rset.next()){
                ttAcademico = new TtAcademico();
                ttAcademico.setIdTipoTituloAcademico(rset.getString("idTipoTitu"));
                ttAcademico.setNombre(rset.getString("nombre"));

                ttAcademicoList.add(ttAcademico);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - obtenerTodas()!");
        }
        return ttAcademicoList;
    } // Fin de obterner todas



    public List<TtAcademico> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_titulo_academico where idTipoTitu LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            ttAcademicoList = new ArrayList<>();
            while (rset.next()){
                ttAcademico = new TtAcademico();
                ttAcademico.setIdTipoTituloAcademico(rset.getString("idTipoTitu"));
                ttAcademico.setNombre(rset.getString("nombre"));

                ttAcademicoList.add(ttAcademico);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarTitulo()!");
        }
        return ttAcademicoList;
    }


    public int agregar(TtAcademico ttAcademico) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into tipo_titulo_academico(idTipoTitu,nombre) values(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ttAcademico.getIdTipoTituloAcademico());
            stmt.setString(2, ttAcademico.getNombre());
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()

    public int modificar(TtAcademico ttAcademico) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_titulo_academico set nombre = ?  where idTipoTitu = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ttAcademico.getNombre());
            stmt.setString(2, ttAcademico.getIdTipoTituloAcademico());
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()

    public int eliminar(String idTipoTituloAcademico) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from tipo_titulo_academico where idTipoTitu = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoTituloAcademico);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()





}