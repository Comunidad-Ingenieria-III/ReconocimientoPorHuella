package medicamento.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medicamento {

    private StringProperty idMedicamento = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty estado = new SimpleStringProperty();

    public Medicamento (String idMedicamento, String nombre, String estado){
        this.idMedicamento.set(idMedicamento);
        this.nombre.set(nombre);
        this.estado.set(estado);

    }
    public Medicamento(){
        this("","","");
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
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

    @Override
    public String toString() {
        return nombre.get();
    }
}
