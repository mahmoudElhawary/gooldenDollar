package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.ProductComment;

@Repository
public interface ProductCommentRepository extends CrudRepository<ProductComment, Long>{
	List<ProductComment> findByProduct(Product product);
}
