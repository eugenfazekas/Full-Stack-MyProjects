package com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User user) {

		userService.registerUser(user);
	    return user.getEmail();
	}
}
