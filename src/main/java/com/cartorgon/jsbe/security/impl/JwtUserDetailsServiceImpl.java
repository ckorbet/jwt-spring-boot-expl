package com.cartorgon.jsbe.security.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.security.JwtUserDetailsService;
import com.cartorgon.jsbe.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public final UserDetails loadUserByUsername(final String username) {
		if(username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username cannot be null or empty");
		}
		
		final UserDto userDto = this.userService.findUserByUsername(username);
		UserDetails userDetails = null;
		if(userDto == null) {
			throw new UsernameNotFoundException(String.format("Username '%s' not found", username));
		} else {
			log.info(String.format("User '%s' found. Building details.....", username));
			userDetails = new User(userDto.getUsername(), userDto.getPassword(), Collections.emptyList());
			log.info("User details built");
		}
		return userDetails;
	}
}