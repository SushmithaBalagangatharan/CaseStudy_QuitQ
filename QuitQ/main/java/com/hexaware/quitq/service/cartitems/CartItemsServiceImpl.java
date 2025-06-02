package com.hexaware.quitq.service.cartitems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.repository.CartItemRepository;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.product.IProductService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description CartItems Service class which contains methods performing CRUD operations on cartItems of the USER.
 * @date 2-06-2025
 * @version 1.0
 */


@Service
public class CartItemsServiceImpl implements ICartItemsService {

	@Autowired
	CartItemRepository cartItemRepository;
	@Lazy
	@Autowired
	ICartService cartService;
	@Autowired
	IUserService userService;
	@Autowired
	IProductService productService;
	
	Logger logger = LoggerFactory.getLogger("CartItemServiceImpl.class");
	
	@Override
	public CartItems createCartItem(Long userId, CartItemDTO cartItemDTO) throws ProductNotFoundException, CartItemNotFoundException, CartNotFoundException {
		
		logger.info("Creating cart item for userId={}, productId={}", userId, cartItemDTO.getProductId());

		Product product = productService.findProductById(cartItemDTO.getProductId());
		if(product == null) {
			logger.error("Product not found with ID: {}", cartItemDTO.getProductId());
			throw new ProductNotFoundException();
		}
		
		Cart cart = cartService.findUserCart(userId);
		
		CartItems cartItem = new CartItems();
		cartItem.setPrice(product.getPrice() * cartItemDTO.getQuantity());
		cartItem.setDiscountPrice(product.getDiscountedPrice() * cartItemDTO.getQuantity());
	
		cartItem.setProduct(product);
		cartItem.setSize(cartItemDTO.getSize());
		cartItem.setCart(cart);
		cartItem.setQuantitiy(cartItemDTO.getQuantity());
				
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItems updateCartItem(Long userId, Long itemId, int quantity) throws CartNotFoundException, CartItemNotFoundException, ProductNotFoundException{
		logger.info("Updating cart item ID={} for userId={} with new quantity={}", itemId, userId, quantity);
		
		Cart cart = cartService.findUserCart(userId);		
		if(cart == null) {
			logger.error("Cart item not found with ID: {}", itemId);
			throw new CartNotFoundException();
		}
				
		CartItems cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new CartItemNotFoundException());
		
		Product product = cartItem.getProduct();
		if(product == null) {
			logger.error("Product associated with cart item is null");
			throw new ProductNotFoundException();
		}
		
		cartItem.setQuantitiy(quantity);
		cartItem.setPrice(product.getPrice() * cartItem.getQuantitiy());
		cartItem.setDiscountPrice(product.getDiscountedPrice() * cartItem.getQuantitiy());;

		return cartItemRepository.save(cartItem);
		
	}

	@Override
	public CartItems isCartItemExists(Long cartId ,Long productId, String size, Long userId) {
		logger.debug("Checking if cart item exists for cartId={}, productId={}, size={}, userId={}",
	            cartId, productId, size, userId);
		return cartItemRepository.isCartItemExist(cartId, productId, size, userId);
	}

	//
	@Override
	public ResponseEntity<String> removeCartItem(Long userId, Long itemId) throws CartNotFoundException, CartItemNotFoundException{
		logger.info("Removing cart item ID={} for userId={}", itemId, userId);
		
		Cart cart = cartService.findUserCart(userId);
		
		CartItems item = findCartItemById(itemId);
		
		cart.getCartItemsList().remove(item);
		
		cartItemRepository.deleteById(itemId);
		
		logger.info("Cart item ID={} removed successfully", itemId);
		return new ResponseEntity<String>("Item Removed", HttpStatus.ACCEPTED);
	}
		
	

	@Override
	public CartItems findCartItemById(Long cartItemId) throws CartItemNotFoundException {
		logger.debug("Fetching cart item by ID: {}", cartItemId);
		return cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException());
	}
	
	@Override
	public CartItems addCartItem(Long userId, CartItemDTO cartItemDTO) 
								throws ProductNotFoundException, CartNotFoundException, CartItemNotFoundException {

		logger.info("Adding new cart item for userId={} and productId={}", userId, cartItemDTO.getProductId());
		
		Cart cart = cartService.findUserCart(userId);
		
		CartItems gotCartItem = isCartItemExists(cart.getCartId(), cartItemDTO.getProductId(), cartItemDTO.getSize(), userId);
		
		if(gotCartItem != null) {
			logger.warn("Duplicate cart item exists for userId={} and productId={} with size={}",
	                userId, cartItemDTO.getProductId(), cartItemDTO.getSize());
			
			throw new IllegalArgumentException("Product with same size exists in cart");
		}
		
		//Possibly assigning an auto-generated ID (like a primary key)
		//Saving newItem to the database (via repository)
		CartItems createdItem = createCartItem(userId, cartItemDTO);
		cart.getCartItemsList().add(createdItem);
		
		logger.info("Cart item added successfully with ID: {}", createdItem.getCartItemsId());
		return createdItem;
	}
	
	
	


}
