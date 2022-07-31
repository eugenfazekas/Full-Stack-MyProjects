package com.model;

public class MessageModel {

	private String message;
	private String fromLogin;
	private String date;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFromLogin() {
		return fromLogin;
	}
	public void setFromLogin(String fromLogin) {
		this.fromLogin = fromLogin;
	}	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "MessageModel [message=" + message + ", fromLogin=" + fromLogin + "]";
	}	
}
