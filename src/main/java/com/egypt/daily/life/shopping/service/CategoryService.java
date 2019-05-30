package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Category;


public interface CategoryService {
	
	List<Category> getAllCategory();
	
	List<String> getAllMainCategory();

	List<String> getAllSubCategory();
	
	List<Category> findAllByMainCategoryName(String mainCategoryName);
	
	Category save(Category category);
	
	void delete(Long categoryId);
	
	Category getCategoryById(Long categoryId);
}
