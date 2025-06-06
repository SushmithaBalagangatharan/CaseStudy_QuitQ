package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/*
 * @author Sushmitha B A
 * @description Order Items ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */

@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {
	
	@Autowired
	IOrderItemService orderItemService;
	@Autowired
	ICartService cartService;
	
	Logger logger = LoggerFactory.getLogger(OrderItemController.class);
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('USER')")
	public OrderItems createOrderItem(OrderItems orderItem) throws OrderItemNotFoundException {
		logger.debug("Creating OrderItem: {}", orderItem);
		return orderItemService.createOrderItem(orderItem);
	}

	
	@PostMapping("/creat_from_cart")
	@PreAuthorize("hasAuthority('USER')")
	public List<OrderItems> createOrderItemsFromCart(@PathVariable Long cartId) throws CartNotFoundException{
		Cart cart = cartService.findByCartId(cartId);
		logger.info("OrderItems created for cartId: {}", cartId);
		return orderItemService.createOrderItemsFromCart(cart);
	}

}
