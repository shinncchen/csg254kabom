package Clients;

import java.security.Key;

/*
 * An object of this class will be created 
 * when a user wants to initiate a chat with another peer.
 */


public class peerDetails {
	
	String username;		// username of the peer whom u want to chat with or .. who initiated the chat
	String peerIP;			// peer's IP address
	byte[] ticket;			// this is the ticket the server sent to initiate chat
	Key peerPublicKey;		// peers public key, this will be extracted out from the ticket
	
	// aDH;					// I AM NOT SURE IF WE NEED THIS HERE .. can add it later
	
	Key peerDH;				// established DH key between client(me)-peer

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
	 * @return the ticket
	 */
	public byte[] getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(byte[] ticket) {
		this.ticket = ticket;
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
	 * @return the peerDH
	 */
	public Key getPeerDH() {
		return peerDH;
	}

	/**
	 * @param peerDH the peerDH to set
	 */
	public void setPeerDH(Key peerDH) {
		this.peerDH = peerDH;
	}
	

}
