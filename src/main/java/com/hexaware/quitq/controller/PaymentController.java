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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.PaymentNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.payment.IPaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	IPaymentService paymentService;

	@PostMapping("/process")
	public  ResponseEntity<String> processPayment(@RequestBody PaymentDTO paymentDTO) throws OrderNotFoundException, UserNotFoundException {
		paymentService.processPayment(paymentDTO);
		
		return new ResponseEntity<String>("Payment processed", HttpStatus.CREATED);
	}
	
	@GetMapping("/byid/{paymentId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Payment getPaymentById(@PathVariable Long paymentId) throws PaymentNotFoundException{
		return paymentService.getPaymentById(paymentId);
	}
	
	@GetMapping("/getall/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Payment> getPaymentsByUserId(@PathVariable Long userId){
		return paymentService.getPaymentsByUserId(userId);
	}
	
	@PutMapping("/update/{paymentId}/{status}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Payment updatePaymentStatus(@PathVariable Long paymentId, String status) throws PaymentNotFoundException {
		return paymentService.updatePaymentStatus(paymentId, status);
	}
	
	@PutMapping("/cancel/{paymentId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String cancelPayment(@PathVariable Long paymentId) throws PaymentNotFoundException {
		String message = paymentService.cancelPayment(paymentId);
		return message;
	}
}

