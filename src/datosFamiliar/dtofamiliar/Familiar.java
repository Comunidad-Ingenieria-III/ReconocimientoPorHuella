package datosFamiliar.dtofamiliar;

import javafx.beans.property.*;

public class Familiar {

    private StringProperty idFamiliar = new SimpleStringProperty();
    private StringProperty primerNombre = new SimpleStringProperty();
    private StringProperty segundoNombre = new SimpleStringProperty();
    private StringProperty primerApellido = new SimpleStringProperty();
    private StringProperty segundoApellido = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty telFamiliar = new SimpleStringProperty();
    private StringProperty estado = new SimpleStringProperty();




    public Familiar(String idFamiliar, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                       String direccion, String telFamiliar, String estado) {
        this.idFamiliar.set(idFamiliar);
        this.primerNombre.set(primerNombre);
        this.segundoNombre.set(segundoNombre);
        this.primerApellido.set(primerApellido);
        this.segundoApellido.set(segundoApellido);
        this.direccion.set(direccion);
        this.telFamiliar.set(telFamiliar);
        this.estado.set(estado);

    }

    public Familiar() {
        this("","","","","","","","");
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

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty DireccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelFamiliar() {
        return telFamiliar.get();
    }

    public StringProperty telFamiliarProperty() {
        return telFamiliar;
    }

    public void setTelFamiliar(String telFamiliar) {
        this.telFamiliar.set(telFamiliar);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    @Override
    public String toString() {
        return (idFamiliar.get());
    }

}
