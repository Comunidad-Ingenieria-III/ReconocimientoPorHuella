package Informes.InformesPacientes.controllerP;

import conexionBD.ConexionRoot;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;

public class Controller {
    public static final String REPORTE_EMPLEADOS ="ReportePaciente.jasper";
    public static JasperPrint generarReportePersonal(){
        try {
            JasperPrint reportePersonalLeno = JasperFillManager.fillReport(REPORTE_EMPLEADOS,new HashMap<>(), ConexionRoot.getConexion());
            return reportePersonalLeno;
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void  generarReporte() throws JRException {
        JasperPrint reporteLleno = generarReportePersonal();
        JasperViewer viewer = new JasperViewer(reporteLleno);
        viewer.setVisible(true);
    }

}
