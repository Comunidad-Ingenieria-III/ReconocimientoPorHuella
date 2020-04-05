package cargo.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cargo {

    private StringProperty idCargo =  new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public Cargo(String idCargo, String nombre) {
        this.idCargo.set(idCargo);
        this.nombre.set(nombre);
    }

    public Cargo() {
        this("","");
    }

    public String getIdCargo() {
        return idCargo.get();
    }

    public StringProperty idCargoProperty() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo.set(idCargo);
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
