package com.hexaware.quitq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
