import java.security.Key;

/*
 * An object of this class will be created 
 * when a user wants to initiate a chat with another peer.
 */


public class peerDetails {
	
	String username;		// username of the peer whom u want to chat with or .. who initiated the chat
	// peerIP;				// peer's IP address
	//ticket[];				// this is the ticket the server sent to initiate chat
	Key peerPublicKey;		// peers public key, this will be extracted out from the ticket
	// aDH;					// a random # for the session key establishment with peer.
	Key peerDH;				// established DH key between client(me)-peer
	

}
