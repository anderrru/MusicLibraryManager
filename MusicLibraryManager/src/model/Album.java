package model;

import java.util.ArrayList;

//Implementing genre using enum
// enum Genre {ROCK, POP, ALTERNATIVE, TRADITIONAL_COUNTRY, LATIN, SING_SONGWRITER}

public class Album {
	// class instance variables
	private String title; // Title of the album
	private int year; // Year of release
	private String artist; // Artist of the album
	private ArrayList<Song> songs; // List of songs in the album
	private Genre genre;

	// album constructor
	// Initializes album with title, artist, year, and list of songs
	public Album(String title, String artist, int year, ArrayList<Song> songs, Genre genre) {
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.songs = new ArrayList<Song>(songs); // Copies the list of songs\
		this.genre = genre;
	}

	// Returns a copy of the list of songs in the album
	public ArrayList<Song> getSongs() {
		return new ArrayList<Song>(songs);
	}

	// Returns the title of the album
	public String getTitle() {
		return title;
	}

	// Returns the artist of the album
	public String getArtist() {
		return artist;
	}
	
	public Genre getGenre() {
		return genre;
	}

	// Retrieves a song from the album by title, returns a copy of the song
	public Song getSong(String title) {
		for (Song s : songs) {
			if (s.getTitle().equals(title))
				return s.getCopy(); // Checks for the song with the given title
		}
		return null; // Returns null if the song is not found
	}

	// Creates a deep copy of the album with copies of its songs
	public Album getAlbumCopy() {
		ArrayList<Song> songs = new ArrayList<>();
		// Add copies of each song to the new album's song list
		for (Song s : this.songs) {
			songs.add(s.getCopy());
		}
		// Creates and returns a new album with the copied songs
		Album a = new Album(this.title, this.artist, this.year, songs, this.genre);
		return a;
	}

	// Returns the release year of the album
	public int getYear() {
		return year;
	}

	// not needed, just for testing
	// Returns a string representation of the album for testing purposes
	public String toString() {
		// Formats the album information into a string
		String msg = String.format("%s by %s (%d)", title, artist, year);
		return msg;
	}
}
