package testCases;

import model.LibraryModel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.Album;
import model.PlayList;
import model.Song;

public class LibraryModelTest {

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
        Album album = new Album("Album Title", "Album Artist", 2021, albumSongs);
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