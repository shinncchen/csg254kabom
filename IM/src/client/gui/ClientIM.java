package client.gui;

import java.awt.CardLayout;
import javax.swing.*;
import client.*;

public class ClientIM extends javax.swing.JFrame {

    private String mUsername = "";
    private ChatMaster chatmaster = new ChatMaster();

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

        UserListjFrame = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserListjList = new javax.swing.JList();
        ContentjPanel = new javax.swing.JPanel();
        LoginjPanel = new javax.swing.JPanel();
        UserListjButton = new javax.swing.JButton();
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

        UserListjFrame.setAlwaysOnTop(true);
        UserListjFrame.setMinimumSize(new java.awt.Dimension(150, 250));
        UserListjFrame.setResizable(false);
        UserListjFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                UserListClosingAction(evt);
            }
        });

        UserListjList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        UserListjList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UserListjList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserSelectedAction(evt);
            }
        });
        jScrollPane1.setViewportView(UserListjList);

        UserListjFrame.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IM Client");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(250, 400));
        setName("IM Window"); // NOI18N

        ContentjPanel.setLayout(new java.awt.CardLayout());

        LoginjPanel.setLayout(new java.awt.GridBagLayout());

        UserListjButton.setText("User List");
        UserListjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserListjButtonActionPerformed(evt);
            }
        });
        LoginjPanel.add(UserListjButton, new java.awt.GridBagConstraints());

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
                selectAllUser(evt);
            }
        });
        jPanel7.add(UsernamejTextField);

        jPasswordField.setText("password");
        jPasswordField.setMinimumSize(new java.awt.Dimension(15, 20));
        jPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectAllPass(evt);
            }
        });
        jPanel7.add(jPasswordField);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        LoginjButton.setText("Login");
        LoginjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginAction(evt);
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

        ActionjMenuBar.setEnabled(false);

        ActionjMenu.setText("Action");
        ActionjMenu.setEnabled(false);

        LogoutjMenuItem.setText("Logout");
        LogoutjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutAction(evt);
            }
        });
        ActionjMenu.add(LogoutjMenuItem);

        ActionjMenuBar.add(ActionjMenu);

        setJMenuBar(ActionjMenuBar);

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Initialize IM client state
     */
    private void initClientIM() {
        setLogoutState();
    }

    /**
     * Select all user textbox
     * @param evt
     */
    private void selectAllUser(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectAllUser
        UsernamejTextField.selectAll();
}//GEN-LAST:event_selectAllUser

    /**
     * Select all password textbox
     * @param evt
     */
    private void selectAllPass(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_selectAllPass
        jPasswordField.selectAll();
}//GEN-LAST:event_selectAllPass

    /**
     * Set IM client to logout state
     */
    private void setLogoutState() {
        this.mUsername = "";
        LogoutjPanel.setVisible(true);
        LoginjPanel.setVisible(false);
        ActionjMenuBar.setEnabled(false);
        ActionjMenu.setEnabled(false);
        StatusjTextField.setText("Disconnected");
    }

    /**
     * Set IM client to login state
     */
    private void setLoginState() {
        LogoutjPanel.setVisible(false);
        LoginjPanel.setVisible(true);
        ActionjMenuBar.setEnabled(true);
        ActionjMenu.setEnabled(true);
        StatusjTextField.setText("Connected");

        // TODO - need function to retrieve user list from server
    
        String[] users = new String[2];
        users[0] = "sdfdsf";
        users[1] = "ytkuypou";

        // populate users on login panel
        populateUserList(users);
    }


    /**
     * Login to the IM
     * @param evt
     */
    private void loginAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAction
        String username = UsernamejTextField.getText();
        String password = new String(jPasswordField.getPassword());

        // TODO - test username + password (RID_20, RID_21, RID_22)

        // switch cardlayout to login panel
        CardLayout cl = (CardLayout)ContentjPanel.getLayout();

        this.mUsername = username;
        cl.show(ContentjPanel, "loginCard");
        setLoginState();
}//GEN-LAST:event_loginAction


    /**
     * Logout from the IM
     * @param evt
     */
    private void logoutAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutAction
        CardLayout cl = (CardLayout)ContentjPanel.getLayout();

        // TODO - need to send logout request (RID_80)

        // switch cardlayout to logout panel
        cl.show(ContentjPanel, "logoutCard");
        setLogoutState();
}//GEN-LAST:event_logoutAction


    /**
     * Display user list in window + disable IM window focus
     * @param evt
     */
    private void UserListjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserListjButtonActionPerformed
        // TODO - need function to retrieve user list from server
        String[] users = new String[2];
        users[0] = "sdfdsf";
        users[1] = "ytkuypou";

        // populate users on login panel
        populateUserList(users);
        UserListjFrame.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_UserListjButtonActionPerformed

    /**
     * Enable IM window focus
     * @param evt
     */
    private void UserListClosingAction(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_UserListClosingAction
        this.setEnabled(true);
    }//GEN-LAST:event_UserListClosingAction

    private void UserSelectedAction(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserSelectedAction

    }//GEN-LAST:event_UserSelectedAction


    /**
     * Create a button for a single user
     * @param guestusername
     * @return
     */
    private JButton createUserButton(String guestusername) {
        JButton userjButton = new JButton();

        // set button properties
        userjButton.setText(guestusername);
        userjButton.setActionCommand(guestusername);

        // add action listener for the button to create a chat window
        userjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createChatWindow(mUsername, evt.getActionCommand());
            }
        });

        return userjButton;
    }


    /**
     * Create buttons to the IM given a list of user
     * @param userlist
     */
    private void populateUserList(String[] userlist) {
        UserListjList.setListData(userlist);


    }


    /**
     * Create a chat window between user and the guest user
     * @param thisuser
     * @param guestuser
     */
    private void createChatWindow(String thisuser, String guestuser) {
        // TODO - need to perform (RID_50, RID_60, RID_61)
        ClientChatWindow chatwindow = new ClientChatWindow(thisuser, guestuser);
        chatwindow.setVisible(true);
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
    private javax.swing.JPanel ContentjPanel;
    private javax.swing.JButton LoginjButton;
    private javax.swing.JPanel LoginjPanel;
    private javax.swing.JMenuItem LogoutjMenuItem;
    private javax.swing.JPanel LogoutjPanel;
    private javax.swing.JLabel PasswordjLabel;
    private javax.swing.JTextField StatusjTextField;
    private javax.swing.JToolBar StatusjToolBar;
    private javax.swing.JButton UserListjButton;
    private javax.swing.JFrame UserListjFrame;
    private javax.swing.JList UserListjList;
    private javax.swing.JLabel UsernamejLabel;
    private javax.swing.JTextField UsernamejTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
