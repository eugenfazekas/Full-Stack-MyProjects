package com.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.service.MyCommandLineRunner;
import com.service.UserService;

@Service
public class MyCommandLineRunnerImpl implements MyCommandLineRunner, CommandLineRunner{

	private UserService userservice;
	
	public MyCommandLineRunnerImpl(UserService userservice) {
		this.userservice = userservice;
	}

	@Override
	public void run(String... args) throws Exception {
		dropCollectionUsers();
		createCollectionUsers();
	}
	
	@Override
	public void createCollectionUsers() {		
		userservice.createCollectionUsers();
	}

	@Override
	public void dropCollectionUsers() {	
		userservice.dropCollectionUsers();
	}
}
