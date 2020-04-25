package datosFamiliar.daofamiliar;

import conexionBD.ConexionRoot;
import datosFamiliar.dtofamiliar.Familiar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamiliarDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private Familiar familiar;
    private List<Familiar> familiares;

    public List<Familiar> listarTodos() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from familiar_paciente where estado= 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            familiares = new ArrayList<>();

            while (rset.next()) {
                familiar = new Familiar();

                familiar.setIdFamiliar(rset.getString("idFamiliar"));
                familiar.setPrimerNombre(rset.getString("nombre1"));
                familiar.setSegundoNombre(rset.getString("nombre2"));
                familiar.setPrimerApellido(rset.getString("apellido1"));
                familiar.setSegundoApellido(rset.getString("apellido2"));
                familiar.setDireccion(rset.getString("direccion"));
                familiar.setTelFamiliar(rset.getString("telefono"));

                familiares.add(familiar);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return familiares;
    } // Fin del método obtenerTodos()

    public List<Familiar> buscar(String buscar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from familiar_paciente where idFamiliar LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            familiares = new ArrayList<>();

            while (rset.next()) {
                familiar = new Familiar();

                familiar.setIdFamiliar(rset.getString("idFamiliar"));
                familiar.setPrimerNombre(rset.getString("nombre1"));
                familiar.setSegundoNombre(rset.getString("nombre2"));
                familiar.setPrimerApellido(rset.getString("apellido1"));
                familiar.setSegundoApellido(rset.getString("apellido2"));
                familiar.setDireccion(rset.getString("direccion"));
                familiar.setTelFamiliar(rset.getString("telefono"));
                familiar.setEstado(rset.getString("estado"));
                familiares.add(familiar);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - buscarPoridFamiliar()!");
        }
        return familiares;
    } // Fin del método obtenerTodos()


    public int agregar(Familiar familiar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into familiar_paciente(idFamiliar, nombre1, nombre2, apellido1, apellido2, direccion, telefono, estado)" +
                         " values(?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, familiar.getIdFamiliar());
            stmt.setString(2, familiar.getPrimerNombre());
            stmt.setString(3, familiar.getSegundoNombre());
            stmt.setString(4, familiar.getPrimerApellido());
            stmt.setString(5, familiar.getSegundoApellido());
            stmt.setString(6, familiar.getDireccion());
            stmt.setString(7, familiar.getTelFamiliar());
            stmt.setInt(8, Integer.parseInt(familiar.getEstado()));

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()

    public int modificar(Familiar familiar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update familiar_paciente set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, " +
                    " direccion = ?, telefono = ? where idFamiliar = ?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, familiar.getPrimerNombre());
            stmt.setString(2, familiar.getSegundoNombre());
            stmt.setString(3, familiar.getPrimerApellido());
            stmt.setString(4, familiar.getSegundoApellido());
            stmt.setString(5, familiar.getDireccion());
            stmt.setString(6, familiar.getTelFamiliar());

            stmt.setString(7, familiar.getIdFamiliar());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarr(int idFamiliar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from familiar_paciente where idFamiliar = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idFamiliar);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean eliminar(String idFamiliar) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idpersona ,p.idFamiliar ,ps.idFamiliar as relacion from persona_familiar AS p " +
                        "INNER JOIN familiar_paciente AS ps ON p.idFamiliar=ps.idFamiliar where ps.idFamiliar = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idFamiliar);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update familiar_paciente set estado = 0 where idFamiliar = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idFamiliar);
                    stmt.executeUpdate();
                    yes = false;


                }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }




}
