package chifatailung.vista.componentes;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/** Pinta únicamente fragmentos decorativos del mockup aprobado, sin crear arte nuevo. */
public class ArteReferencia extends JComponent {
    private static final BufferedImage REFERENCIA = cargar();
    private String region = "leopardo";
    public ArteReferencia() { setOpaque(false); }
    private static BufferedImage cargar() {
        try (var in = ArteReferencia.class.getResourceAsStream("/imagenes/referencia-principal.png")) {
            return in == null ? null : ImageIO.read(in);
        } catch (IOException ex) { throw new IllegalStateException("No se pudo cargar la referencia aprobada",ex); }
    }
    public String getRegion() { return region; }
    public void setRegion(String valor) { region=valor; repaint(); }
    @Override protected void paintComponent(Graphics graphics) {
        if (REFERENCIA == null) return;
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        if ("nubes".equals(region)) {
            g.drawImage(REFERENCIA,0,0,getWidth(),getHeight(),0,165,300,205,null);
        } else if ("cabecera".equals(region)) {
            g.drawImage(REFERENCIA,0,0,getWidth(),getHeight(),1400,95,1536,180,null);
        } else {
            double escala = Math.min(getWidth()/300.0,getHeight()/230.0);
            int ancho=(int)(300*escala), alto=(int)(230*escala);
            g.drawImage(REFERENCIA,0,getHeight()-alto,ancho,getHeight(),0,680,300,910,null);
        }
        g.dispose();
    }
}
