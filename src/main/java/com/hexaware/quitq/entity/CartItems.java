package com.hexaware.quitq.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="cart_items")
public class CartItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartItemsId;
	
	@Positive(message = "Quantity must be greater than 0")
	private Integer quantity;
	
	private Integer price;
	private Integer discountPrice;
	private String size;
	
	@ManyToOne
	@JoinColumn(name="cart_id", nullable=false)
	@JsonBackReference
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public CartItems() {
		super();
	}

	public CartItems(Long cartItemsId, Integer quantitiy, Integer price, Integer discountPrice, String size, Cart cart,
			Product product) {
		super();
		this.cartItemsId = cartItemsId;
		this.quantity = quantitiy;
		this.price = price;
		this.discountPrice = discountPrice;
		this.size = size;
		this.cart = cart;
		this.product = product;
	}

	public Long getCartItemsId() {
		return cartItemsId;
	}

	public void setCartItemsId(Long cartItemsId) {
		this.cartItemsId = cartItemsId;
	}

	public Integer getQuantitiy() {
		return quantity;
	}

	public void setQuantitiy(Integer quantitiy) {
		this.quantity = quantitiy;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
