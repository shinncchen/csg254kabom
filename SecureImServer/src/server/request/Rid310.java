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
public class Rid310 extends Request {

    public Rid310() {
        super(Request.RID_310);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        
        //Request received for user list, check Ua valid, both inside and outside, check time skew, and respond with the list
        if(super.requestData != null && requestData.length > 0) {
            
            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;
            
            try {
            	ois = new ObjectInputStream(bais);
                
            	//get rid of RID, no need
            	ois.readInt();
                
                // retrive list string
                ois.readObject();
                
                // get username plain text
                String usernamePlain = (String)ois.readObject();
                
                // check if user exists
                boolean userAndIpMatch = userInfo.getUsername().equals(usernamePlain);
                
                // decrypt if user is found and login to the server
                if(userAndIpMatch && userInfo.isLoggedIn()) {
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
                            
                            Iterator<String> keyIter = (ChatMaster.users.keySet()).iterator();
                            String userIP = null;
                
                            StringBuffer userList = new StringBuffer();

                            while (keyIter.hasNext()) {
                                // for each entry
                                userIP = keyIter.next();
                                
                                // get userInfo
                                UserInfo userTemp = (UserInfo) ChatMaster.users.get(userIP);
                                
                                // check if user is logged in and append each user to the list except himself
                                if (userTemp.isLoggedIn() && ! userTemp.getUsername().equals(usernamePlain)) {
                                    
                                    userList.append(userTemp.getUsername());
                                    
                                    if(keyIter.hasNext()) {userList.append(",");}
                                }
                            }
                            
                            // create and send request 320
                            Request rid320 = new Rid320();
                            Object[] objects = {userList.toString()};
                            rid320.sendRequest(userInfo, objects);
                        }
                    }
                }
            } //end of try catch block
            catch (Exception e) { System.err.println("Couldn't parse objects, ignoring"); e.printStackTrace(); }
        }
    }
}