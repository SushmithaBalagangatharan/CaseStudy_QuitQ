package com.hexaware.quitq.service.product;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.Size;

public interface IProductService {
	
	Product createProduct(String title, String descritpion, String brand, String color, String imageUrl, 
			String topLevelCategory, String secondLevelCategory, String thirdLevelCategory, int price,
			int discountedPrice, int discountedPercent, int quantity, Set<Size> size);

	String deleteProduct(Long productId);
	
	Product updateProductById(Long productId);
	
	Product findProductById(Long productId);
	
	List<Product> findProductByCategory(String category);
	
	Page<Product> getAllProducts(String category, List<String> colors, List<String> size, Integer minPrice, Integer maxPrice,
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	

	List<Product> findAllProducts();
}
