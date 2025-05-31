package com.hexaware.quitq.service.Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger("AddressServiceImpl.class");

	@Override
	public Address findAddressById(Long id) throws AddressNotFoundException {
		logger.info("Attempting to find address with id: {}", id);
		 
		//return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
		
		return addressRepository.findById(id).orElseThrow(() -> {
            logger.error("Address not found for ID: {}", id);
            return new AddressNotFoundException();
        });
	}

	@Transactional
	@Override
	public ResponseEntity<String> deleteAddressById(Long addressId) throws AddressNotFoundException {
		logger.info("Attempting to delete address with ID: {}", addressId);
		
		Address address = findAddressById(addressId);
		
		// Break the bidirectional relationship from the User side
		UserInfo user = address.getUser();
		if(user!=null) {
			logger.debug("Unlinking address from user with ID: {}", user.getId());
			user.setAddress(null);
		}
		
		addressRepository.deleteById(addressId);
		logger.info("Successfully deleted address with ID: {}", addressId);
		return new ResponseEntity<String>("Address Deleted Successfully", HttpStatus.OK);
	}

	@Override
	public Address updateAddress(Long userId, AddressDTO addressDTO) throws UserNotFoundException {
		Address address = addressRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException());
		
		address.setFirstName(addressDTO.getFirstName());
		address.setLastName(addressDTO.getLastName());
		address.setStreetAddress(addressDTO.getStreetAddress());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setZipCode(addressDTO.getZipCode());
		address.setContactNumber(addressDTO.getContactNumber());
		
		return addressRepository.save(address);
	}

	@Override
	public Address createAddress(AddressDTO addressDTO, String jwt) throws UserNotFoundException {
		logger.info("Creating address for user from JWT: {}", jwt);
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
		
		Address saved = addressRepository.save(address);
	    logger.info("Address created with ID: {}", saved.getAddressId());
	    return saved;
	}

}
