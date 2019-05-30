package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.MainCategory;


public interface MainCategoryService {
	
	List<MainCategory> getAllCategory();
	
	List<String> getAllMainCategory();

	List<String> getAllSubCategory();
	
	List<MainCategory> findAllByMainCategoryName(String mainCategoryName);
	
	MainCategory save(MainCategory category);
	
	void delete(Long categoryId);
	
	MainCategory getCategoryById(Long categoryId);
}
