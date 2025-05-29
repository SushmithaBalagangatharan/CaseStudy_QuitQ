package com.hexaware.quitq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.cart.ICartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	ICartService cartService;

	@GetMapping("/get/{name}")
	public String getString(@PathVariable String name) {
		return name;
	}
	
	@PostMapping("/createbyuser/{userId}")
	public Cart createCart(@PathVariable Long userId) throws UserNotFoundException {
		return cartService.createCart(userId);
	}

	@PostMapping("/createbyemail/{email}")
	public Cart createCartByEmail(@PathVariable String email)  {
		return cartService.createCartByEmail(email);
	}
	
	@GetMapping("/usercart/{userId}")
	public Cart findUserCart(@PathVariable Long userId) throws CartNotFoundException {
		return cartService.findUserCart(userId);
	}
	
	@GetMapping("/bycartid/{cartId}")
	public Cart findByCartId(@PathVariable Long cartId) throws CartNotFoundException {
		return cartService.findByCartId(cartId);
	}
	
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<String> deleteCartById(@PathVariable Long cartId) throws CartNotFoundException{
		return cartService.deleteCartById(cartId);
	}
}
