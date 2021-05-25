package com.service.impl;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.User;
import com.service.MyProjectCommandLineRunnner;
import com.service.UserService;

@Component
public class MyProjectCommandLineRunnerImpl implements CommandLineRunner, MyProjectCommandLineRunnner{
	
	private UserService userService;

	public MyProjectCommandLineRunnerImpl(UserService userService) {
		this.userService = userService;
	}

	public void run(String... args) throws Exception {
		dropUsersTable();
		createUsersTable();
		createDummyUser();
	}

	public void createUsersTable() {
		userService.createUsersTable();
	}

	public void dropUsersTable() {
		userService.dropUsersTable();	
	}

	public void createDummyUser() {
		userService.registerUser(new User("John",new BCryptPasswordEncoder().encode("myPassword")));
	}

}
