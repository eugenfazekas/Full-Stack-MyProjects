
package com.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class InputStringService {
		
	public String readFromInputStream(InputStream inputStream) {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br
	      = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("");
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    		return resultStringBuilder.toString();
	}

	public String rawString(String inputFilePath) {	
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(inputFilePath);		
			return readFromInputStream(inputStream);
	}
}