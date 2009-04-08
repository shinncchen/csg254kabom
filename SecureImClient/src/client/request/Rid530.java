/**
 * P2P AUTHENTICATION protocol - RID 530
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

public class Rid530 extends Request {

    /**
     * Rid530 constructor
     */
    public Rid530() {
        super(Request.RID_530);
    }

    /**
     * Sending a Request RID_530 to the peer client.
     * last msg to send, once sent. A is authenticated with him.
     * @param data
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_530);

            //write T1 in a bytearray to encrypt with session key
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            
            oos2.writeObject(ChatMaster.peerData.getTimeT1());
            oos2.flush();
            
            oos.writeObject(new Security().AESEncrypt(ChatMaster.peerData.getPeerSessionKey(), baos2.toByteArray()));

            oos.flush();

            message = baos.toByteArray();
            oos.close();
            oos2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setError();
        }

        try {
            sender.send(message, ChatMaster.peerData.getPeerIP(), ChatMaster.peerData.getPeerPort());
            //once sent, the guy is authenticated!!

            ChatMaster.clientIM.createChatWindow(ChatMaster.peerData.getUsername());
            ChatMaster.peerData.setAuth(true);

            ChatMaster.changeState(ChatMaster.STATE_RID530);
            System.out.println("sent 530 and changed state...");
        } catch (Exception ex) {
        }
    }

    /**
     * Receiving a Request RID_530 from a peer client.
     * If received, then B authenticated with A
     * @param data
     */
    public void processRequest(Object[] data) {
        // check T1
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

                //decrypt the ticket using server's session key
                byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.peerData.getPeerSessionKey(), encryptedMsg);

                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                ObjectInputStream ois2 = new ObjectInputStream(bais2);

                //check T1 with what i sent in 520
                if (Arrays.equals(ChatMaster.peerData.getTimeT1(), (byte[]) ois2.readObject())) {
                    
                    deactivateTimeout();
                    
                    //then user is authenticated ! hurray
                    ChatMaster.clientIM.createChatWindow(ChatMaster.peerData.getUsername());
                    ChatMaster.peerData.setAuth(true);
                    ChatMaster.changeState(ChatMaster.STATE_RID530);
                }
                ois2.close();
                oia.close();

            } catch (Exception e) {}
        }
    }
}