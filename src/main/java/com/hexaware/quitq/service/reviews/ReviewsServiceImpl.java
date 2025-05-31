package com.hexaware.quitq.service.reviews;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.ReviewsDTO;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.Reviews;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.ReviewNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.ReviewsRepository;
import com.hexaware.quitq.service.product.IProductService;
import com.hexaware.quitq.service.user.IUserService;

@Service
public class ReviewsServiceImpl implements IReviewService {
	
	@Autowired
	IProductService productService;
	@Autowired
	ReviewsRepository reviewRepository;
	@Autowired
	IUserService userService;

	Logger logger = LoggerFactory.getLogger("ReviewsServiceImpl.class");
	@Override
	public Reviews createReviews(ReviewsDTO reviewsDTO, UserInfo user) throws ProductNotFoundException {
		Product product = productService.findProductById(reviewsDTO.getProductId());
		
		Reviews review = new Reviews();
		review.setProduct(product);
		review.setUser(user);
		review.setReview(reviewsDTO.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		logger.info("Created review for user {}", user);
		return reviewRepository.save(review);
	}

	@Override
	public List<Reviews> getProductReviews(Long productId) throws ProductNotFoundException {
		
		logger.info("Fetching product review for product Id {}", productId);
		return reviewRepository.findByProductId(productId);
	}

	@Override
	public String deleteReviewById(Long reviewId) throws ReviewNotFoundException{
		reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException());
		
		reviewRepository.deleteById(reviewId);
		
		logger.warn("Deleted review by review ID {}", reviewId);
		return "Review deleted successfully!";
	}
	
	@Override
	public List<Reviews> getReviewByUserId(Long userId) throws UserNotFoundException{
		userService.findUserById(userId);
		
		logger.info("Got review by user ID {}", userId);
		return reviewRepository.findByUserId(userId);
	}

	@Override
	public Reviews getReviewById(Long reviewId) throws ReviewNotFoundException {
		logger.info("Got review by review ID {}", reviewId);
		return reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException());
	}
}
