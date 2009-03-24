public class TransportData {
    byte[] mDataToSend;
    String mIP;
    int mSendPort;
    byte[] mDataReceive;
    int mStatus;
    String mMessage;

    public void SetDataToSend(byte[] data) {
        mDataToSend = data;
    }

    public void SetIP(String IP) {
        mIP = IP;
    }

    public void SetSendPort(int port) {
        mSendPort = port;
    }

    public byte[] GetDataReceive() {
        return mDataReceive;
    }

    public int GetStatus() {
        return mStatus;
    }

    public String GetMessage() {
        return mMessage;
    }

}