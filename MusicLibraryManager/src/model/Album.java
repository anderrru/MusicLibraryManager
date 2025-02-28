package model;

import java.util.ArrayList;

//Implementing genre using enum
// enum Genre {ROCK, POP, ALTERNATIVE, TRADITIONAL_COUNTRY, LATIN, SING_SONGWRITER}

public class Album {
	// class instance variables
	private String title;
	private int year;
	private String artist;
	private ArrayList<Song> songs;
	
	// album constructor
	public Album(String title, String artist, int year, ArrayList<Song> songs) {
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.songs = new ArrayList<Song>(songs);
	}
	
	public ArrayList<Song> getSongs(){
		return new ArrayList<Song>(songs);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public Song getSong(String title) {
		for (Song s : songs) {
			if (s.getTitle().equals(title)) return s.getCopy();
		}
		return null;
	}
	
	public Album getAlbumCopy() {
		ArrayList<Song> songs = new ArrayList<>();
		for (Song s : this.songs) {
			songs.add(s.getCopy());
		}
		Album a = new Album(this.title, this.artist, this.year, songs);
		return a;
	}
	
	public int getYear() {
		return year;
	}
	
	// not needed, just for testing
	public String toString() {
		String msg = String.format("%s, %d, %s: ", artist, year, title);
		return msg + songs + "\n";
	}
}
