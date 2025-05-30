package com.hexaware.quitq.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	IOrderService orderService;
	@Autowired
	IUserService userService;
	@Autowired
	IAddressService addressService;
	
	@PostMapping("/create/address")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<OrderDTO> createOrder( @RequestBody AddressDTO shippingAddress, @RequestHeader("Authorization") String jwt)
									throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException{
		Address address = addressService.createAddress(shippingAddress, jwt);
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));
		OrderDTO order = orderService.createOrder(userInfo, address);
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@PostMapping("/create/user")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<OrderDTO> createOrderUser(@RequestHeader("Authorization") String jwt)
								  throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));
		OrderDTO order = orderService.createOrderUser(userInfo);
		return new ResponseEntity<OrderDTO>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/findbyid/{orderId}")
	public ResponseEntity<Orders> findOrderById(@PathVariable Long orderId) throws OrderNotFoundException {
		Orders order = orderService.findOrderById(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.FOUND);
	}
	
	@GetMapping("/getall")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orderList = orderService.getAllOrders();
		return new ResponseEntity<List<Orders>>(orderList, HttpStatus.FOUND);
	}
	
	@PostMapping("/placed/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Orders> placedOrder(@PathVariable Long orderId) throws OrderNotFoundException{
		Orders order = orderService.placedOrder(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@GetMapping("/userhistory/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public  ResponseEntity<List<Orders> > userOrderHistory(@PathVariable Long userId) {
		List<Orders> orderList =  orderService.userOrderHistory(userId);
		return new ResponseEntity<List<Orders>>(orderList, HttpStatus.OK);
	}
	
	@GetMapping("/seller/{sellerId}")
	public ResponseEntity<List<Orders>> findOrdersBySellerId(@PathVariable Long sellerId){
		List<Orders> orderList =  orderService.findOrdersBySellerId(sellerId);
		return new ResponseEntity<List<Orders>>(orderList, HttpStatus.OK);
		
	}
	
	@PutMapping("/confirmed/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> confirmedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.confirmedOrder(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/shipped/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public  ResponseEntity<Orders> shippedOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.shippedOrder(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/delivered/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> deliveredOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.deliveredOrder(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/canceled/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Orders> canceledOrder(Long orderId) throws OrderNotFoundException {
		Orders order = orderService.canceledOrder(orderId);
		return new ResponseEntity<Orders>(order, HttpStatus.OK);
	}
	
	@PutMapping("/deleted/{orderId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteOrder(Long orderId) throws OrderNotFoundException {
		orderService.deleteOrder(orderId);
		return new ResponseEntity<String>("Deleted order successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public String getOrder() {
		return "got order";
	}

}
