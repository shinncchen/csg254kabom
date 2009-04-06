/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.io.*;
import java.util.*;
import server.*;
import server.security.*;

/**
 *
 * @author Raghuram
 */
public class Rid710 extends Request {

    public Rid710() {
        super(Request.RID_710);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        // Check Ua and Ip match, check Ua inside and outside match, check T1 within skew
        if(super.requestData != null && requestData.length > 0) {
            
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;
            
            try {
            	ois = new ObjectInputStream(bais);
                
            	//get rid of RID, no need
            	ois.readInt();
                ois.readObject();
                
                // get username plain text
                String usernamePlain = (String)ois.readObject();

                // decrypt if user is found and login to the server
                if(userInfo.getUsername().equals(usernamePlain) && userInfo.isLoggedIn()) {
                    
                    byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[])ois.readObject());

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);
                    
                    // get username encrypted
                    String usernameCrypt = (String)ois2.readObject();
                    
                    // check if user plaintext == user encrypted
                    if(usernamePlain.equals(usernameCrypt)) {
                        // get time stamp
                        userInfo.setTimeT1((byte[])ois2.readObject());
                        
                        //if skew is correct, get list of user online
                        if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta())) {
                            
                            // remove user from the ChatMaster.users
                            userInfo = (UserInfo) ChatMaster.users.remove(userInfo.getIpAdress());
                            
                            System.out.println("The user "+userInfo.getUsername()+" was removed.");
                            
                            // create request 720
                            Request rid720 = new Rid720();
                            rid720.sendRequest(userInfo, null);
                        }
                    }
                }
            } //end of try catch block
            catch (Exception e) { System.err.println("Couldn't parse objects, ignoring"); e.printStackTrace(); }
        }
    }
}