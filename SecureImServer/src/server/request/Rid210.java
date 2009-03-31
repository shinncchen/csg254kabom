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
        if(super.requestData != null && requestData.length > 0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;
            
            String string = null;
            try {
                 ois = new ObjectInputStream(bais);
                 
                 ois.readInt();
                 string = (String) ois.readObject();
                 System.out.println("string rxd: "+string);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            if(string.equalsIgnoreCase("Login")) {

                Security security = new Security();
                byte[] challenge = security.getTimestamp();
                //String challenge = "CHALLENGE";

                System.out.println("sending challenge to user RID 220");
                Request rid220 = new Rid220();
                rid220.sendRequest(userInfo, new Object[] {challenge});

                userInfo.setChallenge(challenge);
                userInfo.setCurrentState(UserInfo.STATE_RID220);
            }
            
        }
    }
    
}
