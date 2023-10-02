package com.FunFinder360.Bean.Model;

import java.util.ArrayList;
import java.util.List;

public class OwnerActivityDetail {
	private int activityId;
	private String userId;
	private String activityName;
	private String category;
	private String location;
	private String locationDetail;
	private int duration;
	private int price;
	private int activityNumber;
	private String openTime;
	private String closeTime;
	private String event;
	private int readHit;
	private String postedDate;

	private List<Object> contentList = new ArrayList<Object>();
	private List<Object> imageList = new ArrayList<Object>();

	private int totalRacodeCount;
	private int contentCount = 0;
	private int imgCount = 0;
	
	public OwnerActivityDetail () {}

	public OwnerActivityDetail(int activityId, String userId, String activityName, String category, String location,
			String locationDetail, int duration, int price, int activityNumber, String openTime, String closeTime,
			String event, int readHit, String postedDate, List<Object> contentList, List<Object> imageList,
			int totalRacodeCount, int contentCount, int imgCount) {
		super();
		this.activityId = activityId;
		this.userId = userId;
		this.activityName = activityName;
		this.category = category;
		this.location = location;
		this.locationDetail = locationDetail;
		this.duration = duration;
		this.price = price;
		this.activityNumber = activityNumber;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.event = event;
		this.readHit = readHit;
		this.postedDate = postedDate;
		this.contentList = contentList;
		this.imageList = imageList;
		this.totalRacodeCount = totalRacodeCount;
		this.contentCount = contentCount;
		this.imgCount = imgCount;
	}

	@Override
	public String toString() {
		return "OwnerActivityDetail [activityId=" + activityId + ", userId=" + userId + ", activityName=" + activityName
				+ ", category=" + category + ", location=" + location + ", locationDetail=" + locationDetail
				+ ", duration=" + duration + ", price=" + price + ", activityNumber=" + activityNumber + ", openTime="
				+ openTime + ", closeTime=" + closeTime + ", event=" + event + ", readHit=" + readHit + ", postedDate="
				+ postedDate + ", contentList=" + contentList + ", imageList=" + imageList + ", totalRacodeCount="
				+ totalRacodeCount + ", contentCount=" + contentCount + ", imgCount=" + imgCount + "]";
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(int activityNumber) {
		this.activityNumber = activityNumber;
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

	public List<Object> getContentList() {
		return contentList;
	}

	public void setContentList(ContentObject contentList) {
		this.contentList.add(contentList);
	}

	public List<Object> getImageList() {
		return imageList;
	}

	public void setImageList(ImageObject imageList) {
		this.imageList.add(imageList);
	}

	public int getTotalRacodeCount() {
		return totalRacodeCount;
	}

	public void setTotalRacodeCount(int totalRacodeCount) {
		this.totalRacodeCount = totalRacodeCount;
	}

	public int getContentCount() {
		return contentCount;
	}

	public void setContentCount(int contentCount) {
		this.contentCount = contentCount;
	}

	public int getImgCount() {
		return imgCount;
	}

	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	};
	
	

}
