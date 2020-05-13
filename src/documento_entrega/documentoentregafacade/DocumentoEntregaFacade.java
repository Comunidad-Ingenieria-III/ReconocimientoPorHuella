package documento_entrega.documentoentregafacade;

import documento_entrega.documentoentregadao.DocumentoEntregaDao;
import documento_entrega.documentoentregadto.DocumentoEntregaDto;

import java.util.List;

public class DocumentoEntregaFacade {


    private DocumentoEntregaDao documentoEntregaDao = new DocumentoEntregaDao();


    public List<DocumentoEntregaDto> obtenerTodoPersonal() {
        return documentoEntregaDao.listarTodos();
    }

    public int agregarPersonalReferencia(DocumentoEntregaDto documentoEntregaDto) {
        return documentoEntregaDao.agregar(documentoEntregaDto);

    }
    /*public List<Familiar> buscar(String buscar) {
        return familiarDAO.buscar(buscar);
    }

    public Familiar buscarPorId(String idFamiliar){
        return familiarDAO.buscarPorId(idFamiliar);
    }

    public int modificarFamiliar(Familiar familiar){
        return familiarDAO.modificar(familiar);
    }

    public boolean eliminarFamiliar(String idFamiliar){
        return familiarDAO.eliminar(idFamiliar);
    }*/
}
