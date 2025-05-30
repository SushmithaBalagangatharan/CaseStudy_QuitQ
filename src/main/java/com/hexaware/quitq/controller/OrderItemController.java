package com.hexaware.quitq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.OrderItems;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.OrderItemNotFoundException;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.orderitem.IOrderItemService;

@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {
	
	@Autowired
	IOrderItemService orderItemService;
	@Autowired
	ICartService cartService;
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public OrderItems createOrderItem(OrderItems orderItem) throws OrderItemNotFoundException {
		return orderItemService.createOrderItem(orderItem);
	}

	
	@PostMapping("/creat_from_cart")
	@PreAuthorize("hasAuthority('USER')")
	public List<OrderItems> createOrderItemsFromCart(@PathVariable Long cartId) throws CartNotFoundException{
		Cart cart = cartService.findByCartId(cartId);
		return orderItemService.createOrderItemsFromCart(cart);
	}

}
