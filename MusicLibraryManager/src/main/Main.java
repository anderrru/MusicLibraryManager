package main;

import java.io.FileNotFoundException;

import model.LibraryModel;
import model.MusicStore;
import model.UserManager;
import view.LoginView;
import view.View; // Import the View class

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		UserManager um = new UserManager();
		LoginView loginView = new LoginView(um);
		loginView.displayLoginScreen();
	}
}