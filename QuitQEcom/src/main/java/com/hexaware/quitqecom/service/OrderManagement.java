package com.hexaware.quitqecom.service;

import java.util.List;

import com.hexaware.quitqecom.component.Orders;

public interface OrderManagement {
	List<Orders> getUserOrders(int userId);
	Orders getOrderById(int orderId);
	Orders cancelOrder(int orderId);
}
