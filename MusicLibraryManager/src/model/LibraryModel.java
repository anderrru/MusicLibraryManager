package model;

import java.util.ArrayList;

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
