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


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private PersonalSalud personalSalud;
    private List<PersonalSalud> personas;
    private PsDto psDto;
    private List<PsDto> listaPs;

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


    public List<PsDto> listaPsdto() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo join personal_salud on personal_salud_titulo.idPersonal = personal_salud.idPersonal ";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            listaPs = new ArrayList<>();

            while (rset.next()) {

                psDto = new PsDto();
                //psDto.setId(rset.getInt("idPst"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));

                listaPs.add(psDto);

            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return listaPs;
    }


    public int agregarPersonal(PersonalSalud personalSalud, PsDto psDto) throws SQLException {
        Connection conflito = conn;

        try {

            conn = ConexionRoot.getConexion();
            conn.setAutoCommit(false);
            String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, tipoDocumento, cargo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            stmt.executeUpdate(sql);

            String sql_1 = "insert into personal_salud_titulo(idPst, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion) values(?, ?, ?, ?, ?)";

            stmt.setInt(1, psDto.getId());
            stmt.setString(2, psDto.getIdPersonal());
            stmt.setString(3, psDto.getIdTipoTitu());
            stmt.setString(4, psDto.getIdInstitucion());
            stmt.setDate(5, new java.sql.Date(psDto.getFechaTitulacion().getTime()));
            stmt.executeUpdate(sql_1);

            JOptionPane.showMessageDialog(null,"datos insertados correctamenti");


            stmt = conn.prepareStatement(sql);
            stmt = conn.prepareStatement(sql_1);

            conn.commit();
            JOptionPane.showMessageDialog(null, "Se ejecutó la transaccion corectamente");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar " + conflito + ": " + ex);

            conn.rollback();
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Algo salio mal");

        }
        return 0;
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


        /*String query2 = "insert into personal_salu_titulo(idPst, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion)" +
                " values('" + psDto.getId() + "','"
                + psDto.getFechaTitulacion() + "','"
                + psDto.getIdPersonal() + "','"
                + psDto.getIdTipoTitu() + "','"
                + psDto.getIdInstitucion() + "','";*/

        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        //boolean exito2 = jdbc.ejecutarQuery(query2);*/


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

    public PsDto buscarPorId(PsDto psDto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo where idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(psDto));
            rset = stmt.executeQuery();

            if (rset.next()) {
                psDto = new PsDto();

                //psDto.setId(rset.getInt("idPst"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return psDto;
    }

    public int eliminarPersonalSalud(String idCliente) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idCliente);

            return stmt.executeUpdate();

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - eliminar()!");
        }

    }


}
