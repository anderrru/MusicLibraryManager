package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicStore {
	private ArrayList<Album> albums;
	private ArrayList<String> albumNames;
	
	public MusicStore() throws FileNotFoundException {
		albums = new ArrayList<Album>();
		albumNames = new ArrayList<String>();
		File myFile = new File("albums.txt");
		readAlbums(myFile);
		addAlbums(albumNames);
	}
	
	// method reads the album.txt file which sets up for reading the individual album files
	private void readAlbums(File myFile) throws FileNotFoundException {
		Scanner myScanner = new Scanner(myFile);
		while (myScanner.hasNext()) {
			// makes album name match the file naming convention of <album>_<artist>
			String line = myScanner.nextLine().replace(',', '_');
			albumNames.add(line);
		}
	}
	
	// reads through the ArrayList of album names to create a new ArrayList of Album objects which is the musicStore
	private void addAlbums(ArrayList<String> albumNames) throws FileNotFoundException {
		for (String name : albumNames) {
			// gets the text file of the album
			name += ".txt";
			File myFile = new File(name);
			Scanner myScanner = new Scanner(myFile);
			
			// parses album info
			String[] albumInfo = myScanner.nextLine().split(",");
			String title = albumInfo[0];
			String artist = albumInfo[1];
			int year = Integer.valueOf(albumInfo[3]);
			
			// create arrayList of songs
			ArrayList<Song> songs = new ArrayList<>();
			while (myScanner.hasNext()) {
				Song song = new Song(myScanner.nextLine());
				songs.add(song);
			}
			
			// creates the album and adds it to the music store
			Album album = new Album(title, artist, year, songs);
			albums.add(album);
		}
	}

	// returns arrayList of albums by artist
	public ArrayList<Album> searchAlbumByArtist(String artist) {
		ArrayList<Album> artistAlbums = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist)) artistAlbums.add(a);
		}
		return artistAlbums;
	}
	
	public ArrayList<Album> getAlbums(){
		return new ArrayList<>(albums);
	}
	
	// TODO: might need to return an arraylist instead to handle albums with the same name
	public Album searchAlbumByTitle(String title) {
		for (Album a : albums) {
			if (a.getTitle().equals(title)) return a;
		}
		return null;
	}
	
	// TODO: same thing as album when handling songs with the same name
	public Song searchSongByTitle(String title) {
		for (Album a : albums) {
			return a.getSong(title);
		}
		return null;
	}
	
	// returns arrayList of all songs by artist
	public ArrayList<Song> searchSongByArtist(String artist) {
		ArrayList<Song> artistSongs = new ArrayList<>();
		for (Album a : albums) {
			if (a.getArtist().equals(artist)) artistSongs.addAll(a.getSongs());
		}
		return artistSongs;
	}
	
}
