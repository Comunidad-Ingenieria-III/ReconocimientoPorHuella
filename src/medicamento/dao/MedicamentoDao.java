package medicamento.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexionBD.ConexionRoot;
import medicamento.dto.Medicamento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDao {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;
    private Medicamento medicamento;
    private List<Medicamento> medicamentoList;

    public List<Medicamento> obtenerTodas(){
        try{
            conn =ConexionRoot.getConexion();
            String sql="select * from medicamento where estado= 1";
            stmt= conn.prepareStatement(sql);
            rset= stmt.executeQuery();
            medicamentoList = new ArrayList<>();

            while (rset.next()){
                medicamento = new Medicamento();
                medicamento.setIdMedicamento(rset.getString("idMedicamento"));
                medicamento.setNombre(rset.getString("nombre"));
                medicamento.setEstado(rset.getString("estado"));
                medicamentoList.add(medicamento);
            }

        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - obtenerTodas()!");
        }
        return medicamentoList;
    } //Fin del método obternet todas()



    public int agregar(Medicamento medicamento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into medicamento(idMedicamento, nombre, estado) values(?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, medicamento.getIdMedicamento());
            stmt.setString(2, medicamento.getNombre());
            stmt.setInt(3, Integer.parseInt(medicamento.getEstado()));


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()

    public List<Medicamento> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from medicamento where idMedicamento LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            medicamentoList = new ArrayList<>();
            while (rset.next()){
                medicamento = new Medicamento();
                medicamento.setIdMedicamento(rset.getString("idMedicamento"));
                medicamento.setNombre(rset.getString("nombre"));
                medicamento.setEstado(rset.getString("estado"));
                medicamentoList.add(medicamento);
            }

        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarMedicamento()!");
        }
        return medicamentoList;
    } // Fin del metodo buscar por ID



    public int modificar(Medicamento medicamento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update medicamento set nombre= ?  where idMedicamento = ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, medicamento.getNombre());
            stmt.setString(2, medicamento.getIdMedicamento());
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    public int eliminarr(String idMedicamento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update medicamento set estado = 0 where idMedicamento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idMedicamento);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()

    public boolean eliminar(String idMedicamento) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idMedicamento, ps.idMedicamento as relacion from registro_atencion_paciente AS p " +
                        "INNER JOIN medicamento AS ps ON p.idMedicamento=ps.idMedicamento where ps.idMedicamento = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idMedicamento);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update medicamento set estado = 0 where idMedicamento = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idMedicamento);
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
