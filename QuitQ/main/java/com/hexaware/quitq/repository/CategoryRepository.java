package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.quitq.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

	public Category findByName(String name);
	
	@Query("SELECT c FROM Category c WHERE c.name= :name AND c.parentCategory.categoryId = :parentCategoryId")
    Category findByNameAndParent(@Param("name") String name, @Param("parentCategoryId") Long parentCategoryId);
	
	
	@Query("SELECT c FROM Category c WHERE c.level = :level")
	List<Category> findByLevel(@Param("level") int level);
	
	
}
