package com.hexaware.quitq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.quitq.entity.Orders;

public interface OrderRepository  extends JpaRepository<Orders, Long>{
	
	@Query("SELECT o FROM Orders o WHERE o.user.id = ?1")
	public List<Orders> findUserOrders(Long userId);
	
	//public List<Orders> getByUserId(Long userId);
	
	@Query("SELECT o FROM Orders o WHERE o.user.id = :sellerId AND o.user.role = 'SELLER'")
	public List<Orders> findOrdersBySellerId(@Param("sellerId") Long sellerId);

}
