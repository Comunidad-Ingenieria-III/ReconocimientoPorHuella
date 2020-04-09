package personal_salud_titulo.psfacade;

import institucionAcademica.dao.InstitucionAcademicaDAO;
import institucionAcademica.dto.InstitucionAcademica;
import personal_salud_titulo.psdao.PsDao;
import personal_salud_titulo.psdto.PsDto;

import java.util.List;

public class PsFacade {
    PsDao psDao = new PsDao();


    public List<PsDto> obtenerTodas() {
        return psDao.cargartodas();
    }

    public int agregar(PsDto psDto) {
        return psDao.agregar(psDto);
    }

    public int modificar(PsDto psDto) {
        return psDao.modificar(psDto);
    }

    public int eliminar(String idInstitucion) {
        return psDao.eliminar(idInstitucion);
    }
}
