package com.hexaware.quitq.service.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexaware.quitq.dto.OrderDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.Cart;
import com.hexaware.quitq.entity.OrderItems;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.AddressRepository;
import com.hexaware.quitq.repository.CartRepository;
import com.hexaware.quitq.repository.OrderItemRepository;
import com.hexaware.quitq.repository.OrderRepository;
import com.hexaware.quitq.repository.UserRepository;
import com.hexaware.quitq.service.cart.ICartService;
import com.hexaware.quitq.service.orderitem.IOrderItemService; 

/*
 * @author Sushmitha B A
 * @description Order Service class which contains methods performing CRUD operations on order of the USER.
 * @date 2-06-2025
 * @version 1.0
 */


@Transactional
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	ICartService cartService;
	@Autowired
	IOrderItemService orderItemService;
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	
	
	Logger logger = LoggerFactory.getLogger("OrderServiceImpl.class");
//	@Transactional(rollbackFor = Exception.class)
	@Override
	public OrderDTO createOrderUser(UserInfo user) throws CartNotFoundException, CartItemNotFoundException {
			Cart cart = cartService.findUserCart(user.getId());
			
			LocalDateTime now = LocalDateTime.now();
			
			Orders createdOrder = new Orders();
			createdOrder.setUser(user);
		    createdOrder.setTotalPrice(cart.getTotalPrice());
		    createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountPrice());
		    createdOrder.setDiscount(cart.getDiscount());
		    createdOrder.setTotalItem(cart.getTotalItem());
		    createdOrder.setShippingAddress(user.getAddress());
		    createdOrder.setOrderDate(now);
		    createdOrder.setOrderStatus("PENDING");
		    createdOrder.setCreatedAt(now);
		    createdOrder.setTrackingId("TRK"+System.currentTimeMillis());
		    createdOrder.setDeliveryDate(now.plusDays(3));
		    
		    String generatedOrderId = "ORD-"+ System.currentTimeMillis();
		    createdOrder.setOrderId(generatedOrderId);
		    
		    Payment payment = new Payment();
		    payment.setStatus("PENDING");
		    payment.setUser(user);
		    payment.setPaymentMethod("CASH");
		    
		    createdOrder.setPayment(payment);	
		    
		    Orders savedOrder = orderRepository.save(createdOrder);
		    
		    List<OrderItems> orderItems = orderItemService.createOrderItemsFromCart(cart);
		    
		    for (OrderItems item : orderItems) {
		        item.setOrder(savedOrder);
		    }
		    
		    orderItems = orderItemRepository.saveAll(orderItems);
		    	    
		    
		    savedOrder.setOrderItems(orderItems);
		    savedOrder = orderRepository.save(savedOrder);
		    
		    cart.getCartItemsList().clear();
		    cartRepository.save(cart);
		    
		    logger.info("Created and saved order with order ID {}", savedOrder.getId());
			return getOrderDTO(savedOrder);
	}

	//accepts new address , not saved in address table
	@Override
	public OrderDTO createOrder(UserInfo user, Address shippingAddress) throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
				
		userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException());
		
		Cart cart = cartService.findUserCart(user.getId());
		
		addressRepository.save(shippingAddress);
		
		LocalDateTime now = LocalDateTime.now();
		
		Orders createdOrder = new Orders();
		createdOrder.setUser(user);
	    createdOrder.setTotalPrice(cart.getTotalPrice());
	    createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountPrice());
	    createdOrder.setDiscount(cart.getDiscount());
	    createdOrder.setTotalItem(cart.getTotalItem());
	    createdOrder.setShippingAddress(shippingAddress);
	    createdOrder.setOrderDate(now);
	    createdOrder.setOrderStatus("PENDING");
	    createdOrder.setCreatedAt(now);
	    createdOrder.setTrackingId("TRK"+System.currentTimeMillis());
	    createdOrder.setDeliveryDate(now.plusDays(3));
	    
	    String generatedOrderId = "ORD-"+ System.currentTimeMillis();
	    createdOrder.setOrderId(generatedOrderId);
	    
	    Payment payment = new Payment();
	    payment.setStatus("PENDING");
	    payment.setUser(user);
	    payment.setPaymentMethod("CASH");
	    createdOrder.setPayment(payment);	
	    
	    Orders savedOrder = orderRepository.save(createdOrder);
	    
	    List<OrderItems> orderItems = orderItemService.createOrderItemsFromCart(cart);
	    
	    for (OrderItems item : orderItems) {
	        item.setOrder(savedOrder);
	    }
	    
	    orderItems = orderItemRepository.saveAll(orderItems);
	    	    
	    
	    savedOrder.setOrderItems(orderItems);
	    savedOrder = orderRepository.save(savedOrder);
	    
	    cart.getCartItemsList().clear();
	    cartRepository.save(cart);
	    
	    logger.info("Created and saved order with order ID {} and shipping address {}", savedOrder.getId(), shippingAddress);
		return getOrderDTO(savedOrder);
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderNotFoundException {
		
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException());
		
		logger.info("Found order by id {}", order);
		return order;
	}
	
	@Override
	public List<Orders> findOrdersByUserId(Long userId){
		logger.info("Found order by user id {}", userId);
		return orderRepository.findOrdersBySellerId(userId);
	}

	@Override
	public List<Orders> userOrderHistory(Long userId) {
		logger.info("Found user order history with user ID {}", userId);
		return orderRepository.findUserOrders(userId);
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("PLACED");
		order.getPayment().setStatus("PAID");
		
		logger.info("Placed order with order ID {}", orderId);
		return orderRepository.save(order);
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("CONFIRMED");
		
		logger.info("Confirmed order with order ID {}", orderId);
		return orderRepository.save(order);
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("SHIPPED");
		
		logger.info("Shipped order with order ID {}", orderId);
		return orderRepository.save(order);
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("DELIVERED");
		
		logger.info("Delivered order with order ID {}", orderId);
		return orderRepository.save(order);
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("CANCEL");
		
		logger.info("Canceled order with order ID {}", orderId);
		return orderRepository.save(order);
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<Orders> orderList = orderRepository.findAll();
		List<OrderDTO> orderDTOList = new ArrayList<>();
		for(Orders order : orderList) {
			orderDTOList.add(getOrderDTO(order));
		}
		
		logger.info("Fetching all orders");
		return orderDTOList;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException());
		orderRepository.delete(order);
		logger.info("Deleted order with order ID {}", orderId);
	}
	
	public OrderDTO getOrderDTO(Orders order) {
		OrderDTO orderDTO = new OrderDTO();
		
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setCreatedAt(order.getCreatedAt());
		orderDTO.setOrderStatus(order.getOrderStatus());
		orderDTO.setTotalPrice(order.getTotalPrice());
		orderDTO.setTrackingId(order.getTrackingId());
		orderDTO.setTotalItem(order.getTotalItem());
		
		return orderDTO;
	}

}
