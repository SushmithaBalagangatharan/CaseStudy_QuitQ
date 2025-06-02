package com.hexaware.quitq.service.payment;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.PaymentNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.OrderRepository;
import com.hexaware.quitq.repository.PaymentRepository;
import com.hexaware.quitq.service.order.IOrderService;

/*
 * @author Sushmitha B A
 * @description Payment Service class which contains methods performing CRUD operations on payment of the USER.
 * @date 2-06-2025
 * @version 1.0
 */

@Service
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
	IOrderService orderService;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	Logger logger = LoggerFactory.getLogger("PaymentServiceImpl.class");

	@Override
	public Payment processPayment(PaymentDTO paymentDTO) throws OrderNotFoundException, UserNotFoundException, PaymentNotFoundException {
		logger.info("Processing payment for order ID: {}", paymentDTO.getOrderId());
		
		 Orders order = orderService.findOrderById(paymentDTO.getOrderId());
		 UserInfo userInfo = order.getUser();
		 
		 Payment payment = order.getPayment();
		 if(payment == null) {
			 logger.error("Order not found for ID: {}", paymentDTO.getOrderId());
			 throw new PaymentNotFoundException();
		 }
		 
		 if("PAID".equalsIgnoreCase(payment.getStatus())) {
			 logger.error("No payment record found for order ID {}", paymentDTO.getOrderId());
			 throw new IllegalStateException("Payment is aldready completed for this order.");
		 }
				 
	        payment.setOrder(order);
	        payment.setUser(userInfo);
	        payment.setAmount(paymentDTO.getAmount());
	        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
	        payment.setStatus("PAID");
	        payment.setTransactionId("TRK"+System.currentTimeMillis());
	        payment.setPaymentDate(LocalDateTime.now());
	      
	        Payment savedPayment = paymentRepository.save(payment);
	        logger.info("Payment processed and saved with ID {}", savedPayment.getId());

	        
	        order.setPayment(savedPayment);
	        order.setOrderStatus("PLACED");
	     
	        orderRepository.save(order);
	        
	        return paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentById(Long paymentId) throws PaymentNotFoundException {
		return paymentRepository.findById(paymentId).orElseThrow(()-> new PaymentNotFoundException());
	}

	@Override
	public List<Payment> getPaymentsByUserId(Long userId) {
		return paymentRepository.findByUserId(userId);
	}

	@Override
	public Payment updatePaymentStatus(Long paymentId, String status) throws PaymentNotFoundException {
		Payment payment = getPaymentById(paymentId);
		payment.setStatus(status);
		 logger.info("Updated payment ID {} to status {}", paymentId, status);
		return payment;
	}

	@Override
	public String cancelPayment(Long paymentId) throws PaymentNotFoundException {
		Payment payment = getPaymentById(paymentId);
		
		if(!payment.getStatus().equals("PAID")) {
			payment.setStatus("CANCELED");
			logger.info("Payment with ID {} has been canceled", paymentId);
			return "Payment cannot canceled!";
		}
		
		 logger.warn("Attempt to cancel already PAID payment ID {}", paymentId);
		return "Payment cannot be canceled!";
	}



}
