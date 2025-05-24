package com.hexaware.quitq.service.review;

import java.util.List;

import com.hexaware.quitq.entity.Reviews;
import com.hexaware.quitq.entity.User;

public interface IReviewService {

	Reviews createReviews(String review, User user);
	List<Reviews> getProductReviews(Long productId);
}
