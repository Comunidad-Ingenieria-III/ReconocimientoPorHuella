package cargo.facade;

import cargo.dao.CargoDAO;
import cargo.dto.Cargo;

import java.util.List;

public class FacadeCargo {

    CargoDAO cargoDAO =  new CargoDAO();

    public List<Cargo> obtenerCargos(){
        return cargoDAO.obtenerTodos();
    }

    public int agregar(Cargo cargo){
        return cargoDAO.agregar(cargo);
    }

    public int modificar(Cargo cargo){
        return cargoDAO.modificar(cargo);
    }

    public int eliminar(String idCargo){
        return cargoDAO.eliminar(idCargo);
    }
}