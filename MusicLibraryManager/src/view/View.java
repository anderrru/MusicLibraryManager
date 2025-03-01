package view;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
	private MusicStore musicStore; // The music store object holding all the albums and songs
	private LibraryModel library; // The user's library model containing songs, albums, and playlists
	private Scanner scanner; // Scanner to get user input

	// Constructor that initializes the music store, library, and scanner
	public View(MusicStore musicStore, LibraryModel library) {
		this.musicStore = musicStore;
		this.library = library;
		this.scanner = new Scanner(System.in);
	}

	// Starts the application and displays the menu options
	public void start() {
		while (true) {
			System.out.println("\nMusic Store System");
			System.out.println("1. Search for a song by title");
			System.out.println("2. Search for a song by artist");
			System.out.println("3. Search for an album by title");
			System.out.println("4. Search for an album by artist");
			System.out.println("5. Add a song to library");
			System.out.println("6. Add an album to library");
			System.out.println("7. List library contents");
			System.out.println("8. Create a playlist");
			System.out.println("9. Add song to a playlist");
			System.out.println("10. Remove song from a playlist");
			System.out.println("11. Mark song as favorite");
			System.out.println("12. Rate a song");
			System.out.println("13. List Favorite Songs");
			System.out.println("14. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline character
			switch (choice) {
			case 1 -> searchSongByTitle(); // Search for a song by title
			case 2 -> searchSongByArtist(); // Search for a song by artist
			case 3 -> searchAlbumByTitle(); // Search for an album by title
			case 4 -> searchAlbumByArtist(); // Search for an album by artist
			case 5 -> addSongToLibrary(); // Add a song to the user's library
			case 6 -> addAlbumToLibrary(); // Add an album to the user's library
			case 7 -> listLibraryContents(); // List all contents in the library
			case 8 -> createPlaylist(); // Create a new playlist
			case 9 -> addSongToPlaylist(); // Add a song to an existing playlist
			case 10 -> removeSongFromPlaylist(); // Remove a song from a playlist
			case 11 -> markSongAsFavorite(); // Mark a song as favorite
			case 12 -> rateSong(); // Rate a song
			case 13 -> getFavorites(); // List all favorite songs
			case 14 -> { // Exit the program
				System.out.println("Exiting...");
				return;
			}
			default -> System.out.println("Invalid choice. Try again."); // Handle invalid choices
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
				System.out.println("Found: " + s.getTitle());
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
				System.out.println("Found: " + s.getTitle());
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
				System.out.println("Found: " + a.getTitle() + " by " + a.getArtist());
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
		ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
		if (!albums.isEmpty()) {
			library.addAlbum(albums.get(0));
			System.out.println("Album added to library.");
		} else {
			System.out.println("Album not found in store.");
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
		System.out.print("Enter song title: ");
		String songTitle = scanner.nextLine();
		// Additional logic needed to locate and add song to playlists
		library.getPlayList(playlistName).addSong(library.getSong(songTitle));
	}

	// Prompts the user to remove a song from a specific playlist
	private void removeSongFromPlaylist() {
		System.out.print("Enter playlist name: ");
		String playlistName = scanner.nextLine();
		System.out.print("Enter song title to remove: ");
		String songTitle = scanner.nextLine();
		// Additional logic needed to locate and remove song from playlist
		library.getPlayList(playlistName).removeSong(songTitle);
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
		System.out.print("Enter rating (1-5): ");
		int rating = scanner.nextInt();
		scanner.nextLine(); // consume newline
		// Additional logic to rate the song
		library.getSong(title).setRating(rating);
	}

	// Displays all the favorite songs in the library
	private void getFavorites() {
		if (library.getFavorites().size() == 0) {
			System.out.println("There are no current favorite songs.");
		} else {
			System.out.println(library.getFavorites());
		}
	}
}
