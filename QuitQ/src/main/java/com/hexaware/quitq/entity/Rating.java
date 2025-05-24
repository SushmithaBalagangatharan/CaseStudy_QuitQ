package com.hexaware.quitq.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long ratingId;
	
	private Double rating;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public Rating() {
		super();
	}

	public Rating(Long ratingId, Double rating, LocalDateTime createdAt, User user, Product product) {
		super();
		this.ratingId = ratingId;
		this.rating = rating;
		this.createdAt = createdAt;
		this.user = user;
		this.product = product;
	}

	public Long getRatingId() {
		return ratingId;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
