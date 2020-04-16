package tipoTituloAcademico.facade;

import tipoTituloAcademico.dao.TtAcademicoDao;
import tipoTituloAcademico.dto.TtAcademico;

import java.util.List;

public class FacadeTtAcademico {
    TtAcademicoDao ttAcademicoDao = new TtAcademicoDao();
    public List<TtAcademico> obtenerTodosTitulosAcdemicos() {
        return ttAcademicoDao.obterTodas();
    }
    public int agregar(TtAcademico ttAcademico) {
        return ttAcademicoDao.agregar(ttAcademico);
    }
    public int modificar(TtAcademico ttAcademico) {
        return ttAcademicoDao.modificar(ttAcademico);
    }
    public int eliminar(String idTipoTituloAcdemico) {
        return ttAcademicoDao.eliminar(idTipoTituloAcdemico);
    }

    public List<TtAcademico> buscar(String buscar) {
        return ttAcademicoDao.buscar(buscar); }

    public TtAcademico obtenerPorId(String idTipoTitu) {
        return ttAcademicoDao.buscarPorId(idTipoTitu);
    }
}
