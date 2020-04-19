package cargo.dao;

import cargo.dto.Cargo;
import conexionBD.ConexionRoot;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private Cargo cargo;
    private List<Cargo> cargos;

    public List<Cargo> obtenerTodos() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from cargo";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            cargos = new ArrayList<>();

            while (rset.next()) {
                cargo = new Cargo();

                cargo.setIdCargo(rset.getString("idCargo"));
                cargo.setNombre(rset.getString("nombre"));
                cargos.add(cargo);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos");
        }
        return cargos;
    }//Fin del metodo obtenerTodos


    public int agregar(Cargo cargo) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into cargo (idcargo, nombre, estado) values (?, ?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cargo.getIdCargo());
            stmt.setString(2, cargo.getNombre());
            stmt.setBoolean(3, cargo.isEstado());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }

        return 0;
    }//Fin del metodo agregar


    public int modificar(Cargo cargo) {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "update cargo set nombre = ? where idCargo = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cargo.getNombre());
            stmt.setString(2, cargo.getIdCargo());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int eliminar(String idCargo) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from cargo where idCargo = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idCargo);

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }
        return 0;
    }//Fin del metodo eliminar

    public Cargo buscarPorId(String idCargo) {
        Cargo cargo = null;
        try {
            conn = ConexionRoot.getConexion();
            String query = "select * from cargo where idCargo=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,idCargo);
            rset = stmt.executeQuery();

            if (rset.next()){
                cargo = new Cargo();
                cargo.setIdCargo(rset.getString("idCargo"));
                cargo.setNombre(rset.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargo;
    }
}
