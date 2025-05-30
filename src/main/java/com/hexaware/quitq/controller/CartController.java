package com.hexaware.quitq.controller;

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
 
	//testing
	@GetMapping("/get/{name}")
	public String getString(@PathVariable String name) {
		return name;
	}
	
	@PostMapping("/create/user")
	@PreAuthorize("hasAuthority('USER')")
	public Cart createCart(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		return cartService.createCart(user.getId());
	}

	@PostMapping("/create/email")
	@PreAuthorize("hasAuthority('USER')")
	public Cart createCartByEmail(@RequestHeader("Authorization") String jwt) throws UserNotFoundException  {
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		return cartService.createCartByEmail(user.getEmail());
	}
	
	@GetMapping("/usercart/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cart findUserCart(@PathVariable Long userId) throws CartNotFoundException, CartItemNotFoundException {
		return cartService.findUserCart(userId);
	}
	
	@GetMapping("/bycartid/{cartId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Cart findByCartId(@PathVariable Long cartId) throws CartNotFoundException {
		return cartService.findByCartId(cartId);
	}
	
	@DeleteMapping("/delete/{cartId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCartById(@PathVariable Long cartId) throws CartNotFoundException{
		return cartService.deleteCartById(cartId);
	}
}
