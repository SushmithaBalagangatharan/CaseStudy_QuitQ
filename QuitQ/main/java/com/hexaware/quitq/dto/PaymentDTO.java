package com.hexaware.quitq.dto;

public class PaymentDTO {
	private Long orderId;
    private String paymentMethod; 
    private double amount;
    private String currency;
    private String transactionId; 

	
    public PaymentDTO() {
		super();
	}
    
    public PaymentDTO(Long orderId, String paymentMethod, double amount, String currency,
			String transactionId) {
		super();
		this.orderId = orderId;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.currency = currency;
		this.transactionId = transactionId;
		
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

    
    
}
