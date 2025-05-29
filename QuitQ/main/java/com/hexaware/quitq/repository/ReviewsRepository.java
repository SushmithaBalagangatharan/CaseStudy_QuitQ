package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

	public List<Reviews> findByProductId(Long productId);
	
	public List<Reviews> findByUserId(Long userId);
}
