package testCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Album;
import model.Genre;
import model.Song;

class AlbumTest {

	@Test
	void testAlbumName() {
		Song song = new Song("Daydreamer");
		ArrayList<Song> songs = new ArrayList<>();
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.getTitle(), "19");
	}

	@Test
	void testAlbumArtistName() {
		ArrayList<Song> songs = new ArrayList<>();
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.getArtist(), "Adele");
	}

	@Test
	void testAlbumYear() {
		ArrayList<Song> songs = new ArrayList<>();
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.getYear(), 2008);
	}

	@Test
	void testAlbumGetSong() {
		Song song = new Song("Daydreamer");
		ArrayList<Song> songs = new ArrayList<>();
		songs.add(song);
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.getSong("Daydreamer").getTitle(), "Daydreamer");
	}

	@Test
	void testAlbumGetSongs() {
		Song song = new Song("Daydreamer");
		ArrayList<Song> songs = new ArrayList<>();
		songs.add(song);
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.getSongs(), songs);
	}

	@Test
	void testAlbumToString() {
		Song song = new Song("Daydreamer");
		ArrayList<Song> songs = new ArrayList<>();
		songs.add(song);
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		assertEquals(album.toString(), "19 by Adele (2008)");	
	
	}

	@Test
	void testAlbumGetCopy() {
		Song song = new Song("Daydreamer");
		ArrayList<Song> songs = new ArrayList<>();
		songs.add(song);
		Album album = new Album("19", "Adele", 2008, songs, Genre.POP);
		Album copy = album.getAlbumCopy();
		assertEquals(album.toString(), copy.toString());
	}

}
