/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author Abdulla
 */
public class Rid420 extends Request {
    
    public Rid420() {
        super(Request.RID_420);
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
					
					//decrypt the msg using our session key
					byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedMsg);
					
	                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
	                ObjectInputStream ois2 = new ObjectInputStream(bais2);
					
	                //match T1
	                if (Arrays.equals(ChatMaster.peerData.getTimeT1(), (byte[])ois2.readObject())){
	                	//store Tk in peer details
	                	byte[] tT = (byte[])ois2.readObject();
	                	//check if same Ua
	                	if(ChatMaster.clientData.getUsername().equals((String)ois2.readObject())){
	                		if(ChatMaster.peerData.getUsername().equals((String)ois2.readObject())){
	                			ChatMaster.peerData.setTimeTk(tT);
	                			ChatMaster.peerData.setPeerIP((String)ois2.readObject());
	                			ChatMaster.peerData.setPeerPort(ois2.readInt());
	                			ChatMaster.peerData.setPeerPublicKey((byte[])ois2.readObject());
	                		}
	                	}
	                	
	                }
	                           	
                	//i have Ticket-To-B, IPb, Portb, PKb
                	//put these in a nice object[]
                	Object[] infoTo510 = new Object[1];
                	infoTo510[0] = (byte[])ois2.readObject();
                	//infoTo510[1] = (String)ChatMaster.peerData.getPeerIP();
                	//infoTo510[2] = (int)ChatMaster.peerData.getPeerPort();
                	//infoTo510[3] = ChatMaster.peerData.getPeerPublicKey();
                	
                	//create Rid510 to send P2P Auth.
                	Request rid510 = new Rid510();
                	rid510.sendRequest(infoTo510);
                	
	                
	                
				} catch (Exception e) { }
				
            }
        }
    }

    public void sendRequest(Object[] data) {
    }
    
}
