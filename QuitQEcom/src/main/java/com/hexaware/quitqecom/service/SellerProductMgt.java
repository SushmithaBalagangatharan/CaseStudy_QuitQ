package com.hexaware.quitqecom.service;

import java.util.List;

import com.hexaware.quitqecom.component.Orders;
import com.hexaware.quitqecom.component.Product;

public interface SellerProductMgt {
	Product addProduct(Product product, int sellerId);
	Product updateProduct(long productId, Product product);
	void markProductOutOfStock(long productId);
	List<Product> getSellerProducts(int sellerId);
	List<Orders> getSellerOrderHistory(int sellerId);
}
