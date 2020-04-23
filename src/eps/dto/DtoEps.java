package eps.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DtoEps {

    private StringProperty idEps = new SimpleStringProperty();
    private StringProperty nombreEps = new SimpleStringProperty();
    private StringProperty direccionEps = new SimpleStringProperty();;
    private StringProperty telEps = new SimpleStringProperty();
    private StringProperty estado = new SimpleStringProperty();
    public DtoEps() {
    }

    public DtoEps(String idEps, String nombreEps, String direccionEps, String telEps,String estado) {
        this.idEps.set(idEps);
        this.nombreEps.set(nombreEps);
        this.direccionEps.set(direccionEps);
        this.telEps.set(telEps);
        this.estado.set(estado);

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

    public String getIdEps() {
        return idEps.get();
    }

    public StringProperty idEpsProperty() {
        return idEps;
    }

    public void setIdEps(String idEps) {
        this.idEps.set(idEps);
    }

    public String getNombreEps() {
        return nombreEps.get();
    }

    public StringProperty nombreEpsProperty() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps.set(nombreEps);
    }

    public String getdireccionEps() {
        return direccionEps.get();
    }

    public StringProperty direccionEpsProperty() {
        return direccionEps;
    }

    public void setdireccionEps(String direccionEps) {
        this.direccionEps.set(direccionEps);
    }

    public String getTelEps() {
        return telEps.get();
    }

    public StringProperty telEpsProperty() {
        return telEps;
    }

    public void setTelEps(String telEps) {
        this.telEps.set(telEps);
    }

    @Override
    public String toString() {
        return nombreEps.get();
    }
}
