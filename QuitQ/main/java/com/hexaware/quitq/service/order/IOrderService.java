package com.hexaware.quitq.service.order;


import java.util.List;

import com.hexaware.quitq.dto.OrderDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IOrderService {
	
	OrderDTO createOrder (UserInfo user, Address shippingAddress) throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException;
	
	Orders findOrderById(Long orderId) throws OrderNotFoundException;
	
	List<Orders> userOrderHistory(Long userId);
	
	List<Orders> findOrdersByUserId(Long userId);
		
	
	Orders placedOrder(Long orderId) throws OrderNotFoundException; // to place order
	
	Orders confirmedOrder(Long orderId) throws OrderNotFoundException; // to confirm order
	
	Orders shippedOrder(Long orderId) throws OrderNotFoundException; // to ship order
	
	Orders deliveredOrder(Long orderId) throws OrderNotFoundException; // to set order status to "delivered"
	
	Orders canceledOrder(Long orderId) throws OrderNotFoundException; // to set order status to "canceled"
	
	List<OrderDTO> getAllOrders();
	
	void deleteOrder(Long orderId) throws OrderNotFoundException;

	OrderDTO createOrderUser(UserInfo user) throws CartNotFoundException, CartItemNotFoundException;
}
