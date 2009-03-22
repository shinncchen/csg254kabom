import java.security.Key;

/*
 * This is a getter/setter class 
 * that will be used to get/set all the client details
 * mostly from/onto session
 */

public class clientDetails {
	
	String username;		
	String pwdHash;			// hash of the password entered
	// **** myIP;			// clients IP address
	Key publicKey;			// new public key ... generated everytime client program is run
	Key privateKey;			// new private key
	// aDH;					// randomly generate this a for Diffie-Hellman
	// gbModP;				// servers contribution to Diffie-Hellman
	Key serverDH;			// session key established between client-server
	// onlineContacts[];	// array sent by server
	int sConv;  			// number of simultaneous conversations. 0 when not chatting with anyone
	
	
}
