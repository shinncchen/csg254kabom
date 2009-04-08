/**
 * LOGIN protocol - Rid250
 *
 * @author HuskyHackers
 */

package server.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import server.ChatMaster;
import server.UserInfo;
import server.security.Security;

public class Rid250 extends Request {

    /**
     * Rid250 constructor
     */
    public Rid250() {
        super(Request.RID_250);
    }

    /**
     * RID_250 never send a Request
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    /**
     * Processing a Request RID_250 from a client
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void processRequest(UserInfo userInfo, Object[] data) {

        // if challenge response t2 is right, log the user in
        if (super.requestData != null && requestData.length > 0) {

            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;

            try {
                ois = new ObjectInputStream(bais);
                
                //get rid of RID, no need
                ois.readInt();
                
                //check whether timestamp matches
                byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[]) ois.readObject());

                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                ObjectInputStream ois2 = new ObjectInputStream(bais2);

                //if timestamp = my timestamp. log em in
                if (Arrays.equals(userInfo.getTimeT2(), (byte[]) ois2.readObject())) {
                    
                    userInfo.deactivateTimeout();
                    
                    System.out.println("User: " + userInfo.getUsername() + " just got logged in");
                    userInfo.setLoggedIn(true);
                }
                
                ois2.close();
                
                userInfo.setCurrentState(UserInfo.STATE_LOGIN);
            } //end of try catch block
            catch (Exception e) {
                System.err.println("Couldn't parse objects, ignoring");
                e.printStackTrace();
            }

        }
    }
}
