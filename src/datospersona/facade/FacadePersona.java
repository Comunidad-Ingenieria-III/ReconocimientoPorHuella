package datospersona.facade;

import datospersona.dao.PersonaDao;
import datospersona.dto.BusquedaDeFamiliar;
import datospersona.dto.Persona;
import javafx.collections.ObservableList;

import java.util.List;

public class FacadePersona {

    private PersonaDao personaDao = new PersonaDao();

    public List<Persona> cargarPersona() {
        return personaDao.cargarPersona();
    }

    public void insertarPersona(Persona persona) {
        personaDao.agregarPersona(persona);

    }

    public void modificarPersona(Persona persona) {
        personaDao.modificar(persona);
    }

   public BusquedaDeFamiliar buscarPersona(String idpersona) {
        return personaDao.buscarPersonalPorId(idpersona);
    }

    public void buscarPorNombre(String nombrepersona){
        personaDao.buscarPorNombre(nombrepersona);
    }

    public void eliminarPersona(int idCliente) {

        personaDao.eliminar(idCliente);
    }

}
