package chifatailung.vista.componentes;
import java.awt.*;
import javax.swing.*;
/** Panel JavaBean: sus hijos y propiedades siguen editables en GUI Builder. */
public class PanelRedondeado extends JPanel {
    private int radio = 16;
    private Color colorFinal;
    private Color colorBorde = new Color(0xE9E3DC);
    public PanelRedondeado() { setOpaque(false); }
    public int getRadio() { return radio; }
    public void setRadio(int valor) { radio = Math.max(0, valor); repaint(); }
    public Color getColorFinal() { return colorFinal; }
    public void setColorFinal(Color valor) { colorFinal = valor; repaint(); }
    public Color getColorBorde() { return colorBorde; }
    public void setColorBorde(Color valor) { colorBorde = valor; repaint(); }
    @Override protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(new GradientPaint(0,0,getBackground(),getWidth(),getHeight(),colorFinal == null ? getBackground() : colorFinal));
        g.fillRoundRect(0,0,getWidth(),getHeight(),radio,radio);
        if (radio > 0 && colorBorde != null) {
            g.setColor(colorBorde); g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,radio,radio);
        }
        g.dispose(); super.paintComponent(graphics);
    }
}
