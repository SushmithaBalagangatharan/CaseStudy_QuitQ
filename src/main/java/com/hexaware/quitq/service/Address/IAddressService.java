package com.hexaware.quitq.service.Address;

import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.dto.AddressDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IAddressService {

	Address createAddress(AddressDTO address, String jwt) throws UserNotFoundException;
	Address findAddressById(Long id) throws AddressNotFoundException;
	ResponseEntity<String> deleteAddressById(Long Id) throws AddressNotFoundException;
	Address updateAddress(Address address);
}
