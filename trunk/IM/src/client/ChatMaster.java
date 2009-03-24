package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import client.authentication.Login;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raghuram
 */
public class ChatMaster {
    
    private String username;
    private byte[] publicKey;
    private String password;
    private byte[] publicKeyServer;
    
    private static Login login;
    
    public ChatMaster() {
        this.username = "Alice";
        this.password = "tiger69";
        this.publicKey = "alice pub".getBytes();
        this.publicKeyServer = "server pub".getBytes();
    }
    
    public void Login() {
        login = new Login(this.username, this.password, this.publicKey, this.publicKeyServer);
        try {
            login.sendRid20();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Login getLogin() {
        return login;
    }
}
