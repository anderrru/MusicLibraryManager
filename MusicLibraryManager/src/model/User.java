package model;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
	private String userName;
	private byte[] hashedPassword;
	private byte[] salt;
	private LibraryModel userLib;
	
	public User (String userName, String password) throws NoSuchAlgorithmException, IOException {
		this.userName = userName;
		userLib = new LibraryModel();
		this.salt = createSalt();
		this.hashedPassword = generatePassword(password);

		String line = userName + ',' + hashedPassword + ',' + salt;

		// write data to file
		FileWriter fw = new FileWriter("users.txt");
		fw.write(line);
		fw.close();
	}

	// generate salt for password securtiy
	public byte[] createSalt(){
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	// hash the password with the salt
	public byte[] generatePassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		return md.digest(password.getBytes());
	}
	
	public String getUserName() {
		return this.userName;
	}

	public LibraryModel getLibrary() {
		return this.userLib;
	}
	
}
