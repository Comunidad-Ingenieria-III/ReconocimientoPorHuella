package perfil.facadeperfil;

import perfil.dao.PerfilDAO;
import perfil.dtoperfil.PerfilDto;

import java.util.List;

public class PerfilFacade {

    PerfilDAO perfilDAO =  new PerfilDAO();

    public List<PerfilDto> obtenerCargos(){
        return perfilDAO.obtenerTodos();
    }

    public int agregar(PerfilDto perfilDto){
        return perfilDAO.agregar(perfilDto);
    }

    public int modificar(PerfilDto perfilDto){
        return perfilDAO.modificar(perfilDto);
    }

    public int eliminar(String idperfil){
        return perfilDAO.eliminar(idperfil);
    }

    public PerfilDto obtenerPorId(String idperfil) {
        return perfilDAO.buscarPorId(idperfil);
    }
}
