package view;

import model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private MusicStore musicStore; // The music store object holding all the albums and songs
	private LibraryModel library; // The user's library model containing songs, albums, and playlists
	private Scanner scanner; // Scanner to get user input

	// Constructor that initializes the music store, library, and scanner
	public View(User user) {
		try {
			this.musicStore = new MusicStore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.library = user.getLibrary();
		this.scanner = new Scanner(System.in);
	}

	// Starts the application and displays the menu options
	public void start() {
		while (true) {
			System.out.println("\nMusic Store System");
			System.out.println("1. Music Store Features");
			System.out.println("2. User Library Features");
			System.out.println("3. Logout");
			System.out.print("Choose a section: ");

			int sectionChoice = scanner.nextInt();
			scanner.nextLine(); // consume newline character

			switch (sectionChoice) {
				case 1 -> musicStoreMenu(); // Navigate to Music Store menu
				case 2 -> userLibraryMenu(); // Navigate to User Library menu
				case 3 -> {
					System.out.println("Logging out...");
					return;
				}
				default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}

	// Prompts the user to enter a song title and searches for it in the store
	private void searchSongByTitle() {
		System.out.print("Enter song title: ");
		String title = scanner.nextLine();
		ArrayList<Song> songs = musicStore.searchSongByTitle(title);
		if (songs.isEmpty()) {
			System.out.println("Song not found.");
		} else {
			for (Song s : songs) {
				System.out.println("Found: " + musicStore.getSongInfo(title));
			}
		}
	}

	// Prompts the user to enter an artist name and searches for songs by that
	// artist
	private void searchSongByArtist() {
		System.out.print("Enter artist name: ");
		String artist = scanner.nextLine();
		ArrayList<Song> songs = musicStore.searchSongByArtist(artist);
		if (songs.isEmpty()) {
			System.out.println("No songs found for artist " + artist);
		} else {
			for (Song s : songs) {
				System.out.println("Found: " + musicStore.getSongInfo(s.getTitle()));
			}
		}
	}

	// Prompts the user to enter an album title and searches for albums by that
	// title
	private void searchAlbumByTitle() {
		System.out.print("Enter album title: ");
		String title = scanner.nextLine();
		ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
		if (albums.isEmpty()) {
			System.out.println("Album not found.");
		} else {
			for (Album a : albums) {
				System.out.println("Found: " + musicStore.getAlbumInfo(title));
			}
		}
	}

	// Prompts the user to enter an artist name and searches for albums by that
	// artist
	private void searchAlbumByArtist() {
		System.out.print("Enter artist name: ");
		String artist = scanner.nextLine();
		ArrayList<Album> albums = musicStore.searchAlbumByArtist(artist);
		if (albums.isEmpty()) {
			System.out.println("No albums found for artist " + artist);
		} else {
			for (Album a : albums) {
				System.out.println("Found: " + musicStore.getAlbumInfo(a.getTitle()));
			}
		}
	}
	
	// Prompts the user to enter a song title and searches for it in the store
		private void searchLibrarySongByTitle() {
			System.out.print("Enter song title: ");
			String title = scanner.nextLine();
			ArrayList<Song> songs = library.searchSongByTitle(title);
			if (songs.isEmpty()) {
				System.out.println("Song not found.");
			} else {
				for (Song s : songs) {
					System.out.println("Found: " + s.getTitle());
				}
			}
		}

		// Prompts the user to enter an artist name and searches for songs by that
		// artist
		private void searchLibrarySongByArtist() {
			System.out.print("Enter artist name: ");
			String artist = scanner.nextLine();
			ArrayList<Song> songs = library.searchSongByArtist(artist);
			if (songs.isEmpty()) {
				System.out.println("No songs found for artist " + artist);
			} else {
				for (Song s : songs) {
					System.out.println("Found: " + s.getTitle());
				}
			}
		}

		// Prompts the user to enter an album title and searches for albums by that
		// title
		private void searchLibraryAlbumByTitle() {
			System.out.print("Enter album title: ");
			String title = scanner.nextLine();
			ArrayList<Album> albums = library.searchAlbumByTitle(title);
			if (albums.isEmpty()) {
				System.out.println("Album not found.");
			} else {
				for (Album a : albums) {
					System.out.println("Found: " + a.getTitle() + " by " + a.getArtist());
				}
			}
		}

		// Prompts the user to enter an artist name and searches for albums by that
		// artist
		private void searchLibraryAlbumByArtist() {
			System.out.print("Enter artist name: ");
			String artist = scanner.nextLine();
			ArrayList<Album> albums = library.searchAlbumByArtist(artist);
			if (albums.isEmpty()) {
				System.out.println("No albums found for artist " + artist);
			} else {
				for (Album a : albums) {
					System.out.println("Found: " + a.getTitle() + " (" + a.getYear() + ")");
				}
			}
		}

	// Prompts the user to add a song to the library
	private void addSongToLibrary() {
		System.out.print("Enter song title to add: ");
		String title = scanner.nextLine();
		ArrayList<Song> songs = musicStore.searchSongByTitle(title);
		if (!songs.isEmpty()) {
			library.addSong(songs.get(0));
			System.out.println("Song added to library.");
		} else {
			System.out.println("Song not found in store.");
		}
	}

	// Prompts the user to add an album to the library
	private void addAlbumToLibrary() {
	    System.out.print("Enter album title to add: ");
	    String title = scanner.nextLine();
	    if (title.isBlank()) {
	        System.out.println("Invalid input. Album title cannot be empty.");
	        return;
	    }
	    ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
	    if (albums.isEmpty()) {
	        System.out.println("Album \"" + title + "\" not found in the store.");
	    } else {
	        library.addAlbum(albums.get(0));
	        System.out.println("Album \"" + title + "\" added to library.");
	    }
	}

	// Lists all songs and albums in the user's library
	private void listLibraryContents() {
		System.out.println("Library songs: " + library.getSongs());
		System.out.println("Library albums: " + library.getAlbums());
	}

	// Prompts the user to create a new playlist
	private void createPlaylist() {
		System.out.print("Enter playlist name: ");
		String name = scanner.nextLine();
		library.createPlaylist(name);
		System.out.println("Playlist created.");
	}

	// Prompts the user to add a song to a specific playlist
	private void addSongToPlaylist() {
	    System.out.print("Enter playlist name: ");
	    String playlistName = scanner.nextLine();
	    if (playlistName.isBlank()) {
	        System.out.println("Invalid input. Playlist name cannot be empty.");
	        return;
	    }
	    PlayList playlist = library.getPlayList(playlistName);
	    if (playlist == null) {
	        System.out.println("Playlist \"" + playlistName + "\" does not exist.");
	        return;
	    }

	    System.out.print("Enter song title: ");
	    String songTitle = scanner.nextLine();
	    if (songTitle.isBlank()) {
	        System.out.println("Invalid input. Song title cannot be empty.");
	        return;
	    }
	    Song song = library.getSong(songTitle);
	    if (song == null) {
	        System.out.println("Song \"" + songTitle + "\" does not exist in the library.");
	        return;
	    }

	    playlist.addSong(song);
	    System.out.println("Song \"" + songTitle + "\" added to playlist \"" + playlistName + "\".");
	}

	// Prompts the user to remove a song from a specific playlist
	private void removeSongFromPlaylist() {
	    System.out.print("Enter playlist name: ");
	    String playlistName = scanner.nextLine();
	    if (playlistName.isBlank()) {
	        System.out.println("Invalid input. Playlist name cannot be empty.");
	        return;
	    }
	    PlayList playlist = library.getPlayList(playlistName);
	    if (playlist == null) {
	        System.out.println("Playlist \"" + playlistName + "\" does not exist.");
	        return;
	    }

	    System.out.print("Enter song title to remove: ");
	    String songTitle = scanner.nextLine();
	    if (songTitle.isBlank()) {
	        System.out.println("Invalid input. Song title cannot be empty.");
	        return;
	    }
	    if (!playlist.hasSong(songTitle)) {
	        System.out.println("Song \"" + songTitle + "\" does not exist in the playlist \"" + playlistName + "\".");
	        return;
	    }

	    playlist.removeSong(songTitle);
	    System.out.println("Song \"" + songTitle + "\" removed from playlist \"" + playlistName + "\".");
	}

	// Prompts the user to mark a song as favorite
	private void markSongAsFavorite() {
		System.out.print("Enter song title: ");
		String title = scanner.nextLine();
		// Additional logic to mark the song as favorite
		library.getSong(title).setFavorite();
	}

	// Prompts the user to rate a song
	private void rateSong() {
	    System.out.print("Enter song title: ");
	    String title = scanner.nextLine();
	    if (title.isBlank()) {
	        System.out.println("Invalid input. Song title cannot be empty.");
	        return;
	    }
	    Song song = library.getSong(title);
	    if (song == null) {
	        System.out.println("Song \"" + title + "\" not found in the library.");
	        return;
	    }

	    System.out.print("Enter rating (1-5): ");
	    int rating = scanner.nextInt();
	    scanner.nextLine(); // consume newline
	    if (rating < 1 || rating > 5) {
	        System.out.println("Invalid rating. Please enter a value between 1 and 5.");
	        return;
	    }

	    song.setRating(rating);
	    System.out.println("Song \"" + title + "\" rated " + rating + " stars.");
	}

	// Displays all the favorite songs in the library
	private void getFavorites() {
		if (library.getFavorites().size() == 0) {
			System.out.println("There are no current favorite songs.");
		} else {
			System.out.println(library.getFavorites());
		}
	}

	// Displays the popular genres in the library
	private void getPopularGenres() {
//		ArrayList<String> popularGenres = library.getPopularGenres();
//		if (popularGenres.isEmpty()) {
//			System.out.println("No popular genres found.");
//		} else {
//			System.out.println("Popular Genres:");
//			for (String genre : popularGenres) {
//				System.out.println("- " + genre);
//			}
//		}
	}

	private void getTopRatedSongs() {
		PlayList topRated = library.getTopRatedSongs();
		if (topRated.getSongs().isEmpty()) {
			System.out.println("No top-rated songs found.");
		} else {
			System.out.println("Top Rated Songs:");
			for (Song song : topRated.getSongs()) {
				System.out.println("- " + song.getTitle() + " (Rating: " + song.getRating() + ")");
			}
		}
	}

	private void getShuffledPlaylist() {
	    System.out.print("Enter playlist title: ");
	    String title = scanner.nextLine();
	    if (title.isBlank()) {
	        System.out.println("Invalid input. Playlist title cannot be empty.");
	        return;
	    }
	    PlayList shuffled = library.getShuffledPlaylist(title);
	    if (shuffled == null) {
	        System.out.println("Playlist \"" + title + "\" does not exist.");
	        return;
	    }
	    if (shuffled.getSongs().isEmpty()) {
	        System.out.println("No songs found in the playlist \"" + title + "\".");
	        return;
	    }

	    System.out.println("Shuffled Playlist:");
	    for (Song song : shuffled.getSongs()) {
	        System.out.println("- " + song.getTitle());
	    }
	}

	private void getSongsSortedByRating() {
		ArrayList<Song> sortedSongs = library.getSongsSortedByRating();
		if (sortedSongs.isEmpty()) {
			System.out.println("No songs found in the library.");
		} else {
			System.out.println("Songs Sorted by Rating:");
			for (Song song : sortedSongs) {
				System.out.println("- " + song.getTitle() + " (Rating: " + song.getRating() + ")");
			}
		}
	}

	private void musicStoreMenu() {
	    while (true) {
	        System.out.println("\nMusic Store Features");
	        System.out.println("1. Search Store for a song by title");
	        System.out.println("2. Search Store for a song by artist");
	        System.out.println("3. Search Store for an album by title");
	        System.out.println("4. Search Store for an album by artist");
	        System.out.println("5. Back to Main Menu");
	        System.out.print("Choose an option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine(); // consume newline character

	        switch (choice) {
	            case 1 -> searchSongByTitle();
	            case 2 -> searchSongByArtist();
	            case 3 -> searchAlbumByTitle();
	            case 4 -> searchAlbumByArtist();
	            case 5 -> {
	                return; // Go back to the main menu
	            }
	            default -> System.out.println("Invalid choice. Try again.");
	        }
	    }
	}

	private void userLibraryMenu() {
	    while (true) {
	        System.out.println("\nUser Library Features");
	        System.out.println("1. Search library for a song by title");
	        System.out.println("2. Search library for a song by artist");
	        System.out.println("3. Search library for an album by title");
	        System.out.println("4. Search library for an album by artist");
	        System.out.println("5. Add a song to library");
	        System.out.println("6. Add an album to library");
	        System.out.println("7. List library contents");
	        System.out.println("8. Create a playlist");
	        System.out.println("9. Add song to a playlist");
	        System.out.println("10. Remove song from a playlist");
	        System.out.println("11. Mark song as favorite");
	        System.out.println("12. Rate a song");
	        System.out.println("13. List Favorite Songs");
	        System.out.println("14. Get Popular Genres");
	        System.out.println("15. Get Top Rated Songs");
	        System.out.println("16. Get Shuffled Playlist");
	        System.out.println("17. Get Songs Sorted by Rating");
	        System.out.println("18. Play a Song"); // New option
	        System.out.println("19. Back to Main Menu");
	        System.out.print("Choose an option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine(); // consume newline character

	        switch (choice) {
	            case 1 -> searchLibrarySongByTitle();
	            case 2 -> searchLibrarySongByArtist();
	            case 3 -> searchLibraryAlbumByTitle();
	            case 4 -> searchLibraryAlbumByArtist();
	            case 5 -> addSongToLibrary();
	            case 6 -> addAlbumToLibrary();
	            case 7 -> listLibraryContents();
	            case 8 -> createPlaylist();
	            case 9 -> addSongToPlaylist();
	            case 10 -> removeSongFromPlaylist();
	            case 11 -> markSongAsFavorite();
	            case 12 -> rateSong();
	            case 13 -> getFavorites();
	            case 14 -> getPopularGenres();
	            case 15 -> getTopRatedSongs();
	            case 16 -> getShuffledPlaylist();
	            case 17 -> getSongsSortedByRating();
	            case 18 -> playSong(); // Call the new playSong method
	            case 19 -> {
	                return; // Go back to the main menu
	            }
	            default -> System.out.println("Invalid choice. Try again.");
	        }
	    }
	}

	private void playSong() {
	    System.out.print("Enter song title to play: ");
	    String title = scanner.nextLine();
	    if (title.isBlank()) {
	        System.out.println("Invalid input. Song title cannot be empty.");
	        return;
	    }
	    if (library.hasSong(title)) {
	        library.playSong(title);
	        System.out.println("Playing song: " + title);
	    } else {
	        System.out.println("Song \"" + title + "\" not found in the library.");
	    }
	}
}
