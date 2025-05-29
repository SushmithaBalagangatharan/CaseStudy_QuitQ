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
	public List<Product> findProductByCategory(@Param("category") String category);
	
	
//	@Query("SELECT p FROM Product p " +
//		    "WHERE (p.category.name = :category OR :category = '') " +
//		    "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
//		    "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount)"+
//			"AND (:stock IS NULL OR (:stock = 'in' AND p.stock > 0) OR (:stock = 'out' AND p.stock = 0))")	
//	List<Product> filterProducts(@Param("category") String category,
//            @Param("minPrice") Integer minPrice,
//            @Param("maxPrice") Integer maxPrice,
//            @Param("minDiscount") Integer minDiscount,
//            @Param("stock") String stock,
//            Pageable pageable);
	
	
//	@Query("SELECT p FROM Product p " +
//            "WHERE (p.category.name = :category OR :category = '') " +
//            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) " +
//            "AND (:minDiscount IS NULL OR p.discountPercent >= :minDiscount) " +
//            "ORDER BY " +
//            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
//            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
//     List<Product> filterProducts(@Param("category") String category, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minDiscount") Integer minDiscount, @Param("sort") String sort);
//


}
