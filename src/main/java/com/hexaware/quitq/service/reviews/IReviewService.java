package com.hexaware.quitq.service.reviews;

import java.util.List;

import com.hexaware.quitq.dto.ReviewsDTO;
import com.hexaware.quitq.entity.Reviews;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.ReviewNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IReviewService {

	Reviews createReviews(ReviewsDTO reviewDTO, UserInfo user) throws ProductNotFoundException;
	List<Reviews> getProductReviews(Long productId) throws ProductNotFoundException;
	String deleteReviewById(Long reviewId) throws ReviewNotFoundException;
	List<Reviews> getReviewByUserId(Long userId) throws UserNotFoundException;
	Reviews getReviewById(Long reviewId) throws ReviewNotFoundException;
}
