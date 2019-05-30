package com.egypt.daily.life.shopping.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Category;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.repository.CategoryRepository;
import com.egypt.daily.life.shopping.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void getVisited(Long productId){
		Product product = productRepository.findById(productId).get();
		product.setProductViews(product.getProductViews()+1);
		productRepository.save(product);
	}
	
	public List<Product> getProductsByMainCategory(String mainCategoryName){
		List<Category> categoryList = categoryRepository.findAllByMainCategoryName(mainCategoryName); 
		List<Product> products = new ArrayList<Product>();
		for(Category category: categoryList){
			for(Product product : category.getProducts()){
				products.add(product);
			}
		}
		return products;
	}
	public List<Product> getProductsBySubCategory(String subCategoryName){
		List<Category> categoryList = categoryRepository.findAllBySubCategoryName(subCategoryName); 
		List<Product> products = new ArrayList<Product>();
		for(Category category: categoryList){
			for(Product product : category.getProducts()){
				products.add(product);
			}
		}
		return products;
	}
	
	public List<Product> sort(List<Product> products, String sortType){
		// 0: Price ASC, 1: Price DESC
		if(sortType.equals("0")){
			Collections.sort(products, Product.Comparators.PRICE);
		}
		if(sortType.equals("1")){
			Collections.sort(products, Product.Comparators.PRICE);
			Collections.reverse(products);
		}
		return products;
	}
	
	public List<Product> getProductsByCategory(Category category){
		return productRepository.findAllByProductCategory(category);
	}
	
	public List<Product> getAllProducts(){
		return  (List<Product>) productRepository.findAll();
	}
	
	public Product getProductById(Long productId){
		return productRepository.findById(productId).get();
	}
	public Product save(Product product){
		product.setProductDate(new Date());
		return productRepository.save(product);
	}
	
	public void delete(Long productId){
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> findTop12ByOrderByProductDateDesc() {
		return productRepository.findTop12ByOrderByProductDateDesc();
	}

	@Override
	public List<Product> findTop12ByOrderBySellCountDesc() {
		return productRepository.findTop12ByOrderBySellCountDesc();
	}

	@Override
	public List<Product> findTop12ByOrderByRatingDesc() {
		return productRepository.findTop12ByOrderByRatingDesc();
	}

	@Override
	public List<Product> findTop12ByOrderByViewsDesc() {
		return productRepository.findAllByOrderByProductViewsDesc();
	}

	@Override
	public List<Product> getAllProductsSlider(Boolean isSlider) {
		return productRepository.findAllByIsSlider(isSlider);
	}
}
