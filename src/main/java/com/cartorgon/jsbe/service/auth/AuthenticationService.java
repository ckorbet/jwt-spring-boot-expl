package com.cartorgon.jsbe.service.auth;

import com.cartorgon.jsbe.model.impl.UserDto;

public interface AuthenticationService {
	void authenticate(UserDto user);
}
