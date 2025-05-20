package com.hexaware.quitqecom.service;

import com.hexaware.quitqecom.component.Cart;

public interface CartManagement {

	Cart getUserCart(int userId);
	Cart addToCart(int userId, Long productId, int quantity);
	Cart updateCartItemQuantity(int userId, Long productId);
	Cart removeItemFromCart(int userId, Long productId);
	double calculateCartTotal(int userId);
}
