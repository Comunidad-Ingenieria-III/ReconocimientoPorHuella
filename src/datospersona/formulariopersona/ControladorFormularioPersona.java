package datospersona.formulariopersona;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import conexionBD.ConexionRoot;
import datosFamiliar.dtofamiliar.Familiar;
import datosFamiliar.facadefamiliar.Facade;
import datospersona.dto.BusquedaDeFamiliar;
import datospersona.dto.Persona;
import datospersona.facade.FacadePersona;
import eps.dto.DtoEps;
import eps.facadeeps.FacadeEps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import persona_familiar.per_fami_facade.Per_Fami_Facade;
import personalSalud.personalsaluddto.BusquedaDePersonal;
import personalSalud.personalsaluddto.PersonalSalud;
import personal_salud_titulo.psdto.PsDto;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorFormularioPersona implements Initializable {

    Persona persona = new Persona();
    FacadePersona facadepersona = new FacadePersona();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeEps facadeEps = new FacadeEps();
    Facade facadefamiliar = new Facade();
    Per_Fami_Facade per_fami_facade = new Per_Fami_Facade();
    private final boolean estado = true;

    @FXML
    private TextField tf_idpersona;
    @FXML
    private TextField tf_primerNombre;
    @FXML
    private TextField tf_segundoNombre;
    @FXML
    private TextField tf_primerApellido;
    @FXML
    private TextField tf_segundoApellido;
    @FXML
    private TextField tf_colnombre1;
    @FXML
    private TextField tf_coltelefono;
    @FXML
    private DatePicker dp_fechaNacimiento;
    @FXML
    private TextField tf_direccion;
    @FXML
    private ComboBox<DtoTipoDocumento> cbxtipodocumento;
    @FXML
    private ComboBox<String> cbxsexo;
    @FXML
    private ComboBox<DtoEps> cbxtipoeps;
    @FXML
    private ComboBox<Persona> cbx_documentopersona;
    @FXML
    private ComboBox<Familiar> cbx_documentofamiliar;
    @FXML
    private ComboBox<Familiar> cbx_nombrefamiliar;
    @FXML
    private ComboBox<Familiar> cbx_telefonofamiliar;
    @FXML
    private DatePicker dp_ingresofamiliar;

    @FXML
    private TableView<Per_Fami_Dto> tb_familiar;
    @FXML
    private TableColumn<Per_Fami_Dto, String> colIdpersona;
    @FXML
    private TableColumn<Per_Fami_Dto, String> colIdFamiliar;
    @FXML
    private TableColumn<Per_Fami_Dto, String> colnombre1;
    @FXML
    private TableColumn<Per_Fami_Dto, String> coltelefono;
    @FXML
    private TableColumn<Per_Fami_Dto, Date> colFechaIngreso;

    @FXML
    private TextArea ta_alergicoA;
    @FXML
    private TextArea ta_enfermedadSufre;
    @FXML
    private TextArea ta_observaciones;
    @FXML
    private TextArea txtArea;
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
    private Button bt_hulla;
    @FXML
    private Button bt_agregarfamiliar;
    @FXML
    private int valor = 1;

    private ObservableList<Per_Fami_Dto> familiares;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        dp_fechaNacimiento.setValue(LocalDate.now());
        dp_ingresofamiliar.setValue(LocalDate.now());

        familiares = FXCollections.observableArrayList();
        tb_familiar.setItems(familiares);

        iniciarCbxSexo();
        iniciarCbxDocumento();
        iniciarEps();
        iniciarFamiliar();
        initializeTableColumn();
        deshabilitarBotones();
        deshabilitarCampos();
        Iniciar();
        validarId();
        iniciarDocumentoPersona();

    }

    public void initializeTableColumn() {
        colIdpersona.setCellValueFactory(new PropertyValueFactory<>("idPersona"));
        colIdFamiliar.setCellValueFactory(new PropertyValueFactory<>("idFamiliar"));
        colnombre1.setCellValueFactory(new PropertyValueFactory<>("nombre1"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
    }

    //Varible que permite iniciar el dispositivo de lector de huella conectado
    // con sus distintos metodos.
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

    //Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    // y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    //Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
    // o verificarla con alguna guardada en la BD
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    //Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    // necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate template;

    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("La Huella Digital ha sido Capturada");
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
                    }
                });
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Retira el Dedo del Lector de Huella");
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Error: " + e.getError());
                    }
                });
            }
        });
    }

    public void EnviarTexto(String string) {
        txtArea.appendText(string + "\n");
    }

    public void EstadoHuellas() {
        EnviarTexto("Muestra de Huellas Restantes para Guardar " + Reclutador.getFeaturesNeeded());
    }

    public void start() {
        Lector.startCapture();
        EnviarTexto("Utilizando el Lector de Huella Dactilar ");
    }

    public void stop() {
        Lector.stopCapture();
        EnviarTexto("No se está usando el Lector de Huella Dactilar ");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
    }

    public void DibujarHuella(java.awt.Image image) {

    }

    public java.awt.Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public void ProcesarCaptura(DPFPSample sample) {
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
        if (featuresinscripcion != null)
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                try {
                    Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear
                } catch (DPFPImageQualityException e) {
                    e.printStackTrace();
                }

                // Dibuja la huella dactilar capturada.
                java.awt.Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

                //btnVerificar.setEnabled(true);
                //btnIdentificar.setEnabled(true);

            } finally {
                EstadoHuellas();
                // Comprueba si la plantilla se ha creado.
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:    // informe de éxito y detiene  la captura de huellas
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        EnviarTexto("Listo la huella ha sido capturada correctamente ahora puede guardarla");
                        break;

                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(null, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
    }

    @FXML
    private void guardarHuella() {
        boolean documento = buscarDocumentos(tf_idpersona.getText());
        if (!documento) {
            int resultado;
            ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
            Integer tamañoHuella = template.serialize().length;
            conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Persona persona = null;
            try {
                conn = ConexionRoot.getConexion();
                stmt = conn.prepareStatement("select idpersona, huella, huella1 from datos_persona where idpersona= ?");
                stmt.setInt(1, Integer.parseInt(tf_idpersona.getText()));
                rs = stmt.executeQuery();
                if (rs.next()) {
                    //Lee la plantilla de la base de datos
                    byte templateBuffer[] = rs.getBytes(2);
                    //Crea una nueva plantilla a partir de la guardada en la base de datos
                    DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                    //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                    setTemplate(referenceTemplate);
                    //Compara las caracteriticas de la huella recientemente capturda con la
                    // plantilla guardada al usuario especifico en la base de datos
                    DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                    //compara las plantilas (actual vs bd)
                    if (result.isVerified()) {
                        //facadepersona.modificarPersona(modificarPersona());
                        JOptionPane.showMessageDialog(null, "La huella ya existe, coloque un dedo diferente");
                    } else {
                        PreparedStatement guardarStmt2 = conn.prepareStatement("update datos_persona set huella1=? where (idpersona=?)");
                        guardarStmt2.setBinaryStream(1, datosHuella, tamañoHuella);
                        guardarStmt2.setInt(2, Integer.parseInt(tf_idpersona.getText()));

                        //Ejecuta la sentencia
                        guardarStmt2.execute();
                        guardarStmt2.close();
                        JOptionPane.showMessageDialog(null, "Huella Guardada Correctament");
                    }
                } else if (!rs.next()) {

                    try {
                        guardarPersona();
                        iniciarDocumentoPersona();
                        cancelar();
                    } catch (RuntimeException e) {
                        throw new RuntimeException("Error SQL - Agregar()!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            int res = facadepersona.modificarPersona(modificarPersona());
            if (res == 1) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("Registro Modificado Correctamente");
                msg.setHeaderText("Exito.");
                msg.show();
                cancelar();
            } else {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Persona ");
                msg.setContentText("No Fue Posible Modificar El Registro ");
                msg.setHeaderText("Algo Salio Mal.");
                msg.show();
                cancelar();
            }
        }
    }

    public boolean buscarDocumentos(String idPersona) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = facadepersona.buscarPersonaPrimaryKey(idPersona);
        boolean resultado;
        if (documento) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }

    @FXML
    private Persona crearPersona() {
        String idpersona = tf_idpersona.getText();
        String primerNombre = tf_primerNombre.getText();
        String segundoNombre = tf_segundoNombre.getText();
        String primerApellido = tf_primerApellido.getText();
        String segundoApellido = tf_segundoApellido.getText();
        Date fechaNacimiento = Date.valueOf(dp_fechaNacimiento.getValue());
        String direccion = tf_direccion.getText();
        String sexo = cbxsexo.getValue();
        String alegicoA = ta_alergicoA.getText();
        String enfermedadSufre = ta_enfermedadSufre.getText();
        String observaciones = ta_observaciones.getText();
        ByteArrayInputStream huella = new ByteArrayInputStream(template.serialize());
        int huella1 = template.serialize().length;
        String ta_tipoDocumento = cbxtipodocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        String ta_idEps = cbxtipoeps.getSelectionModel().getSelectedItem().getIdEps();
        Persona persona = new Persona(idpersona, primerNombre, segundoNombre, primerApellido, segundoApellido,
                fechaNacimiento, direccion, sexo, alegicoA, enfermedadSufre, observaciones, huella, huella1, ta_tipoDocumento, ta_idEps, estado);
        return persona;
    }

    @FXML
    private Persona modificarPersona() {
        String idpersona = tf_idpersona.getText();
        String primerNombre = tf_primerNombre.getText();
        String segundoNombre = tf_segundoNombre.getText();
        String primerApellido = tf_primerApellido.getText();
        String segundoApellido = tf_segundoApellido.getText();
        Date fechaNacimiento = Date.valueOf(dp_fechaNacimiento.getValue());
        String direccion = tf_direccion.getText();
        String sexo = cbxsexo.getValue();
        String alegicoA = ta_alergicoA.getText();
        String enfermedadSufre = ta_enfermedadSufre.getText();
        String observaciones = ta_observaciones.getText();
        String ta_tipoDocumento = cbxtipodocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        String ta_idEps = cbxtipoeps.getSelectionModel().getSelectedItem().getIdEps();
        Persona persona = new Persona(idpersona, primerNombre, segundoNombre, primerApellido, segundoApellido,
                fechaNacimiento, direccion, sexo, alegicoA, enfermedadSufre, observaciones, ta_tipoDocumento, ta_idEps, estado);
        return persona;
    }

    @FXML
    public void consultarPersonaFamiliar() {//Metodo que realiza la consulta de una persona a la BBDD por medio de su cedula, devolviendo todas sus
        //caracteristicas y registros que esta cedula tenga relacionados en la tabla personal_salud_titulo
        if (tf_idpersona.getText().isEmpty()) {
            tf_idpersona.setDisable(false);
            tf_primerNombre.setDisable(true);
            tf_segundoNombre.setDisable(true);
            tf_primerApellido.setDisable(true);
            tf_segundoNombre.setDisable(true);
            dp_fechaNacimiento.setDisable(true);
            tf_direccion.setDisable(true);
            cbxsexo.setDisable(true);
            ta_alergicoA.setDisable(true);
            ta_enfermedadSufre.setDisable(true);
            ta_observaciones.setDisable(true);
            tf_idpersona.requestFocus();
            bt_crear.setDisable(true);
            bt_modificar.setDisable(false);
            bt_inhabilitar.setDisable(false);
            cbx_documentopersona.setDisable(true);
            cbx_documentofamiliar.setDisable(true);
            dp_ingresofamiliar.setDisable(true);

        } else {
            BusquedaDeFamiliar busqueda = facadepersona.buscarPersona(tf_idpersona.getText());
            Persona persona = busqueda.getPersona();

            if (persona == null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("El Registro No Existe ");
                msg.setHeaderText("Información.");
                msg.show();
                cancelar();

            } else if (!persona.isEstado()) {//Condicional que verifica si el objeto personal_salud
                // que se acaba de recuperar esta en estado inactivo en la BBDD
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("El Registro Con Nro De Documento: " + persona.getIdpersona() + " Se Encuentra Inactivo \n"
                        + "Comuniquese Con El Adminsitrador Para Activar De Nuevo Este Registro");
                msg.setHeaderText("Información.");
                msg.show();
                cancelar();

            } else {
                cbxtipodocumento.setValue(facadeTipoDocumento.obtenerPorId(persona.getTipoDocumento()));
                tf_primerNombre.setText(persona.getPrimerNombre());
                tf_segundoNombre.setText(persona.getSegundoNombre());
                tf_primerApellido.setText(persona.getPrimerApellido());
                tf_segundoApellido.setText(persona.getSegundoApellido());
                dp_fechaNacimiento.setValue(persona.getFechaNacimiento().toLocalDate());
                tf_direccion.setText(persona.getDireccion());
                cbxsexo.setValue(persona.getSexo());
                ta_alergicoA.setText(persona.getAlergicoA());
                ta_enfermedadSufre.setText(persona.getEnfermedadSufre());
                ta_observaciones.setText(persona.getObservaciones());
                cbxtipoeps.setValue(facadeEps.obtenerPorId(persona.getIdEps()));

                familiares = FXCollections.observableArrayList(busqueda.getListafamiliar());

                tb_familiar.setItems(familiares);
                initializeTableColumn();
                bt_consultar.setDisable(true);
                bt_crear.setDisable(true);

            }
        }

    }

    @FXML
    public void modificar() {//Metodo que se ejecuta al presionar el boton modificar
        cbxtipodocumento.setDisable(false);
        tf_idpersona.setDisable(true);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        dp_fechaNacimiento.setDisable(false);
        tf_direccion.setDisable(false);
        cbxsexo.setDisable(false);
        cbxtipoeps.setDisable(false);
        ta_alergicoA.setDisable(false);
        ta_enfermedadSufre.setDisable(false);
        ta_observaciones.setDisable(false);
        tf_primerNombre.requestFocus();
        valor = 0;            //Ingreso una variable Jose Martin campo
        bt_modificar.setDisable(true);
        bt_guardar.setDisable(false);
        bt_hulla.setDisable(false);
        cbx_documentopersona.setDisable(false);
        cbx_documentofamiliar.setDisable(false);
        cbx_nombrefamiliar.setDisable(false);
        cbx_telefonofamiliar.setDisable(false);
        dp_ingresofamiliar.setDisable(false);
        bt_agregarfamiliar.setDisable(false);
        tb_familiar.setDisable(false);
        //---------------------------------
    }

    @FXML
    public void eliminarPersona() {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Gestiones - Persona");
        msg.setContentText("¿Está seguro de eliminar la Persona?");
        msg.setHeaderText("Información");
        Optional<ButtonType> action = msg.showAndWait();
        if (action.get() == ButtonType.OK) {
            boolean respuesta = facadepersona.eliminarPersonal(tf_idpersona.getText());
            if (respuesta) {
                Alert msge = new Alert(Alert.AlertType.WARNING);
                msge.setTitle("Gestiones - Persona ");
                msge.setContentText("No puedes Eliminar! \n" + "El Documento Tiene Registros Dependientes!\n"
                        + "Asegurese De Eliminar Los Registros Que Dependen De Este.");
                msge.setHeaderText("Infomación.");
                msge.show();
                cancelar();
            } else {
                Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                msg2.setTitle("Gestiones - Persona");
                msg2.setContentText("Registro Eliminado Correctamente");
                msg2.setHeaderText("Información.");
                msg2.show();
                cancelar();
            }
        }
    }

    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Masculino", "Femenino");
        cbxsexo.setItems(items);
    }

    @FXML
    public void iniciarCbxDocumento() {
        ObservableList<DtoTipoDocumento> listatipodocumento = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());
        cbxtipodocumento.setItems(listatipodocumento);
    }

    @FXML
    public void iniciarEps() {
        ObservableList<DtoEps> listaeps = FXCollections.observableArrayList(facadeEps.CargarEps());
        cbxtipoeps.setItems(listaeps);
    }

    @FXML
    public void iniciarDocumentoPersona() {
        ObservableList<Persona> listapersonas = FXCollections.observableArrayList(facadepersona.cargarPersona());
        cbx_documentopersona.setItems(listapersonas);
    }

    @FXML
    public void iniciarFamiliar() {
        ObservableList<Familiar> listafamiliar = FXCollections.observableArrayList(facadefamiliar.obtenerTodosFamiliares());
        cbx_documentofamiliar.setItems(listafamiliar);
        cbx_nombrefamiliar.setItems(listafamiliar);
        cbx_telefonofamiliar.setItems(listafamiliar);
    }

    @FXML
    public void cancelar() {
        cbxtipodocumento.setValue(null);
        tf_idpersona.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        dp_fechaNacimiento.setValue(null);
        tf_direccion.setText("");
        ta_alergicoA.setText("");
        ta_enfermedadSufre.setText("");
        ta_observaciones.setText("");
        cbxsexo.setValue(null);
        cbxtipoeps.setValue(null);
        txtArea.setText("");
        cbx_documentopersona.setValue(null);
        cbx_documentofamiliar.setValue(null);
        dp_ingresofamiliar.setValue(null);
        colIdpersona.setText("");
        colIdFamiliar.setText("");
        colnombre1.setText("");
        coltelefono.setText("");
        cbx_nombrefamiliar.setValue(null);
        cbx_telefonofamiliar.setValue(null);
        colFechaIngreso.setText("");
        deshabilitarBotones();
        deshabilitarCampos();
    }

    @FXML
    public void limpiar() {
        cbxtipodocumento.setValue(null);
        tf_idpersona.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        dp_fechaNacimiento.setValue(null);
        tf_direccion.setText("");
        ta_alergicoA.setText("");
        ta_enfermedadSufre.setText("");
        ta_observaciones.setText("");
        cbxsexo.setValue(null);
        cbxtipoeps.setValue(null);
        txtArea.setText("");
        colIdpersona.setText("");
        colIdFamiliar.setText("");
        colFechaIngreso.setText("");
        cbx_documentopersona.setValue(null);
        cbx_documentofamiliar.setValue(null);
    }


    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(false);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        bt_hulla.setDisable(false);
        bt_agregarfamiliar.setDisable(false);
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
        bt_hulla.setDisable(true);
        bt_agregarfamiliar.setDisable(true);

    }


    @FXML
    private void habilitarCampos() {
        cbxtipodocumento.setDisable(false);
        tf_idpersona.setDisable(false);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        tf_direccion.setDisable(false);
        cbxsexo.setDisable(false);
        cbxtipoeps.setDisable(false);
        ta_alergicoA.setDisable(false);
        ta_enfermedadSufre.setDisable(false);
        ta_observaciones.setDisable(false);
        txtArea.setDisable(false);
        dp_fechaNacimiento.setDisable(false);
        cbx_documentopersona.setDisable(false);
        cbx_documentofamiliar.setDisable(false);
        cbx_nombrefamiliar.setDisable(false);
        cbx_telefonofamiliar.setDisable(false);
        dp_ingresofamiliar.setDisable(false);
        cbxtipodocumento.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        cbxtipodocumento.setDisable(true);
        tf_idpersona.setDisable(true);
        tf_primerNombre.setDisable(true);
        tf_segundoNombre.setDisable(true);
        tf_primerApellido.setDisable(true);
        tf_segundoApellido.setDisable(true);
        tf_direccion.setDisable(true);
        cbxtipoeps.setDisable(true);
        ta_alergicoA.setDisable(true);
        ta_enfermedadSufre.setDisable(true);
        ta_observaciones.setDisable(true);
        dp_fechaNacimiento.setDisable(true);
        cbxsexo.setDisable(true);
        txtArea.setDisable(true);
        cbx_documentopersona.setDisable(true);
        cbx_documentofamiliar.setDisable(true);
        cbx_nombrefamiliar.setDisable(true);
        cbx_telefonofamiliar.setDisable(true);
        dp_ingresofamiliar.setDisable(true);


    }

    @FXML
    private void eventoCrear() {
        bt_crear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                limpiar();
                habilitarBotones();
                bt_inhabilitar.setDisable(true);
                bt_modificar.setDisable(true);
                bt_guardar.setDisable(false);
                bt_consultar.setDisable(true);

                cbxtipodocumento.requestFocus();
                familiares.clear();
                //valor = 1;
            }
        });
    }

    @FXML
    private void guardarPersona() {
        validar();

        facadepersona.insertarPersona(crearPersona());
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Pacientes - registro");
        msg.setContentText("El Paciente ha sido agregado correctamente");
        msg.setHeaderText("Resultado");
        msg.show();
        bt_crear.setDisable(false);


    }

    @FXML
    public void validarId() {//Metodo para validar que el Id del cargo solo sean numeros
        tf_idpersona.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);

                if (!Character.isDigit(car)) {
                    event.consume();
                }

            }

        });
    }

    public void validar() {
        //Validamos que los campos requeridos no queden vacios
        if (cbxtipodocumento.getSelectionModel().getSelectedItem().equals(null) || tf_idpersona.getText().equals("")
                || tf_primerNombre.getText().equals("") || tf_primerApellido.getText().equals("") || dp_fechaNacimiento.getValue().equals(null)
                || tf_direccion.getText().equals("") || ta_alergicoA.getText().equals("") || ta_enfermedadSufre.getText().equals("")
                || cbxsexo.getSelectionModel().getSelectedItem().equals(null) || cbxtipoeps.getSelectionModel().getSelectedItem().equals(null)
        ) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Pacientes - registro");
            msg.setContentText("Hay campos vacios,Debe llenar todos los campos");
            msg.setHeaderText("Resultado");
            msg.show();
        }
    }

    @FXML
    public boolean recorrerTablaTitulos(List<Per_Fami_Dto> familiares, Per_Fami_Dto per_fami_dto) {//Metodo para recorrer la tabla con el fin de no ingresar regisstros duplicados
        boolean resultado = false;
        for (int i = 0; i < familiares.size(); i++) {
            if (familiares.get(i).getIdPersona().equals(per_fami_dto.getIdPersona()) && familiares.get(i).getIdFamiliar().equals(per_fami_dto.getIdFamiliar())
            ) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }//fin del metodo recorrerTablaTitulos()

    @FXML
    public boolean buscarDocumento(String idpersona) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = per_fami_facade.buscarPorId(idpersona);
        boolean resultado;
        if (documento) {
            resultado = true;
        } else {
            resultado = false;

        }
        return resultado;
    }

    @FXML
    public void agregarFamiliar() {//Metodo para agregar titulos a la tabla personal-salud-titulo de la BBDD
        // y a la tabla del formulario previamente validados para evitar registros dulicados

        Per_Fami_Dto per_fami_dto = new Per_Fami_Dto(

                cbx_documentopersona.getSelectionModel().getSelectedItem().getIdpersona(),
                cbx_documentofamiliar.getSelectionModel().getSelectedItem().getIdFamiliar().toString(),
                cbx_nombrefamiliar.getSelectionModel().getSelectedItem().getPrimerNombre().toString(),
                cbx_telefonofamiliar.getSelectionModel().getSelectedItem().getTelFamiliar().toString(),
                //tf_colnombre1.getText(),
                //tf_coltelefono.getText(),
                Date.valueOf(dp_ingresofamiliar.getValue()),
                estado
        );

        DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");//Formato de fecha para mostrarle al usuario.
        String convertido = fechaHora.format(per_fami_dto.getFechaIngreso());//Formatear una fecha Date a String

        if (per_fami_dto.getFechaIngreso().after(new java.util.Date())) {//Condicional que verifica si la fecha seleccionada es superior a la actual
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Fecha Ingreso - Familiar");
            msg.setContentText("Debe Ingresar Una Fecha Valida: \n" + "La Fecha: " + convertido + " Es Superior A La Fecha De Hoy");
            msg.show();
            dp_ingresofamiliar.requestFocus();
        } else {
            per_fami_facade.agregar(per_fami_dto);
            familiares.add(per_fami_dto);
            Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Fecha Ingreso - Familiar");
            msg.setContentText("La fecha de Ingreso del Familiar ha sido agregada correctamente");
            msg.setHeaderText("Información");
            msg.show();
            cancelar();

            /*if (familiares.isEmpty()) {
                //boolean res = buscarDocumento(per_fami_dto.getIdPersona());
                if (res) {
                    per_fami_facade.agregar(per_fami_dto);
                    familiares.add(per_fami_dto);
                    //limpiarComponentes();
                } else {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Personal Salud");
                    msg.setContentText("El Documento Nro. " + per_fami_dto.getIdPersona() + " No Se Encuentra Registrado!\n" +
                            "Debe Registrarlo Para Poder Asignarle Titulos");
                    msg.show();

                }
            } else {
                boolean resultado = recorrerTablaTitulos(familiares, per_fami_dto);
                if (resultado) {
                    Alert msg = new Alert(Alert.AlertType.ERROR);
                    msg.setTitle("Gestiones - Personal Salud");
                    msg.setContentText("El Registro Ya Existe ");
                    msg.show();
                } else {
                    per_fami_facade.agregar(per_fami_dto);
                    familiares.add(per_fami_dto);
                    //limpiarComponentes();

                }

            }*/
        }
    }


    @FXML
    private void setCerrarFormularioPersonas(ActionEvent event) {
        Stage stage = (Stage) bt_crear.getScene().getWindow();
        stage.close();
    }


}

