package com.FunFinder360.Bean.Model;

public class MemberOwner {
	private String userId;
	private String password;
	private String userName;
	private String businessName;
	private String businessType;
	private int businessNumber;
	private String phoneNumber;
	private String email;
	private String bio;
	private String registrationDate;

	public MemberOwner() {
	}

	public MemberOwner(String userId, String password, String userName, String businessName, String businessType,
			int businessNumber, String phoneNumber, String email, String bio, String registrationDate) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.businessName = businessName;
		this.businessType = businessType;
		this.businessNumber = businessNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bio = bio;
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "MemberOwner [userId=" + userId + ", password=" + password + ", userName=" + userName + ", businessName="
				+ businessName + ", businessType=" + businessType + ", businessNumber=" + businessNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", bio=" + bio + ", registrationDate="
				+ registrationDate + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public int getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

}
