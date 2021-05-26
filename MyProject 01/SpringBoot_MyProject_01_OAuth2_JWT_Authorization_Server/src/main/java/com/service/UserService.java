package com.service;

import com.model.User;

public interface UserService {
	
	void createUsersTable();
	
	void dropUsersTable();

	User findById(String id);
	
	User findByEmail(String email);
	
	String registerUser(User user);
	
	boolean userExistCheck(String email);
}
