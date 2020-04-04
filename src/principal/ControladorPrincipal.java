package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

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
    private MenuItem mnuInstitucionAcademica;
    @FXML
    private MenuItem mnuCargo;
    @FXML
    private MenuItem mnuInstitucionReferencia;
    @FXML
    private MenuItem mnuDatosFamiliar;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void abrirTipoDocumento(ActionEvent event) throws IOException {
        try {
            Parent formulario_tipo_documento;
            formulario_tipo_documento = FXMLLoader.load(getClass().getClassLoader().getResource("tipodocumento/formulariotipodocumento/FormularioTipoDocumento.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Tipo de Documento)");
            stage.setScene(new Scene(formulario_tipo_documento, 721, 427));
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

            Parent formulario_datos_persona = FXMLLoader.load(getClass().getClassLoader().getResource("datospersona/formulariopersona/FormularioDatosPersona.fxml"));
            Stage stage = new Stage();
            AnchorPane panel_principal = new AnchorPane();
            Scene scene = new Scene(panel_principal);
            stage.setTitle("AP_Humana (Gestión Datos Persona)");
            stage.setScene(new Scene(formulario_datos_persona, 800, 600));
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
            Parent formulario_EPS = FXMLLoader.load(getClass().getClassLoader().getResource("eps/formularioeps/FormularioEPS.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión EPS)");
            stage.setScene(new Scene(formulario_EPS, 981, 427));
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
            Parent formulario_datos_familiar = FXMLLoader.load(getClass().getClassLoader().getResource("datosFamiliar/formulariofamiliar/FormularioDatosFamiliar.fxml"));
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
            Parent formulario_Personal_salud = FXMLLoader.load(getClass().getClassLoader().getResource("personalSalud/formulariopersonalsalud/FormularioPersonalSalud.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Personal Salud)");
            stage.setScene(new Scene(formulario_Personal_salud, 830, 545));
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

        Parent formulario_Tipo_Titulo_Academico;
        try {
            formulario_Tipo_Titulo_Academico = FXMLLoader.load(getClass().getClassLoader().getResource("tipoTituloAcademico/formularioTipoTituloAcademico/FormularioTipoTituloAcademico.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Tipo Título Académico)");
            stage.setScene(new Scene(formulario_Tipo_Titulo_Academico, 500, 300));
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
    public void ventanaInstitucionAcademica(){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("institucionAcademica/formulario/FormularioInstitucionAcademica.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("MODULO INSTITUCIÓN ACADEMICA");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }//fin del metodo ventanaInstitucionReferencia()

    @FXML
    public void ventanaCargos(){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("cargo/formulario/formularioCargo.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("MODULO DE CARGOS");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }//fin del metodo ventanaCargos()

    @FXML
    public void ventanaInstitucionReferencia(){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("institucionreferencia/formulario/FormularioInstitucionReferencia.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
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
            stage.setScene(new Scene(formulario_Cargo_Empleado, 500, 300));
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
            stage.setScene(new Scene(formulario_Institucion_Referencia, 500, 343));
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

        Parent formulario_Registro_Atencion_Paciente;
        try {
            formulario_Registro_Atencion_Paciente = FXMLLoader.load(getClass().getClassLoader().getResource("registroAtencionPaciente/FormularioRegistroAtencion.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AP_Humana (Gestión Registro Atención)");
            stage.setScene(new Scene(formulario_Registro_Atencion_Paciente, 900, 450));
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
            Parent formulario_Medicamentos = FXMLLoader.load(getClass().getClassLoader().getResource("medicamento/formularioMedicamento/FormularioMedicamentos.fxml"));
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
            stage.setScene(new Scene(formulario_Pacientes_Atendidos, 700, 400));
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
            stage.setScene(new Scene(formulario_informe_titulos_academicos, 500, 400));
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




