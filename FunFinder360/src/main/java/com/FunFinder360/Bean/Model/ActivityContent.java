package com.FunFinder360.Bean.Model;

public class ActivityContent {
	private int contentId;
	private int personalActivityId;
	private int ownerActivityId;
	private String content;
	private int contentOrder;
	private int totalOrder;

	public ActivityContent () {}

	public ActivityContent(int contentId, int personalActivityId, int ownerActivityId, String content, int contentOrder,
			int totalOrder) {
		super();
		this.contentId = contentId;
		this.personalActivityId = personalActivityId;
		this.ownerActivityId = ownerActivityId;
		this.content = content;
		this.contentOrder = contentOrder;
		this.totalOrder = totalOrder;
	}

	@Override
	public String toString() {
		return "ActivityContent [contentId=" + contentId + ", personalActivityId=" + personalActivityId
				+ ", ownerActivityId=" + ownerActivityId + ", content=" + content + ", contentOrder=" + contentOrder
				+ ", totalOrder=" + totalOrder + "]";
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getPersonalActivityId() {
		return personalActivityId;
	}

	public void setPersonalActivityId(int personalActivityId) {
		this.personalActivityId = personalActivityId;
	}

	public int getOwnerActivityId() {
		return ownerActivityId;
	}

	public void setOwnerActivityId(int ownerActivityId) {
		this.ownerActivityId = ownerActivityId;
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

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}
	
	
	
}
