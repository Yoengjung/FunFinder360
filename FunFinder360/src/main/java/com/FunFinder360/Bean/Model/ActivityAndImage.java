package com.FunFinder360.Bean.Model;

public class ActivityAndImage {
	private int activityId;
	private String userId;
	private String activityName;
	private String category;
	private String location;
	private String locationDetail;
	private String image;
	private int imageOrder;
	private int readHit;
	
	public ActivityAndImage () {}

	public ActivityAndImage(int activityId, String userId, String activityName, String category, String location,
			String locationDetail, String image, int imageOrder, int readHit) {
		super();
		this.activityId = activityId;
		this.userId = userId;
		this.activityName = activityName;
		this.category = category;
		this.location = location;
		this.locationDetail = locationDetail;
		this.image = image;
		this.imageOrder = imageOrder;
		this.readHit = readHit;
	}

	@Override
	public String toString() {
		return "ActivityAndImage [activityId=" + activityId + ", userId=" + userId + ", activityName=" + activityName
				+ ", category=" + category + ", location=" + location + ", locationDetail=" + locationDetail
				+ ", image=" + image + ", imageOrder=" + imageOrder + ", readHit=" + readHit + "]";
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getImageOrder() {
		return imageOrder;
	}

	public void setImageOrder(int imageOrder) {
		this.imageOrder = imageOrder;
	}

	public int getReadHit() {
		return readHit;
	}

	public void setReadHit(int readHit) {
		this.readHit = readHit;
	}

	
	
}
