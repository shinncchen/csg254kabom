/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clients.transport;

/**
 *
 * @author Raghuram
 */
public class TransportData {
    private byte[] mDataToSend;
    private String mIP;
    private int mSendPort;
    private byte[] mDataReceive;
    private int mStatus;
    private String mDataToReceive;

    public byte[] getMDataToSend() {
        return mDataToSend;
    }

    public void setMDataToSend(byte[] mDataToSend) {
        this.mDataToSend = mDataToSend;
    }

    public String getMIP() {
        return mIP;
    }

    public void setMIP(String mIP) {
        this.mIP = mIP;
    }

    public int getMSendPort() {
        return mSendPort;
    }

    public void setMSendPort(int mSendPort) {
        this.mSendPort = mSendPort;
    }

    public byte[] getMDataReceive() {
        return mDataReceive;
    }

    public void setMDataReceive(byte[] mDataReceive) {
        this.mDataReceive = mDataReceive;
    }

    public int getMStatus() {
        return mStatus;
    }

    public void setMStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public String getMDataToReceive() {
        return mDataToReceive;
    }

    public void setMDataToReceive(String mDataToReceive) {
        this.mDataToReceive = mDataToReceive;
    }
}
