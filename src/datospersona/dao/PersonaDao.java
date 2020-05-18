package datospersona.dao;

import conexionBD.ConexionRoot;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.BusquedaDeFamiliar;
import datospersona.dto.Persona;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eps.dto.DtoEps;
import eps.formularioeps.EPS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import javax.swing.JOptionPane;

public class PersonaDao {

    private Persona persona;
    private Familiar familiar;
    private DtoEps eps;
    private List<Persona> personas;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public List<Persona> cargarPersona() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona ";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            personas = new ArrayList<>();

            while (rset.next()) {

                persona = new Persona();

                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setDireccion(rset.getString("direccion"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                persona.setHuella((ByteArrayInputStream) rset.getBinaryStream("huella"));
                persona.setHuella1(rset.getInt("huella1"));
                persona.setEstado(rset.getBoolean("estado"));

                personas.add(persona);
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return personas;
    }

    public Persona ConsultaRegistroAtencion(String idenpersona) {


        try {
            conn = ConexionRoot.getConexion();
            String sql = "SELECT p.idpersona, p.primerNombre, p.primerApellido, p.fechaNacimiento, p.alergicoA, p.enfermedadSufre, p.observaciones, p.huella,\n" +
                    "ep.nombreEps AS nombreEps,\n" +
                    "f.nombre1, f.telefono as telefono \n" +
                    "FROM persona_familiar AS pf\n" +
                    "INNER JOIN datos_persona AS p ON p.idpersona = pf.idpersona\n" +
                    "INNER JOIN familiar_paciente AS f ON f.idFamiliar = pf.idFamiliar\n" +
                    "INNER JOIN eps AS ep ON p.idEps = ep.idEps\n" +
                    "WHERE p.idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idenpersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                persona = new Persona();
                familiar = new Familiar();
                eps = new DtoEps();

                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                eps.setNombreEps(rset.getString("nombreEps"));
                familiar.setPrimerNombre(rset.getString("nombre1"));
                familiar.setTelFamiliar(rset.getString("telefon"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));

            }
        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return persona;
    }

    public BusquedaDeFamiliar buscarPersonalPorId(String idPersona) {//funcion que permite la busqueda de un personal de salud, junto con los
        //registros que le pertenezcan en la tabla personal-salud-titulo.
        Per_Fami_Dto per_fami_dto;
        List<Per_Fami_Dto> listaFamiliares = null;
        Persona persona = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                persona = new Persona();

                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setDireccion(rset.getString("direccion"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                persona.setHuella((ByteArrayInputStream) rset.getBinaryStream("huella"));
                persona.setHuella1(rset.getInt("huella1"));
                persona.setTipoDocumento(rset.getString("idTipoDocumento"));
                persona.setIdEps(rset.getString("idEps"));
                persona.setEstado(rset.getBoolean("estado"));
            }

            String sql2 = "select * from persona_familiar where idpersona = ?";
            stmt = conn.prepareStatement(sql2);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            listaFamiliares = new ArrayList<>();
            while (rset.next()) {
                per_fami_dto = new Per_Fami_Dto();

                per_fami_dto.setIdPersona(rset.getString("idpersona"));
                per_fami_dto.setIdFamiliar(rset.getString("idFamiliar"));
                per_fami_dto.setFechaIngreso(rset.getDate("fechaIngreso"));
                per_fami_dto.setEstado(rset.getBoolean("estado"));

                listaFamiliares.add(per_fami_dto);
            }


        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return new BusquedaDeFamiliar(listaFamiliares, persona);
    }

    public Persona buscarPersonalPorIdPersona(String idPersona) {//funcion que permite la busqueda de un personal de salud, junto con los
        //registros que le pertenezcan en la tabla personal-salud-titulo.

        Persona persona = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                persona = new Persona();

                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setDireccion(rset.getString("direccion"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                persona.setHuella((ByteArrayInputStream) rset.getBinaryStream("huella"));
                persona.setHuella1(rset.getInt("huella1"));
                persona.setTipoDocumento(rset.getString("idTipoDocumento"));
                persona.setIdEps(rset.getString("idEps"));
                persona.setEstado(rset.getBoolean("estado"));
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return persona;
    }

    public boolean buscarIdPersonaEstado(String idPersona) {
        boolean consulta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select estado, idpersona from datos_persona where idpersona = ? and estado = 0";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
               consulta = true;
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return consulta;
    }

    public boolean buscarPrimaryKeyPersona(String idPersona) {//Funcion para realizar la busqueda de un personal de salud por medio de su perimary key
        boolean trpta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersona);
            rset = stmt.executeQuery();

            if (rset.next()) {
                trpta = true;
            }
        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return trpta;
    }

    public List<Persona> buscarPorNombre(String primerNombre) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where primerNombre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(2, primerNombre);
            rset = stmt.executeQuery();


            if (rset.next()) {
                persona = new Persona();

                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                persona.setTipoDocumento(rset.getString("idTipoDocumento"));

            }
        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return personas;
    }

    public int agregarPersona(Persona persona) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into datos_persona(idpersona, primerNombre, segundoNombre, primerApellido, segundoApellido," +
                    "fechaNacimiento, direccion, sexo, alergicoA, enfermedadSufre, observaciones, huella, huella1, idTipoDocumento, idEps, estado)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setString(1, persona.getIdpersona());
            stmt.setString(2, persona.getPrimerNombre());
            stmt.setString(3, persona.getSegundoNombre());
            stmt.setString(4, persona.getPrimerApellido());
            stmt.setString(5, persona.getSegundoApellido());
            stmt.setDate(6, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            stmt.setString(7, persona.getDireccion());
            stmt.setString(8, persona.getSexo());
            stmt.setString(9, persona.getAlergicoA());
            stmt.setString(10, persona.getEnfermedadSufre());
            stmt.setString(11, persona.getObservaciones());
            stmt.setBinaryStream(12, persona.getHuella());
            stmt.setInt(13, persona.getHuella1());
            stmt.setString(14, persona.getTipoDocumento());
            stmt.setString(15, persona.getIdEps());
            stmt.setBoolean(16, persona.isEstado());


            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 1;
    }

    public int modificar(Persona persona) {
        try {
            int x = 0;
            conn = ConexionRoot.getConexion();
            String sql = "update datos_persona set idpersona = ?, primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?,"
                    + " fechaNacimiento = ?, direccion = ?, sexo = ?, alergicoA = ?, enfermedadSufre = ?, observaciones = ? where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, persona.getIdpersona());
            stmt.setString(2, persona.getPrimerNombre());
            stmt.setString(3, persona.getSegundoNombre());
            stmt.setString(4, persona.getPrimerApellido());
            stmt.setString(5, persona.getSegundoApellido());
            stmt.setDate(6, persona.getFechaNacimiento());
            stmt.setString(7, persona.getDireccion());
            stmt.setString(8, persona.getSexo());
            stmt.setString(9, persona.getAlergicoA());
            stmt.setString(10, persona.getEnfermedadSufre());
            stmt.setString(11, persona.getObservaciones());
            stmt.setString(12, persona.getIdpersona());

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 1;
    }

    public int eliminar(int idCliente) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 1;
    }

    public boolean inhabilitarPersona(String idpersona) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {
            conn = ConexionRoot.getConexion();
            //Con este query buscamos si existen registros que coincidan entre la tabla personal_salud y personal_salud_titulo por medio del idPersonal
            String sql = "SELECT p.idpersona ,p.idFamiliar ,ps.idpersona as relacion from persona_familiar AS p " +
                    "INNER JOIN datos_persona AS ps ON p.idpersona=ps.idpersona where ps.idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idpersona);
            rset = stmt.executeQuery();

            if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                yes = true;
            } else {//Si el Id que se envía como parametro no tiene registros dependientes, actualiza el estado a false = inactivo en la BBDD
                String sql1 = "update datos_persona set estado = ? where idpersona= ?";
                stmt = conn.prepareStatement(sql1);
                stmt.setBoolean(1, false);
                stmt.setString(2, idpersona);
                stmt.executeUpdate();
                yes = false;
            }

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return yes;
    }

}

