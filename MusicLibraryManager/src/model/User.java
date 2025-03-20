package model;

public class User {
	private String userName;
	private String hashedPassword;
	private String salt;
	private LibraryModel userLib;
	
	public User (String userName, String password) {
		this.userName = userName;
		userLib = new LibraryModel();
	}
	
	public LibraryModel getLibrary() {
		return this.userLib;
	}
	
}
