package personalSalud.personalsaluddto;

import cargo.dto.Cargo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

public class PersonalSalud {

    private StringProperty idPersonal = new SimpleStringProperty();
    private StringProperty nombre1 = new SimpleStringProperty();
    private StringProperty nombre2 = new SimpleStringProperty();
    private StringProperty apellido1 = new SimpleStringProperty();
    private StringProperty apellido2 = new SimpleStringProperty();
    private StringProperty sexo = new SimpleStringProperty();
    private StringProperty telefono = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty tipoDocumento = new SimpleStringProperty();
    private StringProperty cargo = new SimpleStringProperty();


    public PersonalSalud(String idPersonal, String nombre1, String nombre2, String apellido1, String apellido2,
                         String sexo, String telefono, String email, String tipoDocumento, String cargo) {
        this.idPersonal.set(idPersonal);
        this.nombre1.set(nombre1);
        this.nombre2.set(nombre2);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
        this.sexo.set(sexo);
        this.email.set(email);
        this.telefono.set(telefono);
        this.tipoDocumento.set(tipoDocumento);
        this.cargo.set(cargo);

    }

    public PersonalSalud() {
        this("", "", "", "", "", "", "", "", "", "");
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

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    public String getCargo() {
        return cargo.get();
    }

    public StringProperty cargoProperty() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo.set(cargo);
    }

    @Override
    public String toString() {
        return (idPersonal.get());
    }


}
