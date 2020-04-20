package perfil.dtoperfil;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PerfilDto {

    private StringProperty idperfil =  new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private SimpleBooleanProperty estado = new SimpleBooleanProperty();

    public PerfilDto(String idperfil, String nombre, boolean estado) {
        this.idperfil.set(idperfil);
        this.nombre.set(nombre);
        this.estado.set(estado);
    }

    public PerfilDto() {
        this("","", Boolean.parseBoolean(""));
    }

    public String getIdperfil() {
        return idperfil.get();
    }

    public StringProperty idperfilProperty() {
        return idperfil;
    }

    public void setIdperfil(String idperfil) {
        this.idperfil.set(idperfil);
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

    public boolean isEstado() {
        return estado.get();
    }

    public SimpleBooleanProperty estadoProperty() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado.set(estado);
    }

    @Override
    public String toString() {
        return nombre.get();
    }
}
