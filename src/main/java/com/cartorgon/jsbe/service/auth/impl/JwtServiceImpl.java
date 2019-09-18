package com.cartorgon.jsbe.service.auth.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cartorgon.jsbe.service.auth.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {	
	
	@Value("${spring.application.jwt.expiracy}")
	private long jwtExpiracy;
	
	@Value("${spring.application.jwt.passphrase}")
	private String jwtPassphrase;

	@Override
	public final String getUsernameFromToken(final String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	@Override
	public final Date getExpirationDateFromToken(final String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	@Override
	public final <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(getAllClaimsFromToken(token));
	}

	private Claims getAllClaimsFromToken(final String token) {
		return Jwts.parser().setSigningKey(jwtPassphrase).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(final String token) {
		return getExpirationDateFromToken(token).before(new Date());
	}

	@Override
	public final String generateToken(final UserDetails userDetails) {
		return doGenerateToken(new HashMap<>(), userDetails.getUsername());
	}

	private String doGenerateToken(final Map<String, Object> claims, final String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiracy * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtPassphrase).compact();
	}

	@Override
	public final Boolean validateToken(final String token, final UserDetails userDetails) {
		return (getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}