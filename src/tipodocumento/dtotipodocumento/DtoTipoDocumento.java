package tipodocumento.dtotipodocumento;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DtoTipoDocumento {

    private IntegerProperty idTipoDocumento = new SimpleIntegerProperty();
    private StringProperty nombreTipoDocumento = new SimpleStringProperty();

    public DtoTipoDocumento(int idTipoDocumento, String nombreTipoDocumento) {
        this.idTipoDocumento.set(idTipoDocumento);
        this.nombreTipoDocumento.set(nombreTipoDocumento);
    }

    public DtoTipoDocumento() {
        this(0,"");
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento.get();
    }

    public IntegerProperty idTipoDocumentoProperty() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
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