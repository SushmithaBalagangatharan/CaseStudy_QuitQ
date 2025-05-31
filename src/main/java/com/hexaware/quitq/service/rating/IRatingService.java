package com.hexaware.quitq.service.rating;

import java.util.List;

import com.hexaware.quitq.dto.RatingDTO;
import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.RatingNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IRatingService {
	Rating createRating(RatingDTO ratingDTO, UserInfo userInfo) throws ProductNotFoundException, UserNotFoundException;
	List<Rating> getProductsRating(Long productId) throws ProductNotFoundException, RatingNotFoundException;

}
