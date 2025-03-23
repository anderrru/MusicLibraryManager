package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {

    // format for storing user: userName, User class
    HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }
    
    public void createUser(String userName, String password) {
    	if (!checkUserExists(userName)) {
    		User user = new User(userName, password);
    		addUser(user);
    	}
    }
    
	public boolean checkUserExists(String userName) {
		File myFile = new File("users.txt");
		Scanner myScanner;
		try {
			myScanner = new Scanner(myFile);
			while (myScanner.hasNext()) {
				String name = myScanner.next().split(",")[0].strip();
				if (userName.equals(name)) {
					myScanner.close(); 
					return true;
				}
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User loginUser(String userName, String password) {
		User user = users.get(userName);
		if (user.checkPassword(password)) {
			return user;
		}
		return null;
	}

    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }


}
