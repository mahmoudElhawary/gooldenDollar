package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.MainCategory;

@Repository
public interface MainCategoryRepository extends CrudRepository<MainCategory, Long>{
	
	List<MainCategory> findAllByMainCategoryName(String mainCategoryName);
	
	List<MainCategory> findAllBySubCategoryName(String subCategoryName);
	
	@Query(value = "SELECT DISTINCT sub_category_name from main_category" , nativeQuery = true)
	List<String> findAllSubCategoryName();
	
	@Query(value = "SELECT DISTINCT main_category_name from main_category" , nativeQuery = true)
	List<String> findAllMainCategoryName();
}
