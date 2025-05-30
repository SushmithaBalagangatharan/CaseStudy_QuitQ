package com.hexaware.quitq.service.reviews;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.quitq.dto.ReviewsDTO;
import com.hexaware.quitq.entity.Reviews;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.ReviewNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.user.IUserService;

@SpringBootTest
class ReviewsServiceImplTest {
	
	@Autowired
	IReviewService reviewService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IUserService userService;

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
	@DisplayName("Creating a review for product")
	void testCreateReviews() throws ProductNotFoundException, UserNotFoundException {
		Long productId = 1L;
	    String review = "Excellent";
	    LocalDateTime createdAt = LocalDateTime.now();
	    
	    ReviewsDTO reviewDTO = new ReviewsDTO(productId, review);
	    
	    UserInfo user = userService.findUserById(2L);
	    
		Reviews createdReview = reviewService.createReviews(reviewDTO, user);
		
		assertNotNull(createdReview);
		
		assertEquals(createdReview.getUser().getUserName(), "Ron");
	}
	
	@Test
	void getProductReviews() throws ProductNotFoundException{
		Long productId = 1L;
		List<Reviews> reviews = reviewService.getProductReviews(productId);
		
		assertFalse(reviews.size() < 0);
	}
	
	@Test
	void deleteReviewById() throws UserNotFoundException, ReviewNotFoundException{
		Long reviewId = 2L;
		reviewService.deleteReviewById(reviewId);
		assertThrows(ReviewNotFoundException.class, () -> reviewService.getReviewById(reviewId));
	}

	@Test
	void getReviewByUserId() throws UserNotFoundException{
		Long userId = 2L;
		List<Reviews> reviewList = reviewService.getReviewByUserId(userId);
		assertTrue(reviewList.size() == 1);
	}
	
	@Test
	void getReviewById() throws ReviewNotFoundException {
		Long reviewId = 52L;
		assertNotNull(reviewService.getReviewById(reviewId));
	}
}


















