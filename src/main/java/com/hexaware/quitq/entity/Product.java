package com.hexaware.quitq.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String title;
	private String description;
	@Positive
	private Integer price;
	private Integer discountedPrice;
	private Integer discountedPercent;
	private int quantity;
	private String brand;
	@NotBlank
	private String imageUrl;
	private String color;
	private LocalDateTime createdAt;
	private int numOfRatings;
	

	@Embedded
	@ElementCollection
	private Set<Size> sizes = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference("product-rating")
	private List<Rating> ratings = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo user; //seller
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference("product-review")
	private List<Reviews> review = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "categories_id")
	@JsonBackReference
	private Category category;
	
	
	
	public Product() {
		
	}


	public Product(Long id, @NotNull String title, String description, @Positive Integer price, Integer discountedPrice,
			Integer discountedPercent, int quantity, String brand, @NotBlank String imageUrl, String color,
			LocalDateTime createdAt, int numOfRatings, Set<Size> sizes, List<Rating> ratings, UserInfo user,
			List<Reviews> review, Category categories) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountedPercent = discountedPercent;
		this.quantity = quantity;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.color = color;
		this.createdAt = createdAt;
		this.numOfRatings = numOfRatings;
		this.sizes = sizes;
		this.ratings = ratings;
		this.user = user;
		this.review = review;
		this.category = categories;
	}





	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public Integer getDiscountedPrice() {
		return discountedPrice;
	}



	public void setDiscountedPrice(Integer discountedPrice) {
		this.discountedPrice = discountedPrice;
	}



	public Integer getDiscountedPercent() {
		return discountedPercent;
	}



	public void setDiscountedPercent(Integer discountedPercent) {
		this.discountedPercent = discountedPercent;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public int getNumOfRatings() {
		return numOfRatings;
	}



	public void setNumOfRatings(int numOfRatings) {
		this.numOfRatings = numOfRatings;
	}



	public Set<Size> getSizes() {
		return sizes;
	}



	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}



	public List<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}



	public UserInfo getUser() {
		return user;
	}



	public void setUser(UserInfo user) {
		this.user = user;
	}



	public List<Reviews> getReview() {
		return review;
	}



	public void setReview(List<Reviews> review) {
		this.review = review;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category categories) {
		this.category = categories;
	}


	
	
}
