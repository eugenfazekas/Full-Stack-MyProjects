package com.service.impl;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.model.User;
import com.repository.UserRepository;
import com.service.AccountKeyService;
import com.service.MyProjectCommandLineRunnner;
import com.service.UserService;
import com.util.ProxyServer;

@Component
public class MyProjectCommandLineRunnerImpl implements CommandLineRunner, MyProjectCommandLineRunnner{
	
	private UserRepository userRepository;
	private AccountKeyService accountKeyService;
	private ProxyServer proxyServer;

	

	public MyProjectCommandLineRunnerImpl(UserRepository userRepository, AccountKeyService accountKeyService,
			ProxyServer proxyServer) {
		this.userRepository = userRepository;
		this.accountKeyService = accountKeyService;
		this.proxyServer = proxyServer;
	}

	public void run(String... args) throws Exception {
		dropUsersTable();
		createUsersTable();
		dropAccountKeyTable();
		createAccountKeyTable();
		createDummyUser();
	}

	public void createUsersTable() {
		userRepository.createUsersTable();
	}

	public void dropUsersTable() {
		userRepository.dropUsersTable();
	}

	public void createDummyUser() {
		
		User user = new User();
		UUID uuid = UUID.randomUUID();	
		user.setId(uuid.toString());
		user.setEmail("eu@fa.hu");
	    user.setPassword(new BCryptPasswordEncoder().encode("myPassword"));
	    user.setActive(true);
		userRepository.registerUser(user, "user");
		proxyServer.sendNewUserId(userRepository.findByEmail(user.getEmail()).getId());  
		
		User user2 = new User();
		UUID uuid2 = UUID.randomUUID();	
		user2.setId(uuid2.toString());
		user2.setEmail("admin@fa.hu");
	    user2.setPassword(new BCryptPasswordEncoder().encode("myAdmin"));
	    user2.setActive(true);
		userRepository.registerUser(user2, "user admin");
		proxyServer.sendNewUserId(userRepository.findByEmail(user2.getEmail()).getId()); 
	}

	@Override
	public void createAccountKeyTable() {
		accountKeyService.createAccountKeyTable();	
	}

	@Override
	public void dropAccountKeyTable() {
		accountKeyService.dropAccountKeyTable();	
	}
}
