package com.cartorgon.jsbe.rest.impl;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartorgon.jsbe.rest.JwtRest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JwtRestImpl implements JwtRest {
	
	@Override
	@GetMapping(path = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> jwt() {
		log.info("Received request to 'jwt'...");
		log.info("Serving response");
		return ResponseEntity.ok().build();
	}
}