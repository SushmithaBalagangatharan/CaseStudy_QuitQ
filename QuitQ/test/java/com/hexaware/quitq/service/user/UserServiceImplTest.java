package com.hexaware.quitq.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.quitq.dto.UserDTO;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private IUserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
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

	@ParameterizedTest
	@DisplayName("Test for finding user by id")
	@ValueSource(longs = {1L, 2L, 3L, 4L})
	void testFindByUserId(Long userId) throws UserNotFoundException {
		//Long userId = (long) 1;
		UserInfo user = userService.findUserById(userId);
		assertNotNull(user);
		assertEquals(user.getId(), userId);
	}
	
	@Test
	void testDeleteUserById() throws UserNotFoundException {
		Long userId = (long) 53;
		userService.deleteUserById(userId);
		assertThrows(UserNotFoundException.class, () -> userService.findUserById(userId));
	}
	
	@Test
	void testRegisterUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName("Kennel");
		userDTO.setEmail("kennel@abc.com");
		userDTO.setPassword(passwordEncoder.encode("car"));
		userDTO.setGender("male");
		userDTO.setContactNumber(96178901236L);
		userDTO.setCreatedAt(LocalDate.now());
		userDTO.setRole("ADMIN");
		
		userService.registerUser(userDTO);
		
		UserInfo user = userRepository.findByEmail(userDTO.getEmail());
		assertNotNull(user, "Registered user");
	}
	
	@Test
	void testGetAllUser() {
		assertTrue(userService.getAllUser().size() > 0 );
	}
	
	@Test
	void testFindUserProfileByJwt() throws UserNotFoundException {
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJPbWVyIiwiaWF0IjoxNzQ4NDg3MjA5LCJleHAiOjE3NDg0ODkwMDl9.mkLxE2NlAxVw1yx9RMDoxI65N8AegiPLXkuw-X-u0o8";
		UserInfo user = userService.findUserProfileByJwt(jwt);
		assertNotNull(user, "User exists");
	}
}
