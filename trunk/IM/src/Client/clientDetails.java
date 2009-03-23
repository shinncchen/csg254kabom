package Client;

import java.security.Key;

/*
 * This is a getter/setter class 
 * that will be used to get/set all the client details
 * mostly from/onto session
 */

public class clientDetails {
	
	String username;		
	String pwdHash;			// hash of the password entered
	String myIP;			// clients IP address
	Key publicKey;			// new public key ... generated everytime client program is run
	Key privateKey;			// new private key
	
	// aDH;					// I AM NOT SURE IF WE NEED THIS HERE .. can add it later
	// gbModP;				// CAUSE THE DH ALGORITH SHOULD TAKE CARE OF THIS
	
	Key serverDH;			// session key established between client-server
	byte[] onlineContacts;	// array sent by server
	int sConv;  			// number of simultaneous conversations. 0 when not chatting with anyone
	
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
	 * @return the serverDH
	 */
	public Key getServerDH() {
		return serverDH;
	}
	/**
	 * @param serverDH the serverDH to set
	 */
	public void setServerDH(Key serverDH) {
		this.serverDH = serverDH;
	}
	/**
	 * @return the onlineContacts
	 */
	public byte[] getOnlineContacts() {
		return onlineContacts;
	}
	/**
	 * @param onlineContacts the onlineContacts to set
	 */
	public void setOnlineContacts(byte[] onlineContacts) {
		this.onlineContacts = onlineContacts;
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
	
	
}
