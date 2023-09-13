package com.FunFinder360.Bean.Model;

public class MemberPersonalUser {
	private String userId;
	private String password;
	private String username;
	private String birth;
	private String phoneNumber;
	private String email;
	private String bio = null;
	private String profile_image = null;
	private String registration_date;
	
	public MemberPersonalUser () {}

	public MemberPersonalUser(String userId, String password, String username, String birth, String phoneNumber,
			String email, String bio, String profile_image, String registration_date) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bio = bio;
		this.profile_image = profile_image;
		this.registration_date = registration_date;
	}

	@Override
	public String toString() {
		return "MemberPersonalUser [userId=" + userId + ", password=" + password + ", username=" + username + ", birth="
				+ birth + ", phoneNumber=" + phoneNumber + ", email=" + email + ", bio=" + bio + ", profile_image=" + profile_image
				+ ", registration_date=" + registration_date + "]";
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public String getProfileImage() {
		return profile_image;
	}

	public void setProfileImage(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	
	
	
}
