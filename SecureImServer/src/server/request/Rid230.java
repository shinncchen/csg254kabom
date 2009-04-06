/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.request;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;

import server.ChatMaster;
import server.UserInfo;
import server.security.Security;

/**
 *
 * @author Abdulla
 */
public class Rid230 extends Request {

    public Rid230() {
        super(Request.RID_230);
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
        if (super.requestData != null && requestData.length > 0) {

            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;

            try {
                ois = new ObjectInputStream(bais);
                
                //get rid of RID, no need
                ois.readInt();

                //check challenge that was sent and received
                byte[] challenge = (byte[]) ois.readObject();
                if (Arrays.equals(challenge, userInfo.getChallenge())) {

                    // if challenge matches, cancel timeout
                    userInfo.deactivateTimeout();

                    // decrypt the received data
                    byte[] decryptedMsg = new Security().RSADecrypt(ChatMaster.privateKeyServer, (byte[]) ois.readObject());

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    // retrieve sender timestamp and store
                    userInfo.setTimeT1((byte[]) ois2.readObject());
                    
                    //calcualte delta for that client
                    userInfo.setDelta(new Security().clcDelta(new Security().getTimestamp(), userInfo.getTimeT1()));
                    
                    //if skew is correct
                    if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta())) {
                        
                        //set his username
                        userInfo.setUsername((String) ois2.readObject());
                        
                        //set his public key
                        userInfo.setPublicKey((byte[]) ois2.readObject());
                        
                        //check his hash password
                        byte[] hPass = (byte[]) ois2.readObject();
                        if (ChatMaster.usersDB.isPassCorrect(userInfo.getUsername(), hPass)) { 
                            
                            //if user authenticated send RID240
                            Request rid240 = new Rid240();
                            rid240.sendRequest(userInfo, null);
                            
                        } else {
                            
                            // if password is wrong, remove the user from the hash table
                            ChatMaster.users.remove(userInfo.getIpAdress());
                            
                            System.out.println("Password incorrect for user: " + userInfo.getUsername());
                        }

                    } else {
                        //ignore
                    }

                } else {
                    //ignore
                }
            }
            catch (Exception e) {
                System.err.println("Couldn't parse objects, ignoring");
                e.printStackTrace();
            }

        }
    }
}
