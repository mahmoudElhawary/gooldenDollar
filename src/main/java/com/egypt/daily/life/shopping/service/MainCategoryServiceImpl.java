package com.egypt.daily.life.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.MainCategory;
import com.egypt.daily.life.shopping.repository.MainCategoryRepository;


@Service
public class MainCategoryServiceImpl implements MainCategoryService{

	@Autowired
	private MainCategoryRepository categoryRepostitory;
	
	public List<String> getAllSubCategory(){
		return categoryRepostitory.findAllSubCategoryName();
	}
	
	public List<String> getAllMainCategory(){
		return categoryRepostitory.findAllMainCategoryName();
	}
	
	public List<MainCategory> getAllCategory(){
		return (List<MainCategory>) categoryRepostitory.findAll();
	}
	
	public MainCategory save(MainCategory category){
		return categoryRepostitory.save(category);
	}
	
	public void delete(Long categoryId){
		categoryRepostitory.deleteById(categoryId);
	}
	
	public MainCategory getCategoryById(Long categoryId){
		return categoryRepostitory.findById(categoryId).get();
	}

	@Override
	public List<MainCategory> findAllByMainCategoryName(String mainCategoryName) {
		return categoryRepostitory.findAllByMainCategoryName(mainCategoryName);
	}
}
