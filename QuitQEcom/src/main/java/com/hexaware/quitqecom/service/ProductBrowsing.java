package com.hexaware.quitqecom.service;

import java.util.List;

import com.hexaware.quitqecom.component.Product;

public interface ProductBrowsing {
	
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> searchProducts(String keyword);
	List<Product> filterProducts(String brand, double minPrice, double maxPrice);
	Product getProductDetailsById(Long productId);

}
