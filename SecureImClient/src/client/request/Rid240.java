/**
 * LOGIN protocol - Rid240
 *
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rid240 extends Request {

    /**
     * Rid240 constructor
     */
    public Rid240() {
        super(Request.RID_240);
    }

    /**
     * Processing a Request RID_240 from the server
     * @param data
     */
    public void processRequest(Object[] data) {

        // Check Ua, Challenge response T1, Check T2 in time skew, Retreive session key and store, send RID 250
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
                    byte[] decryptedMsg = new Security().RSADecrypt(ChatMaster.clientData.getPrivateKey(), encryptedMsg);

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //if username matches
                    if (ChatMaster.clientData.getUsername().equals((String) ois2.readObject())) {
                        
                        //if our own sent timestamp matches
                        if (Arrays.equals(ChatMaster.clientData.getTimeT1(), (byte[]) ois2.readObject())) {
                            
                            deactivateTimeout();
                            
                            ChatMaster.clientData.setTimeT2((byte[]) ois2.readObject());
                            ChatMaster.clientData.setSessionKey((byte[]) ois2.readObject());

                            ois2.close();
                            
                            //create Rid250 to send back to server

                            Request rid250 = new Rid250();
                            rid250.sendRequest(null);

                        }
                    }
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     * RID_240 never send a Request
     * @param data
     */
    public void sendRequest(Object[] data) {
    }
}
