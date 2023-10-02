package com.FunFinder360.Bean.Model;

public class Review {
	private int reviewId;
	private int activityId;
	private String userId;
	private int rating;
	private String reviewContent;
	private int reviewOrder;
	private String postedDate;
	private String userName;

	public Review() {}

	public Review(int reviewId, int activityId, String userId, int rating, String reviewContent, int reviewOrder,
			String postedDate, String userName) {
		super();
		this.reviewId = reviewId;
		this.activityId = activityId;
		this.userId = userId;
		this.rating = rating;
		this.reviewContent = reviewContent;
		this.reviewOrder = reviewOrder;
		this.postedDate = postedDate;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", activityId=" + activityId + ", userId=" + userId + ", rating="
				+ rating + ", reviewContent=" + reviewContent + ", reviewOrder=" + reviewOrder + ", postedDate="
				+ postedDate + ", userName=" + userName + "]";
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewOrder() {
		return reviewOrder;
	}

	public void setReviewOrder(int reviewOrder) {
		this.reviewOrder = reviewOrder;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
