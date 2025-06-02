package com.hexaware.quitq.service.product;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.dto.ProductDTO;
import com.hexaware.quitq.entity.Category;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.repository.ProductRepository;
import com.hexaware.quitq.service.category.ICategoryService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ICategoryService categoryService;
	

	
	Logger logger = LoggerFactory.getLogger("ProductServiceImpl.class");
	
	@Override
	public Product createProduct(UserInfo user, ProductDTO dto) {
		
		Category topLevel = categoryService.createFirstLevels(dto.getTopLevelCategory());
		Category secondLevel = categoryService.createSecondLevels(dto.getSecondLevelCategory(), topLevel.getName());
		Category thirdLevel = categoryService.createThirdLevels(dto.getThirdLevelCategory(), secondLevel.getName());
		

		 Product product = new Product();
	        product.setTitle(dto.getTitle());
	        product.setColor(dto.getColor());
	        product.setDescription(dto.getDescription());
	        product.setDiscountedPrice(dto.getDiscountedPrice());
	        product.setDiscountedPercent(dto.getDiscountedPercent());
	        product.setImageUrl(dto.getImageUrl());
	        product.setBrand(dto.getBrand());
	        product.setPrice(dto.getPrice());
	        product.setSizes(dto.getSize());
	        product.setQuantity(dto.getQuantity());
	        product.setCategory(thirdLevel);
	        product.setUser(user);
	        product.setCreatedAt(LocalDateTime.now());
	        
	        Product savedProduct = productRepository.save(product);
	        
	        logger.info("Created product {}", savedProduct);
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		product.getSizes().clear();
		productRepository.deleteById(productId);
		
		logger.warn("Deleted product with product ID {}", productId);
		return "Product Deleted Successfully";
	}

	@Override
	public Product updateProductById(Long productId, ProductDTO productDTO) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		
		Category topLevel = categoryService.createFirstLevels(productDTO.getTopLevelCategory());
		Category secondLevel = categoryService.createSecondLevels(productDTO.getSecondLevelCategory(), topLevel.getName());
		Category thirdLevel = categoryService.createThirdLevels(productDTO.getThirdLevelCategory(), secondLevel.getName());
		
		
		product.setTitle(productDTO.getTitle());
        product.setColor(productDTO.getColor());
        product.setDescription(productDTO.getDescription());
        product.setDiscountedPrice(productDTO.getDiscountedPrice());
        product.setDiscountedPercent(productDTO.getDiscountedPercent());
        product.setImageUrl(productDTO.getImageUrl());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setSizes(productDTO.getSize());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
        
        logger.debug("Updated prodt with product ID {}, and updated product {}", productId, product);
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductNotFoundException {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		
		logger.info("Found product with product ID {} and product {}",productId, product);
		return product;
	}

	@Override
	public List<Product> findProductByCategory(String category) {

		logger.info("Found product with category {} ",category);
		return productRepository.getProductByCategory(category) ;
	}



	@Override
	public List<Product> findAllProducts() {
		logger.info("Found all products");
		return productRepository.findAll();
	}

}
