package login.dtousuario;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

    private IntegerProperty idUsuario;
    private StringProperty primerNombre;
    private StringProperty segundoNombre;
    private StringProperty primerApellido;
    private StringProperty segundoApellido;
    private StringProperty username;
    private StringProperty contrasena;

    public Usuario() {
    }

    public Usuario(String username, String contrasena) {
        this.username = new SimpleStringProperty(username);
        this.contrasena = new SimpleStringProperty(contrasena);
    }

    public Usuario(int idUsuario, String primerNombre, String segundoNombre, String primerApellido,
                   String segundoApellido, String username, String contrasena) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.primerNombre = new SimpleStringProperty(primerNombre);
        this.segundoNombre = new SimpleStringProperty(segundoNombre);
        this.primerApellido = new SimpleStringProperty(primerApellido);
        this.segundoApellido = new SimpleStringProperty(segundoApellido);
        this.username =  new SimpleStringProperty(username);;
        this.contrasena = new SimpleStringProperty(contrasena);
    }

    public int getIdUsuario() {
        return idUsuario.get();
    }

    public IntegerProperty idUsuarioProperty() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario.set(idUsuario);
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

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getContrasena() {
        return contrasena.get();
    }

    public StringProperty contrasenaProperty() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena.set(contrasena);
    }
}
