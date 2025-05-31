package com.hexaware.quitq.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime paymentDate;
	
	@NotBlank
	private String paymentMethod;
	
	@NotBlank
	@Pattern(regexp="PAID|PENDING|CANCELED|FAILED")
	private String status; //paid, failed, pending
	
	@Column(nullable = false)
	private double amount;
	private String transactionId;
	
//	private String cardHolderName;
//	
//	@Size(min=15, max=16, message="Card number must contain minimum of 15 digits")
//	private String cardNumber;
//	
//	private LocalDateTime expirationDate;
//	
//	@NotNull
//	private String cvv;
	
	
	@OneToOne(mappedBy = "payment")
	@JsonBackReference
	private Orders order;
	
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private UserInfo user;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

//	public String getCardHolderName() {
//		return cardHolderName;
//	}
//
//	public void setCardHolderName(String cardHolderName) {
//		this.cardHolderName = cardHolderName;
//	}
//
//	public String getCardNumber() {
//		return cardNumber;
//	}
//
//	public void setCardNumber(String cardNumber) {
//		this.cardNumber = cardNumber;
//	}
//
//	public LocalDateTime getExpirationDate() {
//		return expirationDate;
//	}
//
//	public void setExpirationDate(LocalDateTime expirationDate) {
//		this.expirationDate = expirationDate;
//	}
//
//	public String getCvv() {
//		return cvv;
//	}
//
//	public void setCvv(String cvv) {
//		this.cvv = cvv;
//	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}

