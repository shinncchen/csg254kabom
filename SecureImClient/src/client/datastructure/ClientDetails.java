package client.datastructure;

import java.security.Key;

/*
 * This is a getter/setter class 
 * that will be used to get/set all the client details
 * mostly from/onto session
 */

public class ClientDetails {
        
        private String username;                
        private byte[] pwdHash;                 // hash of the password entered
        private int myPort;                             // client port number
        private byte[] publicKey;                  // new public key ... generated everytime client program is run
        private byte[] privateKey;                 // new private key
        private byte[] sessionKey;                 // session key established between client-server
        private int sConv;                      // number of simultaneous conversations. 0 when not chatting with anyone
        private byte[] timeT1;
        private byte[] timeT2;
        
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public byte[] getPwdHash() {
			return pwdHash;
		}
		public void setPwdHash(byte[] pwdHash) {
			this.pwdHash = pwdHash;
		}
		public int getMyPort() {
			return myPort;
		}
		public void setMyPort(int myPort) {
			this.myPort = myPort;
		}
		public byte[] getPublicKey() {
			return publicKey;
		}
		public void setPublicKey(byte[] publicKey) {
			this.publicKey = publicKey;
		}
		public byte[] getPrivateKey() {
			return privateKey;
		}
		public void setPrivateKey(byte[] privateKey) {
			this.privateKey = privateKey;
		}
		public byte[] getSessionKey() {
			return sessionKey;
		}
		public void setSessionKey(byte[] sessionKey) {
			this.sessionKey = sessionKey;
		}
		public int getSConv() {
			return sConv;
		}
		public void setSConv(int conv) {
			sConv = conv;
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
        
}
