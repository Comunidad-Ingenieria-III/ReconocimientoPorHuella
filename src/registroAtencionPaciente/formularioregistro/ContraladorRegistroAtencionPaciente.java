package registroAtencionPaciente.formularioregistro;

import cargo.dto.Cargo;
import cargo.facade.FacadeCargo;
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
import datospersona.dto.Persona;
import datospersona.facade.FacadePersona;
import eps.dto.DtoEps;
import institucionreferencia.dto.InstitucionReferencia;
import institucionreferencia.facade.FacadeInstitucionReferencia;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import medicamento.dto.Medicamento;
import medicamento.facade.FacadeMedicamento;
import personalSalud.personalsaluddto.PersonalSalud;
import personalSalud.personalsaludfacade.PersonalSaludFacade;
import documento_entrega.documentoentregadto.DocumentoEntregaDto;
import documento_entrega.documentoentregafacade.DocumentoEntregaFacade;
import registroAtencionPaciente.dtoregistro.RegistroDto;
import registroAtencionPaciente.facaderegistro.RegistroFacade;
import sun.util.calendar.LocalGregorianCalendar;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContraladorRegistroAtencionPaciente implements Initializable {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    RegistroDto registroDto = new RegistroDto();
    FacadePersona facadePersona = new FacadePersona();
    RegistroFacade registroFacade = new RegistroFacade();
    FacadeMedicamento facadeMedicamento = new FacadeMedicamento();
    PersonalSaludFacade personalSaludFacade = new PersonalSaludFacade();
    FacadeInstitucionReferencia facadeInstitucionReferencia = new FacadeInstitucionReferencia();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeCargo facadeCargo = new FacadeCargo();
    DocumentoEntregaFacade documentoEntregaFacade = new DocumentoEntregaFacade();

    private final boolean estado = true;

    @FXML
    private TabPane registroPacientes;
    @FXML
    private Tab tp_datosPaciente;
    @FXML
    private Tab tp_atencionPaciente;
    @FXML
    private Tab tp_datosReferencia;

    //=========Primera Ficha del TabPane============

    @FXML
    private TextField tf_idpersonadps;
    @FXML
    private TextField tf_primerNombredps;
    @FXML
    private TextField tf_primerApellidodps;
    @FXML
    private TextField tf_fechaNacimiento;
    @FXML
    private TextField tf_nombreeps;
    @FXML
    private TextField tf_nombrefamiliar;
    @FXML
    private TextField tf_numerotelefonicofamiliar;
    @FXML
    private TextArea ta_alergicoA;
    @FXML
    private TextArea ta_enfermedadSufre;
    @FXML
    private TextArea ta_observaciones;
    @FXML
    private TextArea ta_huella;


    //=========Segundo Ficha del TabPane==========
    @FXML
    private TextField tf_fechaAtencionPaciente;
    @FXML
    private TextField tf_horaAtencionPaciente;
    @FXML
    private ComboBox<String> cbx_estadoPaciente;
    @FXML
    private ComboBox<String> cbx_gasglowPaciente;
    @FXML
    private ComboBox<String> cbx_signosVitalesPaciente;
    @FXML
    private ComboBox<Medicamento> cbx_medicamentoSuministrado;
    @FXML
    private ComboBox<PersonalSalud> cbx_documentoAPH;
    @FXML
    private ComboBox<InstitucionReferencia> cbx_institucionReferencia;
    @FXML
    private TextField tf_direccionAccidente;
    @FXML
    private TextField tf_dosisMedicamento;
    @FXML
    private TextField tf_codigoRemision;
    @FXML
    private TextField tf_idpersonadpsc;
    @FXML
    private TextField tf_primerNombredpsc;
    @FXML
    private TextField tf_primerApellidodpsc;


    //=========Tercera Ficha del TabPane==========

    @FXML
    private ComboBox<DtoTipoDocumento> cbx_tipoDocumento;
    @FXML
    private TextField tf_idPersonalRecibe;
    @FXML
    private TextField tf_primerNombreRecibe;
    @FXML
    private TextField tf_segundoNombreRecibe;
    @FXML
    private TextField tf_primerApellidoRecibe;
    @FXML
    private TextField tf_segundoApellidoRecibe;
    @FXML
    private ComboBox<Cargo> cbx_idCargo;
    @FXML
    private TextField tf_fechaRecepcionPaciente;
    @FXML
    private TextField tf_horaRecepcionPaciente;
    @FXML
    private ComboBox<RegistroDto> cbx_codigoRemision;
    @FXML
    private TextArea ta_observacionesPr;

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
    private Button bt_guardarPr;
    @FXML
    private Button bt_modificar;
    @FXML
    private Button bt_inhabilitar;
    @FXML
    private Button bt_siguiente;
    @FXML
    private Button bt_anterior;

    private int valor = 0;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        start();
        Iniciar();
        iniciarCbxCondicionPaciente();
        iniciarCbxGlasgowPaciente();
        iniciarCbxSignosVitales();
        iniciarCbxMedicamento();
        iniciarCbxPersonalSalud();
        iniciarCbxInstitucionReferencia();
        iniciarCbxTipoDocumento();
        iniciarCbxCargo();
        iniciarCbxRemision();
        buscarR();
        //metodoFechaHora();
        horaAtencion();
        fechaAtencion();
        validarId();

    }

    private void horaAtencion() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            tf_horaAtencionPaciente.setText(LocalDateTime.now().format(formatter));
            tf_horaRecepcionPaciente.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void fechaAtencion() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            tf_fechaAtencionPaciente.setText(LocalDateTime.now().format(formatter));
            tf_fechaRecepcionPaciente.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void metodoFechaHora() {

        Thread clock = new Thread(() -> {
            while (true) {
                //SimpleDateFormat simpli = new SimpleDateFormat("HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                int second = cal.get(Calendar.SECOND);
                int minute = cal.get(Calendar.MINUTE);
                int hour = cal.get(Calendar.HOUR);

                Platform.runLater(() -> {
                    tf_horaRecepcionPaciente.setText(hour + ":" + (minute) + ":" + second);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    //...
                }
            }
        });
        clock.start();
    }

    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("La Huella Digital ha sido Capturada");
                        try {
                            ProcesarCaptura(e.getSample());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        EnviarTexto("El Sensor de Huella Digital esta Activado ");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Desactivado ");
                    }
                });
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido colocado sobre el Lector ");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Retira el Dedo del Lector ");
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
        ta_huella.appendText(string + "\n");
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

    public void DibujarHuella(Image image) {

    }

    public Image CrearImagenHuella(DPFPSample sample) {
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

    public void ProcesarCaptura(DPFPSample sample) throws IOException {
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
                Image image = CrearImagenHuella(sample);
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
                        EnviarTexto("La huella ha sido capturada correctamente ahora puede verificar la persona");
                        identificarHuella();
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
    public void identificarHuella() throws IOException {

        try {
            //Establece los valores para la sentencia SQL
            conn = ConexionRoot.getConexion();

            //Obtiene todas las huellas de la bd
            PreparedStatement identificarStmt = conn.prepareStatement("SELECT p.idpersona, p.primerNombre, p.primerApellido, p.fechaNacimiento, p.alergicoA, p.enfermedadSufre, p.observaciones, p.huella, \n" +
                    "ep.nombreEps AS nombreEps, \n" +
                    "f.nombre1, f.telefono as telefono \n" +
                    "FROM persona_familiar AS pf\n" +
                    "INNER JOIN datos_persona AS p ON p.idpersona = pf.idpersona\n" +
                    "INNER JOIN familiar_paciente AS f ON f.idFamiliar = pf.idFamiliar\n" +
                    "INNER JOIN eps AS ep ON p.idEps = ep.idEps");
            //Obtiene todas las huellas de la bd
            ResultSet rsIdentificar = identificarStmt.executeQuery();

            //Si se encuentra el nombre en la base de datos
            //byte templateBuffer[] = null;
            int i = 0;
            while (rsIdentificar.next()) {
                i++;
                System.out.println("SQL:" + rsIdentificar.getString(1) + "\n");
                System.out.println("Contador:" + i + "\n");

                byte templateBuffer[] = rsIdentificar.getBytes("huella");
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);

                // Compara las caracteriticas de la huella recientemente capturda con la
                // alguna plantilla guardada en la base de datos que coincide con ese tipo
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                //compara las plantilas (actual vs bd)
                //Si encuentra correspondencia dibuja el mapa
                //e indica el nombre de la persona que coincidió.
                if (result.isVerified()) {
                    //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
                    //y manda la informacion a los textfield
                    tf_idpersonadps.setText(rsIdentificar.getString("idpersona"));
                    tf_idpersonadpsc.setText(rsIdentificar.getString("idpersona"));
                    tf_primerNombredps.setText(rsIdentificar.getString("primerNombre"));
                    tf_primerNombredpsc.setText(rsIdentificar.getString("primerNombre"));
                    tf_primerApellidodps.setText(rsIdentificar.getString("primerApellido"));
                    tf_primerApellidodpsc.setText(rsIdentificar.getString("primerApellido"));
                    tf_fechaNacimiento.setText(rsIdentificar.getString("fechaNacimiento"));
                    tf_nombreeps.setText(rsIdentificar.getString("nombreEps"));
                    tf_nombrefamiliar.setText(rsIdentificar.getString("nombre1"));
                    tf_numerotelefonicofamiliar.setText(rsIdentificar.getString("telefono"));
                    ta_alergicoA.setText(rsIdentificar.getString("alergicoA"));
                    ta_enfermedadSufre.setText(rsIdentificar.getString("enfermedadSufre"));
                    ta_observaciones.setText(rsIdentificar.getString("observaciones"));
                    return;
                }
            }
            //Si no encuentra alguna huella que coincida lo indica con un mensaje

            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella.");
        } catch (SQLException | RuntimeException ex) {
            //throw new RuntimeException("Error SQL - Buscar Codigo Remision()!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción SQL");
            alert.setHeaderText("Ocurrio el Error:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        /*finally{
        con.desconectar();
        }*/
    }

    @FXML
    private RegistroDto crearRegistro() {
        Date fechaAtencionPaciente = Date.valueOf(tf_fechaAtencionPaciente.getText());
        Time horaAtencionPaciente = Time.valueOf(tf_horaAtencionPaciente.getText());
        String condicionPaciente = cbx_estadoPaciente.getValue();
        String glasgow = cbx_gasglowPaciente.getValue();
        String signosVitales = cbx_signosVitalesPaciente.getValue();
        String lugarAccidente = tf_direccionAccidente.getText();
        String idMedicamento = cbx_medicamentoSuministrado.getSelectionModel().getSelectedItem().getIdMedicamento();
        String dosis = tf_dosisMedicamento.getText();
        String idPersonal = cbx_documentoAPH.getSelectionModel().getSelectedItem().getIdPersonal();
        String idInstiRefe = cbx_institucionReferencia.getSelectionModel().getSelectedItem().getIdInstitucion();
        String codigoAutorizacion = tf_codigoRemision.getText();
        String idpersona = tf_idpersonadpsc.getText();
        String nombrePaciente = tf_primerNombredpsc.getText();
        String apellidoPaciente = tf_primerApellidodpsc.getText();

        RegistroDto registroDto = new RegistroDto(fechaAtencionPaciente, horaAtencionPaciente, condicionPaciente, glasgow, signosVitales,
                lugarAccidente, idMedicamento, dosis, idPersonal, idInstiRefe, codigoAutorizacion, idpersona, nombrePaciente, apellidoPaciente, estado);
        return registroDto;
    }

    @FXML
    private void guardarRegistro() {

        if (tf_direccionAccidente.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo dirección accidente requerido");
            msg.setHeaderText("Debes llenar el campo dirección del accidente");
            msg.show();
            tf_direccionAccidente.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            tf_direccionAccidente.requestFocus();

        } else if (cbx_signosVitalesPaciente.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo signos vitales requerido");
            msg.setHeaderText("Debes llenar el campo signos vitales");
            msg.show();
            cbx_signosVitalesPaciente.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_signosVitalesPaciente.requestFocus();

        } else if (cbx_estadoPaciente.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo condición del paciente requerido");
            msg.setHeaderText("Debes llenar el campo condición del paciente");
            msg.show();
            cbx_estadoPaciente.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_estadoPaciente.requestFocus();

        } else if (cbx_gasglowPaciente.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo glasgow requerido");
            msg.setHeaderText("Debes llenar el campo glasgow");
            msg.show();
            cbx_gasglowPaciente.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_gasglowPaciente.requestFocus();

        } else if (cbx_medicamentoSuministrado.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo medicamento suministrado requerido");
            msg.setHeaderText("Debes llenar el campo medicamento suministrado");
            msg.show();
            cbx_medicamentoSuministrado.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_medicamentoSuministrado.requestFocus();

        } else if (cbx_documentoAPH.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo documento personal APH requerido");
            msg.setHeaderText("Debes llenar el Campo personal APH");
            msg.show();
            cbx_documentoAPH.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_documentoAPH.requestFocus();

        } else if (tf_codigoRemision.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo código remisión requerido");
            msg.setHeaderText("Debes llenar el campo código remisión");
            msg.show();
            tf_codigoRemision.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            tf_codigoRemision.requestFocus();

        } else if (cbx_institucionReferencia.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Atención Paciente");
            msg.setContentText("Campo Institución de Referencia requerido");
            msg.setHeaderText("Debes llenar el campo institución de referencia");
            msg.show();
            cbx_institucionReferencia.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_institucionReferencia.requestFocus();

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Gestiones - Registro Atención Paciente");
            alert.setHeaderText("Esta seguro de guardar el registro");
            alert.setContentText("Este no puede ser modificado después de guardarlo");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.YES) {

                int res = registroFacade.insertarRegistro(crearRegistro());
                if (res == 1) {

                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Registro Atención Paciente");
                    msg.setContentText("Registro de atención agregado correctamente");
                    msg.setHeaderText("Información.");
                    msg.show();
                    iniciarCbxRemision();
                    cbx_codigoRemision.setValue(registroFacade.buscarCodigoRemision(tf_codigoRemision.getText()));
                    cbx_codigoRemision.setDisable(true);
                } else {

                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.setTitle("Gestiones - Registro Atención Paciente");
                    msg.setContentText("No fue posible agregar el registro");
                    msg.setHeaderText("Algo salio mal.");
                    msg.show();
                }

            } else if (action.get() == ButtonType.NO) {
                limpiar();
            }

        }
    }

    @FXML
    private void guardarDocumentoReferencia() {

        if (cbx_tipoDocumento.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Entrega");
            msg.setContentText("Campo tipo documento requerido");
            msg.setHeaderText("Debes llenar el campo tipo de documento");
            msg.show();
            cbx_tipoDocumento.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_tipoDocumento.requestFocus();

        } else if (tf_idPersonalRecibe.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Entrega");
            msg.setContentText("Campo número documento requerido");
            msg.setHeaderText("Debes llenar el campo número de documento");
            msg.show();
            tf_idPersonalRecibe.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            tf_idPersonalRecibe.requestFocus();

        } else if (tf_primerNombreRecibe.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Entrega");
            msg.setContentText("Campo primer nombre requerido");
            msg.setHeaderText("Debes llenar el campo primer nombre");
            msg.show();
            tf_primerNombreRecibe.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            tf_primerNombreRecibe.requestFocus();

        } else if (tf_primerApellidoRecibe.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Entrega");
            msg.setContentText("Campo primer apellido requerido");
            msg.setHeaderText("Debes llenar el campo primer apellido");
            msg.show();
            tf_primerApellidoRecibe.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            tf_primerApellidoRecibe.requestFocus();

        } else if (cbx_idCargo.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Entrega");
            msg.setContentText("Campo cargo requerido");
            msg.setHeaderText("Debes llenar el campo cargo");
            msg.show();
            cbx_idCargo.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
            cbx_idCargo.requestFocus();

        } else {

            int res = documentoEntregaFacade.agregarPersonalReferencia(crearDocumetoReferencia());

            if (res == 1) {
                Alert msg1 = new Alert(Alert.AlertType.INFORMATION);
                msg1.setTitle("Gestiones - Registro Entrega");
                msg1.setContentText("Registro de entrega agregado correctamente");
                msg1.setHeaderText("Información");
                msg1.show();
                iniciarCbxRemision();
                stop();

            } else {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Entrega");
                msg.setContentText("No fue posible agregar el registro de entrega");
                msg.setHeaderText("Algo salio mal.");
                msg.show();
            }
        }
    }

    @FXML
    public DocumentoEntregaDto crearDocumetoReferencia() {

        String tipoDocumento = cbx_tipoDocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        String idPersonaRecibe = tf_idPersonalRecibe.getText();
        String nombre1 = tf_primerNombreRecibe.getText();
        String nombre2 = tf_segundoNombreRecibe.getText();
        String apellido1 = tf_primerApellidoRecibe.getText();
        String apellido2 = tf_segundoApellidoRecibe.getText();
        String idCargo = cbx_idCargo.getSelectionModel().getSelectedItem().getIdCargo();
        Date fechaRecepcionPaciente = Date.valueOf(tf_fechaRecepcionPaciente.getText());
        Time horaRecepcionPaciente = Time.valueOf(tf_horaRecepcionPaciente.getText());
        String codigoRemisionPR = cbx_codigoRemision.getSelectionModel().getSelectedItem().getCodigoRemision();
        String observaciones = ta_observacionesPr.getText();

        DocumentoEntregaDto documentoEntregaDto = new DocumentoEntregaDto(tipoDocumento, idPersonaRecibe, nombre1, nombre2, apellido1, apellido2, idCargo,
                fechaRecepcionPaciente, horaRecepcionPaciente, codigoRemisionPR, observaciones, estado);
        return documentoEntregaDto;
    }

    @FXML
    public void buscarRegistros() {

        boolean documento = buscarIdPaciente(tf_idpersonadps.getText());
        if (documento) {

            conn = null;
            PreparedStatement stmt = null;
            ResultSet rset = null;

            try {
                conn = ConexionRoot.getConexion();
                stmt = conn.prepareStatement("SELECT p.idpersona, p.primerNombre, p.primerApellido, p.fechaNacimiento, p.alergicoA, p.enfermedadSufre, p.observaciones, p.huella,\n" +
                        "ep.nombreEps AS nombreEps,\n" +
                        "f.nombre1, f.telefono as telefono \n" +
                        "FROM persona_familiar AS pf\n" +
                        "INNER JOIN datos_persona AS p ON p.idpersona = pf.idpersona\n" +
                        "INNER JOIN familiar_paciente AS f ON f.idFamiliar = pf.idFamiliar\n" +
                        "INNER JOIN eps AS ep ON p.idEps = ep.idEps\n" +
                        "WHERE p.idpersona = ?");

                stmt.setString(1, tf_idpersonadps.getText());
                rset = stmt.executeQuery();

                if (rset.next()) {

                    tf_idpersonadps.setText(rset.getString("idpersona"));
                    tf_idpersonadpsc.setText(rset.getString("idpersona"));
                    tf_primerNombredps.setText(rset.getString("primerNombre"));
                    tf_primerNombredpsc.setText(rset.getString("primerNombre"));
                    tf_primerApellidodps.setText(rset.getString("primerApellido"));
                    tf_primerApellidodpsc.setText(rset.getString("primerApellido"));
                    tf_fechaNacimiento.setText(rset.getString("fechaNacimiento"));
                    tf_nombreeps.setText(rset.getString("nombreEps"));
                    tf_nombrefamiliar.setText(rset.getString("nombre1"));
                    tf_numerotelefonicofamiliar.setText(rset.getString("telefono"));
                    ta_alergicoA.setText(rset.getString("alergicoA"));
                    ta_enfermedadSufre.setText(rset.getString("enfermedadSufre"));
                    ta_observaciones.setText(rset.getString("observaciones"));

                }
            } catch (SQLException | RuntimeException ex) {
                //throw new RuntimeException("Error SQL - buscarRegistros()!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Excepción SQL");
                alert.setHeaderText("Ocurrio el Error:");
                alert.setContentText(ex.getLocalizedMessage());
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestiones - Registro Atención Paciente");
            alert.setHeaderText("Información");
            alert.setContentText("El Documento: "+ tf_idpersonadps.getText() +"\n" + "No se encuentra registrado en la base de datos");
            alert.show();
        }
    }

    @FXML
    public void buscarR() {
        tf_idpersonadps.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    buscarRegistros();
                }
            }
        });
    }

    @FXML
    public boolean buscarIdPaciente(String idPaciente) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = facadePersona.buscarPersonaPrimaryKey(idPaciente);
        boolean resultado;
        if (documento) {
            resultado = true;
        } else {
            resultado = false;

        }
        return resultado;
    }

    @FXML
    public void validarId() {//Metodo para validar que el Id del cargo solo sean numeros
        tf_idPersonalRecibe.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char car = event.getCharacter().charAt(0);
                if (!Character.isDigit(car)) {
                    event.consume();
                }
            }
        });
    }

    @FXML
    public boolean buscarDocumento(String codigoRemision) {//Metodo que valida si el número de documento que se esta ingresando esxiste en la BBDD
        boolean documento = registroFacade.buscarPrimaryKeyCodigoRemision(codigoRemision);
        boolean resultado;
        if (documento) {
            resultado = true;
        } else {
            resultado = false;

        }
        return resultado;
    }

    public void validarExistente() {

        tf_codigoRemision.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
            }
        });
    }

    public void validarE() {

            boolean busqueda = buscarDocumento(tf_codigoRemision.getText());
            if (busqueda) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Atención Paciente");
                msg.setContentText("Ya existe un paciente con código de remisión Nro:\n" + tf_codigoRemision.getText() + " Asignado");
                msg.setHeaderText("Información.");
                msg.show();
                tf_codigoRemision.setText("");
                tf_codigoRemision.requestFocus();
            }
    }

    @FXML
    public void iniciarCbxCondicionPaciente() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Vivo", "Muerto");
        cbx_estadoPaciente.setItems(items);
    }

    @FXML
    public void iniciarCbxGlasgowPaciente() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("15/15", "10/15");
        cbx_gasglowPaciente.setItems(items);
    }

    @FXML
    public void iniciarCbxSignosVitales() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("presion Arterial", "Pulso");
        cbx_signosVitalesPaciente.setItems(items);
    }

    @FXML
    public void iniciarCbxMedicamento() {
        ObservableList<Medicamento> listamedicamentos = FXCollections.observableArrayList(facadeMedicamento.obternetTodosMedicamentos());
        cbx_medicamentoSuministrado.setItems(listamedicamentos);
    }

    @FXML
    public void iniciarCbxPersonalSalud() {
        ObservableList<PersonalSalud> listapersonal = FXCollections.observableArrayList(personalSaludFacade.obtenerTodoPersonalSalud());
        cbx_documentoAPH.setItems(listapersonal);
    }

    @FXML
    public void iniciarCbxInstitucionReferencia() {
        ObservableList<InstitucionReferencia> listainstituciones = FXCollections.observableArrayList(facadeInstitucionReferencia.ListarTodas());
        cbx_institucionReferencia.setItems(listainstituciones);
    }

    @FXML
    public void iniciarCbxTipoDocumento() {
        ObservableList<DtoTipoDocumento> listatipodocumentos = FXCollections.observableArrayList(facadeTipoDocumento.cargarTipoDocumento());
        cbx_tipoDocumento.setItems(listatipodocumentos);
    }

    @FXML
    public void iniciarCbxCargo() {
        ObservableList<Cargo> listacargos = FXCollections.observableArrayList(facadeCargo.obtenerCargos());
        cbx_idCargo.setItems(listacargos);
    }

    @FXML
    public void iniciarCbxRemision() {
        ObservableList<RegistroDto> listaremisiones = FXCollections.observableArrayList(registroFacade.CargarTodosRegistros());
        cbx_codigoRemision.setItems(listaremisiones);
    }

    @FXML
    public void limpiar() {

        cbx_estadoPaciente.setValue(null);
        cbx_gasglowPaciente.setValue(null);
        cbx_signosVitalesPaciente.setValue(null);
        tf_direccionAccidente.setText("");
        cbx_medicamentoSuministrado.setValue(null);
        tf_dosisMedicamento.setText("");
        cbx_documentoAPH.setValue(null);
        tf_codigoRemision.setText("");
        cbx_institucionReferencia.setValue(null);

    }

    @FXML
    private void cerrarFormularioRegistroAtencion() {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
        stop();
    }
}


