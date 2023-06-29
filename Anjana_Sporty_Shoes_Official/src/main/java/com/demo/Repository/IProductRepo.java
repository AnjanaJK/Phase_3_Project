package com.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.Entity.Product;


@Repository
public interface IProductRepo extends JpaRepository<Product, Integer>{

	// Home page product display
	@Query("SELECT p.image, p.prod_id, p.prod_name, p.prod_desc, c.category_name, p.prod_price FROM Product p JOIN p.category c ORDER BY p.prod_id")
	 List<Object[]> getProductsWithCategory();

	@Query("SELECT p.image, p.prod_id, p.prod_name, p.prod_desc, c.category_name, p.prod_price FROM Product p JOIN p.category c WHERE c.category_id = :categoryId ORDER BY p.prod_id")
	List<Object[]> findByCategory(@Param("categoryId") int categoryId);

}
