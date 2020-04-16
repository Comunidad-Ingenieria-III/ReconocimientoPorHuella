package institucionAcademica.dao;

import institucionAcademica.dto.InstitucionAcademica;
import tipoTituloAcademico.dto.TtAcademico;

import java.util.List;

public class FacadeInstitucionAcademica {
InstitucionAcademicaDAO institucionAcademicaDAO = new InstitucionAcademicaDAO();


    public List<InstitucionAcademica> obtenerTodasInstituciones() {
        return institucionAcademicaDAO.obtenerTodas();
    }
    public int agregar(InstitucionAcademica institucionAcademica) {
        return institucionAcademicaDAO.agregar(institucionAcademica);
    }
    public int modificar(InstitucionAcademica institucionAcademica) {
        return institucionAcademicaDAO.modificar(institucionAcademica);
    }
    public int eliminar(String idInstitucion) {
        return institucionAcademicaDAO.eliminar(idInstitucion);
    }


    public InstitucionAcademica obtenerPorId(String idInstitucion) {
        return institucionAcademicaDAO.buscarPorId(idInstitucion);
    }

    public List<InstitucionAcademica> buscar(String buscar) {
        return institucionAcademicaDAO.buscar(buscar); }


}
