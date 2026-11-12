
package chifatailung;

import chifatailung.vista.FrmLogin;
import chifatailung.vista.TemaApp;

// punto de arranque del sistema
public class ChifaTaiLung {
    public static void main(String[] args){
        // aplica el tema visual y abre el formulario de login
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }
}

