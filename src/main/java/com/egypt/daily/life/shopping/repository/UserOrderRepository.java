package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserOrder;

@Repository
public interface UserOrderRepository extends CrudRepository<UserOrder, Long>{

	List<UserOrder> findAllByUser(User user);
}
