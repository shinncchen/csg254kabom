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
import server.transport.Sender;

/**
 *
 * @author Raghuram
 */
public class Rid220 extends Request {
    
    public Rid220() {
        super(Request.RID_220);
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
            
            oos.writeInt(Request.RID_220);
            oos.writeObject(data[0]); //the challenge
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            // Send the message to the appropriate IP:PORT
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            
            System.out.println("ipaddress sent to: " + userInfo.getIpAdress() + " and port: " + userInfo.getPort());
            
            // Change state of user to RID 220
            userInfo.setCurrentState(UserInfo.STATE_RID220);
            
            userInfo.activateTimeout(Request.RID_220);
            
            System.out.println("sent 220, set timeout and changed state...");
            
        } catch (Exception ex) {
            
        }
    }
    
}
