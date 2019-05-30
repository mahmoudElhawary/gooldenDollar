package com.egypt.daily.life.shopping.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.CartItem;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.CartItemRepository;
import com.egypt.daily.life.shopping.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	public Cart getCartByCartId(Long cartId){
		return cartRepository.findById(cartId).get();
	}
	
	public void update(Cart cart){
        double grandTotal = userOrderService.getUserOrderGrandTotalByCart(cart);
        Double truncatedDouble = new BigDecimal(grandTotal)
                .setScale(3, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        cart.setGrandTotal(truncatedDouble);

        cartRepository.save(cart);
    }
	
	public Cart validate(Object cartId) throws IOException{
		if(cartId == null){
			throw new IOException("Please Login.");
		}

		Cart cart = cartRepository.findById((Long) cartId).get();
		if(cart == null || cart.getCartItems().size() == 0){
			throw new IOException("cart null or cartItem size == 0.c");
		}
		update(cart);
		return cart;
	}
	
	public void emptyCart(Cart cart){
		for(CartItem cartItem : cart.getCartItems()){
			cartItemRepository.delete(cartItem);
		}
		cart.setGrandTotal(0);
		cartRepository.save(cart);
	}
	
	public Cart save(Cart cart){
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		return (List<Cart>) cartRepository.findAll();
	}

	@Override
	public Cart findById(Long id) {
		return cartRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		cartRepository.deleteById(id);
	}

	@Override
	public List<Cart> findByUser(User user) {
		return cartRepository.findByUser(user);
	}

}
