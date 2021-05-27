package com.service.impl;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;
import com.service.AccountKeyService;
import com.service.MyProjectCommandLineRunnner;
import com.service.UserService;

@Component
public class MyProjectCommandLineRunnerImpl implements CommandLineRunner, MyProjectCommandLineRunnner{
	
	private UserRepository userRepository;
	private AccountKeyService accountKeyService;

	public MyProjectCommandLineRunnerImpl(UserRepository userRepository, AccountKeyService accountKeyService) {
		this.userRepository = userRepository;
		this.accountKeyService = accountKeyService;
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
