package com.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.ProxyUser;
import com.model.User;
import com.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "createUserResource", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody ProxyUser user) {
		userService.createUser(user);
		
		return user.getId().toString();
	}
	
	@RequestMapping(value = "getUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public User findUserById() {		
    	return userService.getUser();
	}
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {		
    	return userService.updateUser(user);
	}
}
