public class TransportData {
    // Send fields
    byte[] mDataToSend;
    String mIP;
    int mPort;
    // Receive fields
    byte[] mDataReceive;
    int mStatus;
    String mMessage;

    /**
     * Constructor object for sending
     * @param IP
     * @param port
     * @param data
     */
    TransportData(String IP, int port, byte[] data) {
        mIP = IP;
        mPort = port;
        mDataToSend = data;
    }

    /**
     * Constructor object for receiving
     * @param data
     * @param message
     * @param status
     */
    TransportData(byte[] data, String message, int status) {
        mDataReceive = data;
        mStatus = status;
        mMessage = message;
    }

    
    public void SetDataToSend(byte[] data) {
        mDataToSend = data;
    }

    public void SetIP(String IP) {
        mIP = IP;
    }

    public void SetPort(int port) {
        mPort = port;
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