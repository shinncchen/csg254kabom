/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raghuram
 */
public class Rid220 extends Request {
    
    public Rid220() {
        super(Request.RID_220);
    }

    public void processRequest(Object[] data) {
        if(super.senderIp.equalsIgnoreCase(ChatMaster.SERVER_IP)) {
            if(super.requestData != null && super.requestData.length > 0) {
                ByteArrayInputStream bais = new ByteArrayInputStream(super.requestData);
                ObjectInputStream oia = null;
                try {
                	oia = new ObjectInputStream(bais);
                } catch (IOException e) { }
                byte[] challenge = null;
                try {
                	oia.readInt();
					challenge = (byte[])oia.readObject();
				} catch (Exception e) { }
				Object[] objectsToSend = new Object[1];
				objectsToSend[0] = challenge;
				System.out.println("challenge = " + challenge);
				Request rid230 = new Rid230();
				rid230.sendRequest(objectsToSend);
            }
        }
    }

    public void sendRequest(Object[] data) {
    }
    
}
