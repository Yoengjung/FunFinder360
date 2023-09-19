package com.FunFinder360.Bean.Model;

public class OwnerActivity {
	private int activityId;
	private String userid;
	private String activitiyName;
	private String category;
	private String location;
	private String locationDetail;
	private int duration;
	private int price;
	private int activitiyNumber;
	private String openTime;
	private String closeTime;
	private String event;
	private int readHit;
	private String postedDate;
	
	public OwnerActivity () {}

	public OwnerActivity(int activityId, String userid, String activitiyName, String category, String location,
			String locationDetail, int duration, int price, int activitiyNumber, String openTime, String closeTime,
			String event, int readHit, String postedDate) {
		super();
		this.activityId = activityId;
		this.userid = userid;
		this.activitiyName = activitiyName;
		this.category = category;
		this.location = location;
		this.locationDetail = locationDetail;
		this.duration = duration;
		this.price = price;
		this.activitiyNumber = activitiyNumber;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.event = event;
		this.readHit = readHit;
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "OwnerActivity [activityId=" + activityId + ", userid=" + userid + ", activitiyName=" + activitiyName
				+ ", category=" + category + ", location=" + location + ", locationDetail=" + locationDetail
				+ ", duration=" + duration + ", price=" + price + ", activitiyNumber=" + activitiyNumber + ", openTime="
				+ openTime + ", closeTime=" + closeTime + ", event=" + event + ", readHit=" + readHit + ", postedDate="
				+ postedDate + "]";
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getActivitiyName() {
		return activitiyName;
	}

	public void setActivitiyName(String activitiyName) {
		this.activitiyName = activitiyName;
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

	public int getActivitiyNumber() {
		return activitiyNumber;
	}

	public void setActivitiyNumber(int activitiyNumber) {
		this.activitiyNumber = activitiyNumber;
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
