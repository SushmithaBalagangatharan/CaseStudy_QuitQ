package com.hexaware.quitq.service.cartitems;

import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface ICartItemsService {

	CartItems createCartItem(CartItems cartItem);
	CartItems updateCartItem(Long userId, Long id, int quantity)  throws CartNotFoundException, CartItemNotFoundException;
	CartItems isCartItemExists(Long cartId, Product product, String size, Long userId);
	ResponseEntity<String> removeCartItem(Long userId, Long cartItemId) throws UserNotFoundException, CartNotFoundException, CartItemNotFoundException ;
	CartItems findCartItemById(Long cartItemId) throws CartItemNotFoundException;
	CartItems addCartItem(Long userId, CartItemDTO dto) throws ProductNotFoundException, CartNotFoundException;
}
