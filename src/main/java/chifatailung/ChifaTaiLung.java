
package chifatailung;

import chifatailung.vista.FrmLogin;
import chifatailung.vista.TemaApp;

public class ChifaTaiLung {
    public static void main(String[] args){
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }
}

