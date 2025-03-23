package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Genre;
import model.MusicStore;
import model.Song;

class MusicStoreTest {
	@Test
	void testGetSongInfo() throws FileNotFoundException {
		MusicStore store = new MusicStore();
		String expOutput = "Song: Daydreamer - Album: 19 - Artist: Adele - Year: 2008 - Genre: POP";
		assertEquals(store.getSongInfo("Daydreamer"), expOutput);
	}

	@Test
	void testGetAlbumInfo() throws FileNotFoundException {
		MusicStore store = new MusicStore();
		String expOutput = "Album: 19 - Artist: Adele - Year: 2008 - Genre: POP\n"
				+ "Songs:\n"
				+ " - Daydreamer\n"
				+ " - Best for Last\n"
				+ " - Chasing Pavements\n"
				+ " - Cold Shoulder\n"
				+ " - Crazy for You\n"
				+ " - Melt My Heart to Stone\n"
				+ " - First Love\n"
				+ " - Right as Rain\n"
				+ " - Make You Feel My Love\n"
				+ " - My Same\n"
				+ " - Tired\n"
				+ " - Hometown Glory\n";
		assertEquals(store.getAlbumInfo("19"), expOutput);
	}
	
	@Test
	void testSearchAlbumByArtist() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Album> albums = store.searchAlbumByArtist("Adele");
		assertTrue(albums.stream().anyMatch(album -> album.getTitle().equalsIgnoreCase("19")));
	}
	
	@Test
	void testSearchAlbumByTitle() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Album> albums = store.searchAlbumByTitle("19");
		assertTrue(albums.stream().anyMatch(album -> album.getTitle().equalsIgnoreCase("19")));
	}
	
	@Test
	void testSearchSongByArtist() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Song> songs = store.searchSongByArtist("Adele");
		assertTrue(songs.stream().anyMatch(song -> song.getTitle().equalsIgnoreCase("Daydreamer")));
	}
	
	@Test
	void testSearchSongByTitle() throws FileNotFoundException{
		MusicStore store = new MusicStore();
		ArrayList<Song> songs = store.searchSongByTitle("Daydreamer");
		assertTrue(songs.stream().anyMatch(song -> song.getTitle().equalsIgnoreCase("Daydreamer")));
	}

}