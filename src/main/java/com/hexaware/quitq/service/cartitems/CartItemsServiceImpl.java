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
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
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
	
//quantity, size, cart_id
	@Override
	public CartItems createCartItem(Long userId, CartItemDTO cartItemDTO) throws ProductNotFoundException, CartItemNotFoundException, CartNotFoundException {
		
		
		Product product = productService.findProductById(cartItemDTO.getProductId());
		if(product == null) {
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
		
		System.out.println("Before Saving cart item: Product ID = " + product.getId() + ", Cart ID = " + cart.getCartId());
		
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItems updateCartItem(Long userId, Long itemId, int quantity) throws CartNotFoundException, CartItemNotFoundException, ProductNotFoundException{
		Cart cart = cartService.findUserCart(userId);		
		if(cart == null) {
			throw new CartNotFoundException();
		}
				
		CartItems cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new CartItemNotFoundException());
		
		Product product = cartItem.getProduct();
		if(product == null) {
			throw new ProductNotFoundException();
		}
		
		cartItem.setQuantitiy(quantity);
		cartItem.setPrice(product.getPrice() * cartItem.getQuantitiy());
		cartItem.setDiscountPrice(product.getDiscountedPrice() * cartItem.getQuantitiy());;

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
	public CartItems addCartItem(Long userId, CartItemDTO cartItemdto) 
								throws ProductNotFoundException, CartNotFoundException, CartItemNotFoundException {

		Cart cart = cartService.findByCartId(userId);
		//Possibly assigning an auto-generated ID (like a primary key)
		//Saving newItem to the database (via repository)
		CartItems createdItem = createCartItem(userId, cartItemdto);
		cart.getCartItemsList().add(createdItem);
		
		return createdItem;
	}
	
	
	


}
