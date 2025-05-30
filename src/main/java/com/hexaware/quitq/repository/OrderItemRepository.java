package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.OrderItems;

public interface OrderItemRepository  extends JpaRepository<OrderItems,Long>{

}
