package com.cartorgon.jsbe.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

import com.cartorgon.jsbe.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Validated
@Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity implements User, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name = "username", columnDefinition = "VARCHAR", nullable = false)
	private String username;
	@Column(name = "password", columnDefinition = "VARCHAR", nullable = false)
	private String password;
	@Column(name = "token", columnDefinition = "VARCHAR", nullable = true)
	private String token;
	
	public UserEntity(final String username, final String password, final String token) {
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public static final UserEntity fromUserDto(final UserDto userDto) {
		return new UserEntity(userDto.getUsername(), 
							  userDto.getPassword(), 
							  userDto.getToken());
	}
}
