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
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, pKey);
            
            //generate new AES key to encrypt the rest of the message
            byte[] AESKey = new Security().generateAESKey();
            
            //read it into a key and encrypt it using RSA
            ByteArrayInputStream bais = new ByteArrayInputStream(AESKey);
            ObjectInputStream ois = new ObjectInputStream(bais);
            SecretKey sKey = (SecretKey)ois.readObject();
            ois.close();
            
            //wrap key with RSA
            byte[] wrappedKey = cipher.wrap(sKey);
            
            //write to objectoutputstream that wrapped key.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(wrappedKey);
            
            byte[] encryptedData = new AES().AESEncrypt(AESKey, messageToEncrypt);
            ous.writeObject(encryptedData);
            ous.flush();
            return baos.toByteArray();

		} catch (Exception e) {
			System.err.println("Problem at RSAEncrypt" + e);
		}
		return null;
	}
	
	public byte[] RSADecrypt(byte[] privateKey, byte[] messageToDecrypt) {
		
		//System.out.println("RSA DEcrypt");
		
		//retrieve the RSA private key from byte array
		try {
			ObjectInputStream keyIn = new ObjectInputStream(new ByteArrayInputStream(privateKey));
			Key pKey = (Key) keyIn.readObject();
			//System.out.println("read private key:" + pKey);
            
			//setup Cipher object to encrypt using RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, pKey);
            
            //unwrap AES key
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(messageToDecrypt));
            byte[] wrappedKey = (byte[]) ois.readObject();
            
            Key sKey = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);;
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sKey);
            oos.flush(); oos.close();
            
            byte[] AESKey = baos.toByteArray();
            
            byte[] message = (byte[])ois.readObject();
            
            return new AES().AESDecrypt(AESKey, message);
            
            //return cipher.doFinal(messageToDecrypt);
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
			System.err.println("Problem at RSADecrypt: " + e);
		}
		return null;
	}
	
}
