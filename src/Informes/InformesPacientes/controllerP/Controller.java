package Informes.InformesPacientes.controllerP;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;

public class Controller {
    public static final String REPORTE_EMPLEADOS ="ReportePaciente.jasper";
    public static final String REPORTE_PACIENTESINDIVIDUAL ="reporteIndividual.jasper";
    public static final String REPORTE_PACIENTESTOTALES="ConsultaPacientes.jasper";





    public static JasperPrint generarReportePersonal(){
        try {
            JasperPrint reportePersonalLeno = JasperFillManager.fillReport(REPORTE_EMPLEADOS,new HashMap<>(), ConexionRoot.getConexion());
            return reportePersonalLeno;
        } catch (RuntimeException | JRException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return null;
    }

    public static JasperPrint informePacientesRegistrados(){
        try {
            JasperPrint reportePersonalLeno = JasperFillManager.fillReport(REPORTE_PACIENTESTOTALES,new HashMap<>(), ConexionRoot.getConexion());
            return reportePersonalLeno;
        } catch (RuntimeException | JRException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return null;
    }



    public static JasperPrint reportePacientes(String identificacion, String nombre, String observaciones){
        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("identificacion",identificacion);
        try {
            JasperPrint reporteEmpleadoLleno= JasperFillManager.fillReport(REPORTE_PACIENTESINDIVIDUAL,parametros, ConexionRoot.getConexion());
            return reporteEmpleadoLleno;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }








}
