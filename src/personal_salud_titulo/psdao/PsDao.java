package personal_salud_titulo.psdao;
import conexionBD.ConexionRoot;
import personal_salud_titulo.psdto.PsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PsDao {


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private PsDto psDto;
    private List<PsDto> listaPs;

    public List<PsDto> cargartodas() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            listaPs = new ArrayList<>();

            while (rset.next()) {

                psDto = new PsDto();
                psDto.setId(rset.getInt("idPst"));
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

    public int agregar(PsDto psDto) {
        try {

            conn = ConexionRoot.getConexion();
            String sql = "insert into personal_salud_titulo(idPst, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion) values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, psDto.getId());
            stmt.setString(2, psDto.getIdPersonal());
            stmt.setString(3, psDto.getIdTipoTitu());
            stmt.setString(4, psDto.getIdInstitucion());
            stmt.setDate(5, new java.sql.Date(psDto.getFechaTitulacion().getTime()));

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método agregar()


    public int modificar(PsDto psDto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update personal_salud_titulo set idPst = ?, fechaTitulacion = ?, idPersonal = ?, idTipoTitulo = ?, idInstitucion = ?  where idPst = ?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, psDto.getIdPersonal());
            stmt.setString(2, psDto.getIdTipoTitu());
            stmt.setString(3, psDto.getIdInstitucion());
            stmt.setDate(4, psDto.getFechaTitulacion());

            stmt.setString(5, psDto.getIdPersonal());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    public int eliminar(String idPst) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from personal_salud_titulo where idPst = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPst);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()*/


}
