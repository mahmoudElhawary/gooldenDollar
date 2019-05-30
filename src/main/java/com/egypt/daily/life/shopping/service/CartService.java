package com.egypt.daily.life.shopping.service;

import java.io.IOException;
import java.util.List;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.User;

public interface CartService {
	
	Cart validate(Object id) throws IOException;
	
	void emptyCart(Cart cart);
	
	Cart save(Cart cart);

	List<Cart> findAll();

	Cart findById(Long id);

	List<Cart> findByUser(User user) ;
	
	void delete(Long id);
}
