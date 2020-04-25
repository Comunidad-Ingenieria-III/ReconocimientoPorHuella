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
            String sql = "select * from cargo where estado = 1";
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
            String sql = "insert into cargo (idcargo, nombre, estado) values (?, ?, ?)";
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
            String sql = "update cargo set nombre = ?, estado = ?  where idCargo = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cargo.getNombre());
            stmt.setBoolean(2, cargo.isEstado());
            stmt.setString(3, cargo.getIdCargo());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    public int eliminarp(String idCargo) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update cargo set estado= 0  where idCargo = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, idCargo);

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            ;
        }
        return 0;
    }//Fin del metodo eliminar

    public boolean eliminar(String idCargo) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

        if(yes==false) {
            conn = ConexionRoot.getConexion();
            //Con este query buscamos si existen registros que coincidan entre la tabla personal_salud y personal_salud_titulo por medio del idPersonal
            String sql = "SELECT p.idPersonal, ps.idCargo as relacion from personal_salud AS p " +
                    "INNER JOIN cargo AS ps ON p.cargo=ps.idCargo where ps.idCargo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idCargo);
            rset = stmt.executeQuery();
            if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                yes = true;
            } else {
                String sql1 = "SELECT p.idPersonaRecibe, ps.idCargo as relacion from documento_referencia AS p " +
                        "INNER JOIN cargo AS ps ON p.idCargo=ps.idCargo where ps.idCargo = ?";
                stmt = conn.prepareStatement(sql1);
                stmt.setString(1, idCargo);
                rset = stmt.executeQuery();

                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update cargo set estado= 0  where idCargo = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idCargo);
                    stmt.executeUpdate();
                    yes = false;


                }

            }
        }

    } catch (RuntimeException | SQLException e) {
        e.printStackTrace();
    }
        return yes;
}



    public List<Cargo> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from cargo where idCargo LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            cargos = new ArrayList<>();
            while (rset.next()){
                cargo = new Cargo();
                cargo.setIdCargo(rset.getString("idCargo"));
                cargo.setNombre(rset.getString("nombre"));
                cargo.setEstado(rset.getBoolean("estado"));
                cargos.add(cargo);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarTitulo()!");
        }
        return cargos;
    }





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
