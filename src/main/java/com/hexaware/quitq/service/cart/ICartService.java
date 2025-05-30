package com.hexaware.quitq.service.cart;

import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.dto.UserDTO;
import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface ICartService {
	
	
	Cart findUserCart(Long userId) throws CartNotFoundException, CartItemNotFoundException;
	Cart findByCartId(Long cartId) throws CartNotFoundException;
	ResponseEntity<String> deleteCartById(Long cartId) throws CartNotFoundException;
	Cart createCartByEmail(String email);
	
	public Cart createCart(Long userId) throws UserNotFoundException;
}
