package com.hexaware.quitq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.cartitems.ICartItemsService;
import com.hexaware.quitq.service.user.IUserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	ICartItemsService cartItemService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger("CartItemController.class");
	
	//get all cart items
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<CartItems> createCartItem(@RequestHeader("Authorization") String jwt,@RequestBody CartItemDTO cartItem) 
									 throws ProductNotFoundException, UserNotFoundException, CartNotFoundException, CartItemNotFoundException {
		logger.info("Creating cart item for user JWT: "+ jwt);
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		CartItems createdCartItem = cartItemService.createCartItem(user.getId(), cartItem);
		logger.debug("Cart item created with ID: ", createdCartItem.getCartItemsId());
		return new ResponseEntity<CartItems>(createdCartItem, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{cartItemId}/{quantity}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<CartItems> updateCartItem(@RequestHeader("Authorization") String jwt, @PathVariable Long cartItemId, @PathVariable int quantity) 
							throws CartNotFoundException, CartItemNotFoundException, UserNotFoundException, ProductNotFoundException{
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		CartItems createdCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, quantity);
		
		logger.info("Cart item updated successfully for ID: "+ createdCartItem.getCartItemsId());
		
		return new ResponseEntity<CartItems>(createdCartItem, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{itemId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> removeCartItem(@RequestHeader("Authorization") String jwt, @PathVariable Long itemId)
							throws CartNotFoundException, CartItemNotFoundException, UserNotFoundException{
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.warn("Removing Cart Item with ID: "+ itemId);
		return cartItemService.removeCartItem(user.getId(), itemId);
	}
	
	@GetMapping("/get/{cartItemId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<CartItems> findCartItemById(@PathVariable Long cartItemId) throws CartItemNotFoundException {
		CartItems cartItem = cartItemService.findCartItemById(cartItemId);
		logger.info("Cart item found: {}", cartItem.getCartItemsId());
		return new ResponseEntity<CartItems>(cartItem, HttpStatus.FOUND);
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<CartItems> addCartItem(@RequestHeader("Authorization") String jwt,@RequestBody CartItemDTO cartItemDTO) 
							throws ProductNotFoundException, CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt);
		CartItems addedCartItem = cartItemService.addCartItem(user.getId(), cartItemDTO);
		logger.info("Cart item added successfully with ID: {}", addedCartItem.getCartItemsId());
		return new ResponseEntity<CartItems>(addedCartItem, HttpStatus.CREATED);
	}
}


