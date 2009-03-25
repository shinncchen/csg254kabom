/*
 * Datastructure for RSAKeys.
 * contains only two fields, PublicKey and PrivateKey
 * which are stored as byte arrays
 */
package client.security;

/**
 * @author Abdulla
 *
 */
public class RSAKeys {

	private byte[] PublicKey;
	private byte[] PrivateKey;
	
	public void setPublicKey(byte[] p) {
		PublicKey = p;
	}
	public void setPrivateKey(byte[] p) {
		PrivateKey = p;
	}
	
	public byte[] getPrivateKey() {
		return PrivateKey;
	}
	public byte[] getPublicKey() {
		return PublicKey;
	}
	
}
