package com.repository;

import com.model.User;

public interface UserRepository {

	User findById(String id);
	
	User findByEmail(String email);
	
	String registerUser(User user, String authorities);
}
