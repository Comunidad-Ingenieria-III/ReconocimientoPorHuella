package tipodocumento.facadetipodocumento;

import tipodocumento.daotipodocumento.DaoTipoDocumento;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import java.util.List;

public class FacadeTipoDocumento {



    private DaoTipoDocumento daotipodocumento = new DaoTipoDocumento();

    public List<DtoTipoDocumento> cargarTipoDocumento(){
        return daotipodocumento.cargarTipoDocumento();
    }

    public void insertarTipoDocumento(DtoTipoDocumento dtotipodocumento){

        daotipodocumento.agregarTipoDocumento(dtotipodocumento);

    }

    public void buscarPersona(String idTipoDocumento) {
        daotipodocumento.buscarPorId(idTipoDocumento);
    }

}
