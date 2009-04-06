package client.request;

import client.ChatMaster;
import client.security.Security;
import client.transport.Sender;
import client.datastructure.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;


public class Rid610 extends Request {
    
    public Rid610() {
        super(Request.RID_610);
    }

    /*
     * This executes when this client wants to talk
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_610);
            // writing user
            oos.writeObject(ChatMaster.clientData.getUsername());
    
            ByteArrayOutputStream ebaos = new ByteArrayOutputStream();
            ObjectOutputStream eoos = new ObjectOutputStream(ebaos);

            //set timestamp T1
            ChatMaster.clientData.setTimeT1(new Security().getTimestamp());
            //package T1
            eoos.writeObject(ChatMaster.clientData.getTimeT1());
            if (data.length==1) {
                //package message
                eoos.writeObject((byte[])data[0]);
            }
            else { System.out.println("Error formatting RID510 with empty data"); }
            eoos.flush();

            // write encrypted(T1 + message) using aes session key
            oos.writeObject(new Security().AESEncrypt(ChatMaster.peerData.getPeerSessionKey(), ebaos.toByteArray()));
            // write hash of message
            oos.writeObject(new Security().getHash((byte[])data[0]));
            oos.flush();

            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());
            
            ChatMaster.changeState(ChatMaster.STATE_RID610);
            System.out.println("sent 610 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    /*
     * This is triggered when someone needs to talk to this client
     */
    public void processRequest(Object[] data) {
    	if(super.requestData != null && super.requestData.length > 0) {
    		ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
    		ObjectInputStream oia = null;
    		try {
    			oia = new ObjectInputStream(bais);
    		} catch (IOException e) { }
    		byte[] encrypted = null;
    		try {
    			//get rid of RID
    			oia.readInt();
    			// get user
                String peerUser = (String)oia.readObject();

                // if Ua is the same of peerData.username
                if(ChatMaster.peerData.getUsername().equals(peerUser)) {
        			encrypted = (byte[])oia.readObject();

					//decrypt the ticket using server's session key
					byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.peerData.getPeerSessionKey(), encrypted);

	                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
	                ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //set T1 for peer user
                    ChatMaster.peerData.setTimeT1((byte[])ois2.readObject());
                    //calcualte delta for that client
                    ChatMaster.peerData.setDelta(new Security().clcDelta(new Security().getTimestamp(), ChatMaster.peerData.getTimeT1()));
                    // if T1 is valid, read message
	                if (new Security().isTimeValid(new Security().getTimestamp(), ChatMaster.peerData.getTimeT1(), ChatMaster.peerData.getDelta())) {
                        byte[] message = (byte[])ois2.readObject();
                        // get hash message
                        byte[] message_hash = (byte[])oia.readObject();
                        // compare if message = hash(message)
                        if(Arrays.equals(new Security().getHash(message), message_hash)) {
                            // add conversation to history
                            ChatMaster.clientIM.getChatWindow().addChatHistory(ChatMaster.peerData.getUsername(), new String((byte[])data[0]));

                            //create Rid620 and send out our reply for message being received
                            Request rid620 = new Rid620();
                            Object[] object = { (byte[])message };
                            rid620.sendRequest(object);
                        }
                        else {
                            System.out.println("Warning the hash is not equal to the hash message.");
                        }
	                }
                }
                else {
                    System.out.println("Warning, peer user not expecting!");
                }
    		} catch (Exception e) { }

    	}
    }
    
}