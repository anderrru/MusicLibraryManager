package model;

import java.util.ArrayList;

// TODO: implement a PlayList class which will be used here for a user playlist
public class LibraryModel {
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	
	public LibraryModel() {
		songs = new ArrayList<>();
		albums = new ArrayList<>();
	}
	
	// TODO: make song a copy instead of a reference
	public void addSong(Song song) {
		songs.add(song);
	}
	
	// TODO: same thing as song but with album
	public void addAlbum(Album album) {
		albums.add(album);
	}
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs);
	}
	
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(albums);
	}
	
}
