package model;

public class Rating {
	private int rating; // The rating value for a song (between 1 and 5)

	/*
	 * Constructor to initialize the rating.
	 * 
	 * @pre rating != null && rating >= 1 && rating <= 5
	 */
	public Rating(int rating) {
		this.rating = rating;
	}

	// Returns the rating value
	public int getRating() {
		return rating;
	}
}