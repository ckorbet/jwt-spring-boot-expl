package com.cartorgon.jsbe.rest.impl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartorgon.jsbe.rest.JwtRest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
public class JwtRestImpl implements JwtRest {

	@GetMapping
	@Override
	public ResponseEntity<String> jwt() {
		log.info("Received request to 'jwt(user, password)'...");
		// TODO
		log.info("Serving response");
		return null;
	}
}