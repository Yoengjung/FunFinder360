package com.FunFinder360.Bean.Model;

public class PersonalActivityDetail {
	private int activityId;
	private String userId;
	private String activityName;
	private String category;
	private String location;
	private String locationDetail;
	private int duration;
	private int cost;
	private int activityNumber;
	private int rating;
	private int readHit;
	private String postedDate;
	
	private String image;
	private String imageOrder;
	
	private String content;
	private int contentOrder;
	
	public PersonalActivityDetail () {}

	public PersonalActivityDetail(int activityId, String userId, String activityName, String category, String location,
			String locationDetail, int duration, int cost, int activityNumber, int rating, int readHit,
			String postedDate, String image, String imageOrder, String content, int contentOrder) {
		super();
		this.activityId = activityId;
		this.userId = userId;
		this.activityName = activityName;
		this.category = category;
		this.location = location;
		this.locationDetail = locationDetail;
		this.duration = duration;
		this.cost = cost;
		this.activityNumber = activityNumber;
		this.rating = rating;
		this.readHit = readHit;
		this.postedDate = postedDate;
		this.image = image;
		this.imageOrder = imageOrder;
		this.content = content;
		this.contentOrder = contentOrder;
	}

	@Override
	public String toString() {
		return "PersonalActivityDetail [activityId=" + activityId + ", userId=" + userId + ", activityName="
				+ activityName + ", category=" + category + ", location=" + location + ", locationDetail="
				+ locationDetail + ", duration=" + duration + ", cost=" + cost + ", activityNumber=" + activityNumber
				+ ", rating=" + rating + ", readHit=" + readHit + ", postedDate=" + postedDate + ", image=" + image
				+ ", imageOrder=" + imageOrder + ", content=" + content + ", contentOrder=" + contentOrder + "]";
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(int activityNumber) {
		this.activityNumber = activityNumber;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getReadHit() {
		return readHit;
	}

	public void setReadHit(int readHit) {
		this.readHit = readHit;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageOrder() {
		return imageOrder;
	}

	public void setImageOrder(String imageOrder) {
		this.imageOrder = imageOrder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentOrder() {
		return contentOrder;
	}

	public void setContentOrder(int contentOrder) {
		this.contentOrder = contentOrder;
	}
	
	
}
