package com.hexaware.quitq.service.payment;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.entity.Orders;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.PaymentNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.PaymentRepository;
import com.hexaware.quitq.service.order.IOrderService;
import com.hexaware.quitq.service.user.IUserService;

@Service
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
	IOrderService orderService;
	@Autowired
	IUserService userService;
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment processPayment(PaymentDTO paymentDTO) throws OrderNotFoundException, UserNotFoundException {
		 Payment payment = new Payment();
		 Orders order = orderService.findOrderById(paymentDTO.getOrderId());
		 UserInfo userInfo = userService.findUserById(paymentDTO.getUserId());
		 
	        payment.setOrder(order);
	        payment.setUser(userInfo);
	        payment.setAmount(paymentDTO.getAmount());
	        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
	        payment.setStatus("PENDING");
	        payment.setTransactionId(paymentDTO.getTransactionId());
	        payment.setPaymentDate(new Date());
		return null;
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
		return payment;
	}

	@Override
	public String cancelPayment(Long paymentId) throws PaymentNotFoundException {
		Payment payment = getPaymentById(paymentId);
		
		if(!payment.getStatus().equals("PAID")) {
			payment.setStatus("CANCELED");
			return "Payment cannot canceled!";
		}
		return "Payment cannot be canceled!";
	}



}
