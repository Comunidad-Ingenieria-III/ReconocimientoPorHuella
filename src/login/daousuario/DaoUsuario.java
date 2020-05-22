package login.daousuario;

import conexionBD.ConexionRoot;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import login.dtousuario.Usuario;
import perfil.dtoperfil.PerfilDto;
import perfil.facadeperfil.PerfilFacade;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
    PerfilFacade perfilFacade = new PerfilFacade();
    private Usuario user = new Usuario();
    private List<Usuario> usuarios;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;


    public Usuario validarIngreso(String usuario, String contrasena) {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from usuario where username = ? and contrasena = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            rset = stmt.executeQuery();

            if (rset.next()) {
                user.setIdUsuario(rset.getString("idUsuario"));
                user.setPrimerNombre(rset.getString("primerNombre"));
                user.setSegundoNombre(rset.getString("segundoNombre"));
                user.setPrimerApellido(rset.getString("primerApellido"));
                user.setSegundoApellido(rset.getString("segundoApellido"));
                user.setUsername(rset.getString("username"));
                user.setContrasena(rset.getString("contrasena"));
                user.setIdperfil(rset.getString("idperfil"));
                user.setEstado(rset.getBoolean("estado"));

                user.setPerfil(perfilFacade.obtenerPorId(user.getIdperfil()));
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return user;
    }


    public List<Usuario> obtenerTodosLosUsuarios() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from usuario where estado = 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            usuarios = new ArrayList<>();

            while (rset.next()) {
                user = new Usuario();

                user.setIdUsuario(rset.getString("idUsuario"));
                user.setPrimerNombre(rset.getString("primerNombre"));
                user.setSegundoNombre(rset.getString("segundoNombre"));
                user.setPrimerApellido(rset.getString("primerApellido"));
                user.setSegundoApellido(rset.getString("segundoApellido"));
                user.setUsername(rset.getString("username"));
                user.setContrasena(rset.getString("contrasena"));
                user.setIdperfil(rset.getString("idperfil"));
                user.setEstado(rset.getBoolean("estado"));

                usuarios.add(user);
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return usuarios;
    }//Fin del metodo obtenerTodos


    public int agregarUsuario(Usuario usuario) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into usuario(idUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido," +
                    " username, contrasena, idperfil, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros

            stmt.setString(1, usuario.getIdUsuario());
            stmt.setString(2, usuario.getPrimerNombre());
            stmt.setString(3, usuario.getSegundoNombre());
            stmt.setString(4, usuario.getPrimerApellido());
            stmt.setString(5, usuario.getSegundoApellido());
            stmt.setString(6, usuario.getUsername());
            stmt.setString(7, usuario.getContrasena());
            stmt.setString(8, usuario.getIdperfil());
            stmt.setBoolean(9, usuario.isEstado());

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 1;
    }

    public int modificarUsuario(Usuario user) {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "update usuario set primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?, username = ?," +
                    "contrasena = ?, idperfil = ?, estado = ? where idUsuario = ? ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getPrimerNombre());
            stmt.setString(2, user.getSegundoNombre());
            stmt.setString(3, user.getPrimerApellido());
            stmt.setString(4, user.getSegundoApellido());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getContrasena());
            stmt.setString(7, user.getIdperfil());
            stmt.setBoolean(8, user.isEstado());

            stmt.setString(9, user.getIdUsuario());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 0;
    }

    public boolean eliminar(String idUsuario) {//Funcion que inhabilita un registro en la tabla usuario de la BBDD

        boolean yes = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update usuario set estado = ? where idusuario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setString(2, idUsuario);
            stmt.executeUpdate();
            yes = true;

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();

        }
        return yes;
    }

    public Usuario buscarPorIdUsuario(String idUsuario) {
        user = null;
        try {
            conn = ConexionRoot.getConexion();
            String query = "select * from usuario where idUsuario=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, idUsuario);
            rset = stmt.executeQuery();

            if (rset.next()) {
                user = new Usuario();
                user.setIdUsuario(rset.getString("idUsuario"));
                user.setPrimerNombre(rset.getString("primerNombre"));
                user.setSegundoNombre(rset.getString("segundoNombre"));
                user.setPrimerApellido(rset.getString("primerApellido"));
                user.setSegundoApellido(rset.getString("segundoApellido"));
                user.setUsername(rset.getString("username"));
                user.setContrasena(rset.getString("contrasena"));
                user.setIdperfil(rset.getString("idperfil"));
                user.setEstado(rset.getBoolean("estado"));
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return user;
    }
}
