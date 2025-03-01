package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore {
	private ArrayList<Album> albums; // List of albums in the music store
	private ArrayList<String> albumNames; // List of album names (titles and artists)

	// Constructor reads album names from the "albums.txt" file and creates albums
	// from individual album files
	public MusicStore() throws FileNotFoundException {
		albums = new ArrayList<Album>();
		albumNames = new ArrayList<String>();
		File myFile = new File("albums.txt");
		readAlbums(myFile); // Reads album names from "albums.txt"
		addAlbums(albumNames); // Creates albums and adds them to the music store
	}

	// Reads the "albums.txt" file and extracts the names of albums (format:
	// <album>_<artist>)
	private void readAlbums(File myFile) throws FileNotFoundException {
		Scanner myScanner = new Scanner(myFile);
		while (myScanner.hasNext()) {
			// Replaces commas with underscores to match file naming convention
			String line = myScanner.nextLine().replace(',', '_');
			albumNames.add(line); // Adds the album name to the list
		}
		myScanner.close();
	}

	// Creates Album objects from the list of album names and adds them to the
	// albums list
	private void addAlbums(ArrayList<String> albumNames) throws FileNotFoundException {
		for (String name : albumNames) {
			// Appends ".txt" to the album name to form the file name
			name += ".txt";
			File myFile = new File(name);
			Scanner myScanner = new Scanner(myFile);

			// Parses album information (title, artist, and year)
			String[] albumInfo = myScanner.nextLine().split(",");
			String title = albumInfo[0];
			String artist = albumInfo[1];
			int year = Integer.valueOf(albumInfo[3]); // Year is the fourth item in the file

			// Creates a list of songs for the album
			ArrayList<Song> songs = new ArrayList<>();
			while (myScanner.hasNext()) {
				Song song = new Song(myScanner.nextLine()); // Creates a Song object from each line
				songs.add(song); // Adds the song to the album's song list
			}
			myScanner.close();

			// Creates an Album object and adds it to the music store's album list
			Album album = new Album(title, artist, year, songs);
			albums.add(album);
		}
	}

	// Searches for albums by the artist and returns a list of matching albums
	public ArrayList<Album> searchAlbumByArtist(String artist) {
		ArrayList<Album> artistAlbums = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist))
				artistAlbums.add(a.getAlbumCopy()); // Adds album if artist matches
		}
		return artistAlbums;
	}

	// Returns a copy of the list of all albums in the music store
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(albums);
	}

	// Searches for albums by title (case-insensitive) and returns a list of
	// matching albums
	public ArrayList<Album> searchAlbumByTitle(String title) {
		ArrayList<Album> matchedAlbums = new ArrayList<>();
		for (Album a : albums) {
			if (a.getTitle().equalsIgnoreCase(title)) {
				matchedAlbums.add(a.getAlbumCopy()); // Adds album if title matches
			}
		}
		return matchedAlbums;
	}

	// Searches for songs by title (case-insensitive) and returns a list of matching
	// songs
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

	// Searches for all songs by the artist and returns a list of matching songs
	public ArrayList<Song> searchSongByArtist(String artist) {
		ArrayList<Song> artistSongs = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist))
				artistSongs.addAll(a.getAlbumCopy().getSongs()); // Adds all songs from matching albums
		}
		return artistSongs;
	}
}