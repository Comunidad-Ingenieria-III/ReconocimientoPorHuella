package datospersona.dao;

import conexionBD.ConexionRoot;
import datospersona.dto.BusquedaDeFamiliar;
import datospersona.dto.Persona;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import javax.swing.JOptionPane;

public class PersonaDao {

    private Persona persona;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public Persona buscarPorId(String idenpersona) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idenpersona);
            rset = stmt.executeQuery();

            if (rset.next()) {


                persona.setIdpersona(rset.getString("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                //persona.setSegundoNombre(rset.getString("segundoNombre"));
                //persona.setPrimerApellido(rset.getString("primerApellido"));
                //persona.setSegundoApellido(rset.getString("segundoApellido"));
                //persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                //persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                //stmt.setBinaryStream(12,persona.getHuella());
                //stmt.setInt(13,persona.getHuella1());

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return persona;
    }

    public BusquedaDeFamiliar buscarPersonalPorId(String idPersona){//funcion que permite la busqueda de un personal de salud, junto con los
        //registros que le pertenezcan en la tabla personal-salud-titulo.
        Per_Fami_Dto per_fami_dto;
        List<Per_Fami_Dto> listaFamiliares = null;
        Persona persona = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idPersona);
            rset = stmt.executeQuery();

            if (rset.next()){
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
            }

            String sql2 = "select * from persona_familiar where idpersona = ?";
            stmt = conn.prepareStatement(sql2);
            stmt.setString(1,idPersona);
            rset = stmt.executeQuery();

            listaFamiliares = new ArrayList<>();
            while (rset.next()){
                per_fami_dto = new Per_Fami_Dto();

                per_fami_dto.setIdPersona(rset.getString("idpersona"));
                per_fami_dto.setIdFamiliar(rset.getString("idFamiliar"));
                per_fami_dto.setFechaIngreso(rset.getDate("fechaIngreso"));

                listaFamiliares.add(per_fami_dto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BusquedaDeFamiliar(listaFamiliares, persona);
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
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return personas;
    }

    public void agregarPersona(Persona persona) throws RuntimeException {
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


            int rta = stmt.executeUpdate();
            if (rta != 1) {
                throw new RuntimeException("Error al agregar!");
            }

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException("Error SQL - Agregar()!");
        }
    }

    public void modificar(Persona persona) {
        try {
            int x = 0;
            conn = ConexionRoot.getConexion();
            String sql = "update datos_persona set idpersona = ?, primerNombre = ?, segundoNombre = ?, primerApellido = ?, segundoApellido = ?,"
                    + " fechaNacimiento = ?, sexo = ?, alergicoA = ?, enfermedadSufre = ?, observaciones = ? where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, persona.getIdpersona());
            stmt.setString(2, persona.getPrimerNombre());
            stmt.setString(3, persona.getSegundoNombre());
            stmt.setString(4, persona.getPrimerApellido());
            stmt.setString(5, persona.getSegundoApellido());
            stmt.setDate(6, persona.getFechaNacimiento());
            stmt.setString(7, persona.getSexo());
            stmt.setString(8, persona.getAlergicoA());
            stmt.setString(9, persona.getEnfermedadSufre());
            stmt.setString(10, persona.getObservaciones());
            stmt.setString(11, persona.getIdpersona());

            int rta = stmt.executeUpdate();
            if (rta != 1) {
                throw new RuntimeException("Error al actualizar!");
            } else {
                JOptionPane.showMessageDialog(null, "Registro Modificado Exitosamente", "INFORMACIÓN", 1);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - actualizar()!");
        }

    }

    public void eliminar(int idCliente) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);

            int rta = stmt.executeUpdate();
            if (rta != 1) {
                throw new RuntimeException("Error al eliminar!");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro Fue Eliminado Exitosamente ", "INFORMACIÓN", 1);
            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - eliminar()!");
        }
    }

}

