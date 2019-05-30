package com.egypt.daily.life.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepostitory;
	
	public List<String> getAllSubCategory(){
		return categoryRepostitory.findAllSubCategoryName();
	}
	
	public List<String> getAllMainCategory(){
		return categoryRepostitory.findAllMainCategoryName();
	}
	
	public List<Category> getAllCategory(){
		return (List<Category>) categoryRepostitory.findAll();
	}
	
	public Category save(Category category){
		return categoryRepostitory.save(category);
	}
	
	public void delete(Long categoryId){
		categoryRepostitory.deleteById(categoryId);
	}
	
	public Category getCategoryById(Long categoryId){
		return categoryRepostitory.findById(categoryId).get();
	}

	@Override
	public List<Category> findAllByMainCategoryName(String mainCategoryName) {
		return categoryRepostitory.findAllByMainCategoryName(mainCategoryName);
	}
}
