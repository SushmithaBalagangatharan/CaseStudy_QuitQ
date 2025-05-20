package com.hexaware.quitqecom.component;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartItems> cartItemsList = new ArrayList<>();
	

	public Cart() {
		super();
	}

	public Cart(int id, User user, List<CartItems> cartItemsList) {
		super();
		this.id = id;
		this.user = user;
		this.cartItemsList = cartItemsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
