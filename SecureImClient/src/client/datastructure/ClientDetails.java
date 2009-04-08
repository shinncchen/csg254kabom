/*
 * This is a getter/setter class that will be used
 * to get/set all the client details mostly from/onto session
 *
  * @author HuskyHackers
 */

package client.datastructure;

public class ClientDetails {

    private String username;
    private byte[] pwdHash;                   // hash of the password entered
    private int myPort;                       // client port number
    private byte[] publicKey;                 // new public key ... generated everytime client program is run
    private byte[] privateKey;                // new private key
    private byte[] sessionKey;                // session key established between client-server
    private int sConv;                        // number of simultaneous conversations. 0 when not chatting with anyone
    private byte[] timeT1;                    // T1 timestamp recorded by the client
    private byte[] timeT2;                    // T2 timestamp recorded by the client
    boolean isLogin = false;                  // login state of the client

    /**
     * Retrieve the username for the current client
     * @return - string, returning the username
     */
    public String getUsername() {
        return username;
    }

        /**
         * Set the username for the client
         * @param username - string, given a username
         */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the hash password for the current client
     * @return - byte[], the password hash
     */
    public byte[] getPwdHash() {
        return pwdHash;
    }

    /**
     * Set the hash password for the client
     * @param pwdHash - byte[], the password hash
     */
    public void setPwdHash(byte[] pwdHash) {
        this.pwdHash = pwdHash;
    }

    /**
     * Get the port number used by the current client
     * @return - int, port number
     */
    public int getMyPort() {
        return myPort;
    }

    /**
     * Set the port number used the client
     * @param myPort - int, port number
     */
    public void setMyPort(int myPort) {
        this.myPort = myPort;
    }

    /**
     * Get the RSA public key used by the current client
     * @return - byte[], RSA public key
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * Set the RSA public key for the client
     * @param publicKey - byte[], RSA public key
     */
    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Get the RSA private key used by the current client
     * @return - byte[], RSA private key
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Set the RSA private key for the client
     * @param privateKey - byte[], given a RSA private key
     */
    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Get the AES session key for the current client
     * @return - byte[], return the AES session key
     */
    public byte[] getSessionKey() {
        return sessionKey;
    }

    /**
     * Set session key for the client
     * @param sessionKey - byte[], return the AES session key
     */
    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * Get the number of conversation(s) the client is currently having
     * @return - int, number of conversation(s)
     */
    public int getSConv() {
        return sConv;
    }

    /**
     * Set the number of converstation(s) the client is having
     * @param conv
     */
    public void setSConv(int conv) {
        sConv = conv;
    }

    /**
     * Get the T1 timestamp for the current client
     * @return - byte[], T1 timestamp
     */
    public byte[] getTimeT1() {
        return timeT1;
    }

    /**
     * Set the T1 timestamp for the current client
     * @param timeT1
     */
    public void setTimeT1(byte[] timeT1) {
        this.timeT1 = timeT1;
    }

    /**
     * Get the T2 timestamp for the current client
     * @return - byte[], T2 timestamp
     */
    public byte[] getTimeT2() {
        return timeT2;
    }

    /**
     * Set the T2 timestamp for the current client
     * @param timeT2 - byte[], T2 timestamp
     */
    public void setTimeT2(byte[] timeT2) {
        this.timeT2 = timeT2;
    }

    /**
     * Get the current login status of the client
     * @return - boolean, true if client is login
     *                    false otherwise
     */
    public boolean getIsLogin() {
        return isLogin;
    }

    /**
     * Set the current login status of the client
     * @return - boolean, true if client is login
     *                    false otherwise
     */
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}