/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientIM.java
 *
 * Created on Mar 14, 2009, 12:25:12 PM
 */

/**
 *
 * @author Shinn Chyang Chen
 */
public class ClientIM extends javax.swing.JFrame {

    /** Creates new form ClientIM */
    public ClientIM() {
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

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        LoginjMenuItem = new javax.swing.JMenuItem();
        RefreshjMenuItem = new javax.swing.JMenuItem();
        ExitjMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        getContentPane().add(jMenuBar1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        LoginjMenuItem.setText("Login");
        jMenu1.add(LoginjMenuItem);

        RefreshjMenuItem.setText("Refresh contact list");
        jMenu1.add(RefreshjMenuItem);

        ExitjMenuItem.setText("Exit");
        jMenu1.add(ExitjMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JMenuItem ExitjMenuItem;
    private javax.swing.JMenuItem LoginjMenuItem;
    private javax.swing.JMenuItem RefreshjMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
