package datospersona.dao;

import conexionBD.ConexionRoot;
import datospersona.dto.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.JOptionPane;

public class PersonaDao {

    private Persona persona;
    private ObservableList<Persona> personas;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public ObservableList<Persona> cargarPersona() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            personas = FXCollections.observableArrayList();

            while (rset.next()) {

                persona = new Persona();

                persona.setIdpersona(rset.getInt("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                stmt.setBinaryStream(12,persona.getHuella());
                stmt.setInt(13,persona.getHuella1());

                personas.add(persona);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return personas;
    }

    public Persona buscarPorId(int idenpersona) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where huella1 = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idenpersona);
            rset = stmt.executeQuery();

            if (rset.next()) {


                persona.setIdpersona(rset.getInt("idpersona"));
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
                stmt.setInt(13,persona.getHuella1());

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return persona;
    }

    public ObservableList<Persona> buscarPorNombre(String primerNombre) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from datos_persona where primerNombre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(2, primerNombre);
            rset = stmt.executeQuery();


            if (rset.next()) {
                persona = new Persona();

                persona.setIdpersona(rset.getInt("idpersona"));
                persona.setPrimerNombre(rset.getString("primerNombre"));
                persona.setSegundoNombre(rset.getString("segundoNombre"));
                persona.setPrimerApellido(rset.getString("primerApellido"));
                persona.setSegundoApellido(rset.getString("segundoApellido"));
                persona.setFechaNacimiento(rset.getDate("fechaNacimiento"));
                persona.setSexo(rset.getString("sexo"));
                persona.setAlergicoA(rset.getString("alergicoA"));
                persona.setEnfermedadSufre(rset.getString("enfermedadSufre"));
                persona.setObservaciones(rset.getString("observaciones"));
                persona.setTipoDocumeto(rset.getInt("idTipoDocumento"));

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
                    "fechaNacimiento, direccion, sexo, alergicoA, enfermedadSufre, observaciones, huella, huella1, idTipoDocumento, idEps)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setInt(1, persona.getIdpersona());
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
            stmt.setInt(14, persona.getTipoDocumento());
            stmt.setInt(15, persona.getidEps());


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
            stmt.setInt(1, persona.getIdpersona());
            stmt.setString(2, persona.getPrimerNombre());
            stmt.setString(3, persona.getSegundoNombre());
            stmt.setString(4, persona.getPrimerApellido());
            stmt.setString(5, persona.getSegundoApellido());
            stmt.setDate(6, persona.getFechaNacimiento());
            stmt.setString(7, persona.getSexo());
            stmt.setString(8, persona.getAlergicoA());
            stmt.setString(9, persona.getEnfermedadSufre());
            stmt.setString(10, persona.getObservaciones());
            stmt.setInt(11, persona.getIdpersona());

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

