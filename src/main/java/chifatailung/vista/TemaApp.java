package chifatailung.vista;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

public class TemaApp {

    public static void inicializar() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        FlatLightLaf.setup();
    }
}