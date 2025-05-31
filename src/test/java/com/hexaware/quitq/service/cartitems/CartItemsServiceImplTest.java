package com.hexaware.quitq.service.cartitems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.CartItemDTO;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

@SpringBootTest
class CartItemsServiceImplTest {

	@Autowired 
	ICartItemsService cartItemService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createCartItem()
					throws ProductNotFoundException, CartItemNotFoundException, CartNotFoundException {
		Long userId = 52L;
		
		Long productId = 102L;
		String size = "M";
		int quantity = 2;
		Integer price = 3000;
		
		CartItemDTO cartItemDTO = new CartItemDTO();
		
		cartItemDTO.setProductId(productId);
		cartItemDTO.setSize(size);
		cartItemDTO.setQuantity(quantity);
		cartItemDTO.setPrice(price);
		
		CartItems  cartItem = cartItemService.createCartItem(userId, cartItemDTO);
		
		assertNotNull(cartItem);
	}
		
	
	@Test
	void addCartItem() 
			throws ProductNotFoundException, CartNotFoundException, CartItemNotFoundException {
		Long userId = 52L;
		
		Long productId = 2L;
		String size = "M";
		int quantity = 2;
		Integer price = 3000;
		
		CartItemDTO cartItemDTO = new CartItemDTO();
		
		cartItemDTO.setProductId(productId);
		cartItemDTO.setSize(size);
		cartItemDTO.setQuantity(quantity);
		cartItemDTO.setPrice(price);
		
		CartItems cartItem = cartItemService.addCartItem(userId, cartItemDTO);
		
		assertNotNull(cartItem);
	}

	@Test
	void findCartItemById() throws CartItemNotFoundException {
		Long cartItemId = 103L;
		CartItems cartItem = cartItemService.findCartItemById(cartItemId);
		
		assertEquals(cartItem.getCartItemsId(), 103);
	}
	
	@Test
	void isCartItemExists() {
		Long cartId = 1L;
		Long productId = 2L;
		String size = "L";
		Long userId = 3L;
		
		
		CartItems cartItem = cartItemService.isCartItemExists(cartId, productId, size, userId);
		
		assertNotNull(cartItem);
	}
	
	
	@Test
	void updateCartItem() throws CartNotFoundException, CartItemNotFoundException, ProductNotFoundException{
		Long userId = 52L;
		Long itemId = 152L;
		int quantity = 4;
		
		CartItems cartItem = cartItemService.updateCartItem(userId, itemId, quantity);
		
		assertNotNull(cartItem);
		assertTrue(cartItem.getQuantitiy()== 4);
	}


	@Test
	void removeCartItem() throws CartNotFoundException, CartItemNotFoundException, UserNotFoundException{
		Long userId = 52L;
		Long itemId = 152L;
		
		cartItemService.removeCartItem(userId, itemId);
		
		assertThrows(CartItemNotFoundException.class  ,() -> cartItemService.findCartItemById(itemId));
	}
}
