package com.hexaware.quitq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.quitq.entity.Category;
import com.hexaware.quitq.service.category.ICategoryService;

/*
 * @author Sushmitha B A
 * @description Category ReST Controller which contains end-points to handle HTTP request and provide response
 * @date 2-06-2025
 * @version 1.0
 */


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	Logger logger = LoggerFactory.getLogger("CategoryController.class");
	
	@PostMapping("/level/first/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createFirstLevels(@PathVariable String topLevelName) {
		logger.info("Creating Top Level Category of "+ topLevelName);
		return categoryService.createFirstLevels(topLevelName);
	}
	
	@PostMapping("/level/second/{secondLevelName}/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createSecondLevels(@PathVariable String secondLevelName, @PathVariable String topLevelName) {
		logger.info("Creating Second Level Category of "+ secondLevelName);
		return categoryService.createSecondLevels(secondLevelName, topLevelName);
	}

	@PostMapping("/level/third/{thirdLevelName}/{topLevelName}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Category createThirdLevels(@PathVariable String thirdLevelName,@PathVariable  String secondLevelName) {
		logger.info("Creating Third Level Category of "+ thirdLevelName);
		return categoryService.createThirdLevels(thirdLevelName, secondLevelName);
	}
	
	@GetMapping("/{level}")
	public List<Category> getByLevel(@PathVariable int level) {
		logger.info("Retreiving category using level"+ level);
		return categoryService.getByLevel(level);
	}
	

}
