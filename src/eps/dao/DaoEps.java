package eps.dao;

import conexionBD.ConexionRoot;
import eps.dto.DtoEps;
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
            String sql = "select * from eps where estado = 1";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            dtoepss = new ArrayList<>();

            while (rset.next()) {
                dtoeps = new DtoEps();
                dtoeps.setIdEps(rset.getString("idEps"));
                dtoeps.setNombreEps(rset.getString("nombreEps"));
                dtoeps.setdireccionEps(rset.getString("direccionEps"));
                dtoeps.setTelEps(rset.getString("telEps"));
                dtoeps.setEstado(rset.getString("estado"));
                dtoepss.add(dtoeps);

            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return dtoepss;
    }

    public List<DtoEps> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from eps where idEps LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            dtoepss = new ArrayList<>();
            while (rset.next()){
                dtoeps = new DtoEps();
                dtoeps.setIdEps(rset.getString("idEps"));
                dtoeps.setNombreEps(rset.getString("nombreEps"));
                dtoeps.setdireccionEps(rset.getString("direccionEps"));
                dtoeps.setTelEps(rset.getString("telEps"));
                dtoeps.setEstado(rset.getString("estado"));
                dtoepss.add(dtoeps);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarEPS()!");
        }
        return dtoepss;
    }





    public int agregarEps(DtoEps dtoEps) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into eps(idEps, nombreEps, direccionEps, telEps, estado) values (?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setString(1, dtoEps.getIdEps());
            stmt.setString(2, dtoEps.getNombreEps());
            stmt.setString(3, dtoEps.getdireccionEps());
            stmt.setString(4, dtoEps.getTelEps());
            stmt.setInt(5, Integer.parseInt(dtoEps.getEstado()));
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


    public int eliminarEpss(String idEps) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update eps set estado= 0 where idEps = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idEps);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()

    public boolean eliminarEps(String idEps) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idPersona, ps.idEps as relacion from datos_persona AS p " +
                        "INNER JOIN eps AS ps ON p.idEps=ps.idEps  where ps.idEps = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idEps);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                    } else {
                        String sql2 = "update eps set estado= 0 where idEps = ?";
                        stmt = conn.prepareStatement(sql2);
                        stmt.setString(1, idEps);
                        stmt.executeUpdate();
                        yes = false;


                    }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }






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
