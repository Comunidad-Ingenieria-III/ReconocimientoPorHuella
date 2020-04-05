package personalSalud.personalsaluddao;

import conexionBD.ConexionRoot;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import personalSalud.personalsaluddto.PersonalSalud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalSaludDao {

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

                personalSalud.setIdPersonal(rset.getInt("idPersonal"));
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


    public boolean agregarPersonalSalud(PersonalSalud personalSalud) throws SQLException {
        //try {
        /*conn = ConexionRoot.getConexion();
        String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, tipoDocumento, cargo)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, personalSalud.getIdPersonal());
        stmt.setString(2, personalSalud.getNombre1());
        stmt.setString(3, personalSalud.getNombre2());
        stmt.setString(4, personalSalud.getApellido1());
        stmt.setString(5, personalSalud.getApellido2());
        stmt.setString(6, personalSalud.getSexo());
        stmt.setString(7, personalSalud.getTelefono());
        stmt.setString(8, personalSalud.getEmail());
        stmt.setString(9, personalSalud.getTipoDocumento());
        stmt.setString(10, personalSalud.getCargo());*/

        String query = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, tipoDocumento, cargo)" +
                " values('"+personalSalud.getIdPersonal()+"','"
                +personalSalud.getNombre1()+"','"
                +personalSalud.getNombre2()+"','"
                +personalSalud.getApellido1()+"','"
                +personalSalud.getApellido2()+"','"
                +personalSalud.getSexo()+"','"
                +personalSalud.getTelefono()+"','"
                +personalSalud.getEmail()+"','"
                +personalSalud.getCargo()+"','"
                +personalSalud.getCargo()+"')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;



        //return stmt.executeUpdate();

        /*} catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());

        }*/
    } // Fin del método agregar()


    public boolean modificarPersonalSalud(PersonalSalud personalSalud)  {
        /*try {
            conn = ConexionRoot.getConexion();
            String sql = "update personal_salud set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, " +
                    " sexo = ?, telefono = ?, email = ?, tipoDocumento = ?, cargo = ? where idPersonal = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, personalSalud.getIdPersonal());
            stmt.setString(2, personalSalud.getNombre1());
            stmt.setString(3, personalSalud.getNombre2());
            stmt.setString(4, personalSalud.getApellido1());
            stmt.setString(5, personalSalud.getApellido2());
            stmt.setString(6, personalSalud.getSexo());
            stmt.setString(7, personalSalud.getTelefono());
            stmt.setString(8, personalSalud.getEmail());
            stmt.setString(9, personalSalud.getTipoDocumento());
            stmt.setString(10, personalSalud.getCargo());

            stmt.setInt(11, personalSalud.getIdPersonal());*/

            String query = "UPDATE personal_salud set " +

                    "nombre1 = '" + personalSalud.getNombre1() + "','" +
                    "nombre2 = '" + personalSalud.getNombre2() + "','" +
                    "apellido1 = '" + personalSalud.getApellido1() + "','" +
                    "apellido2 = '" + personalSalud.getApellido2() + "','" +
                    "sexo = '" + personalSalud.getSexo() + "','" +
                    "telefono = '" + personalSalud.getTelefono() + "','" +
                    "email = '" + personalSalud.getEmail() + "','" +
                    "tipoDocumento = '" + personalSalud.getTipoDocumento() + "','" +
                    "cargo = '" + personalSalud.getCargo() + "')" +
                    "WHERE idPersona = '" + personalSalud.getIdPersonal();
            JdbcHelper jdbc = new JdbcHelper();
            boolean exito = jdbc.ejecutarQuery(query);
            return exito;


            /*return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
        return false;*/
    }

    public int eliminarPersonalSalud(int idPersonal) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from personal_salud where idPersonal = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idPersonal);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
