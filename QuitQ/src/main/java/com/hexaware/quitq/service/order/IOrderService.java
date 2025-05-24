package com.hexaware.quitq.service.order;


import java.util.List;

import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.User;

public interface IOrderService {
	
	Orders createOrder (User user, Address shippingAddress);
	
	Orders findOrderById(Long orderId);
	
	List<Orders> userOrderHistory(Long userId);
	
	Orders placedOrder(Long orderId); // to place order
	
	Orders confirmedOrder(Long orderId); // to confirm order
	
	Orders shippedOrder(Long orderId); // to ship order
	
	Orders deliveredOrder(Long orderId); // to set order status to "delivered"
	
	Orders canceledOrder(Long orderId); // to set order status to "canceled"
	
	List<Orders> getAllOrders();
	
	void deleteOrder(Long orderId);
}
