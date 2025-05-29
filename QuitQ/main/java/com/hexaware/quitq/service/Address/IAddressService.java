package com.hexaware.quitq.service.Address;

import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.exception.AddressNotFoundException;

public interface IAddressService {

	Address createAddress(Address address);
	Address findByAddressId(Long id) throws AddressNotFoundException;
	ResponseEntity<String> deleteAddressByUd(Long Id) throws AddressNotFoundException;
	Address updateAddress(Address address);
}
