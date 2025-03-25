package testCases;

import model.LibraryModel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Album;
import model.Genre;
import model.PlayList;
import model.Song;

public class LibraryModelTest {
	
    private LibraryModel library;
    private Song song1, song2, song3;
    private Album album1, album2;

    @BeforeEach
    void setUp() {
        library = new LibraryModel();

        song1 = new Song("Song A");
        song2 = new Song("Song B");
        song3 = new Song("Song C");
        
        song1.setRating(5);
        song2.setRating(4);
        song3.setRating(3);

        ArrayList<Song> albumSongs = new ArrayList<>();
        albumSongs.add(song1);
        albumSongs.add(song2);
        album1 = new Album("Album 1", "Artist 1", 2023, albumSongs, Genre.POP);

        ArrayList<Song> albumSongs2 = new ArrayList<>();
        albumSongs2.add(song3);
        album2 = new Album("Album 2", "Artist 2", 2024, albumSongs2, Genre.ROCK);

        library.addAlbum(album1);
        library.addAlbum(album2);
    }

//    @Test
//    void testGetPopularGenres() {
//        ArrayList<String> genres = library.getPopularGenres();
//        System.out.println(genres);
//        assertTrue(genres.contains("Pop") || genres.contains("Rock"));
//    }

    @Test
    void testAddSongWithPartialAlbum() {
        Song newSong = new Song("New Song");
        Album partialAlbum = new Album("New Album", "Artist 3", 2025, new ArrayList<>(), Genre.ALTERNATIVE);
        
        library.addSongWithPartialAlbum(newSong, partialAlbum);
        
        assertTrue(library.hasSong("New Song"));
        assertTrue(library.hasAlbum("New Album"));
    }

    @Test
    void testSearchSongByTitle() {
        ArrayList<Song> foundSongs = library.searchSongByTitle("Song A");
        assertEquals(1, foundSongs.size());
        assertEquals("Song A", foundSongs.get(0).getTitle());
    }

    @Test
    void testGetShuffledPlaylist() {
        library.createPlaylist("My Playlist");
        PlayList playlist = library.getPlayList("My Playlist");
        playlist.addSong(song1);
        playlist.addSong(song2);

        PlayList shuffled = library.getShuffledPlaylist("My Playlist");
        assertNotNull(shuffled);
        assertEquals(2, shuffled.getSongs().size());
    }

    @Test
    void testGetSongsSortedByRating() {
        ArrayList<Song> sortedSongs = library.getSongsSortedByRating();
        assertEquals("Song A", sortedSongs.get(0).getTitle()); // Highest rated should be first
    }

    @Test
    void testGetArtistsSortedByName() {
        ArrayList<String> artists = library.getArtistsSortedByName();
        assertEquals(2, artists.size());
        assertEquals("Artist 1", artists.get(0));
    }

    @Test
    void testSearchSongsByGenre() {
        ArrayList<Song> popSongs = library.searchSongsByGenre(Genre.valueOf("POP"));
        assertEquals(2, popSongs.size());
    }

    @Test
    void testGetTopRatedSongs() {
        PlayList topRated = library.getTopRatedSongs();
        assertEquals(2, topRated.getSongs().size()); // Only songs rated 4 or above
    }

    @Test
    void testSearchSongByArtist() {
        ArrayList<Song> artistSongs = library.searchSongByArtist("Artist 1");
        assertEquals(2, artistSongs.size());
    }

    @Test
    void testSearchAlbumByArtist() {
        ArrayList<Album> albums = library.searchAlbumByArtist("Artist 1");
        assertEquals(1, albums.size());
        assertEquals("Album 1", albums.get(0).getTitle());
    }

    @Test
    void testSearchAlbumByTitle() {
        ArrayList<Album> albums = library.searchAlbumByTitle("Album 1");
        assertEquals(1, albums.size());
    }

    @Test
    void testRemoveSong() {
        library.removeSong("Song A");
        assertFalse(library.hasSong("Song A"));
    }

    @Test
    void testHasAlbum() {
        assertTrue(library.hasAlbum("Album 1"));
    }

    @Test
    void testHasSong() {
        assertTrue(library.hasSong("Song A"));
    }

    @Test
    void testGetSongsSortedByTitle() {
        ArrayList<Song> sortedSongs = library.getSongsSortedByTitle();
        assertEquals("Song A", sortedSongs.get(0).getTitle());
    }

    @Test
    void testGetShuffledSongs() {
        ArrayList<Song> shuffledSongs = library.getShuffledSongs();
        assertEquals(3, shuffledSongs.size());
    }

