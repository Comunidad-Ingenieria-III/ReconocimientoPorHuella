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

    public int modificarPersona(Persona persona) {
       return personaDao.modificar(persona);
    }

   public BusquedaDeFamiliar buscarPersona(String idpersona) {
        return personaDao.buscarPersonalPorId(idpersona);
    }

    public boolean buscarPersonaPrimaryKey(String idpersona){
        return personaDao.buscarPrimaryKeyPersona(idpersona);
    }

    public void buscarPorNombre(String nombrepersona){
        personaDao.buscarPorNombre(nombrepersona);
    }

    public void eliminarPersona(int idCliente) {

        personaDao.eliminar(idCliente);
    }

    public boolean eliminarPersonal(String idpersona){//Funcion para inhabilitar un personal de salud
        return  personaDao.inhabilitarPersona(idpersona);
    }

}
