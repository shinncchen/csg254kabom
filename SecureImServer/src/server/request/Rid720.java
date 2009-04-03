/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Raghuram
 */
public class Rid720 extends Request {

    public Rid720() {
        super(Request.RID_720);
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_320);

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
            // userInfo.setCurrentState(UserInfo.STATE_INITAL);
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }

        try {
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            System.out.println("ipaddress sent to: " + userInfo.getIpAdress() + " and port: " + userInfo.getPort());
            userInfo.setCurrentState(UserInfo.STATE_INITAL);
            System.out.println("sent 720 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {

        }
    }

}