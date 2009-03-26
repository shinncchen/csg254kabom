/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.requests;

import client.ChatMaster;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import client.transport.Transport;
import client.transport.TransportData;

/**
 *
 * @author Raghuram
 */
public class Rid61 extends Transport implements Runnable {
    
    private static final int REQUEST_ID = 61;
    private TransportData transportData;

    public void setup(String username, String password, byte[] publicKey, byte[] publicKeyServer) throws IOException {
        this.transportData = new TransportData();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        
        dos.write(REQUEST_ID);
        dos.writeUTF(username);
        
        byte[] encryptedData = "{N1, UA, PKA, h(pwd)}".getBytes();
        dos.write(encryptedData);
        
        dos.flush();
        
        this.transportData.setMDataToSend(baos.toByteArray());
        this.transportData.setMIP("192.168.1.1");
        this.transportData.setMSendPort(4444);
    }

    public void run() {
        System.out.println("sending RID 61");
        this.transportData = super.sendAndReceive(transportData);
        System.out.println("Receivced RID 61");
        //ChatMaster.getP2pAuth().receiveRid61(this.transportData);
    }
    
}
