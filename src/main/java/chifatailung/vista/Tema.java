
package chifatailung.vista;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import javax.swing.UIManager;

public class Tema {
        // Colores de la marca
    public static final Color PRINCIPAL = new Color(0x8C1C13);
    public static final Color ACENTO    = new Color(0xD9822B);
    public static final Color FONDO     = new Color(0xF4F2EF);
    public static final Color SIDEBAR   = new Color(0x2A2523);
    public static final Color TEXTO     = new Color(0x232323);
    public static final Color BORDE     = new Color(0xDDD8D2);

    // Colores de estado de mesa
    public static final Color LIBRE_BG     = new Color(0xE6F2E3);
    public static final Color LIBRE_FG     = new Color(0x1E4D1A);
    public static final Color OCUPADA_BG   = new Color(0xC62828);
    public static final Color OCUPADA_FG   = Color.WHITE;
    public static final Color RESERVADA_BG = new Color(0xF5D9A8);
    public static final Color RESERVADA_FG = new Color(0x3A2600);

    private Tema() {}

    public static void aplicar() {
        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap(
            "@accentColor", String.format("#%06X", PRINCIPAL.getRGB() & 0xFFFFFF)));

        FlatLightLaf.setup();

        UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Panel.background", FONDO);
        UIManager.put("Label.foreground", TEXTO);
        UIManager.put("Button.arc", 10);
        UIManager.put("Component.arc", 10);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Table.selectionBackground", PRINCIPAL);
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.rowHeight", 28);
    }
}
