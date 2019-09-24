package com.cartorgon.jsbe.service.auth;

import org.springframework.security.core.Authentication;

import com.cartorgon.jsbe.model.impl.UserDto;

public interface AuthenticationService {
	Authentication authenticate(UserDto user);
}
