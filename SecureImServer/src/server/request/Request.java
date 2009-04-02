/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.request;

import java.net.InetAddress;
import java.net.UnknownHostException;
import server.UserInfo;

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
    protected int senderPort;
    protected byte[] requestData;
    
    public Request(int requestId) {
        this.requestId = requestId;
        try {
            this.senderIp = new String(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
    
    public Request(int requestId, String senderIp) {
        this.requestId = requestId;
        this.senderIp = senderIp;
    }
    
    public abstract void sendRequest(UserInfo userInfo, Object[] data);
    
    public abstract void processRequest(UserInfo userInfo, Object[] data);

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public void setSenderPort(int senderPort) {
    	this.senderPort = senderPort;
    }
    
    public String getSenderIp() {
        return this.senderIp;
    }

    public int getSenderPort() {
    	return this.senderPort;
    }
    
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }
    
    public byte[] getRequestData() {
        return this.requestData;
    }
    
}
