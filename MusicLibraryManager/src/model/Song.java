package model;

public class Song {
	// Song instance variables
	private String title;
	private boolean favorite;
	private Rating rating;
	private boolean ratingSet;
	
	public Song(String title) {
		this.title = title;
		favorite = false;
		ratingSet = false;
	}
	
	public void setRating(int rating) {
		ratingSet = true;
		this.rating = new Rating(rating);
		if (rating == 5) favorite = true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isFavorite() {
		return favorite;
	}
	
	public void setFavorite() {
		favorite = true;
	}
	
	public int getRating() {
		return rating.getRating();
	}
	
	public Song getCopy() {
		Song s = new Song(this.title);
		if (favorite) s.setFavorite();
		if (ratingSet) {
			s.setRating(getRating());
		}
		return s;
	}
	
	// not needed. just for testing
	public String toString() {
		return title;
	}
}
