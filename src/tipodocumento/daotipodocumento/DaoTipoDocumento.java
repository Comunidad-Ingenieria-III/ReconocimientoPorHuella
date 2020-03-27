package tipodocumento.daotipodocumento;

import conexionBD.ConexionRoot;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTipoDocumento {

    private DtoTipoDocumento dtotipodocumento;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

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
                dtotipodocumento.setIdTipoDocumento(rset.getInt("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));
                dtotipodocumentos.add(dtotipodocumento);
                //System.out.println("archivos que van " + dtotipodocumentos);
            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return dtotipodocumentos;
    }

    public void agregarTipoDocumento(DtoTipoDocumento dtotipodocumento) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into tipo_de_documento(idTipoDocumento, nombreTipoDocumento) values (?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setInt(1, dtotipodocumento.getIdTipoDocumento());
            stmt.setString(2, dtotipodocumento.getNombreTipoDocumento());

            int rta = stmt.executeUpdate();
            if (rta != 1) {
                throw new RuntimeException("Error al agregar!");
            }
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException("Error SQL - Agregar()!");
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

                dtotipodocumento.setIdTipoDocumento(rset.getInt("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return dtotipodocumento;
    }


}

