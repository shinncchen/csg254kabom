/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.security.Security;
import client.transport.Sender;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Abdulla
 */
public class Rid250 extends Request {
    
    public Rid250() {
        super(Request.RID_250);
    }

    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_250);
            //oos.writeObject(new Security().AESEncrypt(ChatMaster.clientData.getSessionKey(), ChatMaster));
            
            ByteArrayOutputStream ebaos = new ByteArrayOutputStream();
            ObjectOutputStream eoos = new ObjectOutputStream(ebaos);
            
            //write timestamp
            eoos.writeObject(ChatMaster.clientData.getTimeT2());


            eoos.flush();
            //encrypting
            oos.writeObject(new Security().AESEncrypt(ChatMaster.clientData.getSessionKey(), ebaos.toByteArray()));
            eoos.close();
            
            oos.flush();
            
            message = baos.toByteArray();

            //user logged in, authenticated
            ChatMaster.clientData.setIsLogin(true);
            // switch IM panel to login
            ChatMaster.clientIM.setLoginState();

            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message);
            
            ChatMaster.changeState(ChatMaster.STATE_RID250);

            System.out.println("sent 250 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {
            
        }
    }

    public void processRequest(Object[] data) {
    }
    
}