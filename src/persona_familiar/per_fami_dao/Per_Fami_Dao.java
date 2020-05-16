package persona_familiar.per_fami_dao;

import conexionBD.ConexionRoot;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import personal_salud_titulo.psdto.PsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class    Per_Fami_Dao {


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private Per_Fami_Dto per_fami_dto;
    private List<Per_Fami_Dto> listaPer_fami;

    public List<Per_Fami_Dto> cargartodas() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from persona_familiar";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            listaPer_fami = new ArrayList<>();

            while (rset.next()) {

                per_fami_dto = new Per_Fami_Dto();

                per_fami_dto.setIdPersona(rset.getString("idpersona"));
                per_fami_dto.setIdFamiliar(rset.getString("idFamiliar"));
                per_fami_dto.setFechaIngreso(rset.getDate("fechaIngreso"));
                per_fami_dto.setEstado(rset.getBoolean("estado"));

                listaPer_fami.add(per_fami_dto);

            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return listaPer_fami;
    }

    public int agregar(Per_Fami_Dto per_fami_dto) {
        try {

            conn = ConexionRoot.getConexion();
            String sql = "insert into persona_familiar(idpersona, idFamiliar, fechaIngreso, estado) values(?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, per_fami_dto.getIdPersona());
            stmt.setString(2, per_fami_dto.getIdFamiliar());
            stmt.setDate(3, new java.sql.Date(per_fami_dto.getFechaIngreso().getTime()));
            stmt.setBoolean(4, per_fami_dto.isEstado());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()


    public int modificar(Per_Fami_Dto per_fami_dto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update persona_familiar set idPersonal = ?, idFamiliar = ?, fechaIngreso = ?, estado  where idpersona = ?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, per_fami_dto.getIdPersona());
            stmt.setString(2, per_fami_dto.getIdFamiliar());
            stmt.setDate(3, per_fami_dto.getFechaIngreso());
            stmt.setBoolean(4, per_fami_dto.isEstado());

            stmt.setString(5, per_fami_dto.getIdPersona());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()

    public Per_Fami_Dto buscarPorId(String idPersona) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from persona_familiar where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                per_fami_dto = new Per_Fami_Dto();

                per_fami_dto.setIdPersona(rset.getString("idpersona"));
                per_fami_dto.setIdFamiliar(rset.getString("idFamiliar"));
                per_fami_dto.setFechaIngreso(rset.getDate("fechaIngreso"));
                per_fami_dto.setEstado(rset.getBoolean("estado"));


            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return per_fami_dto;
    }

    public boolean buscarPrimaryKey(String idpersona) {//Funcion para realizar la busqueda de un personal de salud por medio de su perimary key
        boolean trpta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from persona_familiar where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idpersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                trpta = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trpta;
    }


    public boolean eliminar(String idps, boolean estado) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update persona_familiar set estado = ? where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, estado);
            stmt.setString(2, idps);
            stmt.executeUpdate();

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    } // Fin del método eliminar()*/


}
