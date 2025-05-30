package com.hexaware.quitq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.AddressDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.Address.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Address findAddressById(@PathVariable Long id) throws AddressNotFoundException {
		return addressService.findAddressById(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER')")
	public ResponseEntity<String> deleteAddressById(@PathVariable Long id) throws AddressNotFoundException {
		return addressService.deleteAddressById(id);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('USER')")
	public Address updateAddress(@RequestBody Address address) {
		
		return addressService.updateAddress(address);
	}

	@PostMapping("/create")
	//@PreAuthorize("hasAuthority('USER')")
	public Address createAddress(@RequestBody AddressDTO addressDTO, @RequestHeader("Authorization") String jwt) throws UserNotFoundException {
		
		return addressService.createAddress(addressDTO, jwt);
	}

}
