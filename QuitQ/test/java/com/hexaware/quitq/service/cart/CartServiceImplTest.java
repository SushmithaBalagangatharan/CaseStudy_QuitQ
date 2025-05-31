package com.hexaware.quitq.service.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

@SpringBootTest
class CartServiceImplTest {
	
	@Autowired
	ICartService cartService;

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
	void createCart() throws UserNotFoundException {
		Long userId  = 52L;
		Cart cart = cartService.createCart(userId);
		
		assertEquals(userId, cart.getUser().getId());
		assertTrue(cart.getCartItemsList().isEmpty());
	}
	
	@Test
	@DisplayName("Find Cart using user ID")
	void findUserCart() throws CartNotFoundException, CartItemNotFoundException{
		Long userId = 52L;
		Cart cart = cartService.findUserCart(userId);
		
		assertEquals(userId, cart.getUser().getId());
	}
	
	@Test
	void findByCartId() throws CartNotFoundException {
		Long cartId = 2L;
		Cart cart = cartService.findByCartId(cartId);
		
		assertEquals(cartId, cart.getCartId());
	}

	@Test
	void deleteCartById() throws CartNotFoundException {
		Long cartId = 2L;
		cartService.deleteCartById(cartId);
		
		assertThrows(CartNotFoundException.class, () -> cartService.findByCartId(cartId));
	}
	
	@Test
	void createCartByEmail() {
		String email = "aura@abc.com";
		Cart cart = cartService.createCartByEmail(email);
		
		assertEquals(cart.getUser().getId(), 52);
	}
}







