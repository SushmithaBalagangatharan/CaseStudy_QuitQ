package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.RatingDTO;
import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.RatingNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.rating.IRatingService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description Rating ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	@Autowired
	IRatingService ratingService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger(RatingController.class);

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Rating> createRating(@RequestBody RatingDTO ratingDTO, @RequestHeader("Authorization") String jwt)
								throws ProductNotFoundException, UserNotFoundException {
		String token = jwt.substring(7).trim();
		System.out.println(jwt);
		UserInfo userInfo = userService.findUserProfileByJwt(token);
		logger.debug("Creating rating for userId: {}", userInfo.getId());
		Rating rating = ratingService.createRating(ratingDTO, userInfo);
		
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public List<Rating> getProductsRating(@PathVariable Long productId) throws ProductNotFoundException, RatingNotFoundException {
		
		List<Rating> rating = ratingService.getProductsRating(productId);
		
		logger.info("Fetched {} ratings for productId: {}", rating.size(), productId);

		return rating;
	}
		
	
	
}
