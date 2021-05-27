package com.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@RequestMapping(value = "userExistCheck", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean userExistCheck(@RequestParam String email) {
		return userService.userExistCheck(email);
	}
	
	@RequestMapping(path = "userKeyCheck/{code}", method = RequestMethod.GET)
    public RedirectView codeCheckUser(@PathVariable("code") String code, HttpServletResponse response) {
		 userService.userActivation(code);
		 RedirectView redirectView = new RedirectView();
		 redirectView.setUrl("http://localhost:4200");
	
		 return redirectView;
 }
}
