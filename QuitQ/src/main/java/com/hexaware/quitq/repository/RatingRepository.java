package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Long>{

}
