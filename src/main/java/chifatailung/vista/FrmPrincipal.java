/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chifatailung.vista;

/**
 *
 * @author Steve
 */
public class FrmPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPrincipal.class.getName());

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        configurarPresentacion();
    }

    private javax.swing.Timer relojDashboard;

    /** Presentación adaptable; los componentes y sus layouts pertenecen al .form. */
    private void configurarPresentacion() {
        getRootPane().putClientProperty("JRootPane.titleBarBackground", new java.awt.Color(0x570D08));
        getRootPane().putClientProperty("JRootPane.titleBarForeground", java.awt.Color.WHITE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-login.png")).getImage());
        prgMesas.putClientProperty("FlatLaf.style", "arc: 16; background: #DADDE0; foreground: #AA101B");
        cmbPeriodo.putClientProperty("FlatLaf.style", "arc: 12; background: #FCFAF7");
        lblDescripcion.setMinimumSize(new java.awt.Dimension(0, 26));
        lblSaludo.setMinimumSize(new java.awt.Dimension(0, 50));
        java.awt.GridBagLayout cuadricula = (java.awt.GridBagLayout) pnlCuadricula.getLayout();
        cuadricula.columnWeights = new double[] {1, 1, 1, 1};
        actualizarReloj();
        relojDashboard = new javax.swing.Timer(30_000, evento -> actualizarReloj());
        relojDashboard.start();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent evento) {
                relojDashboard.stop();
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentResized(java.awt.event.ComponentEvent evento) {
                ajustarDistribucion();
            }
        });
        pnlCuadricula.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentResized(java.awt.event.ComponentEvent evento) {
                ajustarDistribucion();
            }
        });
        java.awt.Rectangle pantalla = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(Math.min(1536, pantalla.width), Math.min(1024, pantalla.height));
        setLocationRelativeTo(null);
        javax.swing.SwingUtilities.invokeLater(this::ajustarDistribucion);
    }

    private void actualizarReloj() {
        java.time.ZonedDateTime ahora = java.time.ZonedDateTime.now(java.time.ZoneId.of("America/Lima"));
        java.util.Locale espanol = java.util.Locale.forLanguageTag("es-PE");
        lblFecha.setText(ahora.format(java.time.format.DateTimeFormatter.ofPattern("EEE, dd 'de' MMM 'de' yyyy", espanol)));
        lblHora.setText(ahora.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a", java.util.Locale.US)));
    }

    private void ajustarDistribucion() {
        boolean compacto = getHeight() < 850;
        boolean estrecho = getWidth() < 1450;
        int alturaTarjetas = compacto ? 164 : 184;
        javax.swing.JPanel[] tarjetas = {pnlVentas, pnlPedidos, pnlMesas, pnlClientes};
        for (javax.swing.JPanel tarjeta : tarjetas) tarjeta.setPreferredSize(new java.awt.Dimension(240, alturaTarjetas));
        pnlNavegacion.setPreferredSize(new java.awt.Dimension(300, compacto ? 406 : 476));
        pnlCerrarSesion.setPreferredSize(new java.awt.Dimension(300, compacto ? 88 : 114));
        pnlCerrarSesion.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, compacto ? 28 : 54, 20));
        lblSaludo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, estrecho ? 28 : 36));
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, estrecho ? 16 : 20));
        pnlInfoUsuario.setPreferredSize(new java.awt.Dimension(468, 90));
        javax.swing.JLabel[] titulos = {lblVentasTitulo, lblPedidosTitulo, lblMesasTitulo, lblClientesTitulo};
        chifatailung.vista.componentes.IconoSvg[] iconos = {icoVentas, icoPedidos, icoMesas, icoClientes};
        for (int i=0; i<titulos.length; i++) {
            titulos[i].setFont(new java.awt.Font("Segoe UI", i == 0 ? java.awt.Font.PLAIN : java.awt.Font.BOLD, estrecho ? 14 : 17));
            int lado = estrecho ? 52 : 64;
            iconos[i].setPreferredSize(new java.awt.Dimension(lado,lado));
            iconos[i].setMinimumSize(new java.awt.Dimension(lado,lado));
            iconos[i].setTamano(estrecho ? 30 : 36);
        }
        lblVentasValor.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, estrecho ? 26 : 30));
        lblTituloVentas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, estrecho ? 19 : 23));
        lblTituloActividad.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, estrecho ? 19 : 23));
        cmbPeriodo.setPreferredSize(new java.awt.Dimension(estrecho ? 128 : 160, 42));
        javax.swing.JLabel[] movimientos = {lblMovimiento0, lblMovimiento1, lblMovimiento2, lblMovimiento3, lblMovimiento4};
        javax.swing.JLabel[] detalles = {lblDetalle0, lblDetalle1, lblDetalle2, lblDetalle3, lblDetalle4};
        for (javax.swing.JLabel etiqueta : movimientos) etiqueta.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, compacto ? 14 : estrecho ? 15 : 18));
        for (javax.swing.JLabel etiqueta : detalles) etiqueta.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, compacto ? 12 : estrecho ? 14 : 16));
        javax.swing.JPanel[] textosActividad = {pnlTextoActividad0, pnlTextoActividad1, pnlTextoActividad2, pnlTextoActividad3, pnlTextoActividad4};
        for (int i=0; i<textosActividad.length; i++) {
            java.awt.GridBagLayout layoutTexto = (java.awt.GridBagLayout) textosActividad[i].getLayout();
            java.awt.GridBagConstraints restricciones = layoutTexto.getConstraints(detalles[i]);
            restricciones.insets.top = compacto ? 2 : 4;
            layoutTexto.setConstraints(detalles[i], restricciones);
        }
        ((java.awt.GridLayout)pnlMovimientos.getLayout()).setVgap(compacto ? 4 : 8);
        pnlTituloActividad.setPreferredSize(new java.awt.Dimension(500, compacto ? 44 : 52));
        pnlTituloVentas.setPreferredSize(new java.awt.Dimension(500, compacto ? 44 : 52));
        pnlActividadReciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(compacto ? 12 : 16,16,compacto ? 12 : 22,18));
        int alturaInferior = Math.max(300, Math.min(450, pnlCuadricula.getHeight() - alturaTarjetas - 22));
        pnlVentasSemana.setPreferredSize(new java.awt.Dimension(1, alturaInferior));
        pnlActividadReciente.setPreferredSize(new java.awt.Dimension(1, alturaInferior));
        pnlPrincipal.revalidate();
        pnlPrincipal.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlLateral = new chifatailung.vista.componentes.PanelRedondeado();
        pnlIdentidad = new javax.swing.JPanel();
        pnlMarca = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblNombreRestaurante = new javax.swing.JLabel();
        lblEslogan = new javax.swing.JLabel();
        arteNubes = new chifatailung.vista.componentes.ArteReferencia();
        pnlCuerpoLateral = new javax.swing.JPanel();
        pnlNavegacion = new javax.swing.JPanel();
        btnInicio = new chifatailung.vista.componentes.BotonNavegacion();
        btnCarta = new chifatailung.vista.componentes.BotonNavegacion();
        btnMesas = new chifatailung.vista.componentes.BotonNavegacion();
        btnPedidos = new chifatailung.vista.componentes.BotonNavegacion();
        btnCola = new chifatailung.vista.componentes.BotonNavegacion();
        btnComprobantes = new chifatailung.vista.componentes.BotonNavegacion();
        btnBusqueda = new chifatailung.vista.componentes.BotonNavegacion();
        arteLeopardo = new chifatailung.vista.componentes.ArteReferencia();
        pnlCerrarSesion = new javax.swing.JPanel();
        btnCerrarSesion = new chifatailung.vista.componentes.BotonNavegacion();
        pnlPrincipal = new javax.swing.JPanel();
        pnlEncabezado = new javax.swing.JPanel();
        pnlSaludo = new javax.swing.JPanel();
        lblSaludo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblDemostracion = new javax.swing.JLabel();
        pnlInfoUsuario = new javax.swing.JPanel();
        pnlFechaHora = new javax.swing.JPanel();
        icoCalendario = new chifatailung.vista.componentes.IconoSvg();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        pnlUsuario = new javax.swing.JPanel();
        icoUsuario = new chifatailung.vista.componentes.IconoSvg();
        lblUsuarioConectado = new javax.swing.JLabel();
        lblEnLinea = new javax.swing.JLabel();
        icoDesplegar = new chifatailung.vista.componentes.IconoSvg();
        pnlCuadricula = new javax.swing.JPanel();
        pnlVentas = new chifatailung.vista.componentes.PanelRedondeado();
        icoVentas = new chifatailung.vista.componentes.IconoSvg();
        lblVentasTitulo = new javax.swing.JLabel();
        lblVentasValor = new javax.swing.JLabel();
        lblVentasTendencia = new javax.swing.JLabel();
        pnlPedidos = new chifatailung.vista.componentes.PanelRedondeado();
        icoPedidos = new chifatailung.vista.componentes.IconoSvg();
        lblPedidosTitulo = new javax.swing.JLabel();
        lblPedidosValor = new javax.swing.JLabel();
        lblPedidosTendencia = new javax.swing.JLabel();
        pnlMesas = new chifatailung.vista.componentes.PanelRedondeado();
        icoMesas = new chifatailung.vista.componentes.IconoSvg();
        lblMesasTitulo = new javax.swing.JLabel();
        lblMesasValor = new javax.swing.JLabel();
        pnlMesasProgreso = new javax.swing.JPanel();
        prgMesas = new javax.swing.JProgressBar();
        lblMesasPorcentaje = new javax.swing.JLabel();
        pnlClientes = new chifatailung.vista.componentes.PanelRedondeado();
        icoClientes = new chifatailung.vista.componentes.IconoSvg();
        lblClientesTitulo = new javax.swing.JLabel();
        lblClientesValor = new javax.swing.JLabel();
        lblClientesTendencia = new javax.swing.JLabel();
        pnlVentasSemana = new chifatailung.vista.componentes.PanelRedondeado();
        pnlTituloVentas = new javax.swing.JPanel();
        icoGrafico = new chifatailung.vista.componentes.IconoSvg();
        lblTituloVentas = new javax.swing.JLabel();
        cmbPeriodo = new javax.swing.JComboBox();
        graficoVentas = new chifatailung.vista.componentes.GraficoVentas();
        pnlActividadReciente = new chifatailung.vista.componentes.PanelRedondeado();
        pnlTituloActividad = new javax.swing.JPanel();
        icoActividad = new chifatailung.vista.componentes.IconoSvg();
        lblTituloActividad = new javax.swing.JLabel();
        btnVerTodo = new javax.swing.JButton();
        pnlMovimientos = new javax.swing.JPanel();
        pnlMovimiento0 = new javax.swing.JPanel();
        icoMovimiento0 = new chifatailung.vista.componentes.IconoSvg();
        pnlTextoActividad0 = new javax.swing.JPanel();
        lblMovimiento0 = new javax.swing.JLabel();
        lblDetalle0 = new javax.swing.JLabel();
        lblHoraMovimiento0 = new javax.swing.JLabel();
        pnlMovimiento1 = new javax.swing.JPanel();
        icoMovimiento1 = new chifatailung.vista.componentes.IconoSvg();
        pnlTextoActividad1 = new javax.swing.JPanel();
        lblMovimiento1 = new javax.swing.JLabel();
        lblDetalle1 = new javax.swing.JLabel();
        lblHoraMovimiento1 = new javax.swing.JLabel();
        pnlMovimiento2 = new javax.swing.JPanel();
        icoMovimiento2 = new chifatailung.vista.componentes.IconoSvg();
        pnlTextoActividad2 = new javax.swing.JPanel();
        lblMovimiento2 = new javax.swing.JLabel();
        lblDetalle2 = new javax.swing.JLabel();
        lblHoraMovimiento2 = new javax.swing.JLabel();
        pnlMovimiento3 = new javax.swing.JPanel();
        icoMovimiento3 = new chifatailung.vista.componentes.IconoSvg();
        pnlTextoActividad3 = new javax.swing.JPanel();
        lblMovimiento3 = new javax.swing.JLabel();
        lblDetalle3 = new javax.swing.JLabel();
        lblHoraMovimiento3 = new javax.swing.JLabel();
        pnlMovimiento4 = new javax.swing.JPanel();
        icoMovimiento4 = new chifatailung.vista.componentes.IconoSvg();
        pnlTextoActividad4 = new javax.swing.JPanel();
        lblMovimiento4 = new javax.swing.JLabel();
        lblDetalle4 = new javax.swing.JLabel();
        lblHoraMovimiento4 = new javax.swing.JLabel();
        pnlEspacioInferior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chifa Tai Lung · Panel principal");
        setMinimumSize(new java.awt.Dimension(1180, 700));

        pnlLateral.setBackground(new java.awt.Color(83, 11, 5));
        pnlLateral.setColorFinal(new java.awt.Color(101, 15, 5));
        pnlLateral.setRadio(0);
        pnlLateral.setPreferredSize(new java.awt.Dimension(300, 960));
        pnlLateral.setMinimumSize(new java.awt.Dimension(300, 600));
        pnlLateral.setLayout(new java.awt.BorderLayout());

        pnlIdentidad.setOpaque(false);
        pnlIdentidad.setPreferredSize(new java.awt.Dimension(300, 166));
        pnlIdentidad.setLayout(new java.awt.BorderLayout());

        pnlMarca.setOpaque(false);
        pnlMarca.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 24, 0, 18));
        pnlMarca.setLayout(new java.awt.GridBagLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-login.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 14);
        pnlMarca.add(lblLogo, gridBagConstraints);

        lblNombreRestaurante.setText("<html>Chifa<br>Tai Lung</html>");
        lblNombreRestaurante.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNombreRestaurante.setForeground(new java.awt.Color(255, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMarca.add(lblNombreRestaurante, gridBagConstraints);

        lblEslogan.setText("<html>Sabor tradicional,<br>siempre contigo.</html>");
        lblEslogan.setForeground(new java.awt.Color(255, 248, 235));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlMarca.add(lblEslogan, gridBagConstraints);

        pnlIdentidad.add(pnlMarca, java.awt.BorderLayout.CENTER);

        arteNubes.setRegion("nubes");
        arteNubes.setPreferredSize(new java.awt.Dimension(300, 40));
        pnlIdentidad.add(arteNubes, java.awt.BorderLayout.SOUTH);

        pnlLateral.add(pnlIdentidad, java.awt.BorderLayout.NORTH);

        pnlCuerpoLateral.setOpaque(false);
        pnlCuerpoLateral.setLayout(new java.awt.BorderLayout());

        pnlNavegacion.setOpaque(false);
        pnlNavegacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 13, 4, 9));
        pnlNavegacion.setPreferredSize(new java.awt.Dimension(300, 476));
        pnlNavegacion.setLayout(new java.awt.GridLayout(7, 1, 0, 4));

        btnInicio.setText("Inicio");
        btnInicio.setActivo(true);
        btnInicio.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnInicio);

        btnCarta.setText("Carta de platos");
        btnCarta.setRecurso("carta");
        btnCarta.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnCarta);

        btnMesas.setText("Mesas");
        btnMesas.setRecurso("mesas");
        btnMesas.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnMesas);

        btnPedidos.setText("Pedidos");
        btnPedidos.setRecurso("pedidos");
        btnPedidos.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnPedidos);

        btnCola.setText("Cola de atención");
        btnCola.setRecurso("clientes");
        btnCola.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnCola);

        btnComprobantes.setText("Comprobantes");
        btnComprobantes.setRecurso("comprobantes");
        btnComprobantes.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnComprobantes);

        btnBusqueda.setText("Búsqueda de platos");
        btnBusqueda.setRecurso("buscar");
        btnBusqueda.setToolTipText("Presentación visual; módulo todavía no conectado.");
        pnlNavegacion.add(btnBusqueda);

        pnlCuerpoLateral.add(pnlNavegacion, java.awt.BorderLayout.NORTH);
        pnlCuerpoLateral.add(arteLeopardo, java.awt.BorderLayout.CENTER);

        pnlCerrarSesion.setOpaque(false);
        pnlCerrarSesion.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 26, 54, 20));
        pnlCerrarSesion.setPreferredSize(new java.awt.Dimension(300, 114));
        pnlCerrarSesion.setLayout(new java.awt.BorderLayout());

        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setRecurso("salir");
        btnCerrarSesion.setDorado(true);
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnCerrarSesion.setToolTipText("Control visual: pendiente de conectar a la sesión.");
        pnlCerrarSesion.add(btnCerrarSesion, java.awt.BorderLayout.CENTER);

        pnlCuerpoLateral.add(pnlCerrarSesion, java.awt.BorderLayout.SOUTH);

        pnlLateral.add(pnlCuerpoLateral, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlLateral, java.awt.BorderLayout.WEST);

        pnlPrincipal.setBackground(new java.awt.Color(247, 243, 237));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(28, 32, 26, 24));
        pnlPrincipal.setLayout(new java.awt.BorderLayout());

        pnlEncabezado.setOpaque(false);
        pnlEncabezado.setPreferredSize(new java.awt.Dimension(1100, 126));
        pnlEncabezado.setLayout(new java.awt.GridBagLayout());

        pnlSaludo.setOpaque(false);
        pnlSaludo.setLayout(new java.awt.GridBagLayout());

        lblSaludo.setText("¡Hola, Administrador!");
        lblSaludo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblSaludo.setForeground(new java.awt.Color(12, 28, 42));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlSaludo.add(lblSaludo, gridBagConstraints);

        lblDescripcion.setText("Aquí tienes un resumen del estado del restaurante.");
        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(104, 113, 125));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlSaludo.add(lblDescripcion, gridBagConstraints);

        lblDemostracion.setText("DATOS DE DEMOSTRACIÓN · Sin conexión a servicios");
        lblDemostracion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDemostracion.setForeground(new java.awt.Color(165, 20, 29));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlSaludo.add(lblDemostracion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlEncabezado.add(pnlSaludo, gridBagConstraints);

        pnlInfoUsuario.setOpaque(false);
        pnlInfoUsuario.setPreferredSize(new java.awt.Dimension(468, 90));
        pnlInfoUsuario.setLayout(new java.awt.GridBagLayout());

        pnlFechaHora.setOpaque(false);
        pnlFechaHora.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 20));
        pnlFechaHora.setLayout(new java.awt.GridBagLayout());

        icoCalendario.setRecurso("calendario");
        icoCalendario.setColorIcono(new java.awt.Color(176, 9, 24));
        icoCalendario.setPreferredSize(new java.awt.Dimension(38, 38));
        icoCalendario.setMinimumSize(new java.awt.Dimension(38, 38));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlFechaHora.add(icoCalendario, gridBagConstraints);

        lblFecha.setText("Vie. 30 de oct. de 2026");
        lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(16, 32, 47));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlFechaHora.add(lblFecha, gridBagConstraints);

        lblHora.setText("01:14 PM");
        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblHora.setForeground(new java.awt.Color(16, 32, 47));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlFechaHora.add(lblHora, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlInfoUsuario.add(pnlFechaHora, gridBagConstraints);

        pnlUsuario.setOpaque(false);
        pnlUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        pnlUsuario.setLayout(new java.awt.GridBagLayout());

        icoUsuario.setRecurso("usuario");
        icoUsuario.setTamano(32);
        icoUsuario.setFondoIcono(new java.awt.Color(37, 37, 37));
        icoUsuario.setPreferredSize(new java.awt.Dimension(52, 52));
        icoUsuario.setMinimumSize(new java.awt.Dimension(52, 52));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        pnlUsuario.add(icoUsuario, gridBagConstraints);

        lblUsuarioConectado.setText("Administrador");
        lblUsuarioConectado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUsuarioConectado.setForeground(new java.awt.Color(16, 32, 47));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlUsuario.add(lblUsuarioConectado, gridBagConstraints);

        lblEnLinea.setText("● En línea");
        lblEnLinea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEnLinea.setForeground(new java.awt.Color(42, 159, 97));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlUsuario.add(lblEnLinea, gridBagConstraints);

        icoDesplegar.setRecurso("flecha");
        icoDesplegar.setTamano(18);
        icoDesplegar.setColorIcono(new java.awt.Color(16, 32, 47));
        icoDesplegar.setPreferredSize(new java.awt.Dimension(24, 24));
        icoDesplegar.setMinimumSize(new java.awt.Dimension(24, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlUsuario.add(icoDesplegar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlInfoUsuario.add(pnlUsuario, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        pnlEncabezado.add(pnlInfoUsuario, gridBagConstraints);

        pnlPrincipal.add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlCuadricula.setOpaque(false);
        pnlCuadricula.setLayout(new java.awt.GridBagLayout());

        pnlVentas.setBackground(new java.awt.Color(167, 9, 22));
        pnlVentas.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlVentas.setPreferredSize(new java.awt.Dimension(240, 184));
        pnlVentas.setMinimumSize(new java.awt.Dimension(0, 164));
        pnlVentas.setColorFinal(new java.awt.Color(180, 59, 64));
        pnlVentas.setColorBorde(new java.awt.Color(174, 21, 32));
        pnlVentas.setLayout(new java.awt.GridBagLayout());

        icoVentas.setRecurso("ventas");
        icoVentas.setTamano(36);
        icoVentas.setFondoIcono(new java.awt.Color(238, 16, 36));
        icoVentas.setPreferredSize(new java.awt.Dimension(64, 64));
        icoVentas.setMinimumSize(new java.awt.Dimension(64, 64));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlVentas.add(icoVentas, gridBagConstraints);

        lblVentasTitulo.setText("Ventas de hoy");
        lblVentasTitulo.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblVentasTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasTitulo.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlVentas.add(lblVentasTitulo, gridBagConstraints);

        lblVentasValor.setText("S/ 1,248.00");
        lblVentasValor.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblVentasValor.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblVentasValor.setMinimumSize(new java.awt.Dimension(0, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlVentas.add(lblVentasValor, gridBagConstraints);

        lblVentasTendencia.setText("<html><font color=\"#BFE47D\">↑ 12%</font><font color=\"#FFF6E9\">　vs. ayer</font></html>");
        lblVentasTendencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblVentasTendencia.setForeground(new java.awt.Color(255, 255, 255));
        lblVentasTendencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlVentas.add(lblVentasTendencia, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 9);
        pnlCuadricula.add(pnlVentas, gridBagConstraints);

        pnlPedidos.setBackground(new java.awt.Color(255, 245, 230));
        pnlPedidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlPedidos.setPreferredSize(new java.awt.Dimension(240, 184));
        pnlPedidos.setMinimumSize(new java.awt.Dimension(0, 164));
        pnlPedidos.setLayout(new java.awt.GridBagLayout());

        icoPedidos.setRecurso("carta");
        icoPedidos.setTamano(36);
        icoPedidos.setColorIcono(new java.awt.Color(162, 18, 22));
        icoPedidos.setFondoIcono(new java.awt.Color(247, 168, 26));
        icoPedidos.setPreferredSize(new java.awt.Dimension(64, 64));
        icoPedidos.setMinimumSize(new java.awt.Dimension(64, 64));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlPedidos.add(icoPedidos, gridBagConstraints);

        lblPedidosTitulo.setText("Pedidos de hoy");
        lblPedidosTitulo.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblPedidosTitulo.setForeground(new java.awt.Color(16, 32, 47));
        lblPedidosTitulo.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlPedidos.add(lblPedidosTitulo, gridBagConstraints);

        lblPedidosValor.setText("28");
        lblPedidosValor.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPedidosValor.setForeground(new java.awt.Color(16, 32, 47));
        lblPedidosValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPedidosValor.setMinimumSize(new java.awt.Dimension(0, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlPedidos.add(lblPedidosValor, gridBagConstraints);

        lblPedidosTendencia.setText("<html><font color=\"#00A657\">↑ 7%</font><font color=\"#65707C\">　vs. ayer</font></html>");
        lblPedidosTendencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPedidosTendencia.setForeground(new java.awt.Color(16, 32, 47));
        lblPedidosTendencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlPedidos.add(lblPedidosTendencia, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 11, 9);
        pnlCuadricula.add(pnlPedidos, gridBagConstraints);

        pnlMesas.setBackground(new java.awt.Color(230, 243, 254));
        pnlMesas.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlMesas.setPreferredSize(new java.awt.Dimension(240, 184));
        pnlMesas.setMinimumSize(new java.awt.Dimension(0, 164));
        pnlMesas.setLayout(new java.awt.GridBagLayout());

        icoMesas.setRecurso("mesas");
        icoMesas.setTamano(36);
        icoMesas.setFondoIcono(new java.awt.Color(39, 137, 188));
        icoMesas.setPreferredSize(new java.awt.Dimension(64, 64));
        icoMesas.setMinimumSize(new java.awt.Dimension(64, 64));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlMesas.add(icoMesas, gridBagConstraints);

        lblMesasTitulo.setText("Mesas ocupadas");
        lblMesasTitulo.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblMesasTitulo.setForeground(new java.awt.Color(16, 32, 47));
        lblMesasTitulo.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlMesas.add(lblMesasTitulo, gridBagConstraints);

        lblMesasValor.setText("6 / 12");
        lblMesasValor.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblMesasValor.setForeground(new java.awt.Color(16, 32, 47));
        lblMesasValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMesasValor.setMinimumSize(new java.awt.Dimension(0, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlMesas.add(lblMesasValor, gridBagConstraints);

        pnlMesasProgreso.setOpaque(false);
        pnlMesasProgreso.setLayout(new java.awt.BorderLayout(12, 0));

        prgMesas.setMaximum(12);
        prgMesas.setValue(6);
        prgMesas.setForeground(new java.awt.Color(170, 16, 27));
        prgMesas.setBackground(new java.awt.Color(218, 221, 224));
        prgMesas.setPreferredSize(new java.awt.Dimension(130, 16));
        pnlMesasProgreso.add(prgMesas, java.awt.BorderLayout.CENTER);

        lblMesasPorcentaje.setText("50%");
        lblMesasPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMesasPorcentaje.setForeground(new java.awt.Color(16, 32, 47));
        pnlMesasProgreso.add(lblMesasPorcentaje, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlMesas.add(pnlMesasProgreso, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 11, 9);
        pnlCuadricula.add(pnlMesas, gridBagConstraints);

        pnlClientes.setBackground(new java.awt.Color(237, 248, 237));
        pnlClientes.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlClientes.setPreferredSize(new java.awt.Dimension(240, 184));
        pnlClientes.setMinimumSize(new java.awt.Dimension(0, 164));
        pnlClientes.setLayout(new java.awt.GridBagLayout());

        icoClientes.setRecurso("clientes");
        icoClientes.setTamano(36);
        icoClientes.setFondoIcono(new java.awt.Color(39, 166, 94));
        icoClientes.setPreferredSize(new java.awt.Dimension(64, 64));
        icoClientes.setMinimumSize(new java.awt.Dimension(64, 64));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlClientes.add(icoClientes, gridBagConstraints);

        lblClientesTitulo.setText("Clientes hoy");
        lblClientesTitulo.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblClientesTitulo.setForeground(new java.awt.Color(16, 32, 47));
        lblClientesTitulo.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlClientes.add(lblClientesTitulo, gridBagConstraints);

        lblClientesValor.setText("42");
        lblClientesValor.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblClientesValor.setForeground(new java.awt.Color(16, 32, 47));
        lblClientesValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClientesValor.setMinimumSize(new java.awt.Dimension(0, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlClientes.add(lblClientesValor, gridBagConstraints);

        lblClientesTendencia.setText("<html><font color=\"#00A657\">↑ 18%</font><font color=\"#65707C\">　vs. ayer</font></html>");
        lblClientesTendencia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblClientesTendencia.setForeground(new java.awt.Color(16, 32, 47));
        lblClientesTendencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        pnlClientes.add(lblClientesTendencia, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 11, 0);
        pnlCuadricula.add(pnlClientes, gridBagConstraints);

        pnlVentasSemana.setBackground(new java.awt.Color(252, 250, 247));
        pnlVentasSemana.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 22, 18));
        pnlVentasSemana.setPreferredSize(new java.awt.Dimension(1, 450));
        pnlVentasSemana.setMinimumSize(new java.awt.Dimension(0, 300));
        pnlVentasSemana.setLayout(new java.awt.BorderLayout());

        pnlTituloVentas.setOpaque(false);
        pnlTituloVentas.setPreferredSize(new java.awt.Dimension(500, 52));
        pnlTituloVentas.setLayout(new java.awt.BorderLayout(12, 0));

        icoGrafico.setRecurso("grafico");
        icoGrafico.setTamano(32);
        icoGrafico.setColorIcono(new java.awt.Color(173, 9, 24));
        icoGrafico.setPreferredSize(new java.awt.Dimension(40, 40));
        icoGrafico.setMinimumSize(new java.awt.Dimension(40, 40));
        pnlTituloVentas.add(icoGrafico, java.awt.BorderLayout.WEST);

        lblTituloVentas.setText("Ventas de la semana");
        lblTituloVentas.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        lblTituloVentas.setForeground(new java.awt.Color(16, 32, 47));
        pnlTituloVentas.add(lblTituloVentas, java.awt.BorderLayout.CENTER);

        cmbPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Esta semana" }));
        cmbPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbPeriodo.setPreferredSize(new java.awt.Dimension(160, 42));
        pnlTituloVentas.add(cmbPeriodo, java.awt.BorderLayout.EAST);

        pnlVentasSemana.add(pnlTituloVentas, java.awt.BorderLayout.NORTH);
        pnlVentasSemana.add(graficoVentas, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 9);
        pnlCuadricula.add(pnlVentasSemana, gridBagConstraints);

        pnlActividadReciente.setBackground(new java.awt.Color(252, 250, 247));
        pnlActividadReciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 22, 18));
        pnlActividadReciente.setPreferredSize(new java.awt.Dimension(1, 450));
        pnlActividadReciente.setMinimumSize(new java.awt.Dimension(0, 300));
        pnlActividadReciente.setLayout(new java.awt.BorderLayout());

        pnlTituloActividad.setOpaque(false);
        pnlTituloActividad.setPreferredSize(new java.awt.Dimension(500, 52));
        pnlTituloActividad.setLayout(new java.awt.BorderLayout(12, 0));

        icoActividad.setRecurso("reloj");
        icoActividad.setTamano(32);
        icoActividad.setColorIcono(new java.awt.Color(188, 16, 32));
        icoActividad.setPreferredSize(new java.awt.Dimension(40, 40));
        icoActividad.setMinimumSize(new java.awt.Dimension(40, 40));
        pnlTituloActividad.add(icoActividad, java.awt.BorderLayout.WEST);

        lblTituloActividad.setText("Actividad reciente");
        lblTituloActividad.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        lblTituloActividad.setForeground(new java.awt.Color(16, 32, 47));
        pnlTituloActividad.add(lblTituloActividad, java.awt.BorderLayout.CENTER);

        btnVerTodo.setText("Ver todo");
        btnVerTodo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnVerTodo.setForeground(new java.awt.Color(167, 13, 25));
        btnVerTodo.setContentAreaFilled(false);
        btnVerTodo.setBorderPainted(false);
        btnVerTodo.setToolTipText("Lista de demostración.");
        pnlTituloActividad.add(btnVerTodo, java.awt.BorderLayout.EAST);

        pnlActividadReciente.add(pnlTituloActividad, java.awt.BorderLayout.NORTH);

        pnlMovimientos.setOpaque(false);
        pnlMovimientos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 0, 8));
        pnlMovimientos.setLayout(new java.awt.GridLayout(5, 1, 0, 8));

        pnlMovimiento0.setOpaque(false);
        pnlMovimiento0.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(236, 231, 225)));
        pnlMovimiento0.setLayout(new java.awt.BorderLayout());

        icoMovimiento0.setRecurso("pedidos");
        icoMovimiento0.setTamano(32);
        icoMovimiento0.setFondoIcono(new java.awt.Color(175, 7, 23));
        icoMovimiento0.setPreferredSize(new java.awt.Dimension(52, 52));
        icoMovimiento0.setMinimumSize(new java.awt.Dimension(52, 52));
        pnlMovimiento0.add(icoMovimiento0, java.awt.BorderLayout.WEST);

        pnlTextoActividad0.setOpaque(false);
        pnlTextoActividad0.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        pnlTextoActividad0.setLayout(new java.awt.GridBagLayout());

        lblMovimiento0.setText("Pedido #025 registrado");
        lblMovimiento0.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMovimiento0.setForeground(new java.awt.Color(16, 32, 47));
        lblMovimiento0.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTextoActividad0.add(lblMovimiento0, gridBagConstraints);

        lblDetalle0.setText("Mesa 03 · 3 platos");
        lblDetalle0.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDetalle0.setForeground(new java.awt.Color(100, 111, 123));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTextoActividad0.add(lblDetalle0, gridBagConstraints);

        pnlMovimiento0.add(pnlTextoActividad0, java.awt.BorderLayout.CENTER);

        lblHoraMovimiento0.setText("13:12");
        lblHoraMovimiento0.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoraMovimiento0.setForeground(new java.awt.Color(101, 112, 123));
        lblHoraMovimiento0.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblHoraMovimiento0.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 0, 0));
        pnlMovimiento0.add(lblHoraMovimiento0, java.awt.BorderLayout.EAST);

        pnlMovimientos.add(pnlMovimiento0);

        pnlMovimiento1.setOpaque(false);
        pnlMovimiento1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(236, 231, 225)));
        pnlMovimiento1.setLayout(new java.awt.BorderLayout());

        icoMovimiento1.setRecurso("aceptar");
        icoMovimiento1.setTamano(32);
        icoMovimiento1.setFondoIcono(new java.awt.Color(41, 170, 102));
        icoMovimiento1.setPreferredSize(new java.awt.Dimension(52, 52));
        icoMovimiento1.setMinimumSize(new java.awt.Dimension(52, 52));
        pnlMovimiento1.add(icoMovimiento1, java.awt.BorderLayout.WEST);

        pnlTextoActividad1.setOpaque(false);
        pnlTextoActividad1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        pnlTextoActividad1.setLayout(new java.awt.GridBagLayout());

        lblMovimiento1.setText("Pedido #024 entregado");
        lblMovimiento1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMovimiento1.setForeground(new java.awt.Color(16, 32, 47));
        lblMovimiento1.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTextoActividad1.add(lblMovimiento1, gridBagConstraints);

        lblDetalle1.setText("Mesa 07");
        lblDetalle1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDetalle1.setForeground(new java.awt.Color(100, 111, 123));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTextoActividad1.add(lblDetalle1, gridBagConstraints);

        pnlMovimiento1.add(pnlTextoActividad1, java.awt.BorderLayout.CENTER);

        lblHoraMovimiento1.setText("13:04");
        lblHoraMovimiento1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoraMovimiento1.setForeground(new java.awt.Color(101, 112, 123));
        lblHoraMovimiento1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblHoraMovimiento1.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 0, 0));
        pnlMovimiento1.add(lblHoraMovimiento1, java.awt.BorderLayout.EAST);

        pnlMovimientos.add(pnlMovimiento1);

        pnlMovimiento2.setOpaque(false);
        pnlMovimiento2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(236, 231, 225)));
        pnlMovimiento2.setLayout(new java.awt.BorderLayout());

        icoMovimiento2.setRecurso("mesas");
        icoMovimiento2.setTamano(32);
        icoMovimiento2.setFondoIcono(new java.awt.Color(40, 134, 186));
        icoMovimiento2.setPreferredSize(new java.awt.Dimension(52, 52));
        icoMovimiento2.setMinimumSize(new java.awt.Dimension(52, 52));
        pnlMovimiento2.add(icoMovimiento2, java.awt.BorderLayout.WEST);

        pnlTextoActividad2.setOpaque(false);
        pnlTextoActividad2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        pnlTextoActividad2.setLayout(new java.awt.GridBagLayout());

        lblMovimiento2.setText("Mesa 08 ocupada");
        lblMovimiento2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMovimiento2.setForeground(new java.awt.Color(16, 32, 47));
        lblMovimiento2.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTextoActividad2.add(lblMovimiento2, gridBagConstraints);

        lblDetalle2.setText("4 personas");
        lblDetalle2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDetalle2.setForeground(new java.awt.Color(100, 111, 123));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTextoActividad2.add(lblDetalle2, gridBagConstraints);

        pnlMovimiento2.add(pnlTextoActividad2, java.awt.BorderLayout.CENTER);

        lblHoraMovimiento2.setText("12:58");
        lblHoraMovimiento2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoraMovimiento2.setForeground(new java.awt.Color(101, 112, 123));
        lblHoraMovimiento2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblHoraMovimiento2.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 0, 0));
        pnlMovimiento2.add(lblHoraMovimiento2, java.awt.BorderLayout.EAST);

        pnlMovimientos.add(pnlMovimiento2);

        pnlMovimiento3.setOpaque(false);
        pnlMovimiento3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(236, 231, 225)));
        pnlMovimiento3.setLayout(new java.awt.BorderLayout());

        icoMovimiento3.setRecurso("clientes");
        icoMovimiento3.setTamano(32);
        icoMovimiento3.setFondoIcono(new java.awt.Color(245, 169, 28));
        icoMovimiento3.setPreferredSize(new java.awt.Dimension(52, 52));
        icoMovimiento3.setMinimumSize(new java.awt.Dimension(52, 52));
        pnlMovimiento3.add(icoMovimiento3, java.awt.BorderLayout.WEST);

        pnlTextoActividad3.setOpaque(false);
        pnlTextoActividad3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        pnlTextoActividad3.setLayout(new java.awt.GridBagLayout());

        lblMovimiento3.setText("Cliente registrado");
        lblMovimiento3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMovimiento3.setForeground(new java.awt.Color(16, 32, 47));
        lblMovimiento3.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTextoActividad3.add(lblMovimiento3, gridBagConstraints);

        lblDetalle3.setText("Cliente: Juan Pérez");
        lblDetalle3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDetalle3.setForeground(new java.awt.Color(100, 111, 123));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTextoActividad3.add(lblDetalle3, gridBagConstraints);

        pnlMovimiento3.add(pnlTextoActividad3, java.awt.BorderLayout.CENTER);

        lblHoraMovimiento3.setText("12:45");
        lblHoraMovimiento3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoraMovimiento3.setForeground(new java.awt.Color(101, 112, 123));
        lblHoraMovimiento3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblHoraMovimiento3.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 0, 0));
        pnlMovimiento3.add(lblHoraMovimiento3, java.awt.BorderLayout.EAST);

        pnlMovimientos.add(pnlMovimiento3);

        pnlMovimiento4.setOpaque(false);
        pnlMovimiento4.setLayout(new java.awt.BorderLayout());

        icoMovimiento4.setRecurso("cancelar");
        icoMovimiento4.setTamano(32);
        icoMovimiento4.setFondoIcono(new java.awt.Color(242, 8, 36));
        icoMovimiento4.setPreferredSize(new java.awt.Dimension(52, 52));
        icoMovimiento4.setMinimumSize(new java.awt.Dimension(52, 52));
        pnlMovimiento4.add(icoMovimiento4, java.awt.BorderLayout.WEST);

        pnlTextoActividad4.setOpaque(false);
        pnlTextoActividad4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 8));
        pnlTextoActividad4.setLayout(new java.awt.GridBagLayout());

        lblMovimiento4.setText("Pedido #023 cancelado");
        lblMovimiento4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMovimiento4.setForeground(new java.awt.Color(16, 32, 47));
        lblMovimiento4.setMinimumSize(new java.awt.Dimension(0, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlTextoActividad4.add(lblMovimiento4, gridBagConstraints);

        lblDetalle4.setText("Mesa 05");
        lblDetalle4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDetalle4.setForeground(new java.awt.Color(100, 111, 123));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlTextoActividad4.add(lblDetalle4, gridBagConstraints);

        pnlMovimiento4.add(pnlTextoActividad4, java.awt.BorderLayout.CENTER);

        lblHoraMovimiento4.setText("12:32");
        lblHoraMovimiento4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoraMovimiento4.setForeground(new java.awt.Color(101, 112, 123));
        lblHoraMovimiento4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblHoraMovimiento4.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 0, 0));
        pnlMovimiento4.add(lblHoraMovimiento4, java.awt.BorderLayout.EAST);

        pnlMovimientos.add(pnlMovimiento4);

        pnlActividadReciente.add(pnlMovimientos, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(11, 9, 0, 0);
        pnlCuadricula.add(pnlActividadReciente, gridBagConstraints);

        pnlEspacioInferior.setOpaque(false);
        pnlEspacioInferior.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlCuadricula.add(pnlEspacioInferior, gridBagConstraints);

        pnlPrincipal.add(pnlCuadricula, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Tema.aplicar();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chifatailung.vista.componentes.ArteReferencia arteLeopardo;
    private chifatailung.vista.componentes.ArteReferencia arteNubes;
    private chifatailung.vista.componentes.BotonNavegacion btnBusqueda;
    private chifatailung.vista.componentes.BotonNavegacion btnCarta;
    private chifatailung.vista.componentes.BotonNavegacion btnCerrarSesion;
    private chifatailung.vista.componentes.BotonNavegacion btnCola;
    private chifatailung.vista.componentes.BotonNavegacion btnComprobantes;
    private chifatailung.vista.componentes.BotonNavegacion btnInicio;
    private chifatailung.vista.componentes.BotonNavegacion btnMesas;
    private chifatailung.vista.componentes.BotonNavegacion btnPedidos;
    private javax.swing.JButton btnVerTodo;
    private javax.swing.JComboBox cmbPeriodo;
    private chifatailung.vista.componentes.GraficoVentas graficoVentas;
    private chifatailung.vista.componentes.IconoSvg icoActividad;
    private chifatailung.vista.componentes.IconoSvg icoCalendario;
    private chifatailung.vista.componentes.IconoSvg icoClientes;
    private chifatailung.vista.componentes.IconoSvg icoDesplegar;
    private chifatailung.vista.componentes.IconoSvg icoGrafico;
    private chifatailung.vista.componentes.IconoSvg icoMesas;
    private chifatailung.vista.componentes.IconoSvg icoMovimiento0;
    private chifatailung.vista.componentes.IconoSvg icoMovimiento1;
    private chifatailung.vista.componentes.IconoSvg icoMovimiento2;
    private chifatailung.vista.componentes.IconoSvg icoMovimiento3;
    private chifatailung.vista.componentes.IconoSvg icoMovimiento4;
    private chifatailung.vista.componentes.IconoSvg icoPedidos;
    private chifatailung.vista.componentes.IconoSvg icoUsuario;
    private chifatailung.vista.componentes.IconoSvg icoVentas;
    private javax.swing.JLabel lblClientesTendencia;
    private javax.swing.JLabel lblClientesTitulo;
    private javax.swing.JLabel lblClientesValor;
    private javax.swing.JLabel lblDemostracion;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDetalle0;
    private javax.swing.JLabel lblDetalle1;
    private javax.swing.JLabel lblDetalle2;
    private javax.swing.JLabel lblDetalle3;
    private javax.swing.JLabel lblDetalle4;
    private javax.swing.JLabel lblEnLinea;
    private javax.swing.JLabel lblEslogan;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHoraMovimiento0;
    private javax.swing.JLabel lblHoraMovimiento1;
    private javax.swing.JLabel lblHoraMovimiento2;
    private javax.swing.JLabel lblHoraMovimiento3;
    private javax.swing.JLabel lblHoraMovimiento4;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMesasPorcentaje;
    private javax.swing.JLabel lblMesasTitulo;
    private javax.swing.JLabel lblMesasValor;
    private javax.swing.JLabel lblMovimiento0;
    private javax.swing.JLabel lblMovimiento1;
    private javax.swing.JLabel lblMovimiento2;
    private javax.swing.JLabel lblMovimiento3;
    private javax.swing.JLabel lblMovimiento4;
    private javax.swing.JLabel lblNombreRestaurante;
    private javax.swing.JLabel lblPedidosTendencia;
    private javax.swing.JLabel lblPedidosTitulo;
    private javax.swing.JLabel lblPedidosValor;
    private javax.swing.JLabel lblSaludo;
    private javax.swing.JLabel lblTituloActividad;
    private javax.swing.JLabel lblTituloVentas;
    private javax.swing.JLabel lblUsuarioConectado;
    private javax.swing.JLabel lblVentasTendencia;
    private javax.swing.JLabel lblVentasTitulo;
    private javax.swing.JLabel lblVentasValor;
    private chifatailung.vista.componentes.PanelRedondeado pnlActividadReciente;
    private javax.swing.JPanel pnlCerrarSesion;
    private chifatailung.vista.componentes.PanelRedondeado pnlClientes;
    private javax.swing.JPanel pnlCuadricula;
    private javax.swing.JPanel pnlCuerpoLateral;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlEspacioInferior;
    private javax.swing.JPanel pnlFechaHora;
    private javax.swing.JPanel pnlIdentidad;
    private javax.swing.JPanel pnlInfoUsuario;
    private chifatailung.vista.componentes.PanelRedondeado pnlLateral;
    private javax.swing.JPanel pnlMarca;
    private chifatailung.vista.componentes.PanelRedondeado pnlMesas;
    private javax.swing.JPanel pnlMesasProgreso;
    private javax.swing.JPanel pnlMovimiento0;
    private javax.swing.JPanel pnlMovimiento1;
    private javax.swing.JPanel pnlMovimiento2;
    private javax.swing.JPanel pnlMovimiento3;
    private javax.swing.JPanel pnlMovimiento4;
    private javax.swing.JPanel pnlMovimientos;
    private javax.swing.JPanel pnlNavegacion;
    private chifatailung.vista.componentes.PanelRedondeado pnlPedidos;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlSaludo;
    private javax.swing.JPanel pnlTextoActividad0;
    private javax.swing.JPanel pnlTextoActividad1;
    private javax.swing.JPanel pnlTextoActividad2;
    private javax.swing.JPanel pnlTextoActividad3;
    private javax.swing.JPanel pnlTextoActividad4;
    private javax.swing.JPanel pnlTituloActividad;
    private javax.swing.JPanel pnlTituloVentas;
    private javax.swing.JPanel pnlUsuario;
    private chifatailung.vista.componentes.PanelRedondeado pnlVentas;
    private chifatailung.vista.componentes.PanelRedondeado pnlVentasSemana;
    private javax.swing.JProgressBar prgMesas;
    // End of variables declaration//GEN-END:variables
}
