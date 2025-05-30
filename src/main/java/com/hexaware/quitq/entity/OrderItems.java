package com.hexaware.quitq.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="order_items")
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long orderItemsId;
	
	private String size;
    private int quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userId;
    private LocalDateTime deliveryDate;
    
    
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "order_id")
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	
	public OrderItems(){
		
	}
	

	public OrderItems(Long orderItemsId, String size, int quantity, Integer price, Integer discountedPrice, Long userId,
			LocalDateTime deliveryDate, Orders order, Product product) {
		super();
		this.orderItemsId = orderItemsId;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.userId = userId;
		this.deliveryDate = deliveryDate;
		this.order = order;
		this.product = product;
	}



	public Long getOrderItemsId() {
		return orderItemsId;
	}

	public void setOrderItemsId(Long orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer integer) {
		this.price = integer;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Integer discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	
	
	
}

