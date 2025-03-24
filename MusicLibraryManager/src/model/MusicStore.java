package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MusicStore {
    private ArrayList<Album> albums; // List of albums in the music store
    private HashMap<Album, ArrayList<Song>> albumSongMap;
    private ArrayList<String> albumNames; // List of album names (titles and artists)

    // Constructor reads album names from the "albums.txt" file and creates albums
    // from individual album files
    public MusicStore() throws FileNotFoundException {
        albums = new ArrayList<Album>();
        albumNames = new ArrayList<String>();
        albumSongMap = new HashMap<>();
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
			String genreName = albumInfo[2].toUpperCase().replace(' ', '_').replace('/', '_');
			Genre genre = Genre.valueOf(genreName);
			int year = Integer.valueOf(albumInfo[3]); // Year is the fourth item in the file


            // Creates a list of songs for the album
            ArrayList<Song> songs = new ArrayList<>();
            while (myScanner.hasNext()) {
                Song song = new Song(myScanner.nextLine()); // Creates a Song object from each line
                songs.add(song); // Adds the song to the album's song list
            }
            myScanner.close();


            // Creates an Album object and adds it to the music store's album list
            Album album = new Album(title, artist, year, songs, genre);
            albumSongMap.put(album, songs);
            albums.add(album);
            
        }
    }


    // Searches for albums by the artist and returns a list of matching albums
    public ArrayList<Album> searchAlbumByArtist(String artist) {
        ArrayList<Album> artistAlbums = new ArrayList<>();
        for (Album a : albumSongMap.keySet()) {
            if (a.getArtist().equalsIgnoreCase(artist)) {
                artistAlbums.add(a);
            }
        }
        return artistAlbums;
    }

    // Returns a copy of the list of all albums in the music store
    public ArrayList<Album> getAlbums() {
        ArrayList<Album> newAlbums = new ArrayList<>();
        for (Album a : albums) {
            newAlbums.add(a.getAlbumCopy());
        }
        return newAlbums;
    }

    // Searches for albums by title (case-insensitive) and returns a list of
    // matching albums
    public ArrayList<Album> searchAlbumByTitle(String title) {
        ArrayList<Album> matchedAlbums = new ArrayList<>();
        for (Album a : albumSongMap.keySet()) {
            if (a.getTitle().equalsIgnoreCase(title)) {
                matchedAlbums.add(a);
            }
        }
        return matchedAlbums;
    }

    // Searches for songs by title (case-insensitive) and returns a list of matching
    // songs
    public ArrayList<Song> searchSongByTitle(String title) {
        ArrayList<Song> matchedSongs = new ArrayList<>();
        for (Map.Entry<Album, ArrayList<Song>> entry : albumSongMap.entrySet()) {
            ArrayList<Song> songs = entry.getValue();

            for (Song song : songs) {
                if (song.getTitle().equalsIgnoreCase(title)) {
                    matchedSongs.add(song);
                }
            }
        }
        return matchedSongs;
    }

    // Searches for all songs by the artist and returns a list of matching songs
    public ArrayList<Song> searchSongByArtist(String artist) {
        ArrayList<Song> artistSongs = new ArrayList<>();
        for (Entry<Album, ArrayList<Song>> entry : albumSongMap.entrySet()) {
            Album album = entry.getKey();
            ArrayList<Song> songs = entry.getValue();

            if (album.getArtist().equalsIgnoreCase(artist)) {
                artistSongs.addAll(songs); // Add all songs from this album
            }
        }
        return artistSongs;
    }
    
    // Get information about a song including album it belongs to
    public String getSongInfo(String title) {
        for (Album a : albums) {
            for (Song s : a.getSongs()) {
                if (s.getTitle().equalsIgnoreCase(title)) {
                    // Format: Song Title - Album: [Album Name] - Artist: [Artist Name] - Year: [Year]
                    return String.format("Song: %s - Album: %s - Artist: %s - Year: %d - Genre: %s", 
                                        s.getTitle(), a.getTitle(), a.getArtist(), a.getYear(), a.getGenre());
                }
            }
        }
        return "Song not found in store catalog.";
    }
    
    // Get information about an album
    public String getAlbumInfo(String title) {
        for (Album a : albums) {
            if (a.getTitle().equalsIgnoreCase(title)) {
                StringBuilder info = new StringBuilder();
                info.append(String.format("Album: %s - Artist: %s - Year: %d - Genre: %s\n", 
                                         a.getTitle(), a.getArtist(), a.getYear(), a.getGenre()));
                info.append("Songs:\n");
                for (Song s : a.getSongs()) {
                    info.append(" - ").append(s.getTitle()).append("\n");
                }
                return info.toString();
            }
        }
        return "Album not found in store catalog.";
    }
}
