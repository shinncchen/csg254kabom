/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdulla
 */
public class Rid240 extends Request {
    
    public Rid240() {
        super(Request.RID_240);
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
					byte[] decryptedMsg = new Security().RSADecrypt(ChatMaster.clientData.getPrivateKey(), encryptedMsg);
					
	                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
	                ObjectInputStream ois2 = new ObjectInputStream(bais2);
					
	                //if username matches
	                if (ChatMaster.clientData.getUsername().equals((String)ois2.readObject())) {
	                	//if our own sent timestamp matches
	                	if (Arrays.equals(ChatMaster.clientData.getTimeT1(), (byte[])ois2.readObject())) {
	                		ChatMaster.clientData.setTimeT2((byte[])ois2.readObject());
	                		ChatMaster.clientData.setSessionKey((byte[])ois2.readObject());
	                		
	                		ois2.close();
	                		//create Rid250 to send back to server
	                		
	                		Request rid250 = new Rid250();
	                		rid250.sendRequest(null);
	                		
	                	}
	                
	                }
	                
	                
				} catch (Exception e) { }
				
            }
        }
    }

    public void sendRequest(Object[] data) {
    }
    
}
