package chifatailung.vista;

/** Pantalla de acceso del Chifa Tai Lung, editable con el diseñador de NetBeans. */
public class FrmLogin extends javax.swing.JFrame {
    public FrmLogin() {
        initComponents();
        configurarEstilo();
    }

    private void configurarEstilo() {
        String campos = "arc: 16; borderWidth: 1; borderColor: #CBB79E; focusColor: #C99822; focusedBorderColor: #C99822; margin: 8,14,8,14; background: #FFFFFF";
        txtUsuario.putClientProperty("FlatLaf.style", campos);
        txtClave.putClientProperty("FlatLaf.style", campos);
        txtUsuario.putClientProperty("JTextField.placeholderText", "admin");
        txtClave.putClientProperty("JTextField.placeholderText", "••••••••");
        btnIngresar.putClientProperty("FlatLaf.style", "arc: 16; borderWidth: 0; defaultBorderWidth: 0; defaultBackground: #A51619; defaultEndBackground: #A51619; defaultForeground: #FFFFFF; defaultFocusedBackground: #A51619; defaultHoverBackground: #8C1115; defaultPressedBackground: #780E12; background: #A51619; foreground: #FFFFFF; hoverBackground: #8C1115; pressedBackground: #780E12; focusedBackground: #A51619");
        btnIngresar.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        lblUsuario.setLabelFor(txtUsuario);
        lblClave.setLabelFor(txtClave);
        txtUsuario.getAccessibleContext().setAccessibleName("Usuario");
        txtClave.getAccessibleContext().setAccessibleName("Contraseña");
        lblLogo.getAccessibleContext().setAccessibleName("Logo Chifa Tai Lung");
        getRootPane().setDefaultButton(btnIngresar);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-login.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        pnlAcceso = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblClave = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chifa Tai Lung | Iniciar sesión");
        setResizable(false);

        pnlEncabezado.setBackground(new java.awt.Color(165, 22, 25));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 6, 0, new java.awt.Color(201, 152, 34)));
        pnlEncabezado.setPreferredSize(new java.awt.Dimension(480, 216));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-login.png"))); // NOI18N

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMarca.setText("Chifa Tai Lung");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlAcceso.setBackground(new java.awt.Color(250, 243, 231));
        pnlAcceso.setPreferredSize(new java.awt.Dimension(480, 384));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(48, 32, 25));
        lblTitulo.setText("Iniciar sesión");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 75, 61));
        lblUsuario.setText("Usuario");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(48, 32, 25));

        lblClave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblClave.setForeground(new java.awt.Color(102, 75, 61));
        lblClave.setText("Contraseña");

        txtClave.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(48, 32, 25));

        btnIngresar.setBackground(new java.awt.Color(165, 22, 25));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.setFocusPainted(false);
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);

        javax.swing.GroupLayout pnlAccesoLayout = new javax.swing.GroupLayout(pnlAcceso);
        pnlAcceso.setLayout(pnlAccesoLayout);
        pnlAccesoLayout.setHorizontalGroup(
            pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccesoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClave, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 58, Short.MAX_VALUE))
        );
        pnlAccesoLayout.setVerticalGroup(
            pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccesoLayout.createSequentialGroup()
                .addGroup(pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblClave, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAccesoLayout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 76, Short.MAX_VALUE))
        );

        getContentPane().add(pnlAcceso, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        char[] clave = txtClave.getPassword();
        boolean vacia = clave.length == 0;
        java.util.Arrays.fill(clave, (char) 0);
        if (txtUsuario.getText().trim().isEmpty() || vacia) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresa tu usuario y contraseña.", "Datos incompletos", javax.swing.JOptionPane.WARNING_MESSAGE);
            if (txtUsuario.getText().trim().isEmpty()) txtUsuario.requestFocusInWindow();
            else txtClave.requestFocusInWindow();
            return;
        }
        // Conectar aquí el controlador de autenticación cuando esté disponible.
        javax.swing.JOptionPane.showMessageDialog(this, "La autenticación aún no está conectada.", "Chifa Tai Lung", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnIngresarActionPerformed

    public static void main(String[] args) {
        Tema.aplicar();
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcceso;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
