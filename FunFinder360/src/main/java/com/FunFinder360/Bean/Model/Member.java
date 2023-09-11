package com.FunFinder360.Bean.Model;

public class Member {
	private String id;
	private String password;
	private String email;
	private String introduction;
	private String profileImage;
	
	public Member () {};
	
	public Member(String id, String password, String email, String introduction, String profileImage) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.introduction = introduction;
		this.profileImage = profileImage;
	}
	

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", email=" + email + ", introduction=" + introduction
				+ ", profileImage=" + profileImage + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
