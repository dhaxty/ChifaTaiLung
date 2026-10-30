package chifatailung.vista.componentes;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
/** Gráfico adaptable. Sus valores iniciales son exclusivamente demostrativos. */
public class GraficoVentas extends JComponent {
    @Override public javax.accessibility.AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) accessibleContext = new AccessibleJComponent() {};
        return accessibleContext;
    }
    private final double[] valores = {960,680,920,1248,960,1240,1420};
    private final String[] dias = {"Lun","Mar","Mié","Jue","Vie","Sáb","Dom"};
    public GraficoVentas() {
        setOpaque(false); setPreferredSize(new Dimension(500,340)); setMinimumSize(new Dimension(200,200));
        setFont(new Font("Segoe UI",Font.PLAIN,16));
        setToolTipText("Datos de demostración");
        getAccessibleContext().setAccessibleName("Ventas semanales de demostración: lunes 960, martes 680, miércoles 920, jueves 1248, viernes 960, sábado 1240 y domingo 1420 soles.");
    }
    @Override public String getToolTipText(MouseEvent e) {
        int indice=(int)((e.getX()-54)*7.0/Math.max(1,getWidth()-64));
        return indice>=0 && indice<7 ? dias[indice]+": S/ "+NumberFormat.getNumberInstance(Locale.US).format(valores[indice])+" (demostración)" : "Datos de demostración";
    }
    @Override protected void paintComponent(Graphics graphics) {
        Graphics2D g=(Graphics2D)graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int x0=54, y0=32, ancho=Math.max(1,getWidth()-64), alto=Math.max(1,getHeight()-76);
        g.setFont(getFont()); FontMetrics fm=g.getFontMetrics();
        for(int i=0;i<=4;i++) {
            int y=y0+alto-i*alto/4;
            g.setColor(new Color(0xEEEAE5)); g.drawLine(x0,y,x0+ancho,y);
            String texto=NumberFormat.getIntegerInstance(Locale.US).format(i*500);
            g.setColor(new Color(0x5C6673)); g.drawString(texto,x0-12-fm.stringWidth(texto),y+5);
        }
        double paso=ancho/7.0;
        for(int i=0;i<7;i++) {
            int x=x0+(int)(i*paso+paso*.14), w=Math.max(1,(int)(paso*.72));
            int h=(int)(valores[i]/2000*alto), y=y0+alto-h;
            Color c=i==3 ? new Color(0xA50A15) : new Color(0xD15151);
            g.setPaint(new GradientPaint(x,y,c,x,y+h,c.darker()));
            g.fillRoundRect(x,y,w,h+4,7,7);
            g.setColor(new Color(0xF9F6F1)); g.fillRect(x,y0+alto,w,5);
            g.setFont(getFont().deriveFont(i==3 ? Font.BOLD : Font.PLAIN));
            String dia=dias[i]; g.setColor(i==3?new Color(0x12202D):new Color(0x606976));
            g.drawString(dia,x0+(int)((i+.5)*paso)-g.getFontMetrics().stringWidth(dia)/2,y0+alto+30);
        }
        g.setColor(new Color(0xC6C9CC)); g.drawLine(x0,y0,x0,y0+alto); g.drawLine(x0,y0+alto,x0+ancho,y0+alto);
        int cx=x0+(int)(3.5*paso), cy=y0+alto-(int)(1248.0/2000*alto)-34;
        g.setColor(new Color(0x252525)); g.fillRoundRect(cx-58,cy-21,116,40,9,9);
        g.fillPolygon(new int[]{cx-7,cx+7,cx},new int[]{cy+18,cy+18,cy+26},3);
        g.setFont(getFont().deriveFont(15f)); g.setColor(Color.WHITE);
        String importe="S/ 1,248.00"; g.drawString(importe,cx-g.getFontMetrics().stringWidth(importe)/2,cy+5);
        g.dispose();
    }
}
