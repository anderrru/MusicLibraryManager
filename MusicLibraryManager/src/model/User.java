package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class User {
	private String userName;
	private byte[] hashedPassword;
	private byte[] salt;
	private LibraryModel userLib;
	
	public User (String userName, String password){
		this.userName = userName;
		userLib = new LibraryModel();
		this.salt = createSalt();
		this.hashedPassword = generateHash(password, this.salt);

		String line = userName + ',' + hashedPassword + ',' + salt;

		// write data to file
		BufferedWriter writter;
		try {
			writter = new BufferedWriter(new FileWriter("users.txt", true));
			writter.newLine();
			writter.write(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// generate salt for password securtiy
	public byte[] createSalt(){
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	// hash the password with the salt
	public byte[] generateHash(String password, byte[] salt) {
		MessageDigest md;
		byte[] ret = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			// prepend salt to password
			md.update(salt);
			// hash the salt + password 
			ret = md.digest(password.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean checkPassword(String guessedPassword){
		byte[] hashedGuess = generateHash(guessedPassword, this.salt);
		return Arrays.equals(hashedGuess, hashedPassword);
	}
	
	public String getUserName() {
		return this.userName;
	}

	public LibraryModel getLibrary() {
		return this.userLib;
	}
	
}
