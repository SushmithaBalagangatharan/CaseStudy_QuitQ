package com.hexaware.quitq.service.rating;

import java.util.List;

import com.hexaware.quitq.entity.Rating;
import com.hexaware.quitq.entity.User;

public interface IRatingService {
	
	Rating createRating(Double rating, User user);
	List<Rating> getProductsRating(Long productId);

}
