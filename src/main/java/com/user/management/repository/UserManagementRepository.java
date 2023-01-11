package com.user.management.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.management.entity.User;

@Repository
public interface UserManagementRepository extends JpaRepository<User,Serializable> {
	
	
	public User findByEmail(String email);

	public User findByEmailAndPassword(String email,String userPwd);
}
