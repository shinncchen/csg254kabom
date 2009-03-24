public class TransportData {
    Byte[] mDataToSend;
    String mIP;
    int mSendPort;
    Byte[] mDataReceive;
    int mStatus;
    String mMessage;

    public int send(String ip, int port, byte[] data) {
        return 0;
    }

    public int receive(int port, String message) {
        return 0;
    }
}
