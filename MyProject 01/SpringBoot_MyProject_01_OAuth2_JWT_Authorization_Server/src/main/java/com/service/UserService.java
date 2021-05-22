package com.service;

import com.model.User;

public interface UserService {

	User findById(String id);
	
	User findByEmail(String email);
	
	String registerUser(User user);

}
