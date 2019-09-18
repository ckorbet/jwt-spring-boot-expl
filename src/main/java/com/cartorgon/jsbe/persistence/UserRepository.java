package com.cartorgon.jsbe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cartorgon.jsbe.model.impl.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	@Query("SELECT * FROM user WHERE username = :id")
	UserEntity findByUsername(@Param("username") String username);
}
