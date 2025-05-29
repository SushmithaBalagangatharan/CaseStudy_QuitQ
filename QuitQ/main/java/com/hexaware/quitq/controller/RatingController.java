package com.hexaware.quitq.controller;

import java.util.List;

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
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.rating.IRatingService;
import com.hexaware.quitq.service.user.IUserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	@Autowired
	IRatingService ratingService;
	@Autowired
	IUserService userService;

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Rating> createRating(@RequestBody RatingDTO ratingDTO, @RequestHeader("Authorization") String jwt)
								throws ProductNotFoundException, UserNotFoundException {
		String token = jwt.substring(7).trim();
		System.out.println(jwt);
		UserInfo userInfo = userService.findUserProfileByJwt(token);
		Rating rating = ratingService.createRating(ratingDTO, userInfo);
		
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public List<Rating> getProductsRating(@PathVariable Long productId) throws ProductNotFoundException {
		
		List<Rating> rating = ratingService.getProductsRating(productId);
		return rating;
	}
		
	
	
	
	
}
