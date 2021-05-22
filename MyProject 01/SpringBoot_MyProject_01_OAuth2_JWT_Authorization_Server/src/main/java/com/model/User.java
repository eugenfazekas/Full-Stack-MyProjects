package com.model;

import java.util.List;

public class User {
	
	private String id;
	private String username;
	private String password;
	private String activationCode;
	private boolean active;
	private List<String> authorities;

	public User() {}
		
	

	public User(String id, String username, String password, String activationCode, boolean active,
			List<String> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.activationCode = activationCode;
		this.active = active;
		this.authorities = authorities;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", authorities=" + authorities + "]";
	}
}
