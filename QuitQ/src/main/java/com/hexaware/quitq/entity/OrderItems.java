package com.hexaware.quitq.entity;

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
	
	private int quantity;
	private  Double price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	
	public OrderItems(){
		
	}
	
	public OrderItems(Long orderItemsId, int quantity, Double price, Orders order, Product product) {
		super();
		this.orderItemsId = orderItemsId;
		this.quantity = quantity;
		this.price = price;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	
	
	
}

