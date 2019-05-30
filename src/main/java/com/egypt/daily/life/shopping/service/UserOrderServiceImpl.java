package com.egypt.daily.life.shopping.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.CartItem;
import com.egypt.daily.life.shopping.model.ShippingAddress;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.model.UserOrder;
import com.egypt.daily.life.shopping.model.UserOrderItem;
import com.egypt.daily.life.shopping.model.UserOrderShippingAddress;
import com.egypt.daily.life.shopping.repository.CartItemRepository;
import com.egypt.daily.life.shopping.repository.ShippingAddressRepository;
import com.egypt.daily.life.shopping.repository.UserOrderItemRepository;
import com.egypt.daily.life.shopping.repository.UserOrderRepository;
import com.egypt.daily.life.shopping.repository.UserOrderShippingAddressRepository;

@Service
public class UserOrderServiceImpl implements UserOrderService{
	
	@Autowired
    private CartService cartService;
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private UserOrderShippingAddressRepository userOrderShippingAddressRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private UserOrderItemRepository userOrderItemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
	
	public double getUserOrderGrandTotalByCart(Cart cart) {
        double grandTotal = 0;
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            grandTotal += item.getTotalPriceDouble();
        }

        return grandTotal;
    }
	
	public void addOrderDumpCart(UserOrderShippingAddress userOrderShippingAddress,
									UserOrder userOrder, Cart cart) throws IOException{
		
		if(userOrderShippingAddress == null || userOrder == null || cart == null){
			throw new IOException();
		}
		// initiate customerOrderShippingAddress
		ShippingAddress shippingAddress = shippingAddressRepository.findById(userOrderShippingAddress.getOriginalShippingAddressId()).get();
		userOrderShippingAddress.setAddress(shippingAddress.getAddress());
		userOrderShippingAddress.setCity(shippingAddress.getCity());
		userOrderShippingAddress.setCountry(shippingAddress.getCountry());
		userOrderShippingAddress.setFullName(shippingAddress.getFullName());
		userOrderShippingAddress.setPhoneNumber(shippingAddress.getPhoneNumber());
		userOrderShippingAddress.setState(shippingAddress.getState());
		userOrderShippingAddress.setZipCode(shippingAddress.getZipCode());
		
		// initiate customer order
		userOrder.setUser(cart.getUser());
		userOrder.setOrderDate(new Date());
		userOrder.setOrderTotalPrice(cart.getGrandTotal());
		// for mapping orderItem table
		userOrderRepository.save(userOrder);
		cart.setGrandTotal(0);
		cartService.save(cart);
		// dump cartItem to orderItem, empty cart
		for(CartItem cartItem : cart.getCartItems()){
			UserOrderItem userOrderItem = new UserOrderItem();
			userOrderItem.setUserOrder(userOrder);
			userOrderItem.setProductId(cartItem.getProduct().getProductId());
			userOrderItem.setProductName(cartItem.getProduct().getProductName());
			userOrderItem.setProductPrice(cartItem.getProduct().getProductPrice());
			userOrderItem.setProductQuantity(cartItem.getQuantity());
			userOrderItemRepository.save(userOrderItem);
			cartItemRepository.delete(cartItem);
		}
		// for mapping customerOrder table
		userOrderShippingAddressRepository.save(userOrderShippingAddress);
		
		userOrder.setUserOrderShippingAddress(userOrderShippingAddress);
		userOrderRepository.save(userOrder);
		
		userOrderShippingAddress.setUserOrder(userOrder);
		userOrderShippingAddressRepository.save(userOrderShippingAddress);
	}
	
	public List<UserOrder> getAllUserOrderByUser(User user){
		return userOrderRepository.findAllByUser(user);
	}

}
