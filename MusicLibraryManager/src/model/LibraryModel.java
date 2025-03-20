package model;

import java.util.ArrayList;
import java.util.Collections;

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

	public ArrayList<Song> getSuffledSongs() {
		ArrayList<Song> shuffledSongs = new ArrayList<>(songs);
		Collections.shuffle(shuffledSongs);
		return shuffledSongs;
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
	
	public ArrayList<Album> searchAlbumByTitle(String title) {
		ArrayList<Album> matchedAlbums = new ArrayList<>();
		for (Album a : albums) {
			if (a.getTitle().equalsIgnoreCase(title)) {
				matchedAlbums.add(a.getAlbumCopy()); // Adds album if title matches
			}
		}
		return matchedAlbums;
	}
	
	public ArrayList<Album> searchAlbumByArtist(String artist) {
		ArrayList<Album> artistAlbums = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist))
				artistAlbums.add(a.getAlbumCopy()); // Adds album if artist matches
		}
		return artistAlbums;
	}
	
	public ArrayList<Song> searchSongByArtist(String artist) {
		ArrayList<Song> artistSongs = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist))
				artistSongs.addAll(a.getAlbumCopy().getSongs()); // Adds all songs from matching albums
		}
		return artistSongs;
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
	
	public ArrayList<Song> searchSongByTitle(String title) {
		ArrayList<Song> matchedSongs = new ArrayList<>();
		for (Album a : albums) {
			for (Song s : a.getSongs()) {
				if (s.getTitle().equalsIgnoreCase(title)) {
					matchedSongs.add(s.getCopy()); // Adds song if title matches
				}
			}
		}
		return matchedSongs;
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
