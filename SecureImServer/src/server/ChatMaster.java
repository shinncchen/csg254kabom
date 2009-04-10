/**
 * This class is the state machine.
 *
 * @author HuskyHackers
 */

package server;

import java.util.HashMap;

import server.database.UsersDB;
import server.event.ImEvent;
import server.event.TimeoutEvent;
import server.event.TransportEvent;
import server.request.Request;
import server.request.Rid210;
import server.transport.Listener;
import server.security.*;

public class ChatMaster {

    public static final String SERVER_IP = "127.0.0.1";
    public static int SERVER_PORT = 4444;
    public static byte[] publicKeyServer;
    public static byte[] privateKeyServer;
    private static final String publicKeyServerHex = "aced0005737200146a6176612e73656375726974792e4b6579526570bdf94fb3889aa5430200044c0009616c676f726974686d7400124c6a6176612f6c616e672f537472696e673b5b0007656e636f6465647400025b424c0006666f726d617471007e00014c00047479706574001b4c6a6176612f73656375726974792f4b657952657024547970653b7870740003525341757200025b42acf317f8060854e00200007870000000a230819f300d06092a864886f70d010101050003818d00308189028181009018a41594d2a378e1fd2cc4493b087b86e888e046536c749228c9bb92dd36aa8a66d79cc03a166958683296e8ecc3e8530318c879f8f2056f1fdd78112a064dc75f4c1be5f164b7d6385e1eadba3eff240ab54be9ed6e5819637eca6b6d06e60f027b9bdf0bed1b9a22a250a7d792abff62077c7f7b6be384ed5a50e4d755c50203010001740005582e3530397e7200196a6176612e73656375726974792e4b6579526570245479706500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400065055424c4943";
    private static final String privateKeyServerHex = "aced0005737200146a6176612e73656375726974792e4b6579526570bdf94fb3889aa5430200044c0009616c676f726974686d7400124c6a6176612f6c616e672f537472696e673b5b0007656e636f6465647400025b424c0006666f726d617471007e00014c00047479706574001b4c6a6176612f73656375726974792f4b657952657024547970653b7870740003525341757200025b42acf317f8060854e002000078700000027a30820276020100300d06092a864886f70d0101010500048202603082025c020100028181009018a41594d2a378e1fd2cc4493b087b86e888e046536c749228c9bb92dd36aa8a66d79cc03a166958683296e8ecc3e8530318c879f8f2056f1fdd78112a064dc75f4c1be5f164b7d6385e1eadba3eff240ab54be9ed6e5819637eca6b6d06e60f027b9bdf0bed1b9a22a250a7d792abff62077c7f7b6be384ed5a50e4d755c5020301000102818055da48910f2d7cf67df7012025dd854d4bf0e9f46e12305137f01359adc41d58f8c3137db24e29a234694892ff4e69ded6eec66a53b9ce410c9778b59bcc651a35db15b16a8b3eb7d51de89b5713cc03e6b76301165208056023419ec5d0fbc1a4148c3c52958069acf49af9bef55d04ba0d0ae2df675c5f2d8ead7ec9d2bf61024100d7e62f4daca41aef49e2de629f2dcb1eb983db6ab5132314294173eb45bd5f8359cf27413658399922372e2008407bd0e7163bba1df5eb78cc9273c7c579256d024100aadc4a8822f0e0787a76a62046e95214d5af9ce25f0f8de9be49e129325759e679b552e49899e058a9e7025bde586e8b60aa39ae5e988c8270e15c7f916c32b9024016fc0173327af20721858e894f4aefc1e09c2202d52e2960df1781c0bd56f0d12a017cbb5b8ebeeb07927a4d9cb348bb2faed542f627951ef22071450647e46d024039ae8726f08cdb6b3104cf4ec441cea923fed67b01d6b17540fd961b1f58a80c0bf8b0146684445e0a75e7b0f61e6aab7925deb5445041d7639e6168dbc268590241008c7980774a54749984fe80bb6007ea01987e639477b89941ccf92185870d6b115905b58bab1b68181ceac0b59cbae219fff7c3632bb69c7ddcf4c53325b505f1740006504b435323387e7200196a6176612e73656375726974792e4b6579526570245479706500000000000000001200007872000e6a6176612e6c616e672e456e756d0000000000000000120000787074000750524956415445";
    public static HashMap users = null;
    public static UsersDB usersDB = null;

