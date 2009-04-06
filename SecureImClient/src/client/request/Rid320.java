/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;

public class Rid320 extends Request {

    public Rid320() {
        super(Request.RID_320);
    }

    public void processRequest(Object[] data) {

        // Decrypt response, check T1, display user list
        if (super.senderIp.equalsIgnoreCase(ChatMaster.SERVER_IP)) {
            if (super.requestData != null && super.requestData.length > 0) {

                ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
                ObjectInputStream oia = null;

                try {
                    oia = new ObjectInputStream(bais);
                } catch (IOException e) {
                }
                
                byte[] encryptedMsg = null;
                
                try {
                    oia.readInt();
                    
                    encryptedMsg = (byte[]) oia.readObject();

                    //decrypt the msg using our private key
                    byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedMsg);

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //if timestamp matches
                    if (Arrays.equals(ChatMaster.clientData.getTimeT1(), (byte[]) ois2.readObject())) {
                        
                        // deactivate timeout for RID_310
                        deactivateTimeout();
                    
                        // get userlist
                        String[] userlist = ((String) ois2.readObject()).split(",");
                        
                        // set userlist
                        ChatMaster.clientIM.setUserList(userlist);
                        
                        // display the user list
                        ChatMaster.clientIM.displayUserList();

                        ois2.close();
                        
                        // set the state back to login
                        ChatMaster.changeState(ChatMaster.STATE_LOGIN);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void sendRequest(Object[] data) {
    }
}
