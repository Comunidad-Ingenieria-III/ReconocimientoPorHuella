package datospersona.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.ByteArrayInputStream;
import java.sql.Date;

public class Persona {

    private IntegerProperty idpersona;
    private StringProperty primerNombre;
    private StringProperty segundoNombre;
    private StringProperty primerApellido;
    private StringProperty segundoApellido;
    private Date fechaNacimiento;
    private StringProperty direccion;
    private StringProperty sexo;
    private StringProperty alergicoA;
    private StringProperty enfermedadSufre;
    private StringProperty observaciones;
    private ByteArrayInputStream huella;
    private IntegerProperty huella1;
    private StringProperty tipoDocumento;
    private IntegerProperty idEps;


    public Persona() {
    }

    public Persona(int idpersona, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                   Date fechaNacimiento, String direccion, String sexo, String alegicoA, String enfermedadSufre, String observaciones,
                   ByteArrayInputStream huella, int huella1, String tipoDocumento, int idEps) {

        this.idpersona = new SimpleIntegerProperty(idpersona);
        this.primerNombre = new SimpleStringProperty(primerNombre);
        this.segundoNombre = new SimpleStringProperty(segundoNombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = new SimpleStringProperty(direccion);
        this.sexo = new SimpleStringProperty(sexo);
        this.alergicoA = new SimpleStringProperty(alegicoA);
        this.enfermedadSufre = new SimpleStringProperty(enfermedadSufre);
        this.observaciones = new SimpleStringProperty(observaciones);
        this.huella = huella;
        this.huella1 = new SimpleIntegerProperty(huella1);
        this.tipoDocumento = new SimpleStringProperty(tipoDocumento);
        this.idEps = new SimpleIntegerProperty(idEps);
    }

    public Persona(int idpersona, String primerNombre, String segundoNombre, String alegicoA, String enfermedadSufre, String observaciones) {
        //this.huella1 = new SimpleIntegerProperty(huella1);
        this.idpersona = new SimpleIntegerProperty(idpersona);
        this.primerNombre = new SimpleStringProperty(primerNombre);
        this.segundoNombre = new SimpleStringProperty(segundoNombre);
        this.alergicoA = new SimpleStringProperty(alegicoA);
        this.enfermedadSufre = new SimpleStringProperty(enfermedadSufre);
        this.observaciones = new SimpleStringProperty(observaciones);

    }

    public int getIdpersona() {
        return idpersona.get();
    }

    public IntegerProperty idpersonaProperty() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
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

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccion() {
        return direccion;
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

    public int getidEps() {
        return idEps.get();
    }

    public IntegerProperty getidEpsProperty() {
        return idEps;
    }

    public void setgetidEps(int idEps) {
        this.idEps.set(idEps);
    }


    @Override
    public String toString() {
        return "Persona{" +
                "idpersona=" + idpersona +
                ", primerNombre=" + primerNombre +
                ", segundoNombre=" + segundoNombre +
                ", primerApellido=" + primerApellido +
                ", segundoApellido=" + segundoApellido +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo=" + sexo +
                ", alergicoA=" + alergicoA +
                ", enfermedadSufre=" + enfermedadSufre +
                ", observaciones=" + observaciones +
                ", idTipoDocumeto=" + tipoDocumento +
                ", idEps=" + idEps +
                '}';
    }
}
