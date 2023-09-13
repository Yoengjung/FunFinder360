package com.FunFinder360.Bean.Model;

public class MemberOwner {
	private String user_id;
	private String password;
	private String username;
	private String Business_Name;
	private String Business_Type;
	private Integer Business_Number;
	private Integer PhoneNumber;
	private String email;
	private String registration_date;

	public MemberOwner() {
	}

	public MemberOwner(String user_id, String password, String username, String business_Name, String business_Type,
			Integer business_Number, Integer phoneNumber, String email, String registration_date) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.username = username;
		Business_Name = business_Name;
		Business_Type = business_Type;
		Business_Number = business_Number;
		PhoneNumber = phoneNumber;
		this.email = email;
		this.registration_date = registration_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getBusiness_Name() {
		return Business_Name;
	}

	public void setBusiness_Name(String business_Name) {
		Business_Name = business_Name;
	}

	public String getBusiness_Type() {
		return Business_Type;
	}

	public void setBusiness_Type(String business_Type) {
		Business_Type = business_Type;
	}

	public Integer getBusiness_Number() {
		return Business_Number;
	}

	public void setBusiness_Number(Integer business_Number) {
		Business_Number = business_Number;
	}

	public Integer getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

}
