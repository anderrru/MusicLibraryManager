package model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private String name; // Name of the playlist
	private ArrayList<Song> songs; // List of songs in the playlist

	// Constructor initializes playlist with a name and an empty list of songs
	public PlayList(String name) {
		this.name = name;
		this.songs = new ArrayList<>();
	}

	// Returns the name of the playlist
	public String getName() {
		return name;
	}

	// Adds a song to the front of the playlist (index 0)
	public void addSong(Song song) {
		songs.add(0, song);
	}
	
	public void addToStart(Song song) {
		songs.add(song);
	}

	public boolean hasSong(String song) {
		for (Song s : songs) {
			if (s.getTitle().equalsIgnoreCase(song))
				return true;
		}
		return false;
	}
	
	public void setPlayList(ArrayList<Song> list) {
		this.songs = list;
	}
	
	// Removes a song from the playlist by its title
	public void removeSong(String title) {
		songs.removeIf(song -> song.getTitle().equals(title)); // Removes song if title matches
	}

	// Returns a copy of the list of songs in the playlist
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs); // Returns a new list to prevent external modification
	}
}
