package view;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private MusicStore musicStore;
    private LibraryModel library;
    private Scanner scanner;

    public View(MusicStore musicStore, LibraryModel library) {
        this.musicStore = musicStore;
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nMusic Store - Main Menu");
            System.out.println("1. Search for a song");
            System.out.println("2. Search for an album");
            System.out.println("3. View library");
            System.out.println("4. Add to library");
            System.out.println("5. Manage playlists");
            System.out.println("6. Rate a song");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    searchSong();
                    break;
                case 2:
                    searchAlbum();
                    break;
                case 3:
                    viewLibrary();
                    break;
                case 4:
                    addToLibrary();
                    break;
                case 5:
                    managePlaylists();
                    break;
                case 6:
                    rateSong();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    private void searchSong() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        ArrayList<Song> songs = musicStore.searchSongByTitle(title);
        
        if (songs.isEmpty()) {
            System.out.println("Song not found in store.");
        } else {
            for (Song s : songs) {
                System.out.println("Found: " + s.getTitle() + " by " + musicStore.searchSongByArtist(s.getTitle()));
            }
        }
    }

    private void searchAlbum() {
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();
        ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
        
        if (albums.isEmpty()) {
            System.out.println("Album not found.");
        } else {
            for (Album a : albums) {
                System.out.println("Album: " + a.getTitle() + " by " + a.getArtist());
                System.out.println("Songs: " + a.getSongs());
            }
        }
    }
    
    private void viewLibrary() {
        System.out.println("Library Songs: " + library.getSongs());
        System.out.println("Library Albums: " + library.getAlbums());
    }
    
    private void addToLibrary() {
        System.out.print("Enter album title to add: ");
        String title = scanner.nextLine();
        ArrayList<Album> albums = musicStore.searchAlbumByTitle(title);
        if (!albums.isEmpty()) {
            library.addAlbum(albums.get(0));
            System.out.println("Album added to library.");
        } else {
            System.out.println("Album not found.");
        }
    }
    
    private void managePlaylists() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        library.createPlaylist(name);
        System.out.println("Playlist " + name + " created.");
    }
    
    private void rateSong() {
        System.out.print("Enter song title to rate: ");
        String title = scanner.nextLine();
        ArrayList<Song> songs = musicStore.searchSongByTitle(title);
        if (!songs.isEmpty()) {
            System.out.print("Enter rating (1-5): ");
            int rating = scanner.nextInt();
            songs.get(0).setRating(rating);
            System.out.println("Song rated.");
        } else {
            System.out.println("Song not found.");
        }
    }
}
