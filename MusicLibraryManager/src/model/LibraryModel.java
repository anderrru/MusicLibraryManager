package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;


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
        // Check if song already exists to avoid duplicates
        for (Song s : songs) {
            if (s.getTitle().equals(song.getTitle())) {
                return; // Song already exists, don't add
            }
        }
        songs.add(song);
    }

	public ArrayList<Song> getSuffledSongs() {
		ArrayList<Song> shuffledSongs = new ArrayList<>(songs);
		Collections.shuffle(shuffledSongs);
		return shuffledSongs;
	}

    // Removes a song from the library
    public void removeSong(String title) {
        songs.removeIf(song -> song.getTitle().equals(title));
        // Also remove the song from all playlists
        for (PlayList playlist : playlists) {
            playlist.removeSong(title);
        }
    }

    // Adds an album to the library and includes its songs
    public void addAlbum(Album album) {
        albums.add(album);
        songs.addAll(album.getSongs()); // Adds all songs from the album to the song list
    }

    // Method to add only a song to the library and create a partial album with just that song
    public void addSongWithPartialAlbum(Song song, Album sourceAlbum) {
        // First add the song to the library
        addSong(song);
        
        // Check if album already exists
        boolean albumExists = false;
        for (Album a : albums) {
            if (a.getTitle().equals(sourceAlbum.getTitle()) && 
                a.getArtist().equals(sourceAlbum.getArtist())) {
                albumExists = true;
                break;
            }
        }
        
        // If the album doesn't exist, create a partial album with just this song
        if (!albumExists) {
            ArrayList<Song> partialSongs = new ArrayList<>();
            partialSongs.add(song);
            Album partialAlbum = new Album(sourceAlbum.getTitle(), sourceAlbum.getArtist(), 
                                           sourceAlbum.getYear(), partialSongs, sourceAlbum.getGenre());
            albums.add(partialAlbum);
        }
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

    // Check if a song exists in the library
    public boolean hasSong(String title) {
        for (Song s : songs) {
            if (s.getTitle().equals(title))
                return true;
        }
        return false;
    }

    // Check if an album exists in the library
    public boolean hasAlbum(String title) {
        for (Album a : albums) {
            if (a.getTitle().equals(title))
                return true;
        }
        return false;
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
    
    // Returns a list of songs sorted alphabetically by title
    public ArrayList<Song> getSongsSortedByTitle() {
        ArrayList<Song> sortedSongs = new ArrayList<>(songs);
        Collections.sort(sortedSongs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.getTitle().compareToIgnoreCase(s2.getTitle());
            }
        });
        return sortedSongs;
    }
    
    // Returns a list of artists sorted alphabetically
    public ArrayList<String> getArtistsSortedByName() {
        // Use HashSet to avoid duplicates
        HashSet<String> artistSet = new HashSet<>();
        for (Album album : albums) {
            artistSet.add(album.getArtist());
        }
        
        // Convert set to list for sorting
        ArrayList<String> artistList = new ArrayList<>(artistSet);
        Collections.sort(artistList);
        return artistList;
    }
    
    // Returns a list of songs sorted by rating (highest first)
    public ArrayList<Song> getSongsSortedByRating() {
        ArrayList<Song> ratedSongs = new ArrayList<>();
        
        // Filter songs that have ratings
        for (Song song : songs) {
            if (song.hasRating()) {
                ratedSongs.add(song);
            }
        }
        
        // Sort by rating (highest first)
        Collections.sort(ratedSongs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return Integer.compare(s2.getRating(), s1.getRating());
            }
        });
        
        return ratedSongs;
    }
    
    // Returns a shuffled list of songs
    public ArrayList<Song> getShuffledSongs() {
        ArrayList<Song> shuffledSongs = new ArrayList<>(songs);
        Collections.shuffle(shuffledSongs, new Random());
        return shuffledSongs;
    }
    
    // Returns a shuffled playlist
    public PlayList getShuffledPlaylist(String playlistName) {
        PlayList original = getPlayList(playlistName);
        if (original == null) return null;
        
        PlayList shuffled = new PlayList(playlistName + " (Shuffled)");
        ArrayList<Song> songs = original.getSongs();
        Collections.shuffle(songs, new Random());
        
        for (int i = songs.size() - 1; i >= 0; i--) {
            shuffled.addSong(songs.get(i));
        }
        
        return shuffled;
    }
    
    // Search songs by genre
    public ArrayList<Song> searchSongsByGenre(String genre) {
        ArrayList<Song> genreSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getGenre() != null && song.getGenre().equalsIgnoreCase(genre)) {
                genreSongs.add(song);
            }
        }
        return genreSongs;
    }
    
    // Returns a playlist of top-rated songs (rated 4 or 5)
    public PlayList getTopRatedSongs() {
        PlayList topRated = new PlayList("Top Rated Songs");
        for (Song song : songs) {
            if (song.hasRating() && song.getRating() >= 4) {
                topRated.addSong(song);
            }
        }
        return topRated;
    }
    
    // Get playlist of genres with at least 10 songs in library of that genre
    public ArrayList<String> getPopularGenres() {
        // Count songs by genre
        java.util.HashMap<String, Integer> genreCounts = new java.util.HashMap<>();
        
        for (Song song : songs) {
            String genre = song.getGenre();
            if (genre != null && !genre.isEmpty()) {
                genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
            }
        }
        
        // Find genres with at least 10 songs
        ArrayList<String> popularGenres = new ArrayList<>();
        for (java.util.Map.Entry<String, Integer> entry : genreCounts.entrySet()) {
            if (entry.getValue() >= 10) {
                popularGenres.add(entry.getKey());
            }
        }
        
        return popularGenres;
    }
}
