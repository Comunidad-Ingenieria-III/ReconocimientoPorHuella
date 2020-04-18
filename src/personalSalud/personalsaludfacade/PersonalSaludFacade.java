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

    public List<PsDto> agregarPesonalTitulo() {
        return personalSaludDao.listaPsdto();
    }

    public int agregarPersonal(PersonalSalud personalSalud, List<PsDto> titulos){
        return personalSaludDao.agregarPersonal(personalSalud, titulos);

    }

    public boolean agregarTitulos(List<PsDto> listaps){
        return personalSaludDao.agregarLote(listaps);
    }

    public int modificarPersonal(PersonalSalud personalSalud , List<PsDto> titulos){
        return personalSaludDao.modificarPersonal(personalSalud, titulos);
    }

    public BusquedaDePersonal buscarPersonalTitulos(String idPersonal){
        return personalSaludDao.buscarPersonalPorId(idPersonal);

    }


    /*public PsDto buscar(PsDto psDto){return  personalSaludDao.buscarPorId(psDto);}

   public PersonalSalud buscarPersonalSalud(String idPersonal){
        return personalSaludDao.buscarPersonalSalud(idPersonal);
    }*/

    public void eliminarPersonal(String idPersonal){
        personalSaludDao.eliminarPersonalSalud(idPersonal);
    }

    public boolean buscarPorId(String idPersonal) {
        return  personalSaludDao.buscarPrimaryKey(idPersonal);
    }
}
