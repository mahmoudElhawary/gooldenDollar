package com.egypt.daily.life.shopping.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.MainCategory;
import com.egypt.daily.life.shopping.service.MainCategoryService;

@RestController
public class AdminMainCategoryController {

	@Autowired
	private MainCategoryService categoryService ;
	
	@GetMapping("/allCategoriesMain")
	public ResponseEntity<List<MainCategory>> getAllCategory2() {
		List<MainCategory> categories = categoryService.getAllCategory() ;
		return new ResponseEntity<List<MainCategory>>(categories,HttpStatus.OK) ;
	}
	@GetMapping("/allMainCategoriesMain")
	public ResponseEntity<List<String>> getAllMainCategory2() {
		List<String> categories = categoryService.getAllMainCategory() ;
		return new ResponseEntity<List<String>>(categories,HttpStatus.OK) ;
	}
	@GetMapping("/allSubCategoriesMain")
	public ResponseEntity<List<String>> getAllSubCategory2() {
		List<String> categories = categoryService.getAllSubCategory() ;
		return new ResponseEntity<List<String>>(categories,HttpStatus.OK) ;
	}
	
	@PostMapping("/SubCategoriesByMainNameMain")
	public ResponseEntity<List<MainCategory>> getSubCategoryMain2(@RequestBody String mainCategory) {
		List<MainCategory> categories = categoryService.findAllByMainCategoryName(mainCategory) ;
		return new ResponseEntity<List<MainCategory>>(categories,HttpStatus.OK) ;
	}
	@PostMapping("/saveCategoryMain")
	public ResponseEntity<Response> createCategory2(@RequestBody MainCategory category) {
		if(category != null) {
			category.setCreatedDate(new Date()); 
			categoryService.save(category);
			return new ResponseEntity<Response>(new Response("this category is saved successfully"),HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
	@DeleteMapping("/deleteCategoryMain/{id}")
	public ResponseEntity<Response> deleteCategory2(@PathVariable("id") Long id) {
		if(id != null) {
			categoryService.delete(id);
			return new ResponseEntity<Response>(new Response("this category is deleted successfuly"),HttpStatus.OK) ;
		} else {
			return new ResponseEntity<Response>(new Response("you dont select an category"),HttpStatus.BAD_REQUEST) ;
		}
	}
	@GetMapping("/getCategoryMain/{id}")
	public ResponseEntity<MainCategory> getCategory2(@PathVariable("id") Long id) {
		if(id != null) {
			MainCategory categoryDB= categoryService.getCategoryById(id) ;
			return new ResponseEntity<MainCategory>(categoryDB,HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
}
