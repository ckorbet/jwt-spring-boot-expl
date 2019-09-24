package com.cartorgon.jsbe.security.filter.impl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cartorgon.jsbe.security.JwtUserDetailsService;
import com.cartorgon.jsbe.service.auth.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

	private static final String BEARER_STRING = "Bearer ";

	@Autowired
	private JwtUserDetailsService jwtUserDetSer;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Filtering request.....");		
		final String jwtToken = this.getJWTFromRequest(request);
		if(jwtToken != null && !jwtToken.isBlank()) {			
			try {
				final String username = jwtService.getUsernameFromToken(jwtToken);
				if(username != null && !username.isBlank()) {
					log.info("Username obtained from JWT");
					final UserDetails userDetails = this.validateTokenVsUsername(jwtToken, username);
					if(userDetails != null) {
						this.setAuthenticationInSecurityContext(userDetails, request);
						log.info("Token positively validate");
					} else {
						log.info("Token negastively validate");
					}
				}
			} catch (final IllegalArgumentException iaExcp) {
				log.error("Unable to get JWT token", iaExcp);
			} catch (final ExpiredJwtException ejExcp) {
				log.error("JWT token expired");
			} catch(final MalformedJwtException mfjExcp) {
				log.error("JWT malformed", mfjExcp);
			}
		}		
		filterChain.doFilter(request, response);
	}
	
	private String getJWTFromRequest(final HttpServletRequest request) {
		String resJwtToken = null;
		final String requestTokenHeader = request.getHeader("Authorization");
		if(requestTokenHeader != null && !requestTokenHeader.isBlank()) {
			if(requestTokenHeader.startsWith(BEARER_STRING)) {
				resJwtToken = requestTokenHeader.substring(BEARER_STRING.length());
				log.info("Bearer token extracted from request");
			} else {
				log.info("Authorization token is not a Bearer token");
			}			
		} else {
			log.warn("Request does not contain any Authorization token");
		}
		return resJwtToken;
	}
	
	private void setAuthenticationInSecurityContext(final UserDetails userDetails, final HttpServletRequest request) {
		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		log.info("User authenticated and available at Spring Security Context");
	}
	
	private UserDetails validateTokenVsUsername(final String jwt, final String username) {
		UserDetails userDetails = null;
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			userDetails = this.jwtUserDetSer.loadUserByUsername(username);
			if(!jwtService.validateToken(jwt, userDetails)) {
				userDetails = null;
			}
		}
		log.info("UserDetails validated");
		return userDetails;
	}
}
