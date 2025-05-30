package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Long>{

	public List<Rating> findByProductId(Long productId);
}
