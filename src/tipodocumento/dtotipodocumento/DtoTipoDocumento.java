package tipodocumento.dtotipodocumento;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DtoTipoDocumento {

    private StringProperty idTipoDocumento = new SimpleStringProperty();
    private StringProperty nombreTipoDocumento = new SimpleStringProperty();

    public DtoTipoDocumento(String idTipoDocumento, String nombreTipoDocumento) {
        this.idTipoDocumento.set(idTipoDocumento);
        this.nombreTipoDocumento.set(nombreTipoDocumento);
    }

    public DtoTipoDocumento() {
        this("", "");
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

    @Override
    public String toString() {
        return nombreTipoDocumento.get();
    }
}