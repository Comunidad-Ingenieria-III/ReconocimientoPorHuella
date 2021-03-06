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
            String sql = "select * from tipo_titulo_academico where estado= 1 ";
            stmt = conn.prepareStatement(sql);

            rset = stmt.executeQuery();

            ttAcademicoList = new ArrayList<>();
            while (rset.next()){
                ttAcademico = new TtAcademico();
                ttAcademico.setIdTipoTituloAcademico(rset.getString("idTipoTitu"));
                ttAcademico.setNombre(rset.getString("nombre"));
                ttAcademico.setEstado(rset.getString("estado"));
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
                ttAcademico.setEstado(rset.getString("estado"));
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
            String sql = "insert into tipo_titulo_academico(idTipoTitu,nombre,estado) values(?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ttAcademico.getIdTipoTituloAcademico());
            stmt.setString(2, ttAcademico.getNombre());
            stmt.setInt(3, Integer.parseInt(ttAcademico.getEstado()));
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()

    public int modificar(TtAcademico ttAcademico) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_titulo_academico set nombre = ?  where idTipoTitu = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ttAcademico.getNombre());
            stmt.setString(2, ttAcademico.getIdTipoTituloAcademico());
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()

    public int eliminarr(String idTipoTituloAcademico) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_titulo_academico set estado =0 where idTipoTitu = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoTituloAcademico);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()

    public boolean eliminar(String idTipoTituloAcademico) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idPs, p.idPersonal, ps.idTipoTitu as relacion from personal_salud_titulo AS p " +
                        "INNER JOIN tipo_titulo_academico AS ps ON p.idTipoTitu=ps.idTipoTitu where ps.idTipoTitu = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idTipoTituloAcademico);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update tipo_titulo_academico set estado =0 where idTipoTitu = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idTipoTituloAcademico);
                    stmt.executeUpdate();
                    yes = false;


                }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }






    public TtAcademico buscarPorId(String idTipoTitu) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_titulo_academico where idTipoTitu = ? and estado = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoTitu);
            rset = stmt.executeQuery();

            if (rset.next()) {
                ttAcademico = new TtAcademico();
                ttAcademico.setIdTipoTituloAcademico(rset.getString("idTipoTitu"));
                ttAcademico.setNombre(rset.getString("nombre"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return ttAcademico;
    }
}