package com.cartorgon.jsbe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cartorgon.jsbe.model.impl.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	@Query("FROM UserEntity WHERE username = :username")
	UserEntity findByUsername(@Param("username") final String username);
	
	@Query("FROM UserEntity WHERE username = :username AND password = :password")
	UserEntity findByUsernameAndPassword(
			@Param("username") final String username,
			@Param("password") final String password);
}
