package server;

import java.util.Timer;
import server.request.TimerTimeoutTask;

/**
 *
 * @author Raghuram
 */
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public Long getDelta() {
		return delta;
	}

	public void setDelta(Long delta) {
		this.delta = delta;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
        
    public void activateTimeout(int requestId) {
        deactivateTimeout(); //TODO: should we have this
        timeoutTimer = new Timer();
        timeoutTimer.schedule(new TimerTimeoutTask(requestId, ipAdress), TIMEOUT_DURATION);
    }
    
    public void deactivateTimeout() {
        try {
            timeoutTimer.cancel();
            timeoutTimer.purge();
        } catch (Exception ex) {}
        System.out.println("timeout cancel");
    }
}
