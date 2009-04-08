/**
 * LOGIN protocol - Rid220
 * 
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import java.io.*;

public class Rid220 extends Request {

    /**
     * Rid220 constructor
     */
    public Rid220() {
        super(Request.RID_220);
    }

    /**
     * Processing a Request RID_220 from the server
     * @param data
     */
    public void processRequest(Object[] data) {
        if (super.senderIp.equalsIgnoreCase(ChatMaster.SERVER_IP)) {
            if (super.requestData != null && super.requestData.length > 0) {
                
                ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
                ObjectInputStream oia = null;
                
                try {
                    oia = new ObjectInputStream(bais);
                } catch (IOException e) {
                }
                
                byte[] challenge = null;
                try {
                    oia.readInt();
                    
                    // Read the challenge 
                    challenge = (byte[]) oia.readObject();
                    
                } catch (Exception e) {
                }
                
                if(challenge != null && challenge.length > 0) {
                    //valid request...
                    super.deactivateTimeout();
                    
                    // Create and send RID 230
                    Object[] objectsToSend = new Object[1];
                    objectsToSend[0] = challenge;
                    System.out.println("challenge = " + challenge);
                    Request rid230 = new Rid230();
                    rid230.sendRequest(objectsToSend);
                }
            }
        }
    }

    /**
     * RID_220 never send a Request
     * @param data
     */
    public void sendRequest(Object[] data) {
    }
}
