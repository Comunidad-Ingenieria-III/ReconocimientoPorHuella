package consultas.pacientesatendidos;

import conexionBD.ConexionRoot;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;

import java.util.HashMap;

public class PacientesAtendidos {

    public static final String Lista_pacientes = "src/consultas/pacientesatendidos/lista.jasper";

    public static JasperPrint generarListaAtendidos() {
        try {

            JasperPrint listaP = JasperFillManager.fillReport(Lista_pacientes, new HashMap<>(), ConexionRoot.getConexion());
            return listaP;

        } catch (RuntimeException | JRException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Excepción");
            alert.setHeaderText("Ocurrio el Error SQL:");
            alert.setContentText(ex.getLocalizedMessage());
            alert.show();
        }
        return null;
    }

}
