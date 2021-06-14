package com.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.OneTimePassword;
import com.service.OneTimePasswordService;
import com.service.UserService;

@RestController
public class AuthController {

	private OneTimePasswordService oneTimePasswordService;

	public AuthController(OneTimePasswordService oneTimePasswordService) {
		this.oneTimePasswordService = oneTimePasswordService;

	}

	@PostMapping("/test")
    public String auth() {

	return oneTimePasswordService.createOneTimePassword();
    }
}
