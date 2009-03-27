package client.datastructure;

import java.security.Key;

/*
 * An object of this class will be created 
 * when a user wants to initiate a chat with another peer.
 */


public class PeerDetails {
	
	private String username;		// username of the peer whom u want to chat with or .. who initiated the chat
	private String peerIP;			// peer's IP address
	private int port;
	private String firstname;
	private String lastname;
	private Key peerPublicKey;		// peers public key, this will be extracted out from the ticket
	private Key peerSessionKey;				// established DH key between client(me)-peer
	private byte[] timeT1;
	private byte[] timeT2;
	private byte[] timeTk;
	
	
}
