
package chifatailung.app;
import chifatailung.vista.FrmLogin;
import chifatailung.vista.Tema;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        Tema.aplicar();
        SwingUtilities.invokeLater(() -> new FrmLogin().setVisible(true));
    }
}
