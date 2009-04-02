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

/**
 * P2P Auth second step.
 *
 * @author Abdulla
 */
public class Rid520 extends Request {
    
    public Rid520() {
        super(Request.RID_520);
    }

    /*
     * This executes when replying to first 510 msg
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_520);
            
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            
            //write Ua
            oos2.writeObject((String)ChatMaster.peerData.getUsername());
            //write Ub
            oos2.writeObject((String)ChatMaster.clientData.getUsername());
            //write Tt
            oos2.writeObject((byte[])ChatMaster.peerData.getTimeTk());
            //write T1 (storing in peerDetails to verify later in 530)
            ChatMaster.peerData.setTimeT1(new Security().getTimestamp());
            oos2.writeObject((byte[])ChatMaster.peerData.getTimeT1());
            //write session key
            ChatMaster.peerData.setPeerSessionKey(new Security().generateAESKey());
            oos2.writeObject((byte[])ChatMaster.peerData.getPeerSessionKey());
            
            oos2.flush();
            //encrpt baos2 with PKa
            byte[] encryptedData = new Security().AESEncrypt(ChatMaster.peerData.getPeerSessionKey(), baos2.toByteArray());
            
            oos.writeObject((byte[])encryptedData);
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());
            
            ChatMaster.changeState(ChatMaster.STATE_RID520);
            System.out.println("sent 520 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {
            
        }
    }

    
    /*
     * TO-DO
     * This is triggered when someone needs to talk to this client
     */
    public void processRequest(Object[] data) {
    	if(super.requestData != null && super.requestData.length > 0) {
    		ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
    		ObjectInputStream oia = null;
    		try {
    			oia = new ObjectInputStream(bais);
    		} catch (IOException e) { }
    		byte[] encryptedTicket = null;
    		try {
    			//get rid of RID
    			oia.readInt();
    			//get rid of "HELLO"
    			oia.readObject();
    			
    			encryptedTicket = (byte[])oia.readObject();

					//decrypt the ticket using server's session key
					byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedTicket);

	                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
	                ObjectInputStream ois2 = new ObjectInputStream(bais2);
	                ChatMaster.peerData = new PeerDetails();
	                //get Tt (timestamp)
	                byte[] timestamp = (byte[])ois2.readObject();
	                ChatMaster.peerData.setDelta(new Security().clcDelta(new Security().getTimestamp(), timestamp));
	                if (new Security().isTimeValid(new Security().getTimestamp(), timestamp, ChatMaster.peerData.getDelta())) {
		                //get Ua
		                ChatMaster.peerData.setUsername((String)ois2.readObject());
		              //check if the username sent in the ticket Ub equals my username
		                if (ChatMaster.clientData.getUsername().equals((String)ois2.readObject())) {
		                	//read in his IP address
		                	ChatMaster.peerData.setPeerIP((String)ois2.readObject());
		                	//read in his Port
		                	ChatMaster.peerData.setPeerPort(ois2.readInt());
		                	//read his PKa
		                	ChatMaster.peerData.setPeerPublicKey((byte[])ois2.readObject());
		                	
		                	//create Rid520 and send out our reply
		                	Request rid520 = new Rid520();
		                	rid520.sendRequest(null);
		                	
		                }
	                }

	                //i have Ticket-To-B, IPb, Portb, PKb
	                //put these in a nice object[]
	                Object[] infoTo510 = new Object[1];
	                infoTo510[0] = (byte[])"Ticket to b".getBytes();
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