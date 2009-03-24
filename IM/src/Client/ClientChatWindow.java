package Client;

public class ClientChatWindow extends javax.swing.JFrame {

    private StringBuffer mChatHistory = new StringBuffer();
    private String mGuestUser = "";
    private String mThisUser = "";

    /** Creates new form ClientChatWindow */
    public ClientChatWindow() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        UserjTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChatHistjTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MessagejTextArea = new javax.swing.JTextArea();
        SendjButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jToolBar2 = new javax.swing.JToolBar();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        UserjTextField.setEditable(false);
        UserjTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserjTextField.setText("Username");
        UserjTextField.setFocusable(false);
        jToolBar1.add(UserjTextField);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        ChatHistjTextArea.setColumns(20);
        ChatHistjTextArea.setEditable(false);
        ChatHistjTextArea.setRows(5);
        ChatHistjTextArea.setFocusable(false);
        jScrollPane1.setViewportView(ChatHistjTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        MessagejTextArea.setColumns(20);
        MessagejTextArea.setRows(5);
        jScrollPane2.setViewportView(MessagejTextArea);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        SendjButton.setText("Send");
        SendjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendjButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SendjButton, java.awt.BorderLayout.EAST);
        jPanel1.add(jSeparator1, java.awt.BorderLayout.PAGE_START);

        jToolBar2.setRollover(true);

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("Online");
        jToolBar2.add(jTextField1);

        jPanel1.add(jToolBar2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SendjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendjButtonActionPerformed
        String message = MessagejTextArea.getText();

        if(!message.equals("")) {
            AddChat(mThisUser, message);
        }
    }//GEN-LAST:event_SendjButtonActionPerformed

    public void ReceiveMessageAction(String message) {
        AddChat(mGuestUser, message);
    }

    private void AddChat(String user, String message) {
        mChatHistory.append(user+" > "+message+"\n");
        ChatHistjTextArea.setText(mChatHistory.toString());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientChatWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ChatHistjTextArea;
    private javax.swing.JTextArea MessagejTextArea;
    private javax.swing.JButton SendjButton;
    private javax.swing.JTextField UserjTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}