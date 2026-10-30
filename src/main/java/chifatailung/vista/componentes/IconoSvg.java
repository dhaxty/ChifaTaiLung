package chifatailung.vista.componentes;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import javax.swing.*;
/** Icono classpath sustituible desde las propiedades del diseñador. */
public class IconoSvg extends JLabel {
    private String recurso = "inicio";
    private int tamano = 30;
    private Color colorIcono = Color.WHITE;
    private Color fondoIcono;
    public IconoSvg() { setHorizontalAlignment(CENTER); actualizar(); }
    public String getRecurso() { return recurso; }
    public void setRecurso(String valor) { recurso = valor; actualizar(); }
    public int getTamano() { return tamano; }
    public void setTamano(int valor) { tamano = Math.max(8,valor); actualizar(); }
    public Color getColorIcono() { return colorIcono; }
    public void setColorIcono(Color valor) { colorIcono = valor; actualizar(); }
    public Color getFondoIcono() { return fondoIcono; }
    public void setFondoIcono(Color valor) { fondoIcono = valor; repaint(); }
    private void actualizar() {
        if (recurso == null || recurso.isBlank()) { setIcon(null); return; }
        FlatSVGIcon icono = new FlatSVGIcon("iconos/" + recurso + ".svg",tamano,tamano,IconoSvg.class.getClassLoader());
        icono.setColorFilter(new FlatSVGIcon.ColorFilter(c -> colorIcono));
        setIcon(icono);
        getAccessibleContext().setAccessibleName(recurso);
    }
    @Override protected void paintComponent(Graphics graphics) {
        if (fondoIcono != null) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            Color claro = new Color(Math.min(255,fondoIcono.getRed()+12), Math.min(255,fondoIcono.getGreen()+12), Math.min(255,fondoIcono.getBlue()+12));
            int lado = Math.min(getWidth(),getHeight());
            int x = (getWidth()-lado)/2, y = (getHeight()-lado)/2;
            g.setPaint(new GradientPaint(x,y,claro,x+lado,y+lado,fondoIcono));
            g.fillRoundRect(x,y,lado,lado,16,16); g.dispose();
        }
        super.paintComponent(graphics);
    }
}
