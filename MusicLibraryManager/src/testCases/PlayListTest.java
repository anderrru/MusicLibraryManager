package testCases;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.PlayList;
import model.Song;

public class PlayListTest {

    @Test
    void testGetName() {
        PlayList playlist = new PlayList("Favorites");
        assertEquals("Favorites", playlist.getName());
    }

    @Test
    void testAddSongAndOrder() {
        PlayList playlist = new PlayList("MyPlaylist");
        Song song1 = new Song("Song One");
        Song song2 = new Song("Song Two");
        
        // Add songs; each new song is added at index 0.
        playlist.addSong(song1);
        playlist.addSong(song2);
        
        ArrayList<Song> songs = playlist.getSongs();
        // Expecting song2 to be at index 0 and song1 at index 1.
        assertEquals(2, songs.size());
        assertEquals("Song Two", songs.get(0).getTitle());
        assertEquals("Song One", songs.get(1).getTitle());
    }

    @Test
    void testRemoveSong() {
        PlayList playlist = new PlayList("TestPlaylist");
        Song song1 = new Song("Song1");
        Song song2 = new Song("Song2");
        Song song3 = new Song("Song3");
        
        // Add songs: order will be song3, song2, song1.
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);
        
        // Remove song with title "Song2"
        playlist.removeSong("Song2");
        ArrayList<Song> songs = playlist.getSongs();
        assertEquals(2, songs.size());
        // The remaining songs should be "Song3" and "Song1".
        assertEquals("Song3", songs.get(0).getTitle());
        assertEquals("Song1", songs.get(1).getTitle());
        
        playlist.removeSong("Nonexistent");
        assertEquals(2, playlist.getSongs().size());
    }

    @Test
    void testGetSongsReturnsCopy() {
        PlayList playlist = new PlayList("ImmutableTest");
        Song song1 = new Song("Song1");
        playlist.addSong(song1);
        
        ArrayList<Song> songsCopy = playlist.getSongs();
        songsCopy.add(new Song("Song2"));
        
        ArrayList<Song> originalSongs = playlist.getSongs();
        assertEquals(1, originalSongs.size());
        assertEquals("Song1", originalSongs.get(0).getTitle());
    }
}