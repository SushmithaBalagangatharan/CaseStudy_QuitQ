package com.hexaware.quitq.dto;

import java.time.LocalDateTime;

public class ReviewsDTO {
	 	private Long productId;
	    private String review;
	    private LocalDateTime createdAt;

	    public ReviewsDTO() {
	    }
	    
	    

		public ReviewsDTO(Long productId, String review, LocalDateTime createdAt) {
			super();
			this.productId = productId;
			this.review = review;
			this.createdAt = createdAt;
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


		public LocalDateTime getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

	    
}
