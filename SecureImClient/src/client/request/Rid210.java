/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.transport.Sender;

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
        
        byte[] message = "LOGIN".getBytes();
        
        try {
            sender.send(message);
            
            ChatMaster.changeState(ChatMaster.STATE_RID210);
            
            //TODO: timeout setup
        } catch (Exception ex) {
            
        }
    }

    public void processRequest(Object[] data) {
    }
    
}
