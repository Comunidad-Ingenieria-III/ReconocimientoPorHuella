package cargo.dto;

import javafx.beans.property.*;

public class Cargo {

    private StringProperty idCargo =  new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private SimpleBooleanProperty estado = new SimpleBooleanProperty();

    public Cargo(String idCargo, String nombre, boolean estado) {
        this.idCargo.set(idCargo);
        this.nombre.set(nombre);
        this.estado.set(estado);
    }

    public Cargo() {
        this("","", Boolean.parseBoolean(""));
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

    public boolean isEstado() {
        return estado.get();
    }

    public SimpleBooleanProperty estadoProperty() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado.set(estado);
    }

    @Override
    public String toString() {
        return nombre.get();
    }
}
