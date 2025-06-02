package com.hexaware.quitq.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.AddressDTO;
import com.hexaware.quitq.dto.OrderDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.Address.IAddressService;
import com.hexaware.quitq.service.order.IOrderService;
import com.hexaware.quitq.service.user.IUserService;

/*
 * @author Sushmitha B A
 * @description Order ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	IOrderService orderService;
	@Autowired
	IUserService userService;
	@Autowired
	IAddressService addressService;
	
	Logger logger = LoggerFactory.getLogger("OrderController.class");
	
	@PostMapping("/create/address")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<OrderDTO> createOrder( @RequestBody AddressDTO shippingAddress, @RequestHeader("Authorization") String jwt)
									throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException{
		logger.debug("AddressDTO: {}", shippingAddress);
		Address address = addressService.createAddress(shippingAddress, jwt);
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));
		OrderDTO order = orderService.createOrder(userInfo, address);
		logger.info("Order created successfully for user: ", userInfo.getId());
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@PostMapping("/create/user")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<OrderDTO> createOrderUser(@RequestHeader("Authorization") String jwt)
								  throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));
		OrderDTO order = orderService.createOrderUser(userInfo);
		logger.info("Order created for user ID: "+ userInfo.getId());
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/findbyid/{orderId}")
	public ResponseEntity<Orders> findOrderById(@PathVariable Long orderId) throws OrderNotFoundException {
		Orders order = orderService.findOrderById(orderId);
		logger.info("Order found: "+ order.getId());
		return new ResponseEntity<Orders>(order, HttpStatus.FOUND);
	}
	
	@GetMapping("/getall")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> orderList = orderService.getAllOrders();
		logger.info("Total orders retrieved: ", orderList.size());
		return new ResponseEntity<List<OrderDTO>>(orderList, HttpStatus.FOUND);
	}
	
	@PostMapping("/placed/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Orders> placedOrder(@PathVariable Long orderId) throws OrderNotFoundException{
		Orders order = orderService.placedOrder(orderId);
		logger.info("Order placed successfully for ID: "+ orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@GetMapping("/userhistory/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public  ResponseEntity<List<Orders> > userOrderHistory(@PathVariable Long userId) {
		List<Orders> orderList =  orderService.userOrderHistory(userId);
		logger.info("Order history retrieved for user: "+userId+"Total: {}"+orderList.size());
		return new ResponseEntity<List<Orders>>(orderList, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Orders>> findOrdersBySellerId(@PathVariable Long userId){
		List<Orders> orderList =  orderService.findOrdersByUserId(userId);
		  logger.info("Orders found for user ID {}: {}", userId, orderList.size());
		return new ResponseEntity<List<Orders>>(orderList, HttpStatus.OK);
		
	}
	
	@PutMapping("/confirmed/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> confirmedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.confirmedOrder(orderId);
		logger.info("Order confirmed for ID: {}", orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/shipped/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public  ResponseEntity<Orders> shippedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.shippedOrder(orderId);
		logger.info("Order shipped for ID: {}", orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/delivered/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> deliveredOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.deliveredOrder(orderId);
		logger.info("Order delivered for ID: {}", orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/canceled/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> canceledOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.canceledOrder(orderId);
		logger.warn("Order canceled for ID: {}", orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/deleted/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteOrder(Long orderId) throws OrderNotFoundException {
		orderService.deleteOrder(orderId);
		logger.warn("Order deleted with ID: {}", orderId);
		return new ResponseEntity<String>("Deleted order successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public String getOrder() {
		logger.info("Testing purpose");
		return "got order";
	}

}
