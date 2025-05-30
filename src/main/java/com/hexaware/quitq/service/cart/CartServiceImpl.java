package com.hexaware.quitq.service.cart;

import java.util.List;

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

@Service
public class CartServiceImpl implements ICartService{

	@Autowired
	CartRepository cartRepository;
	@Autowired
	IProductService productService;
	@Autowired
	UserRepository userRepository;

	
//	@Override
//	public Cart createCart(UserDTO user) {
//		Cart cart = new Cart();
//		cart.setUser(user);
//		return cartRepository.save(cart);
//	}
	
	
	@Override
	public Cart createCart(Long userId) throws UserNotFoundException {
		UserInfo user = userRepository.findById(userId)
		        .orElseThrow(() -> new UserNotFoundException());

	    Cart cart = new Cart();
	    cart.setUser(user);
	    return cartRepository.save(cart);
	}
	

	@Override
	public Cart findUserCart(Long userId) throws CartNotFoundException, CartItemNotFoundException {
		Cart cart = cartRepository.findByUserId(userId);
		
		if(cart == null) {
			throw new CartNotFoundException();
		}
//		User adds/removes cart items	Only cart items are updated (not always the summary)
//		User opens their cart	findUserCart(userId) is called, recalculates totals
	
		Integer totalPrice = 0;
		Integer totalDiscountPrice = 0;
		Integer totalItem = 0;
		
		List<CartItems> items = cart.getCartItemsList();
		if(items == null) {
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
		
		return cart;
	}
	


	@Override
	public Cart findByCartId(Long cartId) throws CartNotFoundException {
		return cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException());
	}


	@Override
	public ResponseEntity<String> deleteCartById(Long cartId) throws CartNotFoundException {
		Cart cart = findByCartId(cartId);
		cartRepository.delete(cart);
		return new ResponseEntity<String>("Cart deleted successfully", HttpStatus.OK);
	}


	@Override
	public Cart createCartByEmail(String email) {
		UserInfo user = userRepository.findByEmail(email);
	    Cart cart = new Cart();
	    cart.setUser(user);
	    return cartRepository.save(cart);
	}



}
