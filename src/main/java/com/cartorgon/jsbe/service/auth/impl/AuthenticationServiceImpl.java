package com.cartorgon.jsbe.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.security.JwtUserDetailsService;
import com.cartorgon.jsbe.security.filter.impl.JwtOncePerRequestFilter;
import com.cartorgon.jsbe.service.auth.AuthenticationService;
import com.cartorgon.jsbe.service.auth.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authMgr;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetSer;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public final Authentication authenticate(final UserDto user) {
		if(user == null) {
			throw new IllegalArgumentException("User to authenticate cannot be null or empty");
		}
		log.info(String.format("Authenticating '%s' user.....", user.getUsername()));
		final UserDetails userDetails = this.jwtUserDetSer.loadUserByUsername(user.getUsername());
		Authentication resAuth = null;
		if(userDetails == null) {
			log.warn(String.format("User '%s' has no details", user.getUsername()));
		} else {
			final String resToken = this.jwtService.generateToken(userDetails);
			if(resToken == null || resToken.isBlank()) {
				log.error("Token NOT generated !!");
			} else {
				log.info("Token correctly generated");
				user.setToken(resToken);
				resAuth = this.authMgr.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), userDetails.getAuthorities()));
			}			
		}		
		log.info(String.format("User %s authenticated", user.getUsername()));
		return resAuth;
	}
}