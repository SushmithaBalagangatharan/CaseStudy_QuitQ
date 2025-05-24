package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

}
