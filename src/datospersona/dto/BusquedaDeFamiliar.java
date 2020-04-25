package datospersona.dto;

import persona_familiar.per_fami_dto.Per_Fami_Dto;

import java.util.List;

public class BusquedaDeFamiliar {

    private List<Per_Fami_Dto> listafamiliar;
    private Persona persona;


    public BusquedaDeFamiliar(List<Per_Fami_Dto> listafamiliar, Persona persona) {
        this.listafamiliar = listafamiliar;
        this.persona = persona;
    }

    public List<Per_Fami_Dto> getListafamiliar() {
        return listafamiliar;
    }

    public void setListafamiliar(List<Per_Fami_Dto> listafamiliar) {
        this.listafamiliar = listafamiliar;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}

