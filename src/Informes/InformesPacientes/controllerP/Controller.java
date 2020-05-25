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
    public static final String REPORTE_PACIENTESINDIVIDUAL ="src/Informes/InformesPacientes/ControllerP/reporteIndividual.jasper";

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
    public static JasperPrint reportePacientes(String identificacion){
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

    public  void  generarReportePacientes(String identificacion) throws JRException {
        JasperPrint reporteLleno = reportePacientes(identificacion);
        JasperViewer viewer = new JasperViewer(reporteLleno);
        viewer.setVisible(true);
    }
}
