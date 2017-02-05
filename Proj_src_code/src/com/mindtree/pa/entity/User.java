package com.mindtree.pa.entity;

public class User {

	private int uid;
private String password;
	private String name;

	private String emailId;

	public User() {
		uid = 0;
		password = null;
		name = null;
		emailId = null;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}