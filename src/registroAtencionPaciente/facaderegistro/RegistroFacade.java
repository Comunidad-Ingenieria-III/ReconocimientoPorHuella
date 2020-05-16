package registroAtencionPaciente.facaderegistro;

import datospersona.dto.Persona;
import registroAtencionPaciente.daoregistro.RegistroDao;
import registroAtencionPaciente.dtoregistro.RegistroDto;

import java.util.List;

public class RegistroFacade {

    private RegistroDao registroDao = new RegistroDao();

    public List<RegistroDto> CargarTodosRegistros() {
        return registroDao.cargarRegistroAtencion();
    }

    public int insertarRegistro(RegistroDto registroDto) {
        return registroDao.agregarRegistroAtencion(registroDto);
    }

    public RegistroDto buscarCodigoRemision(String codigoRemision){
        return registroDao.buscarCodigoRemision(codigoRemision);
    }

    public boolean buscarPrimaryKeyCodigoRemision(String codigoRemision){
        return registroDao.buscarPrimaryKeyRegistro(codigoRemision);
    }
}

