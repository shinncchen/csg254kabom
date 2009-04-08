package client.request;

import client.ChatMaster;
import client.datastructure.PeerDetails;
import client.security.Security;
import client.transport.Sender;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author HuskyHackers
 *
 * PERMIT protocol - Rid410
 */
public class Rid410 extends Request {

    /**
     * Rid410 constructor
     */
    public Rid410() {
        super(Request.RID_410);
    }

    /**
     * Sending a Request RID_410 to the server
     * @param data
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_410);
            oos.writeObject("PERMIT");
            oos.writeObject(ChatMaster.clientData.getUsername());
            
            ByteArrayOutputStream ebaos = new ByteArrayOutputStream();
            ObjectOutputStream eoos = new ObjectOutputStream(ebaos);
            
            //write timestamp
            byte[] timestamp = new Security().getTimestamp();
            ChatMaster.peerData.setTimeT1(timestamp);
            eoos.writeObject(ChatMaster.peerData.getTimeT1());
            //Ua
            eoos.writeObject(ChatMaster.clientData.getUsername());
            //Ub
            eoos.writeObject(ChatMaster.peerData.getUsername());
            eoos.flush();
            
            oos.writeObject(new Security().AESEncrypt(ChatMaster.clientData.getSessionKey(), ebaos.toByteArray()));
            eoos.close();
            
            oos.flush();
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            ChatMaster.clientIM.setError();
        }
        
        try {
            sender.send(message);

            ChatMaster.changeState(ChatMaster.STATE_RID410);
            System.out.println("sent 410 and changed state...");
            
            activateTimeout();
        } catch (Exception ex) {
            
        }
    }

    /**
     * RID_410 never process a Request
     * @param data
     */
    public void processRequest(Object[] data) {
    }
    
}