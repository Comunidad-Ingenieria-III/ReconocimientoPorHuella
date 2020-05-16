package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.dtousuario.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable{

    @FXML
    private AnchorPane FormularioPrincipal;
    @FXML
    private Button bt_tipoDocumento;
    @FXML
    private Button bt_datosPersona;
    @FXML
    private Button bt_eps;
    @FXML
    private Button bt_datosFamiliar;
    @FXML
    private Button bt_medicamentos;
    @FXML
    private Button bt_tipotitulo;
    @FXML
    private Button bt_cargoempleado;
    @FXML
    private Button bt_institucionreferencia;
    @FXML
    private Button bt_institucionacademica;
    @FXML
    private Button bt_personalsalud;
    @FXML
    private Button bt_tipoinstitucionreferencia;
    @FXML
    private Button bt_consultapacientesatendidos;
    @FXML
    private Button bt_consultapersonalaph;
    @FXML
    private Button bt_consultapersonalrecibepaciente;
    @FXML
    private Button bt_informenovedadpacientes;
    @FXML
    private Button bt_informenovedadtitulosacademicos;
    @FXML
    private Button bt_registroatencionpaciente;
    @FXML
    private Button bt_informepersonarecibe;
    @FXML
    private MenuItem mi_cerrar;
    @FXML
    private MenuItem mi_tipo_documeto;
    @FXML
    private MenuItem mi_datos_persona;
    @FXML
    private MenuItem mi_regisgtro_atencion;
    @FXML
    private MenuItem mi_eps;
    @FXML
    private MenuItem mi_datos_personal_salud;
    @FXML
    private MenuItem mi_registro_atencion_paciente;
    @FXML
    private MenuItem mi_InstitucionAcademica;
    @FXML
    private MenuItem mi_Cargo;
    @FXML
    private MenuItem mi_InstitucionReferencia;
    @FXML
    private MenuItem mi_DatosFamiliar;
    @FXML
    private MenuItem mi_perfil;
    @FXML
    private MenuItem mi_usario;
    @FXML
    private Menu mnuGestiones;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void abrirPerfil(ActionEvent event) throws IOException {
        try {
            Parent formulario_Perfil;
            formulario_Perfil = FXMLLoader.load(getClass().getClassLoader().getResource("perfil/formularioperfil/FormularioPerfil.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_tipo_documento);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Perfil)");
            stage.setScene(new Scene(formulario_Perfil));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirUsuario(ActionEvent event) throws IOException {
        try {
            Parent formulario_Usuario;
            formulario_Usuario = FXMLLoader.load(getClass().getClassLoader().getResource("login/formulariousuario/AdministracionUsuario.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_tipo_documento);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Perfil)");
            stage.setScene(new Scene(formulario_Usuario));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void abrirTipoDocumento(ActionEvent event) throws IOException {


        try {
            Parent formulario_tipo_documento;
            formulario_tipo_documento = FXMLLoader.load(getClass().getClassLoader().getResource("tipodocumento/formulariotipodocumento/FormularioTipoDocumento.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_tipo_documento);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión EPS)");
            stage.setScene(new Scene(formulario_tipo_documento));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirDatosPersona(ActionEvent event) throws IOException {

        try {

            Parent formulario_datos_persona;
            formulario_datos_persona = FXMLLoader.load(getClass().getClassLoader().getResource("datospersona/formulariopersona/FormularioDatosPersona.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_datos_persona);
            Stage stage = new Stage();
            //AnchorPane panel_principal = new AnchorPane();
            //Scene scene = new Scene(panel_principal);
            stage.setTitle("AP_Humana (Gestión Datos Persona)");
            stage.setScene(new Scene(formulario_datos_persona));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ocultar la ventana de Login
        //((Node) (event.getSource())).getScene().getWindow().hide();

    }


    @FXML
    private void abrirEps(ActionEvent event) throws IOException {

        try {
            Parent formulario_EPS;
            formulario_EPS = FXMLLoader.load(getClass().getClassLoader().getResource("eps/formularioeps/FormularioEPS.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_EPS);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión EPS)");
            stage.setScene(new Scene(formulario_EPS));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirDatosFamiliar(ActionEvent event) throws IOException {

        try {
            Parent formulario_datos_familiar;
            formulario_datos_familiar = FXMLLoader.load(getClass().getClassLoader().getResource("datosFamiliar/formulariofamiliar/FormularioDatosFamiliar.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_datos_familiar);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Datos Familiar)");
            stage.setScene(new Scene(formulario_datos_familiar));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirPersonalSalud(ActionEvent event) throws IOException {

        try {

            Parent formulario_Personal_salud;
            formulario_Personal_salud = FXMLLoader.load(getClass().getClassLoader().getResource("personalSalud/personalsaludformulario/FormularioPersonalSalud.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_Personal_salud);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Personal Salud)");
            stage.setScene(new Scene(formulario_Personal_salud));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abriTipoTituloAcademico(ActionEvent event) throws IOException {


        try {
            Parent formulario_Tipo_Titulo_Academico;
            formulario_Tipo_Titulo_Academico = FXMLLoader.load(getClass().getClassLoader().getResource("tipoTituloAcademico/formularioTipoTituloAcademico/FormularioTipoTituloAcademico.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_Tipo_Titulo_Academico);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Tipo Título Académico)");
            stage.setScene(new Scene(formulario_Tipo_Titulo_Academico));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ventanaInstitucionAcademica() {
        try {
            Parent institucion_Academica;
            institucion_Academica = FXMLLoader.load(getClass().getClassLoader().getResource("institucionAcademica/formulario/FormularioInstitucionAcademica.fxml"));
            //FormularioPrincipal.getChildren().setAll(institucion_Academica);
            Stage stage = new Stage();
            //institucion_Academica.getStylesheets().add("Resources/Style.css");
            stage.setScene(new Scene(institucion_Academica));
            stage.setTitle("MODULO INSTITUCIÓN ACADEMICA");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }//fin del metodo ventanaInstitucionReferencia()

    @FXML
    public void ventanaCargos() {
        try {
            Parent cargo;
            cargo = FXMLLoader.load(getClass().getClassLoader().getResource("cargo/formulario/formularioCargo.fxml"));
            //FormularioPrincipal.getChildren().setAll(cargo);
            Stage stage = new Stage();
            stage.setScene(new Scene(cargo));
            stage.setTitle("MODULO DE CARGOS");
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }//fin del metodo ventanaCargos()

    @FXML
    public void ventanaInstitucionReferencia() {
        try {
            Parent institucion_referencia;
            institucion_referencia = FXMLLoader.load(getClass().getClassLoader().getResource("institucionreferencia/formulario/FormularioInstitucionReferencia.fxml"));
            //FormularioPrincipal.getChildren().setAll(institucion_referencia);
            Stage stage = new Stage();
            stage.setScene(new Scene(institucion_referencia));
            stage.setTitle("MODULO INSTITUCIÓN REFERENCIA");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }//fin del metodo ventanaInstitucionReferencia()


    @FXML
    private void abrirCargoEmpleado(ActionEvent event) throws IOException {

        try {

            Stage stage = new Stage();
            Parent formulario_Cargo_Empleado = FXMLLoader.load(getClass().getClassLoader().getResource("cargoempleado/formulariocargo/FormularioCagoEmpleado.fxml"));
            stage.setTitle("AP_Humana (Gestión Cargo)");
            stage.setScene(new Scene(formulario_Cargo_Empleado));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirInstitucionReferencia(ActionEvent event) throws IOException {

        try {
            Stage stage = new Stage();
            Parent formulario_Institucion_Referencia = FXMLLoader.load(getClass().getClassLoader().getResource("institucionreferencia/formularioinstitucionreferencia/FormularioInstitucionReferencia.fxml"));
            stage.setTitle("AP_Humana (Gestión Institución Referencia)");
            stage.setScene(new Scene(formulario_Institucion_Referencia));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirRegistroAtencionPaciente(ActionEvent event) throws IOException {


        try {
            Parent formulario_Registro_Atencion_Paciente;
            formulario_Registro_Atencion_Paciente = FXMLLoader.load(getClass().getClassLoader().getResource("registroAtencionPaciente/formularioregistro/FormularioRegistroAtencion.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_Registro_Atencion_Paciente);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Registro Atención)");
            stage.setScene(new Scene(formulario_Registro_Atencion_Paciente));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirMedicamentos(ActionEvent event) throws IOException {

        try {
            Parent formulario_Medicamentos;
            formulario_Medicamentos = FXMLLoader.load(getClass().getClassLoader().getResource("medicamento/formularioMedicamento/FormularioMedicamentos.fxml"));
            //FormularioPrincipal.getChildren().setAll(formulario_Medicamentos);
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Medicamentos)");
            stage.setScene(new Scene(formulario_Medicamentos));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirConsultaPacientesAtendidos(ActionEvent event) throws IOException {

        try {
            Parent formulario_Pacientes_Atendidos = FXMLLoader.load(getClass().getClassLoader().getResource("consultas/pacientesatendidos/ConsultaPacientesAtendidos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Gestión Consulta Pacientes Atendidos)");
            stage.setScene(new Scene(formulario_Pacientes_Atendidos));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirConsultaPersonalAph(ActionEvent event) throws IOException {

        try {
            Parent formulario_Personal_Aph = FXMLLoader.load(getClass().getClassLoader().getResource("consultas/personalatiendeemergencia/PersonalAtiendeAccidente.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Gestión Consulta Personal APH)");
            stage.setScene(new Scene(formulario_Personal_Aph, 700, 400));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirConsultaInstitucionReferencia(ActionEvent event) throws IOException {

        try {
            Parent formulario_Consulta_Institucion_Referencia = FXMLLoader.load(getClass().getClassLoader().getResource("consultas/hospitalrecibepaciente/HospitalRecibePaciente.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Consulta Personal que Recibe Paciente)");
            stage.setScene(new Scene(formulario_Consulta_Institucion_Referencia, 1040, 400));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirConsultaHistoriaPaciente(ActionEvent event) throws IOException {

        try {
            Parent formulario_Consulta_Historia_Paciente = FXMLLoader.load(getClass().getClassLoader().getResource("consultas/historiaatencion/HistoriaAtencion.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Consulta Historia Atención)");
            stage.setScene(new Scene(formulario_Consulta_Historia_Paciente, 1060, 400));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirNovedadPacinte(ActionEvent event) throws IOException {

        try {
            Parent formulario_informe_atencion = FXMLLoader.load(getClass().getClassLoader().getResource("Informes/novedadatencionpaciente/FormularioNovedadPaciente.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Gestión Informe Atención Paciente)");
            stage.setScene(new Scene(formulario_informe_atencion, 616, 400));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirNovedadTitulosAcademicos(ActionEvent event) throws IOException {

        try {
            Parent formulario_informe_titulos_academicos = FXMLLoader.load(getClass().getClassLoader().getResource("Informes/novedadtitulos/FormularioNovedadTitulosAcademicos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Gestión Informe Titulos Académicos)");
            stage.setScene(new Scene(formulario_informe_titulos_academicos));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirInformePersonalRecibe(ActionEvent event) throws IOException {

        try {
            Parent formulario_informe_personal_recibe = FXMLLoader.load(getClass().getClassLoader().getResource("Informes/ubicacionpaciente/UbicacionPaciente.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana(Gestión Informe Ubicación Paciente)");
            stage.setScene(new Scene(formulario_informe_personal_recibe, 500, 400));
            stage.setResizable(false);
            stage.getIcons().add(new Image("estrella_vida.jpg"));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            //Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void cerrarAplicacion(ActionEvent event) {

        System.exit(0);

    }


}




