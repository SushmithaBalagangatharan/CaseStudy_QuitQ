package com.hexaware.quitq.dto;

public class PaymentDTO {
	private Long orderId;
    private Long userId;
    private String paymentMethod; // e.g., "COD", "RAZORPAY", "STRIPE"
    private double amount;
    private String currency;
    private String transactionId; // for online gateways
    private String paymentStatus; // "PENDING", "SUCCESS", etc.
	
    public PaymentDTO() {
		super();
	}
    
    public PaymentDTO(Long orderId, Long userId, String paymentMethod, double amount, String currency,
			String transactionId, String paymentStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.currency = currency;
		this.transactionId = transactionId;
		this.paymentStatus = paymentStatus;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
    
    
    
}
