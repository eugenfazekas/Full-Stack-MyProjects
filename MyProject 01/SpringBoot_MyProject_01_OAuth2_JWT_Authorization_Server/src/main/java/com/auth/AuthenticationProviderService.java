package com.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.impl.UserDetailsImpl;
import com.service.impl.UserDetailsServiceImpl;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();	
		UserDetailsImpl user = userDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
						username,
						password,
						user.getAuthorities()
					);
		} else {
			throw new BadCredentialsException("Something went wrong in authentication!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType ) {

		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
