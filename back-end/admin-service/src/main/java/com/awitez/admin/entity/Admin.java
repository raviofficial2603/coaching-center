package com.awitez.admin.entity;

public class Admin {
	
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
}
