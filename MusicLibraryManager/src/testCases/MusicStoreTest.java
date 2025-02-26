package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.MusicStore;

class MusicStoreTest {

	@Test
	void testSearchByArtist() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Album> albums = store.searchAlbumByArtist("Adele");
		assertEquals(albums.get(0).getTitle(), "19");
	}

}
