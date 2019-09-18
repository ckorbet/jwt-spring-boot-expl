package com.cartorgon.jsbe.model.impl;

import com.cartorgon.jsbe.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDto implements User {

	private String username;
	private String password;
	private String token;
	
	public static final UserDto fromUserEntity(final UserEntity userEntity) {
		return new UserDto(userEntity.getUsername(), 
						   userEntity.getPassword(), 
						   userEntity.getToken());
	}
}
