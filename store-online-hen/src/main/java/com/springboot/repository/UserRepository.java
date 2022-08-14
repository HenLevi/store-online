package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();

	User findByEmail(String email);

	User findUserByEmail(String email);
}