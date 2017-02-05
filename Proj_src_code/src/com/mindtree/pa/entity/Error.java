package com.mindtree.pa.entity;

public class Error {

	private int id;

	private String message;

	public Error() {
		id = 0;
		message = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
