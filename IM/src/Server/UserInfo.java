package Server;

import java.security.Key;

public class UserInfo {
	
	private String username;
	private String status;
	private String firstname;
	private String lastname;
	private String IP;
	private String port;
	private Key publicKey;
	private Key sessionKey;
	private int currentState;
	private byte[] timeT1;
	private byte[] timeT2;
	private int sConv;
		
	

}
