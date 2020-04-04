package institucionreferencia.facade;

import institucionAcademica.dao.InstitucionAcademicaDAO;
import institucionAcademica.dto.InstitucionAcademica;
import institucionreferencia.dao.InstitucionReferenciaDAO;
import institucionreferencia.dto.InstitucionReferencia;

import java.util.List;

public class FacadeInstitucionReferencia {

    InstitucionReferenciaDAO institucionReferenciaDAO = new InstitucionReferenciaDAO();

    public List<InstitucionReferencia>ListarTodas(){
        return institucionReferenciaDAO.obtenerTodas();
    }

     public int agregarInstitucionReferencia(InstitucionReferencia institucionReferencia){
        return institucionReferenciaDAO.agregar(institucionReferencia);
     }

     public int modificarInstitucionReferencia(InstitucionReferencia institucionReferencia){
        return institucionReferenciaDAO.modificar(institucionReferencia);
     }

     public int eliminarInstitucionReferencia(String idInstitucionR){
        return institucionReferenciaDAO.eliminar(idInstitucionR);
     }



}
