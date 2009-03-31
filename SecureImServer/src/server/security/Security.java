/**
 * This class has static methods that relate to
 * AES/RSA/Timestamps/Encode/Decode streams
 * Team members: use this class when you need anything
 * to do with security
 * 
 */
package server.security;

import java.io.*;

/**
 * @author Abdulla Al-Ali
 */

public class Security {
	
	private static final int RSA_KEYSIZE = 1024;
	private static final int AES_KEY_SIZE = 128;
	private static final int TIMESKEW_FOR_TIMESTAMPS = 5*60*1000; //five minutes
	
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
	 * generate AES key
	 */
	public byte[] generateAESKey() {
		return new AES().generateKey(AES_KEY_SIZE);
	}
	
	/*
	 * AES Encrypt a msg in bytes and return the cipher in bytes
	 */
	public byte[] AESEncrypt(byte[] key, byte[] message) {
		return new AES().AESEncrypt(key, message);
	}
	
	/*
	 * AES Decrypt a msg in bytes and return the plaintext in bytes
	 */
	public byte[] AESDecrypt(byte[] key, byte[] message) {
		return new AES().AESDecrypt(key, message);
	}
	
	/*
	 * Calculate Sha-1 message digest and return the hash in a byte array
	 */
	public byte[] getHash(byte[] message) {
		return new SHA1().getMessageDigest(message);
	}
	
	/*
	 * getTimeStamp
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
	
	/*
	 * method to
	 * get Long value of a byte[] timestamp (to compare with time skew)
	 */
	public Long getLongOfTimestamp(byte[] timestamp) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(timestamp));
			return ois.readLong();
		} catch (IOException e) { System.err.println("getLongofTimestamp error: " + e); }
		return null;
	}
	
	/*
	 * method to see whether time is within time skew of server
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
	
	/*
	 * converte byte[] to hex string
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

	/*
	 * convert hex string to byte[]
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

	/*
	 * test driver
	 */
	public static void main(String[] args) {
		
		//test RSA
		RSAKeys keys;
		Security security = new Security();
		keys = security.generateRSAKeys();
		System.out.println("HEX of PUBLICKEY: " + Security.byteArrayToHex(keys.getPublicKey()));
		//byte[] encrypted = security.RSAEncrypt(keys.getPublicKey(), "the kaboom is great".getBytes());
		byte[] encrypted = security.RSAEncrypt(Security.hexToByteArray(Security.byteArrayToHex(keys.getPublicKey())), "the kaboom is great".getBytes());
		byte[] decrypted = security.RSADecrypt(keys.getPrivateKey(), encrypted);
		System.out.println("Decrypted: " + new String(decrypted));
		
		//test AES
		byte[] AESkey = security.generateAESKey();
		byte[] encrypted1 = security.AESEncrypt(AESkey, "no its not".getBytes());
		byte[] decrypted2 = security.AESDecrypt(AESkey, encrypted1);
		System.out.println("Decrypted message is: " + new String(decrypted2));
		System.out.println("Decrypted hash is: " + new String(security.getHash(decrypted2)));
		System.out.println("Encrypted hash is: " + new String(security.getHash(encrypted1)));
		
		//test Timestamps
		byte[] timeBefore = security.getTimestamp();
		try { Thread.sleep(50000); } catch (Exception e) { }
		System.out.println("is time different: " + security.isTimeValid(security.getTimestamp(), timeBefore, 5L));
		
	}
	
	
	
	
}