package com.FunFinder360.Bean.Model;

public class OwnerActivityAndImage {
	private String userId;
	private String activityName;
	private String category;
	private String location;
	private String locationDetail;
	private int duration;
	private int price;
	private String openTime;
	private String closeTime;
	private String event;
	private String image;
	private int readHit;
	private String postedDate;
	
	public OwnerActivityAndImage () {}

	public OwnerActivityAndImage(String userId, String activityName, String category, String location,
			String locationDetail, int duration, int price, String openTime, String closeTime, String event,
			String image, int readHit, String postedDate) {
		super();
		this.userId = userId;
		this.activityName = activityName;
		this.category = category;
		this.location = location;
		this.locationDetail = locationDetail;
		this.duration = duration;
		this.price = price;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.event = event;
		this.image = image;
		this.readHit = readHit;
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "OwnerActivityAndImage [userId=" + userId + ", activityName=" + activityName + ", category=" + category
				+ ", location=" + location + ", locationDetail=" + locationDetail + ", duration=" + duration
				+ ", price=" + price + ", openTime=" + openTime + ", closeTime=" + closeTime + ", event=" + event
				+ ", image=" + image + ", readHit=" + readHit + ", postedDate=" + postedDate + "]";
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	
}
