package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
	
	@Query("SELECT ci FROM CartItems ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2 AND ci.size = ?3 AND ci.cart.user.id = ?4")
	public CartItems isCartItemExist(Long cartId, Long productId, String size, Long userId);

}
