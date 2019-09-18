package com.cartorgon.jsbe.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.model.impl.UserDto;
import com.cartorgon.jsbe.model.impl.UserEntity;
import com.cartorgon.jsbe.persistence.UserRepository;
import com.cartorgon.jsbe.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public final UserDto findUserByUsername(final String username) {
		if(username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username cannot be null or empty");
		}	
		
		final UserEntity userEntity = this.userRepo.findByUsername(username);
		UserDto userDto = null;
		if(userEntity == null) {
			log.warn(String.format("User '%s' does NOT exist", username));
		} else {
			userDto = UserDto.fromUserEntity(userEntity);
		}
		return userDto;
	}
}