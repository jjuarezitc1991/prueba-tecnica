package com.jjuarez.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jjuarez.test.exception.ResourceNotFoundException;
import com.jjuarez.test.model.Address;
import com.jjuarez.test.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public ResponseEntity<?> deleteAddress(Long addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
		addressRepository.delete(address);
		return ResponseEntity.ok().build();
	}
	
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}
}
