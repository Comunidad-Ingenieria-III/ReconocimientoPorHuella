package persona_familiar.per_fami_dto;

import javafx.beans.property.*;

import java.sql.Date;

public class Per_Fami_Dto {


    private StringProperty idPersona = new SimpleStringProperty();
    private StringProperty idFamiliar = new SimpleStringProperty();
    private StringProperty nombre1 = new SimpleStringProperty();
    private StringProperty telefono = new SimpleStringProperty();
    private Date fechaIngreso;
    private BooleanProperty estado = new SimpleBooleanProperty();


    public Per_Fami_Dto(String idPersona, String idFamiliar, String nombre1, String telefono, Date fechaIngreso, boolean estado) {

        this.idPersona.set(idPersona);
        this.idFamiliar.set(idFamiliar);
        this.nombre1.set(nombre1);
        this.telefono.set(telefono);
        this.fechaIngreso = fechaIngreso;
        this.estado.set(estado);
    }


    public Per_Fami_Dto() {
        this("", "", "", "", new Date(new java.util.Date().getTime()), false);
    }

    public String getIdPersona() {
        return idPersona.get();
    }

    public StringProperty idPersonaProperty() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona.set(idPersona);
    }

    public String getIdFamiliar() {
        return idFamiliar.get();
    }

    public StringProperty idFamiliarProperty() {
        return idFamiliar;
    }

    public void setIdFamiliar(String idFamiliar) {
        this.idFamiliar.set(idFamiliar);
    }

    public String getNombre1() {
        return nombre1.get();
    }

    public StringProperty nombre1Property() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1.set(nombre1);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstado() {
        return estado.get();
    }

    public BooleanProperty estadoProperty() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado.set(estado);
    }

    @Override
    public String toString() {
        return "Per_Fami_Dto{" +
                "idPersona=" + idPersona +
                ", idFamiliar=" + idFamiliar +
                ", fechaIngreso=" + fechaIngreso +
                ", nombre1=" + nombre1 +
                ", telefono=" + telefono +
                ", estado=" + estado +
                '}';
    }
}
