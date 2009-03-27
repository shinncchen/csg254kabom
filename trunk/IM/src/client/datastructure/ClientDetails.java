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
	private Key publicKey;			// new public key ... generated everytime client program is run
	private Key privateKey;			// new private key
	private Key sessionKey;			// session key established between client-server
	private int sConv;			// number of simultaneous conversations. 0 when not chatting with anyone
	private byte[] timeT1;
	
}
	
	