/**
 * Class containing info about a client
 *
 * @author HuskyHackers
 */

package server;

import java.util.Timer;
import server.request.TimerTimeoutTask;

public class UserInfo {

	private static int CURRENT_STATE = 0;
    public static final int STATE_INITAL = 10;
    //LOGIN
    public static final int STATE_LOGIN = 20;
    public static final int STATE_RID210 = 210;
    public static final int STATE_RID220 = 220;
    public static final int STATE_RID230 = 230;
    public static final int STATE_RID240 = 240;
    public static final int STATE_RID250 = 250;
    //LIST
    public static final int STATE_RID310 = 310;    
    public static final int STATE_RID320 = 320;
    //PERMIT
    public static final int STATE_RID410 = 410;
    public static final int STATE_RID420 = 420;
    //P2P AUTHENTICATION
    public static final int STATE_RID510 = 510;
    public static final int STATE_RID520 = 520;
    public static final int STATE_RID530 = 530;
    //P2P MESSAGE EXCHANGE
    public static final int STATE_RID610 = 610;
    public static final int STATE_RID620 = 620;
    //LOGOUT
    public static final int STATE_RID710 = 710;
    public static final int STATE_RID720 = 720;
    
    private String username;
    private String ipAdress;
    private int port;
    private byte[] publicKey;
    private byte[] sessionKey;
    private int currentState = STATE_INITAL;
    private byte[] challenge;
    private byte[] timeT1;
    private byte[] timeT2;
    private Long delta;
    private boolean isLoggedIn = false;
    private Timer timeoutTimer;
    private long TIMEOUT_DURATION = 5000;

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
     * Get user IP address
     * @return - string, IP address
     */
    public String getIpAdress() {
        return ipAdress;
    }

    /**
     * Set user IP address
     * @param ipAdress - stirng, IP address
     */
    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    /**
     * Get user port number
     * @return - int, port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Set user port number
     * @param port - int, port number
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get user RSA public key
     * @return - byte[] public key
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * Set user RSA public key
     * @param publicKey - byte[] public key
     */
    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Get user AES session key
     * @return - byte[], AES session key
     */
    public byte[] getSessionKey() {
        return sessionKey;
    }

    /**
     * Set user AES session key
     * @param sessionKey - byte[], AES session key
     */
    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * Get user current state
     * @return - int, state
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Set user new State
     * @param currentState - int, new state
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     * Get the challenge received from a protocol
     * @return - byte[], challenge received
     */
    public byte[] getChallenge() {
        return challenge;
    }

    /**
     * Set the challenge received from a protocol
     * @param challenge - byte[], challenge receive
     */
    public void setChallenge(byte[] challenge) {
        this.challenge = challenge;
    }

    /**
     * Get the T1 timestamp received from a protocol
     * @return - byte[], challenge received
     */
    public byte[] getTimeT1() {
        return timeT1;
    }

    /**
     * Set the T1 timestamp receive from a protocol
     * @param timeT1 - byte[], challenge receive
     */
    public void setTimeT1(byte[] timeT1) {
        this.timeT1 = timeT1;
    }

    /**
     * Get the T2 timestamp received from a protocol
     * @return - byte[], challenge received
     */
    public byte[] getTimeT2() {
        return timeT2;
    }

    /**
     * Set the T2 timestamp receive from a protocol
     * @param timeT2 - byte[], challenge receive
     */
    public void setTimeT2(byte[] timeT2) {
        this.timeT2 = timeT2;
    }

    /**
     * Get T(delta), time delay between transmission
     * @return - long, T(delta)
     */
    public Long getDelta() {
		return delta;
	}

    /**
     * Get T(delta), time delay between transmission
     * @param delta - long, T(delta)
     */
	public void setDelta(Long delta) {
		this.delta = delta;
	}

    /**
     * Is the current user login to the server
     * @return - boolean, true if he/she is login
     *                    false otherwise
     */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

    /**
     * Set the login state of the user to the server
     * @param isLoggedIn - boolean, true if he/she is login
     *                     false otherwise
     */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

    /**
     * Activate a timer for a specific request id
     * @param requestId - int, request id
     */
    public void activateTimeout(int requestId) {
        deactivateTimeout();
        timeoutTimer = new Timer();
        timeoutTimer.schedule(new TimerTimeoutTask(requestId, ipAdress), TIMEOUT_DURATION);
    }

    /**
     * Desactivate a timer for a specific request id
     */
    public void deactivateTimeout() {
        try {
            timeoutTimer.cancel();
            timeoutTimer.purge();
        } catch (Exception ex) {}
        System.out.println("timeout cancel");
    }
}
