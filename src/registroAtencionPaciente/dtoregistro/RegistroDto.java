package registroAtencionPaciente.dtoregistro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;


public class RegistroDto{

    private Date fechaAtencionPaciente;
    private Date horaAtencionPaciente;
    private StringProperty condicionPaciente = new SimpleStringProperty();
    private StringProperty glasgow = new SimpleStringProperty();
    private StringProperty signosVitales = new SimpleStringProperty();
    private StringProperty lugarAccidente = new SimpleStringProperty();
    private StringProperty idMedicamento = new SimpleStringProperty();
    private StringProperty dosis = new SimpleStringProperty();
    private StringProperty idPersonal = new SimpleStringProperty();
    private StringProperty idTipoDocumento = new SimpleStringProperty();
    private StringProperty idPersonaRecibe = new SimpleStringProperty();
    private StringProperty nombre1PersonaRecibe = new SimpleStringProperty();
    private StringProperty nombre2PersonaRecibe = new SimpleStringProperty();
    private StringProperty apellido1PersonaRecibe = new SimpleStringProperty();
    private StringProperty apellido2PersonaRecibe = new SimpleStringProperty();
    private StringProperty idCargo = new SimpleStringProperty();
    private Date fechaRecepcionPaciente;
    private Date horaRecepcionPaciente;
    private StringProperty idpersona = new SimpleStringProperty();
    private StringProperty nombrePaciente = new SimpleStringProperty();
    private StringProperty apellidoPaciente = new SimpleStringProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();

