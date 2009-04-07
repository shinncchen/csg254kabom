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
public class Rid230 extends Request {
    
    public Rid230() {
        super(Request.RID_230);
    }

    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_230);
            
            //challenge
            oos.writeObject(data[0]);
            
            ByteArrayOutputStream ebaos = new ByteArrayOutputStream();
            ObjectOutputStream eoos = new ObjectOutputStream(ebaos);
            
            //write timestamp
            ChatMaster.clientData.setTimeT1(new Security().getTimestamp());
            eoos.writeObject(ChatMaster.clientData.getTimeT1());
            //Ua
            eoos.writeObject(ChatMaster.clientData.getUsername());
            //PKa
            eoos.writeObject(ChatMaster.clientData.getPublicKey());
            //h(pwd)
            eoos.writeObject(ChatMaster.clientData.getPwdHash());
            eoos.flush();
            
            oos.writeObject(new Security().RSAEncrypt(ChatMaster.publicKeyServer, ebaos.toByteArray()));
            eoos.close();
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setError();
        }
        
        try {
            sender.send(message);

            ChatMaster.changeState(ChatMaster.STATE_RID230);
            System.out.println("sent 230 and changed state...");
            
            activateTimeout();
            
        } catch (Exception ex) {
            
        }
    }

    public void processRequest(Object[] data) {
    }
    
}