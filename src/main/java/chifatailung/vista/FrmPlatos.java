package chifatailung.vista;

import chifatailung.controlador.PlatoController;
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

public class FrmPlatos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPlatos.class.getName());
    private Usuario oUsuario;
    private PlatoController controlador;
    private DefaultTableModel modeloTabla;
    private String codigoSeleccionado;

    public FrmPlatos() {
        this(new Usuario("admin", "", Rol.ADMIN));
    }

    public FrmPlatos(Rol rol) {
        this(new Usuario(rol == Rol.ADMIN ? "admin" : "empleado", "", (rol != null) ? rol : Rol.ADMIN));
    }

    public FrmPlatos(Usuario oUsuario) {
        this.oUsuario = (oUsuario != null) ? oUsuario : new Usuario("admin", "", Rol.ADMIN);
        Rol rolSesion = (this.oUsuario.getRol() != null) ? this.oUsuario.getRol() : Rol.ADMIN;
        this.controlador = new PlatoController(rolSesion);
        this.codigoSeleccionado = null;

        initComponents();
        
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/logo140px.png")).getImage());
        getRootPane().putClientProperty("FlatLaf.fullWindowContent", true);

        JButton[] botonesMenu = {
            btnCarta, btnPedidos, btnAtencion, btnComprobantes, btnBuscar, btnMesas
        };
        for (JButton btn : botonesMenu) {
            btn.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
            btn.setMargin(new Insets(0, 14, 0, 14));
        }

        btnCarta.setBackground(new Color(182, 34, 38));
        btnCarta.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 1; borderColor: #F3D27A; focusWidth: 0");
        
        btnCarta.setIcon(iconoMenu("carta.svg"));
        btnPedidos.setIcon(iconoMenu("pedidos.svg"));
        btnAtencion.setIcon(iconoMenu("clientes.svg"));
        btnComprobantes.setIcon(iconoMenu("comprobantes.svg"));
        btnBuscar.setIcon(iconoMenu("buscar.svg"));
        btnMesas.setIcon(iconoMenu("mesas.svg"));
        
        pnlBanner.putClientProperty("FlatLaf.style", "arc: 22");

        lblRolBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblRolBadge.putClientProperty("FlatLaf.style", "arc: 999");
        
        lblTotalBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblTotalBadge.putClientProperty("FlatLaf.style", "arc: 999");

        pnlRegistro.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlPlatos.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlHeaderRegistro.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");
        pnlHeaderCola.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");

        txtNombre.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");
        cmbCategoria.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");
        txtPrecio.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");
        cmbEstado.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");

        JButton[] botonesAccion = { btnIncluir, btnLimpiar, btnEditar, btnEliminar, btnCargarPrueba };
        for (JButton btn : botonesAccion) {
            btn.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0");
        }

        tblPlatos.getTableHeader().setBackground(new Color(234, 217, 188));
        tblPlatos.getTableHeader().putClientProperty("FlatLaf.style", "background: #EAD9BC");
        tblPlatos.putClientProperty("FlatLaf.style", "background: #FFFDF8; alternateRowColor: #FAF0DE");
        
        btnCerrarSesion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        btnVolver.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");

        // restriccion de permisos para rol empleado (solo lectura)
        if (this.oUsuario.getRol() == Rol.EMPLEADO) {
            btnIncluir.setEnabled(false);
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        actualizarSesionUI();
        configurarTabla();
        conectarEventos();
        refrescarVista();
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
        pnlPlatos = new javax.swing.JPanel();
        pnlHeaderCola = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalBadge = new javax.swing.JLabel();
        pnlBodyCola = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlatos = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCargarPrueba = new javax.swing.JButton();
        pnlRegistro = new javax.swing.JPanel();
        pnlHeaderRegistro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlBodyRegistro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        btnIncluir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();

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
        lblTituloBanner.setText("Carta de platos");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 231, 194));
        jLabel3.setText("Administra los platos, categorías y precios de la carta del restaurante.");

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
                        .addContainerGap(352, Short.MAX_VALUE))
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
        jLabel7.setText("Platos registrados");
        jLabel7.setPreferredSize(new java.awt.Dimension(166, 22));

        lblTotalBadge.setBackground(new java.awt.Color(255, 248, 238));
        lblTotalBadge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalBadge.setForeground(new java.awt.Color(90, 59, 34));
        lblTotalBadge.setText("Total: 0 platos");
        lblTotalBadge.setOpaque(true);

        javax.swing.GroupLayout pnlHeaderColaLayout = new javax.swing.GroupLayout(pnlHeaderCola);
        pnlHeaderCola.setLayout(pnlHeaderColaLayout);
        pnlHeaderColaLayout.setHorizontalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 487, Short.MAX_VALUE)
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

        tblPlatos.setForeground(new java.awt.Color(59, 42, 32));
        tblPlatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Plato", "Categoria", "Precio (S/)", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlatos.setRowHeight(32);
        jScrollPane1.setViewportView(tblPlatos);

        btnEditar.setBackground(new java.awt.Color(139, 18, 18));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 243, 214));
        btnEditar.setText("Editar seleccionado");

        btnEliminar.setBackground(new java.awt.Color(243, 230, 189));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(90, 59, 34));
        btnEliminar.setText("Eliminar");

        btnCargarPrueba.setBackground(new java.awt.Color(255, 253, 248));
        btnCargarPrueba.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCargarPrueba.setForeground(new java.awt.Color(139, 18, 18));
        btnCargarPrueba.setText("Cargar datos de prueba");

        javax.swing.GroupLayout pnlBodyColaLayout = new javax.swing.GroupLayout(pnlBodyCola);
        pnlBodyCola.setLayout(pnlBodyColaLayout);
        pnlBodyColaLayout.setHorizontalGroup(
            pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyColaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodyColaLayout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlBodyColaLayout.setVerticalGroup(
            pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyColaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCargarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlPlatos.add(pnlBodyCola, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlPlatos, java.awt.BorderLayout.CENTER);

        pnlRegistro.setBackground(new java.awt.Color(255, 248, 238));
        pnlRegistro.setPreferredSize(new java.awt.Dimension(100, 182));
        pnlRegistro.setLayout(new java.awt.BorderLayout());

        pnlHeaderRegistro.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderRegistro.setPreferredSize(new java.awt.Dimension(100, 44));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setText("Registrar nuevo plato");

        javax.swing.GroupLayout pnlHeaderRegistroLayout = new javax.swing.GroupLayout(pnlHeaderRegistro);
        pnlHeaderRegistro.setLayout(pnlHeaderRegistroLayout);
        pnlHeaderRegistroLayout.setHorizontalGroup(
            pnlHeaderRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderRegistroLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(634, Short.MAX_VALUE))
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
        jLabel5.setText("Nombre del plato");

        txtNombre.setForeground(new java.awt.Color(59, 42, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(90, 59, 34));
        jLabel6.setText("Categoria");

        cmbCategoria.setForeground(new java.awt.Color(59, 42, 32));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arroces", "Tallarines", "Sopas", "Entradas", "Platos de fondo", "Bebidas" }));

        btnIncluir.setBackground(new java.awt.Color(139, 18, 18));
        btnIncluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIncluir.setForeground(new java.awt.Color(255, 243, 214));
        btnIncluir.setText("Agregar plato");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Precio (S/)");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Estado");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Agotado", " " }));

        btnLimpiar.setBackground(new java.awt.Color(243, 230, 189));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(90, 59, 34));
        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout pnlBodyRegistroLayout = new javax.swing.GroupLayout(pnlBodyRegistro);
        pnlBodyRegistro.setLayout(pnlBodyRegistroLayout);
        pnlBodyRegistroLayout.setHorizontalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(43, 43, 43)
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        pnlBodyRegistroLayout.setVerticalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlRegistro.add(pnlBodyRegistro, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlRegistro, java.awt.BorderLayout.NORTH);

        pnlContenido.add(pnlCuerpo, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1166, 774));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Plato", "Categoría", "Precio (S/)", "Estado" }
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean editable = false;
                return editable;
            }
        };
        tblPlatos.setModel(modeloTabla);

        tblPlatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPlatoSeleccionado();
            }
        });

        tblPlatos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarPlatoSeleccionado();
            }
        });
    }

    private void conectarEventos() {
        btnIncluir.addActionListener(e -> registrarPlato());
        btnEditar.addActionListener(e -> editarPlato());
        btnEliminar.addActionListener(e -> eliminarPlato());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCargarPrueba.addActionListener(e -> cargarDatosPrueba());

        btnVolver.addActionListener(e -> volverAlMenu());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());

        btnCarta.addActionListener(e -> {
        });
        btnAtencion.addActionListener(e -> abrirAtencion());
        btnComprobantes.addActionListener(e -> abrirComprobantes());
        btnPedidos.addActionListener(e -> {
            new FrmPedidos(oUsuario).setVisible(true);
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

    private void cargarPlatoSeleccionado() {
        int fila = tblPlatos.getSelectedRow();
        if (fila >= 0 && fila < tblPlatos.getRowCount()) {
            Object valCodigo = tblPlatos.getValueAt(fila, 0);
            if (valCodigo != null) {
                codigoSeleccionado = valCodigo.toString().trim();
                Plato p = controlador.buscar(codigoSeleccionado);
                if (p != null) {
                    txtNombre.setText(p.getNombre());
                    seleccionarCategoria(p.getCategoria());
                    txtPrecio.setText(String.format(Locale.US, "%.2f", p.getPrecio()));
                    cmbEstado.setSelectedItem(p.isDisponible() ? "Disponible" : "Agotado");
                }
            }
        }
    }

    private void seleccionarCategoria(String categoria) {
        if (categoria != null && !categoria.trim().isEmpty()) {
            boolean encontrada = false;
            for (int i = 0; i < cmbCategoria.getItemCount(); i++) {
                if (categoria.trim().equalsIgnoreCase(cmbCategoria.getItemAt(i).trim())) {
                    cmbCategoria.setSelectedIndex(i);
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                cmbCategoria.addItem(categoria.trim());
                cmbCategoria.setSelectedItem(categoria.trim());
            }
        }
    }

    private void registrarPlato() {
        String nombre = txtNombre.getText().trim();
        String categoria = (cmbCategoria.getSelectedItem() != null) ? cmbCategoria.getSelectedItem().toString().trim() : "";
        String textoPrecio = txtPrecio.getText().trim();
        boolean disponible = "Disponible".equalsIgnoreCase((cmbEstado.getSelectedItem() != null) ? cmbEstado.getSelectedItem().toString().trim() : "Disponible");

        double precio = 0.0;
        boolean precioValido = false;

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "El nombre del plato es obligatorio.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else if (categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "La categoría es obligatoria.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                precio = Double.parseDouble(textoPrecio);
                if (precio < 0) {
                    JOptionPane.showMessageDialog(this,
                        "El precio no puede ser negativo.",
                        "Validación de datos",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    precioValido = true;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "El precio debe ser un número decimal válido.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            }

            if (precioValido) {
                String err = controlador.registrar(nombre, categoria, precio, disponible);
                if (!err.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        err,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    limpiarCampos();
                    refrescarVista();
                }
            }
        }
    }

    private void editarPlato() {
        if (codigoSeleccionado == null || codigoSeleccionado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Debes seleccionar un plato de la tabla para editarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String nombre = txtNombre.getText().trim();
            String categoria = (cmbCategoria.getSelectedItem() != null) ? cmbCategoria.getSelectedItem().toString().trim() : "";
            String textoPrecio = txtPrecio.getText().trim();
            boolean disponible = "Disponible".equalsIgnoreCase((cmbEstado.getSelectedItem() != null) ? cmbEstado.getSelectedItem().toString().trim() : "Disponible");

            double precio = 0.0;
            boolean precioValido = false;

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "El nombre del plato es obligatorio.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else if (categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "La categoría es obligatoria.",
                    "Validación de datos",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    precio = Double.parseDouble(textoPrecio);
                    if (precio < 0) {
                        JOptionPane.showMessageDialog(this,
                            "El precio no puede ser negativo.",
                            "Validación de datos",
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        precioValido = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                        "El precio debe ser un número decimal válido.",
                        "Validación de datos",
                        JOptionPane.WARNING_MESSAGE);
                }

                if (precioValido) {
                    String err = controlador.actualizar(codigoSeleccionado, nombre, categoria, precio, disponible);
                    if (!err.isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                            err,
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        limpiarCampos();
                        refrescarVista();
                    }
                }
            }
        }
    }

    private void eliminarPlato() {
        if (codigoSeleccionado == null || codigoSeleccionado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Debes seleccionar un plato de la tabla para eliminarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar el plato con código " + codigoSeleccionado + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (confirmacion == JOptionPane.YES_OPTION) {
                String err = controlador.eliminar(codigoSeleccionado);
                if (!err.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        err,
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    limpiarCampos();
                    refrescarVista();
                }
            }
        }
    }

    private void limpiarCampos() {
        codigoSeleccionado = null;
        txtNombre.setText("");
        txtPrecio.setText("");
        if (cmbCategoria.getItemCount() > 0) {
            cmbCategoria.setSelectedIndex(0);
        }
        if (cmbEstado.getItemCount() > 0) {
            cmbEstado.setSelectedIndex(0);
        }
        tblPlatos.clearSelection();
    }

    private void cargarDatosPrueba() {
        String err = controlador.cargarDatosPrueba();
        if (!err.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                err,
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            limpiarCampos();
            refrescarVista();
        }
    }

    private void refrescarVista() {
        if (modeloTabla == null) {
            modeloTabla = (DefaultTableModel) tblPlatos.getModel();
        }
        modeloTabla.setRowCount(0);

        Plato[] lista = controlador.listar();
        if (lista != null) {
            for (int i = 0; i < lista.length; i++) {
                Plato p = lista[i];
                if (p != null) {
                    modeloTabla.addRow(new Object[] {
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria(),
                        String.format(Locale.US, "%.2f", p.getPrecio()),
                        p.isDisponible() ? "Disponible" : "Agotado"
                    });
                }
            }
        }

        lblTotalBadge.setText("Total: " + controlador.getCantidad() + " platos");
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

    private void abrirAtencion() {
        if (oUsuario != null) {
            new FrmAtencion(oUsuario).setVisible(true);
        } else {
            new FrmAtencion().setVisible(true);
        }
        dispose();
    }

    private void abrirComprobantes() {
        if (oUsuario != null) {
            new FrmComprobantes(oUsuario).setVisible(true);
        } else {
            new FrmComprobantes().setVisible(true);
        }
        dispose();
    }

    private void abrirModulo(String nombreModulo) {
    }

    public static void main(String args[]) {
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmPlatos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtencion;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarPrueba;
    private javax.swing.JButton btnCarta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComprobantes;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JPanel pnlPlatos;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JSeparator sepLogo;
    private javax.swing.JTable tblPlatos;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
