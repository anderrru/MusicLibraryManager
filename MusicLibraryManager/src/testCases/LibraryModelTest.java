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
}