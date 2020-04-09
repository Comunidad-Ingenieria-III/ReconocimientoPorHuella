package personalSalud.personalsaludfacade;

import datosFamiliar.daofamiliar.FamiliarDAO;
import datosFamiliar.dtofamiliar.Familiar;
import personalSalud.personalsaluddao.PersonalSaludDao;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import java.sql.SQLException;
import java.util.List;

public class PersonalSaludFacade {


    private PersonalSaludDao personalSaludDao = new PersonalSaludDao();


    public List<PersonalSalud> obtenerTodoPersonalSalud() {
        return personalSaludDao.listarPersonalSalud();
    }

    public boolean agregarPersonalSalud(PersonalSalud personalSalud, PsDto psDto) throws SQLException {
        return personalSaludDao.agregarPersonalSalud(personalSalud, psDto);

    }

    public List<PsDto> agregarPesonalTitulo() {
        return personalSaludDao.listaPsdto();
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

    public PsDto buscar(PsDto psDto){return  personalSaludDao.buscarPorId(psDto);}

   /*public PersonalSalud buscarPersonalSalud(String idPersonal){
        return personalSaludDao.buscarPersonalSalud(idPersonal);
    }*/

    public void eliminarPersonal(String idPersonal){
        personalSaludDao.eliminarPersonalSalud(idPersonal);
    }
}
