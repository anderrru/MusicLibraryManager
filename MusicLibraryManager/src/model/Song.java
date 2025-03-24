package model;

public class Song {
    // Song instance variables
    private String title; // Title of the song
    private boolean favorite; // Flag indicating if the song is marked as favorite
    private Rating rating; // Rating object for the song
    private boolean ratingSet; // Flag indicating if the song has a rating set
    private int plays;

    // Constructor to initialize the song with a title
    public Song(String title) {
        this.title = title;
        favorite = false; // Default to not favorite
        ratingSet = false; // Default to no rating set
        plays = 0;
    }

    // Sets the rating for the song and marks it as a favorite if rating is 5
    public void setRating(int rating) {
        ratingSet = true;
        this.rating = new Rating(rating);
        if (rating == 5)
            favorite = true; // Mark as favorite if rating is 5
    }
    
    public void play() {
    	plays += 1;
    }
    
    public int getPlays() {
    	return this.plays;
    }

    public void playAmt(int amount) {
    	this.plays += amount;
    }
    
    // Returns the title of the song
    public String getTitle() {
        return title;
    }

    // Returns if the song is marked as favorite
    public boolean isFavorite() {
        return favorite;
    }

    // Marks the song as favorite
    public void setFavorite() {
        favorite = true;
    }

    // Returns the rating of the song
    public int getRating() {
        if (!ratingSet) {
            return 0; // Default rating if not set
        }
        return rating.getRating();
    }
    
    // Checks if the song has a rating set
    public boolean hasRating() {
        return ratingSet;
    }
 

    // Returns a copy of the song with the same attributes (title, favorite, rating, genre)
    public Song getCopy() {
        Song s = new Song(this.title);
        if (favorite)
            s.setFavorite(); // Copy the favorite status
        if (ratingSet) {
            s.setRating(getRating()); // Copy the rating if set
        }
        return s;
    }

    // Not needed, just for testing: Returns the title of the song
    public String toString() {
        return title;
    }
}
