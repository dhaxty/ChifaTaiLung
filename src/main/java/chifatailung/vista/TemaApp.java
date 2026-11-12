package chifatailung.vista;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

// configuracion del tema visual de la aplicacion
public class TemaApp {

    public static void inicializar() {
        // activa las decoraciones de ventana y el estilo claro de FlatLaf
        JFrame.setDefaultLookAndFeelDecorated(true);
        FlatLightLaf.setup();
    }
}