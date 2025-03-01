package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.MusicStore;
import model.Song;

class MusicStoreTest {
	
	@Test
	void testSearchAlbumByArtist() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Album> albums = store.searchAlbumByArtist("Adele");
		assertEquals(albums.get(0).getTitle(), "19");
	}
	
	@Test
	void testSearchAlbumByTitle() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Album> albums = store.searchAlbumByTitle("19");
		assertEquals(albums.get(0).getTitle(), "19");
	}
	
	@Test
	void testSearchSongByArtist() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Song> songs = store.searchSongByArtist("Adele");
		assertEquals(songs.get(0).getTitle(), "Daydreamer");
	}
	
	@Test
	void testSearchSongByTitle() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Song> songs = store.searchSongByTitle("Daydreamer");
		assertEquals(songs.get(0).getTitle(), "Daydreamer");
	}

}