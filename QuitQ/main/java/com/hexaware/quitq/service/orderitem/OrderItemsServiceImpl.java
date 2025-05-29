package com.hexaware.quitq.service.orderitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.CartItems;
import com.hexaware.quitq.entity.OrderItems;
import com.hexaware.quitq.repository.OrderItemRepository;


@Service
public class OrderItemsServiceImpl implements IOrderItemService {
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItems createOrderItem(OrderItems orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

	
	public List<OrderItems> createOrderItemsFromCart(Cart cart){
		
		//List<OrderItems> orderItems = new ArrayList<>();
		
		for(CartItems cartItem : cart.getCartItemsList()) {
	    	OrderItems orderItem = new OrderItems();
	    	orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantitiy());
            orderItem.setSize(cartItem.getSize());
            orderItem.setDiscountedPrice(cartItem.getDiscountPrice());
            
            orderItemRepository.save(orderItem);
            //orderItems.add(savedOrderItems);
	    }
		
		return orderItemRepository.findAll();
		
	}
}
