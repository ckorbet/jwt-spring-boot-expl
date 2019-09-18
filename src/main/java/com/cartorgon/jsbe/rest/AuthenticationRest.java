package com.cartorgon.jsbe.rest;

import org.springframework.http.ResponseEntity;

import com.cartorgon.jsbe.model.impl.UserDto;

public interface AuthenticationRest {
	ResponseEntity<?> authenticate(UserDto user);
}
