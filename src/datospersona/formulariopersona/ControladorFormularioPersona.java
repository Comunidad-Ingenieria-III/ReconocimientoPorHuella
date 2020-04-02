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
import datospersona.dto.Persona;
import datospersona.facade.FacadePersona;
import eps.dto.DtoEps;
import eps.facadeeps.FacadeEps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;
import tipodocumento.facadetipodocumento.FacadeTipoDocumento;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControladorFormularioPersona implements Initializable {

    Persona persona = new Persona();
    FacadePersona facadepersona = new FacadePersona();
    FacadeTipoDocumento facadeTipoDocumento = new FacadeTipoDocumento();
    FacadeEps facadeEps = new FacadeEps();


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
    private Button bt_abrirformulariopersonafamiliar;

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dp_fechaNacimiento.setValue(LocalDate.now());
        iniciarCbxSexo();
        iniciarCbxDocumento();
        iniciarEps();
        deshabilitarBotones();
        deshabilitarCampos();
        Iniciar();

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
                        EnviarTexto("Listo la huella ha sido cpaturada correctamente ahora puede guardarla");
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
                    JOptionPane.showMessageDialog(null, "Huella Guardada Correctament");
                }
            } else if (!rs.next()) {
                //String nombre = JOptionPane.showInputDialog("Nombre y Apellidos:");
                try {
                    guardarPersona();
                } catch (RuntimeException e) {
                    throw new RuntimeException("Error SQL - Agregar()!");
                }
                JOptionPane.showMessageDialog(null, "Huella Guardada Correctamente");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Persona crearPersona() {

        int idpersona = Integer.parseInt(tf_idpersona.getText());
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
        int ta_tipoDocumento = cbxtipodocumento.getSelectionModel().getSelectedItem().getIdTipoDocumento();
        int ta_idEps = cbxtipoeps.getSelectionModel().getSelectedItem().getIdEps();

        Persona persona = new Persona(idpersona, primerNombre, segundoNombre, primerApellido, segundoApellido,
                fechaNacimiento, direccion, sexo, alegicoA, enfermedadSufre, observaciones, huella, huella1, ta_tipoDocumento, ta_idEps);

        return persona;

    }

    @FXML
    public void iniciarCbxSexo() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Seleccione", "Masculino", "Femenino");
        cbxsexo.setItems(items);
        cbxsexo.getSelectionModel().selectFirst();
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
        cbxsexo.setValue("");
        cbxtipoeps.setValue(null);
    }


    @FXML
    private void habilitarBotones() {
        bt_crear.setDisable(true); //siempre ira deshabilitado
        bt_consultar.setDisable(true);
        bt_cancelar.setDisable(false);
        bt_salir.setDisable(false);
        bt_guardar.setDisable(false);
        bt_modificar.setDisable(true);
        bt_inhabilitar.setDisable(true);
        //bt_abrirformulariopersonafamiliar.setDisable(false);
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
        //bt_abrirformulariopersonafamiliar.setDisable(true);
    }


    @FXML
    private void habilitarCampos() {
        cbxtipodocumento.setDisable(false);
        tf_idpersona.setDisable(false);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        cbxsexo.setDisable(false);
        cbxtipoeps.setDisable(false);
        ta_alergicoA.setDisable(false);
        ta_enfermedadSufre.setDisable(false);
        ta_observaciones.setDisable(false);
        dp_fechaNacimiento.setDisable(false);
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
        cbxtipoeps.setDisable(true);
        ta_alergicoA.setDisable(true);
        ta_enfermedadSufre.setDisable(true);
        ta_observaciones.setDisable(true);
        dp_fechaNacimiento.setDisable(true);
        cbxsexo.setDisable(true);

    }

    @FXML
    private void btnNuevo_click(ActionEvent event) {
        habilitarBotones();
        limpiar();
        habilitarCampos();

        //habilitarBotones();

    }

    @FXML
    private void guardarPersona() {
        //validar();

        facadepersona.insertarPersona(crearPersona());
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Pacientes - registro");
        msg.setContentText("El Paciente ha sido agregado correctamente");
        msg.setHeaderText("Resultado");
        msg.show();
        bt_crear.setDisable(false);

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
    private void setCerrarFormularioPersonas(ActionEvent event) {
        Stage stage = (Stage) bt_crear.getScene().getWindow();
        stage.close();
    }


}

