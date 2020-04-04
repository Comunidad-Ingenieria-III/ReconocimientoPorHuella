package tipoTituloAcademico.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TtAcademico {
    private StringProperty idTipoTituloAcademico = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public TtAcademico(String idTipoTituloAcademico, String nombre){
        this.idTipoTituloAcademico.set(idTipoTituloAcademico);
        this.nombre.set(nombre);
    }
    public TtAcademico(){
        this("","");
    }

    public String getIdTipoTituloAcademico() {
        return idTipoTituloAcademico.get();
    }

    public StringProperty idTipoTituloAcademicoProperty() {
        return idTipoTituloAcademico;
    }

    public void setIdTipoTituloAcademico(String idTipoTituloAcademico) {
        this.idTipoTituloAcademico.set(idTipoTituloAcademico);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
}
