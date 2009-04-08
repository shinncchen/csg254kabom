/**
 * PERMIT protocol - Rid410
 *
 * @author HuskyHackers
 */
package server.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import server.ChatMaster;
import server.UserInfo;
import server.security.Security;

/**
 *
 * @author HuskyHackers
 */
public class Rid410 extends Request {

    /**
     * Rid410 constructor
     */
    public Rid410() {
        super(Request.RID_410);
    }

    /**
     * RID_410 never send a Request
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void sendRequest(UserInfo userInfo, Object[] data) {
    }

    /**
     * Processing a Request RID_410 from a client
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void processRequest(UserInfo userInfo, Object[] data) {

        // Recive permit request, check if user and ip match, decrypt payload, 
        // check T1 skew, check Ua inside and outside, check if Ub is online, 
        // check if Ua and Ub aren't same ----- then create response
        if (super.requestData != null && requestData.length > 0) {

            ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
            ObjectInputStream ois = null;

            try {
                ois = new ObjectInputStream(bais);

                //get rid of RID and PERMIT
                ois.readInt();
                ois.readObject();

                String usr = (String) ois.readObject();

                // if user exists and is logged in
                if (userInfo.getUsername().equals(usr) && userInfo.isLoggedIn()) {

                    // decrypt payload
                    byte[] decryptedMsg = new Security().AESDecrypt(userInfo.getSessionKey(), (byte[]) ois.readObject());

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //store T1
                    userInfo.setTimeT1((byte[]) ois2.readObject());

                    //if skew is correct
                    if (new Security().isTimeValid(new Security().getTimestamp(), userInfo.getTimeT1(), userInfo.getDelta())) {
                        String userBIp = null;
                        UserInfo tmp = null;

                        // if user inside and outside are same
                        if (usr.equals((String) ois2.readObject())) {

                            String Ub = (String) ois2.readObject();
                            
                            // if Ua and Ub aren't same
                            if (! usr.equals(Ub)) {
                                
                                // retrieve Ub's IP from the hash map
                                Iterator<String> iterator = (ChatMaster.users.keySet()).iterator();
                                while (iterator.hasNext()) {
                                    tmp = (UserInfo) ChatMaster.users.get(iterator.next());
                                    if (Ub.equalsIgnoreCase(tmp.getUsername())) {
                                        userBIp = tmp.getIpAdress();
                                        break;
                                    }
                                }
                                
                                try {
                                    if (userBIp != null) {
                                        // get Ub's user info
                                        UserInfo userBInfo = (UserInfo) ChatMaster.users.get(userBIp);
                                        
                                        if (userBInfo.isLoggedIn()) {
                                            
                                            // create and send RID 420
                                            Request rid420 = new Rid420();
                                            Object[] objects = new Object[1];
                                            objects[0] = (UserInfo) userBInfo;
                                            
                                            rid420.sendRequest(userInfo, objects);
                                            
                                        } else {
                                            System.out.println("Ub is not online");
                                        }
                                    }
                                } catch (NullPointerException e) {
                                    System.out.println("user" + Ub + "is not online");
                                }
                            }
                        } else {
                            //ignore
                        } 

                    } else {
                        //ignore
                    } 

                }
            }
            catch (Exception e) {
                System.err.println("Couldn't parse objects, ignoring");
                e.printStackTrace();
            }

        }
    }
}
