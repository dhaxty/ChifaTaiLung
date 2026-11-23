/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chifatailung.vista;

import chifatailung.controlador.AtencionController;
import chifatailung.modelo.ClienteAtencion;
import chifatailung.modelo.Prioridad;
import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmAtencion extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmAtencion.class.getName());
    private Usuario oUsuario;
    private AtencionController controlador;
    private DefaultTableModel modeloTabla;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmAtencion() {
        this(new Usuario("admin", "", Rol.ADMIN));
    }

    public FrmAtencion(Rol rol) {
        this(new Usuario(rol == Rol.ADMIN ? "admin" : "empleado", "", (rol != null) ? rol : Rol.ADMIN));
    }

    public FrmAtencion(Usuario oUsuario) {
        this.oUsuario = (oUsuario != null) ? oUsuario : new Usuario("admin", "", Rol.ADMIN);
        Rol rolSesion = (this.oUsuario.getRol() != null) ? this.oUsuario.getRol() : Rol.ADMIN;
        this.controlador = new AtencionController(rolSesion);

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
        btnAtencion.setBackground(new Color(182, 34, 38));
        btnAtencion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 1; borderColor: #F3D27A; focusWidth: 0");
        
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
        
        // estilo pastilla para las etiquetas de estado de la cola
        lblSiguienteActual.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblSiguienteActual.putClientProperty("FlatLaf.style", "arc: 999");

        lblEnEsperaBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblEnEsperaBadge.putClientProperty("FlatLaf.style", "arc: 999");

        // bordes redondeados en paneles contenedores
        pnlRegistro.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlCola.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");

        // bordes redondeados en controles de entrada
        txtNombreCliente.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");
        cmbPrioridad.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");

        // bordes redondeados en botones de accion
        JButton[] botonesAccion = { btnIncluir, btnAtender, btnVerSiguiente, btnCargarPrueba };
        for (JButton btn : botonesAccion) {
            btn.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0");
        }

        // cabecera y filas alternadas en la tabla de cola
        tblCola.getTableHeader().setBackground(new Color(234, 217, 188));
        tblCola.getTableHeader().putClientProperty("FlatLaf.style", "background: #EAD9BC");
        tblCola.putClientProperty("FlatLaf.style", "background: #FFFDF8; alternateRowColor: #FAF0DE");
        
        // boton de cerrar sesion redondeado
        btnCerrarSesion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        btnVolver.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        
        // navegacion de regreso y cierre de sesion
        btnVolver.addActionListener(e -> volverAlMenu());

        // datos de sesion activa
        actualizarSesionUI();

        // configuracion de tabla no editable
        configurarTabla();

        // conexion de eventos de botones
        conectarEventos();

        // sincronizacion de datos con la vista
        refrescarVista();
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
        pnlCola = new javax.swing.JPanel();
        pnlHeaderCola = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblSiguienteActual = new javax.swing.JLabel();
        lblEnEsperaBadge = new javax.swing.JLabel();
        pnlBodyCola = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCola = new javax.swing.JTable();
        btnAtender = new javax.swing.JButton();
        btnVerSiguiente = new javax.swing.JButton();
        btnCargarPrueba = new javax.swing.JButton();
        pnlRegistro = new javax.swing.JPanel();
        pnlHeaderRegistro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlBodyRegistro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbPrioridad = new javax.swing.JComboBox<>();
        btnIncluir = new javax.swing.JButton();

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
        lblTituloBanner.setText("Atención de clientes");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 231, 194));
        jLabel3.setText("Control de turnos en espera con atención preferencial");

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
                        .addContainerGap(445, Short.MAX_VALUE))
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

        pnlCola.setBackground(new java.awt.Color(255, 248, 238));
        pnlCola.setLayout(new java.awt.BorderLayout());

        pnlHeaderCola.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderCola.setPreferredSize(new java.awt.Dimension(100, 46));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(139, 18, 18));
        jLabel7.setText("Clientes en espera");
        jLabel7.setPreferredSize(new java.awt.Dimension(166, 22));

        lblSiguienteActual.setBackground(new java.awt.Color(255, 248, 238));
        lblSiguienteActual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSiguienteActual.setForeground(new java.awt.Color(90, 59, 34));
        lblSiguienteActual.setText("Siguiente en fila: Cola vacía");
        lblSiguienteActual.setOpaque(true);

        lblEnEsperaBadge.setBackground(new java.awt.Color(255, 248, 238));
        lblEnEsperaBadge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEnEsperaBadge.setForeground(new java.awt.Color(90, 59, 34));
        lblEnEsperaBadge.setText("En espera: 0");
        lblEnEsperaBadge.setOpaque(true);

        javax.swing.GroupLayout pnlHeaderColaLayout = new javax.swing.GroupLayout(pnlHeaderCola);
        pnlHeaderCola.setLayout(pnlHeaderColaLayout);
        pnlHeaderColaLayout.setHorizontalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(lblSiguienteActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(lblEnEsperaBadge)
                .addGap(69, 69, 69))
        );
        pnlHeaderColaLayout.setVerticalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSiguienteActual)
                    .addComponent(lblEnEsperaBadge))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlCola.add(pnlHeaderCola, java.awt.BorderLayout.NORTH);

        pnlBodyCola.setBackground(new java.awt.Color(255, 248, 238));

        tblCola.setForeground(new java.awt.Color(59, 42, 32));
        tblCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turno", "Cliente", "Prioridad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCola.setRowHeight(32);
        jScrollPane1.setViewportView(tblCola);

        btnAtender.setBackground(new java.awt.Color(139, 18, 18));
        btnAtender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAtender.setForeground(new java.awt.Color(255, 243, 214));
        btnAtender.setText("Atender siguiente");

        btnVerSiguiente.setBackground(new java.awt.Color(243, 230, 189));
        btnVerSiguiente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerSiguiente.setForeground(new java.awt.Color(90, 59, 34));
        btnVerSiguiente.setText("Ver siguiente");

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
                        .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnVerSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlBodyColaLayout.setVerticalGroup(
            pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyColaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnVerSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCargarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlCola.add(pnlBodyCola, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlCola, java.awt.BorderLayout.CENTER);

        pnlRegistro.setBackground(new java.awt.Color(255, 248, 238));
        pnlRegistro.setPreferredSize(new java.awt.Dimension(100, 138));
        pnlRegistro.setLayout(new java.awt.BorderLayout());

        pnlHeaderRegistro.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderRegistro.setPreferredSize(new java.awt.Dimension(100, 44));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setText("Registrar nuevo turno");

        javax.swing.GroupLayout pnlHeaderRegistroLayout = new javax.swing.GroupLayout(pnlHeaderRegistro);
        pnlHeaderRegistro.setLayout(pnlHeaderRegistroLayout);
        pnlHeaderRegistroLayout.setHorizontalGroup(
            pnlHeaderRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderRegistroLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(632, Short.MAX_VALUE))
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
        jLabel5.setText("Nombre del cliente");

        txtNombreCliente.setForeground(new java.awt.Color(59, 42, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(90, 59, 34));
        jLabel6.setText("Prioridad");

        cmbPrioridad.setForeground(new java.awt.Color(59, 42, 32));
        cmbPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preferencial", "Regular" }));

        btnIncluir.setBackground(new java.awt.Color(139, 18, 18));
        btnIncluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIncluir.setForeground(new java.awt.Color(255, 243, 214));
        btnIncluir.setText("Registrar turno");

        javax.swing.GroupLayout pnlBodyRegistroLayout = new javax.swing.GroupLayout(pnlBodyRegistro);
        pnlBodyRegistro.setLayout(pnlBodyRegistroLayout);
        pnlBodyRegistroLayout.setHorizontalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        pnlBodyRegistroLayout.setVerticalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
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

    // configura el modelo de datos de la tabla de cola haciendola no editable
    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Turno", "Cliente", "Prioridad" }
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean editable = false;
                return editable;
            }
        };
        tblCola.setModel(modeloTabla);
    }

    // enlaza los eventos de accion a los botones del formulario y menu
    private void conectarEventos() {
        btnIncluir.addActionListener(e -> registrarTurno());
        btnAtender.addActionListener(e -> atenderCliente());
        btnVerSiguiente.addActionListener(e -> verSiguiente());
        btnCargarPrueba.addActionListener(e -> cargarDatosPrueba());

        btnCarta.addActionListener(e -> {
            new FrmPlatos(oUsuario).setVisible(true);
            dispose();
        });
        btnPedidos.addActionListener(e -> {
            new FrmPedidos(oUsuario).setVisible(true);
            dispose();
        });
        btnAtencion.addActionListener(e -> {
            // Modulo activo actualmente
        });
        btnComprobantes.addActionListener(e -> abrirComprobantes());
        btnBuscar.addActionListener(e -> {
            new FrmArbol(oUsuario).setVisible(true);
            dispose();
        });
        btnMesas.addActionListener(e -> {
            new FrmMesas(oUsuario).setVisible(true);
            dispose();
        });
    }

    // valida y registra un nuevo turno en la cola de atencion
    private void registrarTurno() {
        String nombre = txtNombreCliente.getText().trim();
        Prioridad prioridad = Prioridad.REGULAR;
        Object seleccionado = cmbPrioridad.getSelectedItem();
        if (seleccionado != null && "Preferencial".equalsIgnoreCase(seleccionado.toString().trim())) {
            prioridad = Prioridad.PREFERENCIAL;
        }

        String err = controlador.encolar(nombre, prioridad);
        if (!err.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                err,
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            txtNombreCliente.setText("");
            cmbPrioridad.setSelectedIndex(0);
            refrescarVista();
        }
    }

    // atiende y desencola al cliente al frente de la cola con atencion prioritaria
    private void atenderCliente() {
        String err = controlador.atender();
        if (!err.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                err,
                "Atención de clientes",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            ClienteAtencion atendido = controlador.getUltimoAtendido();
            if (atendido != null) {
                String detalle = "Cliente atendido con éxito:\n\n"
                        + "Turno: " + atendido.getNumeroTurno() + "\n"
                        + "Cliente: " + atendido.getNombreCliente() + "\n"
                        + "Prioridad: " + atendido.getPrioridad().getDescripcion();
                JOptionPane.showMessageDialog(this,
                    detalle,
                    "Cliente atendido",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            refrescarVista();
        }
    }

    // consulta sin remover al proximo cliente al frente de la cola
    private void verSiguiente() {
        ClienteAtencion siguiente = controlador.verSiguiente();
        if (siguiente == null) {
            lblSiguienteActual.setText("Siguiente en fila: Cola vacía");
            JOptionPane.showMessageDialog(this,
                "La cola de atención está vacía. No hay clientes en espera.",
                "Siguiente en fila",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            String descPrioridad = (siguiente.getPrioridad() != null) ? siguiente.getPrioridad().getDescripcion() : "";
            String textoSiguiente = "Siguiente en fila: Turno " + siguiente.getNumeroTurno() + " - " + siguiente.getNombreCliente() + " (" + descPrioridad + ")";
            lblSiguienteActual.setText(textoSiguiente);
            String detalle = "Próximo cliente a ser atendido:\n\n"
                    + "Turno: " + siguiente.getNumeroTurno() + "\n"
                    + "Cliente: " + siguiente.getNombreCliente() + "\n"
                    + "Prioridad: " + descPrioridad;
            JOptionPane.showMessageDialog(this,
                detalle,
                "Siguiente en fila",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // carga clientes de prueba para demostrar la prioridad y orden FIFO
    private void cargarDatosPrueba() {
        String err = controlador.cargarDatosPrueba();
        if (!err.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                err,
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            refrescarVista();
        }
    }

    // sincroniza la tabla y etiquetas con el estado actual de la cola de atencion
    private void refrescarVista() {
        if (modeloTabla == null) {
            modeloTabla = (DefaultTableModel) tblCola.getModel();
        }
        modeloTabla.setRowCount(0);

        ClienteAtencion[] lista = controlador.listar();
        if (lista != null) {
            for (int i = 0; i < lista.length; i++) {
                ClienteAtencion c = lista[i];
                if (c != null) {
                    modeloTabla.addRow(new Object[] {
                        c.getNumeroTurno(),
                        c.getNombreCliente(),
                        (c.getPrioridad() != null) ? c.getPrioridad().getDescripcion() : ""
                    });
                }
            }
        }

        lblEnEsperaBadge.setText("En espera: " + controlador.getCantidad());

        ClienteAtencion siguiente = controlador.verSiguiente();
        if (siguiente == null) {
            lblSiguienteActual.setText("Siguiente en fila: Cola vacía");
        } else {
            String descPrioridad = (siguiente.getPrioridad() != null) ? siguiente.getPrioridad().getDescripcion() : "";
            lblSiguienteActual.setText("Siguiente en fila: Turno " + siguiente.getNumeroTurno() + " - " + siguiente.getNombreCliente() + " (" + descPrioridad + ")");
        }
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

    // abre el modulo de comprobantes transfiriendo el usuario en sesion
    private void abrirComprobantes() {
        new FrmComprobantes(oUsuario).setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> new FrmAtencion().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtencion;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargarPrueba;
    private javax.swing.JButton btnCarta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComprobantes;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnMesas;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnVerSiguiente;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbPrioridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEnEsperaBadge;
    private javax.swing.JLabel lblRolBadge;
    private javax.swing.JLabel lblSesionTitulo;
    private javax.swing.JLabel lblSiguienteActual;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblUsuarioActivo;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlBodyCola;
    private javax.swing.JPanel pnlBodyRegistro;
    private javax.swing.JPanel pnlCola;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlHeaderCola;
    private javax.swing.JPanel pnlHeaderRegistro;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JSeparator sepLogo;
    private javax.swing.JTable tblCola;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
