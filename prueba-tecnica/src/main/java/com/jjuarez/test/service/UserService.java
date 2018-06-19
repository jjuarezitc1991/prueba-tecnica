package com.jjuarez.test.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jjuarez.test.exception.ResourceNotFoundException;
import com.jjuarez.test.model.Address;
import com.jjuarez.test.model.User;
import com.jjuarez.test.repository.AddressRepository;
import com.jjuarez.test.repository.UserRepository;

@Service
public class UserService {
	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	AddressRepository addressRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(Long userId, User userDetails) {
		User user = userRepository.findById(userId)
				 .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		user.setName(userDetails.getName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		
		for(int i = 0; i < userDetails.getAddress().size(); i++) {
			user.getAddress().get(i).setStreet(userDetails.getAddress().get(i).getStreet());
			user.getAddress().get(i).setNeighborhood(userDetails.getAddress().get(i).getNeighborhood());
			user.getAddress().get(i).setNumber(userDetails.getAddress().get(i).getNumber());
		}
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	public ResponseEntity<?> deleteUser(Long userId){
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}

	public User appendAddress(Long userId, @Valid Address address) {
		User user = userRepository.findById(userId)
				 .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		address.setUser(user); 
		user.getAddress().add(address);
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
}
