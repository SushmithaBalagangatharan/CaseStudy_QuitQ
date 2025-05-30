package com.hexaware.quitq.service.order;

import java.time.LocalDateTime;
import java.util.List;

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
	
	@Transactional(rollbackFor = Exception.class)
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
		    
			return getOrderDTO(savedOrder);
	}

	@Override
	public OrderDTO createOrder(UserInfo user, Address shippingAddress) throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
		//UserInfo user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		//shippingAddress.setUser(user);
		//Address address = addressRepository.save(shippingAddress);
		
		//user.setAddress(shippingAddress); // cascade all will apply
		//UserInfo savedUser = userRepository.save(user);
		
		
		Cart cart = cartService.findUserCart(user.getId());
	//	List<OrderItems> orderItems = new ArrayList<>();
		
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
	    
		return getOrderDTO(savedOrder);
	}

	@Override
	public Orders findOrderById(Long orderId) throws OrderNotFoundException {
		
		return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException());
	}
	
	@Override
	public List<Orders> findOrdersBySellerId(Long sellerId){
		return orderRepository.findOrdersBySellerId(sellerId);
	}

	@Override
	public List<Orders> userOrderHistory(Long userId) {
		
		return orderRepository.findUserOrders(userId);
	}

	@Override
	public Orders placedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("PLACED");
		order.getPayment().setStatus("PAID");
		return order;
	}

	@Override
	public Orders confirmedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Orders shippedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Orders deliveredOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Orders canceledOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException());
		order.setOrderStatus("CANCEL");
		return orderRepository.save(order);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException());
		orderRepository.delete(order);
		
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

//    for(CartItems cartItem : cart.getCartItemsList()) {
//	OrderItems orderItem = new OrderItems();
//	orderItem.setPrice(cartItem.getPrice());
//    orderItem.setProduct(cartItem.getProduct());
//    orderItem.setQuantity(cartItem.getQuantitiy());
//    orderItem.setSize(cartItem.getSize());
//    orderItem.setDiscountedPrice(cartItem.getDiscountPrice());
//    
//    OrderItems savedOrderItems = orderItemRepository.save(orderItem);
//    orderItems.add(savedOrderItems);
//}
	
	
//	orderId → a unique, human-readable, business-specific ID that is:
//		shown in the UI
//		included in invoices or confirmation emails
//		used by customers or support staff to track or reference orders
}
