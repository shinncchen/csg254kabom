package client.datastructure;

import java.security.Key;

/*
 * An object of this class will be created 
 * when a user wants to initiate a chat with another peer.
 */


public class PeerDetails {
	
	private String username;		// username of the peer whom u want to chat with or .. who initiated the chat
	private String peerIP;			// peer's IP address
	private int peerPort;
	private String firstname;
	private String lastname;
	private Key peerPublicKey;		// peers public key, this will be extracted out from the ticket
	private Key peerSessionKey;				// established DH key between client(me)-peer
	private byte[] timeT1;
	private byte[] timeT2;
	private byte[] timeTk;
	
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
	 * @return the peerIP
	 */
	public String getPeerIP() {
		return peerIP;
	}
	/**
	 * @param peerIP the peerIP to set
	 */
	public void setPeerIP(String peerIP) {
		this.peerIP = peerIP;
	}
	/**
	 * @return the peerPort
	 */
	public int getPeerPort() {
		return peerPort;
	}
	/**
	 * @param peerPort the peerPort to set
	 */
	public void setPeerPort(int peerPort) {
		this.peerPort = peerPort;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the peerPublicKey
	 */
	public Key getPeerPublicKey() {
		return peerPublicKey;
	}
	/**
	 * @param peerPublicKey the peerPublicKey to set
	 */
	public void setPeerPublicKey(Key peerPublicKey) {
		this.peerPublicKey = peerPublicKey;
	}
	/**
	 * @return the peerSessionKey
	 */
	public Key getPeerSessionKey() {
		return peerSessionKey;
	}
	/**
	 * @param peerSessionKey the peerSessionKey to set
	 */
	public void setPeerSessionKey(Key peerSessionKey) {
		this.peerSessionKey = peerSessionKey;
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
	/**
	 * @return the timeTk
	 */
	public byte[] getTimeTk() {
		return timeTk;
	}
	/**
	 * @param timeTk the timeTk to set
	 */
	public void setTimeTk(byte[] timeTk) {
		this.timeTk = timeTk;
	}
	
	
}
