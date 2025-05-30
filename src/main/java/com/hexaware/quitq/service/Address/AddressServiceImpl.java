package com.hexaware.quitq.service.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.AddressDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.AddressRepository;
import com.hexaware.quitq.service.user.IUserService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	IUserService userService;

	@Override
	public Address findAddressById(Long id) throws AddressNotFoundException {
		
		return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
	}

	@Override
	public ResponseEntity<String> deleteAddressById(Long Id) throws AddressNotFoundException {
		findAddressById(Id);
		addressRepository.deleteById(Id);
		return new ResponseEntity<String>("Address Deleted Successfully", HttpStatus.OK);
	}

	@Override
	public Address updateAddress(Address address) {
		
		return addressRepository.save(address);
	}

	@Override
	public Address createAddress(AddressDTO addressDTO, String jwt) throws UserNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		
		Address address = new Address();
		
		address.setFirstName(addressDTO.getFirstName());
		address.setLastName(addressDTO.getLastName());
		address.setStreetAddress(addressDTO.getStreetAddress());
		address.setCity(addressDTO.getCity());
		address.setContactNumber(addressDTO.getContactNumber());
		address.setState(addressDTO.getState());
		address.setZipCode(addressDTO.getZipCode());
		address.setUser(user);
		
		return addressRepository.save(address);
	}

}
