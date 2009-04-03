/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;

public class Rid720 extends Request {

    public Rid720() {
        super(Request.RID_720);
    }


    public void processRequest(Object[] data) {
        if(super.senderIp.equalsIgnoreCase(ChatMaster.SERVER_IP)) {
            if(super.requestData != null && super.requestData.length > 0) {
                ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
                ObjectInputStream oia = null;
                try {
                	oia = new ObjectInputStream(bais);
                } catch (IOException e) { }
                byte[] encryptedMsg = null;
                try {
                	oia.readInt();
					encryptedMsg = (byte[])oia.readObject();

					//decrypt the msg using our private key
					byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedMsg);

	                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
	                ObjectInputStream ois2 = new ObjectInputStream(bais2);

	                //if timestamp matches
	                if (Arrays.equals(ChatMaster.clientData.getTimeT1(), (byte[])ois2.readObject())) {
                        // user is logout
                        ChatMaster.clientData.setIsLogin(false);
                        System.out.println(ChatMaster.clientData.getUsername()+" is logout.");
                        // im client is set on logout state
                        ChatMaster.clientIM.setLogoutState();
                        ChatMaster.changeState(ChatMaster.STATE_INITAL);
                        ois2.close();
	                }
				} catch (Exception e) { }
            }
        }
    }

    public void sendRequest(Object[] data) {
    }

}
