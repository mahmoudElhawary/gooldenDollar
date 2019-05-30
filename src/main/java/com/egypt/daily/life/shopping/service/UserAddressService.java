package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.ShippingAddress;


public interface UserAddressService {
	
	List<ShippingAddress> getAllShippingAddressByCustomerId(Object id);
	
	void addShippingAddressObject(Object id, ShippingAddress shippingAddress);
}
