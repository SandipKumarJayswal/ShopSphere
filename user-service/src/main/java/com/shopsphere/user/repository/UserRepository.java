package com.shopsphere.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopsphere.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	

}
