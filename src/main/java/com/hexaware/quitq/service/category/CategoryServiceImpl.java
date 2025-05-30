package com.hexaware.quitq.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.quitq.entity.Category;
import com.hexaware.quitq.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category createFirstLevels(String topLevelName) {
		Category topLevel = categoryRepository.findByName(topLevelName);
		if(topLevel == null) {
			topLevel = new Category();
			topLevel.setName(topLevelName);;
			topLevel.setLevel(1);
			topLevel.setDescription("Parent level");
			topLevel = categoryRepository.save(topLevel);
		}
		
		return topLevel;
	}

	@Override
	public Category createSecondLevels(String secondLevelName, String topLevelName) {
		Category topLevel = categoryRepository.findByName(topLevelName);
	    if (topLevel == null) {
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
		}
				
		return secondLevel;
	}

	@Override
	public Category createThirdLevels(String thirdLevelName, String secondLevelName) {
		 if (thirdLevelName == null || secondLevelName == null) {
		        throw new IllegalArgumentException("Category names cannot be null. thirdLevelName=" + thirdLevelName + ", secondLevelName=" + secondLevelName);
		 }
		 
		Category secondLevel = categoryRepository.findByName(secondLevelName);
	    if (secondLevel == null) {
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
		}
		

		return thirdLevel;
	}

	@Override
	public List<Category> getByLevel(int level) {
		return categoryRepository.findByLevel(level);
	}
	
//	@Override
//	public List<Category> getSubcategoryByParentName(String parentName){
//		return categoryRepository.findByParentCategoryName(parentName);
//	}
	
}
