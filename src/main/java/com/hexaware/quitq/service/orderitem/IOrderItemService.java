package com.hexaware.quitq.service.orderitem;

import java.util.List;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.OrderItems;
import com.hexaware.quitq.exception.OrderItemNotFoundException;

public interface IOrderItemService {

	public OrderItems createOrderItem(OrderItems orderItem) throws OrderItemNotFoundException;
	
	public List<OrderItems> createOrderItemsFromCart(Cart cart);
}
