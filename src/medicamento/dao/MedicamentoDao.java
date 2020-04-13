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
            String sql="select * from medicamento";
            stmt= conn.prepareStatement(sql);
            rset= stmt.executeQuery();
            medicamentoList = new ArrayList<>();

            while (rset.next()){
                medicamento = new Medicamento();
                medicamento.setIdMedicamento(rset.getString("idMedicamento"));
                medicamento.setNombre(rset.getString("nombre"));

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
            String sql = "insert into medicamento(idMedicamento, nombre) values(?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, medicamento.getIdMedicamento());
            stmt.setString(2, medicamento.getNombre());



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


    public int eliminar(String idMedicamento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from medicamento  where idMedicamento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idMedicamento);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()




}
