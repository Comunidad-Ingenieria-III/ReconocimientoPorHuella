package eps.facadeeps;

import eps.dao.DaoEps;
import eps.dto.DtoEps;
import javafx.collections.ObservableList;

import java.util.List;

public class FacadeEps {

    private  DaoEps daoeps = new DaoEps();

    public List<DtoEps> CargarEps(){
        return daoeps.cargarTipoEps();
    }

    public void InsertarEps(DtoEps dtoEps){
        daoeps.agregarEps(dtoEps);
    }

    public void buscarEps(int idEps){
        daoeps.buscarPorId(idEps);
    }
}
