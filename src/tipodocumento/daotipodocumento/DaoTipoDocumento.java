package tipodocumento.daotipodocumento;

import conexionBD.ConexionRoot;
import institucionAcademica.dto.InstitucionAcademica;
import tipoTituloAcademico.dto.TtAcademico;
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
            String sql = "select * from tipo_documento where estado= 1";

            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset
            //System.out.println(rset);

            dtotipodocumentos = new ArrayList<>();

            while (rset.next()) {
                dtotipodocumento = new DtoTipoDocumento();
                dtotipodocumento.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));
                dtotipodocumento.setEstado(rset.getString("estado"));
                dtotipodocumentos.add(dtotipodocumento);
                //System.out.println("archivos que van " + dtotipodocumentos);
            }


        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return dtotipodocumentos;
    }

    public List<DtoTipoDocumento> buscar(String buscar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_documento where idTipoDocumento LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            dtotipodocumentos = new ArrayList<>();
            while (rset.next()){
                dtotipodocumento = new DtoTipoDocumento();
                dtotipodocumento.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                dtotipodocumento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));
                dtotipodocumento.setEstado(rset.getString("estado"));
                dtotipodocumentos.add(dtotipodocumento);
            }



        }catch (RuntimeException | SQLException e){
            throw new RuntimeException("Error SQL - BucarTitulo()!");
        }
        return dtotipodocumentos;
    }


    public int agregarTipoDocumento(DtoTipoDocumento dtotipodocumento) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into tipo_documento(idTipoDocumento, nombreTipoDocumento, estado) values (?, ?, ?)";

            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setString(1, dtotipodocumento.getIdTipoDocumento());
            stmt.setString(2, dtotipodocumento.getNombreTipoDocumento());
            stmt.setInt(3, Integer.parseInt(dtotipodocumento.getEstado()));
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    }

    public DtoTipoDocumento buscarPorId(String idTipoDocumento) {
        DtoTipoDocumento documento = null;//Es necesario inicializar la variable para evitar conflictos con los otros metodos que contienen la misma variable
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from tipo_documento where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoDocumento);
            rset = stmt.executeQuery();

            if (rset.next()) {
                documento = new DtoTipoDocumento();
                documento.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                documento.setNombreTipoDocumento(rset.getString("nombreTipoDocumento"));

            }
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return documento;
    }

    public int modificar(DtoTipoDocumento dtotipodocumento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_documento set nombreTipoDocumento = ?  where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, dtotipodocumento.getNombreTipoDocumento());

            stmt.setString(2, dtotipodocumento.getIdTipoDocumento());


            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()


    //


    public int eliminarP(String idTipoDocumento) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update tipo_documento set estado = 0 where idTipoDocumento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idTipoDocumento);
            return stmt.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método eliminar()



    public boolean eliminar(String idTipoDocumento) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();

                String sql = "SELECT p.idPersonal, ps.idTipoDocumento as relacion from personal_salud AS p " +
                        "INNER JOIN tipo_documento AS ps ON p.idTipoDocumento=ps.idTipoDocumento where ps.idTipoDocumento = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idTipoDocumento);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;
                } else {
                    String sql1 = "SELECT p.idPersona, ps.idTipoDocumento as relacion from datos_persona AS p " +
                            "INNER JOIN tipo_documento AS ps ON p.idTipoDocumento=ps.idTipoDocumento where ps.idTipoDocumento = ?";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, idTipoDocumento);
                    rset = stmt.executeQuery();

                    if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                        yes = true;

                    } else {
                        String sql2 = "update tipo_documento set estado = 0 where idTipoDocumento = ?";
                        stmt = conn.prepareStatement(sql2);
                        stmt.setString(1, idTipoDocumento);
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





}

