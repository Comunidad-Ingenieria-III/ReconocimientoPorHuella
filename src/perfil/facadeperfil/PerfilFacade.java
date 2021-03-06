package perfil.facadeperfil;

import perfil.dao.PerfilDAO;
import perfil.dtoperfil.PerfilDto;
import tipoTituloAcademico.dto.TtAcademico;

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

    public boolean eliminar(String idperfil){
        return perfilDAO.eliminar(idperfil);
    }

    public PerfilDto obtenerPorId(String idperfil) {
        return perfilDAO.buscarPorId(idperfil);
    }

    public List<PerfilDto> buscar(String buscar) {return  perfilDAO.buscar(buscar);}

}
