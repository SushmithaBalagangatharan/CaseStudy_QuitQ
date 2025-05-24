package com.hexaware.quitq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name="id")
	private Long cartItemsId;
	
	@Positive
	private Integer quantitiy;
	private Long price;
	private Integer discountPrice;
	private String size;
	
	@ManyToOne
	@JoinColumn(name="cart_id", nullable=false)
	@JsonIgnore
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public CartItems() {
		super();
	}

	public CartItems(Long cartItemsId, Integer quantitiy, Long price, Integer discountPrice, String size, Cart cart,
			Product product) {
		super();
		this.cartItemsId = cartItemsId;
		this.quantitiy = quantitiy;
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
		return quantitiy;
	}

	public void setQuantitiy(Integer quantitiy) {
		this.quantitiy = quantitiy;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
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
