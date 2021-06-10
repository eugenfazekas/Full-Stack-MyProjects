package com.repository;

import com.model.OneTimePassword;

public interface OneTimePasswordRepository {

	void createOneTimePasswordTable();
	
	void dropOneTimePasswordTable();

	void createOneTimePassword(OneTimePassword oneTimePassword);
	
	Integer OneTimePasswordCheck(String email);	
	
	OneTimePassword findOneTimePassword(String email);	
	
	void removeOneTimePassword(String email);
}
