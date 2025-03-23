package view;

import model.User;
import model.UserManager;
import java.util.Scanner;

public class LoginView {
    private UserManager userManager;
    private Scanner scanner;

    public LoginView(UserManager userManager) {
        this.userManager = userManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayLoginScreen() {
        System.out.println("Welcome to the Login System");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User user = userManager.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUserName());
            View view = new View(user);
            view.start();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void registerUser() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        
        if (userManager.checkUserExists(username)) {
            System.out.println("Username already taken. Please choose another.");
        } else {
            userManager.createUser(username, password);
            System.out.println("User registered successfully! You can now log in.");
        }
    }
}
