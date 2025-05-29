package com.hexaware.quitq.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long reviewId;
	private String review;
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JsonBackReference("user-review")
	@JoinColumn(name = "user_id", nullable = false)
	private UserInfo user;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	public Reviews(){
		super();
	}

	public Reviews(Long reviewId, String review, LocalDateTime createdAt, UserInfo user, Product product) {
		super();
		this.reviewId = reviewId;
		this.review = review;
		this.createdAt = createdAt;
		this.user = user;
		this.product = product;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
