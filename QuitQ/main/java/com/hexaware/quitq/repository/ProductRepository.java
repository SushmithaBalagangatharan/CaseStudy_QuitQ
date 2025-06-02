package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.quitq.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE p.category.name = :category")
	public List<Product> getProductByCategory(@Param("category") String category);
	

	List<Product> findByIsDeletedFalse();
}
