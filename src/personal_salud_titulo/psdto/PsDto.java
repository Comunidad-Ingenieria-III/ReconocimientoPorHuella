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

    private IntegerProperty id;
    private IntegerProperty idPersonal;
    private StringProperty idTipoTitu;
    private StringProperty idInstitucion;
    private Date fechaTitulacion;


    public PsDto(int id, int idPersonal, String idTipoTitu, String idInstitucion, Date fechaTitulacion) {

        this.id = new SimpleIntegerProperty(id);
        this.idPersonal = new SimpleIntegerProperty(idPersonal);
        this.idTipoTitu = new SimpleStringProperty(idTipoTitu);
        this.idInstitucion = new SimpleStringProperty(idInstitucion);
        this.fechaTitulacion = fechaTitulacion;
    }

    public PsDto(int idPersonal, String idTipoTitu, String idInstitucion, Date fechaTitulacion) {

        this.idPersonal = new SimpleIntegerProperty(idPersonal);
        this.idTipoTitu = new SimpleStringProperty(idTipoTitu);
        this.idInstitucion = new SimpleStringProperty(idInstitucion);
        this.fechaTitulacion = fechaTitulacion;
    }

    //public PsDto(){}

   public PsDto() {
        this(0,0, "", "", Date.valueOf(""));
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

    public int getIdPersonal() {
        return idPersonal.get();
    }

    public IntegerProperty idPersonalProperty() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
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
