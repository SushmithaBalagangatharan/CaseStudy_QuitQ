package com.hexaware.quitq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.user.IUserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	ICartService cartService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger(CartController.class);
 
	//testing
	@GetMapping("/get/{name}")
	public String getString(@PathVariable String name) {
		logger.info("Testting endpoint");
		return name;
	}
	
	@PostMapping("/create/user")
	@PreAuthorize("hasAuthority('USER')")
	public Cart createCart(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
		logger.info("Request to create cart for user via JWT");
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.debug("Auhenticated user ID : "+user.getId());
		return cartService.createCart(user.getId());
	}

	@PostMapping("/create/email")
	@PreAuthorize("hasAuthority('USER')")
	public Cart createCartByEmail(@RequestHeader("Authorization") String jwt) throws UserNotFoundException  {
		logger.info("Request to create cart by user email via JWT");
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.info("Cart created for user email: {}", user.getEmail());
		return cartService.createCartByEmail(user.getEmail());
	}
	
	@GetMapping("/usercart/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cart findUserCart(@PathVariable Long userId) throws CartNotFoundException, CartItemNotFoundException {
		logger.info("Request to fetch cart for user ID: ", userId);
		return cartService.findUserCart(userId);
	}
	
	@GetMapping("/bycartid/{cartId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cart findByCartId(@PathVariable Long cartId) throws CartNotFoundException {
		logger.info("Request to fetch cart for cart ID: {}", cartId);
		return cartService.findByCartId(cartId);
	}
	
	@DeleteMapping("/delete/{cartId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCartById(@PathVariable Long cartId) throws CartNotFoundException{
		logger.warn("Record got deleted with cart ID: "+cartId);
		return cartService.deleteCartById(cartId);
	}
}
