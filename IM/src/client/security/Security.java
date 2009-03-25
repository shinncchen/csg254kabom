/**
 * This class has static methods that relate to
 * AES/RSA/Timestamps/Encode/Decode streams
 * 
 */
package client.security;

/**
 * @author Abdulla Al-Ali
 *
 */

public class Security {
	
	private static final int RSA_KEYSIZE = 1024;
	
	/*
	 * Returns an RSA PublicKey/PrivateKey pair in object RSAKeys
	 * caller must create an instance of RSAKeys
	 */
	public RSAKeys generateRSAKeys() {
		return new RSA().generateRandomKeys(RSA_KEYSIZE, new RSAKeys());
	}
	
	/*
	 * RSADecrypt a message in bytes and return a byte array
	 */
	public byte[] RSADecrypt(byte[] privateKey, byte[] messageToDecrypt) {
		return new RSA().RSADecrypt(privateKey, messageToDecrypt);
	}
	
	/*
	 * RSAEncrypt a message in bytes and return that encrypted
	 * message in a byte array
	 */
	public byte[] RSAEncrypt(byte[] publicKey, byte[] messageToEncrypt) {
		return new RSA().RSAEncrypt(publicKey, messageToEncrypt);
	}
	
	/*
	 * AES Encrypt a msg in bytes and return the cipher in bytes
	 */
	public byte[] AESEncrypt(byte[] message) {
		return null;
	}
	
	/*
	 * AES Decrypt a msg in bytes and return the plaintext in bytes
	 */
	public byte[] AESDecrypt(byte[] message) {
		return null;
	}
	
	/*
	 * test driver
	 */
	public static void main(String[] args) {
		RSAKeys keys;
		Security security = new Security();
		keys = security.generateRSAKeys();
		System.out.println("Generated");
		byte[] encrypted = security.RSAEncrypt(keys.getPublicKey(), keys.getPublicKey());
		System.out.println("Encrypted: " + new String(encrypted));
		
		//System.out.println("Do i reach here????");
		//security.RSADecrypt(keys.getPrivateKey(), encrypted);
		byte[] decrypted = security.RSADecrypt(keys.getPrivateKey(), encrypted);
		//System.out.println(decrypted==null);
		System.out.println("Decrypted: " + new String(decrypted));
	}
	
	
	
	
}