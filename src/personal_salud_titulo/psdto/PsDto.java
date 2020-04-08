package personal_salud_titulo.psdto;

import institucionAcademica.dto.InstitucionAcademica;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import personalSalud.personalsaluddto.PersonalSalud;
import tipoTituloAcademico.dto.TtAcademico;

import java.sql.Date;
import java.time.LocalDate;

public class PsDto {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty idPersonal = new SimpleStringProperty();
    private StringProperty idTipoTitu = new SimpleStringProperty();
    private StringProperty idInstitucion = new SimpleStringProperty();
    private Date fechaTitulacion;


    public PsDto(int id, String idPersonal, String idTipoTitu, String idInstitucion, Date fechaTitulacion) {

        this.id.set(id);
        this.idPersonal.set(idPersonal);
        this.idTipoTitu.set(idTipoTitu);
        this.idInstitucion.set(idInstitucion);
        this.fechaTitulacion = fechaTitulacion;
    }


   public PsDto() {
        this(0,"", "", "", new Date(new java.util.Date().getTime()));
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getIdPersonal() {
        return idPersonal.get();
    }

    public StringProperty idPersonalProperty() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal.set(idPersonal);
    }

    public String getIdTipoTitu() {
        return idTipoTitu.get();
    }

    public StringProperty idTipoTituProperty() {
        return idTipoTitu;
    }

    public void setIdTipoTitu(String idTipoTitu) {
        this.idTipoTitu.set(idTipoTitu);
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

    public Date getFechaTitulacion() {
        return fechaTitulacion;
    }

    public void setFechaTitulacion(Date fechaTitulacion) {
        this.fechaTitulacion = fechaTitulacion;
    }

    @Override
    public String toString() {
        return "PsDto{" +
                "id=" + id +
                ", idPersonal=" + idPersonal +
                ", idTipoTitu=" + idTipoTitu +
                ", idInstitucion=" + idInstitucion +
                ", fechaTitulacion=" + fechaTitulacion +
                '}';
    }
}
