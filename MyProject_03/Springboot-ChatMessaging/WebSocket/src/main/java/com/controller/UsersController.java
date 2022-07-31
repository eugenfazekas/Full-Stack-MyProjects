package com.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.storage.UserStorage;

@CrossOrigin("http://localhost:4200/")
@RestController
public class UsersController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	 @GetMapping("/registration/{userName}")
	 public String register(@PathVariable String userName) {
		 
		 String user = "User Not Registered";
		 System.out.println("handling register user request: " + userName);
		 try {
			UserStorage.getInstance().setUsers(userName);
			Set<String> users = UserStorage.getInstance().getUsers();
			
			for(String u : users)
			simpMessagingTemplate.convertAndSend("/topic/users/"+ u, userName);
			
			user = "User Registered";
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		 return user;
	 }
	 
	 @GetMapping("/fetchAllUsers")
	 public Set<String> fetchAll(){
		 return UserStorage.getInstance().getUsers();
	 }	 
}
