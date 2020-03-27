package datosFamiliar.facadefamiliar;

import datosFamiliar.daofamiliar.FamiliarDAO;
import datosFamiliar.dtofamiliar.Familiar;
import javafx.collections.ObservableList;

import java.util.List;

public class Facade {


    private FamiliarDAO familiarDAO = new FamiliarDAO();


    public List<Familiar> obtenerTodosFamiliares() {
        return familiarDAO.lsitarTodos();
    }

    public int agregarFamiliar(Familiar familiar) {
        return familiarDAO.agregar(familiar);

    }
}
