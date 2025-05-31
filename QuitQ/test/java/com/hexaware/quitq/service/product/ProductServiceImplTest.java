package com.hexaware.quitq.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.dto.ProductDTO;
import com.hexaware.quitq.entity.Product;
import com.hexaware.quitq.entity.Size;
import com.hexaware.quitq.entity.UserInfo;
import com.hexaware.quitq.exception.ProductNotFoundException;
import com.hexaware.quitq.exception.UserNotFoundException;
import com.hexaware.quitq.repository.CategoryRepository;
import com.hexaware.quitq.service.user.IUserService;

@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	IProductService productService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	IUserService userService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createProduct() throws UserNotFoundException {
		ProductDTO productDTO = new ProductDTO();
		
		String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCb2IiLCJpYXQiOjE3NDg1OTEwNDQsImV4cCI6MTc0ODU5Mjg0NH0.DLb4KEjR9Yx-hEfQ14ieJDz7q90gQHclABhaCKKFsLI";
		UserInfo user = userService.findUserProfileByJwt(jwt);
		
		Set<Size> size = new HashSet<>();
		size.add(new Size("S", 10));
		size.add(new Size("M", 20));
		size.add(new Size("L", 20));
		
		productDTO.setTitle("Skaters");
		productDTO.setDescription("Holiday wear for womens");
		productDTO.setBrand("Deor");
		productDTO.setColor("Pink");
		productDTO.setImageUrl("https://example.com/images/skaters.png");
		productDTO.setTopLevelCategory("Fashion");
		productDTO.setSecondLevelCategory("Women");
		productDTO.setThirdLevelCategory("Skaters");
		productDTO.setPrice(3000);
		productDTO.setDiscountedPrice(1000);
		productDTO.setDiscountedPercent(80);
		productDTO.setQuantity(20);
		productDTO.setSize(size);
		
		assertNotNull(productService.createProduct(user, productDTO));
	}

	@Test
	void findProductById() throws ProductNotFoundException {
		Long productId = 7L;
		Product product = productService.findProductById(productId);
		//checking exception
		assertNull(product);
	}
	
	@Test
	void findProductByCategory() {
		String category = "Jeans";
		List<Product> productList = productService.findProductByCategory(category);
		assertThat(productList).isNotEmpty();
	}
	
	@Test
	void findAllProducts() {
		List<Product> productList = productService.findAllProducts();
		assertThat(productList).isNotEmpty();
	}
	
}
