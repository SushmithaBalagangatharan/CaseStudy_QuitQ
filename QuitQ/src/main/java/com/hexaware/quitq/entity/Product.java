package com.hexaware.quitq.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String title;
	private String description;
	@Positive
	private double price;
	private int discountedPrice;
	private int discountedPercent;
	private int quantity;
	private String brand;
	@NotBlank
	private String imageUrl;
	private String color;
	private LocalDateTime createdAt;
	private int numOfRatings;
	
	@Transient
	private String topLevelCategory;
	@Transient
	private String secondLevelCategory;
	@Transient
	private String thirdLevelCategory;


	@Embedded
	@ElementCollection
	private Set<Size> sizes = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Rating> ratings = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; //seller
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	@JsonBackReference
	private List<Reviews> review = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "categories_id")
	private Category categories;
	
	
	
	public Product() {
		
	}



	public Product(long id, @NotNull String title, String description, @Positive double price, int discountedPrice,
			int discountedPercent, int quantity, String brand, @NotBlank String imageUrl, String color,
			LocalDateTime createdAt, int numOfRatings, String topLevelCategory, String secondLevelCategory,
			String thirdLevelCategory, Set<Size> sizes, List<Rating> ratings, User user, List<Reviews> review,
			Category categories) {
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
		this.topLevelCategory = topLevelCategory;
		this.secondLevelCategory = secondLevelCategory;
		this.thirdLevelCategory = thirdLevelCategory;
		this.sizes = sizes;
		this.ratings = ratings;
		this.user = user;
		this.review = review;
		this.categories = categories;
	}





	public long getId() {
		return id;
	}



	public void setId(long id) {
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



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getDiscountedPrice() {
		return discountedPrice;
	}



	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}



	public int getDiscountedPercent() {
		return discountedPercent;
	}



	public void setDiscountedPercent(int discountedPercent) {
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Reviews> getReview() {
		return review;
	}



	public void setReview(List<Reviews> review) {
		this.review = review;
	}



	public Category getCategories() {
		return categories;
	}



	public void setCategories(Category categories) {
		this.categories = categories;
	}

	
	
	
}
