/**
 * @author HuskyHackers
 *
 * This class has static methods that relate to
 * AES/RSA/Timestamps/Encode/Decode streams
 * Team members: use this class when you need anything
 * to do with security
 */

package server.security;

import java.io.*;

public class Security {
	
	private static final int RSA_KEYSIZE = 1024;
	private static final int AES_KEY_SIZE = 128;
	private static final int TIMESKEW_FOR_TIMESTAMPS = 5*60*1000; //five minutes
	
    /**
	 * Returns an RSA PublicKey/PrivateKey pair in object RSAKeys
	 * caller must create an instance of RSAKeys
     * @return - RSAKeys, RSA keys
     */
	public RSAKeys generateRSAKeys() {
		return new RSA().generateRandomKeys(RSA_KEYSIZE, new RSAKeys());
	}
	
    /**
	 * RSADecrypt a message in bytes and return a byte array
     * @param privateKey       - byte[], RSA private key
     * @param messageToDecrypt - byte[], message to decrypt
     * @return                 - byte[], decrypted message
     */
	public byte[] RSADecrypt(byte[] privateKey, byte[] messageToDecrypt) {
		return new RSA().RSADecrypt(privateKey, messageToDecrypt);
	}
	
    /**
	 * RSAEncrypt a message in bytes and return that encrypted
	 * message in a byte array
     * @param publicKey        - byte[], RSA public key
     * @param messageToEncrypt - byte[], message to encrypt
     * @return                 - byte[], encrypted message
     */
	public byte[] RSAEncrypt(byte[] publicKey, byte[] messageToEncrypt) {
		return new RSA().RSAEncrypt(publicKey, messageToEncrypt);
	}
	
    /**
     * Generate AES key
     * @return - byte[], AES key
     */
	public byte[] generateAESKey() {
		return new AES().generateKey(AES_KEY_SIZE);
	}
	
    /**
     * AES Encrypt a msg in bytes and return the cipher in bytes
     * @param key     - byte[], AES key
     * @param message - byte[], message to encrypt
     * @return        - byte[], encrypted message
     */
	public byte[] AESEncrypt(byte[] key, byte[] message) {
		return new AES().AESEncrypt(key, message);
	}
	
    /**
     * AES Decrypt a msg in bytes and return the plaintext in bytes
     * @param key     - byte[], AES key
     * @param message - byte[], message to decrypt
     * @return        - byte[], decrypted message
     */
	public byte[] AESDecrypt(byte[] key, byte[] message) {
		return new AES().AESDecrypt(key, message);
	}
	
    /**
     * Calculate Sha-1 message digest and return the hash in a byte array
     * @param message - byte[], message to digest
     * @return        - byte[], SHA-1 hash value
     */
	public byte[] getHash(byte[] message) {
		return new SHA1().getMessageDigest(message);
	}
	
    /**
     * Get Timestamp
     * @return - byte[], timestamp
     */
	public byte[] getTimestamp() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream ous = new ObjectOutputStream(baos);
			ous.writeLong(new java.util.Date().getTime());
			ous.flush();
			return baos.toByteArray();
		} catch (IOException e) { System.err.println("IOExeption at getTimeStamp(): " + e); }
		return null;
	}
	
    /**
	 * Method to get Long value of a byte[] timestamp (to compare with time skew)
     * @param timestamp - byte[], timestamp
     * @return          - byte[], timestamp in long
     */
	public Long getLongOfTimestamp(byte[] timestamp) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(timestamp));
			return ois.readLong();
		} catch (IOException e) { System.err.println("getLongofTimestamp error: " + e); }
		return null;
	}
	
    /**
	 * Method to see whether time is within time skew of server
     * @param timeReceived - byte[], received timestamp
     * @param timeSent     - byte[], sent timestamp
     * @param delta        - byte[], delta time
     * @return             - boolean, true timestamp valid
     *                                false otherwise
     */
	public boolean isTimeValid(byte[] timeReceived, byte[] timeSent, Long delta) {
		Long timeRx = new Security().getLongOfTimestamp(timeReceived);
		Long timeSnt = new Security().getLongOfTimestamp(timeSent);
		timeSnt = timeSnt + delta;
		Long difference = timeRx-timeSnt;
		if (difference<0) difference = timeSnt-timeRx;
		if (difference <= Security.TIMESKEW_FOR_TIMESTAMPS) return true;
		else return false;
	}
	
    /**
     * Converte byte[] to hex string
     * @param b - byte[], byte array to convert
     * @return  - string, hexadecimal value of byte[]
     */
	public static String byteArrayToHex(byte[] b) {
		try {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		} catch (Exception e) { System.err.println("Error at getHexString: " + e); }
		return "";
	}

    /**
     * Convert hex string to byte[]
     * @param s - string, hexadecimal value to convert
     * @return  - byte[], byte array representation of the hexadecimal
     */
	public static byte[] hexToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
    /**
     * Calculate delta
     * @param localTime  - byte[], local time
     * @param remoteTime - byte[], remote time
     * @return           - long, time delta
     */
	public Long clcDelta(byte[] localTime, byte[] remoteTime) {
		return this.getLongOfTimestamp(localTime)-this.getLongOfTimestamp(remoteTime);
	}

}