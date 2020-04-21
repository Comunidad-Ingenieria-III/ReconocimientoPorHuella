package personal_salud_titulo.psdto;

import institucionAcademica.dto.InstitucionAcademica;
import javafx.beans.property.*;
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
    private BooleanProperty estado = new SimpleBooleanProperty();


    public PsDto(int id, String idPersonal, String idTipoTitu, String idInstitucion, Date fechaTitulacion, boolean estado) {

        this.id.set(id);
        this.idPersonal.set(idPersonal);
        this.idTipoTitu.set(idTipoTitu);
        this.idInstitucion.set(idInstitucion);
        this.fechaTitulacion = fechaTitulacion;
        this.estado.set(estado);
    }


   public PsDto() {
        this(0,"", "", "", new Date(new java.util.Date().getTime()),false);
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

    public boolean isEstado() {
        return estado.get();
    }

    public BooleanProperty estadoProperty() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado.set(estado);
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
