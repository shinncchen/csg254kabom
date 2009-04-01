package client.datastructure;

import java.security.Key;

/*
 * This is a getter/setter class 
 * that will be used to get/set all the client details
 * mostly from/onto session
 */

public class ClientDetails {
	
	private String username;		
	private String pwdHash;			// hash of the password entered
	private String myIP;			// clients IP address
	private int myPort;				// client port number
	private Key publicKey;			// new public key ... generated everytime client program is run
	private Key privateKey;			// new private key
	private Key sessionKey;			// session key established between client-server
	private int sConv;			// number of simultaneous conversations. 0 when not chatting with anyone
	private byte[] timeT1;
	private byte[] timeT2;
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the pwdHash
	 */
	public String getPwdHash() {
		return pwdHash;
	}
	/**
	 * @param pwdHash the pwdHash to set
	 */
	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}
	/**
	 * @return the myIP
	 */
	public String getMyIP() {
		return myIP;
	}
	/**
	 * @param myIP the myIP to set
	 */
	public void setMyIP(String myIP) {
		this.myIP = myIP;
	}
	/**
	 * @return the myPort
	 */
	public int getMyPort() {
		return myPort;
	}
	/**
	 * @param myPort the myPort to set
	 */
	public void setMyPort(int myPort) {
		this.myPort = myPort;
	}
	/**
	 * @return the publicKey
	 */
	public Key getPublicKey() {
		return publicKey;
	}
	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	}
	/**
	 * @return the privateKey
	 */
	public Key getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(Key privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * @return the sessionKey
	 */
	public Key getSessionKey() {
		return sessionKey;
	}
	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(Key sessionKey) {
		this.sessionKey = sessionKey;
	}
	/**
	 * @return the sConv
	 */
	public int getSConv() {
		return sConv;
	}
	/**
	 * @param conv the sConv to set
	 */
	public void setSConv(int conv) {
		sConv = conv;
	}
	/**
	 * @return the timeT1
	 */
	public byte[] getTimeT1() {
		return timeT1;
	}
	/**
	 * @param timeT1 the timeT1 to set
	 */
	public void setTimeT1(byte[] timeT1) {
		this.timeT1 = timeT1;
	}
	/**
	 * @return the timeT2
	 */
	public byte[] getTimeT2() {
		return timeT2;
	}
	/**
	 * @param timeT2 the timeT2 to set
	 */
	public void setTimeT2(byte[] timeT2) {
		this.timeT2 = timeT2;
	}
	
}
	
	