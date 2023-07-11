package edu.poly.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.entity.Category;
import edu.poly.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByPriceBetween(double min, double max);
	
	@Query("select o from Product o where o.isDelete = false order by NEWID() limit 8")
	List<Product> findAllRandom();
	
	Page<Product> findAllByNameLikeAndCategoryIdLikeAndPriceBetween(String name, String category, Integer price1, Integer price2, Pageable page);
	
	Page<Product> findAllByNameLikeAndCategoryIdLikeAndPriceGreaterThanEqual(String name, String category, Integer price, Pageable page);
	
	Page<Product> findAllByNameLikeAndCategoryIdLike(String name, String category, Pageable page);
	
	List<Product> findAllBynameLike(String name);
	
	List<Product> findByCategory(Category category);

}
