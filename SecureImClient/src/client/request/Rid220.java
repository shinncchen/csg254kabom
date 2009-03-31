/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.ChatMaster;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
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

                int challengeLength = super.requestData.length - 4;
                byte[] challenge = new byte[challengeLength];

                int challengeLengthRead = bais.read(challenge, 4, challenge.length);

                if(challengeLengthRead == challengeLength) { //Just in case, not useful atleast for now
                    //Rid230 rid230 = new Rid230();
                }

            }
        }
    }

    public void sendRequest(Object[] data) {
    }
    
}
