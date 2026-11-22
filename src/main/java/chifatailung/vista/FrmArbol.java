/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package chifatailung.vista;

import chifatailung.controlador.ArbolController;
import chifatailung.estructuras.arboles.cABBPlatos;
import chifatailung.estructuras.arboles.cNodoABB;
import chifatailung.modelo.Plato;
import chifatailung.modelo.Rol;
import chifatailung.modelo.Usuario;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.text.Normalizer;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmArbol extends javax.swing.JFrame {
    
    private Usuario oUsuario;
    private ArbolController controlador;
    private DefaultTableModel modeloTabla;
    private int recorridoActivo;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmArbol() {
        this(new Usuario("admin", "", Rol.ADMIN));
    }

    public FrmArbol(Rol rol) {
        this(new Usuario(rol == Rol.ADMIN ? "admin" : "empleado", "", (rol != null) ? rol : Rol.ADMIN));
    }

    public FrmArbol(Usuario oUsuario) {
        this(oUsuario, (cABBPlatos) null);
    }

    public FrmArbol(Usuario oUsuario, cABBPlatos oABB) {
        this.oUsuario = (oUsuario != null) ? oUsuario : new Usuario("admin", "", Rol.ADMIN);
        Rol rolSesion = (this.oUsuario.getRol() != null) ? this.oUsuario.getRol() : Rol.ADMIN;
        this.controlador = new ArbolController(rolSesion, oABB);
        this.recorridoActivo = 2;

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
        btnBuscar.setBackground(new Color(182, 34, 38));
        btnBuscar.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 1; borderColor: #F3D27A; focusWidth: 0");
        
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
        
        // estilo pastilla para el badge de total de platos
        lblTotalBadge.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 14, 4, 14));
        lblTotalBadge.putClientProperty("FlatLaf.style", "arc: 999");

        // bordes redondeados en paneles contenedores
        pnlRegistro.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlPlatos.putClientProperty("FlatLaf.style", "arc: 16; border: 1,1,1,1,#D8C3A0");
        pnlHeaderRegistro.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");
        pnlHeaderCola.putClientProperty("FlatLaf.style", "arc: 16 16 0 0");

        // bordes redondeados en controles de entrada
        txtNombre.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");
        cmbTipo.putClientProperty("FlatLaf.style", "arc: 10; borderColor: #D8C3A0");

        // borde rojo vino completo para el panel de resultado de busqueda
        pnlResultado.putClientProperty("FlatLaf.style", "arc: 12; border: 1,1,1,1,#8B1212");

        // bordes redondeados en botones de accion y focusWidth: 0
        btnIncluir.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 0; focusWidth: 0");
        btnLimpiar.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");
        btnCargarPrueba.putClientProperty("FlatLaf.style", "arc: 12; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0");

        // borde redondeado y fondo en scroll de la tabla
        jScrollPane2.putClientProperty("FlatLaf.style", "arc: 12; border: 1,1,1,1,#D8C3A0");
        jScrollPane2.getViewport().setBackground(new Color(255, 253, 248));

        // cabecera y filas alternadas en la tabla de arbol
        tblArbol.getTableHeader().setBackground(new Color(234, 217, 188));
        tblArbol.getTableHeader().putClientProperty("FlatLaf.style", "background: #EAD9BC");
        tblArbol.putClientProperty("FlatLaf.style", "background: #FFFDF8; alternateRowColor: #FAF0DE");
        
        // boton de cerrar sesion redondeado
        btnCerrarSesion.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");
        btnVolver.putClientProperty("FlatLaf.style", "arc: 14; borderWidth: 0; focusWidth: 0");

        // datos de sesion activa
        actualizarSesionUI();

        // configuracion de tabla no editable
        configurarTabla();

        // conexion de eventos de botones
        conectarEventos();

        // restablecer estado inicial de busqueda
        restablecerEstadoBusqueda();

        // sincronizacion de datos con la vista y recorrido inicial
        refrescarTablaYRecorrido();
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
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCargarPrueba = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblArbol = new javax.swing.JTable();
        pnlRegistro = new javax.swing.JPanel();
        pnlHeaderRegistro = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pnlBodyRegistro = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        btnIncluir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlResultado = new javax.swing.JPanel();
        lblEstadoBusqueda = new javax.swing.JLabel();
        lblDetallePlato = new javax.swing.JLabel();

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
        lblTituloBanner.setText("Buscar platos");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 231, 194));
        jLabel3.setText("Búsqueda, recorridos y vista gráfica del árbol binario ordenado por nombre.");

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
                        .addContainerGap(312, Short.MAX_VALUE))
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
        jLabel7.setText("Árbol de platos");
        jLabel7.setPreferredSize(new java.awt.Dimension(166, 22));

        lblTotalBadge.setBackground(new java.awt.Color(255, 248, 238));
        lblTotalBadge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalBadge.setForeground(new java.awt.Color(90, 59, 34));
        lblTotalBadge.setText("Nodos: 0");
        lblTotalBadge.setOpaque(true);

        javax.swing.GroupLayout pnlHeaderColaLayout = new javax.swing.GroupLayout(pnlHeaderCola);
        pnlHeaderCola.setLayout(pnlHeaderColaLayout);
        pnlHeaderColaLayout.setHorizontalGroup(
            pnlHeaderColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderColaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 516, Short.MAX_VALUE)
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

        btnEditar.setBackground(new java.awt.Color(243, 230, 189));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(90, 59, 34));
        btnEditar.setText("Mostrar Preorden");

        btnEliminar.setBackground(new java.awt.Color(243, 230, 189));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(90, 59, 34));
        btnEliminar.setText("Mostrar Inorden");

        btnCargarPrueba.setBackground(new java.awt.Color(255, 253, 248));
        btnCargarPrueba.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCargarPrueba.setForeground(new java.awt.Color(139, 18, 18));
        btnCargarPrueba.setText("Cargar datos de prueba");

        jButton1.setBackground(new java.awt.Color(243, 230, 189));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(90, 59, 34));
        jButton1.setText("Mostrar Postorden");

        tblArbol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Plato", "Categoria", "Precio (S/)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblArbol);

        javax.swing.GroupLayout pnlBodyColaLayout = new javax.swing.GroupLayout(pnlBodyCola);
        pnlBodyCola.setLayout(pnlBodyColaLayout);
        pnlBodyColaLayout.setHorizontalGroup(
            pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyColaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlBodyColaLayout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1)
                        .addGap(145, 145, 145)
                        .addComponent(btnCargarPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlBodyColaLayout.setVerticalGroup(
            pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyColaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBodyColaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCargarPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlPlatos.add(pnlBodyCola, java.awt.BorderLayout.CENTER);

        pnlCuerpo.add(pnlPlatos, java.awt.BorderLayout.CENTER);

        pnlRegistro.setBackground(new java.awt.Color(255, 248, 238));
        pnlRegistro.setPreferredSize(new java.awt.Dimension(100, 185));
        pnlRegistro.setLayout(new java.awt.BorderLayout());

        pnlHeaderRegistro.setBackground(new java.awt.Color(234, 217, 188));
        pnlHeaderRegistro.setPreferredSize(new java.awt.Dimension(100, 44));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(139, 18, 18));
        jLabel4.setText("Buscar Plato");

        javax.swing.GroupLayout pnlHeaderRegistroLayout = new javax.swing.GroupLayout(pnlHeaderRegistro);
        pnlHeaderRegistro.setLayout(pnlHeaderRegistroLayout);
        pnlHeaderRegistroLayout.setHorizontalGroup(
            pnlHeaderRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderRegistroLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addContainerGap(704, Short.MAX_VALUE))
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
        jLabel6.setText("Tipo de busqueda");

        cmbTipo.setForeground(new java.awt.Color(59, 42, 32));
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Indexada", "Secuencial" }));

        btnIncluir.setBackground(new java.awt.Color(139, 18, 18));
        btnIncluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIncluir.setForeground(new java.awt.Color(255, 243, 214));
        btnIncluir.setText("Buscar");

        btnLimpiar.setBackground(new java.awt.Color(243, 230, 189));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(90, 59, 34));
        btnLimpiar.setText("Limpiar");

        pnlResultado.setBackground(new java.awt.Color(255, 253, 248));
        pnlResultado.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(139, 18, 18)));

        lblEstadoBusqueda.setText("RESULTADO DE BÚSQUEDA");

        lblDetallePlato.setText("Ingresa el nombre de un plato para buscar.");

        javax.swing.GroupLayout pnlResultadoLayout = new javax.swing.GroupLayout(pnlResultado);
        pnlResultado.setLayout(pnlResultadoLayout);
        pnlResultadoLayout.setHorizontalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDetallePlato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlResultadoLayout.setVerticalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstadoBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDetallePlato)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlBodyRegistroLayout = new javax.swing.GroupLayout(pnlBodyRegistro);
        pnlBodyRegistro.setLayout(pnlBodyRegistroLayout);
        pnlBodyRegistroLayout.setHorizontalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(146, 146, 146))
                            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlBodyRegistroLayout.setVerticalGroup(
            pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
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

    // configura el modelo de datos de la tabla de arbol haciendola no editable
    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Plato", "Categoría", "Precio (S/)" }
        ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean editable = false;
                return editable;
            }
        };
        tblArbol.setModel(modeloTabla);
    }

    // enlaza los eventos de accion a los botones del formulario y menu lateral
    private void conectarEventos() {
        btnIncluir.addActionListener(e -> buscarPlato());
        btnLimpiar.addActionListener(e -> limpiarBusqueda());

        btnEditar.addActionListener(e -> cambiarRecorrido(1));
        btnEliminar.addActionListener(e -> cambiarRecorrido(2));
        jButton1.addActionListener(e -> cambiarRecorrido(3));

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
            // Ya nos encontramos en el modulo de busqueda en arbol
        });
        btnPedidos.addActionListener(e -> abrirModulo("Pedidos"));
        btnMesas.addActionListener(e -> abrirModulo("Mesas"));
    }

    // realiza la busqueda secuencial o indexada en el arbol segun el combo
    private void buscarPlato() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Ingresa el nombre del plato a buscar.",
                "Validación de datos",
                JOptionPane.WARNING_MESSAGE);
        } else {
            String tipo = String.valueOf(cmbTipo.getSelectedItem()).trim();
            Plato encontrado = null;
            String modoTexto = "";
            cNodoABB raiz = (controlador != null && controlador.getArbol() != null) ? controlador.getArbol().getRaiz() : null;
            String nombreNorm = normalizarTexto(nombre);

            if (tipo.equalsIgnoreCase("Indexada")) {
                modoTexto = "BÚSQUEDA INDEXADA";
                encontrado = buscarIndexadaAux(raiz, nombreNorm);
                if (encontrado == null && controlador != null) {
                    encontrado = controlador.buscarIndexada(nombre);
                }
            } else {
                modoTexto = "BÚSQUEDA SECUENCIAL";
                encontrado = buscarSecuencialAux(raiz, nombreNorm);
                if (encontrado == null && controlador != null) {
                    encontrado = controlador.buscarSecuencial(nombre);
                }
            }

            if (encontrado != null) {
                lblEstadoBusqueda.setText("ENCONTRADO · " + modoTexto);
                lblEstadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblEstadoBusqueda.setForeground(new Color(139, 18, 18));
                String detalle = encontrado.getCodigo() + " - " + encontrado.getNombre()
                        + " (" + encontrado.getCategoria() + ") · "
                        + String.format(Locale.US, "S/. %.2f", encontrado.getPrecio())
                        + " · " + (encontrado.isDisponible() ? "Disponible" : "Agotado");
                lblDetallePlato.setText(detalle);
                lblDetallePlato.setForeground(new Color(59, 42, 32));

                seleccionarFilaPlato(encontrado.getCodigo());
            } else {
                tblArbol.clearSelection();
                lblEstadoBusqueda.setText("NO ENCONTRADO · " + modoTexto);
                lblEstadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblEstadoBusqueda.setForeground(new Color(139, 18, 18));
                lblDetallePlato.setText("No se encontró ningún plato con el nombre «" + nombre + "» en el árbol.");
                lblDetallePlato.setForeground(new Color(90, 59, 34));
            }
        }
    }

    // selecciona y desplaza el scroll hacia la fila del plato encontrado en la tabla
    private void seleccionarFilaPlato(String codigo) {
        if (codigo != null && tblArbol != null) {
            int totalFilas = tblArbol.getRowCount();
            for (int i = 0; i < totalFilas; i++) {
                Object valorCelda = tblArbol.getValueAt(i, 0);
                if (valorCelda != null && codigo.equalsIgnoreCase(valorCelda.toString())) {
                    tblArbol.setRowSelectionInterval(i, i);
                    tblArbol.scrollRectToVisible(tblArbol.getCellRect(i, 0, true));
                    break;
                }
            }
        }
    }

    // normaliza un texto eliminando tildes y diacriticos y pasandolo a minusculas
    private String normalizarTexto(String texto) {
        String resultado = "";
        if (texto != null) {
            String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
            resultado = normalizado.replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT).trim();
        }
        return resultado;
    }

    // busqueda indexada en el ABB navegando por izquierda o derecha con nombres normalizados
    private Plato buscarIndexadaAux(cNodoABB p, String nombreNorm) {
        Plato encontrado = null;
        if (p != null && nombreNorm != null && !nombreNorm.isEmpty()) {
            String nombreNodoNorm = normalizarTexto(p.getValor() != null ? p.getValor().getNombre() : "");
            int cmp = nombreNorm.compareTo(nombreNodoNorm);
            if (cmp == 0) {
                encontrado = p.getValor();
            } else if (cmp < 0) {
                encontrado = buscarIndexadaAux(p.getIzq(), nombreNorm);
            } else {
                encontrado = buscarIndexadaAux(p.getDer(), nombreNorm);
            }
        }
        return encontrado;
    }

    // busqueda secuencial en el ABB recorriendo todos los nodos con nombres normalizados
    private Plato buscarSecuencialAux(cNodoABB p, String nombreNorm) {
        Plato encontrado = null;
        if (p != null && nombreNorm != null && !nombreNorm.isEmpty()) {
            String nombreNodoNorm = normalizarTexto(p.getValor() != null ? p.getValor().getNombre() : "");
            if (nombreNorm.equals(nombreNodoNorm)) {
                encontrado = p.getValor();
            } else {
                encontrado = buscarSecuencialAux(p.getIzq(), nombreNorm);
                if (encontrado == null) {
                    encontrado = buscarSecuencialAux(p.getDer(), nombreNorm);
                }
            }
        }
        return encontrado;
    }

    // limpia el campo de texto, deselecciona la tabla y restablece los textos de busqueda
    private void limpiarBusqueda() {
        txtNombre.setText("");
        if (cmbTipo.getItemCount() > 0) {
            cmbTipo.setSelectedIndex(0);
        }
        tblArbol.clearSelection();
        restablecerEstadoBusqueda();
    }

    // restablece los textos informativos del panel de resultado
    private void restablecerEstadoBusqueda() {
        lblEstadoBusqueda.setText("RESULTADO DE BÚSQUEDA");
        lblEstadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEstadoBusqueda.setForeground(new Color(139, 18, 18));

        lblDetallePlato.setText("Ingresa el nombre de un plato y pulsa Buscar.");
        lblDetallePlato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDetallePlato.setForeground(new Color(59, 42, 32));
    }

    // cambia el recorrido activo y actualiza la tabla
    private void cambiarRecorrido(int nuevoRecorrido) {
        this.recorridoActivo = nuevoRecorrido;
        refrescarTablaYRecorrido();
    }

    // carga los platos de prueba en el arbol binario y actualiza la interfaz
    private void cargarDatosPrueba() {
        String err = controlador.cargarDatosPrueba();
        if (!err.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                err,
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        } else {
            lblEstadoBusqueda.setText("RESULTADO DE BÚSQUEDA");
            lblDetallePlato.setText("Datos de prueba cargados correctamente (" + controlador.getCantidad() + " platos).");
            refrescarTablaYRecorrido();
        }
    }

    // sincroniza la tabla, badges y recorridos con el arbol binario actual
    private void refrescarTablaYRecorrido() {
        if (modeloTabla == null) {
            modeloTabla = (DefaultTableModel) tblArbol.getModel();
        }
        modeloTabla.setRowCount(0);

        Plato[] platos = obtenerPlatosRecorrido(recorridoActivo);
        if (platos != null) {
            for (int i = 0; i < platos.length; i++) {
                Plato p = platos[i];
                if (p != null) {
                    modeloTabla.addRow(new Object[] {
                        p.getCodigo(),
                        p.getNombre(),
                        p.getCategoria(),
                        String.format(Locale.US, "S/. %.2f", p.getPrecio())
                    });
                }
            }
        }

        lblTotalBadge.setText("Nodos: " + (controlador != null ? controlador.getCantidad() : 0));
        actualizarEstiloBotonesRecorrido();
    }

    // resalta en rojo vino el boton del recorrido activo y neutraliza estados de foco en los 3 botones
    private void actualizarEstiloBotonesRecorrido() {
        JButton[] botones = { btnEditar, btnEliminar, jButton1 };
        int[] tipos = { 1, 2, 3 };

        String estiloActivo = "arc: 12; background: #8B1212; foreground: #FFF3D6; hoverBackground: #A11818; pressedBackground: #6E0D0D; focusedBackground: #8B1212; borderWidth: 1; borderColor: #8B1212; focusWidth: 0; font: bold";
        String estiloInactivo = "arc: 12; background: #F3E6BD; foreground: #5A3B22; hoverBackground: #EAD9A6; pressedBackground: #DFC98A; focusedBackground: #F3E6BD; borderWidth: 1; borderColor: #D8C3A0; focusWidth: 0; font: bold";

        for (int i = 0; i < botones.length; i++) {
            JButton btn = botones[i];
            if (tipos[i] == recorridoActivo) {
                btn.setBackground(new Color(139, 18, 18));
                btn.setForeground(new Color(255, 243, 214));
                btn.putClientProperty("FlatLaf.style", estiloActivo);
            } else {
                btn.setBackground(new Color(243, 230, 189));
                btn.setForeground(new Color(90, 59, 34));
                btn.putClientProperty("FlatLaf.style", estiloInactivo);
            }
        }
    }

    // devuelve arreglo de platos ordenados segun la opcion de recorrido
    private Plato[] obtenerPlatosRecorrido(int opcion) {
        int total = 0;
        if (controlador != null) {
            total = controlador.getCantidad();
        }
        Plato[] arreglo = new Plato[total];
        if (total > 0 && controlador != null && controlador.getArbol() != null) {
            cNodoABB raiz = controlador.getArbol().getRaiz();
            switch (opcion) {
                case 1:
                    llenarPreOrden(raiz, arreglo, 0);
                    break;
                case 2:
                    llenarInOrden(raiz, arreglo, 0);
                    break;
                case 3:
                    llenarPostOrden(raiz, arreglo, 0);
                    break;
                default:
                    llenarInOrden(raiz, arreglo, 0);
                    break;
            }
        }
        return arreglo;
    }

    private int llenarPreOrden(cNodoABB p, Plato[] arreglo, int indice) {
        int idx = indice;
        if (p != null && p.getValor() != null) {
            arreglo[idx++] = p.getValor();
            idx = llenarPreOrden(p.getIzq(), arreglo, idx);
            idx = llenarPreOrden(p.getDer(), arreglo, idx);
        }
        return idx;
    }

    private int llenarInOrden(cNodoABB p, Plato[] arreglo, int indice) {
        int idx = indice;
        if (p != null && p.getValor() != null) {
            idx = llenarInOrden(p.getIzq(), arreglo, idx);
            arreglo[idx++] = p.getValor();
            idx = llenarInOrden(p.getDer(), arreglo, idx);
        }
        return idx;
    }

    private int llenarPostOrden(cNodoABB p, Plato[] arreglo, int indice) {
        int idx = indice;
        if (p != null && p.getValor() != null) {
            idx = llenarPostOrden(p.getIzq(), arreglo, idx);
            idx = llenarPostOrden(p.getDer(), arreglo, idx);
            arreglo[idx++] = p.getValor();
        }
        return idx;
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
        JOptionPane.showMessageDialog(this,
            "Este módulo aún está en construcción.",
            nombreModulo,
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TemaApp.inicializar();
        java.awt.EventQueue.invokeLater(() -> new FrmArbol().setVisible(true));
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
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDetallePlato;
    private javax.swing.JLabel lblEstadoBusqueda;
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
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JSeparator sepLogo;
    private javax.swing.JTable tblArbol;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
