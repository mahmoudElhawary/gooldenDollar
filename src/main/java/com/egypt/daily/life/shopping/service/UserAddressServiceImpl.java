package com.egypt.daily.life.shopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.ShippingAddress;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.ShippingAddressRepository;

@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	@Autowired
	private UserService userService;
	
	public List<ShippingAddress> getAllShippingAddressByCustomerId(Object id) {
		User user = userService.findOne((Long) id);
		return shippingAddressRepository.findAllByUser(user);
	}
	
	@Transactional
	public void addShippingAddressObject(Object id, ShippingAddress shippingAddress){
		User user = userService.findOne((Long)id);
		// if there is no default shipping address, then set it up
		shippingAddress.setIsDefault(true);
		Hibernate.initialize(user.getShippingAddresses());
		for(ShippingAddress shippingAddress_ : user.getShippingAddresses()){
			if(shippingAddress_.getIsDefault()){
				shippingAddress.setIsDefault(false);
				break;
			}
		}
		shippingAddress.setUser(user); 
		shippingAddressRepository.save(shippingAddress);
	}
}