    /**
     * This method initializes the client.
     * Set state to INITIAL.
     * Open a port to listen to transport events.
     */
    public static void initialize() {

        // Intialize the RSA keys
        ChatMaster.publicKeyServer = Security.hexToByteArray(publicKeyServerHex);
        ChatMaster.privateKeyServer = Security.hexToByteArray(privateKeyServerHex);

        // Initializing user data
        users = new HashMap();
        usersDB = new UsersDB();

        // Starting UPD server to listen to incoming requests
        Listener listener = new Listener();
        listener.setPort(SERVER_PORT);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        
        System.out.println("Listener started...");
    }

    /**
     * ChatMaster handle manages the state transitions based on occurring events
     * @param imEvent imEvent is either a GuiEvent or a TransportEvent
     */
    public synchronized static void handle(ImEvent imEvent) {

        String ipAddress = imEvent.getClientIp();

        if (ipAddress != null && ipAddress.length() > 0) {
            if (imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                TransportEvent transportEvent = (TransportEvent) imEvent;
                Request request = transportEvent.getRequestRecieved();

                int requestId = request.getRequestId();

                // Retreive the user from the Hash Map
                UserInfo currentUser = (UserInfo) users.get(ipAddress);

                // only if user doesn't exist and RID is 210, create an user entry
                if (currentUser == null) {
                    if (requestId == Request.RID_210) {
                        System.out.println("processing rid 210...");
                        
                        //the user hasn't established any contact yet, so create an hashmap entry
                        Rid210 rid210 = (Rid210) request;
                        UserInfo userInfo = new UserInfo();
                        userInfo.setIpAdress(request.getSenderIp());

                        // Process 210 and respond
                        rid210.processRequest(userInfo, null);
                        
                        // Crate an hash map entry
                        users.put(request.getSenderIp(), userInfo);
                        
                        System.out.println("new user created in hashmap");
                    } else {
                        //user hasn't established contact, but is try to send a request other than the very first one, drop it
                    }
                } else {
                    int userState = currentUser.getCurrentState();

                    switch (userState) {

                        case UserInfo.STATE_INITAL: {
                            
                            break;
                        }
//
                        // Received challenge response, Check and send session key
                        case UserInfo.STATE_RID220: {
                            System.out.println("action in state RID220");
                            request = transportEvent.getRequestRecieved();
                            
                            if (request.getRequestId() == Request.RID_230) {
                                request.processRequest(currentUser, null);

                            } else {
                                //ignore request
                            } 

                            break;
                        }
                        case UserInfo.STATE_RID240: {
                            System.out.println("action in state RID240");
                            request = transportEvent.getRequestRecieved();
                            if (request.getRequestId() == Request.RID_250) {
                                request.processRequest(currentUser, null);
                            } else {
                            } //ignore

                            break;
                        }

                        // This same case can be used for PERMIT or LIST or LOGOUT 
                        case UserInfo.STATE_LOGIN: {
                                request = transportEvent.getRequestRecieved();
                                if(request.getRequestId() == Request.RID_310) {
                                	System.out.println("310 LIST");
                                    request.processRequest(currentUser, null);
                                    
                                } 
                                else if (request.getRequestId() == Request.RID_410){
                                	System.out.println("410 PERMIT");
                                    request.processRequest(currentUser, null);
                                }
                                else if (request.getRequestId() == Request.RID_710){
                                	System.out.println("710 LOGOUT");
                                    request.processRequest(currentUser, null);
                                }
                                else {}
                            break;
                        }


                    }
                }
            } else if (imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                System.out.println("Timeout occoured for ip: " + ipAddress);
                TimeoutEvent timeoutEvent = (TimeoutEvent) imEvent;
                int requestId = timeoutEvent.getRequestId();

                UserInfo currentUser = (UserInfo) users.get(ipAddress);

                if (currentUser != null) {
                    int userState = currentUser.getCurrentState();

                    switch (userState) {

                        case UserInfo.STATE_RID220: {
                            System.out.println("timeout in state RID220");
                            if (timeoutEvent.getRequestId() == Request.RID_220) {
                                System.out.println("removing user...");
                                users.remove(ipAddress);
                            }
                            break;
                        }
                        
                        case UserInfo.STATE_RID240: {
                            System.out.println("timeout in state RID240");
                            if (timeoutEvent.getRequestId() == Request.RID_240) {
                                System.out.println("removing user...");
                                users.remove(ipAddress);
                            }
                            break;
                        }
                        
                        case UserInfo.STATE_RID250: {
                            System.out.println("timeout in state RID250");
                            if (timeoutEvent.getRequestId() == Request.RID_250) {
                                System.out.println("removing user...");
                                users.remove(ipAddress);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
