package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.LibraryModel;
import model.MusicStore;

class LibraryModelTest {

	@Test
	void testAddAlbum() throws FileNotFoundException {
		MusicStore store = new MusicStore();
		LibraryModel lib = new LibraryModel();
		lib.addAlbum(store.searchAlbumByTitle("19").get(0));
		assertEquals(lib.getAlbums().get(0).toString(), store.searchAlbumByTitle("19").get(0).toString());
	}
	
	@Test
	void testAddSong() throws FileNotFoundException {
		MusicStore store = new MusicStore();
		LibraryModel lib = new LibraryModel();
		lib.addSong(store.searchSongByTitle("Daydreamer").get(0));
		assertEquals(lib.getSongs().get(0).toString(), store.searchSongByTitle("Daydreamer").get(0).toString());
	}
}
