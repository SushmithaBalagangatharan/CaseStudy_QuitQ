package com.hexaware.quitq.service.reviews;

import java.time.LocalDateTime;
import java.util.List;

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

	@Override
	public Reviews createReviews(ReviewsDTO reviewsDTO, UserInfo user) throws ProductNotFoundException {
		Product product = productService.findProductById(reviewsDTO.getProductId());
		
		Reviews review = new Reviews();
		review.setProduct(product);
		review.setUser(user);
		review.setReview(reviewsDTO.getReview());
		review.setCreatedAt(LocalDateTime.now());
		return reviewRepository.save(review);
	}

	@Override
	public List<Reviews> getProductReviews(Long productId) throws ProductNotFoundException {
		
		return reviewRepository.findByProductId(productId);
	}

	@Override
	public String deleteReviewById(Long reviewId) throws ReviewNotFoundException{
		reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException());
		
		reviewRepository.deleteById(reviewId);
		
		return "Review deleted successfully!";
	}
	
	@Override
	public List<Reviews> getReviewByUserId(Long userId) throws UserNotFoundException{
		userService.findUserById(userId);
		return reviewRepository.findByUserId(userId);
	}

	@Override
	public Reviews getReviewById(Long reviewId) throws ReviewNotFoundException {
		return reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException());
	}
}
