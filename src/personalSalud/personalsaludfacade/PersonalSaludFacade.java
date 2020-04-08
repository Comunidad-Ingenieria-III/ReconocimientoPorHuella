package personalSalud.personalsaludfacade;

import datosFamiliar.daofamiliar.FamiliarDAO;
import datosFamiliar.dtofamiliar.Familiar;
import personalSalud.personalsaluddao.PersonalSaludDao;
import personalSalud.personalsaluddto.PersonalSalud;

import java.sql.SQLException;
import java.util.List;

public class PersonalSaludFacade {


    private PersonalSaludDao personalSaludDao = new PersonalSaludDao();


    public List<PersonalSalud> obtenerTodoPersonalSalud() {
        return personalSaludDao.listarPersonalSalud();
    }

    public boolean agregarPersonalSalud(PersonalSalud personalSalud) throws SQLException {
        return personalSaludDao.agregarPersonalSalud(personalSalud);

    }

    public int agregarPersonal(PersonalSalud personalSalud) throws SQLException {
        return personalSaludDao.agregarPersonal(personalSalud);

    }

    public boolean modificarPersonalSalud(PersonalSalud personalSalud) throws SQLException {
        return personalSaludDao.modificarPersonalSalud(personalSalud);
    }
    public int modificarPersonal(PersonalSalud personalSalud){
        return personalSaludDao.modificarPersonal(personalSalud);
    }

   /*public PersonalSalud buscarPersonalSalud(String idPersonal){
        return personalSaludDao.buscarPersonalSalud(idPersonal);
    }*/

    public void eliminarPersonal(String idPersonal){
        personalSaludDao.eliminarPersonalSalud(idPersonal);
    }
}
