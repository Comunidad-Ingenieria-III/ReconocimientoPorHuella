package login.dtousuario;

import javafx.beans.property.*;
import perfil.dtoperfil.PerfilDto;

public class Usuario {

    private SimpleStringProperty idUsuario = new SimpleStringProperty();
    private SimpleStringProperty primerNombre = new SimpleStringProperty();
    private SimpleStringProperty segundoNombre = new SimpleStringProperty();
    private SimpleStringProperty primerApellido = new SimpleStringProperty();
    private SimpleStringProperty segundoApellido = new SimpleStringProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty contrasena = new SimpleStringProperty();
    private SimpleStringProperty idperfil = new SimpleStringProperty();
    private SimpleBooleanProperty estado = new SimpleBooleanProperty();
    private PerfilDto perfil;

    public Usuario(String username, String contrasena) {
        this.username.set(username);
        this.contrasena.set(contrasena);
    }

    public Usuario(String idUsuario, String primerNombre, String segundoNombre, String primerApellido,
                   String segundoApellido, String username, String contrasena, String idperfil, boolean estado) {
        this.idUsuario.set(idUsuario);
        this.primerNombre.set(primerNombre);
        this.segundoNombre.set(segundoNombre);
        this.primerApellido.set(primerApellido);
        this.segundoApellido.set(segundoApellido);
        this.username.set(username);
        this.contrasena.set(contrasena);
        this.idperfil.set(idperfil);
        this.estado.set(estado);
    }

    public Usuario() {
        this("", "", "", "", "", "", "", "", Boolean.parseBoolean(""));
    }

    public String getIdUsuario() {
        return idUsuario.get();
    }

    public SimpleStringProperty idUsuarioProperty() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario.set(idUsuario);
    }

    public String getPrimerNombre() {
        return primerNombre.get();
    }

    public SimpleStringProperty primerNombreProperty() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre.set(primerNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre.get();
    }

    public SimpleStringProperty segundoNombreProperty() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre.set(segundoNombre);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public SimpleStringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido.get();
    }

    public SimpleStringProperty segundoApellidoProperty() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido.set(segundoApellido);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getContrasena() {
        return contrasena.get();
    }

    public SimpleStringProperty contrasenaProperty() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena.set(contrasena);
    }

    public String getIdperfil() {
        return idperfil.get();
    }

    public SimpleStringProperty idperfilProperty() {
        return idperfil;
    }

    public void setIdperfil(String idperfil) {
        this.idperfil.set(idperfil);
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

    public PerfilDto getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDto perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", primerNombre=" + primerNombre +
                ", segundoNombre=" + segundoNombre +
                ", primerApellido=" + primerApellido +
                ", segundoApellido=" + segundoApellido +
                ", username=" + username +
                ", contrasena=" + contrasena +
                ", estado=" + estado +
                '}';
    }
}
