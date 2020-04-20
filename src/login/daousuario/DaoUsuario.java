package login.daousuario;

import conexionBD.ConexionRoot;
import javafx.collections.ObservableList;
import login.dtousuario.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {

    private Usuario user = new Usuario();
    private ObservableList<Usuario> usuarios;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;


    public void crearUsuario(Usuario usuario) throws RuntimeException {
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

            int rta = stmt.executeUpdate();
            if (rta != 1) {
                throw new RuntimeException("Error al agregar!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro Guardado Con Exito", "INFORMACIÓN", 1);
            }

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException("Error SQL - Agregar()!");
        }
    }
}
