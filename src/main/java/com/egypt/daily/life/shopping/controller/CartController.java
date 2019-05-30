package com.egypt.daily.life.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egypt.daily.life.shopping.domain.Response;
import com.egypt.daily.life.shopping.model.Cart;
import com.egypt.daily.life.shopping.model.CartItem;
import com.egypt.daily.life.shopping.model.ContactUs;
import com.egypt.daily.life.shopping.model.MainCategory;
import com.egypt.daily.life.shopping.model.Product;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.CartItemRepository;
import com.egypt.daily.life.shopping.repository.CartRepository;
import com.egypt.daily.life.shopping.repository.ProductRepository;
import com.egypt.daily.life.shopping.repository.UserRepository;
import com.egypt.daily.life.shopping.service.CartService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@GetMapping("getAllCarts")
	public ResponseEntity<List<Cart>> getAllCarts() {
		return new ResponseEntity<List<Cart>>(cartService.findAll(), HttpStatus.OK);
	}

	@GetMapping("getCart")
	public ResponseEntity<Cart> getCart(@PathVariable("id") Long id) {
		return new ResponseEntity<Cart>(cartService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/cartByUser")
	public ResponseEntity<List<Cart>> getCartByUser(@RequestBody User user) {
		List<Cart> cartDB =cartService.findByUser(user) ;
		return new ResponseEntity<List<Cart>>(cartDB,HttpStatus.OK) ;
	}
	@PostMapping("/saveCart")
	public ResponseEntity<Cart> saveCart(@RequestParam("product") String product, @RequestParam("user") String user)
			throws JsonParseException, JsonMappingException, IOException {
		// get user data from rest api
		User userData = new ObjectMapper().readValue(user, User.class);
		// get product data from rest api
		Product productData = new ObjectMapper().readValue(product, Product.class);
		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cartItem = new CartItem();
		cartItem.setProduct(productData);
		cartItem.setTotalPriceDouble(productData.getProductPrice() * cartItem.getQuantity());
		cartItems.add(cartItem);
		Cart cart = new Cart();
		cart.setUser(userData);
		cart.setCartItems(cartItems);
		cartItem.setCart(cart);
		Cart cartDB = cartService.save(cart);
		return new ResponseEntity<Cart>(cartDB, HttpStatus.OK);
	}

	@DeleteMapping("deleteCart/{id}")
	public ResponseEntity<Response> deleteCart(@PathVariable("id") Long id) {
		cartService.delete(id);
		return new ResponseEntity<Response>(new Response("cart is removed"), HttpStatus.OK);
	}
}
