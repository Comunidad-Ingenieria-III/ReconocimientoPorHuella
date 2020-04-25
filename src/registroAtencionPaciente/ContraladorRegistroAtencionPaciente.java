package registroAtencionPaciente;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import datospersona.facade.FacadePersona;
import tipodocumento.dtotipodocumento.DtoTipoDocumento;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ContraladorRegistroAtencionPaciente implements Initializable {

    @FXML
    private TextField tf_idpersonadps;
    @FXML
    private TextField tf_primerNombredps;
    @FXML
    private TextField tf_segundoNombredps;
    @FXML
    private TextField tf_primerApellidodps;
    @FXML
    private TextField tf_segundoApellidodps;
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
    private Button bt_buscar_huella;


    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rset;

    private FacadePersona facadepersona = new FacadePersona();

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
        //buscarHuella();

    }

    /*@FXML
    public Persona buscarPorHuella() {


        //int huella1 = Integer.parseInt(ta_huella.getText());

        int identificacion = Integer.parseInt(tf_idpersonadps.getText());
        String nombre = tf_primerNombredps.getText();
        String nombre2 = tf_numerotelefonicofamiliar.getText();
        String alergico = ta_alergicoA.getText();
        String sufrode = ta_enfermedadSufre.getText();
        String observaciones = ta_observaciones.getText();


        Persona persona = new Persona(identificacion, nombre, nombre2, alergico, sufrode, observaciones);

        return persona;
    }

    public void buscarHuella() {

        int num = Integer.parseInt(tf_idpersonadps.getText());
        facadepersona.buscarPersona(num);
        JOptionPane.showMessageDialog(null, "Dato Encontrado", "INFORMACIÓN", 1);
    }*/



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
    public void identificarHuella() throws IOException {

        try{
            //Establece los valores para la sentencia SQL
            conn = ConexionRoot.getConexion();

            //Obtiene todas las huellas de la bd
            PreparedStatement identificarStmt = conn.prepareStatement("SELECT idpersona, primerNombre, fechaNacimiento, alergicoA," +
                    "enfermedadSufre, observaciones, huella FROM datos_persona" +
                    " UNION ALL SELECT idpersona, primerNombre, fechaNacimiento, alergicoA, enfermedadSufre, observaciones, huella1 FROM datos_persona");
            //Obtiene todas las huellas de la bd
            ResultSet rsIdentificar = identificarStmt.executeQuery();

            //Si se encuentra el nombre en la base de datos
            //byte templateBuffer[] = null;
            int i=0;
            while(rsIdentificar.next()){
                i++;
                System.out.println("SQL:"+rsIdentificar.getString(1)+"\n");
                System.out.println("Contador:"+i+"\n");

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
                if (result.isVerified()){
                //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
                    //JOptionPane.showMessageDialog(null, "Me llamo: "+
                            //rsIdentificar.getString("primerNombre"));
                    tf_idpersonadps.setText(rsIdentificar.getString("idpersona"));
                    tf_primerNombredps.setText(rsIdentificar.getString("primerNombre"));
                    tf_fechaNacimiento.setText(rsIdentificar.getString("fechaNacimiento"));
                    ta_alergicoA.setText(rsIdentificar.getString("alergicoA"));
                    ta_enfermedadSufre.setText(rsIdentificar.getString("enfermedadSufre"));
                    ta_observaciones.setText(rsIdentificar.getString("observaciones"));
                    return;
                }
            }
            //Si no encuentra alguna huella que coincida lo indica con un mensaje
            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella.");
        }
        catch (SQLException e) {
            System.out.println("Se produjo el siguiente error: "+e.getMessage());
            e.printStackTrace();
        }
        /*finally{
        con.desconectar();
        }*/
    }

    /*@FXML
    public void limpiar() {
        tf_nombretipodocumento.setText("");
        tf_idpersona.setText("");
        tf_primerNombre.setText("");
        tf_segundoNombre.setText("");
        tf_primerApellido.setText("");
        tf_segundoApellido.setText("");
        tf_nombreeps.setText("");
        tf_nombrefamiliar.setText("");
        tf_numerotelefonicofamiliar.setText("");
        ta_alergicoA.setText("");
        ta_enfermedadSufre.setText("");
        ta_observaciones.setText("");
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
        cbxtipodocumento.setDisable(false);
        tf_nombretipodocumento.setDisable(false);
        tf_idpersona.setDisable(false);
        tf_primerNombre.setDisable(false);
        tf_segundoNombre.setDisable(false);
        tf_primerApellido.setDisable(false);
        tf_segundoApellido.setDisable(false);
        cbxsexo.setDisable(false);
        cbxtipoeps.setDisable(false);
        tf_nombreeps.setDisable(false);
        cbxdocumentofamiliar.setDisable(false);
        tf_nombrefamiliar.setDisable(false);
        tf_numerotelefonicofamiliar.setDisable(false);
        ta_alergicoA.setDisable(false);
        ta_enfermedadSufre.setDisable(false);
        ta_observaciones.setDisable(false);
        dp_fechaNacimiento.setDisable(false);
        cbxtipodocumento.requestFocus();
    }

    @FXML
    private void deshabilitarCampos() {
        cbxtipodocumento.setDisable(true);
        tf_nombretipodocumento.setDisable(true);
        tf_idpersona.setDisable(true);
        tf_primerNombre.setDisable(true);
        tf_segundoNombre.setDisable(true);
        tf_primerApellido.setDisable(true);
        tf_segundoApellido.setDisable(true);
        cbxtipoeps.setDisable(true);
        tf_nombreeps.setDisable(true);
        cbxdocumentofamiliar.setDisable(true);
        tf_nombrefamiliar.setDisable(true);
        tf_numerotelefonicofamiliar.setDisable(true);
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

    }*/

    @FXML
    private void setCerrarFormularioRegistroAtencion() {
        Stage stage = (Stage) bt_crear.getScene().getWindow();
        stage.close();
    }
}

