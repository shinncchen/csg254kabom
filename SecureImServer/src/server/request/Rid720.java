/**
 * LOGOUT protocol - RID 720
 *
 * @author HuskyHackers
 */

package server.request;

import server.ChatMaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.UserInfo;
import server.security.Security;
import server.transport.Sender;

public class Rid720 extends Request {

    /**
     * Rid720 constructor
     */
    public Rid720() {
        super(Request.RID_720);
    }

    /**
     * RID_720 never process a Request
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void processRequest(UserInfo userInfo, Object[] data) {
    }

    /**
     * Sending a Request RID_720 to a client
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void sendRequest(UserInfo userInfo, Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_720);

            //encrypt data
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            
            //T1
            oos2.writeObject(userInfo.getTimeT1());
            
            oos2.flush();

            //encrypt with session key
            oos.writeObject(new Security().AESEncrypt(userInfo.getSessionKey(), baos2.toByteArray()));
            
            oos.flush();

            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            userInfo.setCurrentState(UserInfo.STATE_INITAL);
            System.out.println("sent 720 and changed state...");
        } catch (Exception ex) {

        }
    }

}
