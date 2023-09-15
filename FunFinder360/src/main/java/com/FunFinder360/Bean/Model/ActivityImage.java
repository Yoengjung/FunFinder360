package com.FunFinder360.Bean.Model;

public class ActivityImage {
	private int imageId;
	private int personalActivityId;
	private int ownerActivityId;
	private String image;
	private int imageOrder;
	
	public ActivityImage () {}

	public ActivityImage(int imageId, int personalActivityId, int ownerActivityId, String image, int imageOrder) {
		super();
		this.imageId = imageId;
		this.personalActivityId = personalActivityId;
		this.ownerActivityId = ownerActivityId;
		this.image = image;
		this.imageOrder = imageOrder;
	}

	@Override
	public String toString() {
		return "ActivityImage [imageId=" + imageId + ", personalActivityId=" + personalActivityId + ", ownerActivityId="
				+ ownerActivityId + ", image=" + image + ", imageOrder=" + imageOrder + "]";
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
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
	
	
}
