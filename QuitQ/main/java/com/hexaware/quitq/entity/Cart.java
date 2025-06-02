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
	@JsonBackReference
	private UserInfo user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<CartItems> cartItemsList = new ArrayList<>();
	
	@Column(name="total_price")
	private Integer totalPrice;
	
	@Column(name="total_item")
	private Integer totalItem;
	private Integer totalDiscountPrice;
	private Integer discount;
	
	public  Cart() {
		super();
	}

	public Cart(Long cartId, UserInfo user, List<CartItems> cartItemsList, Integer totalPrice, Integer totalItem,
			Integer totalDiscountPrice, Integer discount) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.cartItemsList = cartItemsList;
		this.totalPrice = totalPrice;
		this.totalItem = totalItem;
		this.totalDiscountPrice = totalDiscountPrice;
		this.discount = discount;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<CartItems> getCartItemsList() {
		return cartItemsList;
	}

	public void setCartItemsList(List<CartItems> cartItemsList) {
		this.cartItemsList = cartItemsList;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Integer getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(Integer totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	
	
	
	
	
}


