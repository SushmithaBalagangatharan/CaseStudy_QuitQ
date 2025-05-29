package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.quitq.entity.UserInfo;

public interface UserRepository  extends JpaRepository<UserInfo, Long>{

	//@Query("SELECT u FROM users u WHERE u.email = ?1")
	UserInfo findByEmail(String email);
	
	UserInfo findByUserName(String username);


}
