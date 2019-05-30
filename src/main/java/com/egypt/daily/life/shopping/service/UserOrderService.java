package com.egypt.daily.life.shopping.service;

import java.io.IOException;
import java.util.List;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserOrder;
import com.egypt.daily.life.shopping.model.UserOrderShippingAddress;

public interface UserOrderService {
	
	double getUserOrderGrandTotalByCart(Cart cart);
	
	void addOrderDumpCart(UserOrderShippingAddress userOrderShippingAddress, UserOrder userOrder, Cart cart) throws IOException;
	
	List<UserOrder> getAllUserOrderByUser(User user);
}
