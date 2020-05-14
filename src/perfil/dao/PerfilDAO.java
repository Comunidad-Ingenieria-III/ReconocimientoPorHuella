package perfil.dao;


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
            String sql = "select * from perfil";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            perfiles = new ArrayList<>();

            while (rset.next()) {
                perfilDto = new PerfilDto();

                perfilDto.setIdperfil(rset.getString("idperfil"));
                perfilDto.setNombre(rset.getString("nombre"));
                perfilDto.setEstado(rset.getBoolean("estado"));
                perfiles.add(perfilDto);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos");
        }
        return perfiles;
    }//Fin del metodo obtenerTodos

    public List<PerfilDto> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from perfil where idperfil LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            perfiles = new ArrayList<>();
            while (rset.next()){
                perfilDto = new PerfilDto();
                perfilDto.setIdperfil(rset.getString("idperfil"));
                perfilDto.setNombre(rset.getString("nombre"));
                perfilDto.setEstado(rset.getBoolean("estado"));
                perfiles.add(perfilDto);
            }
        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarPerfil()!");
        }
        return perfiles;
    }

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
            String sql = "update perfil set nombre = ?, estado = ?  where idperfil = ?";
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

    public int eliminarr(String idperfil) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update perfil set estado = 0 where idperfil = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idperfil);

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }
        return 0;
    }//Fin del metodo eliminar

    public boolean eliminar(String idperfil) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idUsuario ,p.idperfil ,ps.idperfil as relacion from usuario AS p " +
                        "INNER JOIN perfil AS ps ON p.idperfil=ps.idperfil where ps.idperfil = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idperfil);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update perfil set estado = 0 where idperfil = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idperfil);
                    stmt.executeUpdate();
                    yes = false;
                }
            }
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }

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
                perfilDto.setEstado(rset.getBoolean("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfilDto;
    }
}