    public RegistroDto(){this(new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime()), "", "", "", "", "", "", "", "",
    "", "", "", "", "", "", new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime()), "", "", "",
    false);}

    public RegistroDto(Date fechaAtencionPaciente, Date horaAtencionPaciente, String condicionPaciente, String glasgow, String signosVitales,
                       String  lugarAccidente, String idMedicamento, String dosis, String idPersonal, String idTipoDocumeto, String idPersonaRecibe, String nombre1PersonaRecibe,
                       String nombre2PersonaRecibe, String apellido1PersonaRecibe, String apellido2PersonaRecibe, String idCargo,
                       Date fechaRecepcionPaciente, Date horaRecepcionPaciente,  String idpersona, String nombrePaciente, String apellidoPaciente,
                       boolean estado){
        this.fechaAtencionPaciente = fechaAtencionPaciente;
        this.horaAtencionPaciente = horaAtencionPaciente;
        this.condicionPaciente.set(condicionPaciente);
        this.glasgow.set(glasgow);
        this.signosVitales.set(signosVitales);
        this.lugarAccidente.set(lugarAccidente);
        this.idMedicamento.set(idMedicamento);
        this.dosis.set(dosis);
        this.idPersonal.set(idPersonal);
        this.idTipoDocumento.set(idTipoDocumeto);
        this.idPersonaRecibe.set(idPersonaRecibe);
        this.nombre1PersonaRecibe.set(nombre1PersonaRecibe);
        this.nombre2PersonaRecibe.set(nombre2PersonaRecibe);
        this.apellido1PersonaRecibe.set(apellido1PersonaRecibe);
        this.apellido2PersonaRecibe.set(apellido2PersonaRecibe);
        this.idCargo.set(idCargo);
        this.fechaRecepcionPaciente = fechaRecepcionPaciente;
        this.horaRecepcionPaciente = horaRecepcionPaciente;
        this.idpersona.set(idpersona);
        this.nombrePaciente.set(nombrePaciente);
        this.apellidoPaciente.set(apellidoPaciente);
        this.estado.setValue(estado);
    }

    public Date getFechaAtencionPaciente() {
        return fechaAtencionPaciente;
    }

    public void setFechaAtencionPaciente(Date fechaAtencionPaciente) {
        this.fechaAtencionPaciente = fechaAtencionPaciente;
    }

    public Date getHoraAtencionPaciente() {
        return horaAtencionPaciente;
    }

    public void setHoraAtencionPaciente(Date horaAtencionPaciente) {
        this.horaAtencionPaciente = horaAtencionPaciente;
    }

    public String getCondicionPaciente() {
        return condicionPaciente.get();
    }

    public StringProperty condicionPacienteProperty() {
        return condicionPaciente;
    }

    public void setCondicionPaciente(String condicionPaciente) {
        this.condicionPaciente.set(condicionPaciente);
    }

    public String getGlasgow() {
        return glasgow.get();
    }

    public StringProperty glasgowProperty() {
        return glasgow;
    }

    public void setGlasgow(String glasgow) {
        this.glasgow.set(glasgow);
    }

    public String getSignosVitales() {
        return signosVitales.get();
    }

    public StringProperty signosVitalesProperty() {
        return signosVitales;
    }

    public void setSignosVitales(String signosVitales) {
        this.signosVitales.set(signosVitales);
    }

    public String getLugarAccidente() {
        return lugarAccidente.get();
    }

    public StringProperty lugarAccidenteProperty() {
        return lugarAccidente;
    }

    public void setLugarAccidente(String lugarAccidente) {
        this.lugarAccidente.set(lugarAccidente);
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

    public String getDosis() {
        return dosis.get();
    }

    public StringProperty dosisProperty() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis.set(dosis);
    }

    public String getIdPersonal() {
        return idPersonal.get();
    }

    public StringProperty idPersonalProperty() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal.set(idPersonal);
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

    public String getNombre1PersonaRecibe() {
        return nombre1PersonaRecibe.get();
    }

    public StringProperty nombre1PersonaRecibeProperty() {
        return nombre1PersonaRecibe;
    }

    public void setNombre1PersonaRecibe(String nombre1PersonaRecibe) {
        this.nombre1PersonaRecibe.set(nombre1PersonaRecibe);
    }

    public String getNombre2PersonaRecibe() {
        return nombre2PersonaRecibe.get();
    }

    public StringProperty nombre2PersonaRecibeProperty() {
        return nombre2PersonaRecibe;
    }

    public void setNombre2PersonaRecibe(String nombre2PersonaRecibe) {
        this.nombre2PersonaRecibe.set(nombre2PersonaRecibe);
    }

    public String getApellido1PersonaRecibe() {
        return apellido1PersonaRecibe.get();
    }

    public StringProperty apellido1PersonaRecibeProperty() {
        return apellido1PersonaRecibe;
    }

    public void setApellido1PersonaRecibe(String apellido1PersonaRecibe) {
        this.apellido1PersonaRecibe.set(apellido1PersonaRecibe);
    }

    public String getApellido2PersonaRecibe() {
        return apellido2PersonaRecibe.get();
    }

    public StringProperty apellido2PersonaRecibeProperty() {
        return apellido2PersonaRecibe;
    }

    public void setApellido2PersonaRecibe(String apellido2PersonaRecibe) {
        this.apellido2PersonaRecibe.set(apellido2PersonaRecibe);
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

    public String getIdpersona() {
        return idpersona.get();
    }

    public StringProperty idpersonaProperty() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona.set(idpersona);
    }

    public String getNombrePaciente() {
        return nombrePaciente.get();
    }

    public StringProperty nombrePacienteProperty() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente.set(nombrePaciente);
    }

    public String getApellidoPaciente() {
        return apellidoPaciente.get();
    }

    public StringProperty apellidoPacienteProperty() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente.set(apellidoPaciente);
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
        return "RegistroDto{" +
                "fechaAtencionPaciente=" + fechaAtencionPaciente +
                ", horaAtencionPaciente=" + horaAtencionPaciente +
                ", condicionPaciente=" + condicionPaciente +
                ", glasgow=" + glasgow +
                ", signosVitales=" + signosVitales +
                ", lugarAccidente=" + lugarAccidente +
                ", idMedicamento=" + idMedicamento +
                ", dosis=" + dosis +
                ", idPersonal=" + idPersonal +
                ", idTipoDocumento=" + idTipoDocumento +
                ", idPersonaRecibe=" + idPersonaRecibe +
                ", nombre1PersonaRecibe=" + nombre1PersonaRecibe +
                ", nombre2PersonaRecibe=" + nombre2PersonaRecibe +
                ", apellido1PersonaRecibe=" + apellido1PersonaRecibe +
                ", apellido2PersonaRecibe=" + apellido2PersonaRecibe +
                ", idCargo=" + idCargo +
                ", fechaRecepcionPaciente=" + fechaRecepcionPaciente +
                ", horaRecepcionPaciente=" + horaRecepcionPaciente +
                ", idpersona=" + idpersona +
                ", nombrePaciente=" + nombrePaciente +
                ", apellidoPaciente=" + apellidoPaciente +
                ", estado=" + estado +
                '}';
    }
}
