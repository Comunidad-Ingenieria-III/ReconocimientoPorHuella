package consultas.pacientesatendidos;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;

public class PacientesAtendidos {

        public static final String REPORTE_EMPLEADOS ="listaPacientesAtendidos.jasper";
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

        public  void  generarListaPacientes() throws JRException {
            JasperPrint reporteLleno = generarReportePersonal();
            JasperViewer viewer = new JasperViewer(reporteLleno);
            viewer.setVisible(true);
        }

}
