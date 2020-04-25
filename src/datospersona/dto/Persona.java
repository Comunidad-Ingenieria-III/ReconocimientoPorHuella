package datospersona.dto;

import javafx.beans.property.*;

import java.io.ByteArrayInputStream;
import java.sql.Date;

public class Persona {

    private StringProperty idpersona = new SimpleStringProperty();
    private StringProperty primerNombre = new SimpleStringProperty();
    private StringProperty segundoNombre = new SimpleStringProperty();
    private StringProperty primerApellido = new SimpleStringProperty();
    private StringProperty segundoApellido = new SimpleStringProperty();
    private Date fechaNacimiento;
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty sexo = new SimpleStringProperty();
    private StringProperty alergicoA = new SimpleStringProperty();
    private StringProperty enfermedadSufre = new SimpleStringProperty();
    private StringProperty observaciones = new SimpleStringProperty();
    private ByteArrayInputStream huella;
    private IntegerProperty huella1 = new SimpleIntegerProperty();
    private StringProperty tipoDocumento = new SimpleStringProperty();
    private StringProperty idEps = new SimpleStringProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();


    public Persona() {}

    public Persona(String idpersona, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                   Date fechaNacimiento, String direccion, String sexo, String alegicoA, String enfermedadSufre, String observaciones,
                   ByteArrayInputStream huella, int huella1, String tipoDocumento, String idEps, boolean estado) {

        this.idpersona.set(idpersona);
        this.primerNombre.set(primerNombre);
        this.segundoNombre.set(segundoNombre);
        this.primerApellido.set(primerApellido);
        this.segundoApellido.set(segundoApellido);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion.set(direccion);
        this.sexo.set(sexo);
        this.alergicoA.set(alegicoA);
        this.enfermedadSufre.set(enfermedadSufre);
        this.observaciones.set(observaciones);
        this.huella = huella;
        this.huella1.setValue(huella1);
        this.tipoDocumento.set(tipoDocumento);
        this.idEps.set(idEps);
        this.estado.setValue(estado);
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

    public String getPrimerNombre() {
        return primerNombre.get();
    }

    public StringProperty primerNombreProperty() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre.set(primerNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre.get();
    }

    public StringProperty segundoNombreProperty() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre.set(segundoNombre);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public StringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public StringProperty segundoApellidoProperty() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido.set(segundoApellido);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getAlergicoA() {
        return alergicoA.get();
    }

    public StringProperty alergicoAProperty() {
        return alergicoA;
    }

    public void setAlergicoA(String alergicoA) {
        this.alergicoA.set(alergicoA);
    }

    public String getEnfermedadSufre() {
        return enfermedadSufre.get();
    }

    public StringProperty enfermedadSufreProperty() {
        return enfermedadSufre;
    }

    public void setEnfermedadSufre(String enfermedadSufre) {
        this.enfermedadSufre.set(enfermedadSufre);
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public ByteArrayInputStream getHuella() {
        return huella;
    }

    public void setHuella(ByteArrayInputStream huella) {
        this.huella = huella;
    }

    public int getHuella1() {
        return huella1.get();
    }

    public IntegerProperty huella1Property() {
        return huella1;
    }

    public void setHuella1(int huella1) {
        this.huella1.set(huella1);
    }

    public String getTipoDocumento() {
        return tipoDocumento.get();
    }

    public StringProperty tipoDocumentoProperty() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento.set(tipoDocumento);
    }

    public String getIdEps() {
        return idEps.get();
    }

    public StringProperty idEpsProperty() {
        return idEps;
    }

    public void setIdEps(String idEps) {
        this.idEps.set(idEps);
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
        return (idpersona.get());
    }
}
