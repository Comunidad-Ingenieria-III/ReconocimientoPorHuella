package persona_familiar.per_fami_dto;

import javafx.beans.property.*;

import java.sql.Date;

public class Per_Fami_Dto {


    private StringProperty idPersona = new SimpleStringProperty();
    private StringProperty idFamiliar = new SimpleStringProperty();
    private Date fechaIngreso;
    private BooleanProperty estado = new SimpleBooleanProperty();


    public Per_Fami_Dto(String idPersona, String idFamiliar, Date fechaIngreso, boolean estado) {

        this.idPersona.set(idPersona);
        this.idFamiliar.set(idFamiliar);
        this.fechaIngreso = fechaIngreso;
        this.estado.set(estado);
    }


    public Per_Fami_Dto() {
        this("", "", new Date(new java.util.Date().getTime()), false);
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
                ", estado=" + estado +
                '}';
    }
}
