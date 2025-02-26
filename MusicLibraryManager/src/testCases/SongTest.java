package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Song;

class SongTest {

	@Test
	void testSongRating() {
		Song song = new Song("Daydreamer");
		song.setRating(5);
		assertEquals(song.getRating(), 5);
	}
	
	@Test
	void testSongFavorite() {
		Song song = new Song("Daydreamer");
		song.setRating(5);
		assertTrue(song.isFavorite());
	}
	
	@Test
	void testSetFavorite() {
		Song song = new Song("Daydreamer");
		song.setFavorite();
		assertTrue(song.isFavorite());
	}

}
