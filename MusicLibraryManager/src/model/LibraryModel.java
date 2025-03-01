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
		songs.addAll(album.getSongs());
	}
	
    public void createPlaylist(String name) {
        playlists.add(new PlayList(name));
    }

    
    public PlayList getPlayList(String name) {
    	for (PlayList p : playlists) {
    		if (p.getName().equals(name)) return p;
    	}
    	return null;
    }
    
    public Song getSong(String title) {
    	for (Song s : songs) {
    		if (s.getTitle().equals(title)) return s;
    	}
    	return null;
    }

    public ArrayList<Song> getFavorites(){
    	ArrayList<Song> favorites = new ArrayList<>();
    	for (Song s : songs) {
    		if (s.isFavorite()) favorites.add(s);
    	}
    	return favorites;
    }
	
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs);
	}
	
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(albums);
	}
}
