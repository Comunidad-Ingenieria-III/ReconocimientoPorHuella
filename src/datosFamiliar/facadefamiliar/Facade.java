package datosFamiliar.facadefamiliar;

import datosFamiliar.daofamiliar.FamiliarDAO;
import datosFamiliar.dtofamiliar.Familiar;
import javafx.collections.ObservableList;

import java.util.List;

public class Facade {


    private FamiliarDAO familiarDAO = new FamiliarDAO();


    public List<Familiar> obtenerTodosFamiliares() {
        return familiarDAO.listarTodos();
    }

    public int agregarFamiliar(Familiar familiar) {
        return familiarDAO.agregar(familiar);

    }

    public int modificarFamiliar(Familiar familiar){
        return familiarDAO.modificar(familiar);
    }

    public int eliminarFamiliar(int idFamiliar){
        return familiarDAO.eliminar(idFamiliar);
    }
}
