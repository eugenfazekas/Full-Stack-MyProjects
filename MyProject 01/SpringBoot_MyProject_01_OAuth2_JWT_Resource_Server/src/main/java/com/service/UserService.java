package com.service;

import org.springframework.web.multipart.MultipartFile;

import com.model.ProxyUser;
import com.model.User;

public interface UserService {

	public void createCollectionUsers();
	
	public void dropCollectionUsers();
	
	public void createUser(ProxyUser user);
	
	public String getDate();
	
	public void createUserDirPath(String userId);

	public User updateUser(User user);	
	
	public void uploadProfilePhoto(MultipartFile fileInput);
	
	public String deleteProfilePhoto(String userId, String photoName, boolean imageNameActive);
	
	public void setActiveProfilePhoto(String userId, String photoName);
}
