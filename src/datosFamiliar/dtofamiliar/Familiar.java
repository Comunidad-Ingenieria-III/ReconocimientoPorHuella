package datosFamiliar.dtofamiliar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Familiar {

    private IntegerProperty idFamiliar = new SimpleIntegerProperty();
    private StringProperty primerNombre = new SimpleStringProperty();
    private StringProperty segundoNombre = new SimpleStringProperty();
    private StringProperty primerApellido = new SimpleStringProperty();
    private StringProperty segundoApellido = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private StringProperty telFamiliar = new SimpleStringProperty();


    public Familiar(int idFamiliar, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                       String direccion, String telFamiliar) {
        this.idFamiliar.set(idFamiliar);
        this.primerNombre.set(primerNombre);
        this.segundoNombre.set(segundoNombre);
        this.primerApellido.set(primerApellido);
        this.segundoApellido.set(segundoApellido);
        this.direccion.set(direccion);
        this.telFamiliar.set(telFamiliar);

    }

    public Familiar() {
        this(0,"","","","","","");
    }

    public int getIdFamiliar() {
        return idFamiliar.get();
    }

    public IntegerProperty idFamiliarProperty() {
        return idFamiliar;
    }

    public void setIdFamiliar(int idFamiliar) {
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

}
