package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
