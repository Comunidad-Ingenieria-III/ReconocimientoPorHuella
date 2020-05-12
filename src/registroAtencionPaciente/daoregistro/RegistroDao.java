package registroAtencionPaciente.daoregistro;

import conexionBD.ConexionRoot;
import datospersona.dto.Persona;
import registroAtencionPaciente.dtoregistro.RegistroDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegistroDao {

    private RegistroDto registroDto;
    private List<RegistroDto> registrosAtencion;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    public int agregarRegistroAtencion(RegistroDto registroDto) throws RuntimeException {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into registro_atencion_paciente(fechaAtencionPaciente, horaAtencionPaciente, condicionPaciente," +
                    "glasgow, signosVitales, lugarAccidente, idMedicamento, idPersonal, idTipoDocumento, dosis, idPersonaRecibe, nombre1PersonaRecibe," +
                    "nombre2PersonaRecibe, apellido1PersonaRecibe,apellido2PersonaRecibe, idCargo, fechaRecepcionPaciente, horaRecepcionPaciente," +
                    "idpersona, nombrePaciente, apellidoPaciente, estado)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);//compilo y paso parametros
            stmt.setDate(1, new java.sql.Date(registroDto.getFechaAtencionPaciente().getTime()));
            stmt.setDate(2, new java.sql.Date(registroDto.getHoraAtencionPaciente().getTime()));
            stmt.setString(3, registroDto.getCondicionPaciente());
            stmt.setString(4, registroDto.getGlasgow());
            stmt.setString(5, registroDto.getSignosVitales());
            stmt.setString(6, registroDto.getLugarAccidente());
            stmt.setString(7, registroDto.getIdMedicamento());
            stmt.setString(8, registroDto.getDosis());
            stmt.setString(9, registroDto.getIdPersonal());
            stmt.setString(10, registroDto.getIdTipoDocumento());
            stmt.setString(11, registroDto.getIdPersonaRecibe());
            stmt.setString(12, registroDto.getNombre1PersonaRecibe());
            stmt.setString(13, registroDto.getNombre2PersonaRecibe());
            stmt.setString(14, registroDto.getApellido1PersonaRecibe());
            stmt.setString(15, registroDto.getApellido2PersonaRecibe());
            stmt.setString(16, registroDto.getIdCargo());
            stmt.setDate(17, new java.sql.Date(registroDto.getFechaRecepcionPaciente().getTime()));
            stmt.setDate(18, new java.sql.Date(registroDto.getHoraRecepcionPaciente().getTime()));
            stmt.setString(19, registroDto.getIdpersona());
            stmt.setString(20, registroDto.getNombrePaciente());
            stmt.setString(21, registroDto.getApellidoPaciente());
            stmt.setBoolean(22, registroDto.isEstado());

            stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException("Error SQL - Agregar()!");
        }
        return 1;
    }
}
