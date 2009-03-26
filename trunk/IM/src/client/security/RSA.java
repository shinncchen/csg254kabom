/**
 * RSA Utilities main body
 * don't use this class
 */
package client.security;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * @author Abdulla
 *
 */
public class RSA {
	/*
	 * Generates RSA Keys
	 */
	public RSAKeys generateRandomKeys(int keysize, RSAKeys keys) {
		
		try {
			KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom random = new SecureRandom();
			pairGen.initialize(keysize, random);
			KeyPair keyPair = pairGen.generateKeyPair();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(baos);
			//writing public key to stream
			out.writeObject(keyPair.getPublic());
			out.flush();
			keys.setPublicKey(baos.toByteArray());
			baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			//writing private key to stream
			out.writeObject(keyPair.getPrivate());
			System.out.println("private key written: " + keyPair.getPrivate());
			out.flush();    
			keys.setPrivateKey(baos.toByteArray());
			return keys;
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		return null;
	}
	
	public byte[] RSAEncrypt(byte[] publicKey, byte[] messageToEncrypt) {
		
		//retrieve the RSA public key from byte array
		try {
			ObjectInputStream keyIn = new ObjectInputStream(new ByteArrayInputStream(publicKey));
			Key pKey = (Key) keyIn.readObject();
			keyIn.close();
            //setup Cipher object to encrypt using RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, pKey);
            return cipher.doFinal(messageToEncrypt);
            /*System.out.println("blocksize11: " + cipher.getBlockSize());
            //setup Input and Outputstreams to encrypt
            ByteArrayInputStream bais = new ByteArrayInputStream(messageToEncrypt);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(bais);
            System.out.println("Datainputstream size: " + dis.available());
            DataOutputStream out = new DataOutputStream(baos);
            System.out.println("Before crypt");
            //out.write(cipher.getIV());
            crypt(dis, out, cipher);
            System.out.println("After crypt");
            out.flush();
            return baos.toByteArray();*/

		} catch (Exception e) {
			System.err.println("Problem at RSAEncrypt" + e);
		}
		return "loved".getBytes();
	}
	
	public byte[] RSADecrypt(byte[] privateKey, byte[] messageToDecrypt) {
		
		System.out.println("RSA DEcrypt");
		
		//retrieve the RSA private key from byte array
		try {
			ObjectInputStream keyIn = new ObjectInputStream(new ByteArrayInputStream(privateKey));
			Key pKey = (Key) keyIn.readObject();
			System.out.println("read private key:" + pKey);
            //setup Cipher object to encrypt using RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, pKey);
            return cipher.doFinal(messageToDecrypt);
            /*
            //setup Input and Outputstreams to encrypt
            ByteArrayInputStream bais = new ByteArrayInputStream(messageToDecrypt);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(bais);
            DataOutputStream out = new DataOutputStream(baos);
            crypt(dis, out, cipher);
            out.flush();
            return baos.toByteArray();*/

		} catch (Exception e) {
			System.err.println("Problem at RSADecrypt");
		}
		return "Greatness".getBytes();
	}
	
}
