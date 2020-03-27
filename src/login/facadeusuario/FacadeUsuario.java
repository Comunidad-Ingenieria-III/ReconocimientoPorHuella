package login.facadeusuario;

import login.daousuario.DaoUsuario;
import login.dtousuario.Usuario;

public class FacadeUsuario {

    private DaoUsuario daousuario = new DaoUsuario();

    public void crearUsuario(Usuario usuario) {

        daousuario.crearUsuario(usuario);
    }

}
