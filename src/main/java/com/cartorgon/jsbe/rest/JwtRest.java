package com.cartorgon.jsbe.rest;

import org.springframework.http.ResponseEntity;

public interface JwtRest {    
    ResponseEntity<String> jwt();    
}