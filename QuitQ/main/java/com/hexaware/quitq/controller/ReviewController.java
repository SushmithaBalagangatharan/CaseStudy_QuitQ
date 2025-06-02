package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.ReviewsDTO;
import com.hexaware.quitq.entity.Reviews;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.ReviewNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.reviews.IReviewService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description Review ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */

@RestController
@RequestMapping("api/reviews/")
public class ReviewController {
	
	@Autowired
	IReviewService reviewService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger("PrviewController.class");
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public Reviews createReviews(@RequestBody ReviewsDTO reviewsDTO,@RequestHeader("Authorization") String jwt) throws ProductNotFoundException, UserNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.debug("Creating review by userId: {}", user.getId());
		return reviewService.createReviews(reviewsDTO, user);
	}
	
	@GetMapping("/product/{productId}")
	public List<Reviews> getProductReviews(@PathVariable Long productId) throws ProductNotFoundException{
		List<Reviews> reviews = reviewService.getProductReviews(productId);
	    logger.info("Found {} reviews for productId: {}", reviews.size(), productId);
	    return reviews;
	}
	
	@DeleteMapping("/{reviewId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReviewById(@PathVariable Long reviewId) throws ReviewNotFoundException{
		 logger.warn("Review with id {} is getting deleting", reviewId);
		return reviewService.deleteReviewById(reviewId);
	}
	

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Reviews> getReviewByUserId(@PathVariable Long userId) throws UserNotFoundException{
		List<Reviews> reviews = reviewService.getReviewByUserId(userId);
	    logger.info("Found {} reviews by userId: {}", reviews.size(), userId);
	    return reviews;
	}
	
	@GetMapping("/{reviewId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Reviews getReviewById(@PathVariable Long reviewId) throws ReviewNotFoundException {
		logger.info("Fetching review with id: {}", reviewId);
		return reviewService.getReviewById(reviewId);
	}
}
