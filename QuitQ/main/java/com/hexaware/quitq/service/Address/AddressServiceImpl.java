package com.hexaware.quitq.service.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.repository.AddressRepository;

public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Address findByAddressId(Long id) throws AddressNotFoundException {
		
		return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
	}

	@Override
	public ResponseEntity<String> deleteAddressByUd(Long Id) throws AddressNotFoundException {
		findByAddressId(Id);
;		addressRepository.deleteById(Id);
		return new ResponseEntity<String>("Address Deleted Successfully", HttpStatus.OK);
	}

	@Override
	public Address updateAddress(Address address) {
		
		return addressRepository.save(address);
	}

	@Override
	public Address createAddress(Address address) {
		
		return addressRepository.save(address);
	}

}
