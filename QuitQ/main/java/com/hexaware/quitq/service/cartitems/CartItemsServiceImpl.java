package com.hexaware.quitq.service.cartitems;

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
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.CartItemRepository;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.product.IProductService;
import com.hexaware.quitq.service.user.IUserService;

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
	
	@Override
	public CartItems createCartItem(CartItems cartItem) {
		
		cartItem.setPrice(cartItem.getPrice() * cartItem.getQuantitiy());
		cartItem.setDiscountPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantitiy());;
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItems updateCartItem(Long userId, Long id, int quantity) throws CartNotFoundException, CartItemNotFoundException{
		Cart cart = cartService.findUserCart(userId);
		
		if(cart == null) {
			throw new CartNotFoundException();
		}
		
		CartItems cartItem = cartItemRepository.findById(id).orElseThrow(() -> new CartItemNotFoundException());
		cartItem.setQuantitiy(quantity);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantitiy());
		cartItem.setDiscountPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantitiy());;

		return cartItemRepository.save(cartItem);
		
	}

	@Override
	public CartItems isCartItemExists(Long cartId ,Product product, String size, Long userId) {
		return cartItemRepository.isCartItemExist(cartId, product, size, userId);
	}

	//
	@Override
	public ResponseEntity<String> removeCartItem(Long userId, Long itemId) throws CartNotFoundException, CartItemNotFoundException{
		Cart cart = cartService.findUserCart(userId);
		
		CartItems item = findCartItemById(itemId);
		
		cart.getCartItemsList().remove(item);
		
		return new ResponseEntity<String>("Item Removed", HttpStatus.ACCEPTED);
	}
		
	

	@Override
	public CartItems findCartItemById(Long cartItemId) throws CartItemNotFoundException {
		
		return cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException());
	}
	
	@Override
	public CartItems addCartItem(Long userId, CartItemDTO dto) throws ProductNotFoundException, CartNotFoundException {

		Cart cart = cartService.findByCartId(userId);
		Product product = productService.findProductById(dto.getProductId());
		
		CartItems newItem = new CartItems();
		newItem.setProduct(product);
		newItem.setCart(cart);
		newItem.setQuantitiy(dto.getQuantity());
		newItem.setPrice(dto.getPrice());
		newItem.setSize(dto.getSize());
		
		//Possibly assigning an auto-generated ID (like a primary key)
		//Saving newItem to the database (via repository)
		CartItems createdItem = createCartItem(newItem);
		cart.getCartItemsList().add(createdItem);
		
		return createdItem;
	}
	
	
	


}
