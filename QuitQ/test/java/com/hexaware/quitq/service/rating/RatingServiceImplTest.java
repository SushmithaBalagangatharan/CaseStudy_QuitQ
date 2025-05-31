package com.hexaware.quitq.service.rating;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.RatingDTO;
import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.RatingNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

@SpringBootTest
class RatingServiceImplTest {
	
	@Autowired
	IRatingService ratingService;

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
	void createRating( ) throws ProductNotFoundException, UserNotFoundException {
		Long productId = 1L;
		double rating = 3.5;
		
		RatingDTO createdDTO = new RatingDTO(productId, rating);
		
		UserInfo user = new UserInfo();
		user.setId(1L);
		user.setUserName("sushmitha");
		user.setContactNumber(6789012345L);
		user.setPassword("abc@132");
		user.setGender("female");
		user.setEmail("sushmi@abc.com");
		user.setRole("USER");
		
		Rating createdRating = ratingService.createRating(createdDTO, user);
		assertNotNull(createdRating);
	}

	@Test
	void getProductsRating() throws ProductNotFoundException, RatingNotFoundException{
		Long productId = 1L;
		List<Rating> ratings = ratingService.getProductsRating(productId);
		
		assertTrue(ratings.size() > 0);
	}
}












