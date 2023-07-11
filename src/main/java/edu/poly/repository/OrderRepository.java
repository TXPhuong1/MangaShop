package edu.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
