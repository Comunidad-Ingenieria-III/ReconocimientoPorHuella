package registroAtencionPaciente.dtoregistro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;


public class RegistroDto {

    private Date fechaAtencionPaciente;
    private Date horaAtencionPaciente;
    private StringProperty condicionPaciente = new SimpleStringProperty();
    private StringProperty glasgow = new SimpleStringProperty();
    private StringProperty signosVitales = new SimpleStringProperty();
    private StringProperty lugarAccidente = new SimpleStringProperty();
    private StringProperty idMedicamento = new SimpleStringProperty();
    private StringProperty dosis = new SimpleStringProperty();
    private StringProperty idPersonal = new SimpleStringProperty();
    private StringProperty idInstiRefe = new SimpleStringProperty();
    private StringProperty codigoRemision = new SimpleStringProperty();
    private StringProperty idpersona = new SimpleStringProperty();
    private StringProperty nombrePaciente = new SimpleStringProperty();
    private StringProperty apellidoPaciente = new SimpleStringProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();

    public RegistroDto() {
        this(new Date(new java.util.Date().getTime()), new Date(new java.util.Date().getTime()), "", "",
                "", "", "", "", "", "", "", "", "", "",
                false);
    }

    public RegistroDto(Date fechaAtencionPaciente, Date horaAtencionPaciente, String condicionPaciente, String glasgow, String signosVitales,
                       String lugarAccidente, String idMedicamento, String dosis, String idPersonal, String idInstiRefe,
                       String codigoRemision, String idpersona, String nombrePaciente, String apellidoPaciente,
                       boolean estado) {
        this.fechaAtencionPaciente = fechaAtencionPaciente;
        this.horaAtencionPaciente = horaAtencionPaciente;
        this.condicionPaciente.set(condicionPaciente);
        this.glasgow.set(glasgow);
        this.signosVitales.set(signosVitales);
        this.lugarAccidente.set(lugarAccidente);
        this.idMedicamento.set(idMedicamento);
        this.dosis.set(dosis);
        this.idPersonal.set(idPersonal);
        this.idInstiRefe.set(idInstiRefe);
        this.codigoRemision.set(codigoRemision);
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

    public String getIdInstiRefe() {
        return idInstiRefe.get();
    }

    public StringProperty idInstiRefeProperty() {
        return idInstiRefe;
    }

    public void setIdInstiRefe(String idInstiRefe) {
        this.idInstiRefe.set(idInstiRefe);
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
        return codigoRemision.get();
    }
}
