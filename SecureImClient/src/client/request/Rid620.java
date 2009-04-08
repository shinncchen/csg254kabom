/**
 * P2P MESSAGE EXCHANGE protocol - RID 620
 *
 * @author HuskyHackers
 */
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

public class Rid620 extends Request {

    /**
     * Rid620 constructor
     */
    public Rid620() {
        super(Request.RID_620);
    }

    /**
     * Sending a Request RID_610 to the peer client.
     * This executes when replying to first 510 msg
     * @param data
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_620);

            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);

            //write T1 from peer user
            oos2.writeObject((byte[]) ChatMaster.peerData.getTimeT1());

            oos2.flush();

            //encrpt baos2 with PKa
            byte[] encryptedData = new Security().AESEncrypt(ChatMaster.peerData.getPeerSessionKey(), baos2.toByteArray());

            oos.writeObject((byte[]) encryptedData);

            oos.flush();

            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setError();
        }

        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());

            ChatMaster.changeState(ChatMaster.STATE_RID530);
            System.out.println("sent 620 and changed state...");
        } catch (Exception ex) {
        }
    }

    /**
     * Receiving a Request RID_620 from a peer client.
     * A is receiving the second msg from B
     * @param data
     */
    public void processRequest(Object[] data) {
        
        // check T1 and display message
        if (super.requestData != null && super.requestData.length > 0) {
            
            ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
            ObjectInputStream oia = null;

            try {
                oia = new ObjectInputStream(bais);
            } catch (IOException e) {
            }
            
            byte[] encryptedMsg = null;
            
            try {
                //get rid of RID
                oia.readInt();

                encryptedMsg = (byte[]) oia.readObject();

                //decrypt the message using aes session key
                byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.peerData.getPeerSessionKey(), encryptedMsg);

                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                ObjectInputStream ois2 = new ObjectInputStream(bais2);

                //if timestamp matches
                if (Arrays.equals(ChatMaster.peerData.getTimeT1(), (byte[]) ois2.readObject())) {
                    
                    deactivateTimeout();
                    
                    //ChatMaster.clientIM.getChatWindow().addChatHistory(ChatMaster.clientData.getUsername(), "message delivered");
                    System.out.println("Message was delivered to " + ChatMaster.peerData.getUsername());
                    
                    ChatMaster.changeState(ChatMaster.STATE_RID530);
                }
            } catch (Exception e) {}
        }
    }
}