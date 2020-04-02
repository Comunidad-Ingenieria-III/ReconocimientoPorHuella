package tipodocumento.daotipodocumento;

import conexionBD.ConexionRoot;
import institucionAcademica.dto.InstitucionAcademica;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTipoDocumento {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private DtoTipoDocumento dtotipodocumento;
    List<DtoTipoDocumento> dtotipodocumentos;

    public List<DtoTipoDocumento> cargarTipoDocumento() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_de_documento";

            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset
            //System.out.println(rset);

            dtotipodocumentos = new ArrayList<>();

            while (rset.next()) {
                dtotipodocumento = new DtoTipoDocumento();
                dtotipodocumento.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));
                dtotipodocumentos.add(dtotipodocumento);
                //System.out.println("archivos que van " + dtotipodocumentos);
            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return dtotipodocumentos;
    }

    public int agregarTipoDocumento(DtoTipoDocumento dtotipodocumento) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into tipo_de_documento(idTipoDocumento, nombreTipoDocumento) values (?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setString(1, dtotipodocumento.getIdTipoDocumento());
            stmt.setString(2, dtotipodocumento.getNombreTipoDocumento());

            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public DtoTipoDocumento buscarPorId(String idTipoDocumento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_de_documento where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoDocumento);
            rset = stmt.executeQuery();

            if (rset.next()) {

                dtotipodocumento.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return dtotipodocumento;
    }

    public int modificar(DtoTipoDocumento dtotipodocumento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_de_documento set nombreTipoDocumento = ?  where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, dtotipodocumento.getIdTipoDocumento());

            stmt.setString(2, dtotipodocumento.getNombreTipoDocumento());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    public int eliminar(String idTipoDocumento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from tipo_de_documento where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idTipoDocumento));
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()


}

