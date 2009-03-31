/**
 * This class is used to calculate message digest using SHA1
 * Team members don't call this class. Use Security() instead.
 */
package server.security;

import java.security.*;
/**
 * @author Abdulla
 *
 */
public class SHA1 {

	public byte[] getMessageDigest(byte[] message) {
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(message);
			return sha.digest();
		} catch (Exception e) {
			System.err.println("SHA1 Calculation error: " + e);
		}
		return null;
	}
}
