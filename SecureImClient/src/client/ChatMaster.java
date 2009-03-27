package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import client.event.ImEvent;
import client.event.TransportEvent;
import client.request.Request;
import client.request.Rid210;

/**
 *
 * @author Raghuram
 */
public class ChatMaster {
    
    public static final int STATE_INITAL = 10;
    public static final int STATE_RID210 = 210;
    public static final int STATE_RID230 = 230;
    public static final int STATE_RID250 = 250;
    public static final int STATE_LOGIN = 20;
    
    private static int CURRENT_STATE = 0;
    
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 4444;
    
    private static String username;
    private static byte[] publicKey;
    private static String password;
    private static byte[] publicKeyServer;
    
    public static void initialize() {
        ChatMaster.CURRENT_STATE = ChatMaster.STATE_INITAL;
        
        ChatMaster.username = "Alice";
        ChatMaster.password = "tiger69";
        ChatMaster.publicKey = "alice pub".getBytes();
        ChatMaster.publicKeyServer = "server pub".getBytes();
        
        ChatMaster.handle(null);
    }
    
    public synchronized static void handle(ImEvent imEvent) {
        switch (ChatMaster.CURRENT_STATE) {
            
            case ChatMaster.STATE_INITAL: {
                //Login login = new Login(username, password, publicKey, publicKeyServer);
                Request rid210 = new Rid210();
                rid210.sendRequest(null);
                break;
            }
            
            case ChatMaster.STATE_RID210: {
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    if(transportEvent.getEventType() == Request.RID_220) {
                        Request request = transportEvent.getRequestRecieved();
                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
            }
        }
    }
    
    public static void changeState(int newState) {
        ChatMaster.CURRENT_STATE = newState;
    }
}
