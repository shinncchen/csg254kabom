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
public class Rid310 extends Request {

    public Rid310() {
        super(Request.RID_210);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        if(super.requestData != null && requestData.length > 0) {
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;

            String string = null;
            int port = 0;

            try {
                 ois = new ObjectInputStream(bais);

                 ois.readInt();
                 string = (String) ois.readObject();
                 port = ois.readInt();

                 System.out.println("string rxd: "+string);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            if(string.equalsIgnoreCase("Login") && port!=0) {

                Security security = new Security();
                byte[] challenge = security.getTimestamp();

                System.out.println("sending challenge to user");
                Request rid220 = new Rid220();

                userInfo.setChallenge(challenge);
                userInfo.setCurrentState(UserInfo.STATE_RID220);
                userInfo.setPort(port);

                rid220.sendRequest(userInfo, new Object[] {challenge});
            }

        }
    }

}
