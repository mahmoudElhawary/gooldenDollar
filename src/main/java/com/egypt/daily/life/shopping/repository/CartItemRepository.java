package com.egypt.daily.life.shopping.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.CartItem;

@Repository
@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
