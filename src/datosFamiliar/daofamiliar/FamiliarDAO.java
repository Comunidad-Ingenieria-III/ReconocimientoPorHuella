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
            String sql = "select * from familiar_paciente";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            familiares = new ArrayList<>();

            while (rset.next()) {
                familiar = new Familiar();

                familiar.setIdFamiliar(rset.getInt("idFamiliar"));
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



    public int agregar(Familiar familiar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into familiar_paciente(idFamiliar, nombre1, nombre2, apellido1, apellido2, direccion, telefono)" +
                         " values(?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, familiar.getIdFamiliar());
            stmt.setString(2, familiar.getPrimerNombre());
            stmt.setString(3, familiar.getSegundoNombre());
            stmt.setString(4, familiar.getPrimerApellido());
            stmt.setString(5, familiar.getSegundoApellido());
            stmt.setString(6, familiar.getDireccion());
            stmt.setString(7, familiar.getTelFamiliar());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()

    public int modificar(Familiar familiar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update familiar_paciente set nombre1 = ?, set nombre2 = ?, set apellido1 = ?, set apellido2 = ?, " +
                    "set direccion = ?, set telefono = ? where idFamiliar = ?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, familiar.getPrimerNombre());
            stmt.setString(2, familiar.getSegundoNombre());
            stmt.setString(3, familiar.getPrimerApellido());
            stmt.setString(4, familiar.getSegundoApellido());
            stmt.setString(5, familiar.getDireccion());
            stmt.setString(6, familiar.getTelFamiliar());

            stmt.setInt(7, familiar.getIdFamiliar());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminar(int idFamiliar) {
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


}
