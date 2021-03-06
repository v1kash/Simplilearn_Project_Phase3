package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	List<Product> findByBrand(String name);
	List<Product> findBySeason(String name);
	List<Product> findByCategory(String name);
	List<Product> findByColor(String name);
}