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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.PaymentNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.payment.IPaymentService;

/*
 * @author Sushmitha B A
 * @description Payment ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	IPaymentService paymentService;
	
	Logger logger = LoggerFactory.getLogger("PaymentController.class");

	@PostMapping("/process")
	@PreAuthorize("hasAuthority('USER')")
	public  ResponseEntity<String> processPayment(@RequestBody PaymentDTO paymentDTO) 
			throws OrderNotFoundException, UserNotFoundException, PaymentNotFoundException {
	    logger.debug("Processing payment for DTO: {}", paymentDTO);
		paymentService.processPayment(paymentDTO);
		logger.info("Payment processed successfully");
		return new ResponseEntity<String>("Payment processed", HttpStatus.CREATED);
	}
	
	@GetMapping("/byid/{paymentId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Payment getPaymentById(@PathVariable Long paymentId) throws PaymentNotFoundException{
		logger.info("Fetching payment with payment ID: {}", paymentId);
		return paymentService.getPaymentById(paymentId);
	}
	
	@GetMapping("/getall/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Payment> getPaymentsByUserId(@PathVariable Long userId){
		logger.debug("Fetching payment with user ID: {}", userId);
		return paymentService.getPaymentsByUserId(userId);
	}
	
	@PutMapping("/update/{paymentId}/{status}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Payment updatePaymentStatus(@PathVariable Long paymentId, String status) throws PaymentNotFoundException {
		logger.debug("Fetching payment with payment ID: {} and with status: {}", paymentId);
		return paymentService.updatePaymentStatus(paymentId, status);
	}
	
	
	@PutMapping("/cancel/{paymentId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String cancelPayment(@PathVariable Long paymentId) throws PaymentNotFoundException {
		String message = paymentService.cancelPayment(paymentId);
		logger.warn("Cancel result: {}", message);
		return message;
	}
	
}

