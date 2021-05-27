package com.service.impl;

import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.AccountKey;
import com.model.User;
import com.repository.UserRepository;
import com.service.AccountKeyService;
import com.service.UserService;
import com.util.EmailService;

@Service
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AccountKeyService accountKeyService;
	private UserRepository userRepository;
	private EmailService emailService;

	

	public UserServiceImpl(AccountKeyService accountKeyService, UserRepository userRepository,
			EmailService emailService) {
		this.accountKeyService = accountKeyService;
		this.userRepository = userRepository;
		this.emailService = emailService;
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
	    user.setActive(false);
		userRepository.registerUser(user,"user");
		accountKeyService.createAccountKey(new AccountKey(uuid.toString(),"user",user.getEmail()));
		
		try {
			emailService.sendMessageen(user.getEmail(), "User! ", uuid.toString());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("New User registered "+user.toString());
		return null;
	}
	
	@Override
	public boolean userExistCheck(String email) {
	
		boolean response = userRepository.userExistCheck(email)  > 0 ? true : false;
		log.debug("UserUserExistCheck "+ email+ " exist = " + response);
		
		return response;
	}

	@Override
	public String userActivation(String key) {
	
		boolean userExist = accountKeyService.keyCheck(key);
		String activated = userExist == true ? "userActivated" : "notActivated";
		if(activated == "userActivated") {
			AccountKey account = accountKeyService.accountKey(key);
			userRepository.setActiveUser(account.getEmail());
			accountKeyService.removeKey(key);
		}
		return activated;
	}
}
