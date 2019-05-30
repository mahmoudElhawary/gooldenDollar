package com.egypt.daily.life.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.UserOrderItem;

@Repository
public interface UserOrderItemRepository extends CrudRepository <UserOrderItem, Long>{

}
