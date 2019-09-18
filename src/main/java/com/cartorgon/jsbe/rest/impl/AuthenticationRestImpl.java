package com.cartorgon.jsbe.rest.impl;

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
	public final ResponseEntity<?> authenticate(@RequestBody final UserDto user) {
		log.info("Received request to 'authenticate(UserDto)'.....");
		this.authService.authenticate(user);
		log.info("Serving response");
		return null;
	}

}
