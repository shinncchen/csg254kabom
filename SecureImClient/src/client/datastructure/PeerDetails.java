package client.datastructure;

/*
 * An object of this class will be created 
 * when a user wants to initiate a chat with another peer.
 */


public class PeerDetails {
        
        private String username;                // username of the peer whom u want to chat with or .. who initiated the chat
        private String peerIP;                  // peer's IP address
        private int peerPort;
        private byte[] peerPublicKey;           // peers public key, this will be extracted out from the ticket
        private byte[] peerSessionKey;          // established DH key between client(me)-peer
        private byte[] timeT1;
        private byte[] timeT2;
        private byte[] timeTk;
        private Long delta;
        private boolean isAuth = false;
		
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
		 * @return the peerPublicKey
		 */
		public byte[] getPeerPublicKey() {
			return peerPublicKey;
		}
		/**
		 * @param peerPublicKey the peerPublicKey to set
		 */
		public void setPeerPublicKey(byte[] peerPublicKey) {
			this.peerPublicKey = peerPublicKey;
		}
		/**
		 * @return the peerSessionKey
		 */
		public byte[] getPeerSessionKey() {
			return peerSessionKey;
		}
		/**
		 * @param peerSessionKey the peerSessionKey to set
		 */
		public void setPeerSessionKey(byte[] peerSessionKey) {
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
		/**
		 * @return the delta
		 */
		public Long getDelta() {
			return delta;
		}
		/**
		 * @param delta the delta to set
		 */
		public void setDelta(Long delta) {
			this.delta = delta;
		}
		/**
		 * @return the isAuth
		 */
		public boolean isAuth() {
			return isAuth;
		}
		/**
		 * @param isAuth the isAuth to set
		 */
		public void setAuth(boolean isAuth) {
			this.isAuth = isAuth;
		}
        
        
        
        
}
