package edu.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Account;
import edu.poly.entity.Cart;
import edu.poly.key.CartKey;

public interface CartRepository extends JpaRepository<Cart, CartKey>{
	
	List<Cart> findByAccountCart(Account account);
}
