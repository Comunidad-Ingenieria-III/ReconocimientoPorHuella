package eps.dao;

import conexionBD.ConexionRoot;
import eps.dto.DtoEps;
import institucionAcademica.dto.InstitucionAcademica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEps {

    private DtoEps dtoeps;
    private List<DtoEps> dtoepss;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public List<DtoEps> cargarEps() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from eps";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            dtoepss = new ArrayList<>();

            while (rset.next()) {
                dtoeps = new DtoEps();
                dtoeps.setIdEps(rset.getString("idEps"));
                dtoeps.setNombreEps(rset.getString("nombreEps"));
                dtoeps.setdireccionEps(rset.getString("direccionEps"));
                dtoeps.setTelEps(rset.getString("telEps"));
                dtoepss.add(dtoeps);

            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return dtoepss;
    }

    public int agregarEps(DtoEps dtoEps) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into eps(idEps, nombreEps, direccionEps, telEps) values (?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setString(1, dtoEps.getIdEps());
            stmt.setString(2, dtoEps.getNombreEps());
            stmt.setString(3, dtoEps.getdireccionEps());
            stmt.setString(4, dtoEps.getTelEps());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public int modificarEps(DtoEps dtoEps) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update eps set nombreEps = ?, direccionEps = ?, telEps = ?  where idEps = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, dtoEps.getNombreEps());
            stmt.setString(2, dtoEps.getdireccionEps());
            stmt.setString(3, dtoEps.getTelEps());

            stmt.setString(4, dtoEps.getIdEps());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    public int eliminarEps(String idEps) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from eps where idEps = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idEps);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()

    public DtoEps buscarPorId(String idEps) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from eps where idEps = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idEps);
            rset = stmt.executeQuery();

            if (rset.next()) {

                dtoeps.setIdEps(rset.getString("idEps"));
                dtoeps.setNombreEps(rset.getString("nombreEps"));
                dtoeps.setdireccionEps(rset.getString("direccionEps"));
                dtoeps.setTelEps(rset.getString("telEps"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return dtoeps;
    }
}
