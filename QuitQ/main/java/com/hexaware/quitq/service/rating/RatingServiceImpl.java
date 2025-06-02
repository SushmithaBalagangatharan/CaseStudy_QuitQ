package com.hexaware.quitq.service.rating;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.RatingDTO;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.RatingNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.RatingRepository;
import com.hexaware.quitq.service.product.IProductService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description Cart Service class which contains methods for creating and fetching product ratings.
 * @date 2-06-2025
 * @version 1.0
 */


@Service
public class RatingServiceImpl implements IRatingService {
	
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	IProductService productService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger("RatingServiceImpl.class");

	@Override
	public Rating createRating(RatingDTO ratingDTO, UserInfo userInfo) throws ProductNotFoundException, UserNotFoundException {
		
		Product product = productService.findProductById(ratingDTO.getProductId());
		
		Rating userRatings = new Rating();
		
		userRatings.setRating(ratingDTO.getRating());
		userRatings.setUser(userInfo);
		userRatings.setProduct(product);;
		userRatings.setCreatedAt(LocalDateTime.now());
		
	    product.setNumOfRatings(product.getNumOfRatings()+1);
	    
	   
	    logger.info("Created ratings {}",userRatings );
		return ratingRepository.save(userRatings);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) throws ProductNotFoundException, RatingNotFoundException {
		
		productService.findProductById(productId);
		
		List<Rating> ratingsList =  ratingRepository.findByProductId(productId);
		
		if(ratingsList.isEmpty()) 
		{
			throw new RatingNotFoundException();
		}
		
		logger.info("Fetched product ratings {}", ratingsList);
		
		return ratingsList;
	}

}

