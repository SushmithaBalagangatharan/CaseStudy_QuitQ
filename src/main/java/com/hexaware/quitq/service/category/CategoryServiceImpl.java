package com.hexaware.quitq.service.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.Category;
import com.hexaware.quitq.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	Logger logger = LoggerFactory.getLogger("CategoryServiceImpl.class");
			
	@Override
	public Category createFirstLevels(String topLevelName) {
		logger.info("Creating top-level category with name: {}", topLevelName);

		Category topLevel = categoryRepository.findByName(topLevelName);
		if(topLevel == null) {			
			topLevel = new Category();
			topLevel.setName(topLevelName);;
			topLevel.setLevel(1);
			topLevel.setDescription("Parent level");
			topLevel = categoryRepository.save(topLevel);
			
	        logger.info("Top-level category '{}' created with ID {}", topLevelName, topLevel.getCategoryId());
		}
		
		return topLevel;
	}

	@Override
	public Category createSecondLevels(String secondLevelName, String topLevelName) {
		 logger.info("Creating second-level category '{}' under top-level '{}'", secondLevelName, topLevelName);
		
		Category topLevel = categoryRepository.findByName(topLevelName);
	    if (topLevel == null) {
	    	logger.error("Top-level category '{}' not found", topLevelName);
	    	throw new IllegalArgumentException("Top level category not found: " + topLevelName);
	    }

	    Category secondLevel = categoryRepository.findByNameAndParent(secondLevelName, topLevel.getCategoryId());
	   	    
		if(secondLevel == null) {
			secondLevel = new Category();
			secondLevel.setName(secondLevelName);
			secondLevel.setLevel(2);
			secondLevel.setDescription("Second child level");
			secondLevel.setParentCategory(topLevel);
			secondLevel = categoryRepository.save(secondLevel);
			
			logger.info("Second-level category '{}' created with ID {}", secondLevelName, secondLevel.getCategoryId());
		}
				
		return secondLevel;
	}

	@Override
	public Category createThirdLevels(String thirdLevelName, String secondLevelName) {
		logger.info("Creating third-level category '{}' under second-level '{}'", thirdLevelName, secondLevelName);
		
		if (thirdLevelName == null || secondLevelName == null) {
			logger.error("Category names cannot be null: thirdLevelName='{}', secondLevelName='{}'", thirdLevelName, secondLevelName); 
			throw new IllegalArgumentException("Category names cannot be null. thirdLevelName=" + thirdLevelName + ", secondLevelName=" + secondLevelName);
		 }
		 
		Category secondLevel = categoryRepository.findByName(secondLevelName);
	    if (secondLevel == null) {
	        logger.error("Second-level category '{}' not found", secondLevelName);
	    	throw new IllegalArgumentException("Second level category not found: " + secondLevelName);
		 }

	   
		 Category thirdLevel = categoryRepository.findByNameAndParent(thirdLevelName, secondLevel.getCategoryId() );
		 
		 
		if(thirdLevel == null) {
			thirdLevel = new Category();
			thirdLevel.setName(thirdLevelName);
			thirdLevel.setLevel(3);
			thirdLevel.setDescription("Third child level");
			thirdLevel.setParentCategory(secondLevel);
			
			thirdLevel = categoryRepository.save(thirdLevel);
	        logger.info("Third-level category '{}' created with ID {}", thirdLevelName, thirdLevel.getCategoryId());
		}
		

		return thirdLevel;
	}

	@Override
	public List<Category> getByLevel(int level) {
		logger.debug("Fetching categories at level {}", level);
		return categoryRepository.findByLevel(level);
	}
	
//	@Override
//	public List<Category> getSubcategoryByParentName(String parentName){
//		return categoryRepository.findByParentCategoryName(parentName);
//	}
	
}
