package main;

import java.io.FileNotFoundException;

import model.LibraryModel;
import model.MusicStore;
import view.View; // Import the View class

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		MusicStore store = new MusicStore();
		LibraryModel userLib = new LibraryModel();
		View view = new View(store, userLib);
		view.start();
	}
}