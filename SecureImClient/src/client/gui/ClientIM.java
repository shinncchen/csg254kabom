package client.gui;

import java.awt.*;
import client.*;
import client.datastructure.*;
import client.event.GuiEvent;

public class ClientIM extends javax.swing.JFrame {

    //LOGIN
    public static final int STATE_RID210 = 210;
    //LIST
    public static final int STATE_RID310 = 310;
    //PERMIT
    public static final int STATE_RID410 = 410;
    //P2P AUTHENTICATION
    public static final int STATE_RID510 = 510;
    //P2P MESSAGE EXCHANGE
    public static final int STATE_RID610 = 610;

    private String[] userList = null;


    /** Creates new form ClientIM */
    public ClientIM() {
        initComponents();
        // set ClientIM to logout state
        setLogoutState();
        // initialize ChatMaster
        ChatMaster.initialize();
        // set ClientIM object to ChatMaster
        ChatMaster.setClientIMObject(this);
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
        jPanel2 = new javax.swing.JPanel();
        PickUserjButton = new javax.swing.JButton();
        ContentjPanel = new javax.swing.JPanel();
        LoginjPanel = new javax.swing.JPanel();
        UserListjButton = new javax.swing.JButton();
        LogoutjPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ErrorjTextField = new javax.swing.JTextField();
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
        jScrollPane1.setViewportView(UserListjList);

        UserListjFrame.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        PickUserjButton.setText("OK");
        PickUserjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PickUserjButtonActionPerformed(evt);
            }
        });
        jPanel2.add(PickUserjButton);

        UserListjFrame.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

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

        ErrorjTextField.setEditable(false);
        ErrorjTextField.setFont(new java.awt.Font("Tahoma", 1, 11));
        ErrorjTextField.setForeground(new java.awt.Color(255, 0, 51));
        ErrorjTextField.setText("Invalid Username or password");
        ErrorjTextField.setBorder(null);
        jPanel1.add(ErrorjTextField, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        UsernamejLabel.setText("Username");
        jPanel6.add(UsernamejLabel);

        PasswordjLabel.setText("Password");
        jPanel6.add(PasswordjLabel);

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        UsernamejTextField.setText("Raghu");
        UsernamejTextField.setMinimumSize(new java.awt.Dimension(50, 20));
        UsernamejTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectAllUser(evt);
            }
        });
        jPanel7.add(UsernamejTextField);

        jPasswordField.setText("ok");
        jPasswordField.setMinimumSize(new java.awt.Dimension(50, 20));
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
        CardLayout cl = (CardLayout)ContentjPanel.getLayout();

        // switch cardlayout to logout panel
        cl.show(ContentjPanel, "logoutCard");
        // disable menubar
        ActionjMenu.setEnabled(false);
        // set status to disconnect
        StatusjTextField.setText("Disconnected");
        // hide error text box
        ErrorjTextField.setVisible(false);
        // initialize userlist
        userList = null;
    }

    /**
     * Set IM client to login state
     */
    private void setLoginState() {
        CardLayout cl = (CardLayout)ContentjPanel.getLayout();

        // switch cardlayout to login panel
        cl.show(ContentjPanel, "loginCard");
        // enable menu bar
        ActionjMenu.setEnabled(true);
        // set status to connected
        StatusjTextField.setText("Connected");
        // hide error text box
        ErrorjTextField.setVisible(false);
    }

    /**
     * Login action for IM
     * @param evt
     */
    private void loginAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAction
        String[] parameter = new String[3];
        parameter[0] = UsernamejTextField.getText();
        parameter[1] = new String(jPasswordField.getPassword());

        // create gui event for login
        GuiEvent guiEvent = new GuiEvent();
        // set parameter for the gui event
        guiEvent.setParameter(parameter);
        // set ChatMaster state for LOGIN
        ChatMaster.changeState(STATE_RID210);
        ChatMaster.handle(guiEvent);

        // TODO - need condition to check if login
        if(true) {
            setLoginState();
        }
        else {
            ErrorjTextField.setVisible(true);
        }
}//GEN-LAST:event_loginAction

    /**
     * Logout action for IM
     * @param evt
     */
    private void logoutAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutAction
        // TODO - need to send logout request (RID_710)
        // set logout state for IM
        if(true) {
            setLogoutState();
        }
}//GEN-LAST:event_logoutAction

    /**
     * Display user list in window + disable IM window focus
     * @param evt
     */
    private void UserListjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserListjButtonActionPerformed
        // create gui event for login
        GuiEvent guiEvent = new GuiEvent();
        // set parameter for the gui event
        guiEvent.setParameter(null);
        // set ChatMaster state for LOGIN
        ChatMaster.changeState(STATE_RID310);
        ChatMaster.handle(guiEvent);

        // populate users on login panel
        UserListjList.setListData(userList);
        // display user list window
        UserListjFrame.setVisible(true);
        // disable IM window to be focused
        this.setEnabled(false);
    }//GEN-LAST:event_UserListjButtonActionPerformed

    /**
     * set the userlist for the IM
     * @param userlist
     */
    public void setUserList(String[] userlist) {
        this.userList = userlist;
    }

    /**
     * Enable IM window focus
     * @param evt
     */
    private void UserListClosingAction(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_UserListClosingAction
        // enable IM window to be focused
        this.setEnabled(true);
    }//GEN-LAST:event_UserListClosingAction

    /**
     * Action when user request a chat with another user.
     * It creates a new chat window
     * @param evt
     */
    private void PickUserjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PickUserjButtonActionPerformed
        String guestuser = (String)UserListjList.getSelectedValue();

        if(!guestuser.trim().equals("")) {
            // TODO - client select an user to talk (RID_410)
            //        get ticket from the server

            UserListjFrame.setVisible(false);
            createChatWindow(ChatMaster.clientData.getUsername(), guestuser);
            this.setEnabled(true);
        }
    }//GEN-LAST:event_PickUserjButtonActionPerformed

    /**
     * Create a chat window between user and the guest user
     * @param thisuser
     * @param guestuser
     */
    private void createChatWindow(String thisuser, String guestuser) {
        // TODO - need to perform for P2P authentification (RID_510, RID_530)
        ClientChatWindow chatwindow = new ClientChatWindow(thisuser, new PeerDetails());
        chatwindow.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ActionjMenu;
    private javax.swing.JMenuBar ActionjMenuBar;
    private javax.swing.JPanel ContentjPanel;
    private javax.swing.JTextField ErrorjTextField;
    private javax.swing.JButton LoginjButton;
    private javax.swing.JPanel LoginjPanel;
    private javax.swing.JMenuItem LogoutjMenuItem;
    private javax.swing.JPanel LogoutjPanel;
    private javax.swing.JLabel PasswordjLabel;
    private javax.swing.JButton PickUserjButton;
    private javax.swing.JTextField StatusjTextField;
    private javax.swing.JToolBar StatusjToolBar;
    private javax.swing.JButton UserListjButton;
    private javax.swing.JFrame UserListjFrame;
    private javax.swing.JList UserListjList;
    private javax.swing.JLabel UsernamejLabel;
    private javax.swing.JTextField UsernamejTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
