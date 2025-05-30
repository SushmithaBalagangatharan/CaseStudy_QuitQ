package com.hexaware.quitq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.entity.Category;
import com.hexaware.quitq.service.category.ICategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryControll {
	
	@Autowired
	ICategoryService categoryService;
	
	@PostMapping("/level/first/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createFirstLevels(@PathVariable String topLevelName) {
		return categoryService.createFirstLevels(topLevelName);
	}
	
	@PostMapping("/level/second/{secondLevelName}/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createSecondLevels(@PathVariable String secondLevelName, @PathVariable String topLevelName) {
		return categoryService.createSecondLevels(secondLevelName, topLevelName);
	}

	@PostMapping("/level/third/{thirdLevelName}/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createThirdLevels(@PathVariable String thirdLevelName,@PathVariable  String secondLevelName) {
		return categoryService.createThirdLevels(thirdLevelName, secondLevelName);
	}
	
	@GetMapping("/{level}")
	public List<Category> getByLevel(@PathVariable int level) {
		return categoryService.getByLevel(level);
	}
	
//	@GetMapping("/subcategory/{parentName}")
//	public List<Category> getSubcategories(@PathVariable String parentName){
//		return categoryService.getSubcategoryByParentName(parentName);
//	}
}
