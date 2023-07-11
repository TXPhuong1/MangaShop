package edu.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	List<Account> findAllByFullnameLike(String fullname);
}
