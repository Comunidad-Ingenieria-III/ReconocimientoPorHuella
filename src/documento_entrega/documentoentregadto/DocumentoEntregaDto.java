package documento_entrega.documentoentregadto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class DocumentoEntregaDto {

    private StringProperty idTipoDocumento = new SimpleStringProperty();
    private StringProperty idPersonaRecibe = new SimpleStringProperty();
    private StringProperty nombre1 = new SimpleStringProperty();
    private StringProperty nombre2 = new SimpleStringProperty();
    private StringProperty apellido1 = new SimpleStringProperty();
    private StringProperty apellido2 = new SimpleStringProperty();
    private StringProperty idCargo = new SimpleStringProperty();
    private Date fechaRecepcionPaciente;
    private Date horaRecepcionPaciente;
    private StringProperty codigoRemision = new SimpleStringProperty();
    private StringProperty obsevaciones = new SimpleStringProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();


    public DocumentoEntregaDto(String idTipoDocumento, String idPersonaRecibe, String nombre1, String nombre2, String apellido1, String apellido2,
                               String idCargo, Date fechaRecepcionPaciente, Date horaRecepcionPaciente, String codigoRemision, String observaciones, boolean estado) {
        this.idTipoDocumento.set(idTipoDocumento);
        this.idPersonaRecibe.set(idPersonaRecibe);
        this.nombre1.set(nombre1);
        this.nombre2.set(nombre2);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
        this.idCargo.set(idCargo);
        this.fechaRecepcionPaciente = fechaRecepcionPaciente;
        this.horaRecepcionPaciente = horaRecepcionPaciente;
        this.codigoRemision.set(codigoRemision);
        this.obsevaciones.set(observaciones);
        this.estado.set(estado);

    }

    public DocumentoEntregaDto() {
        this("","", "", "", "", "", "", new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime()),"", "", false);
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento.get();
    }

    public StringProperty idTipoDocumentoProperty() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento.set(idTipoDocumento);
    }

    public String getIdPersonaRecibe() {
        return idPersonaRecibe.get();
    }

    public StringProperty idPersonaRecibeProperty() {
        return idPersonaRecibe;
    }

    public void setIdPersonaRecibe(String idPersonaRecibe) {
        this.idPersonaRecibe.set(idPersonaRecibe);
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

    public String getNombre2() {
        return nombre2.get();
    }

    public StringProperty nombre2Property() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2.set(nombre2);
    }

    public String getApellido1() {
        return apellido1.get();
    }

    public StringProperty apellido1Property() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1.set(apellido1);
    }

    public String getApellido2() {
        return apellido2.get();
    }

    public StringProperty apellido2Property() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2.set(apellido2);
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

    public Date getFechaRecepcionPaciente() {
        return fechaRecepcionPaciente;
    }

    public void setFechaRecepcionPaciente(Date fechaRecepcionPaciente) {
        this.fechaRecepcionPaciente = fechaRecepcionPaciente;
    }

    public Date getHoraRecepcionPaciente() {
        return horaRecepcionPaciente;
    }

    public void setHoraRecepcionPaciente(Date horaRecepcionPaciente) {
        this.horaRecepcionPaciente = horaRecepcionPaciente;
    }

    public String getCodigoRemision() {
        return codigoRemision.get();
    }

    public StringProperty codigoRemisionProperty() {
        return codigoRemision;
    }

    public void setCodigoRemision(String codigoRemision) {
        this.codigoRemision.set(codigoRemision);
    }

    public String getObsevaciones() {
        return obsevaciones.get();
    }

    public StringProperty obsevacionesProperty() {
        return obsevaciones;
    }

    public void setObsevaciones(String obsevaciones) {
        this.obsevaciones.set(obsevaciones);
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
        return "PersonalRecibeDto{" +
                "idPersonaRecibe=" + idPersonaRecibe +
                ", nombre1=" + nombre1 +
                ", nombre2=" + nombre2 +
                ", apellido1=" + apellido1 +
                ", apellido2=" + apellido2 +
                ", idCargo=" + idCargo +
                ", fechaRecepcionPaciente=" + fechaRecepcionPaciente +
                ", horaRecepcionPaciente=" + horaRecepcionPaciente +
                ", codigoRemision=" + codigoRemision +
                ", obsevaciones=" + obsevaciones +
                ", estado=" + estado +
                '}';
    }
}
