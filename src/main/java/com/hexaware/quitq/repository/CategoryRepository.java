package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.quitq.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

	public Category findByName(String name);
	
	@Query("Select c from Category c Where c.name= :name And c.parentCategory.categoryId = :parentCategoryId")
    Category findByNameAndParent(@Param("name") String name, @Param("parentCategoryId") Long parentCategoryId);
	
	
	@Query("SELECT c FROM Category c WHERE c.level = :level")
	List<Category> findByLevel(@Param("level") int level);
	
	
//	@Query("SELECT c FROM Category c WHERE parent_category_id = (SELECT category_id FROM category WHERE name = :name)")
//	List<Category> findByParentCategoryName(@Param("name") String name);
}
