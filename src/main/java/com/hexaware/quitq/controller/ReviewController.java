package com.hexaware.quitq.controller;

import java.util.List;

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

@RestController
@RequestMapping("api/reviews/")
public class ReviewController {
	
	@Autowired
	IReviewService reviewService;
	@Autowired
	IUserService userService;
	
	@PostMapping("/create")
	@PreAuthorize("hasAuhtority('USER')")
	public Reviews createReviews(@RequestBody ReviewsDTO reviewsDTO,@RequestHeader("Authorization") String jwt) throws ProductNotFoundException, UserNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt);
		return reviewService.createReviews(reviewsDTO, user);
	}
	
	@GetMapping("/product/{productId}")
	public List<Reviews> getProductReviews(@PathVariable Long productId) throws ProductNotFoundException{
		return reviewService.getProductReviews(productId);
	}
	
	@DeleteMapping("/product/{productId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteReviewById(@PathVariable Long reviewId) throws ReviewNotFoundException{
		return reviewService.deleteReviewById(reviewId);
	}
	

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Reviews> getReviewByUserId(@PathVariable Long userId) throws UserNotFoundException{
		return reviewService.getReviewByUserId(userId);
	}
	
	@GetMapping("/{reviewId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Reviews getReviewById(Long reviewId) throws ReviewNotFoundException {
		return reviewService.getReviewById(reviewId);
	}
}
