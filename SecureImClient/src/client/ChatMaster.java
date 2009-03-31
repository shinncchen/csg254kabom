package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import client.event.ImEvent;
import client.event.TransportEvent;
import client.request.Request;
import client.request.Rid210;
import client.transport.Listener;

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
    private static byte[] publicKeyServer;
    
    private static String username;
    private static String password;
    private static byte[] publicKey;
    
    public static void initialize() {
        ChatMaster.CURRENT_STATE = ChatMaster.STATE_INITAL;
        
        ChatMaster.username = "Alice";
        ChatMaster.password = "tiger69";
        ChatMaster.publicKey = "alice pub".getBytes();
        ChatMaster.publicKeyServer = "server pub".getBytes();
        
        Listener listener = new Listener();
        listener.setPort(5555);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        System.out.println("Listener started...");
        
        ChatMaster.handle(null);
    }
    
    public synchronized static void handle(ImEvent imEvent) {
        switch (ChatMaster.CURRENT_STATE) {
            
            case ChatMaster.STATE_INITAL: {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException ex) {}

                Request rid210 = new Rid210();
                rid210.sendRequest(null);
                break;
            }
            
            case ChatMaster.STATE_RID210: {
                System.out.println("action in state RID210");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_220) {
                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
                break;
            }
        }
    }
    
    public static void changeState(int newState) {
        ChatMaster.CURRENT_STATE = newState;
    }
}
