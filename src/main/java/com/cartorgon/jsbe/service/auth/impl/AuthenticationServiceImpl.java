package com.cartorgon.jsbe.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.service.auth.AuthenticationService;
import com.cartorgon.jsbe.service.auth.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authMgr;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public final void authenticate(final UserDto user) {
		if(user == null) {
			throw new IllegalArgumentException("User to authenticate cannot be null or empty");
		}
		log.info(String.format("Authenticating %s user.....", user.getUsername()));
		log.info("Generating authentication token.....");
		final UsernamePasswordAuthenticationToken usrPwdAuthToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		log.info("Authentication token generated");
		this.authMgr.authenticate(usrPwdAuthToken);
		log.info(String.format("User %s authenticated", user.getUsername()));		
	}
	
	public class JwtUserDetailsService implements UserDetailsService {

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
