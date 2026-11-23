/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chifatailung.vista;

import chifatailung.controlador.MesaController;
import chifatailung.estructuras.matrices.cMatrizMesas;
import chifatailung.modelo.EstadoMesa;
import chifatailung.modelo.Mesa;
import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class FrmMesas extends javax.swing.JFrame {

    private Usuario oUsuario;
    private MesaController controlador;
    private int numeroMesaSeleccionada;
    private String[] clientesMesas;
    private JButton[][] botonesMesas;

    /**
     * Creates new form FrmMesas
     */
    public FrmMesas() {
        this(new Usuario("admin", "", Rol.ADMIN));
    }

    public FrmMesas(Rol rol) {
        this(new Usuario(rol == Rol.ADMIN ? "admin" : "empleado", "", (rol != null) ? rol : Rol.ADMIN));
    }

    public FrmMesas(Usuario oUsuario) {
        this(oUsuario, (cMatrizMesas) null);
    }

    public FrmMesas(Usuario oUsuario, cMatrizMesas oMatriz) {
        this.oUsuario = (oUsuario != null) ? oUsuario : new Usuario("admin", "", Rol.ADMIN);
        Rol rolSesion = (this.oUsuario.getRol() != null) ? this.oUsuario.getRol() : Rol.ADMIN;
        this.controlador = new MesaController(rolSesion, oMatriz);
        this.numeroMesaSeleccionada = 0;
        this.clientesMesas = new String[17];
        this.botonesMesas = new JButton[4][4];

        initComponents();

        // estilos visuales e iconos
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/logo140px.png")).getImage());
        getRootPane().putClientProperty("FlatLaf.fullWindowContent", true);

        // botones del menu lateral con esquinas redondeadas
        JButton[] botonesMenu = {
            btnCarta, btnPedidos, btnAtencion, btnComprobantes, btnBuscar, btnMesas
        };
        for (JButton btn : botonesMenu) {
            btn.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
            btn.setMargin(new Insets(0, 14, 0, 14));
        }

        // resaltar el modulo activo en el menu lateral
        btnMesas.setBackground(new Color(182, 34, 38));
        btnMesas.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 1; borderColor: #F3D27A; focusWidth: 0");

        // iconos SVG para los botones laterales
        btnCarta.setIcon(iconoMenu("carta.svg"));
        btnPedidos.setIcon(iconoMenu("pedidos.svg"));
        btnAtencion.setIcon(iconoMenu("clientes.svg"));
        btnComprobantes.setIcon(iconoMenu("comprobantes.svg"));
        btnBuscar.setIcon(iconoMenu("buscar.svg"));
        btnMesas.setIcon(iconoMenu("mesas.svg"));

        // redondeo del banner superior
        pnlBanner.putClientProperty("FlatLaf.style", "arc: 22");

        // estilo pastilla para el badge del rol
        lblRolBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblRolBadge.putClientProperty("FlatLaf.style", "arc: 999");

        // estilo pastilla para el badge de total de mesas
        lblTotalBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblTotalBadge.putClientProperty("FlatLaf.style", "arc: 999");

        // bordes redondeados en paneles contenedores
        pnlRegistro.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlPlatos.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlHeaderRegistro.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");
        pnlHeaderCola.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");

        // bordes redondeados en controles de entrada
        txtCliente.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0; focusWidth: 0");

        // bordes redondeados en botones de accion y focusWidth: 0
        btnOcupar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 0; focusWidth: 0");
        btnReservar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnLiberar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnCargarPrueba.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");

        // boton de cerrar sesion redondeado
        btnCerrarSesion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        btnVolver.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");

        // datos de sesion activa
        actualizarSesionUI();

        // construccion de la cuadricula dinamica de 16 mesas
        construirCuadricula();

        // conexion de eventos de botones
        conectarEventos();

        // sincronizacion inicial de datos con la vista y resumen
        actualizarSeleccionYResumen();
    }

    // icono blanco para los botones del menu lateral
    private FlatSVGIcon iconoMenu(String nombreArchivo) {
        FlatSVGIcon icono = new FlatSVGIcon(nombreArchivo, 20, 20);
        icono.setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.WHITE));
        return icono;
    }


    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
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
        pnlPlatos = new javax.swing.JPanel();
        pnlHeaderCola = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalBadge = new javax.swing.JLabel();
        pnlBodyCola = new javax.swing.JPanel();
        pnlMatrizMesas = new javax.swing.JPanel();
        pnlRegistro = new javax.swing.JPanel();
        pnlHeaderRegistro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlBodyRegistro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnOcupar = new javax.swing.JButton();
        lblMesaSeleccionada = new javax.swing.JLabel();
        btnReservar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnLiberar = new javax.swing.JButton();
        btnCargarPrueba = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        lblTituloBanner.setText("Gestión de mesas");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 231, 194));
        jLabel3.setText("Control de disponibilidad y estados de las 16 mesas (Matriz 4 × 4).");

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
                        .addContainerGap(365, Short.MAX_VALUE))
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
        pnlCuerpo.setLayout(new java.awt.BorderLayout(0, 18));

        pnlPlatos.setBackground(new java.awt.Color(255, 248, 238));
        pnlPlatos.setLayout(new java.awt.BorderLayout());

        pnlHeaderCola.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderCola.setPreferredSize(new java.awt.Dimension(100, 46));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(139, 18, 18));
        jLabel7.setText("Mapa del Salón (Matriz 4 × 4)");
        jLabel7.setPreferredSize(new java.awt.Dimension(166, 22));

        lblTotalBadge.setBackground(new java.awt.Color(255, 248, 238));
        lblTotalBadge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalBadge.setForeground(new java.awt.Color(90, 59, 34));
        lblTotalBadge.setText("Libres: 16 | Ocupadas: 0 | Reservadas: 0");
        lblTotalBadge.setOpaque(true);

        javax.swing.GroupLayout pnlHeaderColaLayout = new javax.swing.GroupLayout(pnlHeaderCola);
        pnlHeaderCola.setLayout(pnlHeaderColaLayout);
        pnlHeaderColaLayout.setHorizontalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                .addComponent(lblTotalBadge)
                .addGap(69, 69, 69))
        );
        pnlHeaderColaLayout.setVerticalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalBadge))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlPlatos.add(pnlHeaderCola, java.awt.BorderLayout.NORTH);

        pnlBodyCola.setBackground(new java.awt.Color(255, 248, 238));
        pnlBodyCola.setLayout(new java.awt.BorderLayout());

        pnlMatrizMesas.setBackground(new java.awt.Color(255, 253, 248));
        pnlMatrizMesas.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        pnlMatrizMesas.setLayout(new java.awt.GridLayout(4, 4, 12, 12));
        pnlBodyCola.add(pnlMatrizMesas, java.awt.BorderLayout.CENTER);

        pnlPlatos.add(pnlBodyCola, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlPlatos, java.awt.BorderLayout.CENTER);

        pnlRegistro.setBackground(new java.awt.Color(255, 248, 238));
        pnlRegistro.setPreferredSize(new java.awt.Dimension(100, 152));
        pnlRegistro.setLayout(new java.awt.BorderLayout());

        pnlHeaderRegistro.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderRegistro.setPreferredSize(new java.awt.Dimension(100, 44));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setText("Control de Mesa");

        javax.swing.GroupLayout pnlHeaderRegistroLayout = new javax.swing.GroupLayout(pnlHeaderRegistro);
        pnlHeaderRegistro.setLayout(pnlHeaderRegistroLayout);
        pnlHeaderRegistroLayout.setHorizontalGroup(
            pnlHeaderRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderRegistroLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(674, Short.MAX_VALUE))
        );
        pnlHeaderRegistroLayout.setVerticalGroup(
            pnlHeaderRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRegistro.add(pnlHeaderRegistro, java.awt.BorderLayout.NORTH);

        pnlBodyRegistro.setBackground(new java.awt.Color(255, 248, 238));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(90, 59, 34));
        jLabel5.setText("Mesa seleccionada:");

        txtCliente.setForeground(new java.awt.Color(59, 42, 32));

        btnOcupar.setBackground(new java.awt.Color(139, 18, 18));
        btnOcupar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOcupar.setForeground(new java.awt.Color(255, 243, 214));
        btnOcupar.setText("Ocupar mesa");

        lblMesaSeleccionada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMesaSeleccionada.setText("Ninguna — Haz clic en una mesa");

        btnReservar.setBackground(new java.awt.Color(243, 230, 189));
        btnReservar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReservar.setForeground(new java.awt.Color(90, 59, 34));
        btnReservar.setText("Reservar");

        jLabel6.setText("Cliente / Responsable (opcional)");

        btnLiberar.setBackground(new java.awt.Color(243, 230, 189));
        btnLiberar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLiberar.setForeground(new java.awt.Color(90, 59, 34));
        btnLiberar.setText("Liberar");

        btnCargarPrueba.setBackground(new java.awt.Color(255, 253, 248));
        btnCargarPrueba.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCargarPrueba.setForeground(new java.awt.Color(139, 18, 18));
        btnCargarPrueba.setText("Cargar datos de prueba");

        javax.swing.GroupLayout pnlBodyRegistroLayout = new javax.swing.GroupLayout(pnlBodyRegistro);
        pnlBodyRegistro.setLayout(pnlBodyRegistroLayout);
        pnlBodyRegistroLayout.setHorizontalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMesaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(223, 223, 223))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyRegistroLayout.createSequentialGroup()
                                .addComponent(btnOcupar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)))
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(btnLiberar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(btnCargarPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        pnlBodyRegistroLayout.setVerticalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMesaSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCargarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLiberar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOcupar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pnlRegistro.add(pnlBodyRegistro, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlRegistro, java.awt.BorderLayout.NORTH);

        pnlContenido.add(pnlCuerpo, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1166, 774));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // actualiza las etiquetas del usuario y rol activo en la cabecera
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

    // construye dinamicamente las 16 tarjetas de mesa en la cuadricula GridLayout 4x4
    private void construirCuadricula() {
        pnlMatrizMesas.removeAll();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Mesa mesa = (controlador != null) ? controlador.obtener(i, j) : null;
                int numero = (mesa != null) ? mesa.getNumero() : ((i * 4) + j + 1);
                JButton btn = new JButton();
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setMargin(new Insets(4, 4, 4, 4));
                btn.addActionListener(e -> seleccionarMesa(numero));
                botonesMesas[i][j] = btn;
                pnlMatrizMesas.add(btn);
            }
        }
    }

    // enlaza los eventos de accion a los botones del formulario y menu lateral
    private void conectarEventos() {
        btnOcupar.addActionListener(e -> ocuparMesa());
        btnReservar.addActionListener(e -> reservarMesa());
        btnLiberar.addActionListener(e -> liberarMesa());
        btnCargarPrueba.addActionListener(e -> cargarDatosPrueba());

        btnVolver.addActionListener(e -> volverAlMenu());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        btnCarta.addActionListener(e -> {
            new FrmPlatos(oUsuario).setVisible(true);
            dispose();
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
            // Modulo activo actualmente
        });
        btnPedidos.addActionListener(e -> {
            new FrmPedidos(oUsuario).setVisible(true);
            dispose();
        });
    }

    // selecciona una mesa, actualiza el detalle y refresca el resaltado
    private void seleccionarMesa(int numero) {
        this.numeroMesaSeleccionada = numero;
        Mesa mesa = (controlador != null) ? controlador.buscar(numero) : null;
        if (mesa != null) {
            String codigo = String.format("M%02d", mesa.getNumero());
            lblMesaSeleccionada.setText(codigo + " (Capacidad: " + mesa.getCapacidad() + " personas) — " + mesa.getEstado());
            String cliente = clientesMesas[numero];
            txtCliente.setText(cliente != null ? cliente : "");
        }
        refrescarMatriz();
    }

    // cambia el estado de la mesa seleccionada a OCUPADA
    private void ocuparMesa() {
        if (numeroMesaSeleccionada <= 0) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una mesa de la cuadrícula.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            Mesa mesa = (controlador != null) ? controlador.buscar(numeroMesaSeleccionada) : null;
            if (mesa != null) {
                if (mesa.getEstado() == EstadoMesa.OCUPADA) {
                    JOptionPane.showMessageDialog(this,
                        "La mesa " + String.format("M%02d", mesa.getNumero()) + " ya se encuentra ocupada.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String cliente = txtCliente.getText().trim();
                    if (!cliente.isEmpty()) {
                        clientesMesas[numeroMesaSeleccionada] = cliente;
                    }
                    String err = (controlador != null)
                        ? controlador.cambiarEstado(numeroMesaSeleccionada, EstadoMesa.OCUPADA)
                        : "Controlador no disponible.";
                    if (!err.isEmpty()) {
                        JOptionPane.showMessageDialog(this, err, "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        actualizarSeleccionYResumen();
                    }
                }
            }
        }
    }

    // cambia el estado de la mesa seleccionada a RESERVADA
    private void reservarMesa() {
        if (numeroMesaSeleccionada <= 0) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una mesa de la cuadrícula.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String cliente = txtCliente.getText().trim();
            if (!cliente.isEmpty()) {
                clientesMesas[numeroMesaSeleccionada] = cliente;
            }
            String err = (controlador != null)
                ? controlador.cambiarEstado(numeroMesaSeleccionada, EstadoMesa.RESERVADA)
                : "Controlador no disponible.";
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this, err, "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                actualizarSeleccionYResumen();
            }
        }
    }

    // cambia el estado de la mesa seleccionada a LIBRE y limpia el cliente
    private void liberarMesa() {
        if (numeroMesaSeleccionada <= 0) {
            JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una mesa de la cuadrícula.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            clientesMesas[numeroMesaSeleccionada] = null;
            txtCliente.setText("");
            String err = (controlador != null)
                ? controlador.cambiarEstado(numeroMesaSeleccionada, EstadoMesa.LIBRE)
                : "Controlador no disponible.";
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this, err, "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                actualizarSeleccionYResumen();
            }
        }
    }

    // carga una distribucion variada de prueba en las mesas del salon
    private void cargarDatosPrueba() {
        if (controlador != null) {
            String err = controlador.cargarDatosPrueba();
            if (!err.isEmpty()) {
                JOptionPane.showMessageDialog(this, err, "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                for (int i = 1; i <= 16; i++) {
                    clientesMesas[i] = null;
                }
                clientesMesas[2] = "Familia Wong";
                clientesMesas[3] = "Carlos Ramos";
                clientesMesas[5] = "Empresa BCP";
                clientesMesas[7] = "Lucía Méndez";
                clientesMesas[8] = "Jorge Choy";
                clientesMesas[11] = "Ana Chang";
                clientesMesas[14] = "Reunión Directiva";

                actualizarSeleccionYResumen();
            }
        }
    }

    // refresca el contenido visual y bordes de todas las tarjetas de la matriz
    private void refrescarMatriz() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Mesa mesa = (controlador != null) ? controlador.obtener(i, j) : null;
                JButton btn = botonesMesas[i][j];
                if (mesa != null && btn != null) {
                    int num = mesa.getNumero();
                    String cliente = clientesMesas[num];
                    boolean seleccionada = (num == numeroMesaSeleccionada);

                    btn.setText(generarHtmlMesa(mesa, cliente));
                    aplicarEstiloTarjeta(btn, mesa.getEstado(), seleccionada);
                }
            }
        }
        pnlMatrizMesas.revalidate();
        pnlMatrizMesas.repaint();
    }

    // genera el contenido HTML formateado y centrado para la tarjeta de cada mesa
    private String generarHtmlMesa(Mesa mesa, String cliente) {
        String codigo = String.format("M%02d", mesa.getNumero());
        String capacidad = "Cap. " + mesa.getCapacidad() + " pers.";
        String insigniaColor;
        String estadoTexto;

        if (mesa.getEstado() == EstadoMesa.LIBRE) {
            insigniaColor = "#5E8B5E";
            estadoTexto = "LIBRE";
        } else if (mesa.getEstado() == EstadoMesa.OCUPADA) {
            insigniaColor = "#C83E3E";
            estadoTexto = "OCUPADA";
        } else {
            insigniaColor = "#D99B26";
            estadoTexto = "RESERVADA";
        }

        String lineaCliente = "";
        if (mesa.getEstado() != EstadoMesa.LIBRE && cliente != null && !cliente.trim().isEmpty()) {
            String cliLimpio = cliente.trim();
            if (cliLimpio.length() > 16) {
                cliLimpio = cliLimpio.substring(0, 13) + "...";
            }
            lineaCliente = "<br><span style='font-size: 10px; color: #4A3225;'>" + cliLimpio + "</span>";
        }

        String html = "<html><center>"
                + "<b>" + codigo + "</b> &nbsp; "
                + "<span style='font-size: 10px; color: #554433;'>" + capacidad + "</span><br>"
                + "<span style='font-size: 11px; color: " + insigniaColor + ";'><b>● " + estadoTexto + "</b></span>"
                + lineaCliente
                + "</center></html>";

        return html;
    }

    // aplica el fondo y los bordes segun estado y resaltado de seleccion activa
    private void aplicarEstiloTarjeta(JButton btn, EstadoMesa estado, boolean seleccionada) {
        Color fondo;
        String borderColor;

        if (estado == EstadoMesa.LIBRE) {
            fondo = new Color(0xE6, 0xF2, 0xE6);
            borderColor = "#8DB88D";
        } else if (estado == EstadoMesa.OCUPADA) {
            fondo = new Color(0xFB, 0xE8, 0xE8);
            borderColor = "#C85A5A";
        } else {
            fondo = new Color(0xFD, 0xF3, 0xDC);
            borderColor = "#E0B458";
        }

        btn.setBackground(fondo);
        if (seleccionada) {
            btn.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 2; borderColor: #8B1212; focusWidth: 0");
        } else {
            btn.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: " + borderColor + "; focusWidth: 0");
        }
    }

    // actualiza la seleccion de mesa, los campos y el resumen superior de conteos
    private void actualizarSeleccionYResumen() {
        refrescarMatriz();
        if (numeroMesaSeleccionada > 0 && controlador != null) {
            Mesa mesa = controlador.buscar(numeroMesaSeleccionada);
            if (mesa != null) {
                String codigo = String.format("M%02d", mesa.getNumero());
                lblMesaSeleccionada.setText(codigo + " (Capacidad: " + mesa.getCapacidad() + " personas) — " + mesa.getEstado());
                String cli = clientesMesas[numeroMesaSeleccionada];
                txtCliente.setText(cli != null ? cli : "");
            }
        } else {
            lblMesaSeleccionada.setText("Ninguna — Haz clic en una mesa");
            txtCliente.setText("");
        }

        int libres = (controlador != null) ? controlador.contarLibres() : 0;
        int ocupadas = (controlador != null) ? controlador.contarOcupadas() : 0;
        int reservadas = (controlador != null) ? controlador.contarReservadas() : 0;
        lblTotalBadge.setText("Libres: " + libres + " | Ocupadas: " + ocupadas + " | Reservadas: " + reservadas);
    }

    // regresa a la ventana principal conservando la sesion activa
    private void volverAlMenu() {
        if (oUsuario != null) {
            new FrmPrincipal(oUsuario).setVisible(true);
        } else {
            new FrmPrincipal().setVisible(true);
        }
        dispose();
    }

    // cierra la sesion actual y regresa a la ventana de login
    private void cerrarSesion() {
        new FrmLogin().setVisible(true);
        dispose();
    }

    // aviso informativo para modulos laterales todavia no disponibles
    private void abrirModulo(String nombreModulo) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmMesas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtencion;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarPrueba;
    private javax.swing.JButton btnCarta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComprobantes;
    private javax.swing.JButton btnLiberar;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnOcupar;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblMesaSeleccionada;
    private javax.swing.JLabel lblRolBadge;
    private javax.swing.JLabel lblSesionTitulo;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblTotalBadge;
    private javax.swing.JLabel lblUsuarioActivo;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlBodyCola;
    private javax.swing.JPanel pnlBodyRegistro;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlHeaderCola;
    private javax.swing.JPanel pnlHeaderRegistro;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JPanel pnlMatrizMesas;
    private javax.swing.JPanel pnlPlatos;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JSeparator sepLogo;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
