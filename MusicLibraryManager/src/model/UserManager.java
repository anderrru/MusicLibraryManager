package model;

import java.util.HashMap;

public class UserManager {

    // format for storing user: userName, User class
    HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    
}
