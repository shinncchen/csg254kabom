/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.UserInfo;
import server.security.Security;
import server.transport.Sender;

/**
 *
 * @author Deepak
 */
public class Rid420 extends Request {
    
    public Rid420() {
        super(Request.RID_420);
    }

    public void processRequest(UserInfo userInfo, Object[] data) {
    }

    public void sendRequest(UserInfo userInfo, Object[] data) {
        Sender sender = new Sender();
        UserInfo userBInfo = null;
        if(data.length == 1){
        	userBInfo = (UserInfo)data[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        byte[] message = null;
        
        try {
            oos = new ObjectOutputStream(baos);
            
            oos.writeInt(Request.RID_420);
            
            //encrypt data
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
            byte[] firstPartData = null;
            
            //T1
            oos2.writeObject(userInfo.getTimeT1());
            //generate Tt
            byte[] tT = new Security().getTimestamp();
            oos2.writeObject(tT);
            //Ua
            oos2.writeObject(userInfo.getUsername());
            //Ub
            oos2.writeObject(userBInfo.getUsername());
            //IPb
            oos2.writeObject(userBInfo.getIpAdress());
            //portB
            oos2.writeInt(userBInfo.getPort());
            //PKb
            oos2.writeObject(userBInfo.getPublicKey());
            oos2.flush();
            
            //encrypt with Ua's session key
            firstPartData = baos2.toByteArray();
            firstPartData = new Security().AESEncrypt(userInfo.getSessionKey(),firstPartData);
            oos2.close();
            
            //oos.writeObject(new Security().AESEncrypt(userInfo.getSessionKey(), baos2.toByteArray()));
            		
            //CREATE TICKET
            ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
            ObjectOutputStream oos3 = new ObjectOutputStream(baos3);
            byte[] secondPartData = null;
            
            //Tt
            oos3.writeObject(tT);
            //Ua
            oos3.writeObject(userInfo.getUsername());
            //Ub
            oos3.writeObject(userBInfo.getUsername());
            //IPa
            oos3.writeObject(userInfo.getIpAdress());
            //portA
            oos3.writeInt(userInfo.getPort());
            //PKa
            oos3.writeObject(userInfo.getPublicKey());
            oos3.flush();
            
          //encrypt with Ub's session key
            secondPartData = baos3.toByteArray();
            secondPartData = new Security().AESEncrypt(userInfo.getSessionKey(), secondPartData);
            //oos.writeObject(new Security().AESEncrypt(userBInfo.getSessionKey(), baos3.toByteArray()));
            
            oos3.close();
            
            // write both parts into oos
            oos.writeObject(firstPartData);
            oos.writeObject(secondPartData);
            
            oos.flush();
            System.out.println("TICKET-to-" +userBInfo.getUsername() + " created !!" );
            
            message = baos.toByteArray();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //TODO: call error screen
        }
        
        try {
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            userInfo.setCurrentState(UserInfo.STATE_LOGIN);
            System.out.println("sent 420 and changed state...");
            //TODO: timeout setup
        } catch (Exception ex) {
            
        }
    }
    
}