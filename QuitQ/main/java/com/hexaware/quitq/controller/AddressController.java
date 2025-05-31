package com.hexaware.quitq.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.Address.IAddressService;
import com.hexaware.quitq.service.user.IUserService;


@RestController
@RequestMapping("/api/address")
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	@Autowired
	IUserService userService;
	
	
	Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Address findAddressById(@PathVariable Long id) throws AddressNotFoundException {
		logger.info("Finding Employee with ID "+id);
		return addressService.findAddressById(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER')")
	public ResponseEntity<String> deleteAddressById(@PathVariable Long id) throws AddressNotFoundException {
		logger.warn("Record got deleted by id "+ id);
		return addressService.deleteAddressById(id);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('USER')")
	public Address updateAddress(@RequestHeader("Authorization") String jwt ,@RequestBody AddressDTO addressDTO) throws UserNotFoundException {
		logger.info("Recieved request to update address for JWT: "+jwt);
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.info("updated successfully");
		return addressService.updateAddress(user.getId(), addressDTO);
	}

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public Address createAddress(@RequestBody AddressDTO addressDTO, @RequestHeader("Authorization") String jwt) throws UserNotFoundException {
		logger.info("Created Address");
		return addressService.createAddress(addressDTO, jwt);
	}

}
