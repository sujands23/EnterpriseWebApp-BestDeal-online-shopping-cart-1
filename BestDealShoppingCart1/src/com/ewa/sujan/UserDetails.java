package com.ewa.sujan;

public class UserDetails {
	
	String UserId,Password,EmailId,UserName,Role;

	public UserDetails(String userId, String password, String emailId, String userName,String role) {
		super();
		UserId = userId;
		Password = password;
		EmailId = emailId;
		UserName = userName;
		Role=role;
	}
	
	public UserDetails(String userId, String password, String emailId) {
		super();
		UserId = userId;
		Password = password;
		EmailId = emailId;
	}
	
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
}
