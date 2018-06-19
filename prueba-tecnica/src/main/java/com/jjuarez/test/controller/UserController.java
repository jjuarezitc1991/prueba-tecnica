package com.jjuarez.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjuarez.test.model.Address;
import com.jjuarez.test.model.User;
import com.jjuarez.test.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {  
	 
	@Autowired 
	UserService userService;
	
	@GetMapping("/") 
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value="id") Long userId) {
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	@PostMapping("/") 
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/address")
	public ResponseEntity<User> appendAddress(@PathVariable(value="id") Long userId,
							@Valid @RequestBody Address address) {
		return new ResponseEntity<>(userService.appendAddress(userId, address), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value="id") Long userId,
							@Valid @RequestBody User userDetails) {
		return new ResponseEntity<>(userService.updateUser(userId, userDetails), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") Long userId) {
		return userService.deleteUser(userId);
	}
}
