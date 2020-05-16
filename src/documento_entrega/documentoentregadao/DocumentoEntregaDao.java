package documento_entrega.documentoentregadao;

import conexionBD.ConexionRoot;
import documento_entrega.documentoentregadto.DocumentoEntregaDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoEntregaDao {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private DocumentoEntregaDto documentoEntregaDto;
    private List<DocumentoEntregaDto> listaPersonal;

    public List<DocumentoEntregaDto> listarTodos() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from documento_entrega where estado= 1";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            listaPersonal = new ArrayList<>();

            while (rset.next()) {
                documentoEntregaDto = new DocumentoEntregaDto();

                documentoEntregaDto.setIdTipoDocumento(rset.getString("idTipoDocumento"));
                documentoEntregaDto.setIdPersonaRecibe(rset.getString("idPersonaRecibe"));
                documentoEntregaDto.setNombre1(rset.getString("nombre1"));
                documentoEntregaDto.setNombre2(rset.getString("nombre2"));
                documentoEntregaDto.setApellido1(rset.getString("apellido1"));
                documentoEntregaDto.setApellido2(rset.getString("apellido2"));
                documentoEntregaDto.setIdCargo(rset.getString("idCargo"));
                documentoEntregaDto.setFechaRecepcionPaciente(rset.getDate("fechaRecepcionPaciente"));
                documentoEntregaDto.setHoraRecepcionPaciente(rset.getTime("horaRecepcionPaciente"));
                documentoEntregaDto.setCodigoRemision(rset.getString("codigoRemision"));
                documentoEntregaDto.setObsevaciones(rset.getString("observacion"));
                documentoEntregaDto.setEstado(rset.getBoolean("estado"));


                listaPersonal.add(documentoEntregaDto);
            }

        } catch (RuntimeException | SQLException ex) {
            //throw new RuntimeException("Error SQL - obtenerTodos()!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
        }
        return listaPersonal;
    } // Fin del método obtenerTodos()

    /*public List<Familiar> buscar(String buscar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from familiar_paciente where idFamiliar LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buscar);
            rset = stmt.executeQuery();

            familiares = new ArrayList<>();

            while (rset.next()) {
                familiar = new Familiar();

                familiar.setIdFamiliar(rset.getString("idFamiliar"));
                familiar.setPrimerNombre(rset.getString("nombre1"));
                familiar.setSegundoNombre(rset.getString("nombre2"));
                familiar.setPrimerApellido(rset.getString("apellido1"));
                familiar.setSegundoApellido(rset.getString("apellido2"));
                familiar.setDireccion(rset.getString("direccion"));
                familiar.setTelFamiliar(rset.getString("telefono"));
                familiar.setEstado(rset.getString("estado"));
                familiares.add(familiar);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - buscarPoridFamiliar()!");
        }
        return familiares;
    } // Fin del método obtenerTodos()

    public Familiar buscarPorId(String idFamiliar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from familiar_paciente where idFamiliar = ? and estado = 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idFamiliar);
            rset = stmt.executeQuery();

            familiares = new ArrayList<>();

            while (rset.next()) {
                familiar = new Familiar();

                familiar.setIdFamiliar(rset.getString("idFamiliar"));
                familiar.setPrimerNombre(rset.getString("nombre1"));
                familiar.setSegundoNombre(rset.getString("nombre2"));
                familiar.setPrimerApellido(rset.getString("apellido1"));
                familiar.setSegundoApellido(rset.getString("apellido2"));
                familiar.setDireccion(rset.getString("direccion"));
                familiar.setTelFamiliar(rset.getString("telefono"));
                familiar.setEstado(rset.getString("estado"));
                familiares.add(familiar);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - buscarPoridFamiliar()!");
        }
        return familiar;
    } // Fin del método obtenerTodos()*/


    public int agregar(DocumentoEntregaDto documentoEntregaDto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into documento_entrega(idTipoDocumento, idPersonaRecibe, nombre1, nombre2, apellido1, apellido2, idCargo, fechaRecepcionPaciente," +
                    "horaRecepcionPaciente, codigoRemision, observaciones, estado)" +
                         " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, documentoEntregaDto.getIdTipoDocumento());
            stmt.setString(2, documentoEntregaDto.getIdPersonaRecibe());
            stmt.setString(3, documentoEntregaDto.getNombre1());
            stmt.setString(4, documentoEntregaDto.getNombre2());
            stmt.setString(5, documentoEntregaDto.getApellido1());
            stmt.setString(6, documentoEntregaDto.getApellido2());
            stmt.setString(7, documentoEntregaDto.getIdCargo());
            stmt.setDate(8, new java.sql.Date(documentoEntregaDto.getFechaRecepcionPaciente().getTime()));
            stmt.setTime(9, new java.sql.Time(documentoEntregaDto.getHoraRecepcionPaciente().getTime()));
            stmt.setString(10, documentoEntregaDto.getCodigoRemision());
            stmt.setString(11, documentoEntregaDto.getObsevaciones());
            stmt.setBoolean(12, documentoEntregaDto.isEstado());

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            //throw new RuntimeException("Error SQL - Agregar()!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
        }
        return 1;
    } // Fin del método agregar()

    /*public int modificar(Familiar familiar){
        try {
            conn = ConexionRoot.getConexion();
            String sql = "update familiar_paciente set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, " +
                    " direccion = ?, telefono = ? where idFamiliar = ?";
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, familiar.getPrimerNombre());
            stmt.setString(2, familiar.getSegundoNombre());
            stmt.setString(3, familiar.getPrimerApellido());
            stmt.setString(4, familiar.getSegundoApellido());
            stmt.setString(5, familiar.getDireccion());
            stmt.setString(6, familiar.getTelFamiliar());

            stmt.setString(7, familiar.getIdFamiliar());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarr(int idFamiliar) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from familiar_paciente where idFamiliar = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idFamiliar);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean eliminar(String idFamiliar) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {

            if(yes==false) {
                conn = ConexionRoot.getConexion();
                String sql = "SELECT p.idpersona ,p.idFamiliar ,ps.idFamiliar as relacion from persona_familiar AS p " +
                        "INNER JOIN familiar_paciente AS ps ON p.idFamiliar=ps.idFamiliar where ps.idFamiliar = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, idFamiliar);
                rset = stmt.executeQuery();
                if (rset.next()) {//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                    yes = true;

                } else {
                    String sql2 = "update familiar_paciente set estado = 0 where idFamiliar = ?";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setString(1, idFamiliar);
                    stmt.executeUpdate();
                    yes = false;


                }


            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }*/
}
