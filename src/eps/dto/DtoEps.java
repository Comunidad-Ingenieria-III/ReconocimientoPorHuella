package eps.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DtoEps {

    private IntegerProperty idEps = new SimpleIntegerProperty();
    private StringProperty nombreEps = new SimpleStringProperty();
    private StringProperty direccionEps = new SimpleStringProperty();;
    private IntegerProperty telEps = new SimpleIntegerProperty();

    public DtoEps() {
    }

    public DtoEps(int idEps, String nombreEps, String direccionEps, int telEps) {
        this.idEps.set(idEps);
        this.nombreEps.set(nombreEps);
        this.direccionEps.set(direccionEps);
        this.telEps.set(telEps);
    }

    public int getIdEps() {
        return idEps.get();
    }

    public IntegerProperty idEpsProperty() {
        return idEps;
    }

    public void setIdEps(int idEps) {
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

    public int gettelEps() {
        return telEps.get();
    }

    public IntegerProperty telEpsProperty() {
        return telEps;
    }

    public void settelEps(int telEps) {
        this.telEps.set(telEps);
    }

    @Override
    public String toString() {
        return nombreEps.get();
    }
}
