package com.hexaware.quitq.service.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.quitq.entity.Category;

@SpringBootTest
class CategoryServiceImplTest {
	
	@Autowired
	ICategoryService categoryService;

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
	void createFirstLevels() { 
		String topLevelName = "Toys";
		Category category = categoryService.createFirstLevels(topLevelName);
		assertEquals(1,category.getLevel());
	}
	
	@Test
	void createSecondLevels() { 
		String topLevelName = "Toys";
		String secondLevelName = "In-door";
		Category category = categoryService.createSecondLevels(secondLevelName, topLevelName);
		assertEquals(2,category.getLevel());
	}

	@Test
	void createThirdLevels() { 
		String secondLevelName = "Toys";
		String thirdLevelName = "Building-Blocks";
		Category category = categoryService.createSecondLevels(thirdLevelName, secondLevelName);
		assertEquals(2,category.getLevel());
	}
}
