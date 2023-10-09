package com.FunFinder360.Bean.Model;

public class TotalRating {
	private int rating;
	private int totalRating;
	
	public TotalRating () {}

	public TotalRating(int rating, int totalRating) {
		super();
		this.rating = rating;
		this.totalRating = totalRating;
	}

	@Override
	public String toString() {
		return "TotalRating [rating=" + rating + ", totalRating=" + totalRating + "]";
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}
	
	
}
