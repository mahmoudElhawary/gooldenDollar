package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long>{
	
	List<Product> findAllByOrderByProductViewsDesc();
	
	List<Product> findAllByProductCategory(Category category);
	
	List<Product> findAllByIsSlider(boolean isSlider);
	
	List<Product> findTop12ByOrderByProductDateDesc();

    List<Product> findTop12ByOrderBySellCountDesc();

    List<Product> findTop12ByOrderByRatingDesc();
    
    List<Product> findTop12ByOrderByViewDesc();
    
    List<Product> findAllByNameContaining(String name);
}
