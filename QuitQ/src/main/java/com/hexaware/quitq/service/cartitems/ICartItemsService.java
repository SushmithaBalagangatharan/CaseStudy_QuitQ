package com.hexaware.quitq.service.cartitems;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.Product;

public interface ICartItemsService {

	CartItems createCartItem(CartItems cartItem);
	void updateCartItem(Long userId, Long id, int quantity);
	CartItems isCartItemExists(Cart cart, Product product, String size, Long userId);
	void removeCartItem(Long userId, Long cartItemId);
	CartItems findCartItemById(Long cartItemId);
}
