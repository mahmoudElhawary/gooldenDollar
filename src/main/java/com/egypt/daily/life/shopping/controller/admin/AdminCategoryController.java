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
import com.egypt.daily.life.shopping.service.CategoryService;

@RestController
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService ;
	
	
	@GetMapping("/allCategories")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categories = categoryService.getAllCategory() ;
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK) ;
	}
	@GetMapping("/allMainCategories")
	public ResponseEntity<List<String>> getAllMainCategory() {
		List<String> categories = categoryService.getAllMainCategory() ;
		return new ResponseEntity<List<String>>(categories,HttpStatus.OK) ;
	}
	@GetMapping("/allSubCategories")
	public ResponseEntity<List<String>> getAllSubCategory() {
		List<String> categories = categoryService.getAllSubCategory() ;
		return new ResponseEntity<List<String>>(categories,HttpStatus.OK) ;
	}
	@GetMapping("/SubCategoriesByMainName")
	public ResponseEntity<List<Category>> getSubCategory(@RequestParam("mainCategoryName") String mainCategory) {
		List<Category> categories = categoryService.findAllByMainCategoryName(mainCategory) ;
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK) ;
	}
	@PostMapping("/saveCategory")
	public ResponseEntity<Response> createCategory(@RequestBody Category category) {
		if(category != null) {
			category.setCreatedDate(new Date());
			categoryService.save(category);
			return new ResponseEntity<Response>(new Response("this category is saved successfully"),HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<Response> deleteCategory(@PathVariable("id") Long id) {
		if(id != null) {
			categoryService.delete(id);
			return new ResponseEntity<Response>(new Response("this category is deleted successfuly"),HttpStatus.OK) ;
		} else {
			return new ResponseEntity<Response>(new Response("you dont select an category"),HttpStatus.BAD_REQUEST) ;
		}
	}
	@GetMapping("/getCategory/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
		if(id != null) {
			Category categoryDB= categoryService.getCategoryById(id) ;
			return new ResponseEntity<Category>(categoryDB,HttpStatus.OK) ;
		} else {
			return null ;
		}
	}
}
