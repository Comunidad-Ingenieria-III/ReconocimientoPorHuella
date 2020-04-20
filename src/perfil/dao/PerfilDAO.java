package perfil.dao;

import cargo.dto.Cargo;
import conexionBD.ConexionRoot;
import perfil.dtoperfil.PerfilDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private PerfilDto perfilDto;
    private List<PerfilDto> perfiles;

    public List<PerfilDto> obtenerTodos() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from perfil where estado = 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            perfiles = new ArrayList<>();

            while (rset.next()) {
                perfilDto = new PerfilDto();

                perfilDto.setIdperfil(rset.getString("idperfil"));
                perfilDto.setNombre(rset.getString("nombre"));
                perfiles.add(perfilDto);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos");
        }
        return perfiles;
    }//Fin del metodo obtenerTodos


    public int agregar(PerfilDto perfilDto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into perfil (idperfil, nombre, estado) values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, perfilDto.getIdperfil());
            stmt.setString(2, perfilDto.getNombre());
            stmt.setBoolean(3, perfilDto.isEstado());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }

        return 0;
    }//Fin del metodo agregar


    public int modificar(PerfilDto perfilDto) {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "update perfil set nombre = ?, estado = ?  where idCargo = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, perfilDto.getNombre());
            stmt.setBoolean(2, perfilDto.isEstado());
            stmt.setString(3, perfilDto.getIdperfil());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int eliminar(String idperfil) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from perfil where idperfil = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idperfil);

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }
        return 0;
    }//Fin del metodo eliminar

    public PerfilDto buscarPorId(String idperfil) {
        PerfilDto perfilDto = null;
        try {
            conn = ConexionRoot.getConexion();
            String query = "select * from perfil where idperfil=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,idperfil);
            rset = stmt.executeQuery();

            if (rset.next()){
                perfilDto = new PerfilDto();
                perfilDto.setIdperfil(rset.getString("idperfil"));
                perfilDto.setNombre(rset.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfilDto;
    }
}
