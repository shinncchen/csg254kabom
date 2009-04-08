/**
 * P2P MESSAGE EXCHANGE protocol - RID 610
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

public class Rid610 extends Request {

    /**
     * Rid610 constructor
     */
    public Rid610() {
        super(Request.RID_610);
    }

    /**
     * Sending a Request RID_610 to the peer client.
     * Client sends a message to a peer client
     * @param data
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
            ChatMaster.peerData.setTimeT1(new Security().getTimestamp());

            //package T1
            eoos.writeObject(ChatMaster.peerData.getTimeT1());

            //package the actual message
            String msg = new String(requestData);
            eoos.writeObject(msg);
            eoos.flush();

            // write encrypted(T1 + message) using aes session key
            oos.writeObject(new Security().AESEncrypt(ChatMaster.peerData.getPeerSessionKey(), ebaos.toByteArray()));

            // write hash of message
            oos.writeObject(new Security().getHash(msg.getBytes()));
            oos.flush();

            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setError();
        }

        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());

            ChatMaster.clientIM.getChatWindow().addChatHistory(ChatMaster.clientData.getUsername(), new String(requestData));
            ChatMaster.changeState(ChatMaster.STATE_RID610);

            System.out.println("sent 610 and changed state...");
            activateTimeout();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Receiving a Request RID_610 from a peer client.
     * Client receives a message from a peer client
     * @param data
     */
    public void processRequest(Object[] data) {
        
        // Check Ua matches with the peer details, check T1 time skew, check message integrity
        if (super.requestData != null && super.requestData.length > 0) {
            
            ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
            ObjectInputStream oia = null;
            
            try {
                oia = new ObjectInputStream(bais);
            } catch (IOException e) {
            }
            
            byte[] encrypted = null;
            
            try {
                //get rid of RID
                oia.readInt();
                
                // get user
                String peerUser = (String) oia.readObject();

                // if Ua is the same of peerData.username
                if (ChatMaster.peerData.getUsername().equals(peerUser)) {
                    encrypted = (byte[]) oia.readObject();

                    //decrypt the ticket using server's session key
                    byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.peerData.getPeerSessionKey(), encrypted);

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //set T1 for peer user
                    ChatMaster.peerData.setTimeT1((byte[]) ois2.readObject());

                    // if T1 is valid, read message
                    if (new Security().isTimeValid(new Security().getTimestamp(), ChatMaster.peerData.getTimeT1(), ChatMaster.peerData.getDelta())) {
                        String message = (String) ois2.readObject();
                        
                        // get hash message
                        byte[] message_hash = (byte[]) oia.readObject();
                        
                        // compare if message = hash(message)
                        if (Arrays.equals(new Security().getHash(message.getBytes()), message_hash)) {
                            
                            // add conversation to history
                            ChatMaster.clientIM.getChatWindow().addChatHistory(ChatMaster.peerData.getUsername(), message);

                            //create Rid620 and send out our reply for message being received
                            Request rid620 = new Rid620();
                            rid620.sendRequest(null);
                        } else {
                            System.out.println("Warning the hash is not equal to the hash message.");
                        }
                    }
                } else {
                    System.out.println("Warning, peer user not expecting!");
                }
            } catch (Exception e) {}
        }
    }
}