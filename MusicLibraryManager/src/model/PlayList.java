package model;

import java.util.ArrayList;

class PlayList {
	 private String name;
	 private ArrayList<Song> songs;

	 public PlayList(String name) {
	     this.name = name;
	     this.songs = new ArrayList<>();
	 }

	 public String getName() {
	     return name;
	 }

	 // adds to front of list
	 public void addSong(Song song) {
	     songs.add(0, song);
	 }

	 public void removeSong(String title) {
	     songs.removeIf(song -> song.getTitle().equals(title));
	 }

	 public ArrayList<Song> getSongs() {
	     return new ArrayList<>(songs);
	 }
}
