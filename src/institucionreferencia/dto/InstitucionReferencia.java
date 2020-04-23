package institucionreferencia.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InstitucionReferencia {

    private StringProperty idInstitucion =  new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty telefono = new SimpleStringProperty();
    private StringProperty estado = new SimpleStringProperty();

    public InstitucionReferencia(String idInstitucion, String nombre, String direccion, String telefono,String estado) {
        this.idInstitucion.set(idInstitucion);
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.telefono.set(telefono);
        this.estado.set(estado);
    }

    public InstitucionReferencia() {
        this("","","","","");
    }

    public String getIdInstitucion() {
        return idInstitucion.get();
    }

    public StringProperty idInstitucionProperty() {
        return idInstitucion;
    }

    public void setIdInstitucion(String idInstitucion) {
        this.idInstitucion.set(idInstitucion);
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

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
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
        return "InstitucionReferencia{" +
                "idInstitucion=" + idInstitucion +
                ", nombre=" + nombre +
                ", direccion=" + direccion +
                ", telefono=" + telefono +
                '}';
    }
}
