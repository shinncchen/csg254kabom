package server;

/**
 *
 * @author Raghuram
 */
public class UserInfo {

    public static final int STATE_INITAL = 10;
    public static final int STATE_RID220 = 220;
    public static final int STATE_RID240 = 240;
    public static final int STATE_RID260 = 260;
    public static final int STATE_LOGIN = 20;
    
    private String username;
    private String firstname;
    private String lastname;
    private String ipAdress;
    private int port;
    private byte[] publicKey;
    private byte[] sessionKey;
    private int currentState = STATE_INITAL;
    private byte[] challenge;
    private byte[] timeT1;
    private byte[] timeT2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public byte[] getChallenge() {
        return challenge;
    }

    public void setChallenge(byte[] challenge) {
        this.challenge = challenge;
    }

    public byte[] getTimeT1() {
        return timeT1;
    }

    public void setTimeT1(byte[] timeT1) {
        this.timeT1 = timeT1;
    }

    public byte[] getTimeT2() {
        return timeT2;
    }

    public void setTimeT2(byte[] timeT2) {
        this.timeT2 = timeT2;
    }
}
