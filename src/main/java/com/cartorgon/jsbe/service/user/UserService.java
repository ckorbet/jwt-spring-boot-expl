package com.cartorgon.jsbe.service.user;

import com.cartorgon.jsbe.model.impl.UserDto;

public interface UserService {
	UserDto findUserByUsername(String username);
}
