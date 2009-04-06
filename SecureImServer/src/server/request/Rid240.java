/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import server.ChatMaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.UserInfo;
import server.security.Security;
import server.transport.Sender;

/**
 *
 * @author Abdulla
 */
public class Rid240 extends Request {
    
    public Rid240() {
        super(Request.RID_240);
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_240);
            
            //encrypt data
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            //Ua
            oos2.writeObject(userInfo.getUsername());
            //T1
            oos2.writeObject(userInfo.getTimeT1());
            //T2
            userInfo.setTimeT2(new Security().getTimestamp());
            oos2.writeObject(userInfo.getTimeT2());
            //generate session key
            userInfo.setSessionKey(new Security().generateAESKey());
            oos2.writeObject(userInfo.getSessionKey());
            oos2.flush();
            
            //encrypt it
            oos.writeObject(new Security().RSAEncrypt(userInfo.getPublicKey(), baos2.toByteArray()));
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            
            userInfo.setCurrentState(UserInfo.STATE_RID240);
            userInfo.activateTimeout(Request.RID_240);
            
            System.out.println("sent 240 and changed state...");
        } catch (Exception ex) {
            
        }
    }
    
}