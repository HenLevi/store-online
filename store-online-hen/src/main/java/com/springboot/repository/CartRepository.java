package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Cart;
import com.springboot.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

	List<Cart> deleteByUser(User user);
}