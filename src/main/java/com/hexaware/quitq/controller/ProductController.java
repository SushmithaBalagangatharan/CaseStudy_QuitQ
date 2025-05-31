package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.dto.ProductDTO;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.service.product.IProductService;
import com.hexaware.quitq.service.user.IUserService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	IProductService productService;
	@Autowired
	IUserService userService;
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/create")
	@PreAuthorize("hasAuthority('SELLER')")
	public ResponseEntity<Product> createProduct(@RequestHeader("Authorization") String jwt, @RequestBody ProductDTO dto) throws UserNotFoundException {
		UserInfo user = userService.findUserProfileByJwt(jwt.substring(7));
		logger.debug("Creating product for user: {}", user.getId());
		Product product = productService.createProduct(user, dto);
		logger.info("Product created successfully: {}", product.getId());
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{productId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
		logger.info("Deleting product with product Id: {}", productId);
		return productService.deleteProduct(productId);
	}
	
	//verify that seller only deletes the product
	@PutMapping("/update/{productId}")
	@PreAuthorize("hasAnyAuthority('ADMIN','SELLER')")
	public ResponseEntity<Product> updateProductById(@PathVariable Long productId, @RequestBody ProductDTO productDTO) throws ProductNotFoundException {
		logger.debug("Update details: {}", productDTO);
		Product product = productService.updateProductById(productId, productDTO);
		logger.info("Product updated: {}", product.getId());
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getbycategory/{category}")
	public List<Product> findProductByCategory(@PathVariable String category) {
		logger.info("Finding product by category {}",category);
		return productService.findProductByCategory(category);
	}

	@GetMapping("/getall")
	public List<Product> findAllProducts() {
		logger.info("Sinding all products");
		return productService.findAllProducts();
	}
	
	@GetMapping("/getbyid/{productId}")
	public Product findProductById(@PathVariable Long productId) throws ProductNotFoundException {
		logger.info("Finding product with id: {}", productId);
		return productService.findProductById(productId);
	}
}
