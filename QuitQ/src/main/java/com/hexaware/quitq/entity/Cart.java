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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<CartItems> cartItemsList = new ArrayList<>();
	
	@Column(name="total_price")
	private Double totalPrice;
	
	@Column(name="total_item")
	private Integer totalItem;
	private Integer totalDiscountPrice;
	private Integer discount;
	
	public  Cart() {
		super();
	}
	
	
	public Cart(long cartId, User user, List<CartItems> cartItemsList, double totalPrice, int totalItem,
			int totalDiscountPrice, int discount) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.cartItemsList = cartItemsList;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.totalDiscountPrice = totalDiscountPrice;
		this.discount = discount;
	}


	public long getCartId() {
		return cartId;
	}


	public void setCartId(long cartId) {
		this.cartId = cartId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<CartItems> getCartItemsList() {
		return cartItemsList;
	}


	public void setCartItemsList(List<CartItems> cartItemsList) {
		this.cartItemsList = cartItemsList;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}


	public int getTotalDiscountPrice() {
		return totalDiscountPrice;
	}


	public void setTotalDiscountPrice(int totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
}


