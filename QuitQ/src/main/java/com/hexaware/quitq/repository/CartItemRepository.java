package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.CartItems;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {

}
