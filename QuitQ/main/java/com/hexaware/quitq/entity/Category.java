package com.hexaware.quitq.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long categoryId;
	
	@Size(min=2, max=50, message = "name should be atleast 2 character")
	@NotNull
	private String name; // toplevel, secondlevel, thirdlevel
	
	private String description;
	
	@PositiveOrZero
	public int level;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product> productList = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_categry_id")
	private Category parentCategory;
	
	public Category() {
		super();
	}

	public Category(long categoryId,
			@Size(min = 2, max = 50, message = "aname should be atleast 2 character") @NotNull String name,
			String description, int level, List<Product> productList, Category parentCategory) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.level = level;
		this.productList = productList;
		this.parentCategory = parentCategory;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	
	
}

