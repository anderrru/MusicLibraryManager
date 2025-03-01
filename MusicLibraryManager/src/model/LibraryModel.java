package model;

import java.util.ArrayList;

public class LibraryModel {
	private ArrayList<Song> songs; // List of songs in the library
	private ArrayList<Album> albums; // List of albums in the library
	private ArrayList<PlayList> playlists; // List of playlists in the library

	// Constructor initializes the lists of songs, albums, and playlists
	public LibraryModel() {
		songs = new ArrayList<>();
		albums = new ArrayList<>();
		playlists = new ArrayList<>();
	}

	// Adds a song to the library
	public void addSong(Song song) {
		songs.add(song);
	}

	// Adds an album to the library and includes its songs
	public void addAlbum(Album album) {
		albums.add(album);
		songs.addAll(album.getSongs()); // Adds all songs from the album to the song list
	}

	// Creates a new playlist with the given name
	public void createPlaylist(String name) {
		playlists.add(new PlayList(name));
	}

	// Searches for a playlist by name and returns it
	public PlayList getPlayList(String name) {
		for (PlayList p : playlists) {
			if (p.getName().equals(name))
				return p; // Finds and returns the playlist with the matching name
		}
		return null; // Returns null if no playlist is found with that name
	}

	// Searches for a song by title and returns it
	public Song getSong(String title) {
		for (Song s : songs) {
			if (s.getTitle().equals(title))
				return s; // Finds and returns the song with the matching title
		}
		return null; // Returns null if the song is not found
	}

	// Returns a list of all favorite songs
	public ArrayList<Song> getFavorites() {
		ArrayList<Song> favorites = new ArrayList<>();
		for (Song s : songs) {
			if (s.isFavorite())
				favorites.add(s); // Adds songs to the list that are marked as favorites
		}
		return favorites;
	}

	// Returns a copy of the list of songs in the library
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(songs);
	}

	// Returns a copy of the list of albums in the library
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(albums);
	}
}
