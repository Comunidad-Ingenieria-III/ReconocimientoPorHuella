package datospersona.facade;

import datospersona.dao.PersonaDao;
import datospersona.dto.Persona;
import javafx.collections.ObservableList;

public class FacadePersona {

    private PersonaDao personaDao = new PersonaDao();

    public ObservableList<Persona> cargarPersona() {
        return personaDao.cargarPersona();
    }

    public void insertarPersona(Persona persona) {
        personaDao.agregarPersona(persona);

    }

    public void modificarPersona(Persona persona) {
        personaDao.modificar(persona);
    }

    public void buscarPersona(int idenPersona) {
        personaDao.buscarPorId(idenPersona);
    }

    public void buscarPorNombre(String nombrepersona){
        personaDao.buscarPorNombre(nombrepersona);
    }

    public void eliminarPersona(int idCliente) {

        personaDao.eliminar(idCliente);
    }

}
