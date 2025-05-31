package com.hexaware.quitq.service.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.AddressDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.exception.AddressNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

@SpringBootTest
class AddressServiceImplTest {

	@Autowired
	IAddressService addressService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createAddress() throws UserNotFoundException {
		String firstName = "Aura";
		String lastName = "Smith";
		String streetAddress = "palace 202-02";
		String city = "Newyork";
		String state = "New York State";
		String zipCode = "nys-589";
		String contactNumber = "3456786554";
		
		AddressDTO addressDTO = new AddressDTO();
		
		String jwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXJhIiwiaWF0IjoxNzQ4NTk3MjUyLCJleHAiOjE3NDg1OTkwNTJ9.VeBVMcBFxg8sPUzAr2fgqH-ALhN7RCZNAwf5669Viy4";
		
		addressDTO.setFirstName(firstName);
		addressDTO.setLastName(lastName);
		addressDTO.setStreetAddress(streetAddress);
		addressDTO.setCity(city);
		addressDTO.setState(state);
		addressDTO.setZipCode(zipCode);
		addressDTO.setContactNumber(contactNumber);
		
		Address address = addressService.createAddress(addressDTO, jwtToken);
		
		assertNotNull(address);
	}
	
	@Test
	void updateAddress() throws UserNotFoundException {
		Long userId = 52L;
		
		String firstName = "Aura";
		String lastName = "Smith";
		String streetAddress = "palace 202-02";
		String city = "Newyork City";
		String state = "New York State";
		String zipCode = "nyc-589";
		String contactNumber = "3456786554";
		
		AddressDTO addressDTO = new AddressDTO();
				
		addressDTO.setFirstName(firstName);
		addressDTO.setLastName(lastName);
		addressDTO.setStreetAddress(streetAddress);
		addressDTO.setCity(city);
		addressDTO.setState(state);
		addressDTO.setZipCode(zipCode);
		addressDTO.setContactNumber(contactNumber);
		
		Address address = addressService.updateAddress(userId, addressDTO);
		
		assertNotNull(address);
		assertEquals("Newyork City",address.getCity());
	}
	
	@Test
	void deleteAddressById() throws AddressNotFoundException {
		Long addressId = 52L;
		addressService.deleteAddressById(addressId);
		assertThrows(AddressNotFoundException.class, () -> addressService.findAddressById(addressId));
	}
}
