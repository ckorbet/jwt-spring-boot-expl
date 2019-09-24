package com.cartorgon.jsbe.security.impl;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>
 * This class will extend Spring's AuthenticationEntryPoint class and override its method to commence. 
 * It rejects every unauthenticated request and sends error code 401.
 * </p>
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public final void commence(final HttpServletRequest request, 
							   final HttpServletResponse response,
							   final AuthenticationException authException) throws IOException {
		log.error(String.format("Responding with unauthorized error: %s", authException.getMessage()));
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}