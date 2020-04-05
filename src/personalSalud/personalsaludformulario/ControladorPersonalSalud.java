package personalSalud.personalsaludformulario;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
import conexionBD.JdbcHelper;
import datosFamiliar.dtofamiliar.Familiar;
import datospersona.dto.Persona;
import eps.dto.DtoEps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import personalSalud.personalsaluddto.PersonalSalud;
import personalSalud.personalsaludfacade.PersonalSaludFacade;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControladorPersonalSalud implements Initializable {

    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeCargo facadeCargo = new FacadeCargo();

    @FXML
    private ComboBox<DtoTipoDocumento> cmb_tipodocumento;
    @FXML
    private TextField tf_numerodocumento;
    @FXML
    private TextField tf_nombre1;
    @FXML
    private TextField tf_nombre2;
    @FXML
    private TextField tf_apellido1;
    @FXML
    private TextField tf_apellido2;
    @FXML
    private ComboBox<String> cmb_sexo;
    @FXML
    private TextField tf_numtelefono;
    @FXML
    private TextField tf_correoelectronico;
    @FXML
    private ComboBox<Cargo> cmb_cargo;
    @FXML
    private Button bt_crear;
    @FXML
    private Button bt_consultar;
    @FXML
    private Button bt_cancelar;
    @FXML
    private Button bt_salir;
    @FXML
    private Button bt_guardar;
    @FXML
    private Button bt_modificar;
    @FXML
    private Button bt_inhabilitar;
    @FXML
    private TableView mi_tabla;
    @FXML
    private TableColumn<PersonalSalud, String> colIdTitulo;
    @FXML
    private TableColumn<PersonalSalud, String> colIdInstitucion;
    @FXML
    private TableColumn<PersonalSalud, String> colFecha;
    @FXML
    private TableColumn<DtoEps, String> colTelefono;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        iniciarCbxDocumento();
        iniciarCbxSexo();
        iniciarCargo();
        //deshabilitarBotones();
        //deshabilitarCampos();

    }

    @FXML
    private PersonalSalud crearPersonalSalud() {

        int idPersonal = Integer.parseInt(tf_numerodocumento.getText());
        String nombre1 = tf_nombre1.getText();
        String nombre2 = tf_nombre2.getText();
        String apellido1 = tf_apellido1.getText();
        String apellido2 = tf_apellido2.getText();
        String sexo = cmb_sexo.getValue();
        String telefono = tf_numtelefono.getText();
        String email = tf_correoelectronico.getText();
        String tipoDocumento = cmb_tipodocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        String cargo = cmb_cargo.getSelectionModel().getSelectedItem().getIdCargo();

        PersonalSalud personal = new PersonalSalud(idPersonal, nombre1, nombre2, apellido1, apellido2,
                sexo, telefono, email, tipoDocumento, cargo);

        return personal;

    }

    private boolean modificarPersonalSalud(PersonalSalud personalSalud) {

        String query = "UPDATE personal_salud set " +

                "nombre1 = '" + personalSalud.getNombre1() + "','" +
                "nombre2 = '" + personalSalud.getNombre2() + "','" +
                "apellido1 = '" + personalSalud.getApellido1() + "','" +
                "apellido2 = '" + personalSalud.getApellido2() + "','" +
                "sexo = '" + personalSalud.getSexo() + "','" +
                "telefono = '" + personalSalud.getTelefono() + "','" +
                "email = '" + personalSalud.getEmail() + "','" +
                "tipoDocumento = '" + personalSalud.getTipoDocumento() + "','" +
                "cargo = '" + personalSalud.getCargo() + "')" +
                "WHERE idPersona = '" + personalSalud.getIdPersonal();
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;


    }

    @FXML
    private void guardarPersona() {
        //validar();
        try {
            guardar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*int res = personalSaludFacade.agregarPersonalSalud(crearPersonalSalud());

        if (res == 1) {
            //familiares.add(familiar);//Cada que se agrege un objeto a se actualiza la observable(el modelo de la tableView
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Pacientes - familiares");
            msg.setContentText("El familiar ha sido agregado correctamente");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Pacientes - Familiares");
            msg.setContentText("El familiar NO ha sido agregado correctamente");
            msg.setHeaderText("REsult");
            msg.show();
        }
        //limpiarFormulario();*/

    }

    public boolean validar(PersonalSalud personalSalud) {
        StringBuilder sb = new StringBuilder();
        boolean esValido = true;
        if (personalSalud == null) {
            esValido = false;
            sb.append("*No existe libro\n");
        }

        if (tf_nombre1.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Primer Nombre Requerido ");
        }

        if (tf_apellido1.getText().isEmpty()) {
            esValido = false;
            sb.append("Campo Primer Apellido Requerido ");
        }


        /*}else if (!tf_Usuario.getText().contains("@") || !tf_Usuario.getText().contains(".")){
            lblUsuario.setText("Usuario invalido");
        }else {
            lblUsuario.setText("");
        }

        /*if (personalSalud.getNombre1().trim().isEmpty()) {
            esValido = false;
            sb.append("*Nombre requerido\n");
        }
        /*if(libro.getNombre().trim().length() > 100){
            esValido = false;
            sb.append("*Nombre debe ser menor a 100 caracteres\n");
        }
        if(libro.getAutor().trim().equals("")){
            esValido = false;
            sb.append("*Autor requerido\n");
        }
        if(libro.getAutor().trim().length() > 100){
            esValido = false;
            sb.append("*Autor debe ser menor a 100 caracteres\n");
        }
        if(libro.getGenero().trim().equals("")){
            esValido = false;
            sb.append("*Género requerido\n");
        }
        if(libro.getPaginas() <= 0){
            esValido = false;
            sb.append("*Páginas debe ser mayor a cero\n");
        }
        if(libro.getFechaLong() == 0){
            esValido = false;
            sb.append("*Fecha requerida\n");
        }*/

        if (!esValido) {
            JOptionPane.showMessageDialog(null, "Se encontraron los siguientes "
                            + "errores: \n" + sb.toString(), "Error de validación",
                    JOptionPane.WARNING_MESSAGE);
        }
        return esValido;
    }

    public boolean guardar() throws SQLException {

        PersonalSalud personalSalud = new PersonalSalud();

        if (validar(personalSalud) == false) {
            return false;
        }
        boolean exito;
        if (personalSalud.getIdPersonal() == 0) {

            exito = personalSaludFacade.agregarPersonalSalud(crearPersonalSalud());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Pacientes - familiares");
            msg.setContentText("El familiar ha sido agregado correctamente");
            msg.setHeaderText("Resultado");
            msg.show();

        } else
            exito = modificarPersonalSalud(personalSalud);

        return exito;
    }


    /*
    @FXML
     public void guardarPersonalSaldud() {//Este metodo toma los valores de los componenetes del formulario, crea un objeto y lo envia a la BD
        PersonalSalud personalSalud = new PersonalSalud(
                Integer.parseInt(tf_numerodocumento.getText()),
                tf_nombre1.getText(),
                tf_nombre2.getText(),
                tf_apellido1.getText(),
                tf_apellido2.getText(),
                cmb_sexo.getValue(),
                tf_numtelefono.getText(),
                tf_correoelectronico.getText(),
                cmb_tipodocumento.getValue(),
                cmb_cargo.getValue()

        );

        int res = personalSaludFacade.agregarPersonalSalud(personalSalud);

        if (res == 1) {
            //familiares.add(familiar);//Cada que se agrege un objeto a se actualiza la observable(el modelo de la tableView
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Pacientes - familiares");
            msg.setContentText("El familiar ha sido agregado correctamente");
            msg.setHeaderText("Resultado");
            msg.show();

        } else {

            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Pacientes - Familiares");
            msg.setContentText("El familiar NO ha sido agregado correctamente");
            msg.setHeaderText("REsult");
            msg.show();
        }
        //limpiarFormulario();

    }

    /*
    @FXML
    public void modificarPersonalSalud() {
        PersonalSalud personalSalud = new Familiar(
                Integer.parseInt(tf_idfamiliar.getText()),
                tf_nombre1.getText(),
                tf_nombre2.getText(),
                tf_apellido1.getText(),
                tf_apellido2.getText(),
                tf_direccion.getText(),
                tf_numtelefono.getText()

        );
        int res = facade.modificarFamiliar(familiar);
        if (res == 1) {
            familiares.set(tblFamiliares.getSelectionModel().getSelectedIndex(), familiar);
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar se ha modificado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar No ha sido modificada");
            msg.setHeaderText("Resultado");
            msg.show();
        }

    }

    @FXML
    public void eliminarFamiliar() {
        int res = facade.eliminarFamiliar(tblFamiliares.getSelectionModel().getSelectedItem().getIdFamiliar());
        if (res == 1) {
            familiares.remove(tblFamiliares.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar se ha eliminado");
            msg.setHeaderText("Resultado");
            msg.show();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Familiar Paciente");
            msg.setContentText("El familiar  No ha sido eliminada");
            msg.setHeaderText("Resultado");
            msg.show();
        }


    }*/

    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Seleccione", "Masculino", "Femenino");
        cmb_sexo.setItems(items);
        cmb_sexo.getSelectionModel().selectFirst();
    }

    @FXML
    public void iniciarCbxDocumento() {
        ObservableList<DtoTipoDocumento> listatipodocumento = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());
        cmb_tipodocumento.setItems(listatipodocumento);
    }

    @FXML
    public void iniciarCargo() {
        ObservableList<Cargo> listaCargo = FXCollections.observableArrayList(facadeCargo.obtenerCargos());
        cmb_cargo.setItems(listaCargo);
    }


    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(true);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        habilitarCampos();
    }

    @FXML
    private void deshabilitarBotones() {
        bt_crear.setDisable(false); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(true);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
    }

    @FXML
    private void habilitarCampos() {
        cmb_tipodocumento.setDisable(false);
        tf_numerodocumento.setDisable(false);
        tf_nombre1.setDisable(false);
        tf_nombre2.setDisable(false);
        tf_apellido1.setDisable(false);
        tf_apellido2.setDisable(false);
        cmb_sexo.setDisable(false);
        tf_correoelectronico.setDisable(false);
        tf_numtelefono.setDisable(false);
        cmb_cargo.setDisable(false);
        mi_tabla.setDisable(false);
        tf_numerodocumento.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        cmb_tipodocumento.setDisable(true);
        tf_numerodocumento.setDisable(true);
        tf_nombre1.setDisable(true);
        tf_nombre2.setDisable(true);
        tf_apellido1.setDisable(true);
        tf_apellido2.setDisable(true);
        cmb_sexo.setDisable(true);
        tf_correoelectronico.setDisable(true);
        tf_numtelefono.setDisable(true);
        cmb_cargo.setDisable(true);
        mi_tabla.setDisable(true);
        tf_numerodocumento.requestFocus();
    }

    @FXML
    private void cerraPersonalSalud(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }
}
