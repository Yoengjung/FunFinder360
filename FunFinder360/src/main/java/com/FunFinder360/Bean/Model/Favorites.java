package com.FunFinder360.Bean.Model;

public class Favorites {
	private int favoritesId;
	private String userId;
	private int personalActivityId;
	private int ownerActivityId;
	private String createDate;
	
	public Favorites () {}

	public Favorites(int favoritesId, String userId, int personalActivityId, int ownerActivityId, String createDate) {
		super();
		this.favoritesId = favoritesId;
		this.userId = userId;
		this.personalActivityId = personalActivityId;
		this.ownerActivityId = ownerActivityId;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Favorites [favoritesId=" + favoritesId + ", userId=" + userId + ", personalActivityId="
				+ personalActivityId + ", ownerActivityId=" + ownerActivityId + ", createDate=" + createDate + "]";
	}

	public int getFavoritesId() {
		return favoritesId;
	}

	public void setFavoritesId(int favoritesId) {
		this.favoritesId = favoritesId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
