/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.authentication;

import java.io.IOException;
import client.requests.Rid20;
import client.transport.TransportData;

/**
 *
 * @author Raghuram
 */
public class Login {

    private String username;
    private byte[] publicKey;
    private String password;
    private byte[] publicKeyServer;
    
    public Login(String username, String password, byte[] publicKey, byte[] publicKeyServer) {
        this.username = username;
        this.password = password;
        this.publicKey = publicKey;
        this.publicKeyServer = publicKeyServer;
    }
    
    public void sendRid20() throws IOException {
        Rid20 rid20 = new Rid20();
        rid20.setup(this.username, this.password, this.publicKey, this.publicKeyServer);
        
        Thread rid20Thread = new Thread(rid20);
        rid20Thread.start();
        System.out.println("RID 20 thread started");
    }
    
    public void receiveRid20(TransportData transportData) {
        System.out.println("RID 20 response: "+new String(transportData.getMDataReceive()));
    }
    
    public void receiveRid21(TransportData transportData) {
        System.out.println("RID 21 response: "+new String(transportData.getMDataReceive()));
    }
    
    public void receiveRid22(TransportData transportData) {
        System.out.println("RID 22 response: "+new String(transportData.getMDataReceive()));
    }

}
