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

                Iterator<String> keyIter = (ChatMaster.users.keySet()).iterator();
                boolean isUserFound = false;
                String userIP = null;

                while (keyIter.hasNext() && !isUserFound) {
                    // for each entry
                    userIP = keyIter.next();
                    // get userInfo
                    userInfo = (UserInfo) ChatMaster.users.get(userIP);

                    // check if username is Login
                    if (usernamePlain.equalsIgnoreCase(userInfo.getUsername())){
                        System.out.println("username:" +userInfo.getUsername());
                        isUserFound = true;
                    }
                }

                // decrypt if user is found and login to the server
                if(isUserFound && userInfo.isLoggedIn()) {
                    byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[])ois.readObject());

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);
                    
                    // get username encrypted
                    String usernameCrypt = (String)ois2.readObject();
                    // check if user plaintext == user encrypted
                    if(usernamePlain.equals(usernameCrypt)) {
                        // get time stamp
                        userInfo.setTimeT1((byte[])ois2.readObject());
                        //calcualte delta for that client
                        userInfo.setDelta(new Security().clcDelta(new Security().getTimestamp(), userInfo.getTimeT1()));
                        //if skew is correct, get list of user online
                        if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta())) {
                            keyIter = (ChatMaster.users.keySet()).iterator();
                            userIP = null;
                            StringBuffer userList = new StringBuffer();

                            while (keyIter.hasNext()) {
                                // for each entry
                                userIP = keyIter.next();
                                // get userInfo
                                userInfo = (UserInfo) ChatMaster.users.get(userIP);
                                // check if username is Login and append to user list
                                if (userInfo.isLoggedIn()){
                                    userList.append(userInfo.getUsername());
                                    if(keyIter.hasNext()) {userList.append(",");}
                                }
                            }
                            // create request 320
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