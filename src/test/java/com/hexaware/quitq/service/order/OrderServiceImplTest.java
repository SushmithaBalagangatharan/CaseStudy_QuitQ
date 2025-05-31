package com.hexaware.quitq.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.OrderDTO;
import com.hexaware.quitq.entity.Address;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.CartItemNotFoundException;
import com.hexaware.quitq.exception.CartNotFoundException;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.user.IUserService;

@SpringBootTest
class OrderServiceImplTest {

	@Autowired
	IUserService userService;
	@Autowired
	IOrderService orderService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createOrderUser() throws CartNotFoundException, CartItemNotFoundException, UserNotFoundException {
		String jwt = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdXJhIiwiaWF0IjoxNzQ4NjA5NjIxLCJleHAiOjE3NDg2MTE0MjF9.I41sZHfEpLCvj-eEXBBGuPlZQRfcnm9IvTT8vmvp400";
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));
		OrderDTO orderDTO = orderService.createOrderUser(userInfo);
		
		assertNotNull(orderDTO);
	}

	@Test
	void createOrder() throws CartNotFoundException, UserNotFoundException, CartItemNotFoundException {
		String jwt = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJvbGQiLCJpYXQiOjE3NDg2MTE1ODgsImV4cCI6MTc0ODYxMzM4OH0.V2O67pcJoKlPkQYqtgl-aEEtoRY0nKkQ56rdpe3OVYs";
		UserInfo userInfo = userService.findUserProfileByJwt(jwt.substring(7));

		Address shippingAddress  = new Address();
		
		shippingAddress.setFirstName("Harold");
		shippingAddress.setLastName("Kem");
		shippingAddress.setStreetAddress("palace 708-09");
		shippingAddress.setCity("Los Angels");
		shippingAddress.setContactNumber("9567890876");
		shippingAddress.setState("California");
		shippingAddress.setZipCode("567890-98");
		
		OrderDTO orderDTO = orderService.createOrder(userInfo, shippingAddress);
		
		assertNotNull(orderDTO);
	}

	@Test
	void findOrderById() throws OrderNotFoundException {
		Long orderId = 2L;
		
		Orders order = orderService.findOrderById(orderId);
		
		assertNotNull(order);
		assertEquals(order.getId(), 2);
	}
	
	@Test
	void findOrdersByUserId(){
		Long userId = 102L;
		
		List<Orders> order = orderService.findOrdersByUserId(userId);
		
		assertNotNull(order);
		assertThat(order).size();
	}
	
	@Test
	void userOrderHistory() {
		Long userId = 102L;
		
		List<Orders> order = orderService.userOrderHistory(userId);
		
		assertNotNull(order);
		assertThat(order).size();
	}
	
	@Test
	void placedOrder() throws OrderNotFoundException {
		Long orderId  = 102L;
		Orders order = orderService.placedOrder(orderId);
		assertNotNull(order);
		assertEquals("PAID", order.getPayment().getStatus());
	}
	
		   //The user has submitted the order successfully on the website/app. - PLACED
	@Test //The order has been reviewed and accepted by the seller or system. - CONFIRMED
	void confirmedOrder() throws OrderNotFoundException {
		Long orderId  = 202L;
		Orders order = orderService.confirmedOrder(orderId);
		
		assertNotNull(order);
		assertEquals("CONFIRMED", order.getOrderStatus());
	}
	
	
	@Test
	void shippedOrder() throws OrderNotFoundException {
		Long orderId  = 202L;
		Orders order = orderService.shippedOrder(orderId);
		
		assertNotNull(order);
		assertEquals("SHIPPED", order.getOrderStatus());
	}
	
	@Test
	void deliveredOrder() throws OrderNotFoundException {
		Long orderId  = 202L;
		Orders order = orderService.deliveredOrder(orderId);
		
		assertNotNull(order);
		assertEquals("DELIVERED", order.getOrderStatus());
	}
	
	@Test
	void canceledOrder() throws OrderNotFoundException {
		Long orderId  = 202L;
		Orders order = orderService.canceledOrder(orderId);
		
		assertNotNull(order);
		assertEquals("CANCEL", order.getOrderStatus());
	}
	
	@Test
	void getAllOrders() {
		List<OrderDTO> orderList = orderService.getAllOrders();
		
		assertThat(orderList).isNotEmpty();
	}
	
	@Test // could not delete order because of foreign key constraint
	 void deleteOrder() throws OrderNotFoundException {
		Long orderId  = 202L;
		orderService.deleteOrder(orderId);
		
		assertThrows(OrderNotFoundException.class, () -> orderService.findOrderById(orderId));
	}
	
	
	
}

	
	
	
	
	