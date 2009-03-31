package server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import server.event.ImEvent;
import server.event.TransportEvent;
import server.request.Request;
import server.request.Rid210;
import server.transport.Listener;

/**
 *
 * @author Raghuram
 */
public class ChatMaster {
    
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 4444;
    
    private static byte[] publicKeyServer;
    private static byte[] privateKeyServer;
    
    private static HashMap users = null;
    
    public static void initialize() {
        ChatMaster.publicKeyServer = "server pub".getBytes();
        ChatMaster.privateKeyServer = "server priv".getBytes();
        
        users = new HashMap();
        
        Listener listener = new Listener();
        listener.setPort(SERVER_PORT);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        System.out.println("Listener started...");
    }
    
    public synchronized static void handle(ImEvent imEvent) {
        
        String ipAddress = imEvent.getClientIp();
        
        if(ipAddress != null && ipAddress.length() > 0) {
            if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                TransportEvent transportEvent = (TransportEvent) imEvent;
                Request request = transportEvent.getRequestRecieved();
                
                int requestId = request.getRequestId();
                
                UserInfo currentUser = (UserInfo) users.get(ipAddress);
                
                if(currentUser == null) {
                    if(requestId == Request.RID_210) {
                        System.out.println("processing rid 210...");
                        //the user hasn't established any contact yet, so create an hashmap entry
                        Rid210 rid210 = (Rid210) request;
                        UserInfo userInfo = new UserInfo();
                        rid210.processRequest(userInfo, null);
                        users.put(ipAddress, userInfo);
                        System.out.println("new user created in hashmap");
                    }
                    else {
                        //user hasn't established contact, but is try to send a request other than the very first one, drop it
                    }
                }
                else {
                    int userState = currentUser.getCurrentState();
                    
                    switch (userState) {
            
//                        case UserInfo.STATE_INITAL: {
//                            if(requestId == Request.RID_220) {
//                                request.processRequest(null);
//                            }
//                            break;
//                        }
//
//                        case UserInfo.STATE_RID220: {
//                            System.out.println("action in state RID220");
//                            
//                            break;
//                        }
                }
            }
        }
        
        
        }
    }
    
}
