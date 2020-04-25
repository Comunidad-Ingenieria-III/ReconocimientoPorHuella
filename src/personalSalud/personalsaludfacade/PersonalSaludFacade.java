package personalSalud.personalsaludfacade;

import datosFamiliar.daofamiliar.FamiliarDAO;
import datosFamiliar.dtofamiliar.Familiar;
import personalSalud.personalsaluddao.PersonalSaludDao;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import java.sql.SQLException;
import java.util.List;

public class PersonalSaludFacade {


    private PersonalSaludDao personalSaludDao = new PersonalSaludDao();


    public List<PersonalSalud> obtenerTodoPersonalSalud() {
        return personalSaludDao.listarPersonalSalud();
    }

    public int agregarPersonal(PersonalSalud personalSalud, List<PsDto> titulos){//funcion para gregar personal de salud y personal-salud-titulo con transaccion
        return personalSaludDao.agregarPersonal(personalSalud, titulos);

    }
    public int agregarPersonal2(PersonalSalud personalSalud){//Funcion para agregar un personal de salud
        return personalSaludDao.agregarPersonal2(personalSalud);

    }

    public boolean agregarPsdto(PsDto psDto){//Funcion para agregar un personal-salud-titulo
        return personalSaludDao.agregarPsdto(psDto);
    }

    public int modificarPersonal(PersonalSalud personalSalud , List<PsDto> titulos){//Funcion para modficar personal de salud y personal de salud titulo
        //en el mismo metodo.
        return personalSaludDao.modificarPersonal(personalSalud, titulos);
    }
    public int modificarPersonal2(PersonalSalud personalSalud){//Funcion para modificar un personal de salud
        return personalSaludDao.modificarPersonal2(personalSalud);
    }

    public BusquedaDePersonal buscarPersonalTitulos(String idPersonal){//Funcion para realizar la busqueda de un personal de salud incluyendo los
        //titulos que estan relacionados con este en la tabla personal-salud-titulo
        return personalSaludDao.buscarPersonalPorId(idPersonal);

    }

    public boolean eliminarPersonal(String idPersonal){//Funcion para inhabilitar un personal de salud
        return  personalSaludDao.inhabilitarPersonalSalud(idPersonal);
    }

    public boolean buscarPorId(String idPersonal) {//Funcion para realizar la busqueda de un personal de salud por medio de su clave primaria
        return  personalSaludDao.buscarPrimaryKey(idPersonal);
    }
    public PersonalSalud buscarPorIdPersonal(String idPersonal){
        return personalSaludDao.buscarPorIdPersonal(idPersonal);
    }

    public PsDto buscarPsdto (int idPs){
        return personalSaludDao.buscarPsdto(idPs);
    }

    public int modificarPsdto(PsDto psDto) {
        return personalSaludDao.modificarTitulos(psDto);
    }

    public boolean eliminarPsdto(int idPs, boolean estado){
        return personalSaludDao.inhabilitarPsdto(idPs, estado);
    }
}
