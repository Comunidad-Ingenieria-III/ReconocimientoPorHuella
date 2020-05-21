package registroAtencionPaciente.daoregistro;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;
import registroAtencionPaciente.dtoregistro.RegistroDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RegistroDao {

    private RegistroDto registroDto;
    private List<RegistroDto> registrosAtencion;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public List<RegistroDto> cargarRegistroAtencion() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from registro_atencion_pacientes ";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            registrosAtencion = new ArrayList<>();

            while (rset.next()) {

                registroDto = new RegistroDto();

                registroDto.setFechaAtencionPaciente(rset.getDate("fechaAtencionPaciente"));
                registroDto.setHoraAtencionPaciente(rset.getTime("horaAtencionPaciente"));
                registroDto.setCondicionPaciente(rset.getString("condicionPaciente"));
                registroDto.setGlasgow(rset.getString("glasgow"));
                registroDto.setSignosVitales(rset.getString("signosVitales"));
                registroDto.setLugarAccidente(rset.getString("lugarAccidente"));
                registroDto.setIdMedicamento(rset.getString("idMedicamento"));
                registroDto.setDosis(rset.getString("dosis"));
                registroDto.setIdPersonal(rset.getString("idPersonal"));
                registroDto.setIdInstiRefe(rset.getString("idInstiRefe"));
                registroDto.setCodigoRemision(rset.getString("codigoRemision"));
                registroDto.setIdpersona(rset.getString("idpersona"));
                registroDto.setNombrePaciente(rset.getString("nombrePaciente"));
                registroDto.setApellidoPaciente(rset.getString("apellidoPaciente"));
                registroDto.setEstado(rset.getBoolean("estado"));

                registrosAtencion.add(registroDto);
            }
        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return registrosAtencion;
    }

    public int agregarRegistroAtencion(RegistroDto registroDto) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into registro_atencion_pacientes(fechaAtencionPaciente, horaAtencionPaciente, condicionPaciente," +
                    "glasgow, signosVitales, lugarAccidente, idMedicamento, dosis, idPersonal, idInstiRefe," +
                    "codigoRemision, idpersona, nombrePaciente, apellidoPaciente, estado)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);//compilo y paso parametros

            stmt.setDate(1, new java.sql.Date(registroDto.getFechaAtencionPaciente().getTime()));
            stmt.setTime(2, new java.sql.Time(registroDto.getHoraAtencionPaciente().getTime()));
            stmt.setString(3, registroDto.getCondicionPaciente());
            stmt.setString(4, registroDto.getGlasgow());
            stmt.setString(5, registroDto.getSignosVitales());
            stmt.setString(6, registroDto.getLugarAccidente());
            stmt.setString(7, registroDto.getIdMedicamento());
            stmt.setString(8, registroDto.getDosis());
            stmt.setString(9, registroDto.getIdPersonal());
            stmt.setString(10, registroDto.getIdInstiRefe());
            stmt.setString(11, registroDto.getCodigoRemision());
            stmt.setString(12, registroDto.getIdpersona());
            stmt.setString(13, registroDto.getNombrePaciente());
            stmt.setString(14, registroDto.getApellidoPaciente());
            stmt.setBoolean(15, registroDto.isEstado());

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return 1;
    }

    public RegistroDto buscarCodigoRemision(String codigoRemision) {

        RegistroDto registroDto = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select codigoRemision from registro_atencion_pacientes where codigoRemision = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigoRemision);
            rset = stmt.executeQuery();

            if (rset.next()) {
                registroDto = new RegistroDto();

                registroDto.setCodigoRemision(rset.getString("codigoRemision"));

            }
        } catch (SQLException | RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return registroDto;
    }

    public boolean buscarPrimaryKeyRegistro(String codigoRemision) {//Funcion para realizar la busqueda de un personal de salud por medio de su perimary key
        boolean trpta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select codigoRemision from registro_atencion_pacientes where codigoRemision = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codigoRemision);
            rset = stmt.executeQuery();

            if (rset.next()) {
                trpta = true;
            }

        } catch (SQLException | RuntimeException ex) {
            //throw new RuntimeException("Error SQL - Buscar Primary Key()!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return trpta;
    }

}
