package personalSalud.personalsaluddto;

import personal_salud_titulo.psdto.PsDto;

import java.util.List;

public class BusquedaDePersonal {
    private List<PsDto> listaTitulos;
    private PersonalSalud personalSalud;

    public BusquedaDePersonal(List<PsDto> listaTitulos, PersonalSalud personalSalud) {
        this.listaTitulos = listaTitulos;
        this.personalSalud = personalSalud;
    }

    public List<PsDto> getListaTitulos() {
        return listaTitulos;
    }

    public void setListaTitulos(List<PsDto> listaTitulos) {
        this.listaTitulos = listaTitulos;
    }

    public PersonalSalud getPersonalSalud() {
        return personalSalud;
    }

    public void setPersonalSalud(PersonalSalud personalSalud) {
        this.personalSalud = personalSalud;
    }
}
