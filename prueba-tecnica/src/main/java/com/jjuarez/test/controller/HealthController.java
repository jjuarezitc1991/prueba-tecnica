package com.jjuarez.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {
	
	@GetMapping("/")
	public ResponseEntity<?> isUp(){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
