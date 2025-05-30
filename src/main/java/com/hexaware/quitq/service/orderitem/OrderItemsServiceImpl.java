package com.hexaware.quitq.service.orderitem;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public OrderItems createOrderItem(OrderItems orderItem) throws OrderItemNotFoundException {
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
		
		return orderItemsList;
		
	}
}
