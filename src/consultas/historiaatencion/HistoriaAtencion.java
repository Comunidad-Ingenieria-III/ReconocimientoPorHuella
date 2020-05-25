package consultas.historiaatencion;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class HistoriaAtencion {



    public static JasperPrint generarListaP(){

        try {

            Map parametros = new HashMap();
            String numero = JOptionPane.showInputDialog(null,"Ingrese el numero del documento que requiere");
            parametros.put("id_persona", numero);

            JasperReport report = JasperCompileManager.compileReport("src/consultas/historiaatencion/historialPaciente.jrxml");
            JasperPrint listaP = JasperFillManager.fillReport(report, parametros, ConexionRoot.getConexion());
            return listaP;

        }  catch (RuntimeException | JRException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return null;
    }

}

