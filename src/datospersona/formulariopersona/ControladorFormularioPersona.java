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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import persona_familiar.per_fami_dto.Per_Fami_Dto;
import persona_familiar.per_fami_facade.Per_Fami_Facade;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;
import javax.swing.*;
import java.io.ByteArrayInputStream;
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
    Per_Fami_Dto per_fami_dto = new Per_Fami_Dto();
    Familiar familiar = new Familiar();
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
    private DatePicker dp_ingresofamiliar;
    @FXML
    private TableView<Per_Fami_Dto> tb_familiar;
    @FXML
    private TableColumn<Per_Fami_Dto, String> colIdpersona;
    @FXML
    private TableColumn<Per_Fami_Dto, String> colIdFamiliar;
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
    private Button bt_modificarfamiliar;
    @FXML
    private Button bt_inhabilitarfamiliar;
    @FXML
    private  Label lb_huella;
    @FXML
    private Label lblImagenHuella;

    private int valor = 0;

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
        manejarEventosTablaFamiliares();
        colocarImagenBotones();
        buscarPersona();
        reiniciarStilosCamposRequeridos();

    }

    public void initializeTableColumn() {
        colIdpersona.setCellValueFactory(new PropertyValueFactory<>("idPersona"));
        colIdFamiliar.setCellValueFactory(new PropertyValueFactory<>("idFamiliar"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
    }

    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Masculino", "Femenino", "Otro");
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
    }

    @FXML
    public void iniciarCbxid_persona() {

        ObservableList<Persona> listaIdpersona = FXCollections.observableArrayList(facadepersona.cargarPersona());
        for (int i = 0; i < listaIdpersona.size(); i++) {
            if (!listaIdpersona.get(i).isEstado())//Ciclo para recorrer la lista de objetos de personal-salud, y eliminar los registros que tengan
                //el estado inactivo en la BBDD
                listaIdpersona.remove(i);//si el estado esta inactivo, se elimina de la lista
        }
        cbx_documentopersona.setItems(listaIdpersona);
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

    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public void ProcesarCaptura(DPFPSample sample) {
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                try {
                    Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear
                    // Dibuja la huella dactilar capturada.
                    java.awt.Image image = CrearImagenHuella(sample);
                    DibujarHuella(image);

                } catch (DPFPImageQualityException e) {
                    e.printStackTrace();
                }

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
        } else {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Registro Persona");
            msg.setContentText("No ha capturado la huella");
            msg.setHeaderText("Debes capturar la huella");
            msg.show();

        }
    }


    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
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
        return (DPFPGlobal.getSampleConversionFactory().createImage(sample));
    }

    @FXML
    private void guardarHuella() {

        boolean documento = buscarDocumentos(tf_idpersona.getText());
        if (!documento) {

            if (cbxtipodocumento.getSelectionModel().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Debe seleccionar un tipo de documento");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxtipodocumento.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxtipodocumento.requestFocus();

            } else if (tf_idpersona.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Número de documento");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_idpersona.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_idpersona.requestFocus();

            } else if (tf_primerNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Primer nombre");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_primerNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_primerNombre.requestFocus();

            } else if (tf_primerApellido.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Primer apellido");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_primerApellido.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_primerApellido.requestFocus();

            } else if (dp_fechaNacimiento.getValue() == null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Fecha de nacimiento");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                dp_fechaNacimiento.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                dp_fechaNacimiento.requestFocus();

            } else if (tf_direccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Dirección");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_direccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_direccion.requestFocus();

            } else if (cbxsexo.getSelectionModel().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Debe seleccionar un sexo");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxsexo.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxsexo.requestFocus();

            } else if (cbxtipoeps.getSelectionModel().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Debe seleccionar una EPS");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxtipoeps.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxtipoeps.requestFocus();

            } else if (ta_alergicoA.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Alérgico A:");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                ta_alergicoA.setStyle("-fx-border-color: red;");
                ta_alergicoA.requestFocus();

            } else if (ta_enfermedadSufre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Enfermedad que padece");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                ta_enfermedadSufre.setStyle("-fx-border-color: red;");
                ta_enfermedadSufre.requestFocus();

            } else if (txtArea.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Huella Dactilar");
                msg.setHeaderText("Debes capturar la Huella");
                msg.show();
                txtArea.setStyle("-fx-border-color: red;");
                bt_hulla.requestFocus();

            } else if (Reclutador.getTemplate().equals(null)) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Debes capturar la huella dactilar");
                msg.setHeaderText("Campo Requerido");
                msg.show();
                txtArea.setStyle("-fx-border-color: red;");
                bt_hulla.requestFocus();

            } else {

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
                            JOptionPane.showMessageDialog(null, "La huella ya existe, coloque un dedo diferente");
                        } else {
                            PreparedStatement guardarStmt2 = conn.prepareStatement("update datos_persona set huella1=? where (idpersona=?)");
                            guardarStmt2.setBinaryStream(1, datosHuella, tamañoHuella);
                            guardarStmt2.setInt(2, Integer.parseInt(tf_idpersona.getText()));

                            //Ejecuta la sentencia
                            guardarStmt2.execute();
                            guardarStmt2.close();
                            Alert msg = new Alert(Alert.AlertType.INFORMATION);
                            msg.setTitle("Gestiones - Persona");
                            msg.setContentText("Huella guardada correctamente");
                            msg.setHeaderText("Información.");
                            msg.show();
                        }
                    } else if (!rs.next()) {

                        try {
                            guardarPersona();
                            stop();
                            Reclutador.clear();

                        } catch (RuntimeException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Ocurrio el Error SQL:");
                            alert.setContentText(ex.getLocalizedMessage());
                            alert.show();
                        }
                    }
                } catch (SQLException | RuntimeException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Ocurrio el Error SQL:");
                    alert.setContentText(ex.getLocalizedMessage());
                }

            }
        } else {

            if (cbxtipodocumento == null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Debe seleccionar un tipo de documento");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxtipodocumento.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxtipodocumento.requestFocus();

            } else if (tf_idpersona.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Número de documento ");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_idpersona.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_idpersona.requestFocus();

            } else if (tf_primerNombre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Primer nombre");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_primerNombre.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_primerNombre.requestFocus();

            } else if (tf_primerApellido.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Primer apellido");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                tf_primerApellido.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_primerApellido.requestFocus();

            } else if (dp_fechaNacimiento.getValue() == null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Fecha de nacimiento");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                dp_fechaNacimiento.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                dp_fechaNacimiento.requestFocus();

            } else if (tf_direccion.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Dirección");
                msg.setHeaderText("Campos requeridos");
                ;
                msg.show();
                tf_direccion.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                tf_direccion.requestFocus();

            } else if (cbxsexo.getSelectionModel().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Seleccione un sexo");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxsexo.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxsexo.requestFocus();

            } else if (cbxtipoeps.getSelectionModel().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Seleccione una EPS");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                cbxtipoeps.setStyle("-fx-border-color: red ; -fx-border-radius: 8px;");
                cbxtipoeps.requestFocus();

            } else if (ta_alergicoA.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Alérgico A:");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                ta_alergicoA.setStyle("-fx-border-color: red;");
                ta_alergicoA.requestFocus();

            } else if (ta_enfermedadSufre.getText().isEmpty()) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Registro Persona");
                msg.setContentText("Enfermedad que padece");
                msg.setHeaderText("Campos requeridos");
                msg.show();
                ta_enfermedadSufre.setStyle("-fx-border-color: red;");
                ta_enfermedadSufre.requestFocus();

            } else {

                int res = facadepersona.modificarPersona(modificarPersona());
                if (res == 1) {
                    iniciarCbxid_persona();
                    Alert msg1 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                    msg1.setTitle("Gestiones - Persona");
                    msg1.setContentText("Registro modificado correctamente\n \n" + "¿Desea modificar su Familiar?");
                    msg1.setHeaderText("Información");

                    Optional<ButtonType> action = msg1.showAndWait();

                    if (action.get() == ButtonType.YES) {
                        tb_familiar.setDisable(false);
                        cbx_documentopersona.setDisable(true);
                        cbx_documentofamiliar.setDisable(false);
                        dp_ingresofamiliar.setDisable(false);
                        bt_agregarfamiliar.setDisable(false);
                        cbx_documentopersona.setValue(facadepersona.buscarIdPersona(tf_idpersona.getText()));
                        bt_crear.setDisable(false);
                    } else if (action.get() == ButtonType.NO) {
                        cancelar();
                    }
                }
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
    private void guardarPersona() {

        int res = facadepersona.insertarPersona(crearPersona());
        if (res == 1) {
            Alert msg1 = new Alert(Alert.AlertType.INFORMATION);
            msg1.setTitle("Gestiones - Persona");
            msg1.setContentText("Debes ingresar el familiar");
            msg1.setHeaderText("Registro agregado correctamente");
            msg1.show();
            iniciarCbxid_persona();
            cbx_documentopersona.setValue(facadepersona.buscarIdPersona(tf_idpersona.getText()));
            bt_crear.setDisable(false);
            cbx_documentofamiliar.requestFocus();
            cbx_documentofamiliar.setDisable(false);
            cbx_documentopersona.setDisable(true);
            dp_ingresofamiliar.setDisable(false);
            bt_agregarfamiliar.setDisable(false);
            bt_modificarfamiliar.setDisable(true);
            bt_inhabilitarfamiliar.setDisable(true);

            //cancelar();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("No fue posible agregar el registro");
            msg.setHeaderText("Algo salio mal.");
            msg.show();
        }
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
            tb_familiar.setDisable(true);
            bt_agregarfamiliar.setDisable(true);
            valor = 0;
        } else {

            BusquedaDeFamiliar busqueda = facadepersona.buscarPersona(tf_idpersona.getText());
            Persona persona = busqueda.getPersona();

            if (persona == null) {
                Alert msg = new Alert(Alert.AlertType.WARNING);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("El documento " + tf_idpersona.getText() + " no se encuentra registrado");
                msg.setHeaderText("Información.");
                msg.show();
                tf_idpersona.setText("");
                tf_idpersona.requestFocus();

            } else if (!persona.isEstado()) {//Condicional que verifica si el objeto personal_salud
                // que se acaba de recuperar esta en estado inactivo en la BBDD
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("El documento " + persona.getIdpersona() + "\n"
                        + "se escuentra registrado, mas su estado es inhabilitado. Contacte a su administrador");
                msg.setHeaderText("Información.");
                msg.show();
                tf_idpersona.setText("");
                tf_idpersona.requestFocus();

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
                cbx_documentopersona.setValue(facadepersona.buscarIdPersona(persona.getIdpersona()));

                ObservableList<Per_Fami_Dto> familiaresActivos;//Lista para recibir los familiares desde la BBDD
                familiaresActivos = FXCollections.observableArrayList(busqueda.getListafamiliar());

                for (int i = 0; i < familiaresActivos.size(); i++) {//Ciclo para recorrer la lista de objetos de personal_familiar, para agregar a la lista que estara
                    //enlazada con tb_familiar solamente los se encuentres en estado activo.
                    if (familiaresActivos.get(i).isEstado()) {
                        familiares.add(familiaresActivos.get(i));
                    }
                }
                tb_familiar.setItems(familiares);

                cbx_documentopersona.setDisable(true);
                initializeTableColumn();
                bt_consultar.setDisable(true);
                bt_crear.setDisable(true);
                tf_idpersona.setDisable(true);
            }
        }

    }

    @FXML
    public void modificar() {//Metodo que se ejecuta al presionar el boton modificar

        if (tf_idpersona.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("No hay registro para eliminar");
            msg.setHeaderText("Algo salio mal");
            msg.show();
        } else {
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
            bt_modificar.setDisable(false);
            bt_guardar.setDisable(false);
            bt_hulla.setDisable(true);
            cbx_documentopersona.setDisable(true);
            cbx_documentofamiliar.setDisable(false);
            dp_ingresofamiliar.setDisable(false);
            bt_agregarfamiliar.setDisable(false);
            bt_modificarfamiliar.setDisable(false);
            tb_familiar.setDisable(false);
            //---------------------------------
        }
    }

    @FXML
    public void eliminarPersona() {

        if (tf_idpersona.getText().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("No hay registro para eliminar");
            msg.setHeaderText("Algo salio mal");
            msg.show();

        } else {

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
                    msge.setContentText("No puedes eliminar! \n" + "El documento tiene registros dependientes!\n"
                            + "Asegurese de eliminar los registros que dependen de este.");
                    msge.setHeaderText("Información.");
                    msge.show();
                    cancelar();
                } else {
                    Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
                    msg2.setTitle("Gestiones - Persona");
                    msg2.setContentText("Registro eliminado correctamente");
                    msg2.setHeaderText("Información.");
                    msg2.show();
                    cancelar();
                }
            }
        }
    }

    public void validarExistente() {

        tf_primerNombre.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                validarE();
                tf_primerNombre.setStyle(null);
            }
        });
    }

    public void validarE() {
        if (valor == 1) {

            boolean primary = buscarDocumentos(tf_idpersona.getText());
            if (primary) {
                boolean busqueda = buscarDocumento(tf_idpersona.getText());
                if (busqueda) {
                    Alert msg = new Alert(Alert.AlertType.INFORMATION);
                    msg.setTitle("Gestiones - Persona");
                    msg.setContentText("El documento : " + tf_idpersona.getText() + "\n" + "Se escuentra registrado, mas su estado es inhabilitado. Contacte el administrador");
                    msg.setHeaderText("Información.");
                    msg.show();
                    tf_idpersona.setText("");
                    tf_primerNombre.setText("");
                    tf_idpersona.requestFocus();
                } else {
                    Alert msg = new Alert(Alert.AlertType.WARNING);
                    msg.setTitle("Gestiones - Persona");
                    msg.setContentText("El Documento :" + tf_idpersona.getText() + "\n" + "existente, no es posible agregar");
                    msg.setHeaderText("Información.");
                    msg.show();
                    tf_idpersona.setText("");
                    tf_primerNombre.setText("");
                    tf_idpersona.requestFocus();
                }
            }
        }
    }

    @FXML
    public void buscarPersona() {
        tf_idpersona.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (valor == 0) {
                    if (event.getCode() == KeyCode.ENTER)
                        consultarPersonaFamiliar();
                }
            }
        });
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
                cbx_documentofamiliar.setDisable(true);
                cbx_documentopersona.setDisable(true);
                dp_ingresofamiliar.setDisable(true);
                bt_agregarfamiliar.setDisable(true);
                bt_modificarfamiliar.setDisable(true);
                bt_inhabilitarfamiliar.setDisable(true);
                familiares.clear();
                valor = 1;
            }
        });
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
                tf_idpersona.setStyle(null);
            }
        });
    }

    @FXML
    public void reiniciarStilosCamposRequeridos() {//Metodo para reiniciar los estilos de las validaciones

        cbxtipodocumento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cbxtipodocumento.setStyle(null);
            }
        });
        tf_primerApellido.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                tf_primerApellido.setStyle(null);
            }
        });
        dp_fechaNacimiento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dp_fechaNacimiento.setStyle(null);
            }
        });
        tf_direccion.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                tf_direccion.setStyle(null);
            }
        });
        cbxsexo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cbxsexo.setStyle(null);
            }
        });
        cbxtipoeps.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cbxtipoeps.setStyle(null);
            }
        });
        ta_alergicoA.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                ta_alergicoA.setStyle(null);
            }
        });
        ta_enfermedadSufre.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                ta_enfermedadSufre.setStyle(null);
            }
        });
        txtArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                txtArea.setStyle(null);
            }
        });
    }

    @FXML
    public void cancelar() {
        cbxtipodocumento.setValue(null);
        cbxtipodocumento.setStyle(null);
        tf_idpersona.setText("");
        tf_idpersona.setStyle(null);
        tf_primerNombre.setText("");
        tf_primerNombre.setStyle(null);
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_primerApellido.setStyle(null);
        tf_segundoApellido.setText("");
        dp_fechaNacimiento.setValue(null);
        dp_fechaNacimiento.setStyle(null);
        tf_direccion.setText("");
        tf_direccion.setStyle(null);
        ta_alergicoA.setText("");
        ta_alergicoA.setStyle(null);
        ta_enfermedadSufre.setText("");
        ta_enfermedadSufre.setStyle(null);
        ta_observaciones.setText("");
        cbxsexo.setValue(null);
        cbxsexo.setStyle(null);
        cbxtipoeps.setValue(null);
        cbxtipoeps.setStyle(null);
        txtArea.setText("");
        txtArea.setStyle(null);
        cbx_documentopersona.setValue(null);
        cbx_documentofamiliar.setValue(null);
        dp_ingresofamiliar.setValue(null);
        colIdpersona.setText("");
        colIdFamiliar.setText("");
        familiares.clear();
        colFechaIngreso.setText("");
        setTemplate(null);
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
        bt_modificarfamiliar.setDisable(false);
        bt_inhabilitarfamiliar.setDisable(false);
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
        bt_modificarfamiliar.setDisable(true);
        bt_inhabilitarfamiliar.setDisable(true);
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
        dp_ingresofamiliar.setDisable(true);


    }

    public void colocarImagenBotones() {

        URL linkbt_guardar = getClass().getResource("/imagenes/disquete.png");
        URL linkbt_modificar = getClass().getResource("/imagenes/btGuardar.png");
        URL linkbt_inhabilitar = getClass().getResource("/imagenes/eliminar.png");

        Image imagenGuardar = new Image(linkbt_guardar.toString(), 24, 24, false, true);
        bt_agregarfamiliar.setGraphic(new ImageView(imagenGuardar));
        Image imagenModificar = new Image(linkbt_modificar.toString(), 24, 24, false, true);
        bt_modificarfamiliar.setGraphic(new ImageView(imagenModificar));
        Image imagenInhabilitar = new Image(linkbt_inhabilitar.toString(), 24, 24, false, true);
        bt_inhabilitarfamiliar.setGraphic(new ImageView(imagenInhabilitar));

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
        boolean documento = facadepersona.buscarIdEstado(idpersona);
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

        if (cbx_documentofamiliar.getSelectionModel().isEmpty()) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("Debe seleccionar un familiar");
            msg.show();
            cbx_documentofamiliar.requestFocus();
        } else if (dp_ingresofamiliar.getValue() == null) {
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("Debe seleccionar una fecha");
            msg.show();
            cbx_documentofamiliar.requestFocus();
        } else {

            Per_Fami_Dto per_fami_dto = new Per_Fami_Dto(
                    cbx_documentopersona.getSelectionModel().getSelectedItem().getIdpersona(),
                    cbx_documentofamiliar.getSelectionModel().getSelectedItem().getIdFamiliar(),
                    Date.valueOf(dp_ingresofamiliar.getValue()),
                    estado
            );

            DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");//Formato de fecha para mostrarle al usuario.
            String convertido = fechaHora.format(per_fami_dto.getFechaIngreso());//Formatear una fecha Date a String


            if (per_fami_dto.getFechaIngreso().after(new java.util.Date())) {//Condicional que verifica si la fecha seleccionada es superior a la actual
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Agregar Familiar");
                msg.setContentText("Debe ingresar una fecha valida: \n" + "¡La fecha: " + convertido + " es superior a la fecha de hoy!");
                msg.show();
                dp_ingresofamiliar.requestFocus();
            } else {

                per_fami_facade.agregar(per_fami_dto);
                familiares.add(per_fami_dto);
                Alert msg1 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                msg1.setTitle("Gestiones - Agregar Familiar");
                msg1.setContentText("Familiar agregado correctamente\n \n" + "¿Desea agregar mas familiares ?");
                msg1.setHeaderText("Información");
                Optional<ButtonType> action = msg1.showAndWait();

                if (action.get() == ButtonType.YES) {
                    tb_familiar.setDisable(false);
                    cbx_documentopersona.setDisable(true);
                    cbx_documentofamiliar.setDisable(false);
                    dp_ingresofamiliar.setDisable(false);
                    bt_agregarfamiliar.setDisable(false);
                    cbx_documentopersona.setValue(facadepersona.buscarIdPersona(tf_idpersona.getText()));
                    bt_crear.setDisable(true);
                } else if (action.get() == ButtonType.NO) {

                    cancelar();
                }
            }
        }
    }

    @FXML
    public void modificarRegistroDeTablaFamiliares() {//Metodo que permite editar los registros de la tabla y asi poder actualizar en la base de datos
        Per_Fami_Dto per_fami_dto = new Per_Fami_Dto(
                cbx_documentopersona.getSelectionModel().getSelectedItem().getIdpersona(),
                cbx_documentofamiliar.getSelectionModel().getSelectedItem().getIdFamiliar(),
                Date.valueOf(dp_ingresofamiliar.getValue()),
                estado
        );

        DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");//Formato de fecha para mostrarle al usuario.
        String convertido = fechaHora.format(per_fami_dto.getFechaIngreso());//Formatear una fecha Date a String

        if (per_fami_dto.getFechaIngreso().after(new java.util.Date())) {//Condicional que verifica si la fecha seleccionada es superior a la actual
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("Debe ingresar una fecha valida: \n" + "¡La fecha: " + convertido + " es superior a la fecha de hoy!");
            msg.show();
            dp_ingresofamiliar.requestFocus();
        } else {
            boolean resultado = recorrerTablaTitulos(familiares, per_fami_dto);
            if (resultado) {
                Alert msg = new Alert(Alert.AlertType.ERROR);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("El registro ya existe ");
                msg.show();
            } else {
                per_fami_facade.modificar(per_fami_dto);
                familiares.set(tb_familiar.getSelectionModel().getSelectedIndex(), per_fami_dto);
                Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("Gestiones - Persona");
                msg.setContentText("Haz modificado tú familiar");
                msg.show();
                cancelar();
            }
        }
    }

    @FXML
    public void eliminarFamiliares() {//Metodo para eliminar el registro seleccionado en la tabla
        String idPs = tb_familiar.getSelectionModel().getSelectedItem().getIdPersona();//Extraemos en id del psDto, desde la fila seleccionada en la tabla
        boolean estado = false;

        Alert msg1 = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        msg1.setTitle("Gestiones - Persona");
        msg1.setContentText("Estas seguro de eliminar el familiar");
        msg1.setHeaderText("Información");
        Optional<ButtonType> action = msg1.showAndWait();

        if (action.get() == ButtonType.YES) {

            per_fami_facade.eliminar(idPs, estado);
            familiares.remove(tb_familiar.getSelectionModel().getFocusedIndex());

            Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Gestiones - Persona");
            msg.setContentText("Has eliminado tu familiar");
            cancelar();
        } else if (action.get() == ButtonType.NO) {
            cancelar();
        }
    }

    @FXML
    public void textESC(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ESCAPE))
            cancelar();
    }

    public void manejarEventosTablaFamiliares() {//Metodo para que cuando se seleccione un registro de la tabla se asigne a los componentes
        tb_familiar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Per_Fami_Dto>() {
            @Override
            public void changed(ObservableValue<? extends Per_Fami_Dto> observable, Per_Fami_Dto oldValue, Per_Fami_Dto newValue) {
                if (newValue != null) {
                    cbx_documentopersona.getSelectionModel().select(facadepersona.buscarIdPersona(
                            tb_familiar.getSelectionModel().getSelectedItem().getIdPersona()));

                    cbx_documentofamiliar.getSelectionModel().select(facadefamiliar.buscarPorId(
                            tb_familiar.getSelectionModel().getSelectedItem().getIdFamiliar()));

                    dp_ingresofamiliar.setValue(newValue.getFechaIngreso().toLocalDate());

                    bt_agregarfamiliar.setDisable(true);
                    bt_modificarfamiliar.setDisable(false);
                    bt_inhabilitarfamiliar.setDisable(false);

                }

            }
        });//FIN DEL LISTENER
    }

    @FXML
    private void setCerrarFormularioPersonas(ActionEvent event) {
        Stage stage = (Stage) bt_crear.getScene().getWindow();
        stage.close();
    }


}

