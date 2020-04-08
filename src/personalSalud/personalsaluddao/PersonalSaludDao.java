package personalSalud.personalsaluddao;

import conexionBD.ConexionRoot;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import institucionAcademica.dto.InstitucionAcademica;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalSaludDao {

    JdbcHelper jdbcHelper = new JdbcHelper();
    PsDto psDto = new PsDto();

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private PersonalSalud personalSalud;
    private List<PersonalSalud> personas;

    public List<PersonalSalud> listarPersonalSalud() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            personas = new ArrayList<>();

            while (rset.next()) {
                personalSalud = new PersonalSalud();

                personalSalud.setIdPersonal(rset.getString("idPersonal"));
                personalSalud.setNombre1(rset.getString("nombre1"));
                personalSalud.setNombre2(rset.getString("nombre2"));
                personalSalud.setApellido1(rset.getString("apellido1"));
                personalSalud.setApellido2(rset.getString("apellido2"));
                personalSalud.setSexo(rset.getString("sexo"));
                personalSalud.setTelefono(rset.getString("telefono"));
                personalSalud.setEmail(rset.getString("email"));
                personalSalud.setTipoDocumento(rset.getString("tipoDocumento"));
                personalSalud.setCargo(rset.getString("cargo"));

                personas.add(personalSalud);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return personas;
    } // Fin del método obtenerTodos()


    public int agregarPersonal(PersonalSalud personalSalud) throws SQLException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, tipoDocumento, cargo)" +
                    " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, personalSalud.getIdPersonal());
            stmt.setString(2, personalSalud.getNombre1());
            stmt.setString(3, personalSalud.getNombre2());
            stmt.setString(4, personalSalud.getApellido1());
            stmt.setString(5, personalSalud.getApellido2());
            stmt.setString(6, personalSalud.getSexo());
            stmt.setString(7, personalSalud.getTelefono());
            stmt.setString(8, personalSalud.getEmail());
            stmt.setString(9, personalSalud.getTipoDocumento());
            stmt.setString(10, personalSalud.getCargo());

            //return stmt.executeUpdate();

            String sql_1 = "insert into personal_salud_titulo(idPst, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion) values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql_1);
            stmt.setInt(1, psDto.getId());
            stmt.setString(2, psDto.getIdPersonal());
            stmt.setString(3, psDto.getIdTipoTitu());
            stmt.setString(4, psDto.getIdInstitucion());
            stmt.setDate(5, new java.sql.Date(psDto.getFechaTitulacion().getTime()));

            return stmt.executeUpdate();




        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()


    public boolean agregarPersonalSalud(PersonalSalud personalSalud) throws SQLException {

        String query = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, tipoDocumento, cargo)" +
                " values('" + personalSalud.getIdPersonal() + "','"
                + personalSalud.getNombre1() + "','"
                + personalSalud.getNombre2() + "','"
                + personalSalud.getApellido1() + "','"
                + personalSalud.getApellido2() + "','"
                + personalSalud.getSexo() + "','"
                + personalSalud.getTelefono() + "','"
                + personalSalud.getEmail() + "','"
                + personalSalud.getCargo() + "','"
                + personalSalud.getCargo() + "')";

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;

    }


    public Boolean modificarPersonalSalud(PersonalSalud personalSalud) {

        String sql = "UPDATE personal_salud SET nombre1 = '" + personalSalud.getNombre1() + "',"
                + "nombre2 = '" + personalSalud.getNombre2() + "',"
                + "apellido1 = '" + personalSalud.getApellido1() + "',"
                + "apellido2 = '" + personalSalud.getApellido2() + "',"
                + "sexo = '" + personalSalud.getSexo() + "',"
                + "telefono = '" + personalSalud.getTelefono() + "',"
                + "email = '" + personalSalud.getEmail()
                + " WHERE idPersonal = '" + personalSalud.getTipoDocumento();
        //+ "tipoDocumento = '" + Integer.parseInt(personalSalud.getTipoDocumento())+"',"
        //+ "cargo = '" + personalSalud.getCargo()


        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(sql);
        return exito;

    }

    public int modificarPersonal(PersonalSalud personalSalud) {
        try {
            conn = ConexionRoot.getConexion();

            String sql = "update personal_salud set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, sexo = ?," +
                    " telefono = ?, email = ?, tipoDocumento = ?, cargo = ?  where idPersonal = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, personalSalud.getNombre1());
            stmt.setString(2, personalSalud.getNombre2());
            stmt.setString(3, personalSalud.getApellido1());
            stmt.setString(4, personalSalud.getApellido2());
            stmt.setString(5, personalSalud.getSexo());
            stmt.setString(6, personalSalud.getTelefono());
            stmt.setString(7, personalSalud.getEmail());
            stmt.setString(8, personalSalud.getTipoDocumento());
            stmt.setString(9, personalSalud.getCargo());

            stmt.setString(10, String.valueOf(Integer.parseInt(String.valueOf(personalSalud.getIdPersonal()))));
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()

    /*public PersonalSalud buscarPersonalSalud(int idPersonal) {
        String query = "SELECT * FROM personal_salud WHERE idPersonal = " + idPersonal;
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs = jdbc.realizarConsulta(query);

        PersonalSalud personalSalud = null;

        try {
            if (rs.next()) {
                int id = idPersonal;
                String nombre1 = rs.getString("nombre1");
                String nombre2 = rs.getString("nombre2");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                //String sexo = rs.getString("sexo");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                //String tipoDocumento = rs.getString("tipoDocumento");
                //String cargo = rs.getString("cargo");

                personalSalud = new PersonalSalud(idPersonal, nombre1, nombre2, apellido1, apellido2,
                        telefono, email);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Personal de Salud: " + ex,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return personalSalud;
    }*/

    public void eliminarPersonalSalud(String idCliente) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idCliente);

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
