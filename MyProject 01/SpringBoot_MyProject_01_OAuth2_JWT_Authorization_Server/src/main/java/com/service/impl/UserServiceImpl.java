package com.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createUsersTable() {
		userRepository.createUsersTable();
	}

	public void dropUsersTable() {
		userRepository.dropUsersTable();
		
	}
	
	public User findById(String id) {

		return userRepository.findById(id);
	}

	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	public String registerUser(User user) {
		
		UUID uuid = UUID.randomUUID();	
		user.setId(uuid.toString());
	    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	    user.setActive(true);
		userRepository.registerUser(user,"user");
		
		log.debug("New User registered "+user.toString());
		return null;
	}
	
	@Override
	public boolean userExistCheck(String email) {
	
		boolean response = userRepository.userExistCheck(email)  > 0 ? true : false;
		log.debug("UserUserExistCheck "+ email+ " exist = " + response);
		
		return response;
	}

}
