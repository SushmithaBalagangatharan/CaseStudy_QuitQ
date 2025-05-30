package com.hexaware.quitq.service.category;

import java.util.List;

import com.hexaware.quitq.entity.Category;

public interface ICategoryService {
	
	Category createFirstLevels(String topLevelName);
	Category createSecondLevels(String secondLevelName, String topLevel);
	Category createThirdLevels(String thirdLevelName, String secondLevelName);
	List<Category> getByLevel(int level);
//	List<Category> getSubcategoryByParentName(String parentName);
}
