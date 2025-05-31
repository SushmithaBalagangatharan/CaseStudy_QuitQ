package com.hexaware.quitq.dto;

public class ReviewsDTO {
	 	private Long productId;
	    private String review;

	    public ReviewsDTO() {
	    }

		public ReviewsDTO(Long productId, String review) {
			super();
			this.productId = productId;
			this.review = review;	
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public String getReview() {
			return review;
		}

		public void setReview(String review) {
			this.review = review;
		}

	    
}
