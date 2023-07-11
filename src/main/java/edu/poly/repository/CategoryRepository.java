package edu.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{
	
	List<Category> findByIsDelete(Boolean isDelete);

	List<Category> findAllByIdLike(String name);
}
