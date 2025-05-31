package com.hexaware.quitq.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.quitq.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

	Optional<Address> findByUserId(Long userId);
}
