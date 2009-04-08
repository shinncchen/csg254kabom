/**
 * LIST protocol - Rid310
 *
 * @author HuskyHackers
 */

package client.request;

import client.*;
import client.security.*;
import client.transport.*;

import java.io.*;

public class Rid310 extends Request {

    /**
     * Rid310 constructor
     */
    public Rid310() {
        super(Request.RID_310);
    }

    /**
     * Sending a Request RID_310 to the server
     * @param data
     */
    public void sendRequest(Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_310);
            oos.writeObject("LIST");
            oos.writeObject(ChatMaster.clientData.getUsername());

            ByteArrayOutputStream ebaos = new ByteArrayOutputStream();
            ObjectOutputStream eoos = new ObjectOutputStream(ebaos);

            //Ua
            eoos.writeObject(ChatMaster.clientData.getUsername());
            //write timestamp
            ChatMaster.clientData.setTimeT1(new Security().getTimestamp());
            //T1
            eoos.writeObject(ChatMaster.clientData.getTimeT1());
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

            ChatMaster.changeState(ChatMaster.STATE_RID310);
            
            // activate timeout for RID_310
            activateTimeout();
            
            System.out.println("sent 310 and changed state...");
        } catch (Exception ex) {

        }
    }

    /**
     * RID_310 never process a Request
     * @param data
     */
    public void processRequest(Object[] data) {
    }
}
