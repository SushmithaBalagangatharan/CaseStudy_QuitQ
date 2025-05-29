package com.hexaware.quitq.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name = "order_id", unique = true, nullable = false)
    private String orderId;
	
	private LocalDateTime createdAt;
	
	@Pattern(regexp = "CANCEL|DELIVERED|PENDING|PLACED|SHIPPED")
	private String orderStatus;
	
	private Integer totalPrice;	
	
	@Column(name="tracking_id")
	private String trackingId;
	
	private Integer totalItem;
	
	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	private Integer discount;
	
	@NotNull
	@OneToOne
	private Address shippingAddress;
	
	@Column(name="delivey_date")
	private LocalDateTime deliveryDate;
	
	@Column(name="total_discounted_price")
	private Integer totalDiscountedPrice;
	
	@NotNull
	@ManyToOne
	private UserInfo user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<OrderItems> orderItems = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "payment_id")
	private Payment payment = new Payment();
	
	
	public Orders() {
		
	}


	public Orders(Long id, String orderId, LocalDateTime createdAt, String orderStatus, Integer totalPrice,
			String trackingId, Integer totalItem, LocalDateTime orderDate, Integer discount,
			@NotNull Address shippingAddress, LocalDateTime deliveryDate, Integer totalDiscountedPrice,
			@NotNull UserInfo user, List<OrderItems> orderItems, Payment payment) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.createdAt = createdAt;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.trackingId = trackingId;
		this.totalItem = totalItem;
		this.orderDate = orderDate;
		this.discount = discount;
		this.shippingAddress = shippingAddress;
		this.deliveryDate = deliveryDate;
		this.totalDiscountedPrice = totalDiscountedPrice;
		this.user = user;
		this.orderItems = orderItems;
		this.payment = payment;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Integer getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Integer integer) {
		this.totalPrice = integer;
	}


	public String getTrackingId() {
		return trackingId;
	}


	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}


	public Integer getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}


	public LocalDateTime getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}


	public Integer getDiscount() {
		return discount;
	}


	public void setDiscount(Integer discount) {
		this.discount = discount;
	}


	public Address getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public Integer getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}


	public void setTotalDiscountedPrice(Integer totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}


	public UserInfo getUser() {
		return user;
	}


	public void setUser(UserInfo user) {
		this.user = user;
	}


	public List<OrderItems> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

	
	
}
