/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.request;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raghuram
 */
public abstract class Request {
    
    public static final int RID_210 = 210;
    public static final int RID_220 = 220;
    public static final int RID_230 = 230;
    public static final int RID_240 = 240;
    public static final int RID_250 = 250;
    
    protected int requestId;
    protected String senderIp;
    protected byte[] requestData;
    
    public Request(int requestId) {
        this.requestId = requestId;
        try {
            this.senderIp = new String(InetAddress.getLocalHost().getAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
    
    public Request(int requestId, String senderIp) {
        this.requestId = requestId;
        this.senderIp = senderIp;
    }
    
    public abstract void sendRequest(Object[] data);
    
    public abstract void processRequest(Object[] data);

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public String getSenderIp() {
        return this.senderIp;
    }

    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }
    
    public byte[] getRequestData() {
        return this.requestData;
    }
    
}
