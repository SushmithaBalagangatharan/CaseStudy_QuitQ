package com.hexaware.quitq.entity;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PaymentInformation {
	
	private String cardHolderName;
	@Size(min=15, max=16, message="Card number must contain minimum of 15 digits")
	private String cardNumber;
	private LocalDateTime expirationDate;
	@NotNull
	private String cvv;
	
	public PaymentInformation() {
		super();
	}
	
	public PaymentInformation(String cardHolderName, String cardNumber, LocalDateTime expirationDate, String cvv) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
	
}
