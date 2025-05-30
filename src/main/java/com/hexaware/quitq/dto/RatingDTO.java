package com.hexaware.quitq.dto;

public class RatingDTO {

	// since we have manytoone relation with product in ratings, we have a seperate productid here
	private Long productId;
	private double rating;
	
	public RatingDTO() {
		
	}

	
	public RatingDTO(Long productId, double rating) {
		super();
		this.productId = productId;
		this.rating = rating;
	}



	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	
	
}
