package tipodocumento.dtotipodocumento;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DtoTipoDocumento {

    private StringProperty idTipoDocumento = new SimpleStringProperty();
    private StringProperty nombreTipoDocumento = new SimpleStringProperty();
    private StringProperty estado = new SimpleStringProperty();

    public DtoTipoDocumento(String idTipoDocumento, String nombreTipoDocumento, String estado) {
        this.idTipoDocumento.set(idTipoDocumento);
        this.nombreTipoDocumento.set(nombreTipoDocumento);
        this.estado.set(estado);
    }

    public DtoTipoDocumento() {
        this("", "","");
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento.get();
    }

    public StringProperty idTipoDocumentoProperty() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento.set(idTipoDocumento);
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento.get();
    }

    public StringProperty nombreTipoDocumentoProperty() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento.set(nombreTipoDocumento);
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
        return nombreTipoDocumento.get();
    }
}