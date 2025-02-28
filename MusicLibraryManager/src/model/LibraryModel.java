package model;

import java.util.ArrayList;


//Playlist class for user-defined song collections
class PlayList {
 private String name;
 private ArrayList<Song> songs;

 public PlayList(String name) {
     this.name = name;
     this.songs = new ArrayList<>();
 }

 public String getName() {
     return name;
 }

 public void addSong(Song song) {
     songs.add(song);
 }

 public void removeSong(String title) {
     songs.removeIf(song -> song.getTitle().equals(title));
 }

 public ArrayList<Song> getSongs() {
     return new ArrayList<>(songs);
 }
}


public class LibraryModel {
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
    private ArrayList<PlayList> playlists;

	
	public LibraryModel() {
		songs = new ArrayList<>();
		albums = new ArrayList<>();
        playlists = new ArrayList<>();

	}
	
	// TODO: make song a copy instead of a reference
	public void addSong(Song song) {
		songs.add(song);
	}
	
	// TODO: same thing as song but with album
	public void addAlbum(Album album) {
		albums.add(album);
	}
	
    public void createPlaylist(String name) {
        playlists.add(new PlayList(name));
    }

	
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs);
	}
	
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(albums);
	}
	
}
