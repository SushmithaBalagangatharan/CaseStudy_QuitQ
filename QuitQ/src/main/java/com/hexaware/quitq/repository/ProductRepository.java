package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
