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
 * P2P Auth starts here.
 *
 * @author Abdulla
 */
public class Rid510 extends Request {

    public Rid510() {
        super(Request.RID_510);
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

            oos.writeInt(Request.RID_510);
            oos.writeObject("HELLO");

            //writing Ticket-To-B
            if (data.length == 1) {
                oos.writeObject((byte[]) data[0]);
            } else {
                System.out.println("Error formatting RID510");
            }

            oos.flush();

            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        //TODO: call error screen
        }

        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());

            ChatMaster.changeState(ChatMaster.STATE_RID510);

            activateTimeout();

            System.out.println("Sent RID 510 and activated Timer...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * This is triggered when someone needs to talk to this client
     */
    public void processRequest(Object[] data) {
        // Retreive ticket, check Tt within time skew, check Ub in ticket, store relevent data and return 520
        if (super.requestData != null && super.requestData.length > 0) {

            ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
            ObjectInputStream oia = null;
            
            try {
                oia = new ObjectInputStream(bais);
            } catch (IOException e) {
            }
            
            byte[] encryptedTicket = null;
            
            try {
                //get rid of RID
                oia.readInt();
                
                //get rid of "HELLO"
                oia.readObject();

                encryptedTicket = (byte[]) oia.readObject();

                //decrypt the ticket using server's session key
                byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedTicket);

                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                ObjectInputStream ois2 = new ObjectInputStream(bais2);
                
                ChatMaster.peerData = new PeerDetails();
                
                //get Tt (timestamp)
                byte[] timestamp = (byte[]) ois2.readObject();
                
                // set delta
                ChatMaster.peerData.setDelta(new Security().clcDelta(new Security().getTimestamp(), timestamp));
                
                ChatMaster.peerData.setTimeTk(timestamp);
                
                // check if Tk is in time skew
                if (new Security().isTimeValid(new Security().getTimestamp(), timestamp, ChatMaster.peerData.getDelta())) {
                    
                    //get Ua
                    ChatMaster.peerData.setUsername((String) ois2.readObject());
                    
                    //check if the username sent in the ticket Ub equals my username
                    if (ChatMaster.clientData.getUsername().equals((String) ois2.readObject())) {
                        //read in his IP address
                        ChatMaster.peerData.setPeerIP((String) ois2.readObject());
                        
                        //read in his Port
                        ChatMaster.peerData.setPeerPort(ois2.readInt());
                        
                        //read his PKa
                        ChatMaster.peerData.setPeerPublicKey((byte[]) ois2.readObject());

                        //create Rid520 and send out our reply
                        Request rid520 = new Rid520();
                        rid520.sendRequest(null);
                    }
                }
            } catch (Exception e) {
            }

        }
    }
}