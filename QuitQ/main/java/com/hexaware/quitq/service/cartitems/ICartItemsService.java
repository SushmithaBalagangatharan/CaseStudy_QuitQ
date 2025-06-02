package com.hexaware.quitq.service.cartitems;

import org.springframework.http.ResponseEntity;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface ICartItemsService {

	CartItems createCartItem(Long userId, CartItemDTO cartItemDTO) throws ProductNotFoundException, CartNotFoundException, CartItemNotFoundException;
	CartItems updateCartItem(Long userId, Long id, int quantity)  throws CartNotFoundException, CartItemNotFoundException, ProductNotFoundException;
	CartItems isCartItemExists(Long cartId, Long productId, String size, Long userId);
	ResponseEntity<String> removeCartItem(Long userId, Long cartItemId) throws UserNotFoundException, CartNotFoundException, CartItemNotFoundException ;
	CartItems findCartItemById(Long cartItemId) throws CartItemNotFoundException;
	CartItems addCartItem(Long userId, CartItemDTO dto) throws ProductNotFoundException, CartNotFoundException, CartItemNotFoundException;
}
