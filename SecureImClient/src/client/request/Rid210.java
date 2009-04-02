/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.transport.Sender;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Raghuram
 */
public class Rid210 extends Request {
    
    public Rid210() {
        super(Request.RID_210);
    }

    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //DataOutputStream dos = new DataOutputStream(baos);
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_210);
            oos.writeObject("LOGIN");
            oos.writeInt(ChatMaster.LOCAL_PORT);
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message);
            
            ChatMaster.changeState(ChatMaster.STATE_RID210);
            System.out.println("sent 210 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {
            
        }
    }

    public void processRequest(Object[] data) {
    }
    
}
