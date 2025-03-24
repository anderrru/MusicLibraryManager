package view;

import model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            System.out.println("Error loading music store: " + e.getMessage());
        }
        this.library = user.getLibrary();
        this.scanner = new Scanner(System.in);
    }

    // Starts the application and displays the main menu options
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
                case 1 -> musicStoreMenu();
                case 2 -> userLibraryMenu();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Music Store Features Menu
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
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> searchSongByTitle();
                case 2 -> searchSongByArtist();
                case 3 -> searchAlbumByTitle();
                case 4 -> searchAlbumByArtist();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // User Library Features Menu
    private void userLibraryMenu() {
        while (true) {
            System.out.println("\nUser Library Features");
            System.out.println("1. Search library for a song by title");
            System.out.println("2. Search library for a song by artist");
            System.out.println("3. Search library for an album by title");
            System.out.println("4. Search library for an album by artist");
            System.out.println("5. Add a song to library (with partial album)");
            System.out.println("6. Add an album to library");
            System.out.println("7. Remove a song from library");
            System.out.println("8. List library contents");
            System.out.println("9. Create a playlist");
            System.out.println("10. Add song to a playlist");
            System.out.println("11. Remove song from a playlist");
            System.out.println("12. Mark song as favorite");
            System.out.println("13. Rate a song");
            System.out.println("14. List Favorite Songs");
            System.out.println("15. Get Popular Genres (at least 10 songs per genre)");
            System.out.println("16. Get Top Rated Songs (rated 4 or 5)");
            System.out.println("17. Get Shuffled Playlist (for a specific playlist)");
            System.out.println("18. Get Songs Sorted by Rating");
            System.out.println("19. Get Songs Sorted by Title");
            System.out.println("20. Get Artists Sorted by Name");
            System.out.println("21. Get Shuffled Songs (all songs)");
            System.out.println("22. Search library for songs by genre");
            System.out.println("23. Show 10 Recently Played Songs");
            System.out.println("24. Show 10 Frequently Played Songs");
            System.out.println("25. Play a Song");
            System.out.println("26. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> searchSongByTitle();
                case 2 -> searchSongByArtist();
                case 3 -> searchAlbumByTitle();
                case 4 -> searchAlbumByArtist();
                case 5 -> addSongToLibrary();
                case 6 -> addAlbumToLibrary();
                case 7 -> removeSongFromLibrary();
                case 8 -> listLibraryContents();
                case 9 -> createPlaylist();
                case 10 -> addSongToPlaylist();
                case 11 -> removeSongFromPlaylist();
                case 12 -> markSongAsFavorite();
                case 13 -> rateSong();
                case 14 -> listFavoriteSongs();
                case 15 -> getPopularGenres();
                case 16 -> getTopRatedSongs();
                case 17 -> getShuffledPlaylist();
                case 18 -> getSongsSortedByRating();
                case 19 -> listSongsSortedByTitle();
                case 20 -> listArtistsSortedByName();
                case 21 -> getShuffledSongs();
                case 22 -> searchSongByGenre();
                case 23 -> showRecentPlays();
                case 24 -> showFrequentPlays();
                case 25 -> playSong();
                case 26 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ---------------- Music Store Functionality ----------------

    private void searchSongByTitle() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        ArrayList<Song> songs = musicStore.searchSongByTitle(title);
        if (songs.isEmpty()) {
            System.out.println("Song not found in store.");
        } else {
            for (Song s : songs) {
                String info = musicStore.getSongInfo(s.getTitle());
                if (library.hasSong(s.getTitle())) {
                    info += " - (Already in your library)";
                }
                System.out.println("Found: " + info);
            }
        }
    }

    private void searchSongByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        ArrayList<Song> songs = musicStore.searchSongByArtist(artist);
        if (songs.isEmpty()) {
            System.out.println("No songs found for artist " + artist + " in store.");
        } else {
            for (Song s : songs) {
                String info = musicStore.getSongInfo(s.getTitle());
                if (library.hasSong(s.getTitle())) {
                    info += " - (Already in your library)";
                }
                System.out.println("Found: " + info);
            }
        }
    }

    private void searchAlbumByTitle() {
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();
        ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
        if (albums.isEmpty()) {
            System.out.println("Album not found in store.");
        } else {
            for (Album a : albums) {
                String info = musicStore.getAlbumInfo(a.getTitle());
                System.out.println("Found: " + info);
            }
        }
    }

    private void searchAlbumByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        ArrayList<Album> albums = musicStore.searchAlbumByArtist(artist);
        if (albums.isEmpty()) {
            System.out.println("No albums found for artist " + artist + " in store.");
        } else {
            for (Album a : albums) {
                String info = musicStore.getAlbumInfo(a.getTitle());
                System.out.println("Found: " + info);
            }
        }
    }

    // ---------------- User Library Functionality ----------------

    // Updated to add the song with its partial album (only songs that are in library)
    private void addSongToLibrary() {
        System.out.print("Enter song title to add: ");
        String title = scanner.nextLine();
        ArrayList<Song> songs = musicStore.searchSongByTitle(title);
        if (!songs.isEmpty()) {
            Song song = songs.get(0);
            Album sourceAlbum = null;
            for (Album album : musicStore.getAlbums()) {
                for (Song s : album.getSongs()) {
                    if (s.getTitle().equalsIgnoreCase(song.getTitle())) {
                        sourceAlbum = album;
                        break;
                    }
                }
                if (sourceAlbum != null) break;
            }
            if (sourceAlbum != null) {
                library.addSongWithPartialAlbum(song, sourceAlbum);
                System.out.println("Song and its partial album added to library.");
            } else {
                library.addSong(song);
                System.out.println("Song added to library (source album not found).");
            }
        } else {
            System.out.println("Song not found in store.");
        }
    }

    // Adds the full album to the library
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

    // Removes a song from the library
    private void removeSongFromLibrary() {
        System.out.print("Enter song title to remove from library: ");
        String title = scanner.nextLine();
        if (library.hasSong(title)) {
            library.removeSong(title);
            System.out.println("Song \"" + title + "\" removed from library.");
        } else {
            System.out.println("Song \"" + title + "\" not found in library.");
        }
    }

    private void listLibraryContents() {
        System.out.println("Library Songs:");
        for (Song song : library.getSongs()) {
            System.out.println("- " + song.getTitle() + " (Rating: " + song.getRating() + ")");
        }
        System.out.println("Library Albums:");
        for (Album album : library.getAlbums()) {
            System.out.println("- " + album.getTitle() + " by " + album.getArtist());
        }
    }

    private void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            System.out.println("Invalid input. Playlist name cannot be empty.");
            return;
        }
        library.createPlaylist(name);
        System.out.println("Playlist \"" + name + "\" created.");
    }

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
            System.out.println("Song \"" + songTitle + "\" does not exist in playlist \"" + playlistName + "\".");
            return;
        }

        playlist.removeSong(songTitle);
        System.out.println("Song \"" + songTitle + "\" removed from playlist \"" + playlistName + "\".");
    }

    private void markSongAsFavorite() {
        System.out.print("Enter song title to mark as favorite: ");
        String title = scanner.nextLine();
        Song song = library.getSong(title);
        if (song == null) {
            System.out.println("Song \"" + title + "\" not found in library.");
            return;
        }
        song.setFavorite();
        System.out.println("Song \"" + title + "\" marked as favorite.");
    }

    private void rateSong() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        if (title.isBlank()) {
            System.out.println("Invalid input. Song title cannot be empty.");
            return;
        }
        Song song = library.getSong(title);
        if (song == null) {
            System.out.println("Song \"" + title + "\" not found in library.");
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

    private void listFavoriteSongs() {
        ArrayList<Song> favorites = library.getFavorites();
        if (favorites.isEmpty()) {
            System.out.println("There are no favorite songs in your library.");
        } else {
            System.out.println("Favorite Songs:");
            for (Song song : favorites) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    // Returns popular genres (with at least 10 songs) from the library.
    // Assumes that your LibraryModel now provides an updated getPopularGenres() method.
    private void getPopularGenres() {
        ArrayList<String> popularGenres = library.getPopularGenres();
        if (popularGenres.isEmpty()) {
            System.out.println("No popular genres found (at least 10 songs per genre required).");
        } else {
            System.out.println("Popular Genres:");
            for (String genre : popularGenres) {
                System.out.println("- " + genre);
            }
        }
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
        System.out.print("Enter playlist name for shuffling: ");
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
            System.out.println("No songs with ratings found in the library.");
        } else {
            System.out.println("Songs Sorted by Rating (Highest to Lowest):");
            for (Song song : sortedSongs) {
                System.out.println("- " + song.getTitle() + " (Rating: " + song.getRating() + ")");
            }
        }
    }

    private void listSongsSortedByTitle() {
        ArrayList<Song> sortedSongs = library.getSongsSortedByTitle();
        if (sortedSongs.isEmpty()) {
            System.out.println("No songs found in the library.");
        } else {
            System.out.println("Songs Sorted by Title:");
            for (Song song : sortedSongs) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    private void listArtistsSortedByName() {
        ArrayList<String> artists = library.getArtistsSortedByName();
        if (artists.isEmpty()) {
            System.out.println("No artists found in the library.");
        } else {
            System.out.println("Artists Sorted by Name:");
            for (String artist : artists) {
                System.out.println("- " + artist);
            }
        }
    }

    private void getShuffledSongs() {
        ArrayList<Song> shuffledSongs = library.getShuffledSongs();
        if (shuffledSongs.isEmpty()) {
            System.out.println("No songs available to shuffle.");
        } else {
            System.out.println("Shuffled Songs:");
            for (Song song : shuffledSongs) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    private void searchSongByGenre() {
        System.out.print("Enter genre (e.g., ROCK, POP, ALTERNATIVE, TRADITIONAL_COUNTRY, LATIN, SINGER_SONGWRITER): ");
        String genreInput = scanner.nextLine().toUpperCase().replace(' ', '_');
        Genre genre;
        try {
            genre = Genre.valueOf(genreInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre entered.");
            return;
        }
        ArrayList<Song> genreSongs = library.searchSongsByGenre(genre);
        if (genreSongs.isEmpty()) {
            System.out.println("No songs found for genre: " + genre);
        } else {
            System.out.println("Songs in genre " + genre + ":");
            for (Song song : genreSongs) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    // Assumes that the LibraryModel now maintains an automatic playlist of the 10 most recently played songs.
    private void showRecentPlays() {
        PlayList recentPlays = library.getRecentPlaysPlaylist();
        if (recentPlays == null || recentPlays.getSongs().isEmpty()) {
            System.out.println("No recent plays available.");
        } else {
            System.out.println("10 Most Recently Played Songs:");
            for (Song song : recentPlays.getSongs()) {
                System.out.println("- " + song.getTitle());
            }
        }
    }

    // Assumes that the LibraryModel now maintains an automatic playlist of the 10 most frequently played songs.
    private void showFrequentPlays() {
        PlayList frequentPlays = library.getFrequentPlaysPlaylist();
        if (frequentPlays == null || frequentPlays.getSongs().isEmpty()) {
            System.out.println("No frequent plays available.");
        } else {
            System.out.println("10 Most Frequently Played Songs:");
            for (Song song : frequentPlays.getSongs()) {
                System.out.println("- " + song.getTitle());
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
