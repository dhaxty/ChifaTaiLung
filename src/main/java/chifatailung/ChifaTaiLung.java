
package chifatailung;
import com.formdev.flatlaf.FlatLightLaf;


public class ChifaTaiLung {
    public static void main(String[] args){
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> new chifatailung.vista.FrmLogin().setVisible(true));
    }
}
