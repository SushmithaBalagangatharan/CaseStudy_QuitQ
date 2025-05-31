package com.hexaware.quitq.service.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.PaymentDTO;
import com.hexaware.quitq.exception.OrderNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;

@SpringBootTest
class PaymentServiceImplTest {
	@Autowired
	IPaymentService paymentService;

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
//
//	@Test
//	void processPayment() throws OrderNotFoundException, UserNotFoundException {
//		PaymentDTO paymentDTO = new PaymentDTO();
//		
//		paymentDTO.setOrderId(null);
//		paymentDTO.setUserId(null);
//		paymentDTO.setAmount(0);
//		paymentDTO.setPaymentMethod(null);
//		paymentDTO.setPaymentStatus(null);
//		paymentDTO.setTransactionId(null);
//		
//		assertNotNull(paymentService.processPayment(paymentDTO));
//	}
}
