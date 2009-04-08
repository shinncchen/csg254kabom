/**
 * AES utilities
 *
 * @author HuskyHackers
 */

package server.security;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class AES {
	
	/**
	 * Generate a key
	 * 
	 * @param keysize
	 * @return an AESkey in a bytearray
	 */
	public byte[] generateKey(int keysize) {
		try {
			
			//get an instance of AES.
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(keysize);
			SecureRandom random = new SecureRandom();
			keygen.init(random);
			SecretKey key = keygen.generateKey();
			
			//write it to array through streams and return it
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(baos);
			os.writeObject(key);
			os.flush();
			return baos.toByteArray();
			
		} catch (Exception e) {
			System.err.println("Err: generating AES key");
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * Encrypt a message with this key and return a byte array
	 * 
	 * @param key key to encrypt with
	 * @param message the message to encrypt
	 * @return bytearray of encrypte data
	 */
	public byte[] AESEncrypt(byte[] key, byte[] message) {
		
		try {
			//extract key
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(key));
			SecretKey sKey = (SecretKey) ois.readObject();
			
			//initiate cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            
            //initiate output and input streams
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(message));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream dos = new ObjectOutputStream(baos);
            dos.writeObject(cipher.getIV());
            
            //encrypt
            crypt(dis, dos, cipher);
            
            //flush and return bytearray
            dos.flush();
            return baos.toByteArray();
		} catch (Exception e) {
			System.err.println("AESEncrypt error");
		}
		return null;
		
	}
	
    /**
     * Decrypt a message with this key and return a byte array
     * @param key     - byte[], aes key
     * @param message - byte[], message to decrypt
     * @return        - byte[], decrypted message
     */
	public byte[] AESDecrypt(byte[] key, byte[] message) {
		try {
			//extract key
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(key));
			SecretKey sKey = (SecretKey) ois.readObject();
            
            //initiate output and input streams
            ObjectInputStream dis = new ObjectInputStream(new ByteArrayInputStream(message));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            
            //read IV
            byte[] IV = (byte[])dis.readObject();
            
			//initiate cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, sKey, iv);
            
            //decrypt
            crypt(dis, dos, cipher);
            
            //flush and return bytearray
            dos.flush();
            return baos.toByteArray();
		} catch (Exception e) {
			System.err.println("AESDecrypt error: " + e);
		}
		return null;
	}
	
	/**
	 * Encrypts or decrypts a file and outputs it to a file using
	 * a cipher that is already initiated.
	 * @param in the input file's stream
	 * @param out the output file's stream
	 * @param cipher the cipher already initiated
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static void crypt(InputStream in, OutputStream out,
			Cipher cipher) throws IOException, GeneralSecurityException {
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(blockSize);
		byte[] inBytes = new byte[blockSize];
		byte[] outBytes = new byte[outputSize];
		int inLength = 0;
		boolean more = true;
		while (more)
		{
			inLength = in.read(inBytes);
			if (inLength == blockSize)
			{
				int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
				out.write(outBytes, 0, outLength);
			}
			else more = false;         
		}
		if (inLength > 0)
			outBytes = cipher.doFinal(inBytes, 0, inLength);
		else
			outBytes = cipher.doFinal();
		out.write(outBytes);
	}
	
}
