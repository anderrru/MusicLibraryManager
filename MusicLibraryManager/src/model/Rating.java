package model;

public class Rating {
	private int rating;
	
	/*
	 * @pre rating != null && rating >= 1 && rating <= 5
	 */
	public Rating(int rating) {
		this.rating = rating;
	}
	
	public int getRating() {
		return rating;
	}
}
