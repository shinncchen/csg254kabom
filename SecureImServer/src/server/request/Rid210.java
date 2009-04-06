/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import server.UserInfo;
import server.security.Security;

/**
 *
 * @author Raghuram
 */
public class Rid210 extends Request {
    
    public Rid210() {
        super(Request.RID_210);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        
        // If request data exists
        if(super.requestData != null && requestData.length > 0) {
            
            // Init streams to retreive data
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;
            
            String string = null;
            int port = 0;
            
            try {
                 ois = new ObjectInputStream(bais);
                 
                 // Check ICD for why data is retreived this way
                 ois.readInt();
                 string = (String) ois.readObject();
                 port = ois.readInt();
                 
                 System.out.println("string rxd: "+string);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            // if the user sends LOGIN, send challenge
            if(string.equalsIgnoreCase("Login") && port!=0) {

                // Create a challenge
                Security security = new Security();
                byte[] challenge = security.getTimestamp();

                System.out.println("sending challenge to user");
                
                // Create and send RID 220
                Request rid220 = new Rid220();

                // Check ICD
                userInfo.setChallenge(challenge);
                userInfo.setCurrentState(UserInfo.STATE_RID220);
                userInfo.setPort(port);
                
                rid220.sendRequest(userInfo, new Object[] {challenge});
            }
            
        }
    }
    
}
