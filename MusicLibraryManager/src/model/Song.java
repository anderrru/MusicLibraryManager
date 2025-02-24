package model;

public class Song {
	// Song instance variables
	private String title;
	private boolean favorite;
	private Rating rating;
	
	public Song(String title) {
		this.title = title;
		favorite = false;
	}
	
	public void setRating(int rating) {
		this.rating = new Rating(rating);
		if (rating == 5) favorite = true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	public int getRating() {
		return rating.getRating();
	}
}
