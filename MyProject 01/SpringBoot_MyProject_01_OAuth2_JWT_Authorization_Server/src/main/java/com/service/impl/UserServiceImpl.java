package com.service.impl;

import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findById(String id) {

		return userRepository.findById(id);
	}

	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	public String registerUser(User user) {
		
		String authorities = "";
		
		for (String userAuths : user.getAuthorities()) {
			authorities += userAuths+ " ";
			
		}
		userRepository.registerUser(user,authorities);
		return null;
	}

}
