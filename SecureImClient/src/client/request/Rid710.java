/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import client.*;
import client.security.*;
import client.transport.*;

import java.io.*;


public class Rid710 extends Request {

    public Rid710() {
        super(Request.RID_710);
    }

    public void sendRequest(Object[] data) {
        Sender sender = new Sender();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        byte[] message = null;

        try {
            oos = new ObjectOutputStream(baos);

            oos.writeInt(Request.RID_710);
            oos.writeObject("LOGOUT");
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
            //TODO: call error screen
        }

        try {
            sender.send(message);

            ChatMaster.changeState(ChatMaster.STATE_RID710);
            System.out.println("sent 710 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {

        }
    }

    public void processRequest(Object[] data) {
    }
}
