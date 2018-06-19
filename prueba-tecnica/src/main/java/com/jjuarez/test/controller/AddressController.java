package com.jjuarez.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjuarez.test.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable(value="id") Long addressId) {
		return addressService.deleteAddress(addressId);
	}
}
