/**
 * RSA Utilities main body
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

		System.out.println("blocksize: " + blockSize);
		int intLength = in.read(inBytes);
		System.out.println("bytes read from input stream: " + intLength);
		System.out.println("");
		int inLength = 0;;
		boolean more = true;
		while (more)
		{
			inLength = in.read(inBytes);
			//System.out.println("Still more?");
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
	/*
	private void encryptFile() { 
		System.out.println("Ecrypting file: " + filename2 + " using pubkey: " + filename1);
		System.out.println("Output: " + filename2+"-encrypted");
		try {

			//filename1 is public key
			//filename2 is file to be encrypted
			
			//get an instance of AES.
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(AES_KEY_SIZE);
            SecureRandom random = new SecureRandom();
            keygen.init(random);
            SecretKey key = keygen.generateKey();

            //retrieve RSA's public key
            ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(filename1));
            Key publicKey = (Key) keyIn.readObject();
            keyIn.close();            

            //wrap the AES key with the public key of RSA
            //and write it to file.
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, publicKey);
            byte[] wrappedKey = cipher.wrap(key);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filename2+"-encrypted"));
            out.writeInt(wrappedKey.length);
            out.write(wrappedKey);

            //encode filename2 with the AES that we have
            //using CBC mode
            InputStream in = new FileInputStream(filename2);
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            //must also write out the generated IV to encrypted file
            //no wrapping here, security risk ?
            out.write(cipher.getIV());
            
            //encryption process
            crypt(in, out, cipher);
            in.close();
            out.close();

		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		System.out.println("Done!");
		*/
	
}
