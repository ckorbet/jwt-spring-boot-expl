package com.cartorgon.jsbe.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.service.auth.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authMgr;
	
	@Override
	public final void authenticate(final UserDto user) {
		if(user == null) {
			throw new IllegalArgumentException("User to authenticate cannot be null or empty");
		}
		log.info(String.format("Authenticating '%s' user.....", user.getUsername()));
		log.info("Generating authentication token.....");
		final UsernamePasswordAuthenticationToken usrPwdAuthToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		log.info("Authentication token generated");
		this.authMgr.authenticate(usrPwdAuthToken);
		log.info(String.format("User %s authenticated", user.getUsername()));		
	}
}
