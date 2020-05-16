package login.facadeusuario;

import javafx.beans.property.BooleanProperty;
import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;

import java.util.List;

public class FacadeUsuario {

    private DaoUsuario daousuario = new DaoUsuario();

    public Usuario validarUsuario(String user, String contrasena){
        return daousuario.validarIngreso(user,contrasena);
    }

    public List<Usuario> obtenerUsuarios() {
        return daousuario.obtenerTodosLosUsuarios();
    }

    public int agregarUsuario(Usuario usuario) {
        return daousuario.agregarUsuario(usuario);
    }

    public int modificarUsuario(Usuario usuario){
        return daousuario.modificarUsuario(usuario);
    }

    public boolean eliminarUsuario(String idUsuario ){
        return daousuario.eliminar(idUsuario);
    }

    public Usuario obtenerUsuarioPorId(String idUsuario){
        return daousuario.buscarPorIdUsuario(idUsuario);
    }


}
