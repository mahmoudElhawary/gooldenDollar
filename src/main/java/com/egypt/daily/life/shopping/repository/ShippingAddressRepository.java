package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.ShippingAddress;
import com.egypt.daily.life.shopping.model.User;

@Repository
public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long>{
	
	List<ShippingAddress> findAllByUser(User user);
	
}
