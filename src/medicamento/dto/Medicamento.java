package medicamento.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medicamento {

    private StringProperty idMedicamento = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public Medicamento (String idMedicamento, String nombre){
        this.idMedicamento.set(idMedicamento);
        this.nombre.set(nombre);

    }
    public Medicamento(){
        this("","");
    }



    public String getIdMedicamento() {
        return idMedicamento.get();
    }

    public StringProperty idMedicamentoProperty() {
        return idMedicamento;
    }

    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento.set(idMedicamento);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
}
