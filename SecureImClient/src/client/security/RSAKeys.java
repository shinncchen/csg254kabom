package client.security;

/**
 * @author HuskyHackers
 *
 * Datastructure for RSAKeys.
 * contains only two fields, PublicKey and PrivateKey
 * which are stored as byte arrays
 */

public class RSAKeys {

	private byte[] PublicKey;
	private byte[] PrivateKey;

    /**
     * Set RSA public key
     * @param p - byte[], RSA public key
     */
	public void setPublicKey(byte[] p) {
		PublicKey = p;
	}

    /**
     * Set RSA private key
     * @param p - byte[], RSA private key
     */
	public void setPrivateKey(byte[] p) {
		PrivateKey = p;
	}

    /**
     * Get RSA private key
     * @return - byte[], RSA private key
     */
	public byte[] getPrivateKey() {
		return PrivateKey;
	}

    /**
     * Get RSA public key
     * @return - byte[], RSA public key
     */
	public byte[] getPublicKey() {
		return PublicKey;
	}
}
