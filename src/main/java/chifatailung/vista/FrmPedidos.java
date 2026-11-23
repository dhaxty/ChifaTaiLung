package chifatailung.vista;

import chifatailung.controlador.PedidoController;
import chifatailung.estructuras.arreglos.cArregloPlatos;
import chifatailung.estructuras.listas.cListaPedidos;
import chifatailung.modelo.DetallePedido;
import chifatailung.modelo.Pedido;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Insets;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmPedidos extends javax.swing.JFrame {
    
    private Usuario oUsuario;
    private PedidoController controlador;
    private cArregloPlatos oCarta;
    private DefaultTableModel modeloDetalles;
    private DefaultTableModel modeloPedidos;
    private String idPedidoSeleccionado;

    public FrmPedidos() {
        this(new Usuario("admin", "", Rol.ADMIN));
    }

    public FrmPedidos(Rol rol) {
        this(new Usuario((rol == Rol.ADMIN) ? "admin" : "empleado", "", (rol != null) ? rol : Rol.ADMIN));
    }

    public FrmPedidos(Usuario oUsuario) {
        this(oUsuario, (cListaPedidos) null);
    }

    public FrmPedidos(Usuario oUsuario, cListaPedidos oLista) {
        this.oUsuario = (oUsuario != null) ? oUsuario : new Usuario("admin", "", Rol.ADMIN);
        Rol rolSesion = (this.oUsuario.getRol() != null) ? this.oUsuario.getRol() : Rol.ADMIN;
        this.controlador = new PedidoController(rolSesion, oLista);
        this.oCarta = new cArregloPlatos(50);
        this.idPedidoSeleccionado = null;

        initComponents();

        inicializarCartaPlatos();

        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/logo140px.png")).getImage());
        getRootPane().putClientProperty("FlatLaf.fullWindowContent", true);

        JButton[] botonesMenu = {
            btnCarta, btnPedidos, btnAtencion, btnComprobantes, btnBuscar, btnMesas
        };
        for (JButton btn : botonesMenu) {
            btn.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
            btn.setMargin(new Insets(0, 14, 0, 14));
        }

        btnPedidos.setBackground(new Color(182, 34, 38));
        btnPedidos.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 1; borderColor: #F3D27A; focusWidth: 0");

        btnCarta.setIcon(iconoMenu("carta.svg"));
        btnPedidos.setIcon(iconoMenu("pedidos.svg"));
        btnAtencion.setIcon(iconoMenu("clientes.svg"));
        btnComprobantes.setIcon(iconoMenu("comprobantes.svg"));
        btnBuscar.setIcon(iconoMenu("buscar.svg"));
        btnMesas.setIcon(iconoMenu("mesas.svg"));

        pnlBanner.putClientProperty("FlatLaf.style", "arc: 22");

        lblRolBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblRolBadge.putClientProperty("FlatLaf.style", "arc: 999");

        pnlDatosPedido.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlDetallePedido.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlPedidos.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");

        jPanel1.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");
        jPanel5.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");
        jPanel6.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");

        txtNumPedido.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");
        txtCliente.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");
        cmbMesa.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");
        cmbPlato.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");
        spnCantidad.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");
        txtPrecioUnit.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");

        jButton1.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 0; focusWidth: 0");
        jButton2.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        jButton3.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnRegistrar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 0; focusWidth: 0");
        btnActualizar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnLimpiar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnEliminarPedido.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 0; focusWidth: 0");
        btnCargarPrueba.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");

        jScrollPane1.putClientProperty("FlatLaf.style", "arc: 12; border: 1,1,1,1,#D8C3A0");
        jScrollPane1.getViewport().setBackground(new Color(255, 253, 248));
        jScrollPane2.putClientProperty("FlatLaf.style", "arc: 12; border: 1,1,1,1,#D8C3A0");
        jScrollPane2.getViewport().setBackground(new Color(255, 253, 248));

        btnCerrarSesion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        btnVolver.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");

        configurarEstilosTablas();
        actualizarSesionUI();
        configurarTablas();
        cargarComboMesas();
        cargarComboPlatos();

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));

        conectarEventos();
        limpiarFormulario();
        refrescarTablaPedidos();
    }

    private FlatSVGIcon iconoMenu(String nombreArchivo) {
        FlatSVGIcon icono = new FlatSVGIcon(nombreArchivo, 20, 20);
        icono.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));
        return icono;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLateral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sepLogo = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        btnCarta = new javax.swing.JButton();
        btnPedidos = new javax.swing.JButton();
        btnAtencion = new javax.swing.JButton();
        btnComprobantes = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnMesas = new javax.swing.JButton();
        lblSesionTitulo = new javax.swing.JLabel();
        lblUsuarioActivo = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        pnlContenido = new javax.swing.JPanel();
        pnlBanner = new javax.swing.JPanel();
        lblRolBadge = new javax.swing.JLabel();
        lblTituloBanner = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        pnlCuerpo = new javax.swing.JPanel();
        pnlPedidos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalPedidos = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnEliminarPedido = new javax.swing.JButton();
        btnCargarPrueba = new javax.swing.JButton();
        pnlIzquierda = new javax.swing.JPanel();
        pnlDetallePedido = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmbPlato = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        txtPrecioUnit = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblContadorDetalles = new javax.swing.JLabel();
        pnlDatosPedido = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbMesa = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 760));
        setResizable(false);

        pnlLateral.setBackground(new java.awt.Color(140, 19, 22));
        pnlLateral.setMinimumSize(new java.awt.Dimension(280, 720));
        pnlLateral.setPreferredSize(new java.awt.Dimension(280, 720));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo140px.png"))); // NOI18N

        sepLogo.setForeground(new java.awt.Color(198, 146, 52));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(220, 185, 120));
        jLabel2.setText("MÓDULOS");

        btnCarta.setBackground(new java.awt.Color(158, 25, 29));
        btnCarta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCarta.setForeground(new java.awt.Color(247, 231, 194));
        btnCarta.setText("Carta de platos");
        btnCarta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCarta.setIconTextGap(14);

        btnPedidos.setBackground(new java.awt.Color(158, 25, 29));
        btnPedidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPedidos.setForeground(new java.awt.Color(247, 231, 194));
        btnPedidos.setText("Pedidos");
        btnPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPedidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPedidos.setIconTextGap(14);

        btnAtencion.setBackground(new java.awt.Color(158, 25, 29));
        btnAtencion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAtencion.setForeground(new java.awt.Color(247, 231, 194));
        btnAtencion.setText("Atención de clientes");
        btnAtencion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtencion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAtencion.setIconTextGap(14);

        btnComprobantes.setBackground(new java.awt.Color(158, 25, 29));
        btnComprobantes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnComprobantes.setForeground(new java.awt.Color(247, 231, 194));
        btnComprobantes.setText("Comprobantes");
        btnComprobantes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComprobantes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnComprobantes.setIconTextGap(14);

        btnBuscar.setBackground(new java.awt.Color(158, 25, 29));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(247, 231, 194));
        btnBuscar.setText("Buscar platos");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setIconTextGap(14);

        btnMesas.setBackground(new java.awt.Color(158, 25, 29));
        btnMesas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMesas.setForeground(new java.awt.Color(247, 231, 194));
        btnMesas.setText("Mesas");
        btnMesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMesas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMesas.setIconTextGap(14);

        lblSesionTitulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSesionTitulo.setForeground(new java.awt.Color(233, 201, 143));
        lblSesionTitulo.setText("Sesión iniciada como");

        lblUsuarioActivo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuarioActivo.setForeground(new java.awt.Color(247, 231, 194));
        lblUsuarioActivo.setText("admin");

        btnCerrarSesion.setBackground(new java.awt.Color(243, 210, 122));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(74, 10, 12));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlLateralLayout = new javax.swing.GroupLayout(pnlLateral);
        pnlLateral.setLayout(pnlLateralLayout);
        pnlLateralLayout.setHorizontalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLateralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(sepLogo)
                    .addComponent(btnCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtencion, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(btnComprobantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuarioActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSesionTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        pnlLateralLayout.setVerticalGroup(
            pnlLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLateralLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(sepLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(lblSesionTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuarioActivo)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(pnlLateral, java.awt.BorderLayout.WEST);

        pnlContenido.setBackground(new java.awt.Color(247, 238, 221));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(42, 28, 28, 28));
        pnlContenido.setLayout(new java.awt.BorderLayout(0, 22));

        pnlBanner.setBackground(new java.awt.Color(140, 19, 22));
        pnlBanner.setPreferredSize(new java.awt.Dimension(814, 185));

        lblRolBadge.setBackground(new java.awt.Color(235, 196, 110));
        lblRolBadge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRolBadge.setForeground(new java.awt.Color(60, 20, 10));
        lblRolBadge.setText("Administrador");
        lblRolBadge.setOpaque(true);

        lblTituloBanner.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTituloBanner.setForeground(new java.awt.Color(255, 244, 214));
        lblTituloBanner.setText("Gestión de pedidos");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 231, 194));
        jLabel3.setText("Registro de pedidos con hasta 10 detalles cada uno (lista enlazada).");

        btnVolver.setBackground(new java.awt.Color(243, 210, 122));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(74, 10, 12));
        btnVolver.setText("Volver al menú");

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBannerLayout.createSequentialGroup()
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRolBadge)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBannerLayout.createSequentialGroup()
                        .addComponent(lblTituloBanner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblRolBadge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloBanner)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pnlContenido.add(pnlBanner, java.awt.BorderLayout.NORTH);

        pnlCuerpo.setOpaque(false);
        pnlCuerpo.setLayout(new java.awt.BorderLayout(16, 0));

        pnlPedidos.setBackground(new java.awt.Color(255, 253, 248));
        pnlPedidos.setPreferredSize(new java.awt.Dimension(430, 100));
        pnlPedidos.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(234, 217, 188));
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 36));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(139, 18, 18));
        jLabel6.setText("Pedidos registrados");
        jLabel6.setMaximumSize(new java.awt.Dimension(115, 20));
        jLabel6.setMinimumSize(new java.awt.Dimension(115, 20));
        jLabel6.setPreferredSize(new java.awt.Dimension(115, 20));

        lblTotalPedidos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalPedidos.setText("Total: 0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(lblTotalPedidos)
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalPedidos))
                .addContainerGap())
        );

        pnlPedidos.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel7.setBackground(new java.awt.Color(255, 253, 248));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N.º", "Cliente", "Mesa", "Det.", "Total S/"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPedidos);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(139, 18, 18));
        jLabel15.setText("Total general");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(139, 18, 18));
        jLabel16.setText("S/ 0.00");

        btnEliminarPedido.setBackground(new java.awt.Color(139, 18, 18));
        btnEliminarPedido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarPedido.setForeground(new java.awt.Color(255, 243, 214));
        btnEliminarPedido.setText("Eliminar pedido");

        btnCargarPrueba.setBackground(new java.awt.Color(229, 215, 188));
        btnCargarPrueba.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCargarPrueba.setText("Cargar datos de prueba");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(35, 35, 35)
                .addComponent(jLabel16)
                .addGap(48, 48, 48))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addComponent(btnEliminarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(btnEliminarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCargarPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pnlPedidos.add(jPanel7, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlPedidos, java.awt.BorderLayout.EAST);

        pnlIzquierda.setOpaque(false);
        pnlIzquierda.setLayout(new java.awt.BorderLayout(0, 12));

        pnlDetallePedido.setBackground(new java.awt.Color(255, 253, 248));
        pnlDetallePedido.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 253, 248));

        jLabel10.setText("Plato de la carta");

        cmbPlato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Cant.");

        jLabel12.setText("Precio S/");

        txtPrecioUnit.setEditable(false);
        txtPrecioUnit.setBackground(new java.awt.Color(239, 229, 209));
        txtPrecioUnit.setText("0.00");

        jButton1.setBackground(new java.awt.Color(139, 18, 18));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 243, 214));
        jButton1.setText("Agregar detalle");

        jButton2.setBackground(new java.awt.Color(229, 215, 188));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(90, 59, 34));
        jButton2.setText("Editar detalle");

        jButton3.setBackground(new java.awt.Color(229, 215, 188));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(90, 59, 34));
        jButton3.setText("Quitar detalle");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Plato", "Cant.", "P. unit (S/)", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(139, 18, 18));
        jLabel13.setText("Total del pedido:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(139, 18, 18));
        jLabel14.setText("S/ 0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(cmbPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12))))
                                .addGap(0, 43, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPlato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnlDetallePedido.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(234, 217, 188));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 36));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(139, 18, 18));
        jLabel5.setText("Detalle del pedido");
        jLabel5.setPreferredSize(new java.awt.Dimension(115, 20));

        lblContadorDetalles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContadorDetalles.setText("Detalles: 0 / 10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(lblContadorDetalles)
                .addGap(28, 28, 28))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContadorDetalles))
                .addContainerGap())
        );

        pnlDetallePedido.add(jPanel5, java.awt.BorderLayout.NORTH);

        pnlIzquierda.add(pnlDetallePedido, java.awt.BorderLayout.CENTER);

        pnlDatosPedido.setBackground(new java.awt.Color(255, 253, 248));
        pnlDatosPedido.setPreferredSize(new java.awt.Dimension(100, 118));
        pnlDatosPedido.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(234, 217, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 36));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setText("Datos del pedido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDatosPedido.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel4.setBackground(new java.awt.Color(255, 253, 248));

        jLabel7.setText("N.º pedido");

        txtNumPedido.setEditable(false);
        txtNumPedido.setBackground(new java.awt.Color(239, 229, 209));
        txtNumPedido.setText("001");

        jLabel8.setText("Cliente");

        jLabel9.setText("Mesa (1–16)");

        cmbMesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cmbMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtCliente)
                    .addComponent(cmbMesa))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlDatosPedido.add(jPanel4, java.awt.BorderLayout.CENTER);

        pnlIzquierda.add(pnlDatosPedido, java.awt.BorderLayout.NORTH);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 44));

        btnRegistrar.setBackground(new java.awt.Color(139, 18, 18));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 243, 214));
        btnRegistrar.setText("Registrar pedido");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(235, 196, 110));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(90, 59, 34));
        btnActualizar.setText("Actualizar pedido");

        btnLimpiar.setBackground(new java.awt.Color(229, 215, 188));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(90, 59, 34));
        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlIzquierda.add(jPanel3, java.awt.BorderLayout.SOUTH);

        pnlCuerpo.add(pnlIzquierda, java.awt.BorderLayout.CENTER);

        pnlContenido.add(pnlCuerpo, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1296, 774));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrarPedido();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void actualizarSesionUI() {
        if (oUsuario != null) {
            lblUsuarioActivo.setText(oUsuario.getUsuario());
            if (oUsuario.getRol() == Rol.ADMIN) {
                lblRolBadge.setText("Administrador");
            } else {
                lblRolBadge.setText("Empleado");
            }
        }
    }

    private void configurarEstilosTablas() {
        jTable1.getTableHeader().setBackground(new Color(139, 18, 18));
        jTable1.getTableHeader().setForeground(new Color(255, 253, 248));
        jTable1.getTableHeader().putClientProperty("FlatLaf.style", "background: #8B1212; foreground: #FFFDF8");
        jTable1.putClientProperty("FlatLaf.style", "background: #FFFDF8; alternateRowColor: #FAF0DE");

        tblPedidos.getTableHeader().setBackground(new Color(139, 18, 18));
        tblPedidos.getTableHeader().setForeground(new Color(255, 253, 248));
        tblPedidos.getTableHeader().putClientProperty("FlatLaf.style", "background: #8B1212; foreground: #FFFDF8");
        tblPedidos.putClientProperty("FlatLaf.style", "background: #FFFDF8; alternateRowColor: #FAF0DE");
    }

    private void configurarTablas() {
        modeloDetalles = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Plato", "Cant.", "P. unit. (S/)", "Subtotal" }
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean editable = false;
                return editable;
            }
        };
        jTable1.setModel(modeloDetalles);

        modeloPedidos = new DefaultTableModel(
            new Object[][] {},
            new String[] { "N.º", "Cliente", "Mesa", "Det.", "Total S/" }
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean editable = false;
                return editable;
            }
        };
        tblPedidos.setModel(modeloPedidos);
    }

    private void cargarComboMesas() {
        cmbMesa.removeAllItems();
        for (int i = 1; i <= 16; i++) {
            cmbMesa.addItem(String.format("M%02d", i));
        }
        if (cmbMesa.getItemCount() > 0) {
            cmbMesa.setSelectedIndex(0);
        }
    }

    private void inicializarCartaPlatos() {
        oCarta.arregloVacio();
        oCarta.registrar("Arroz Chaufa de Pollo", "Chaufas", 18.00, true);
        oCarta.registrar("Arroz Chaufa Especial", "Chaufas", 24.00, true);
        oCarta.registrar("Aeropuerto de Pollo", "Combinados", 22.00, true);
        oCarta.registrar("Tallarín Saltado de Carne", "Tallarines", 22.00, true);
        oCarta.registrar("Tallarín Taypa", "Tallarines", 28.00, true);
        oCarta.registrar("Sopa Wantán Especial", "Sopas", 16.00, true);
        oCarta.registrar("Sopa Fuchifú", "Sopas", 15.00, true);
        oCarta.registrar("Wantán Frito (6 unid.)", "Entradas", 12.00, true);
        oCarta.registrar("Pollo ChiJauKay", "Especialidades", 25.00, true);
        oCarta.registrar("Pollo TiPaKay", "Especialidades", 25.00, true);
        oCarta.registrar("Kam Lu Wantán", "Especialidades", 28.00, true);
        oCarta.registrar("Chicha Morada 1L", "Bebidas", 10.00, true);
    }

    private void cargarComboPlatos() {
        cmbPlato.removeAllItems();
        Plato[] lista = oCarta.listar();
        for (int i = 0; i < lista.length; i++) {
            Plato p = lista[i];
            if (p != null) {
                cmbPlato.addItem(p.getCodigo() + " · " + p.getNombre());
            }
        }
        if (cmbPlato.getItemCount() > 0) {
            cmbPlato.setSelectedIndex(0);
            actualizarPrecioPlatoSeleccionado();
        }
    }

    private void actualizarPrecioPlatoSeleccionado() {
        String item = (String) cmbPlato.getSelectedItem();
        if (item != null && item.contains("·")) {
            String codigo = item.substring(0, item.indexOf("·")).trim();
            Plato plato = oCarta.buscar(codigo);
            if (plato != null) {
                txtPrecioUnit.setText(String.format(Locale.US, "%.2f", plato.getPrecio()));
            }
        }
    }

    private void conectarEventos() {
        cmbPlato.addActionListener(e -> actualizarPrecioPlatoSeleccionado());

        jButton1.addActionListener(e -> agregarDetalle());
        jButton2.addActionListener(e -> editarDetalle());
        jButton3.addActionListener(e -> quitarDetalle());

        btnActualizar.addActionListener(e -> actualizarPedido());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        btnEliminarPedido.addActionListener(e -> eliminarPedido());
        btnCargarPrueba.addActionListener(e -> cargarDatosPrueba());

        btnVolver.addActionListener(e -> volverAlMenu());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        tblPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPedidoSeleccionado();
            }
        });
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarPedidoSeleccionado();
            }
        });

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDetalleSeleccionado();
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarDetalleSeleccionado();
            }
        });

        btnCarta.addActionListener(e -> {
            new FrmPlatos(oUsuario).setVisible(true);
            dispose();
        });
        btnPedidos.addActionListener(e -> {
        });
        btnAtencion.addActionListener(e -> {
            new FrmAtencion(oUsuario).setVisible(true);
            dispose();
        });
        btnComprobantes.addActionListener(e -> {
            new FrmComprobantes(oUsuario).setVisible(true);
            dispose();
        });
        btnBuscar.addActionListener(e -> {
            new FrmArbol(oUsuario).setVisible(true);
            dispose();
        });
        btnMesas.addActionListener(e -> {
            new FrmMesas(oUsuario).setVisible(true);
            dispose();
        });
    }











    private void cargarPedidoSeleccionado() {
        int fila = tblPedidos.getSelectedRow();
        if (fila >= 0 && fila < tblPedidos.getRowCount()) {
            Object valNum = tblPedidos.getValueAt(fila, 0);
            if (valNum != null) {
                String num = valNum.toString().trim();
                Pedido p = controlador.buscar(num);
                if (p != null) {
                    idPedidoSeleccionado = p.getNumero();
                    txtNumPedido.setText(p.getNumero());
                    txtCliente.setText(p.getNombreCliente());
                    seleccionarMesaEnCombo(p.getNumeroMesa());

                    modeloDetalles.setRowCount(0);
                    DetallePedido[] dets = p.listarDetalles();
                    if (dets != null) {
                        for (int i = 0; i < dets.length; i++) {
                            DetallePedido d = dets[i];
                            if (d != null) {
                                modeloDetalles.addRow(new Object[] {
                                    d.getCodigoPlato(),
                                    d.getNombrePlato(),
                                    d.getCantidad(),
                                    String.format(Locale.US, "%.2f", d.getPrecioUnitario()),
                                    String.format(Locale.US, "%.2f", d.calcularSubtotal())
                                });
                            }
                        }
                    }
                    actualizarTotalesDetalles();
                }
            }
        }
    }

    private void cargarDetalleSeleccionado() {
        int fila = jTable1.getSelectedRow();
        if (fila >= 0 && fila < jTable1.getRowCount()) {
            Object codObj = jTable1.getValueAt(fila, 0);
            Object cantObj = jTable1.getValueAt(fila, 2);
            Object precObj = jTable1.getValueAt(fila, 3);
            if (codObj != null) {
                seleccionarPlatoEnCombo(codObj.toString());
            }
            if (cantObj != null) {
                try {
                    spnCantidad.setValue(Integer.parseInt(cantObj.toString().trim()));
                } catch (NumberFormatException ignored) {
                }
            }
            if (precObj != null) {
                txtPrecioUnit.setText(precObj.toString().trim());
            }
        }
    }

    private void seleccionarPlatoEnCombo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            String codBuscado = codigo.trim().toLowerCase(Locale.ROOT);
            for (int i = 0; i < cmbPlato.getItemCount(); i++) {
                String item = cmbPlato.getItemAt(i);
                if (item != null && item.toLowerCase(Locale.ROOT).startsWith(codBuscado)) {
                    cmbPlato.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void seleccionarMesaEnCombo(int numero) {
        String codigo = String.format("M%02d", numero);
        for (int i = 0; i < cmbMesa.getItemCount(); i++) {
            String item = cmbMesa.getItemAt(i);
            if (item != null && item.equalsIgnoreCase(codigo)) {
                cmbMesa.setSelectedIndex(i);
                break;
            }
        }
    }

    private void agregarDetalle() {
        String item = (String) cmbPlato.getSelectedItem();
        if (item == null || !item.contains("·")) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un plato válido de la carta.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String codigo = item.substring(0, item.indexOf("·")).trim();
            String nombre = item.substring(item.indexOf("·") + 1).trim();
            int cant = ((Number) spnCantidad.getValue()).intValue();
            double precio = 0.0;
            try {
                precio = Double.parseDouble(txtPrecioUnit.getText().trim());
            } catch (NumberFormatException ex) {
                Plato p = oCarta.buscar(codigo);
                if (p != null) {
                    precio = p.getPrecio();
                }
            }

            if (cant < 1) {
                JOptionPane.showMessageDialog(this,
                    "La cantidad debe ser mayor o igual a 1.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                int filaExistente = -1;
                for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                    Object valCod = modeloDetalles.getValueAt(i, 0);
                    if (valCod != null && valCod.toString().equalsIgnoreCase(codigo)) {
                        filaExistente = i;
                        break;
                    }
                }

                if (filaExistente != -1) {
                    int cantActual = Integer.parseInt(modeloDetalles.getValueAt(filaExistente, 2).toString().trim());
                    int nuevaCant = cantActual + cant;
                    double nuevoSubtotal = nuevaCant * precio;
                    modeloDetalles.setValueAt(nuevaCant, filaExistente, 2);
                    modeloDetalles.setValueAt(String.format(Locale.US, "%.2f", nuevoSubtotal), filaExistente, 4);
                    actualizarTotalesDetalles();
                } else {
                    if (modeloDetalles.getRowCount() >= 10) {
                        JOptionPane.showMessageDialog(this,
                            "El pedido no puede superar el límite máximo de 10 detalles.",
                            "Límite alcanzado",
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        double subtotal = cant * precio;
                        modeloDetalles.addRow(new Object[] {
                            codigo,
                            nombre,
                            cant,
                            String.format(Locale.US, "%.2f", precio),
                            String.format(Locale.US, "%.2f", subtotal)
                        });
                        actualizarTotalesDetalles();
                    }
                }
                spnCantidad.setValue(1);
            }
        }
    }

    private void editarDetalle() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0 || fila >= modeloDetalles.getRowCount()) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un detalle de la tabla para editarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String item = (String) cmbPlato.getSelectedItem();
            if (item == null || !item.contains("·")) {
                JOptionPane.showMessageDialog(this,
                    "Selecciona un plato válido de la carta.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                String codigo = item.substring(0, item.indexOf("·")).trim();
                String nombre = item.substring(item.indexOf("·") + 1).trim();
                int cant = ((Number) spnCantidad.getValue()).intValue();
                double precio = 0.0;
                try {
                    precio = Double.parseDouble(txtPrecioUnit.getText().trim());
                } catch (NumberFormatException ex) {
                    Plato p = oCarta.buscar(codigo);
                    if (p != null) {
                        precio = p.getPrecio();
                    }
                }

                if (cant < 1) {
                    JOptionPane.showMessageDialog(this,
                        "La cantidad debe ser mayor o igual a 1.",
                        "Validación de datos",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    int otraFila = -1;
                    for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                        if (i != fila) {
                            Object valCod = modeloDetalles.getValueAt(i, 0);
                            if (valCod != null && valCod.toString().equalsIgnoreCase(codigo)) {
                                otraFila = i;
                                break;
                            }
                        }
                    }

                    if (otraFila != -1) {
                        int cantExistente = Integer.parseInt(modeloDetalles.getValueAt(otraFila, 2).toString().trim());
                        int nuevaCant = cantExistente + cant;
                        double nuevoSubtotal = nuevaCant * precio;
                        modeloDetalles.setValueAt(nuevaCant, otraFila, 2);
                        modeloDetalles.setValueAt(String.format(Locale.US, "%.2f", nuevoSubtotal), otraFila, 4);
                        modeloDetalles.removeRow(fila);
                    } else {
                        double subtotal = cant * precio;
                        modeloDetalles.setValueAt(codigo, fila, 0);
                        modeloDetalles.setValueAt(nombre, fila, 1);
                        modeloDetalles.setValueAt(cant, fila, 2);
                        modeloDetalles.setValueAt(String.format(Locale.US, "%.2f", precio), fila, 3);
                        modeloDetalles.setValueAt(String.format(Locale.US, "%.2f", subtotal), fila, 4);
                    }
                    actualizarTotalesDetalles();
                }
            }
        }
    }

    private void quitarDetalle() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0 || fila >= modeloDetalles.getRowCount()) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un detalle de la tabla para quitarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            modeloDetalles.removeRow(fila);
            actualizarTotalesDetalles();
            jTable1.clearSelection();
        }
    }

    private void actualizarTotalesDetalles() {
        int totalItems = modeloDetalles.getRowCount();
        lblContadorDetalles.setText("Detalles: " + totalItems + " / 10");
        double totalPedido = 0.0;
        for (int i = 0; i < totalItems; i++) {
            Object valSub = modeloDetalles.getValueAt(i, 4);
            if (valSub != null) {
                try {
                    totalPedido += Double.parseDouble(valSub.toString().trim());
                } catch (NumberFormatException ignored) {
                }
            }
        }
        jLabel14.setText(String.format(Locale.US, "S/ %.2f", totalPedido));
    }

    private void registrarPedido() {
        String cliente = txtCliente.getText().trim();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El nombre del cliente es obligatorio.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else if (modeloDetalles.getRowCount() < 1) {
            JOptionPane.showMessageDialog(this,
                "El pedido debe contener al menos 1 detalle.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String numPedido = txtNumPedido.getText().trim();
            if (numPedido.isEmpty()) {
                numPedido = calcularSiguienteNumeroPedido();
            }
            int numMesa = obtenerNumeroMesaSeleccionada();

            Pedido nuevoPedido = new Pedido(numPedido, cliente, numMesa);
            for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                String cod = modeloDetalles.getValueAt(i, 0).toString();
                String nom = modeloDetalles.getValueAt(i, 1).toString();
                int cant = Integer.parseInt(modeloDetalles.getValueAt(i, 2).toString());
                double prec = Double.parseDouble(modeloDetalles.getValueAt(i, 3).toString());
                nuevoPedido.agregarDetalle(new DetallePedido(cod, nom, cant, prec));
            }

            String err = controlador.registrar(nuevoPedido);
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    err,
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                limpiarFormulario();
                refrescarTablaPedidos();
            }
        }
    }

    private void actualizarPedido() {
        if (idPedidoSeleccionado == null || idPedidoSeleccionado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un pedido registrado de la tabla para actualizarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String cliente = txtCliente.getText().trim();
            if (cliente.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "El nombre del cliente es obligatorio.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else if (modeloDetalles.getRowCount() < 1) {
                JOptionPane.showMessageDialog(this,
                    "El pedido debe contener al menos 1 detalle.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                int numMesa = obtenerNumeroMesaSeleccionada();
                Pedido datosNuevos = new Pedido(idPedidoSeleccionado, cliente, numMesa);
                for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                    String cod = modeloDetalles.getValueAt(i, 0).toString();
                    String nom = modeloDetalles.getValueAt(i, 1).toString();
                    int cant = Integer.parseInt(modeloDetalles.getValueAt(i, 2).toString());
                    double prec = Double.parseDouble(modeloDetalles.getValueAt(i, 3).toString());
                    datosNuevos.agregarDetalle(new DetallePedido(cod, nom, cant, prec));
                }

                String err = controlador.actualizar(idPedidoSeleccionado, datosNuevos);
                if (!err.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        err,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    refrescarTablaPedidos();
                    seleccionarFilaPedido(idPedidoSeleccionado);
                }
            }
        }
    }

    private void limpiarFormulario() {
        idPedidoSeleccionado = null;
        txtCliente.setText("");
        if (cmbMesa.getItemCount() > 0) {
            cmbMesa.setSelectedIndex(0);
        }
        if (cmbPlato.getItemCount() > 0) {
            cmbPlato.setSelectedIndex(0);
            actualizarPrecioPlatoSeleccionado();
        }
        spnCantidad.setValue(1);
        modeloDetalles.setRowCount(0);
        lblContadorDetalles.setText("Detalles: 0 / 10");
        jLabel14.setText("S/ 0.00");
        tblPedidos.clearSelection();
        txtNumPedido.setText(calcularSiguienteNumeroPedido());
    }

    private void eliminarPedido() {
        if (idPedidoSeleccionado == null || idPedidoSeleccionado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Selecciona un pedido registrado de la tabla para eliminarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar el pedido N.º " + idPedidoSeleccionado + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                String err = controlador.eliminar(idPedidoSeleccionado);
                if (!err.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        err,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    limpiarFormulario();
                    refrescarTablaPedidos();
                }
            }
        }
    }

    private void cargarDatosPrueba() {
        controlador.reiniciarPedidos();

        Pedido p1 = new Pedido("001", "Carlos Ramos", 2);
        p1.agregarDetalle(new DetallePedido("P002", "Arroz Chaufa Especial", 2, 24.00));
        p1.agregarDetalle(new DetallePedido("P008", "Wantán Frito (6 unid.)", 1, 12.00));
        p1.agregarDetalle(new DetallePedido("P012", "Chicha Morada 1L", 1, 10.00));
        controlador.registrar(p1);

        Pedido p2 = new Pedido("002", "María Torres", 7);
        p2.agregarDetalle(new DetallePedido("P009", "Pollo ChiJauKay", 1, 25.00));
        p2.agregarDetalle(new DetallePedido("P006", "Sopa Wantán Especial", 1, 16.00));
        controlador.registrar(p2);

        Pedido p3 = new Pedido("003", "Familia Quispe", 5);
        p3.agregarDetalle(new DetallePedido("P003", "Aeropuerto de Pollo", 2, 22.00));
        p3.agregarDetalle(new DetallePedido("P005", "Tallarín Taypa", 1, 28.00));
        p3.agregarDetalle(new DetallePedido("P010", "Pollo TiPaKay", 1, 25.00));
        controlador.registrar(p3);

        limpiarFormulario();
        refrescarTablaPedidos();
    }

    private void refrescarTablaPedidos() {
        modeloPedidos.setRowCount(0);
        Pedido[] pedidos = controlador.listar();
        if (pedidos != null) {
            for (int i = 0; i < pedidos.length; i++) {
                Pedido p = pedidos[i];
                if (p != null) {
                    modeloPedidos.addRow(new Object[] {
                        p.getNumero(),
                        p.getNombreCliente(),
                        String.format("M%02d", p.getNumeroMesa()),
                        p.getCantidadDetalles(),
                        String.format(Locale.US, "S/ %.2f", p.calcularTotal())
                    });
                }
            }
        }
        lblTotalPedidos.setText("Total: " + controlador.getCantidad());
        jLabel16.setText(String.format(Locale.US, "S/ %.2f", controlador.totalGeneral()));
        txtNumPedido.setText(calcularSiguienteNumeroPedido());
    }

    private String calcularSiguienteNumeroPedido() {
        int maxNumero = 0;
        if (controlador != null) {
            Pedido[] lista = controlador.listar();
            if (lista != null) {
                for (int i = 0; i < lista.length; i++) {
                    Pedido p = lista[i];
                    if (p != null && p.getNumero() != null) {
                        try {
                            String digitos = p.getNumero().replaceAll("\\D+", "");
                            if (!digitos.isEmpty()) {
                                int val = Integer.parseInt(digitos);
                                if (val > maxNumero) {
                                    maxNumero = val;
                                }
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
            }
        }
        String resultado = String.format("%03d", maxNumero + 1);
        return resultado;
    }

    private int obtenerNumeroMesaSeleccionada() {
        int numero = 1;
        String item = (String) cmbMesa.getSelectedItem();
        if (item != null) {
            try {
                String digitos = item.replaceAll("\\D+", "");
                if (!digitos.isEmpty()) {
                    numero = Integer.parseInt(digitos);
                }
            } catch (NumberFormatException ignored) {
            }
        }
        return numero;
    }

    private void seleccionarFilaPedido(String numero) {
        if (numero != null && tblPedidos != null) {
            for (int i = 0; i < tblPedidos.getRowCount(); i++) {
                Object val = tblPedidos.getValueAt(i, 0);
                if (val != null && val.toString().equalsIgnoreCase(numero)) {
                    tblPedidos.setRowSelectionInterval(i, i);
                    tblPedidos.scrollRectToVisible(tblPedidos.getCellRect(i, 0, true));
                    break;
                }
            }
        }
    }

    private void volverAlMenu() {
        if (oUsuario != null) {
            new FrmPrincipal(oUsuario).setVisible(true);
        } else {
            new FrmPrincipal().setVisible(true);
        }
        dispose();
    }

    private void cerrarSesion() {
        new FrmLogin().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmPedidos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAtencion;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarPrueba;
    private javax.swing.JButton btnCarta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComprobantes;
    private javax.swing.JButton btnEliminarPedido;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbMesa;
    private javax.swing.JComboBox<String> cmbPlato;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblContadorDetalles;
    private javax.swing.JLabel lblRolBadge;
    private javax.swing.JLabel lblSesionTitulo;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblTotalPedidos;
    private javax.swing.JLabel lblUsuarioActivo;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDatosPedido;
    private javax.swing.JPanel pnlDetallePedido;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JSeparator sepLogo;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtPrecioUnit;
    // End of variables declaration//GEN-END:variables
}
