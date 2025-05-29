package com.hexaware.quitq.service.payment;

import java.util.List;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.entity.Payment;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.PaymentNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

public interface IPaymentService {
	Payment processPayment(PaymentDTO paymentRequest) throws OrderNotFoundException, UserNotFoundException;

    Payment getPaymentById(Long paymentId) throws PaymentNotFoundException;

    List<Payment> getPaymentsByUserId(Long userId);

    Payment updatePaymentStatus(Long paymentId,String status) throws PaymentNotFoundException;

    String cancelPayment(Long paymentId) throws PaymentNotFoundException;

  
}
