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
            oos2.writeObject((String) ChatMaster.peerData.getUsername());
            //write Ub
            oos2.writeObject((String) ChatMaster.clientData.getUsername());
            //write Tt
            oos2.writeObject((byte[]) ChatMaster.peerData.getTimeTk());
            //write T1 (storing in peerDetails to verify later in 530)
            ChatMaster.peerData.setTimeT1(new Security().getTimestamp());
            oos2.writeObject((byte[]) ChatMaster.peerData.getTimeT1());
            //write session key
            ChatMaster.peerData.setPeerSessionKey(new Security().generateAESKey());
            oos2.writeObject((byte[]) ChatMaster.peerData.getPeerSessionKey());

            oos2.flush();
            //encrpt baos2 with PKa
            byte[] encryptedData = new Security().RSAEncrypt(ChatMaster.peerData.getPeerPublicKey(), baos2.toByteArray());

            oos.writeObject((byte[]) encryptedData);

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
            
            activateTimeout();
        } catch (Exception ex) {
        }
    }

    /*
     * A is receiving the second msg from B
     */
    public void processRequest(Object[] data) {
        
        // Check Tt against peer data Tt, check Ua and Ub, store session key
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

                //decrypt the msg using my priv key
                byte[] decryptedMsg = new Security().RSADecrypt(ChatMaster.clientData.getPrivateKey(), encryptedMsg);

                ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                ObjectInputStream ois2 = new ObjectInputStream(bais2);

                //get Ua and check if it equals me
                if (ChatMaster.clientData.getUsername().equalsIgnoreCase((String) ois2.readObject())) {
                    
                    //get Ub make sure he's the guy we want to talk to
                    if (ChatMaster.peerData.getUsername().equalsIgnoreCase((String) ois2.readObject())) {
                        
                        //get Tt and make sure its what we stored earlier from the server
                        if (Arrays.equals(ChatMaster.peerData.getTimeTk(), (byte[]) ois2.readObject())) {
                            
                            //store T1 into peerdetails t1 to send it in rid530
                            ChatMaster.peerData.setTimeT1((byte[]) ois2.readObject());
                            
                            // set delta
                            ChatMaster.peerData.setDelta(new Security().clcDelta(new Security().getTimestamp(), ChatMaster.peerData.getTimeT1()));
                            
                            //only if T1 in time skew
                            if (new Security().isTimeValid(new Security().getTimestamp(), ChatMaster.peerData.getTimeT1(), ChatMaster.peerData.getDelta())) {
                                
                                deactivateTimeout();
                                
                                //give me his session key !
                                ChatMaster.peerData.setPeerSessionKey((byte[]) ois2.readObject());
                                
                                //send that to Rid530
                                Request rid530 = new Rid530();
                                rid530.sendRequest(null);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

        }
    }
}