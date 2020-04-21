package personalSalud.personalsaluddao;

import conexionBD.ConexionRoot;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.Persona;
import institucionAcademica.dto.InstitucionAcademica;
import javafx.scene.control.Alert;
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

    public List<PersonalSalud> listarPersonalSalud() {//Funcion para obtener todos los registros de la tabla personal_salud de la BBDD
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
                personalSalud.setEstado(rset.getBoolean("estado"));
                personas.add(personalSalud);
            }

        } catch (RuntimeException | SQLException e) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Obtener Los Registros De Personal Salud");
            msg.setHeaderText("Error.");
            msg.show();
            e.printStackTrace();

        }
        return personas;
    } // Fin del método listarPersonalSalud()


    public int agregarPersonal2(PersonalSalud personalSalud) {//Funcion para agregar  registros de la tabla personal_salud de la BBDD
        PreparedStatement personal;

        try {

            conn = ConexionRoot.getConexion();
            //conn.setAutoCommit(false);
            String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2," +
                    " sexo, telefono, email, idTipoDocumento, cargo, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            personal.setBoolean(11, personalSalud.isEstado());
            personal.executeUpdate();

            return 1;

        } catch (SQLException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Agregar El Registro");
            msg.setHeaderText("Error.");
            msg.show();
            ex.printStackTrace();
        }
        return 0;
    } // Fin del método agregar()




    public int agregarPersonal(PersonalSalud personalSalud, List<PsDto> titulos) {//funcion para agregar los registros de personal_salud y
        //los registros de personal_salud_titulo a la BBDD por medio de una transaccion SQL
        PreparedStatement personal,pertitu;

        try {

            conn = ConexionRoot.getConexion();
            conn.setAutoCommit(false);
            String sql = "insert into personal_salud(idPersonal, nombre1, nombre2, apellido1, apellido2," +
                    " sexo, telefono, email, idTipoDocumento, cargo, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            personal.setBoolean(11, personalSalud.isEstado());
            personal.executeUpdate();

            String sql1 = "insert into personal_salud_titulo(idPersonal, idTipoTitu, idInstitucion, fechaTitulacion, estado) values(?, ?, ?, ?, ?)";
            pertitu = conn.prepareStatement(sql1);
            for (PsDto title : titulos) {
                pertitu.setString(1, title.getIdPersonal());
                pertitu.setString(2, title.getIdTipoTitu());
                pertitu.setString(3, title.getIdInstitucion());
                pertitu.setDate(4,title.getFechaTitulacion());
                pertitu.setBoolean(5, title.isEstado());
                pertitu.addBatch();
            }
            pertitu.executeBatch();
            pertitu.executeUpdate();

            conn.commit();

            return 1;

        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Ejecutar La Transacción");
            msg.setHeaderText("Error.");
            msg.show();
            ex.printStackTrace();
        }
        return 0;
    } // Fin del método agregar()



    public boolean agregarPsdto(PsDto psDto) {//Funcion para agregar los registros de personal_salud_titulo a la BBDDD
        try {
            conn = ConexionRoot.getConexion();
            String sql = "insert into personal_salud_titulo(idPs, idPersonal, idTipoTitu, idInstitucion, fechaTitulacion, estado) values(?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
                stmt.setInt(1, psDto.getId());
                stmt.setString(2, psDto.getIdPersonal());
                stmt.setString(3, psDto.getIdTipoTitu());
                stmt.setString(4, psDto.getIdInstitucion());
                stmt.setDate(5,psDto.getFechaTitulacion());
                stmt.setBoolean(6, psDto.isEstado());

            stmt.executeUpdate();
            return true;
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }






    public int modificarPersonal(PersonalSalud personalSalud, List<PsDto> titulos) {//Funcion para modificar los registros de la tabla personal-salud
        //y personal-salud-titulo
        PreparedStatement personal,pertitu;
        try {
            conn = ConexionRoot.getConexion();

            String sql = "update personal_salud set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, sexo = ?," +
                    " telefono = ?, email = ?, idTipoDocumento = ?, cargo = ?  where idPersonal = ?";
            personal = conn.prepareStatement(sql);
            personal.setString(1, personalSalud.getNombre1());
            personal.setString(2, personalSalud.getNombre2());
            personal.setString(3, personalSalud.getApellido1());
            personal.setString(4, personalSalud.getApellido2());
            personal.setString(5, personalSalud.getSexo());
            personal.setString(6, personalSalud.getTelefono());
            personal.setString(7, personalSalud.getEmail());
            personal.setString(8, personalSalud.getTipoDocumento());
            personal.setString(9, personalSalud.getCargo());

            personal.setString(10, personalSalud.getIdPersonal());
            personal.executeUpdate();

            String sql1 = "update personal_salud_titulo set idPersonal = ?, idTipoTitu = ? , idInstitucion = ?, fechatitulacion = ? where idPs = ?";
            pertitu = conn.prepareStatement(sql1);

            for (PsDto title : titulos) {
                pertitu.setString(1, title.getIdPersonal());
                pertitu.setString(2, title.getIdTipoTitu());
                pertitu.setString(3, title.getIdInstitucion());
                pertitu.setDate(4,title.getFechaTitulacion());
                pertitu.setInt(5, title.getId());

                pertitu.addBatch();

            }
            System.out.println(titulos.toString());
            pertitu.executeBatch();

        } catch (SQLException | RuntimeException e) {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Modificar");
            msg.setHeaderText("Exito!");
            msg.show();
            e.printStackTrace();
            return 0;
        }
        return 1;
    } // Fin del método modificar()

    public BusquedaDePersonal buscarPersonalPorId(String idPersonal){//funcion que permite la busqueda de un personal de salud, junto con los
        //registros que le pertenezcan en la tabla personal-salud-titulo.
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
                personalSalud.setEstado(rset.getBoolean("estado"));
            }

            String sql2 = "select * from personal_salud_titulo where idPersonal=?";
            stmt = conn.prepareStatement(sql2);
            stmt.setString(1,idPersonal);
            rset = stmt.executeQuery();

            listaTitulos = new ArrayList<>();
            while (rset.next()){
                psDto = new PsDto();
                psDto.setId(rset.getInt("idPs"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));
                psDto.setEstado(rset.getBoolean("estado"));

                listaTitulos.add(psDto);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
            return new BusquedaDePersonal(listaTitulos,personalSalud);
    }


    public boolean inhabilitarPersonalSalud(String idPersonal) {//Funcion que inhabilita un registro en la BBDD siempre y cuando no existas registros
        //en otras tablas que dependan de la clave primaria de éste

        boolean yes = false;
        try {
            conn = ConexionRoot.getConexion();
            //Con este query buscamos si existen registros que coincidan entre la tabla personal_salud y personal_salud_titulo por medio del idPersonal
            String sql = "SELECT p.idPersonal, ps.idPs as relacion from personal_salud AS p " +
                    "INNER JOIN personal_salud_titulo AS ps ON p.idPersonal=ps.idPersonal where p.idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPersonal);
            rset = stmt.executeQuery();

            if (rset.next()){//Si se encuentra al menos una coincidencia, el usuario no podra inactivar el registro
                yes = true;
            }else{//Si el Id que se envía como parametro no tiene registros dependientes, actualiza el estado a false = inactivo en la BBDD
                String sql1 = "update personal_salud set estado = ? where idPersonal= ?";
                stmt = conn.prepareStatement(sql1);
                stmt.setBoolean(1, false);
                stmt.setString(2, idPersonal);
                stmt.executeUpdate();
                yes = false;
            }

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return yes;
    }


    public boolean buscarPrimaryKey(String idPersonal) {//Funcion para realizar la busqueda de un personal de salud por medio de su perimary key
        boolean trpta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud where idPersonal = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idPersonal);
            rset = stmt.executeQuery();

            if (rset.next()){
                trpta = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trpta;
    }

    public PsDto buscarPsdto(int idPs) {
        boolean trpta = false;
        try {
            conn = ConexionRoot.getConexion();
            String sql = "select * from personal_salud_titulo where idPs = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idPs);
            rset = stmt.executeQuery();

            if (rset.next()){
                PsDto psDto= new PsDto();
                psDto.setId(rset.getInt("idPs"));
                psDto.setIdPersonal(rset.getString("idPersonal"));
                psDto.setIdTipoTitu(rset.getString("idTipoTitu"));
                psDto.setIdInstitucion(rset.getString("idInstitucion"));
                psDto.setFechaTitulacion(rset.getDate("fechaTitulacion"));
                psDto.setEstado(rset.getBoolean("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psDto;
    }

    public int modificarPersonal2(PersonalSalud personalSalud) {//Funcion para modificar registros de personal salud
        PreparedStatement personal;
        try {
            conn = ConexionRoot.getConexion();

            String sql = "update personal_salud set nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, sexo = ?," +
                    " telefono = ?, email = ?, idTipoDocumento = ?, cargo = ?, estado = ?  where idPersonal = ?";
            personal = conn.prepareStatement(sql);
            personal.setString(1, personalSalud.getNombre1());
            personal.setString(2, personalSalud.getNombre2());
            personal.setString(3, personalSalud.getApellido1());
            personal.setString(4, personalSalud.getApellido2());
            personal.setString(5, personalSalud.getSexo());
            personal.setString(6, personalSalud.getTelefono());
            personal.setString(7, personalSalud.getEmail());
            personal.setString(8, personalSalud.getTipoDocumento());
            personal.setString(9, personalSalud.getCargo());
            personal.setBoolean(10, personalSalud.isEstado());

            personal.setString(11, personalSalud.getIdPersonal());
            personal.executeUpdate();

        } catch (SQLException | RuntimeException e) {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Modificar");
            msg.setHeaderText("Exito!");
            msg.show();
            e.printStackTrace();
            return 0;
        }
        return 1;
    } // Fin del método modificarPersonal2()

    public int modificarTitulos(PsDto psDto) {//Funcion para modificar los registros en la tabla personal_salud_titulo de la BBDD
        PreparedStatement psdto;
        try {
            conn = ConexionRoot.getConexion();

            String sql = "update personal_salud_titulo set idPersonal = ?, idTipoTitu = ?, idInstitucion = ?, fechaTitulacion = ?, estado = ? where idPs = ?";
            psdto = conn.prepareStatement(sql);

            psdto.setString(1,psDto.getIdPersonal());
            psdto.setString(2, psDto.getIdTipoTitu());
            psdto.setString(3, psDto.getIdInstitucion());
            psdto.setDate(4,psDto.getFechaTitulacion());
            psdto.setBoolean(5, psDto.isEstado());

            psdto.setInt(6, psDto.getId());
            psdto.executeUpdate();
        } catch (SQLException | RuntimeException e) {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Personal Salud");
            msg.setContentText("Error Al Modificar");
            msg.setHeaderText("Exito!");
            msg.show();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}

