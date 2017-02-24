import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private final String TRANSFORMATION = "AES/OFB8/PKCS5Padding";

	private SecretKeySpec secretKey;
	private byte[] key;

	private IvParameterSpec ivParameterSpec;

	private void setKey(String myKey) throws Exception {

		MessageDigest sha;
		
		key = myKey.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);

		ivParameterSpec = new IvParameterSpec(key);

		secretKey = new SecretKeySpec(key, "AES");

	}

	public int encrypt(ByteBuffer input, ByteBuffer output, String secret) throws Exception {

		setKey(secret);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
		return cipher.doFinal(input, output);

	}

	public byte[] encrypt(byte[] dataToEncrypt, String secret) throws Exception {

		setKey(secret);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
		return cipher.doFinal(dataToEncrypt);

	}

	public int decrypt(ByteBuffer input, ByteBuffer output, String secret) throws Exception {

		setKey(secret);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		return cipher.doFinal(input, output);

	}

	public byte[] decrypt(byte[] dataToDecrypt, String secret) throws Exception {

		setKey(secret);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		return cipher.doFinal(dataToDecrypt);

	}

	public void encryptFile(String inputFilename, String outputFilename, String secret) throws Exception {
		processFile(inputFilename, outputFilename, secret, Cipher.ENCRYPT_MODE);
	}

	public void decryptFile(String inputFilename, String outputFilename, String secret) throws Exception {
		processFile(inputFilename, outputFilename, secret, Cipher.DECRYPT_MODE);
	}

	private void processFile(String inputFilename, String outputFilename, String secret, int mode) throws Exception {

		final int CAPACITY = (int) new File(inputFilename).length();

		FileInputStream in = new FileInputStream(inputFilename);
		FileOutputStream out = new FileOutputStream(outputFilename);

		setKey(secret);
		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		if (mode == Cipher.DECRYPT_MODE)
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		if (mode == Cipher.ENCRYPT_MODE)
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

		CipherOutputStream chOut = new CipherOutputStream(out, cipher);

		byte[] buffer = new byte[CAPACITY];
		int len;
		while ((len = in.read(buffer)) > 0) {
			chOut.write(buffer, 0, len);
		}

		chOut.flush();
		chOut.close();

		in.close();
		out.close();

	}

	public static void usage() throws Exception {
		
		PrintStream log = System.out;
		
		AES aes= new AES();
		
		final String TRANSFORMATION = aes.TRANSFORMATION;
		
		long currTime = System.currentTimeMillis();
		String secret = String.valueOf(currTime);
		
		aes.setKey(secret);
		
		final int keyLenght = aes.key.length;
		
		String keyValue = "";
		for (byte value : aes.key) {
			keyValue = keyValue + String.format("%x ", value);
		}
		
		log.println("AES Eaxmple");
		log.println("");
		log.println("Usage:");
		log.println(" java AES [ encrypt | decrypt ] inputFile outputFile secret");
		log.println("");
		log.println("Example:");
		log.println(" java AES encrypt /home/john/report1.txt /tmp/report1.txt.c mysecret1234");
		log.println("");
		log.println("Transformation: " + TRANSFORMATION);
		log.println("    Key length: " + keyLenght + " [ " + keyValue + "]");
		log.println("");
		log.println("Author: igor.delac@gmail.com");
		log.println("");
		
	}
	
	public static void main(String[] args) throws Exception {

		boolean foundKeyword = false;

		String method = null, inputFile = null, outputFile = null, key = null;

		if (args.length == 0) {
			usage();
			return;
		}
		
		for (String arg : args) {
			
			if (arg.equalsIgnoreCase("-h") || arg.equalsIgnoreCase("--help")) {
				usage();
				return;
			}

			if (arg.equalsIgnoreCase("decrypt") || arg.equalsIgnoreCase("encrypt")) {
				foundKeyword = true;
			}

			if (foundKeyword) {

				if (method == null) {
					if (arg.equalsIgnoreCase("decrypt")) {
						method = arg;
					}

					if (arg.equalsIgnoreCase("encrypt")) {
						method = arg;
					}

					continue;
				}

				if (inputFile == null) {
					inputFile = arg;
					continue;
				}

				if (outputFile == null) {
					outputFile = arg;
					continue;
				}

				if (key == null) {
					key = arg;
					continue;
				}

			}
		}

		if (method != null && inputFile != null && outputFile != null && key != null) {

			if (method.equalsIgnoreCase("decrypt")) {
				AES aes = new AES();
				aes.decryptFile(inputFile, outputFile, key);
			}

			if (method.equalsIgnoreCase("encrypt")) {
				AES aes = new AES();
				aes.encryptFile(inputFile, outputFile, key);
			}

		} else if (method != null && inputFile != null) {
			if (method.equalsIgnoreCase("encrypt")) {
				new MainWindow("AES " + method, inputFile, inputFile + ".c", Cipher.ENCRYPT_MODE).setVisible(true);
			}
			if (method.equalsIgnoreCase("decrypt")) {
				if (inputFile.endsWith(".c")) {
					new MainWindow("AES " + method, inputFile, inputFile.substring(0, inputFile.length() - 2), Cipher.DECRYPT_MODE)
							.setVisible(true);
				} else {
					new MainWindow("AES " + method, inputFile, inputFile + ".decrypted", Cipher.DECRYPT_MODE).setVisible(true);
				}
			}
		}

	}

}
