/**
 * LOGIN protocol - RID 210
 *
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import client.transport.Sender;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Rid210 extends Request {

    /**
     * Rid210 constructor
     */
    public Rid210() {
        super(Request.RID_210);
    }

    /**
     * Sending a Request RID_210 to the server
     * @param data
     */
    public void sendRequest(Object[] data) {
        
        // Create a new sender object
        Sender sender = new Sender();
        
        // Init streams for data output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        // Array that stores bytes that are to be sent
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            // Check ICD for data
            oos.writeInt(Request.RID_210);
            oos.writeObject("LOGIN");
            oos.writeInt(ChatMaster.LOCAL_PORT);
            
            oos.flush();
            
            // Retrieve message to be sent
            message = baos.toByteArray();
            oos.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setErrorVisible();
        }
        
        try {
            // Send the message
            sender.send(message);
            
            // Change state to 210
            ChatMaster.changeState(ChatMaster.STATE_RID210);
            
            activateTimeout();
            
            System.out.println("sent 210, timeout init and changed state...");
        } catch (Exception ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setErrorVisible();
        }
    }

    /**
     * RID_210 never process a Request
     * @param data
     */
    public void processRequest(Object[] data) {
    }
    
}
