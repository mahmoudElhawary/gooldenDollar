package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;


public interface ProductService {
	
	void getVisited(Long productId);
	
	List<Product> getProductsByMainCategory(String mainCategoryName);
	
	List<Product> getProductsBySubCategory(String subCategoryName);
	
	List<Product> getProductsByCategory(Category category);
	
	List<Product> findTop12ByOrderByProductDateDesc();

    List<Product> findTop12ByOrderBySellCountDesc();

    List<Product> findTop12ByOrderByRatingDesc();
    
    List<Product> findTop12ByOrderByViewsDesc();
	
	List<Product> getAllProducts();
	
	List<Product> getAllProductsSlider(Boolean isSlider);
	
	Product getProductById(Long productId);
	
	Product save(Product product);
	
	void delete(Long productId);
	
	List<Product> sort(List<Product> products, String sortType);
}
