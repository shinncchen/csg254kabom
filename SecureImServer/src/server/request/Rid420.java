/**
 * LOGIN protocol - RID 420
 *
 * @author HuskyHackers
 */

package server.request;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.UserInfo;
import server.security.Security;
import server.transport.Sender;

public class Rid420 extends Request {

    /**
     * Rid420 constructor
     */
    public Rid420() {
        super(Request.RID_420);
    }

    /**
     * RID_420 never process a Request
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void processRequest(UserInfo userInfo, Object[] data) {
    }

    /**
     * Sending a Request RID_420 to a client
     * @param userInfo - UserInfo, info about the client
     * @param data
     */
    public void sendRequest(UserInfo userInfo, Object[] data) {
        // Create and send ticket info...
        
        Sender sender = new Sender();
        
        if(data.length != 1){
            return;
        }
        
        UserInfo userBInfo = (UserInfo)data[0];
        
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
            secondPartData = new Security().AESEncrypt(userBInfo.getSessionKey(), secondPartData);
            
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
        }
        
        try {
            sender.send(message, userInfo.getIpAdress(), userInfo.getPort());
            System.out.println("sent 420...");
        } catch (Exception ex) {
            
        }
    }
    
}