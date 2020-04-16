package personalSalud.personalsaluddao;

import conexionBD.ConexionRoot;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.Persona;
import institucionAcademica.dto.InstitucionAcademica;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalSaludDao {

    JdbcHelper jdbcHelper = new JdbcHelper();


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private PersonalSalud personalSalud;
    private List<PersonalSalud> personas;
    private PsDto psDto;
    private List<PsDto> listaPs;

    public List<PersonalSalud> listarPersonalSalud() {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud";
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            personas = new ArrayList<>();

            while (rset.next()) {
                personalSalud = new PersonalSalud();

                personalSalud.setIdPersonal(rset.getString("idPersonal"));
                personalSalud.setNombre1(rset.getString("nombre1"));
                personalSalud.setNombre2(rset.getString("nombre2"));
                personalSalud.setApellido1(rset.getString("apellido1"));
                personalSalud.setApellido2(rset.getString("apellido2"));
                personalSalud.setSexo(rset.getString("sexo"));
                personalSalud.setTelefono(rset.getString("telefono"));
                personalSalud.setEmail(rset.getString("email"));
                personalSalud.setTipoDocumento(rset.getString("tipoDocumento"));
                personalSalud.setCargo(rset.getString("cargo"));

                personas.add(personalSalud);
            }

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerTodos()!");
        }
        return personas;
    } // Fin del método obtenerTodos()


    public List<PsDto> listaPsdto() throws RuntimeException {

        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo join personal_salud on personal_salud_titulo.idPersonal = personal_salud.idPersonal ";
            stmt = conn.prepareStatement(sql);//preparar consulta
            rset = stmt.executeQuery();//ejecutar la consulta y guardarla en la variabble rset

            listaPs = new ArrayList<>();

            while (rset.next()) {

                psDto = new PsDto();
                //psDto.setId(rset.getInt("idPst"));
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


    public int agregarPersonal(PersonalSalud personalSalud, List<PsDto> titulos) {
        PreparedStatement personal,pertitu;

        try {

            conn = ConexionRoot.getConexion();
            conn.setAutoCommit(false);
            String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2, sexo, telefono, email, idTipoDocumento, cargo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            personal = conn.prepareStatement(sql);
            personal.setString(1, personalSalud.getIdPersonal());
            personal.setString(2, personalSalud.getNombre1());
            personal.setString(3, personalSalud.getNombre2());
            personal.setString(4, personalSalud.getApellido1());
            personal.setString(5, personalSalud.getApellido2());
            personal.setString(6, personalSalud.getSexo());
            personal.setString(7, personalSalud.getTelefono());
            personal.setString(8, personalSalud.getEmail());
            personal.setString(9, personalSalud.getTipoDocumento());
            personal.setString(10, personalSalud.getCargo());
            personal.executeUpdate();


            String sql1 = "insert into personal_salud_titulo(idPersonal, idTipoTitu, idInstitucion, fechaTitulacion) values(?, ?, ?, ?)";
            pertitu = conn.prepareStatement(sql1);
            for (PsDto title : titulos) {
                pertitu.setString(1, title.getIdPersonal());
                pertitu.setString(2, title.getIdTipoTitu());
                pertitu.setString(3, title.getIdInstitucion());
                pertitu.setDate(4,title.getFechaTitulacion());
                pertitu.addBatch();

            }
            pertitu.executeBatch();
            pertitu.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(null, "Se ejecutó la transaccion corectamente");
            return 1;

        } catch (SQLException ex) {
            try {
                conn.rollback();
                JOptionPane.showMessageDialog(null, "Error en la transaccion");
               ex.printStackTrace();
            } catch (SQLException e) {
                e.getMessage();
            }


        }
        return 0;
    } // Fin del método agregar()



    public boolean agregarLote(List<PsDto> listaPs) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into personal_salud_titulo(idPst, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion) values(?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            for (PsDto titulo : listaPs) {
                stmt.setInt(1, titulo.getId());
                stmt.setString(2, titulo.getIdPersonal());
                stmt.setString(3, titulo.getIdTipoTitu());
                stmt.setString(4, titulo.getIdInstitucion());
                stmt.setDate(5,titulo.getFechaTitulacion());
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return false;
        }
    }



    public int modificarPersonal(PersonalSalud personalSalud) {
        try {
            conn = ConexionRoot.getConexion();

            String sql = "update personal_salud set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, sexo = ?," +
                    " telefono = ?, email = ?, tipoDocumento = ?, cargo = ?  where idPersonal = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, personalSalud.getNombre1());
            stmt.setString(2, personalSalud.getNombre2());
            stmt.setString(3, personalSalud.getApellido1());
            stmt.setString(4, personalSalud.getApellido2());
            stmt.setString(5, personalSalud.getSexo());
            stmt.setString(6, personalSalud.getTelefono());
            stmt.setString(7, personalSalud.getEmail());
            stmt.setString(8, personalSalud.getTipoDocumento());
            stmt.setString(9, personalSalud.getCargo());

            stmt.setString(10, String.valueOf(Integer.parseInt(String.valueOf(personalSalud.getIdPersonal()))));
            return stmt.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.toString());
            return 0;
        }
    } // Fin del método modificar()

    public BusquedaDePersonal buscarPersonalPorId(String idPersonal){
        PsDto psDto;
        List<PsDto>listaTitulos = null;
        PersonalSalud personalSalud = null;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud where idPersonal=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idPersonal);
            rset = stmt.executeQuery();

            if (rset.next()){
                personalSalud = new PersonalSalud();
                personalSalud.setIdPersonal(rset.getString("idPersonal"));
                personalSalud.setNombre1(rset.getString("nombre1"));
                personalSalud.setNombre2(rset.getString("nombre2"));
                personalSalud.setApellido1(rset.getString("apellido1"));
                personalSalud.setApellido2(rset.getString("apellido2"));
                personalSalud.setSexo(rset.getString("sexo"));
                personalSalud.setTelefono(rset.getString("telefono"));
                personalSalud.setEmail(rset.getString("email"));
                personalSalud.setTipoDocumento(rset.getString("idTipoDocumento"));
                personalSalud.setCargo(rset.getString("cargo"));



            }

            String sql2 = "select * from personal_salud_titulo where idPersonal=?";
            stmt = conn.prepareStatement(sql2);
            stmt.setString(1,idPersonal);
            rset = stmt.executeQuery();

            listaTitulos = new ArrayList<>();
            while (rset.next()){
                psDto = new PsDto();
                psDto.setId(rset.getInt("idPst"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));

                listaTitulos.add(psDto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
            return new BusquedaDePersonal(listaTitulos,personalSalud);
    }



    /*public PsDto buscarPorId(PsDto psDto) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo where idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(psDto));
            rset = stmt.executeQuery();

            if (rset.next()) {
                psDto = new PsDto();

                //psDto.setId(rset.getInt("idPst"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));

            }
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - obtenerPorId()!");
        }
        return psDto;
    }*/

    public int eliminarPersonalSalud(String idCliente) {
        try {
            conn = ConexionRoot.getConexion();
            String sql = "delete from datos_persona where idpersona = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idCliente);

            return stmt.executeUpdate();

        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException("Error SQL - eliminar()!");
        }
    }

}
