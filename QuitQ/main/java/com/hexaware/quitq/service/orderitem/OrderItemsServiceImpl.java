package com.hexaware.quitq.service.orderitem;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.OrderItems;
import com.hexaware.quitq.exception.OrderItemNotFoundException;
import com.hexaware.quitq.repository.OrderItemRepository;


@Service
public class OrderItemsServiceImpl implements IOrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	Logger logger = LoggerFactory.getLogger("OrderItemsServiceImpl.class");

	@Override
	public OrderItems createOrderItem(OrderItems orderItem) throws OrderItemNotFoundException {
		logger.info("Creating order with order item {}", orderItem);
		orderItemRepository.findById(orderItem.getOrderItemsId()).orElseThrow(() -> new OrderItemNotFoundException());
		return orderItemRepository.save(orderItem);
	}

	
	public List<OrderItems> createOrderItemsFromCart(Cart cart){
		
		List<OrderItems> orderItemsList = new ArrayList<>();
		
		for(CartItems cartItem : cart.getCartItemsList()) {
	    	OrderItems orderItem = new OrderItems();
	    	orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantitiy());
            orderItem.setSize(cartItem.getSize());
            orderItem.setDiscountedPrice(cartItem.getDiscountPrice());
            
            OrderItems savedItem = orderItemRepository.save(orderItem);
            orderItemsList.add(savedItem);
	    }
		
		logger.info("Created order items from cart {}", orderItemsList);
		return orderItemsList;
		
	}
}
