package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.ProxyUser;
import com.model.User;

@Component
public class ProxyServer {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private RestTemplate rest;
	
	@Value("${RESOURCE_SERVER_BASE_URL}")
	private String baseURL;
	
	public void sendNewUserId(String id) {
		String url = baseURL + "/user/createUserResource";
		
		var body = new ProxyUser();
		body.setId(id);
		
		var request = new HttpEntity<>(body);
		
		var response = rest.postForEntity(url, request, Void.class);
		
		log.debug("Resource Server Status = "+response.getStatusCode().toString()+ "with Id " +id);
	}
}
