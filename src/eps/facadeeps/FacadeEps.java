package eps.facadeeps;

import eps.dao.DaoEps;
import eps.dto.DtoEps;
import javafx.collections.ObservableList;

import java.util.List;

public class FacadeEps {

    private  DaoEps daoeps = new DaoEps();

    public List<DtoEps> CargarEps(){
        return daoeps.cargarEps();
    }

    public int insertarEps(DtoEps dtoEps){
        return daoeps.agregarEps(dtoEps);
    }

    public int modificarEps(DtoEps dtoEps){
        return daoeps.modificarEps(dtoEps);
    }

    public int eliminarEps(String dtoEps){
        return daoeps.eliminarEps(dtoEps);
    }

    public void buscarEps(String idEps){
        daoeps.buscarPorId(idEps);
    }
}
