package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Orders;

public interface OrderRepository  extends JpaRepository<Orders, Long>{

}
