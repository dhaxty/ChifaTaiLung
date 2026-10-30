package chifatailung.vista.componentes;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import javax.swing.*;
/** Botón visual, sin operaciones de negocio ni navegación inventada. */
public class BotonNavegacion extends JButton {
    private String recurso = "inicio";
    private boolean activo;
    private boolean dorado;
    public BotonNavegacion() {
        setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false);
        setHorizontalAlignment(LEADING); setIconTextGap(26);
        setBorder(BorderFactory.createEmptyBorder(8,20,8,12));
        setFont(new Font("Segoe UI",Font.PLAIN,18));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setRolloverEnabled(true); actualizarIcono();
        addChangeListener(e -> repaint());
    }
    public String getRecurso() { return recurso; }
    public void setRecurso(String valor) { recurso=valor; actualizarIcono(); }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean valor) { activo=valor; actualizarIcono(); repaint(); }
    public boolean isDorado() { return dorado; }
    public void setDorado(boolean valor) { dorado=valor; actualizarIcono(); repaint(); }
    private void actualizarIcono() {
        Color c = dorado ? new Color(0x4D2306) : activo ? Color.WHITE : new Color(0xDFD5B9);
        setForeground(c);
        if (recurso == null) return;
        FlatSVGIcon icono = new FlatSVGIcon("iconos/"+recurso+".svg",32,32,BotonNavegacion.class.getClassLoader());
        icono.setColorFilter(new FlatSVGIcon.ColorFilter(original -> c)); setIcon(icono);
    }
    @Override protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if (dorado || activo || getModel().isRollover() || hasFocus()) {
            Color a = dorado ? new Color(0xECAA22) : new Color(0xBD1019);
            Color b = dorado ? new Color(0xD29422) : new Color(0x9E0B13);
            if (getModel().isPressed()) { a=a.darker(); b=b.darker(); }
            g.setPaint(new GradientPaint(0,0,a,getWidth(),getHeight(),b));
            g.fillRoundRect(0,0,getWidth(),getHeight(),20,20);
            if (hasFocus()) { g.setColor(new Color(0xE2B252)); g.drawRoundRect(1,1,getWidth()-3,getHeight()-3,20,20); }
        }
        g.dispose(); super.paintComponent(graphics);
    }
}
