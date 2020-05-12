package registroAtencionPaciente.facaderegistro;

import datospersona.dto.Persona;
import registroAtencionPaciente.daoregistro.RegistroDao;
import registroAtencionPaciente.dtoregistro.RegistroDto;

public class RegistroFacade {

    private RegistroDao registroDao = new RegistroDao();


    public int insertarRegistro(RegistroDto registroDto) {
        return registroDao.agregarRegistroAtencion(registroDto);
    }
}
