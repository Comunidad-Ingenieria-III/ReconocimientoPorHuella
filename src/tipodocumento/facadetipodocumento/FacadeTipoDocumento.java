package tipodocumento.facadetipodocumento;

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

    public int eliminarTipoDocumento(String tipoDocumento) {
        return daotipodocumento.eliminar(tipoDocumento);
    }

}
