package com.cartorgon.jsbe.service.auth;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String getUsernameFromToken(String token);
	Date getExpirationDateFromToken(String token);
	<T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
	String generateToken(UserDetails userDetails);
	Boolean validateToken(String token, UserDetails userDetails);
}