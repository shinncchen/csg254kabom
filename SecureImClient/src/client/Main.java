package client;

import client.gui.ClientIM;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * To start the client using default settings, just call main without arguments.
 * To start the client with custom settings, use: 
 * arg1 - Server IP
 * arg2 - Server Port
 * arg3 - Local Port
 */
public class Main {

    public static void main(String[] args) {
        
        if(args != null && args.length == 3) {
            try {
                ChatMaster.SERVER_IP = args[0];
                ChatMaster.SERVER_PORT = Integer.parseInt(args[1]);
                ChatMaster.LOCAL_PORT = Integer.parseInt(args[2]);
            } catch (Exception e) {
                System.out.println("Invalid network details.");
                e.printStackTrace();
                System.exit(0);
            }
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientIM().setVisible(true);
            }
        });
    }
}