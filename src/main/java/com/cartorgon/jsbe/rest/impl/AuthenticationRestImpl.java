package com.cartorgon.jsbe.rest.impl;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.rest.AuthenticationRest;
import com.cartorgon.jsbe.service.auth.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class AuthenticationRestImpl implements AuthenticationRest {
	
	@Autowired
	private AuthenticationService authService;
	
	@Override
	@PostMapping(value = "/authenticate")
	public final ResponseEntity<?> authenticate(@RequestBody @NotNull final UserDto user) {
		if(user.getUsername() == null || user.getUsername().isBlank() || 
				user.getPassword() == null || user.getPassword().isBlank()) {
			throw new IllegalArgumentException("Username and password cannot be null or blank");
		}
		log.info("Received request to 'authenticate(UserDto)'.....");
		this.authService.authenticate(user);		
		log.info("Serving response");
		return ResponseEntity.ok(user);
	}
}