package com.service.impl;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.OneTimePassword;
import com.repository.OneTimePasswordRepository;
import com.service.OneTimePasswordService;


@Service
public class OneTimePasswordServiceImpl implements OneTimePasswordService {

	private OneTimePasswordRepository oneTimePasswordRepository;
	
	 @Autowired
	private HttpServletRequest request;
	 
	public OneTimePasswordServiceImpl(OneTimePasswordRepository oneTimePasswordServiceImpl) {
		this.oneTimePasswordRepository = oneTimePasswordServiceImpl;
	}

	@Override
	public void createOneTimePasswordTable() {
		oneTimePasswordRepository.createOneTimePasswordTable();
		
	}

	@Override
	public void dropOneTimePasswordTable() {
		oneTimePasswordRepository.dropOneTimePasswordTable();
		
	}

	@Override
	public String createOneTimePassword() {
		
		UUID uuid = UUID.randomUUID();	
		OneTimePassword oneTimePassword = new OneTimePassword();
		String password = getRandomNumberString();
		oneTimePassword.setId(uuid.toString());
		oneTimePassword.setEmail(request.getHeader("username"));
		oneTimePassword.setPassword(new BCryptPasswordEncoder().encode(password));
		 oneTimePasswordRepository.createOneTimePassword(oneTimePassword);	
		 return password;
	}

	@Override
	public OneTimePassword findOneTimePassword(String email) {
		return oneTimePasswordRepository.findOneTimePassword(email);
	}

	@Override
	public void removeOneTimePassword(String email) {
		oneTimePasswordRepository.removeOneTimePassword(email);
	}

	private String getRandomNumberString() {

	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
}
