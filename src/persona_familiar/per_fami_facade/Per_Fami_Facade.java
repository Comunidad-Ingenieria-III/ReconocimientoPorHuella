package persona_familiar.per_fami_facade;

import persona_familiar.per_fami_dao.Per_Fami_Dao;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import personal_salud_titulo.psdao.PsDao;
import personal_salud_titulo.psdto.PsDto;

import java.util.List;

public class Per_Fami_Facade {
    Per_Fami_Dao per_fami_dao = new Per_Fami_Dao();


    public List<Per_Fami_Dto> obtenerTodas() {
        return per_fami_dao.cargartodas();
    }

    public int agregar(Per_Fami_Dto per_fami_dto) {
        return per_fami_dao.agregar(per_fami_dto);
    }

    public int modificar(Per_Fami_Dto per_fami_dto) {
        return per_fami_dao.modificar(per_fami_dto);
    }

    public void buscar(Per_Fami_Dto per_fami_dto){ per_fami_dao.buscarPorId(String.valueOf(per_fami_dto));}

    public boolean buscarPorId(String idPersonal) {//Funcion para realizar la busqueda de un personal de salud por medio de su clave primaria
        return  per_fami_dao.buscarPrimaryKey(idPersonal);
    }

    public int eliminar(String idpersona) {
        return per_fami_dao.eliminar(idpersona);
    }
}