    @Test
    public void testAddSongAndGetSong() {
        LibraryModel library = new LibraryModel();
        Song song = new Song("Test Song");
        library.addSong(song);
        Song retrieved = library.getSong("Test Song");
        assertNotNull(retrieved);
        assertEquals("Test Song", retrieved.getTitle());
        // test for non-existent song
        assertNull(library.getSong("Nonexistent"));
    }

    @Test
    public void testAddAlbumAndGetSongsAndAlbums() {
        LibraryModel library = new LibraryModel();
        ArrayList<Song> albumSongs = new ArrayList<>();
        albumSongs.add(new Song("Album Song 1"));
        albumSongs.add(new Song("Album Song 2"));
        Album album = new Album("Album Title", "Album Artist", 2021, albumSongs, Genre.POP);
        library.addAlbum(album);
        // Verify album list
        ArrayList<Album> albums = library.getAlbums();
        assertEquals(1, albums.size());
        // Verify songs added from the album
        ArrayList<Song> songs = library.getSongs();
        assertTrue(songs.stream().anyMatch(s -> s.getTitle().equals("Album Song 1")));
        assertTrue(songs.stream().anyMatch(s -> s.getTitle().equals("Album Song 2")));
    }

    @Test
    public void testCreateAndGetPlaylist() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("MyPlaylist");
        PlayList playlist = library.getPlayList("MyPlaylist");
        assertNotNull(playlist);
        assertEquals("MyPlaylist", playlist.getName());
        // Verify non-existing playlist returns null
        assertNull(library.getPlayList("NoPlaylist"));
    }

    @Test
    public void testFavorites() {
        LibraryModel library = new LibraryModel();
        Song song1 = new Song("Song1");
        Song song2 = new Song("Song2");
        // song2 will be marked favorite when rating is 5
        song2.setRating(5);
        library.addSong(song1);
        library.addSong(song2);
        ArrayList<Song> favorites = library.getFavorites();
        assertEquals(1, favorites.size());
        assertEquals("Song2", favorites.get(0).getTitle());
    }

        @Test
        public void testGetMostFreqPlayedSongs() {
            // Create a LibraryModel instance
            LibraryModel library = new LibraryModel();

            // Create and add songs with varying play counts
            Song song1 = new Song("Song 1"); // 3 plays
            Song song2 = new Song("Song 2"); // 10 plays
            Song song3 = new Song("Song 3"); // 7 plays
            Song song4 = new Song("Song 4"); // 15 plays
            Song song5 = new Song("Song 5"); // 5 plays
            Song song6 = new Song("Song 6"); // 20 plays
            Song song7 = new Song("Song 7"); // 8 plays
            Song song8 = new Song("Song 8"); // 12 plays
            Song song9 = new Song("Song 9"); // 1 play
            Song song10 = new Song("Song 10"); // 6 plays
            Song song11 = new Song("Song 11"); // 4 plays

            // Add songs to the library
            library.addSong(song1);
            library.addSong(song2);
            library.addSong(song3);
            library.addSong(song4);
            library.addSong(song5);
            library.addSong(song6);
            library.addSong(song7);
            library.addSong(song8);
            library.addSong(song9);
            library.addSong(song10);
            library.addSong(song11);
            
            song1.playAmt(3);
            song2.playAmt(10);
            song3.playAmt(7);
            song4.playAmt(15);
            song5.playAmt(5);
            song6.playAmt(20);
            song7.playAmt(8);
            song8.playAmt(12);
            song9.playAmt(1);
            song10.playAmt(6);
            song11.playAmt(4);

            // Get the most frequently played songs
            PlayList mostFreqPlayed = library.getFrequentPlaysPlaylist();

            // Verify the playlist contains exactly 10 songs
            assertEquals(10, mostFreqPlayed.getSongs().size(), "The playlist should contain 10 songs.");

            // Verify the songs are sorted by play count in descending order
            ArrayList<Song> songs = mostFreqPlayed.getSongs();
            assertEquals("Song 6", songs.get(0).getTitle(), "The most played song should be 'Song 6'.");
            assertEquals("Song 4", songs.get(1).getTitle(), "The second most played song should be 'Song 4'.");
            assertEquals("Song 8", songs.get(2).getTitle(), "The third most played song should be 'Song 8'.");
            assertEquals("Song 2", songs.get(3).getTitle(), "The fourth most played song should be 'Song 2'.");
            assertEquals("Song 7", songs.get(4).getTitle(), "The fifth most played song should be 'Song 7'.");
            assertEquals("Song 3", songs.get(5).getTitle(), "The sixth most played song should be 'Song 3'.");
            assertEquals("Song 10", songs.get(6).getTitle(), "The seventh most played song should be 'Song 10'.");
            assertEquals("Song 5", songs.get(7).getTitle(), "The eighth most played song should be 'Song 5'.");
            assertEquals("Song 11", songs.get(8).getTitle(), "The ninth most played song should be 'Song 11'.");
            assertEquals("Song 1", songs.get(9).getTitle(), "The tenth most played song should be 'Song 1'.");
        }
        
        @Test
        public void testGetMostRecentPlayedSongs() {
            // Create a LibraryModel instance
            LibraryModel library = new LibraryModel();

            // Create and add songs with varying play counts
            Song song1 = new Song("Song 1"); // 3 plays
            Song song2 = new Song("Song 2"); // 10 plays
            Song song3 = new Song("Song 3"); // 7 plays
            Song song4 = new Song("Song 4"); // 15 plays
            Song song5 = new Song("Song 5"); // 5 plays
            Song song6 = new Song("Song 6"); // 20 plays
            Song song7 = new Song("Song 7"); // 8 plays
            Song song8 = new Song("Song 8"); // 12 plays
            Song song9 = new Song("Song 9"); // 1 play
            Song song10 = new Song("Song 10"); // 6 plays
            Song song11 = new Song("Song 11"); // 4 plays

            // Add songs to the library
            library.addSong(song1);
            library.addSong(song2);
            library.addSong(song3);
            library.addSong(song4);
            library.addSong(song5);
            library.addSong(song6);
            library.addSong(song7);
            library.addSong(song8);
            library.addSong(song9);
            library.addSong(song10);
            library.addSong(song11);
            
            library.playSong(song1.getTitle());         
            library.playSong(song2.getTitle());  
            library.playSong(song3.getTitle());  
            library.playSong(song4.getTitle());  
            library.playSong(song5.getTitle());  
            library.playSong(song6.getTitle());  
            library.playSong(song7.getTitle());  
            library.playSong(song8.getTitle());  
            library.playSong(song9.getTitle());
            library.playSong(song10.getTitle()); 
            library.playSong(song11.getTitle());  
            
            // Get the most frequently played songs
            PlayList mostRecentlyPlayed = library.getRecentPlaysPlaylist();

            // Verify the playlist contains exactly 10 songs
            assertEquals(10, mostRecentlyPlayed.getSongs().size(), "The playlist should contain 10 songs.");

            // Verify the songs are sorted by play count in descending order
            ArrayList<Song> songs = mostRecentlyPlayed.getSongs();
            assertEquals("Song 11", songs.get(0).getTitle());
            assertEquals("Song 10", songs.get(1).getTitle());
            assertEquals("Song 9", songs.get(2).getTitle());
            assertEquals("Song 8", songs.get(3).getTitle());
            assertEquals("Song 7", songs.get(4).getTitle());
            assertEquals("Song 6", songs.get(5).getTitle());
            assertEquals("Song 5", songs.get(6).getTitle());
            assertEquals("Song 4", songs.get(7).getTitle());
            assertEquals("Song 3", songs.get(8).getTitle());
            assertEquals("Song 2", songs.get(9).getTitle());
        }
           @Test
    public void testGetPopularGenres() {
        // Create a LibraryModel instance
        LibraryModel library = new LibraryModel();

        ArrayList<Song> popSongs = new ArrayList<>();
        ArrayList<Song> rockSongs = new ArrayList<>();
        ArrayList<Song> alternativeSongs = new ArrayList<>();


        // Add 12 Pop songs
        for (int i = 1; i <= 12; i++) {
            Song song = new Song("Pop Song " + i);
            popSongs.add(song);
        }

        // Add 8 Rock songs
        for (int i = 1; i <= 8; i++) {
            Song song = new Song("Rock Song " + i);
            rockSongs.add(song);
        }

        // Add 10 Jazz songs
        for (int i = 1; i <= 10; i++) {
            Song song = new Song("Alternative Song " + i);
            alternativeSongs.add(song);
        }

        // Create albums and songs
        Album popAlbum = new Album("Pop Album", "Pop Artist", 2020, popSongs, Genre.POP);
        Album rockAlbum = new Album("Rock Album", "Rock Artist", 2021, rockSongs, Genre.ROCK);
        Album alternativeAlbum = new Album("Alternative Album", "Alternative Artist", 2019, alternativeSongs, Genre.ALTERNATIVE);


        // Add albums to the library
        library.addAlbum(popAlbum);
        library.addAlbum(rockAlbum);
        library.addAlbum(alternativeAlbum);

        // Get popular genres
        ArrayList<String> popularGenres = library.getPopularGenres();

        // Verify the results
        assertEquals(2, popularGenres.size(), "There should be 2 popular genres.");
        assertTrue(popularGenres.contains("POP"), "Pop should be a popular genre.");
        assertTrue(popularGenres.contains("ALTERNATIVE"), "Alternative should be a popular genre.");
        assertFalse(popularGenres.contains("ROCK"), "Rock should not be a popular genre.");
    }
}