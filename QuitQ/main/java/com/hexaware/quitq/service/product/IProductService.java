package com.hexaware.quitq.service.product;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hexaware.quitq.dto.ProductDTO;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;

public interface IProductService {
	
	//product req
	Product createProduct(UserInfo user, ProductDTO dto);

	String deleteProduct(Long productId) throws ProductNotFoundException;
	
	Product updateProductById(Long productId, ProductDTO product) throws ProductNotFoundException;
	
	Product findProductById(Long productId) throws ProductNotFoundException;
	
	List<Product> findProductByCategory(String category);
	

	List<Product> findAllProducts();
}
