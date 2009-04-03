package client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import client.datastructure.*;
import client.event.*;
import client.gui.*;
import client.request.*;
import client.transport.*;
import client.security.*;

/**
 *
 * @author Raghuram
 */
public class ChatMaster {
    
	private static int CURRENT_STATE = 0;
    public static final int STATE_INITAL = 10;
    //LOGIN
    public static final int STATE_LOGIN = 20;
    public static final int STATE_RID210 = 210;
    public static final int STATE_RID230 = 230;
    public static final int STATE_RID250 = 250;
    //LIST
    public static final int STATE_RID310 = 310;    
    public static final int STATE_RID320 = 320;
    //PERMIT
    public static final int STATE_RID410 = 410;
    public static final int STATE_RID420 = 420;
    //P2P AUTHENTICATION
    public static final int STATE_RID510 = 510;
    public static final int STATE_RID520 = 520;
    public static final int STATE_RID530 = 530;
    //P2P MESSAGE EXCHANGE
    public static final int STATE_RID610 = 610;
    public static final int STATE_RID620 = 620;
    //LOGOUT
    public static final int STATE_RID710 = 710;
    public static final int STATE_RID720 = 720;


    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 4444;
    public static final int LOCAL_PORT = 5555;
    
    public static byte[] publicKeyServer;
    private static final String publicKeyServerHex = "aced0005737200146a6176612e73656375726974792e4b6579526570bdf94fb3889aa5430200044c0009616c676f726974686d7400124c6a6176612f6c616e672f537472696e673b5b0007656e636f6465647400025b424c0006666f726d617471007e00014c00047479706574001b4c6a6176612f73656375726974792f4b657952657024547970653b7870740003525341757200025b42acf317f8060854e00200007870000000a230819f300d06092a864886f70d010101050003818d00308189028181009018a41594d2a378e1fd2cc4493b087b86e888e046536c749228c9bb92dd36aa8a66d79cc03a166958683296e8ecc3e8530318c879f8f2056f1fdd78112a064dc75f4c1be5f164b7d6385e1eadba3eff240ab54be9ed6e5819637eca6b6d06e60f027b9bdf0bed1b9a22a250a7d792abff62077c7f7b6be384ed5a50e4d755c50203010001740005582e3530397e7200196a6176612e73656375726974792e4b6579526570245479706500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400065055424c4943";
    
    public static ClientDetails clientData;
    public static PeerDetails peerData;

    public static ClientIM clientIM = null;
    
    public static void initialize() {
        // ChatMaster.CURRENT_STATE = ChatMaster.STATE_INITAL;
        clientData = new ClientDetails();
        // clientData.setUsername("Raghu");
        // clientData.setPwdHash(new Security().getHash("ok".getBytes()));
        RSAKeys rsaKeys = new Security().generateRSAKeys();
        clientData.setPublicKey(rsaKeys.getPublicKey());
        clientData.setPrivateKey(rsaKeys.getPrivateKey());
        ChatMaster.publicKeyServer = Security.hexToByteArray(publicKeyServerHex);
        
        Listener listener = new Listener();
        listener.setPort(LOCAL_PORT);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        System.out.println("Listener started...");
        
        // ChatMaster.handle(null);
    }
    
    public synchronized static void handle(ImEvent imEvent) {

        switch (ChatMaster.CURRENT_STATE) {


            //LOGIN BEGINS HERE
            case ChatMaster.STATE_RID210: {
                System.out.println("action in state RID210");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_220) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == imEvent.USER_EVENT) {
                    GuiEvent guiEvent = (GuiEvent) imEvent;
                    Request request = guiEvent.getRequestRecieved();

                    request.sendRequest(null);
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
                break;
            }
            case ChatMaster.STATE_RID230: {
                System.out.println("action in state RID230");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_240) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
            }
            break;
            //LIST BEGINS HERE
            case ChatMaster.STATE_RID310: {
                System.out.println("action in state RID310");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_320) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == imEvent.USER_EVENT) {
                    GuiEvent guiEvent = (GuiEvent) imEvent;
                    Request request = guiEvent.getRequestRecieved();

                    request.sendRequest(null);
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
                break;
            }
            //PERMIT BEGINS HERE
            case ChatMaster.STATE_RID410: {
                System.out.println("action in state RID410");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_420) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == imEvent.USER_EVENT) {
                    GuiEvent guiEvent = (GuiEvent) imEvent;
                    Request request = guiEvent.getRequestRecieved();

                    request.sendRequest(null);
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
                break;
            }
            //P2P AUTH
            case ChatMaster.STATE_RID510: {
                System.out.println("action in state RID510");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_520) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == ImEvent.TIMEOUT_EVENT) {
                    ChatMaster.changeState(ChatMaster.STATE_INITAL);
                }
                break;
            }
            //LOGOUT
            case ChatMaster.STATE_RID710 : {
                System.out.println("action in state RID710");
                if(imEvent.getEventType() == ImEvent.TRANSPORT_EVENT) {
                    TransportEvent transportEvent = (TransportEvent) imEvent;
                    Request request = transportEvent.getRequestRecieved();
                    if(request.getRequestId() == Request.RID_720) {

                        request.processRequest(null);
                    }
                }
                else if(imEvent.getEventType() == imEvent.USER_EVENT) {
                    GuiEvent guiEvent = (GuiEvent) imEvent;
                    Request request = guiEvent.getRequestRecieved();

                    request.sendRequest(null);
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

    public static void setClientIMObject(ClientIM clientIM) {
        ChatMaster.clientIM = clientIM;
    }
}
