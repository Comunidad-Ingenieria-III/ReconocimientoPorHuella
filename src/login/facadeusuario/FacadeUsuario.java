package login.facadeusuario;

import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;

import java.util.List;

public class FacadeUsuario {

    private DaoUsuario daousuario = new DaoUsuario();

    public List<Usuario> obtenerUsuarios() {
        return daousuario.obtenerTodosLosUsuarios();
    }

    public int crearUsuario(Usuario usuario) {

        return daousuario.crearUsuario(usuario);
    }

    public int modificarUsuario(Usuario usuario){
        return daousuario.modificarUsuario(usuario);
    }

    public boolean eliminarUsurio(String idUsuario){
        return daousuario.eliminar(idUsuario);
    }

    public Usuario obtenerUsuarioPorId(String idUsuario){
        return daousuario.buscarPorIdUsuario(idUsuario);
    }

    public List<Usuario> buscarUsurio(String buscarusuario){
        return daousuario.buscarUsuario(buscarusuario);
    }

}
