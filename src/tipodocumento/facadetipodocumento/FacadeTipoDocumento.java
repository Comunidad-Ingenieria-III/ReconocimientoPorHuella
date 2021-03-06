package tipodocumento.facadetipodocumento;

import tipoTituloAcademico.dto.TtAcademico;
import tipodocumento.daotipodocumento.DaoTipoDocumento;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

import java.util.List;

public class FacadeTipoDocumento {


    private DaoTipoDocumento daotipodocumento = new DaoTipoDocumento();

    public List<DtoTipoDocumento> cargarTipoDocumento() {
        return daotipodocumento.cargarTipoDocumento();
    }

    public int insertarTipoDocumento(DtoTipoDocumento dtotipodocumento) {
        return daotipodocumento.agregarTipoDocumento(dtotipodocumento);
    }

    public void buscarPersona(String idTipoDocumento) {
        daotipodocumento.buscarPorId(idTipoDocumento);
    }

    public int modificarTipoDocumeto(DtoTipoDocumento dtoTipoDocumento) {
        return daotipodocumento.modificar(dtoTipoDocumento);
    }

    public boolean eliminarTipoDocumento(String tipoDocumento) {
        return daotipodocumento.eliminar(tipoDocumento);
    }

    public DtoTipoDocumento obtenerPorId(String idTipoDocumento){
        return daotipodocumento.buscarPorId(idTipoDocumento);
    }

    public List<DtoTipoDocumento> buscar(String buscar) {
        return daotipodocumento.buscar(buscar); }

}
