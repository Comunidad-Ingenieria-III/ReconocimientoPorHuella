package medicamento.facade;
import medicamento.dao.MedicamentoDao;
import medicamento.dto.Medicamento;

import java.util.List;
public class FacadeMedicamento {
    MedicamentoDao medicamentoDao =new MedicamentoDao();
    public List<Medicamento> obternetTodosMedicamentos(){ return medicamentoDao.obtenerTodas();}
    public int agregar(Medicamento medicamento) { return medicamentoDao.agregar(medicamento); }
    public int modificar(Medicamento medicamento) { return medicamentoDao.modificar(medicamento); }
    public int eliminar(String idMedicamento) { return medicamentoDao.eliminar(idMedicamento); }
}

