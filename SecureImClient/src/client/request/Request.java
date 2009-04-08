/**
 * Abstract class for Request
 *
 * @author HuskyHackers
 */

package client.request;

import client.ChatMaster;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Request {
    
    //LOGIN
	public static final int RID_210 = 210;
    public static final int RID_220 = 220;
    public static final int RID_230 = 230;
    public static final int RID_240 = 240;
    public static final int RID_250 = 250;
    //LIST
    public static final int RID_310 = 310;
    public static final int RID_320 = 320;
    //PERMIT
    public static final int RID_410 = 410;
    public static final int RID_420 = 420;
    //P2P AUTHENTICATION
    public static final int RID_510 = 510;
    public static final int RID_520 = 520;
    public static final int RID_530 = 530;
    //P2P MESSAGE EXCHANGE
    public static final int RID_610 = 610;
    public static final int RID_620 = 620;
    //LOGOUT
    public static final int RID_710 = 710;
    public static final int RID_720 = 720;
    
    protected int requestId;
    protected String senderIp;
    protected byte[] requestData;

    /**
     * Request constructor given a request id
     * @param requestId - int, request id
     */
    public Request(int requestId) {
        this.requestId = requestId;
        try {
            this.senderIp = new String(InetAddress.getLocalHost().getHostAddress());
            //System.out.println("localIP: "+this.senderIp);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Request constructor given a request id and sender IP
     * @param requestId - int, request id
     * @param senderIp  - string, ip of the sender
     */
    public Request(int requestId, String senderIp) {
        this.requestId = requestId;
        this.senderIp = senderIp;
    }

    /**
     * Sending a Request
     * @param data - Object[], data in the Request
     */
    public abstract void sendRequest(Object[] data);

    /**
     * Processing a Request
     * @param data - Object[], data in the Request
     */
    public abstract void processRequest(Object[] data);

    /**
     * Set request id from a Request
     * @param requestId - int, request id
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * Get request id from a Request
     * @return
     */
    public int getRequestId() {
        return this.requestId;
    }

    /**
     * Set request id from a Request
     * @param senderIp - string, IP address of the sender
     */
    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    /**
     * Get IP address of the sender from a Request
     * @return - string, IP address of the sender
     */
    public String getSenderIp() {
        return this.senderIp;
    }

    /**
     * Set request data from a Request
     * @param requestData - byte[], data
     */
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    /**
     * Get request data from a Request
     * @return - byte[], data
     */
    public byte[] getRequestData() {
        return this.requestData;
    }

    /**
     * Activate a timeout from this Request
     */
    public void activateTimeout() {
        ChatMaster.activateTimeout(requestId);
    }

    /**
     * Desactivvate the timeout from this Request
     */
    public void deactivateTimeout() {
        ChatMaster.deactivateTimeout();
    }
    
}
