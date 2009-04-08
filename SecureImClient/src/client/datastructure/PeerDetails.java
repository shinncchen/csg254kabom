/*
 * This is a getter/setter class that will be used
 * to get/set all the peer client details mostly from/onto session
 *
 * @author HuskyHackers
 */

package client.datastructure;

public class PeerDetails {

    private String username;                // username of the peer whom u want to chat with or .. who initiated the chat
    private String peerIP;                  // peer's IP address
    private int peerPort;                   // port number used by the peer client
    private byte[] peerPublicKey;           // peers public key, this will be extracted out from the ticket
    private byte[] peerSessionKey;          // established DH key between client(me)-peer
    private byte[] timeT1;                  // T1 timestamp recorded by peer client
    private byte[] timeT2;                  // T2 timestamp recorded by peer client
    private byte[] timeTk;
    private Long delta;                     // timestamp delta
    private boolean isAuth = false;         // if peer client is been autenthicated to the server

    /**
     * Get the username of the current peer client
     * @return - string, the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the peer client
     * @param - string, username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get IP of the current peer client
     * @return - string, the peerIP
     */
    public String getPeerIP() {
        return peerIP;
    }

    /**
     * Set the IP of the peer client
     * @param peerIP - string, the peerIP to set
     */
    public void setPeerIP(String peerIP) {
        this.peerIP = peerIP;
    }

    /**
     * Get the port number of the peer client
     * @return the peerPort - int, the port number
     */
    public int getPeerPort() {
        return peerPort;
    }

    /**
     * Set the port number of the peer client
     * @param peerPort - int, the peerPort to set
     */
    public void setPeerPort(int peerPort) {
        this.peerPort = peerPort;
    }

    /**
     * Get the RSA public key of the current peer client
     * @return - byte[], the peerPublicKey
     */
    public byte[] getPeerPublicKey() {
        return peerPublicKey;
    }

    /**
     * Set the RSA public key of the peer client
     * @param peerPublicKey - byte[], the peerPublicKey
     */
    public void setPeerPublicKey(byte[] peerPublicKey) {
        this.peerPublicKey = peerPublicKey;
    }

    /**
     * Get the AES session key of the current peer client
     * @return - byte[], the AES session key
     */
    public byte[] getPeerSessionKey() {
        return peerSessionKey;
    }

    /**
     * Set the AES session key of the peer client
     * @param peerSessionKey - byte[], the AES session key
     */
    public void setPeerSessionKey(byte[] peerSessionKey) {
        this.peerSessionKey = peerSessionKey;
    }

    /**
     * Get the T1 timestamp of the current peer client
     * @return - byte[], T1 timestamp
     */
    public byte[] getTimeT1() {
        return timeT1;
    }

    /**
     * Set the T1 timestamp of the current peer client
     * @param - byte[], T1 timestamp
     */
    public void setTimeT1(byte[] timeT1) {
        this.timeT1 = timeT1;
    }

    /**
     * Set the T2 timestamp of the current peer client
     * @return - byte[], T2 timestamp
     */
    public byte[] getTimeT2() {
        return timeT2;
    }

    /**
     * Set the T2 timestamp of the current peer client
     * @param timeT2 - byte[], T2 timestamp
     */
    public void setTimeT2(byte[] timeT2) {
        this.timeT2 = timeT2;
    }

    /**
     * Set the Tk timestamp of the current peer client
     * @return - byte[], Tk timestamp
     */
    public byte[] getTimeTk() {
        return timeTk;
    }

    /**
     * Set the Tk timestamp of the current peer client
     * @param timeTk - byte[], Tk timestamp
     */
    public void setTimeTk(byte[] timeTk) {
        this.timeTk = timeTk;
    }

    /**
     * Get the T(delta) of the current peer client
     * @return - long, T(delta)
     */
    public Long getDelta() {
        return delta;
    }

    /**
     * Set the T(delta) of the current peer client
     * @param delta - long, T(delta)
     */
    public void setDelta(Long delta) {
        this.delta = delta;
    }

    /**
     * Get peer user authentification status to the server
     * @return - boolean, is peer user authenticated
     */
    public boolean isAuth() {
        return isAuth;
    }

    /**
     * Set peer user authentification status to the server
     * @param isAuth - boolean, true if is authenticated
     *                          false otherwise
     */
    public void setAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }
}
