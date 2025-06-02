package com.hexaware.quitq.service.cart;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.CartRepository;
import com.hexaware.quitq.repository.UserRepository;
import com.hexaware.quitq.service.product.IProductService;

import jakarta.transaction.Transactional;

/*
 * @author Sushmitha B A
 * @description Cart Service class which contains methods performing CRUD operations on cart of the USER.
 * @date 2-06-2025
 * @version 1.0
 */


@Service
public class CartServiceImpl implements ICartService{

	@Autowired
	CartRepository cartRepository;
	@Autowired
	IProductService productService;
	@Autowired
	UserRepository userRepository;


	
	Logger logger = LoggerFactory.getLogger("CartServiceImpl.class");
	
	@Override
	public Cart createCart(Long userId) throws UserNotFoundException {
		logger.info("Creating cart for userId: {}", userId);
		
		UserInfo user = userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFoundException());

	    Cart cart = new Cart();
	    cart.setUser(user);
	    logger.info("Cart created with ID: {}", cart.getCartId());
	    
	    return cartRepository.save(cart);
	}
	

	@Override
	@Transactional // because cart-items is lazy loaded by the springboot, Keeps session open during method execution
	public Cart findUserCart(Long userId) throws CartNotFoundException, CartItemNotFoundException {
		logger.info("Fetching cart for userId: {}", userId);
		
		Cart cart = cartRepository.findByUserId(userId);
		if(cart == null) {
			logger.warn("Cart not found for userId: {}", userId);
			throw new CartNotFoundException();
		}
//		User adds/removes cart items.Only cart items are updated (not always the summary)
//		User opens their cart	findUserCart(userId) is called, recalculates totals
	
		Integer totalPrice = 0;
		Integer totalDiscountPrice = 0;
		Integer totalItem = 0;
		
		List<CartItems> items = cart.getCartItemsList();
		if(items == null) {
			logger.warn("Cart items not found for cartId: {}", cart.getCartId());
			 throw new CartItemNotFoundException();
		}
		
		for(CartItems cartItem : items) {
			totalPrice += cartItem.getPrice();
			totalDiscountPrice += cartItem.getDiscountPrice();
			totalItem += cartItem.getQuantitiy();
		}
		
		cart.setTotalDiscountPrice(totalDiscountPrice);
		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(totalItem);
		cart.setDiscount(totalPrice - totalDiscountPrice);
		
		 logger.debug("Cart totals calculated for userId {}: totalPrice={}, totalDiscount={}, totalItems={}",
		            userId, totalPrice, totalDiscountPrice, totalItem);
		 
		return cart;
	}
	

	@Override
	public Cart findByCartId(Long cartId) throws CartNotFoundException {
		logger.info("Fetching cart by ID: {}", cartId);
		return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException());
	}


	@Override
	public ResponseEntity<String> deleteCartById(Long cartId) throws CartNotFoundException {
		logger.info("Deleting cart with ID: {}", cartId);
		
		Cart cart = findByCartId(cartId);
		cartRepository.delete(cart);
		
		 logger.info("Cart deleted successfully with ID: {}", cartId);
		return new ResponseEntity<String>("Cart deleted successfully", HttpStatus.OK);
	}


	@Override
	public Cart createCartByEmail(String email) {
		logger.info("Creating cart for user with email: {}", email);
		
		UserInfo user = userRepository.findByEmail(email);
	    Cart cart = new Cart();
	    cart.setUser(user);
	    
	    logger.info("Cart created with ID: {} for email: {}", cart.getCartId(), email);
	    return cartRepository.save(cart);
	}



}
