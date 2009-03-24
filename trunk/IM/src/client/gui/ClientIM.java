package client.gui;

public class ClientIM extends javax.swing.JFrame {

    /** Creates new form ClientIM */
    public ClientIM() {
        initComponents();
        initClientIM();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegisterjFrame = new javax.swing.JFrame();
        ButtonjPanelR = new javax.swing.JPanel();
        OKjButtonR = new javax.swing.JButton();
        ClearjButtonR = new javax.swing.JButton();
        RContentjPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        LoginjFrame = new javax.swing.JFrame();
        ButtonjPanelR1 = new javax.swing.JPanel();
        OKjButtonR1 = new javax.swing.JButton();
        ClearjButtonR1 = new javax.swing.JButton();
        RContentjPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        ContentjPanel = new javax.swing.JPanel();
        LoginjPanel = new javax.swing.JPanel();
        LogoutjPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        UsernamejLabel = new javax.swing.JLabel();
        PasswordjLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        UsernamejTextField = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        LoginjButton = new javax.swing.JButton();
        StatusjToolBar = new javax.swing.JToolBar();
        StatusjTextField = new javax.swing.JTextField();
        ActionjMenuBar = new javax.swing.JMenuBar();
        ActionjMenu = new javax.swing.JMenu();
        LogoutjMenuItem = new javax.swing.JMenuItem();

        RegisterjFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        RegisterjFrame.setTitle("Registration");
        RegisterjFrame.setMinimumSize(new java.awt.Dimension(300, 175));
        RegisterjFrame.setResizable(false);

        ButtonjPanelR.setLayout(new java.awt.GridLayout(1, 3, 5, 0));

        OKjButtonR.setText("OK");
        ButtonjPanelR.add(OKjButtonR);

        ClearjButtonR.setText("Cancel");
        ButtonjPanelR.add(ClearjButtonR);

        RegisterjFrame.getContentPane().add(ButtonjPanelR, java.awt.BorderLayout.SOUTH);

        RContentjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        RContentjPanel.setPreferredSize(new java.awt.Dimension(300, 100));
        RContentjPanel.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jLabel1.setText("Name");
        jPanel2.add(jLabel1);

        jLabel3.setText("Username");
        jPanel2.add(jLabel3);

        jLabel2.setText("Password");
        jPanel2.add(jLabel2);

        RContentjPanel.add(jPanel2, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 0, 5));
        jPanel4.add(jTextField1);
        jPanel4.add(jTextField3);
        jPanel4.add(jPasswordField1);

        RContentjPanel.add(jPanel4, java.awt.BorderLayout.CENTER);

        RegisterjFrame.getContentPane().add(RContentjPanel, java.awt.BorderLayout.NORTH);

        RegisterjFrame.getAccessibleContext().setAccessibleParent(this);

        LoginjFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        LoginjFrame.setTitle("User Login");
        LoginjFrame.setMinimumSize(new java.awt.Dimension(300, 175));
        LoginjFrame.setResizable(false);

        ButtonjPanelR1.setLayout(new java.awt.GridLayout(1, 3, 5, 0));

        OKjButtonR1.setText("OK");
        ButtonjPanelR1.add(OKjButtonR1);

        ClearjButtonR1.setText("Cancel");
        ButtonjPanelR1.add(ClearjButtonR1);

        LoginjFrame.getContentPane().add(ButtonjPanelR1, java.awt.BorderLayout.SOUTH);

        RContentjPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register To The Server", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        RContentjPanel1.setPreferredSize(new java.awt.Dimension(300, 100));
        RContentjPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel3.setLayout(new java.awt.GridLayout(3, 1));

        jLabel5.setText("Username");
        jPanel3.add(jLabel5);

        jLabel6.setText("Password");
        jPanel3.add(jLabel6);

        RContentjPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel5.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jTextField4.setText("jTextField1");
        jPanel5.add(jTextField4);

        jPasswordField2.setText("jPasswordField1");
        jPanel5.add(jPasswordField2);

        RContentjPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        LoginjFrame.getContentPane().add(RContentjPanel1, java.awt.BorderLayout.NORTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IM Client");

        ContentjPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout LoginjPanelLayout = new javax.swing.GroupLayout(LoginjPanel);
        LoginjPanel.setLayout(LoginjPanelLayout);
        LoginjPanelLayout.setHorizontalGroup(
            LoginjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        LoginjPanelLayout.setVerticalGroup(
            LoginjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        ContentjPanel.add(LoginjPanel, "loginCard");

        LogoutjPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.BorderLayout(5, 10));

        jPanel6.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        UsernamejLabel.setText("Username");
        jPanel6.add(UsernamejLabel);

        PasswordjLabel.setText("Password");
        jPanel6.add(PasswordjLabel);

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        UsernamejTextField.setText("Enter username");
        UsernamejTextField.setMinimumSize(new java.awt.Dimension(15, 20));
        UsernamejTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SelectAllUser(evt);
            }
        });
        jPanel7.add(UsernamejTextField);

        jPasswordField.setText("password");
        jPasswordField.setMinimumSize(new java.awt.Dimension(15, 20));
        jPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SelectAllPass(evt);
            }
        });
        jPanel7.add(jPasswordField);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        LoginjButton.setText("Login");
        LoginjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginjButtonActionPerformed(evt);
            }
        });
        jPanel1.add(LoginjButton, java.awt.BorderLayout.SOUTH);

        LogoutjPanel.add(jPanel1, new java.awt.GridBagConstraints());

        ContentjPanel.add(LogoutjPanel, "logoutCard");

        getContentPane().add(ContentjPanel, java.awt.BorderLayout.CENTER);

        StatusjToolBar.setRollover(true);

        StatusjTextField.setEditable(false);
        StatusjTextField.setText("Disconnected");
        StatusjTextField.setFocusable(false);
        StatusjToolBar.add(StatusjTextField);

        getContentPane().add(StatusjToolBar, java.awt.BorderLayout.SOUTH);

        ActionjMenu.setText("Action");
        ActionjMenu.setEnabled(false);

        LogoutjMenuItem.setText("Logout");
        ActionjMenu.add(LogoutjMenuItem);

        ActionjMenuBar.add(ActionjMenu);

        setJMenuBar(ActionjMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectAllUser(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SelectAllUser
        UsernamejTextField.selectAll();
}//GEN-LAST:event_SelectAllUser

    private void SelectAllPass(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SelectAllPass
        jPasswordField.selectAll();
    }//GEN-LAST:event_SelectAllPass

    private void LoginjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginjButtonActionPerformed
        String username = UsernamejTextField.getText();
        String password = new String(jPasswordField.getPassword());
    }//GEN-LAST:event_LoginjButtonActionPerformed

    /**
     * Initialize IM client state
     */
    private void initClientIM() {
        SetLogout();
    }

    /**
     * Set IM client to logout state
     */
    private void SetLogout() {
        LogoutjPanel.setVisible(true);
        LoginjPanel.setVisible(false);
        ActionjMenuBar.setEnabled(false);
    }

    /**
     * Set IM client to login state
     */
    private void SetLogin() {
        LogoutjPanel.setVisible(false);
        LoginjPanel.setVisible(true);
        ActionjMenuBar.setEnabled(true);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientIM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ActionjMenu;
    private javax.swing.JMenuBar ActionjMenuBar;
    private javax.swing.JPanel ButtonjPanelR;
    private javax.swing.JPanel ButtonjPanelR1;
    private javax.swing.JButton ClearjButtonR;
    private javax.swing.JButton ClearjButtonR1;
    private javax.swing.JPanel ContentjPanel;
    private javax.swing.JButton LoginjButton;
    private javax.swing.JFrame LoginjFrame;
    private javax.swing.JPanel LoginjPanel;
    private javax.swing.JMenuItem LogoutjMenuItem;
    private javax.swing.JPanel LogoutjPanel;
    private javax.swing.JButton OKjButtonR;
    private javax.swing.JButton OKjButtonR1;
    private javax.swing.JLabel PasswordjLabel;
    private javax.swing.JPanel RContentjPanel;
    private javax.swing.JPanel RContentjPanel1;
    private javax.swing.JFrame RegisterjFrame;
    private javax.swing.JTextField StatusjTextField;
    private javax.swing.JToolBar StatusjToolBar;
    private javax.swing.JLabel UsernamejLabel;
    private javax.swing.JTextField UsernamejTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
