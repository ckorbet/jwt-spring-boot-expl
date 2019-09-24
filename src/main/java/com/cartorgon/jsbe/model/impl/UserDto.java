package com.cartorgon.jsbe.model.impl;

import com.cartorgon.jsbe.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserDto implements User {

	@JsonProperty
	private String username;
	@JsonProperty
	private String password;
	@JsonProperty
	private String token;
	
	public static final UserDto fromUserEntity(final UserEntity userEntity) {
		return new UserDto(userEntity.getUsername(), 
						   userEntity.getPassword(), 
						   userEntity.getToken());
	}
	
	public UserDto(final String username, final String password) {
		this.username = username;
		this.password = password;
	}
}
