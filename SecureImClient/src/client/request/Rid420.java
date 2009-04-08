/**
 * PERMIT protocol - Rid420
 *
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import client.security.Security;

import java.io.*;
import java.util.Arrays;

public class Rid420 extends Request {

    /**
     * Rid420 constructor
     */
    public Rid420() {
        super(Request.RID_420);
    }

    /**
     * Processing a Request RID_420 from the server
     * @param data
     */
    public void processRequest(Object[] data) {
        // check response nonce T1, check Ua and Ub, store relevant data
        if (super.senderIp.equalsIgnoreCase(ChatMaster.SERVER_IP)) {

            if (super.requestData != null && super.requestData.length > 0) {
                
                ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
                ObjectInputStream oia = null;
                
                try {
                    oia = new ObjectInputStream(bais);
                } catch (IOException e) {
                }
                
                byte[] encryptedMsg = null;
                
                try {
                    oia.readInt();

                    encryptedMsg = (byte[]) oia.readObject();

                    //decrypt the msg using our session key
                    byte[] decryptedMsg = new Security().AESDecrypt(ChatMaster.clientData.getSessionKey(), encryptedMsg);

                    ByteArrayInputStream bais2 = new ByteArrayInputStream(decryptedMsg);
                    ObjectInputStream ois2 = new ObjectInputStream(bais2);

                    //match T1
                    if (Arrays.equals(ChatMaster.peerData.getTimeT1(), (byte[]) ois2.readObject())) {
                        
                        //store Tk in peer details
                        byte[] tT = (byte[]) ois2.readObject();
                        
                        //check if same Ua
                        if (ChatMaster.clientData.getUsername().equals((String) ois2.readObject())) {
                            
                            //check if same Ub
                            if (ChatMaster.peerData.getUsername().equals((String) ois2.readObject())) {
                                
                                deactivateTimeout();
                                
                                // store relevent data
                                ChatMaster.peerData.setTimeTk(tT);
                                ChatMaster.peerData.setPeerIP((String) ois2.readObject());
                                ChatMaster.peerData.setPeerPort(ois2.readInt());
                                ChatMaster.peerData.setPeerPublicKey((byte[]) ois2.readObject());
                            }
                        }
                    }

                    //we have Ticket-To-B, IPb, Portb, PKb, we store it in an object and pass it to 510
                    Object[] infoTo510 = new Object[1];
                    infoTo510[0] = (byte[]) oia.readObject();

                    //create Rid510 to send P2P Auth.
                    Request rid510 = new Rid510();
                    rid510.sendRequest(infoTo510);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * RID_220 never send a Request
     * @param data
     */
    public void sendRequest(Object[] data) {
    }
}
