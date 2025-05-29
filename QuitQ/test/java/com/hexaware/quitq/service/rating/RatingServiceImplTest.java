package com.hexaware.quitq.service.rating;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.quitq.dto.RatingDTO;
import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.UserInfo;

class RatingServiceImplTest {

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
	void createRating(RatingDTO ratingDTO, UserInfo userInfo) {
		Long productId = 101L;
		double rating = 3.5;
		
	
	}

}
