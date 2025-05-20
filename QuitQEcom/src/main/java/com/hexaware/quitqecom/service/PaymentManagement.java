package com.hexaware.quitqecom.service;

import java.util.Date;

import com.hexaware.quitqecom.component.Orders;

public interface PaymentManagement {
	void clearCartAfterPayment(int userid);
	Orders doPayment(Date paymentDate, String paymentMethod, String status, double amount);
}
