package com.hexaware.quitq.service.cart;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.User;

public interface ICartService {
	
	Cart createCart(User user);
	CartItems addCartItem(Long userId, CartItemDTO dto);
	Cart findUserCart(Long userId);
	String removeCartItem(Long userId, Long itemId);
}
